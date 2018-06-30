package com.qf.shop.manager.pojo.dto;

/**用于封装前台发来的当前页 和 pageSizexx
 * 快捷键alt+insert
 * Created by 孙立业 on 2018/4/17.
 */
public class Page {
    //当前页
    private long page;
    //页面的数据条数
    private long limit;

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }
    //获取偏移量
    public int getOffset(){
        return (int)((page-1)*limit);
    }



}
