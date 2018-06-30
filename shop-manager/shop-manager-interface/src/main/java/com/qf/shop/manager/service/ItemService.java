package com.qf.shop.manager.service;

import com.qf.shop.manager.pojo.dto.Page;
import com.qf.shop.manager.pojo.po.TbItemCustom;
import com.qf.shop.manager.pojo.vo.TbItemQuery;

import java.util.List;

/**
 * Created by 孙立业 on 2018/4/17.
 */
public interface ItemService {


    long countItems(TbItemQuery query);

    List<TbItemCustom> listItemByPage(Page page,TbItemQuery query);

    int updateStatusByIds(List<Long> ids);
}
