package com.demo.auth.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户表(TUser)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 16:49:55
 */
@Data
public class TUser implements Serializable {
    private static final long serialVersionUID = 370077225736547493L;
    /**
    * id
    */
    private Long id;
    /**
    * 部门id
    */
    private Long deptId;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 电话
    */
    private String phone;
    /**
    * 删除标识（0未删除 1已删除）
    */
    private String delFlag;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;

}