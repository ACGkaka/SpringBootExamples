package com.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p> @Title User
 * <p> @Description 用户实体
 *
 * @author zhj
 * @date 2021/4/25 17:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 用户名
     * orderNum 注解导出顺序
     * width 列宽
     * isImportField 导入时判断是否为必填，必填
     */
    @Excel(name="用户名", orderNum = "0", width = 30, isImportField = "true_st")
    private String username;

    /** 密码 */
    @Excel(name="密码", orderNum = "2", width = 30)
    private String password;

    /**
     * 性别
     * replace 码表替换
     */
    @Excel(name = "性别", orderNum = "1", width = 30, replace = { "男_1", "女_2" }, suffix = "生")
    private int sex;

    /** 创建时间 */
    @Excel(name="创建时间", orderNum = "3", width = 35, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}

