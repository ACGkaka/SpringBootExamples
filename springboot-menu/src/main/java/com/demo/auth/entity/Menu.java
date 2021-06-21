package com.demo.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单表(Menu)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = -38111335699825497L;
    /**
    * id
    */
    private Long id;
    /**
    * 父菜单id
    */
    private Long parentId;
    /**
    * 菜单名称
    */
    private String menuName;
    /**
    * 菜单链接
    */
    private String menuUrl;
    /**
    * 菜单类型（C菜单 B按钮）
    */
    private String menuType;
    /**
    * 显示顺序
    */
    private Integer orderNum;
    /**
    * 是否显示（0显示 1隐藏）
    */
    private String visible;
    /**
    * 菜单图标
    */
    private String icon;
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