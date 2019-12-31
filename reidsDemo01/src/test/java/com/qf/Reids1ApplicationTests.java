package com.qf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class Reids1ApplicationTests {
    //java断通过 jedis操作redis服务器
    public static void main(String []args){
        Jedis jedis = new Jedis("106.13.180.225",6379);
        System.out.println(jedis.ping());
        //连接成功
    }

    @Test
    void contextLoads() {
    //获取连接
        Jedis jedis = new Jedis("106.13.180.225",6379);
        jedis.set("userName","杰克");
        String userName = jedis.get("userName");
        System.out.println("redis的name是:"+userName);
        jedis.close();
    }

    //redis作用：减轻数据库（mysql）的压力
    //需求：判断key是否存在，如果存在 去redis查  如果不存在 去数据库查询并存储redis
    @Test
    public void test2(){
        Jedis jedis = new Jedis("106.13.180.225",6379);
        String key="password";
        if(jedis.exists(key)){
            String result = jedis.get(key);
            System.out.println("redis缓存中查出来："+result);
        }else{
            String result="千锋牛逼";
            jedis.set(key,result);
            System.out.println("mysql数据库中查询："+result);
        }
        jedis.close();
    }
}
