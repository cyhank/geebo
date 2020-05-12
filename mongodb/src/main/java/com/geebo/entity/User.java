package com.geebo.entity;

import lombok.Data;

/**
 * @Author: Hank
 * @Desription
 * @Date: Created in 16:18 2020/4/3
 */
@Data
public class User {
    private int id;
    private String name;
    private String sex;

    /**
     * 用于打印对象内容
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
