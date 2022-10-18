package com.demo.module.mapper;

import com.demo.module.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ACGkaka
 * @since 2021-04-25
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {

}
