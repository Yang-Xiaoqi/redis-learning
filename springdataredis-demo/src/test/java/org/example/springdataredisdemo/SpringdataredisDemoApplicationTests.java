package org.example.springdataredisdemo;

import org.example.springdataredisdemo.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringdataredisDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void testString(){
        //写入一条string数据
        redisTemplate.opsForValue().set("name","琦哥");

        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }
    @Test
    void testSaveUser(){
        redisTemplate.opsForValue().set("user:100",new User("虎哥",21));
        User o = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println(o);
    }

}
