package com.demo.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表(Role)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -17940512502353114L;
    /**
    * id
    */
    private Long id;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 角色昵称
    */
    private String roleNickname;
    /**
    * 角色状态（0正常 1停用）
    */
    private String status;
    /**
     * 菜单ID数组
     */
    private Long[] menuIds;
    /**
    * 删除标识（0未删除 1已删除）
    */
    private String delFlag;
    /**
    * 备注
    */
    private String remark;
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