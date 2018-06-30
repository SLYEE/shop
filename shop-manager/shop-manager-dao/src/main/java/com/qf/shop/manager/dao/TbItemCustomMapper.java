package com.qf.shop.manager.dao;

import com.qf.shop.manager.pojo.dto.Page;
import com.qf.shop.manager.pojo.po.TbItemCustom;
import com.qf.shop.manager.pojo.vo.TbItemIndex;
import com.qf.shop.manager.pojo.vo.TbItemQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 孙立业 on 2018/4/17.
 */
public interface TbItemCustomMapper {
    long countItems(@Param("query")TbItemQuery query);
    List<TbItemCustom> listItemByPage(@Param("page")Page page,@Param("query")TbItemQuery query);

    List<TbItemIndex> listItemsByIndex();
}
