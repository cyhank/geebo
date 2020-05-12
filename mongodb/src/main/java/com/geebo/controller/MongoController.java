package com.geebo.controller;

import com.geebo.entity.User;
import com.mongodb.MongoClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 16:18 2020/4/3
 */
@RestController
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public void contextLoads() {

        MongoClient mongoClient = new MongoClient("localhost", 27017);


        /*User user = new User();
        user.setId(30);
        user.setName("钟镇涛");
        user.setSex("男");
        mongoTemplate.insert(user);
        user.setId(33);
        user.setName("周星驰");
        user.setSex("男");
        mongoTemplate.insert(user);*/
    }
}
