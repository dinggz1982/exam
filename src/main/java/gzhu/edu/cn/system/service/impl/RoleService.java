package gzhu.edu.cn.system.service.impl;

import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.service.IRoleService;

@Service("roleService")
public class RoleService extends BaseDAOImpl<Role, Long> implements IRoleService{

}
