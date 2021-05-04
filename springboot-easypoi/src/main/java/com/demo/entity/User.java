package com.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * <p> @Title User
 * <p> @Description 用户实体
 *
 * @author zhj
 * @date 2021/4/25 17:32
 */
public class User {

    /** 用户名. */
    @Excel(name="用户名", orderNum = "0", width = 30)
    private String username;

    /** 密码. */
    @Excel(name="密码", orderNum = "1", width = 30)
    private String password;

}

