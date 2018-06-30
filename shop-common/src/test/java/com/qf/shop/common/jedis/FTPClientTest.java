/*
package com.qf.shop.common.jedis;

import com.qf.shop.common.util.FtpUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.*;

*
 * Created by 孙立业 on 2018/5/3.


public class FTPClientTest {
    @Test
    public  void testFtpClient1() throws IOException {
        //获取FTpClient
        FTPClient client = new FTPClient();
        //连接
        client.connect("47.98.214.192",21);
        //使用宿主账号进行登录
        client.login("ftpuser","123456");
        //创建一个输入流
        FileInputStream inputStream=new FileInputStream(new File("d:\\1.jpg"));

        //通过client修改工作目录并修改文件类型二进制
        client.changeWorkingDirectory("/home/ftpuser/www/images");
        client.setFileType(FTP.BINARY_FILE_TYPE);
        //上传图片
        client.storeFile("hello2.jpg",inputStream);
        //释放资源
        inputStream.close();
        //登出
        client.logout();
    }
    @Test
    public  void testFtpClient2() throws FileNotFoundException {
        FileInputStream inputStream=new FileInputStream(new File("d:\\a.jpg"));
        FtpUtils.uploadFile("47.98.214.192",21,"ftpuser","123456","/home/ftpuser/www/images","/2018","ss.jpg", inputStream);
    }
}
*/
