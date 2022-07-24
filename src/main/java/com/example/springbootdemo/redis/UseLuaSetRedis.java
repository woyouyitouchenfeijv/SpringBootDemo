package com.example.springbootdemo.redis;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2022/7/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class UseLuaSetRedis {

    @Test
    public void t() {
        long l = TimeUnit.SECONDS.toMillis(15L);
        System.out.println(l);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean lock(String key, String value, String seconds){
        try{
            String path = "static/redis_lock.lua";
            ClassPathResource resource = new ClassPathResource(path);
            ScriptSource scriptSource = new ResourceScriptSource(resource);
            DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
            defaultRedisScript.setScriptSource(scriptSource);
            System.out.println(scriptSource.getScriptAsString());

            //设置返回类型
            defaultRedisScript.setResultType(Long.class);

            //组装数据
            List<Object> keyList = new ArrayList<>();
            keyList.add(key);
            keyList.add(value);
            keyList.add(seconds);
            // 第三个参数是无用的,但不能为空，考虑到内存空间，遂作故用
            Long result = (Long)redisTemplate.execute(defaultRedisScript, keyList);
            return result == 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("redis分布式锁存在异常,请注意key:"+ key+"value"+value+ "seconds:"+seconds);
            return false;
        }
    }


    @Test
    public void testLock() {
        lock("testKey1","testValue2","300");
    }
}
