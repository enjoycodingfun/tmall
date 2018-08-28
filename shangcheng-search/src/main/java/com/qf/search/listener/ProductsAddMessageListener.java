package com.qf.search.listener;

import com.qf.search.dao.TbProductsIndexMapper;
import com.qf.search.pojo.vo.TbProductsIndex;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

public class ProductsAddMessageListener implements MessageListener {
    @Autowired
    private TbProductsIndexMapper productsMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public void onMessage(Message message) {

        try {
            //接收消息
            TextMessage textMessage = (TextMessage)message;
            String pid = textMessage.getText();
            //查询出商品
            TbProductsIndex tbProductsIndex = productsMapper.getProductsByPid(pid);
            //导入到索引库
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField("id",tbProductsIndex.getId());
            solrInputDocument.addField("pname",tbProductsIndex.getPname());
            solrInputDocument.addField("pdesc",tbProductsIndex.getPdesc());
            solrInputDocument.addField("sell_point",tbProductsIndex.getSellPoint());

            solrServer.add(solrInputDocument);
            solrServer.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
