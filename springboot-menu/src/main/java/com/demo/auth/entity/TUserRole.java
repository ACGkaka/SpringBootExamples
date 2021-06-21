package com.demo.auth.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户和角色关联表(TUserRole)实体类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Data
public class TUserRole implements Serializable {
    private static final long serialVersionUID = -99020226147672924L;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 部门ID
    */
    private Long roleId;

}