package com.demo.auth.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 角色和部门关联表(TRoleDept)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Data
public class TRoleDept implements Serializable {
    private static final long serialVersionUID = 287207961932600703L;
    /**
    * 角色ID
    */
    private Long roleId;
    /**
    * 部门ID
    */
    private Long deptId;

}