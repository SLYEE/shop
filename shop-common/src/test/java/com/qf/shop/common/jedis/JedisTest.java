/*
package com.qf.shop.common.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class JedisTest {
    //连接单机版
    @Test
    public void testJedis1(){
        Jedis jedis=new Jedis("47.98.214.192",6379);
        jedis.auth("foobared");
        String name = jedis.get("name");//123
        System.out.println(name);
        jedis.close();
    }

    //连接单机版使用连接池
    @Test
    public void testJedis2(){
        JedisPool jedisPool= new JedisPool("47.98.214.192",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.auth("foobared");
        String name = jedis.get("key1");//master
        System.out.println(name);
        jedis.close();
        jedisPool.close();
    }
    //连接集群版
    @Test
    public void testJedis3() throws IOException {
        //设置一个set集合 redis节点的列表
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("47.98.214.192",9001));
        nodes.add(new HostAndPort("47.98.214.192",9002));
        nodes.add(new HostAndPort("47.98.214.192",9003));
        nodes.add(new HostAndPort("47.98.214.192",9004));
        nodes.add(new HostAndPort("47.98.214.192",9005));
        nodes.add(new HostAndPort("47.98.214.192",9006));
        //jedis集群
        JedisCluster jedisCluster=new JedisCluster(nodes);


        String hello = jedisCluster.get("hello");
        System.out.println(hello);
        //关闭资源
        jedisCluster.close();


    }
}
*/
