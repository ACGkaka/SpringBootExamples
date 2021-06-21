package com.demo.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门表(Dept)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Data
public class Dept implements Serializable {
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
    * 更新时间
    */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

}