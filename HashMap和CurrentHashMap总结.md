# **HashMap和CurrentHashMap总结:**

1. ##### 谈谈你理解的 HashMap，讲讲其中的 get put 过程, 1.8 做了什么优化？3. 是线程安全的嘛？ 4. 不安全会导致哪些问题？ 5. 如何解决？有没有线程安全的并发容器

    HashMap：它根据键的hashCode值存储数据，大多数情况下可以直接定位到它的值，因而具 有很快的访问速度，但遍历顺序却是不确定的。 HashMap多只允许一条记录的键为null，允许 多条记录的值为null。HashMap非线程安全，即任一时刻可以有多个线程同时写HashMap，可能 会导致数据的不一致。如果需要满足线程安全，可以用 Collections的synchronizedMap方法使 HashMap具有线程安全的能力，或者使用ConcurrentHashMap。

   ##### put 方法(jdk1.7):

   ```java
   public V put(K key, V value) {
           if (table == EMPTY_TABLE) {
               inflateTable(threshold);
           }
           if (key == null)
               return putForNullKey(value);
           int hash = hash(key);
           int i = indexFor(hash, table.length);
           for (Entry<K,V> e = table[i]; e != null; e = e.next) {
               Object k;
               if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                   V oldValue = e.value;
                   e.value = value;
                   e.recordAccess(this);
                   return oldValue;
               }
           }
   
           modCount++;
           addEntry(hash, key, value, i);
           return null;
       }
   ```

   - 判断当前数组是否需要初始化。 

   - 如果 key 为空，则 put 一个空值进去。 

   - 根据 key 计算出 hashcode。 根据计算出的 hashcode 定位出所在桶。 

   - 如果桶是一个链表则需要遍历判断里面的 hashcode、key 是否和传入 key 相等， 如果相等则进行覆盖，并返回原来的值。 

   - 如果桶是空的，说明当前位置没有数据存入；新增一个 Entry 对象写入当前位置

     ```java
     void addEntry(int hash, K key, V value, int bucketIndex) {
             if ((size >= threshold) && (null != table[bucketIndex])) {
                 resize(2 * table.length);
                 hash = (null != key) ? hash(key) : 0;
                 bucketIndex = indexFor(hash, table.length);
             }
     
             createEntry(hash, key, value, bucketIndex);
         }
     ```

     当调用 addEntry 写入 Entry 时需要判断是否需要扩容。
     如果需要就进行两倍扩充，并将当前的 key 重新 hash 并定位。
     而在 createEntry 中会将当前位置的桶传入到新建的桶中，如果当前桶有值e'e'e就会在位置 形成链表。

     ##### get方法(JDK1.7)

     ```java
     public V get(Object key) {
             if (key == null)
                 return getForNullKey();
             Entry<K,V> entry = getEntry(key);
     
             return null == entry ? null : entry.getValue();
         }
     final Entry<K,V> getEntry(Object key) {
             if (size == 0) {
                 return null;
             }
     
             int hash = (key == null) ? 0 : hash(key);
             for (Entry<K,V> e = table[indexFor(hash, table.length)];
                  e != null;
                  e = e.next) {
                 Object k;
                 if (e.hash == hash &&
                     ((k = e.key) == key || (key != null && key.equals(k))))
                     return e;
             }
             return null;
         }
     ```

     - 首先也是根据 key 计算出 hashcode，然后定位到具体的桶中。 

     - 判断该位置是否为链表。 不是链表就根据 key、key 的 hashcode 是否相等来返回值。 

     - 为链表则需要遍历直到 key 及 hashcode 相等时候就返回值。 

     - 啥都没取到就直接返回 null 。

       

       **缺陷:当 Hash 冲突严重时，在桶上形成的链表会变的越来越长，这样在查询时的效率就 会越来越低；时间复杂度为 O(N) 。**

       ##### JDK1.8的优化:

       和 1.7 大体上都差不多，还是有几个重要的区别：
       TREEIFY_THRESHOLD 用于判断是否需要将链表转换为红黑树的阈值。 

       HashEntry 修改为 Node。Node 的核心组成其实也是和 1.7 中的 HashEntry 一样，存放的都是 key value hashcode next 等数据

       ##### put方法(JDK1.8)

       ```java
       final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                          boolean evict) {
               Node<K,V>[] tab; Node<K,V> p; int n, i;
               if ((tab = table) == null || (n = tab.length) == 0)
                   n = (tab = resize()).length;//判断当前桶是否为空，空的就需要初始化
               if ((p = tab[i = (n - 1) & hash]) == null)
                   tab[i] = newNode(hash, key, value, null);
           //上面根据当前 key 的 hashcode 定位到具体的桶中并判断是否为空，为空表明没有 Hash 冲突就直接在当前位置创建一个新桶即可。
               else {
                   Node<K,V> e; K k;
                   if (p.hash == hash &&
                       ((k = p.key) == key || (key != null && key.equals(k))))
                       e = p;
                   else if (p instanceof TreeNode)
                       e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                   else {
                       for (int binCount = 0; ; ++binCount) {
                           if ((e = p.next) == null) {
                               p.next = newNode(hash, key, value, null);
                               if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                                   treeifyBin(tab, hash);
                               break;
                           }
                           if (e.hash == hash &&
                               ((k = e.key) == key || (key != null && key.equals(k))))
                               break;
                           p = e;
                       }
                   }
                   if (e != null) { // existing mapping for key
                       V oldValue = e.value;
                       if (!onlyIfAbsent || oldValue == null)
                           e.value = value;
                       afterNodeAccess(e);
                       return oldValue;
                   }
               }
               ++modCount;
               if (++size > threshold)
                   resize();
               afterNodeInsertion(evict);
               return null;
           }
       ```

       

       1. 判断当前桶是否为空，空的就需要初始化（resize 中会判断是否进行初始化）。

       2. 根据当前 key 的 hashcode 定位到具体的桶中并判断是否为空，为空表明没有 Hash 冲突就直接在当前位置创建一个新桶即可。

       3. 如果当前桶有值（ Hash 冲突），那么就要比较当前桶中的 key、key 的 hashcode 与写入的 key 是否相等，相等就赋值给 e ,在第 8 步的时候会统一进行 赋值及返回。

       4. 如果当前桶为红黑树，那就要按照红黑树的方式写入数据。

       5. 如果是个链表，就需要将当前的 key、value 封装成一个新节点写入到当前桶的后 面（形成链表）。

       6. 接着判断当前链表的大小是否大于预设的阈值，大于时就要转换为红黑树。

       7. 如果在遍历过程中找到 key 相同时直接退出遍历。

       8. 如果 e != null 就相当于存在相同的 key,那就需要将值覆盖。

       9. 最后判断是否需要进行扩容。

          ##### get方法(JDK1.8)

          ```java
          public V get(Object key) {
                  Node<K,V> e;
                  return (e = getNode(hash(key), key)) == null ? null : e.value;
              }
           final Node<K,V> getNode(int hash, Object key) {
                  Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
                  if ((tab = table) != null && (n = tab.length) > 0 &&
                      (first = tab[(n - 1) & hash]) != null) {
                      if (first.hash == hash && // always check first node
                          ((k = first.key) == key || (key != null && key.equals(k))))
                          return first;
                      if ((e = first.next) != null) {
                          if (first instanceof TreeNode)
                              return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                          do {
                              if (e.hash == hash &&
                                  ((k = e.key) == key || (key != null && key.equals(k))))
                                  return e;
                          } while ((e = e.next) != null);
                      }
                  }
                  return null;
              }
          ```

          - 首先将 key hash 之后取得所定位的桶。 

          - 如果桶为空则直接返回 null 。 

          - 否则判断桶的第一个位置(有可能是链表、红黑树)的 key 是否为查询的 key，是就 直接返回 value。

          - 如果第一个不匹配，则判断它的下一个是红黑树还是链表。 

          - 红黑树就按照树的查找方式返回值。 不然就按照链表的方式遍历匹配返回值。

            **从这两个核心方法（get/put）可以看出 1.8 中对大链表做了优化，修改为红黑树之后查 询效率直接提高到了 O(logn) 。**

            **但是 HashMap 原有的问题也都存在，比如在并发场景下使用时容易出现死循环。**

             HashMap 扩容的时候会调用 resize() 方法，就是这里的并发操 作容易在一个桶上形成环形链表；这样当获取一个不存在的 key 时，计算出的 index 正 好是环形链表的下标就会出现死循环。

            **无论是 1.7 还是 1.8 其实都能看出 JDK 没有对它做任何 的同步操作，所以并发会出问题，甚至出现死循环导致系统不可用。**

2. ##### ConcurrentHashMap 是如何实现的？ 1.7、1.8 实现有何不同？为什么这么做？

   ##### 是由 Segment 数组、HashEntry 组成，和 HashMap 一样，仍然是数组加 链表。

   **和 HashMap 非常类似，唯一的区别就是其中的核心数据如 value ，以及链表都是 volatile 修饰的，保证了获取时的可见性。**
   **原 理 上 来 说 ： ConcurrentHashMap 采 用 了 分 段 锁 技 术 ， 其 中 Segment 继 承 于 ReentrantLock。不会像 HashTable 那样不管是 put 还是 get 操作都需要做同步处 理，理论上 ConcurrentHashMap 支持 CurrencyLevel (Segment 数组数量)的线程 并发。每当一个线程占用锁访问一个 Segment 时，不会影响到其他的 Segment。**

put方法(JDK1.7)

- 首先是通过 key 定位到 Segment，之后在对应的 Segment 中进行具体的 put。

- 虽然 HashEntry 中的 value 是用 volatile 关键词修饰的，但是并不能保证并发的原子 性，所以 put 操作时仍然需要加锁处理。

- 首 先 第 一 步 的 时 候 会 尝 试 获 取 锁 ， 如 果 获 取 失 败 肯 定 就 有 其 他 线 程 存 在 竞 争 ， 则 利 用 scanAndLockForPut() 自旋获取锁。1. 尝试自旋获取锁。 2. 如果重试的次数达到了 MAX_SCAN_RETRIES 则改为阻塞锁获取，保证能获取成功

  **put流程**

  1. 将当前 Segment 中的 table 通过 key 的 hashcode 定位到 HashEntry。 2. 遍历该 HashEntry，如果不为空则判断传入的 key 和当前遍历的 key 是否相等， 相等则覆盖旧的 value。 3. 不为空则需要新建一个 HashEntry 并加入到 Segment 中，同时会先判断是否需要 扩容。 4. 最后会解除在 1 中所获取当前 Segment 的锁。

get方法(JDK1.7)

get 逻辑比较简单：
只需要将 Key 通过 Hash 之后定位到具体的 Segment ，再通过一次 Hash 定位到具 体的元素上。
由于 HashEntry 中的 value 属性是用 volatile 关键词修饰的，保证了内存可见性，所 以每次获取时都是最新值。
ConcurrentHashMap 的 get 方法是非常高效的，因为整个过程都不需要加锁

**1.7 已经解决了并发问题，并且能支持 N 个 Segment 这么多次数的并发，但依然存在 HashMap 在 1.7 版本中的问题。那就是查询遍历链表效率太低。因此 1.8 做了一些数据结构上的调整**

**和 1.8 HashMap 结构类似？**
**其中抛弃了原有的 Segment 分段锁，而采用了 CAS + synchronized 来保证并发安 全性,也将 1.7 中存放数据的 HashEntry 改为 Node，但作用都是相同的。其中的 val next 都用了 volatile 修饰，保证了可见性。**

##### put(JDK1.8)

根据 key 计算出 hashcode 。 

判断是否需要进行初始化。 

f 即为当前 key 定位出的 Node，如果为空表示当前位置可以写入数据，利用 CAS 尝试写入，失败则自旋保证成功。 

如果当前位置的 hashcode == MOVED == -1 ,则需要进行扩容。

 如果都不满足，则利用 synchronized 锁写入数据。

 如果数量大于 TREEIFY_THRESHOLD 则要转换为红黑树

##### get(JDK1.8)

根据计算出来的 hashcode 寻址，如果就在桶上那么直接返回值。 

如果是红黑树那就按照树的方式获取值。 就不满足那就按照链表的方式遍历获取值

1.8 在 1.7 的 数 据 结 构 上 做 了 大 的 改 动 ， 采 用 红 黑 树 之 后 可 以 保 证 查 询 效 率 （ O(logn) ），甚至取消了 ReentrantLock 改为了 synchronized，这样可以 看出在新版的 JDK 中对 synchronized 优化是很到位的。

**第二节** **Redis****的安装**

**2.1** **下载**

从官网下载，[Redis官网点击下载](http://download.redis.io/releases/)

通过SecureCRT将下载的文件上传到/root目录(目录根据自己的实际情况选择,这个目录只是解压目录,不是安装目录)

解压&编译&安装

cd /opt/work 切换到指定目录
 tar -zxvf redis-3.2.11.tar.gz   解压
 cd redis-3.2.11     切换到解压目录
 make   编译
 make PREFIX=/opt/work/redis install   安装

源码目录分析：
 在/opt/work/src/redis3.2/下有一个redis.conf文件，这个文件为redis核心配置文件。
 在/opt/work/src/redis3.2/src/下，有redis的常用命令，安装完成后，会将这些命令自动放入到安装路径下的bin目录下
 在/opt/work/src/redis3.2/utils/下，有redis的服务启动脚本

**2.2** **配置和启动**

redis.conf是redis的配置文件，redis.conf在redis源码目录。

注意修改port作为redis进程的端口,port默认6379。

拷贝配置文件到安装目录下 

进入源码目录，里面有一份配置文件 redis.conf，然后将其拷贝到安装路径下

cd /opt/work/redis 切换目录
 mkdir conf 创建配置文件的目录
 cp /opt/work/redis-3.2.11/redis.conf /opt/work/redis/conf 复制配置文件
 ./redis-server 启动

redis关闭的方式

pkill redis server
 kill 进程号
 /opt/work/redis/bin/redis-cli shutdown

注意：这里直接执行Redis-server 启动的Redis服务，是在前台直接运行的(效果如上图)，也就是说，执行完该命令后，如果Lunix关闭当前会话，则Redis服务也随即关闭。正常情况下，启动Redis服务需要从后台启动，并且指定启动配置文件。

**2.3** **后端模式启动**

修改redis.conf配置文件， daemonize yes 以后端模式启动

执行如下命令启动redis：
 vim /opt/work/redis/conf/redis.conf   编辑redis的配置文件，修改daemonize yes 保存即可

ps aux|grep redis 查询redis是否启动
 /opt/work/redis/bin/redis-server 启动redis

redis.conf配置文件详解

4.1. Redis默认不是以守护进程的方式运行，可以通过该配置项修改，使用yes启用守护进程
 ​
   daemonize no
 ​
 4.2. 当Redis以守护进程方式运行时，Redis默认会把pid写入/var/run/redis.pid文件，可以通过pidfile指定
 ​
   pidfile /var/run/redis.pid
 ​
 4.3. 指定Redis监听端口，默认端口为6379，作者在自己的一篇博文中解释了为什么选用6379作为默认端口，因为6379在手机按键上MERZ对应的号码，而MERZ取自意大利歌女Alessia Merz的名字
 ​
   port 6379
 ​
 4.4. 绑定的主机地址
 ​
   bind 127.0.0.1
 ​
 4.5.当 客户端闲置多长时间后关闭连接，如果指定为0，表示关闭该功能
   timeout 300
 ​
 4.6. 指定日志记录级别，Redis总共支持四个级别：debug、verbose、notice、warning，默认为verbose
   loglevel verbose
 ​
 4.7. 日志记录方式，默认为标准输出，如果配置Redis为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给/dev/null
   logfile stdout
 ​
 4.8. 设置数据库的数量，默认数据库为0，可以使用SELECT <dbid>命令在连接上指定数据库id
   databases 16
    
 4.9. 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合
   save <seconds> <changes>
   Redis默认配置文件中提供了三个条件：
   save 900 1
   save 300 10
   save 60 10000
   分别表示900秒（15分钟）内有1个更改，300秒（5分钟）内有10个更改以及60秒内有10000个更改。
 ​
 4.10. 指定存储至本地数据库时是否压缩数据，默认为yes，Redis采用LZF压缩，如果为了节省CPU时间，可以关闭该选项，但会导致数据库文件变的巨大
   rdbcompression yes
 ​
 4.11. 指定本地数据库文件名，默认值为dump.rdb
   dbfilename dump.rdb
 ​
 4.12. 指定本地数据库存放目录
   dir ./
 ​
 4.13. 设置当本机为slav服务时，设置master服务的IP地址及端口，在Redis启动时，它会自动从master进行数据同步
   slaveof <masterip> <masterport>
 ​
 4.14. 当master服务设置了密码保护时，slav服务连接master的密码
   masterauth <master-password>
 ​
 4.15. 设置Redis连接密码，如果配置了连接密码，客户端在连接Redis时需要通过AUTH <password>命令提供密码，默认关闭
   requirepass foobared
 ​
 4.16. 设置同一时间最大客户端连接数，默认无限制，Redis可以同时打开的客户端连接数为Redis进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis会关闭新的连接并向客户端返回max number of clients reached错误信息
   maxclients 128
 ​
 4.17. 指定Redis最大内存限制，Redis在启动时会把数据加载到内存中，达到最大内存后，Redis会先尝试清除已到期或即将到期的Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis新的vm机制，会把Key存放内存，Value会存放在swap区
   maxmemory <bytes>
 ​
 4.18. 指定是否在每次更新操作后进行日志记录，Redis在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis本身同步数据文件是按上面save条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为no
   appendonly no
 ​
 4.19. 指定更新日志文件名，默认为appendonly.aof
 ​
     appendfilename appendonly.aof
 ​
 4.20. 指定更新日志条件，共有3个可选值： 
   no：表示等操作系统进行数据缓存同步到磁盘（快） 
   always：表示每次更新操作后手动调用fsync()将数据写到磁盘（慢，安全） 
   everysec：表示每秒同步一次（折衷，默认值）
   appendfsync everysec
 ​
 4.21. 指定是否启用虚拟内存机制，默认值为no，简单的介绍一下，VM机制将数据分页存放，由Redis将访问量较少的页即冷数据swap到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析Redis的VM机制）
 ​
     vm-enabled no
 ​
 4.22. 虚拟内存文件路径，默认值为/tmp/redis.swap，不可多个Redis实例共享
 ​
     vm-swap-file /tmp/redis.swap
 ​
 4.23. 将所有大于vm-max-memory的数据存入虚拟内存,无论vm-max-memory设置多小,所有索引数据都是内存存储的(Redis的索引数据 就是keys),也就是说,当vm-max-memory设置为0的时候,其实是所有value都存在于磁盘。默认值为0
 ​
     vm-max-memory 0
 ​
 4.24. Redis swap文件分成了很多的page，一个对象可以保存在多个page上面，但一个page上不能被多个对象共享，vm-page-size是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page大小最好设置为32或者64bytes；如果存储很大大对象，则可以使用更大的page，如果不 确定，就使用默认值
 ​
     vm-page-size 32
 ​
 4.25. 设置swap文件中的page数量，由于页表（一种表示页面空闲或使用的bitmap）是在放在内存中的，，在磁盘上每8个pages将消耗1byte的内存。
 ​
     vm-pages 134217728
 ​
 4.26. 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4
 ​
     vm-max-threads 4
 ​
 4.27. 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启
 ​
   glueoutputbuf yes
 ​
 4.28. 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法
 ​
   hash-max-zipmap-entries 64
 ​
   hash-max-zipmap-value 512
 ​
 4.29. 指定是否激活重置哈希，默认为开启（后面在介绍Redis的哈希算法时具体介绍）
 ​
   activerehashing yes
 ​
 4.30. 指定包含其它的配置文件，可以在同一主机上多个Redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件
 ​
   include /path/to/local.conf

**2.4** **启动多个****redis****进程**

**2.4.1** **启动时指定端口**

启动时指定端口可在一台服务器启动多个redis进程

cd /opt/work/redis/bin
 ./redis-server ../conf/redis.conf --port 6380

**2.4.2** **创建多个****redis****目录**

创建多个redis目录，以端口号命名,推荐使用此种方式

比如：创建6381、6382两个目录，将redis的安装文件bin和conf拷贝至这两个目录。
 修改6381目录下的redis.conf设置端口号为6381
 修改6382目录下的redis.conf设置端口号为6382
 启动6381和6382目录下的redis-server程序：
 cd 6381
 ./redis-server . /redis.conf
 cd 6382
 ./redis-server . /redis.conf

**2.5 redis****客户端**

在redis的安装目录中有redis的客户端，即redis-cli（Redis Command Line Interface），它是Redis自带的基于命令行的Redis客户端

ps aux|grep redis   查询redis是否启动
 ./redis-server ../conf/redis.conf 启动redis
 ./redis-cli -h 127.0.0.1 -p 6379   启动redis客户端
 ping     Redis提供了PING命令来测试客户端与Redis的连接是否正常，如果连接正常会收到回复PONG

**2.6** **远程连接**

默认不允许远程连接，需要修改一下信息才可以进行修改

将redis.conf中的bind 127.0.0.1进行注释
 vim /opt/work/redis/conf/redis.conf   编辑配置文件
 /opt/work/redis/bin/redis-server ../conf/redis.conf   启动redis
 /opt/work/redis/bin/redis-cli 打开客户端，连接成功，再进行下一步
 config set requirepass lx 设置密码
 quit 退出客户端
 /opt/work/redis/bin/redis-cli 打开客户端
 auth lx 输入密码

使用远程进行连接 

# 简要版redis安装及集群搭建

##### 主题词：Redis入门

·        什么是Redis？

**Redis****是一个开源的****NoSQL****数据库，个人觉得，****NoSQL****数据库不能替代关系型数据，而是关系型数据库的一个有效补充在使用。**

Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。从2010年3月15日起，Redis的开发工作由VMware主持。从2013年5月开始，Redis的开发由Pivotal赞助。

NoSQL，泛指非关系型的数据库。随着互联网web2.0网站的兴起，传统的关系数据库在应付web2.0网站，特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。NoSQL数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用难题。

NoSQL数据库的四大分类![https://i.loli.net/2017/09/11/59b6909fe4cfb.jpg](file:///C:/Users/running/AppData/Local/Temp/msohtmlclip1/01/clip_image001.jpg)

Redis的前世今生：2008年，意大利一家创业公司Merzia的创始人Salvatore Sanfilippo为了避免MySQL的低性能，亲自定做一个数据库，并于2009年开发完成，这个就是Redis。短短几年，用户数据量猛增。国内如**新浪微博、街旁和知乎**等，国外如**GitHub****、****Stack Overflow****、暴雪**等，都是Redis的用户。

Redis常见应用场景

\1.     **缓存（首页商品内容、购物车信息）（最多使用）**

\2.     任务队列（秒杀、抢购、12306等）

\3.     聊天室在线好友列表

\4.     应用排行榜

\5.     数据过期处理

## ·        需求：CentOS7操作系统下Redis的安装与配置

\1.     下载

```
wget http://download.redis.io/releases/redis-3.0.7.tar.gz
```

\2.     解压

```
tar -zxvf redis-3.0.7.tar.gz
```

\3.     编译与安装

```
cd redis-3.0.7

如果这里报指令错误,请安装gcc环境:yum -y install gcc automake autoconf libtool make

make

make install PREFIX=/usr/local/redis-3.0.7
```

\4.     启动服务器

```
cd /usr/local/redis-3.0.7

bin/redis-server 如果是已经进入bin目录,要输入./redis-server

# 直接启动为前台启动，控制台会被占用

# 可以使用Ctrl+C强制关闭服务
```

\5.     配置后台启动

·        将源码中redis.conf拷贝到/usr/local/redis目录

·        vi命令中“/daemonize” 查找 daemonize，小写n查找下一个，大写N查找上一个

·        并将"daemonize no"行改为"daemonize yes"

·        允许远程连接，注释#bind 127.0.0.1(protected-mode no)

·        修改连接密码，requirepass 

```


cp /root/redis-3.0.7/redis.conf ./

vim redis.conf


```

·        后台启动

```
bin/redis-server redis.conf
```

·        查看状态

```
[root@localhost redis-3.0.7]ps -ef | grep redis

root 10452 0.0 0.1 137444 7460 ? Ssl 11:27 0:00 bin/redis-server *:6379 

root 10456 0.0 0.0 103244 836 pts/0 S+ 11:28 0:00 grep redis
```

\6.     测试环境

```
bin/redis-cli

redis> set foo bar

OK

redis> get foo

"bar"
```

\7.     关闭服务器

```
# 推荐使用

bin/redis-cli shutdown
# 不推荐使用

kill -9 10452
```

·        **重点需求：学习****Redis****的各种数据类型**

o   参见：<https://www.w3cschool.cn/redis/>

o   参见：<http://www.runoob.com/redis/redis-tutorial.html>

§  需求：完成redis集群的搭建

\2.     了解集群和主从的区别

\3.     redis集群基本概念

redis集群的详细贴子：<http://blog.csdn.net/sanwenyublog/article/details/52942236>

redis集群中至少应该有三个节点。要保证集群的高可用，每个节点需要有一个备份机。因此redis集群至少需要六台服务器

这里搭建的是伪分布模式，可以使用一台服务器运行6个redis实例，修改redis的端口号为7001-7006

相关算法：<http://blog.csdn.net/u014490157/article/details/52244378>

## redis集群的搭建

§  安装ruby环境

```
yum install ruby

yum install rubygems
```

§  安装ruby脚本运行使用的包

```
# 离线安装

gem install redis-3.0.7.gem
# 在线安装

gem install redis -v 3.0.7
如下为离线安装实例:
[root@localhost ~]# gem install redis-3.0.0.gem 
Successfully installed redis-3.0.0
1 gem installed
Installing ri documentation for redis-3.0.0...
Installing RDoc documentation for redis-3.0.0...
[root@localhost ~]# 
[root@localhost ~]# cd redis-3.0.7/src
[root@localhost src]# ll *.rb(注意这个文件稍后要拷贝到rediscluster那个文件夹中)
-rwxrwxr-x. 1 root root 48141 Apr  1  2015 redis-trib.rb
```

§  创建6台服务器，将6台的端口号修改9001——9006

```
1.在/usr/soft(我自己的软件安装目录)创建一个rediscluster文件夹(用来创建集群),将上面已经安装好的redis文件夹(注意是安装目录)复制六份到这个文件夹

上图是我的目录结构,先把redis-3.0.7复制一份到rediscluster里面并改名为redis1
指令:cp /usr/soft/redis-3.0.7  /usr/soft/rediscluster
然后改文件夹名字为redis1,可以用notepad++等工具,也可以用指令:mv redis-3.0.7 redis1
然后记住先将redis1里面的数据文件dump.rdb删除,然后打开redis.conf之后修改三处: cluster-enabled yes前的注释去掉,将port分别改为9001,再将密码先注释掉requirepass(如# requirepass xxxxxx),之后保存(用指令的话先vi redis.conf进入文件,然后键盘i修改,修改后按esc键,再按shift+:,之后wq!保存退出)
然后再把redis1复制五份,分别命名为redis2-redis6,之后进去把各自port修改为9002-9006
上图中-R表示递归复制(复制文件夹下的所有文件及文件夹)

 

4. 如果是阿里云ECS服务器的话，请将端口号+10000添加到安全组

如：7001需要加入安全组，17001也需要加入安全组
```

§  自定义shell脚本启动6台服务器

```
先用进入rediscluster文件夹,然后用指令touch startup.sh创建文件,之后通过vi startup.sh进入文件,然后编辑写入如下内容(注意文件的相对路径,因为我的路径结构是如下的,即rediscluster下有redis1-6,startup.sh然后有redis1-6文件夹下有bin,bin内部有redis.conf和redis-server,所以我的脚本文件应该按照如下写:

cd /usr/soft/rediscluster/redis1

bin/redis-server  bin/redis.conf

cd ../redis2

bin/redis-server bin/redis.conf

cd ../redis3

bin/redis-server bin/redis.conf

cd ../redis4

bin/redis-server bin/redis.conf

cd ../redis5

bin/redis-server bin/redis.conf

cd ../redis6

bin/redis-server bin/redis.conf
自定义的启动脚本完成后保存并退出,来到rediscluster文件夹下,然后输入./startup.sh 运行脚本即可全部启动(关闭的脚本类似)
```

§  自定义shell脚本关闭6台服务器交给大家来做

(在rediscluster下touch创建shutdown.sh,然后写入如下内容)

cd /usr/soft/rediscluster/redis1

bin/redis-cli -p 9001 shutdown

bin/redis-cli -p 9002 shutdown

bin/redis-cli -p 9003 shutdown

bin/redis-cli -p 9004 shutdown

bin/redis-cli -p 9005 shutdown

bin/redis-cli -p 9006 shutdown

然后保存退出,记得用cat -A shutdown.sh来看下写入的指令是不是尾部以$(换行结束),如果是就对了,否则会报错(注意用windows记事本写的脚本会在尾部加上其他我们看不到的符号,要注意检查下)

§  运行如下代码搭建集群环境(注意这个时候要把redis-trib.rb这个离线安装文件-------一般在redis的解压目录的文件夹下的src中拷贝到rediscluster中),之后运行如下指令

§  ![img](file:///C:/Users/running/AppData/Local/Temp/msohtmlclip1/01/clip_image009.jpg)

```
./redis-trib.rb create --replicas 1 10.31.166.22:9001 10.31.166.22:9002 10.31.166.22:9003 10.31.166.22:9004 10.31.166.22:9005 10.31.166.22:9006
```

§  集群创建成功的两张截图

![https://i.loli.net/2017/09/14/59ba40edb0422.jpg](file:///C:/Users/running/AppData/Local/Temp/msohtmlclip1/01/clip_image011.jpg)![https://i.loli.net/2017/09/14/59ba40edc85f6.jpg](file:///C:/Users/running/AppData/Local/Temp/msohtmlclip1/01/clip_image013.jpg)

§  客户端如何连接集群中的机器

```
# -p 端口号

# -c 开启reidis cluster模式,连接redis cluster节点时候使用

bin/redis-cli -p 7004 -c
```

§  往集群节点存入数据进行测试，查看数据到底存入到哪个节点

redis集群中内置了16384个哈希槽，当需要往集群中存放键值对的时候，redis先对key使用CRC16算法算出一个结果，然后拿这个结果对16384求余，这样每个key都会对应一个编号为0-16383之间的哈希槽，redis会根据节点数量大致均等的将哈希槽映射到不同的节点上

 

 