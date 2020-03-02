package gzhu.edu.cn.system.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.system.entity.Role;

public interface IRoleService extends BaseService<Role, Integer>{
	
	/**
	 * 根据角色id和权限id更新角色对应的权限
	 * @param roleId
	 * @param resourceIds
	 * @return
	 */
	public boolean updateAuth(Integer roleId,int[] resourceIds);

}
