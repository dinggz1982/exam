package gzhu.edu.cn.system.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.entity.ResourceButton;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IResourceButtonService;

/**
 * 资源按钮
 * <p>Title : ResourceOperateService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年1月31日 上午1:07:23
 */
@Service("resourceOperateService")
public class ResourceButtonService extends BaseDAOImpl<ResourceButton, Long> implements IResourceButtonService {

	@Override
	public Set<ResourceButton> getResourceButtonByUserId(Resource resource, User user) {
		Set<ResourceButton> resourceButtons= new HashSet<ResourceButton>();
		// Set<Role> roles = user.getRoles();
		Set<Role> roleList = user.getRoles();
		if (roleList != null && roleList.size() > 0) {
			String ids = "";
			for (Iterator iterator = roleList.iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				long roleId = role.getId();
				ids += roleId + ",";
			}
			ids = ids.substring(0, ids.length() - 1);

			List<Object[]> buttons = this.findBySql(
					"select r.id,r.name,r.url,r.type from sys_resource_button r,sys_role_resourcebuttons rr where rr.role_id in(" + ids
							+ ") and rr.resourceButtons_id = r.id order by r.id asc");
			for (Iterator iterator = buttons.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				ResourceButton button = new ResourceButton();
				button.setId(Integer.parseInt(objects[0].toString()));
				button.setName(objects[1].toString());
				button.setUrl(objects[2].toString());
				button.setType(Integer.parseInt(objects[3].toString()));
				resourceButtons.add(button);
			}
		}
		
		return resourceButtons;
	}


}
