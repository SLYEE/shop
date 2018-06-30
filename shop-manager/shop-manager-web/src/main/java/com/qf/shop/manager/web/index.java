package com.qf.shop.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 孙立业 on 2018/4/13.
 */
@Controller
public class index {
    @RequestMapping(value="/{page}",method = RequestMethod.GET)
    public  String index(@PathVariable String page){
        return page;
    }
    @RequestMapping(value="/pages/{pageNum}")
    public String forward1(@PathVariable String pageNum){
        return "pages/"+pageNum;
    }
    @RequestMapping(value="/pages/{pageNum1}/{pageNum2}")
    public String forward2(@PathVariable String pageNum1,@PathVariable String pageNum2){
        return "pages/"+pageNum1+"/"+pageNum2;
    }
}
