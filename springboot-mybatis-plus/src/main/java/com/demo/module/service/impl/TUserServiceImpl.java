package com.demo.module.service.impl;

import com.demo.module.entity.TUser;
import com.demo.module.mapper.TUserMapper;
import com.demo.module.service.TUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ACGkaka
 * @since 2021-04-25
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {

}
