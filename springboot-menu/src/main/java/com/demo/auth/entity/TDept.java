package com.demo.auth.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 部门表(TDept)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Data
public class TDept implements Serializable {
    private static final long serialVersionUID = -55607576678116765L;
    /**
    * id
    */
    private Long id;
    /**
    * 父部门id
    */
    private Long parentId;
    /**
    * 部门名称
    */
    private String deptName;
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