package com.geebo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Author: Hank
 * @Desription 灵活的获取Spring Bean对象
 * @Date: Created in 10:44 2020/3/13
 */
@Service
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.context=applicationContext;
    }
    public static <T> T getBean(String name, Class<T> requiredType){
        return context.getBean(name, requiredType);
    }
}
