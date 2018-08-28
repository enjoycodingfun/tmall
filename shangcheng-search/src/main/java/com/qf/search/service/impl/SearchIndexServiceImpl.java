package com.qf.search.service.impl;

import com.qf.search.dao.SearchIndexDao;
import com.qf.search.pojo.dto.TbProductsIndexResult;
import com.qf.search.service.SearchIndexService;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SearchIndexServiceImpl implements SearchIndexService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchIndexDao searchIndexDao;

    @Override
    public TbProductsIndexResult searchIndex(String keyword, int pageIndex, int pageSize) {
        TbProductsIndexResult result = null;
        try {
            //创建solr查询对象
            SolrQuery query = new SolrQuery();
            //设置查询的关键字
            query.setQuery(keyword);
            if (pageIndex <= 0) {
                pageIndex = 1;
            }
            //设置查询的分页条件
            query.setStart((pageIndex - 1) * pageSize);
            query.setRows(pageSize);
            //设置默认查询字段
            query.set("df", "item_keywords");
            //设置高亮部分
            query.setHighlight(true);
            query.addHighlightField("pname");
            query.setHighlightSimplePre("<em style='color:red;'>");
            query.setHighlightSimplePost("</em>");
            //调用DAO层方法进行查询，查询条件：query
            result = searchIndexDao.search(query,pageSize);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
