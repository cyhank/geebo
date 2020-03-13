package com.geebo.web;


import com.geebo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hank
 * @Desription
 * @Date: Created in 12:06 2020/3/13
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/set")
    public String setKey(String key, String value) {
        redisUtil.set(key, value);
        return "setKey:key:" + key + " value" + value;
    }

    @GetMapping("/get")
    public String getValue(String key) {
        String value = redisUtil.get(key) + "";
        return "getValue:key:" + key + " value" + value;
    }

}
