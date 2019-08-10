package com.jtfu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtfu.entity.Role;
import com.jtfu.mapper.RoleMapper;
import com.jtfu.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jtfu
 * @since 2019-08-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
