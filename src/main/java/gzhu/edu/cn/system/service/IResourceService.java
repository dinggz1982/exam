package gzhu.edu.cn.system.service;

import java.util.Set;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;



/**
 * URL资源管理
 * <p>Title : IResourceService</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2017年11月26日 上午1:52:38
 */
public interface IResourceService extends BaseService<Resource, Long>{
	
	/**
	 * 根据角色获取Resource集
	 * <p>方法名:getResourcesByRoleId </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月26日 上午1:54:28
	 * @param roleId
	 * @return
	 */
	public Set<Resource> getResourcesByRoleId(Role role);
	
	/**
	 * 根据用户获取该用户可以获取的资源列表
	 * <p>方法名:getResourcesByUserId </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2017年11月26日 上午1:55:09
	 * @param userId
	 * @return
	 */
	public Set<Resource> getResourcesByUserId(User user);
	
	/**
	 * 根据用户获取权限菜单
	 * <p>方法名:getMenuByUser </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年1月30日 下午10:19:48
	 * @param user
	 * @return
	 */
	public Set<Resource> getMenuByUser(User user);
	
	/**
	 * 根据角色返回资源信息
	 * <p>方法名:getResourceTree </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年1月30日 下午11:56:20
	 * @param roleId
	 * @return
	 */
	public String getResourceTree(Integer roleId);
	
	/**
	 * 根据角色id获取权限树，用于角色权限配置
	 * @param roleId
	 * @return
	 */
	public String getAuthTreeByRoleId(Integer roleId);
	
	/**
	 * 通过url获取资源
	 * <p>方法名:getResourceByURL </p>
	 * <p>Description : </p>
	 * <p>Company : </p>
	 * @author 丁国柱
	 * @date 2018年1月31日 上午2:01:00
	 * @param url
	 * @return
	 */
	public Resource getResourceByURL(String url);
	
	/**
	 * 获取用户菜单，并封装成前端格式
	 * @param user
	 * @return
	 */
	public String getMenuStringByUser(User user);
}
