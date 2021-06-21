package com.demo.auth.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;

/**
 * 角色表(TRole)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Data
public class TRole implements Serializable {
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
    private String roleKey;
    /**
    * 角色状态（0正常 1停用）
    */
    private String status;
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
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;

}