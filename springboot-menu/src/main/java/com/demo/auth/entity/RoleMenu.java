package com.demo.auth.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 角色和菜单关联表(RoleMenu)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Data
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -23642316652535899L;
    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 菜单ID
    */
    private Long menuId;

}