package com.demo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> @Title User
 * <p> @Description 用户实体
 *
 * @author ACGkaka
 * @date 2021/5/6 14:02
 */
@Data
@AllArgsConstructor
public class User {

    /**
     * 主键
     */
    public Integer id;

    /**
     * 用户名
     */
    public String username;

    /**
     * 密码
     */
    public String password;

    /**
     * 邮箱
     */
    public String email;

}
