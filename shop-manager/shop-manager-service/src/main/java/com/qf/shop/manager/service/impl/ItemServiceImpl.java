package com.qf.shop.manager.service.impl;

import com.qf.shop.manager.dao.TbItemCustomMapper;
import com.qf.shop.manager.dao.TbItemMapper;
import com.qf.shop.manager.pojo.dto.Page;
import com.qf.shop.manager.pojo.po.TbItem;
import com.qf.shop.manager.pojo.po.TbItemCustom;
import com.qf.shop.manager.pojo.po.TbItemExample;
import com.qf.shop.manager.pojo.vo.TbItemQuery;
import com.qf.shop.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**快捷键 Ctrl+ o生成方法
 * ctrl+alt+t 生成trycatch
 * Created by 孙立业 on 2018/4/17.
 */
@Service
public class ItemServiceImpl implements ItemService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbItemCustomMapper tbItemCustomDao;
    @Autowired
    private TbItemMapper tbItemDao;
    @Override
    public long countItems(TbItemQuery query) {
        long count=0L;
        try {
            count =tbItemCustomDao.countItems(query);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<TbItemCustom> listItemByPage(Page page,TbItemQuery query) {
        List<TbItemCustom> listItemByPage=null;
        try {
            listItemByPage = tbItemCustomDao.listItemByPage(page,query);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return listItemByPage;
    }

    @Override
    public int updateStatusByIds(List<Long> ids) {
        int  i=0;
        try {
            //创建一个tbitem对象
           TbItem recode= new TbItem();
           recode.setStatus((byte)3); 
            //创建一个模板
            TbItemExample tbItemExample = new TbItemExample();
            //创建Criteria用于组装sql语句
            TbItemExample.Criteria criteria = tbItemExample.createCriteria();
            criteria.andIdIn(ids);
            //执行sql语句 此方法只会对不为null的数据进行更新
            i = tbItemDao.updateByExampleSelective(recode, tbItemExample);


        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return i;
    }
}
