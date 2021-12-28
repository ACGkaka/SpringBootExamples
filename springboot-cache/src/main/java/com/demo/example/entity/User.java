package com.demo.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> @Title User
 * <p> @Description 用户实体
 *
 * @author zhj
 * @date 2021/12/28 15:41
 */
@Data
@AllArgsConstructor
public class User {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 城市
     */
    private String city;
}
