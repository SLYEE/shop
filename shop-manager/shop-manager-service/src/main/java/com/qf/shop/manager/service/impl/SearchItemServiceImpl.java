package com.qf.shop.manager.service.impl;

import com.qf.shop.common.dto.MessageObject;
import com.qf.shop.manager.dao.TbItemCustomMapper;
import com.qf.shop.manager.dao.TbItemMapper;
import com.qf.shop.manager.pojo.po.TbItemCustom;
import com.qf.shop.manager.pojo.vo.TbItemIndex;
import com.qf.shop.manager.service.SearchItemService;
import com.sun.net.httpserver.Authenticator;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by 孙立业 on 2018/5/18.
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    //使用logback的日志
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCustomMapper itemCustomdao;
    @Autowired
    //solr客户端
    private SolrServer solrServer;

    @Override
    public MessageObject importIndexLibrary() {
        MessageObject mo=new MessageObject();
        mo.setSuccess(false);
        try {
            //1.采集数据
            List<TbItemIndex> list=itemCustomdao.listItemsByIndex();
            //2.添加到索引库
            //a.遍历集合
            for(TbItemIndex item:list){
                //b.创建一个新的文档对象
                SolrInputDocument document=new SolrInputDocument();
                //c.给文档对象的属性设置值
                document.addField("id",item.getId());
                document.addField("item_title",item.getTitle());
                document.addField("item_sell_point",item.getSellPoint());
                document.addField("item_image",item.getImage());
                document.addField("item_price",item.getPrice());
                document.addField("item_category_name",item.getCatName());
                //d.写入文档域
                solrServer.add(document);
            }
            //e.提交
            solrServer.commit();
            mo.setSuccess(true);
            mo.setMsg("succcess");
        } catch (SolrServerException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mo;
    }
}
