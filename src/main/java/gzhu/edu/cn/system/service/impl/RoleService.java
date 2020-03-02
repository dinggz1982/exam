package gzhu.edu.cn.system.service.impl;

import java.util.Iterator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.service.IRoleService;

/**
 * 角色服务层代码
 * 
 * @author dingguozhu
 *
 */
@Service("roleService")
public class RoleService extends BaseDAOImpl<Role, Integer> implements IRoleService {

	@Override
	@Transactional
	public boolean updateAuth(Integer roleId, int[] resourceIds) {
		boolean isSuccess = false;
		try {
			// 1.先删除以前的权限信息
			this.executeSql("delete from sys_role_resources where role_id=" + roleId);
			// 2.新增新的权限
			if (resourceIds != null && resourceIds.length > 0) {
				// 拼装批量插入语句
				StringBuffer bf = new StringBuffer();
				bf.append("insert into sys_role_resources (role_id,resources_id) values ");
				for (int i = 0; i < resourceIds.length; i++) {
					int resourceId = resourceIds[i];
					bf.append("(" + roleId + "," + resourceId + "),");
				}
				int result = this.executeSql(bf.deleteCharAt(bf.length() - 1).toString());
				if(result==1){
					isSuccess = true;
				}else{
					isSuccess = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

}
