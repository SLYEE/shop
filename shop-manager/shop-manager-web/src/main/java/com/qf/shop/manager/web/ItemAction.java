package com.qf.shop.manager.web;

import com.qf.shop.common.dto.MessageObject;
import com.qf.shop.common.fdfs.FastDFSFile;
import com.qf.shop.common.fdfs.FastDFSUtils;
import com.qf.shop.common.util.PropKit;
import com.qf.shop.common.util.StrKit;
import com.qf.shop.manager.pojo.dto.MessageResult;
import com.qf.shop.manager.pojo.dto.Page;
import com.qf.shop.manager.pojo.po.TbItemCustom;
import com.qf.shop.manager.pojo.vo.TbItemQuery;
import com.qf.shop.manager.service.ItemService;
import com.qf.shop.manager.service.SearchItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 孙立业 on 2018/4/17.
 */
@Controller
public class ItemAction {
    //logback的日志
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ItemService itemService;
    @Autowired
    private SearchItemService searchItemService;
    @RequestMapping(value = "/items",method = RequestMethod.GET)
    @ResponseBody
  //  MessageResult这个类封装了layui页面返回所需的代码,信息,总条数,页面的数据


    public MessageResult<TbItemCustom> listItemByPage(Page page,TbItemQuery query){
        MessageResult<TbItemCustom> messageResult=new MessageResult<>();
        try {
            //获取数据的总条数
            long count=itemService.countItems(query);
            List<TbItemCustom> data =itemService.listItemByPage(page,query);
            //封装messageResult
            messageResult.setCode(0);
            messageResult.setCount(count);
            messageResult.setMsg("success");
            messageResult.setData(data);
        }catch (Exception e){
            //日志记录异常
            logger.error(e.getMessage(),e);
            //处理异常
            e.printStackTrace();
        }
        return  messageResult;
    }
    @RequestMapping(value = "/item/batch")
    @ResponseBody
    public int updateStatusByIds(@RequestParam("ids[]") List<Long> ids){
        int i=0;
        try {
            i=itemService.updateStatusByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    //http://47.98.214.192/group1/M00/00/00/rBA5mVryyaGAEvumAAl_4OI4tPM408.jpg
    public Map<String,Object> uploadImage(@RequestParam MultipartFile file){
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            FastDFSFile fastDFSFile = new FastDFSFile(file.getBytes(), file.getOriginalFilename(), file.getSize());
            String path = FastDFSUtils.uploadFile(fastDFSFile);
            //读取配置文件中的
            String basePath = PropKit.use("fdfs_client.conf").get("fdfs_server");
            if(StrKit.notBlank(path)){
                result.put("code", 0);
                result.put("msg", "上传成功");
                Map<String,Object> dataMap = new HashMap<String,Object>();
                dataMap.put("src",basePath+"/"+ path);
                result.put("data", dataMap);
            }else{
                result.put("code", 1);
                result.put("msg", "上传失败");
                Map<String,Object> dataMap = new HashMap<String,Object>();
                dataMap.put("src", "");
                result.put("data", dataMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    //导入索引库
    @ResponseBody
    @RequestMapping(value="/item/indexlib/import",method = RequestMethod.POST)
    public MessageObject importIndex(){
        MessageObject mo=null;
        try {
            mo=searchItemService.importIndexLibrary();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return mo;
    }

}
