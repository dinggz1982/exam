package gzhu.edu.cn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IRoleService;
import gzhu.edu.cn.system.service.IUserService;

/**
 * 初始化用户信息
 * @author dingguozhu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement  
public class InitUser {

	@Resource
	private IUserService userService;

	@Resource
	private IRoleService roleService;

	@Test
	@Rollback(false)
	public void addAdminUser() throws Exception {
	//	System.out.println(this.userService.findById(1l).getEmail());

		
		
		// 管理員角色
		Role role1 = new Role();
		role1.setName("管理员");
		role1.setDescription("这是管理员角色");

		Role role2 = new Role();
		role2.setName("学生");
		role2.setDescription("这是学生角色");

		this.roleService.save(role1);
		this.roleService.save(role2);

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
		//roles.add(role2);
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
