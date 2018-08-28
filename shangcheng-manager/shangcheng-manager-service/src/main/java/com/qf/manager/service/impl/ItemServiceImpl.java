package com.qf.manager.service.impl;

import com.qf.manager.dao.TbItemMapper;
import com.qf.manager.dao.TbProductsMapper;
import com.qf.manager.pojo.dto.ItemResult;
import com.qf.manager.pojo.dto.PageParam;
import com.qf.manager.pojo.dto.ZTreeNode;
import com.qf.manager.pojo.po.TbProducts;
import com.qf.manager.pojo.po.TbProductsExample;
import com.qf.manager.pojo.vo.Category;
import com.qf.manager.pojo.vo.ItemCat;
import com.qf.manager.pojo.vo.ItemSeach;
import com.qf.manager.service.ItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemMapper itemDao;
    @Autowired
    private TbProductsMapper tbProductsMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public ItemResult<ItemCat> listItems(PageParam pageParam, ItemSeach itemSeach) {

        ItemResult<ItemCat> result = new ItemResult<>();
        //封装Tbitem
        result.setCode(0);
        result.setMsg("select success");

        try {
            long count = itemDao.countItems(itemSeach);
            result.setCount(count);

            List<ItemCat> data = itemDao.listItems(pageParam,itemSeach);
            result.setData(data);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg("select failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int removeItemMary(List<String> ids) {

        TbProducts record = new TbProducts();
        record.setStatus(3);
        TbProductsExample example = new TbProductsExample();
        TbProductsExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(ids);
        return tbProductsMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateProduct(String pid) {

        TbProducts a = new TbProducts();
        a.setStatus(3);
        TbProductsExample example = new TbProductsExample();
        TbProductsExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        return tbProductsMapper.updateByExampleSelective(a,example);
    }

    @Override
    public List<Category> listCategory() {
        List<Category> result = itemDao.listCategory();
        return result;
    }

    @Override
    public List<ItemCat> listProduct() {

        List<ItemCat> data = itemDao.listProduct();

        return data;
    }

    @Override
    public int removeProduct(String ids) {
        String[] s = ids.split(",");
        List<String> list = new ArrayList<>();
        for (String str:s) {
            list.add(str);
        }
        TbProducts record = new TbProducts();
        record.setStatus(3);
        TbProductsExample example = new TbProductsExample();
        TbProductsExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(list);
        return tbProductsMapper.updateByExampleSelective(record,example);
    }

    @Override
    public  List<ItemCat> listSearch(String searchKey) {

        List<ItemCat> data = itemDao.listSearch(searchKey);

        return data;
    }

    @Override
    public int itemStop(String pid) {
        TbProducts a = new TbProducts();
        a.setStatus(2);
        TbProductsExample example = new TbProductsExample();
        TbProductsExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        return tbProductsMapper.updateByExampleSelective(a,example);
    }

    @Override
    public int itemStart(String pid) {
        TbProducts a = new TbProducts();
        a.setStatus(1);
        TbProductsExample example = new TbProductsExample();
        TbProductsExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        return tbProductsMapper.updateByExampleSelective(a,example);
    }

    @Override
    public ItemCat listProductByPid(String pid) {

        ItemCat data = itemDao.listProductByPid(pid);
        return data;
    }

    @Override
    public int editProduct(TbProducts p) {

        return tbProductsMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public void importIndexLib() {
        try {
            //1 采集数据
            TbProductsExample example = new TbProductsExample();
            TbProductsExample.Criteria criteria = example.createCriteria();
            List<TbProducts> list = tbProductsMapper.selectByExample(example);
            //2 导入索引库（遍历集合 documentList）
            for (TbProducts p : list) {
                //a Document
                SolrInputDocument document = new SolrInputDocument();

                //b 把list中每个对象的属性设置到document的field
                document.addField("id", p.getPid());
                document.addField("sell_point",p.getSellPoint());
                document.addField("pdesc",p.getPdesc());
                document.addField("pname",p.getPname());
                document.addField("price",p.getPrice());
                document.addField("pimage",p.getPimage());


                //c addDocument
                solrServer.add(document);
            }
            //d 提交
            solrServer.commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

   /* @Override
    public List<ZTreeNode> getItemCatList(int parentId) {

        List<>
        return null;
    }*/
}
