package com.geebo.controller;

import com.geebo.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoControllerTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setId(35);
        user.setName("元华");
        user.setSex("男");
        mongoTemplate.insert(user);
        user.setId(36);
        user.setName("吴孟达");
        user.setSex("男");
        mongoTemplate.insert(user);
    }






    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
