package com.jtfu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtfu.entity.User;
import com.jtfu.mapper.UserMapper;
import com.jtfu.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jtfu
 * @since 2019-08-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
