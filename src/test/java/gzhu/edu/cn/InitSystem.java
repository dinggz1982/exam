package gzhu.edu.cn;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IResourceService;
import gzhu.edu.cn.system.service.IRoleService;
import gzhu.edu.cn.system.service.IUserService;

/**
 * 初始化系统
 * @author dingguozhu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement  
public class InitSystem {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IResourceService resourceService;

	@Test
	@Rollback(false)
	public void init() throws Exception {
	//	System.out.println(this.userService.findById(1l).getEmail());

		//创建菜单1.系统管理
		Resource resource = new Resource();
		resource.setName("系统管理");
		resource.setMenu(true);
		resource.setRemark("系统管理");
		this.resourceService.save(resource);
		
		//用户管理菜单
		Resource userResource = new Resource();
		userResource.setName("用户管理");
		userResource.setParent(resource);
		userResource.setMenu(true);
		userResource.setUrl("/system/user/index");
		userResource.setRemark("系统管理");
		this.resourceService.save(userResource);
		
		//角色管理菜单
		Resource roleResource = new Resource();
		roleResource.setName("角色管理");
		roleResource.setParent(resource);
		roleResource.setMenu(true);
		roleResource.setUrl("/system/role/index");
		roleResource.setRemark("角色管理");
		this.resourceService.save(roleResource);

		//权限菜单
		Resource menuResource = new Resource();
		menuResource.setName("权限菜单");
		menuResource.setMenu(true);
		menuResource.setUrl("/system/resource/index");
		menuResource.setRemark("权限菜单");
		menuResource.setParent(resource);
		this.resourceService.save(menuResource);
		
		//系统日志
		Resource logResource = new Resource();
		logResource.setName("系统日志");
		logResource.setMenu(true);
		logResource.setUrl("/system/log/index");
		logResource.setRemark("logResource");
		logResource.setParent(resource);
		this.resourceService.save(logResource);
		
		
		Set<Resource> resources = new HashSet<Resource>();
		resources.add(logResource);
		resources.add(menuResource);
		resources.add(roleResource);
		resources.add(userResource);
		resources.add(resource);
		
		// 管理员角色
		Role role1 = new Role();
		role1.setName("管理员");
		role1.setDescription("这是管理员角色");
		role1.setResources(resources);
		role1.setResources(resources);
		
		//学生
		Role role2 = new Role();
		role2.setName("学生");
		role2.setDescription("这是学生角色");
		
		//教师
		Role role3 = new Role();
		role2.setName("教师");
		role2.setDescription("这是教师角色");

		this.roleService.save(role1);
		this.roleService.save(role2);
		this.roleService.save(role3);
		//新增管理员信息
		User admin = new User();
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
		admin.setPassword(bc.encode("dgz123"));
		admin.setUsername("丁国柱");
		admin.setEmail("dgz888@163.com");
		admin.setNickName("丁国柱");
		admin.setSex("男");
		Set<Role>  roles = new HashSet<Role>();
		roles.add(role1);
		roles.add(role2);
		admin.setRoles(roles);
		
		userService.saveUser(admin);
		
		//新增学生信息
		for (int i = 1; i <= 100; i++) {
			User user  = new User();
			user.setCreateTime(new Date());
			user.setPassword(bc.encode("123456"));
			user.setUsername("学生"+i);
			user.setEmail("student"+i+"@163.com");
			user.setNickName("学生"+i);
			user.setSex("男");
			roles.clear();
			roles.add(role1);
			
			this.userService.save(user);
			System.out.println("保存："+ i);
		}
		
	}
	/*
	*//**
	 * 新增学生用户
	 *//*
	@Test
	public void addStudent(){
		
		for (int i = 1; i <= 100; i++) {
			User user  = new User();
			user.setCreateTime(new Date());
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
			user.setPassword(bc.encode("123456"));
			user.setUsername("学生"+i);
			user.setEmail("student"+i+"@163.com");
			user.setNickName("学生"+i);
			user.setSex("男");
			Set<Role>  roles = new HashSet<Role>();
			Role role1 = new Role();
			role1.setId(2l);
			roles.add(role1);
			this.userService.save(user);
		}
		
	}*/
	
	/*@Test
	public void getPageDataTest(){
		PageData<User> data = this.userService.getPageData(1, 10, "");
		System.out.println(data.getTotalCount());
	}
	
	@Test
	@Rollback(false) 
	public void addUser() throws Exception{
		for (int i = 0; i < 100; i++) {
			User user = new User();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
			user.setPassword(bc.encode("123456"));
			user.setUsername("丁国柱");
			user.setEmail("dgz888@163.com");
			user.setNickName("丁国柱");
			user.setSex("男");
			List<Role>  roles = new ArrayList<Role>();
			userService.saveUser(user);
		}
	}

	
	@Test
	@Rollback(value=false)
	public void addRoleByUser() throws Exception{
		User user = this.userService.findById(2l);
		List<Role> roles = this.roleService.findAll();
		 Set<Role> set = new HashSet<Role>(roles);
		user.setRoles(set);
		this.userService.update(user);
	}*/
}
