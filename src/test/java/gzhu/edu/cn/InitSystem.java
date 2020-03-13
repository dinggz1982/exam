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

import gzhu.edu.cn.profile.service.IStudyTeamService;
import gzhu.edu.cn.system.entity.Resource;
import gzhu.edu.cn.system.entity.Role;
import gzhu.edu.cn.system.entity.User;
import gzhu.edu.cn.system.service.IResourceService;
import gzhu.edu.cn.system.service.IRoleService;
import gzhu.edu.cn.system.service.IUserService;

/**
 * 初始化系统
 * 
 * @author dingguozhu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement
public class InitSystem {
	//管理员用户名
	public String adminUserName="admin";
	
	public String adminPassword = "admin";

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IResourceService resourceService;
	
	@Autowired
	private IStudyTeamService studyTeamService;

	
	@Test
	@Rollback(false)
	public void init() throws Exception {
		initUsers();
	}

	/**
	 * 初始化用户
	 * 
	 * @throws Exception
	 */
	public void initUsers() throws Exception {
		// System.out.println(this.userService.findById(1l).getEmail());

		// 创建菜单1.系统管理
		Resource resource = new Resource();
		resource.setName("系统管理");
		resource.setMenu(true);
		resource.setRemark("系统管理");
		this.resourceService.save(resource);

		// 用户管理菜单
		Resource userResource = new Resource();
		userResource.setName("用户管理");
		userResource.setParent(resource);
		userResource.setMenu(true);
		userResource.setUrl("/system/user/index");
		userResource.setRemark("系统管理");
		this.resourceService.save(userResource);

		// 角色管理菜单
		Resource roleResource = new Resource();
		roleResource.setName("角色管理");
		roleResource.setParent(resource);
		roleResource.setMenu(true);
		roleResource.setUrl("/system/role/index");
		roleResource.setRemark("角色管理");
		this.resourceService.save(roleResource);

		// 权限菜单
		Resource menuResource = new Resource();
		menuResource.setName("权限菜单");
		menuResource.setMenu(true);
		menuResource.setUrl("/system/resource/index");
		menuResource.setRemark("权限菜单");
		menuResource.setParent(resource);
		this.resourceService.save(menuResource);

		// 系统日志
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

		// 学生
		Role role2 = new Role();
		role2.setName("学生");
		role2.setDescription("这是学生角色");

		// 教师
		Role role3 = new Role();
		role3.setName("教师");
		role3.setDescription("这是教师角色");

		this.roleService.save(role1);
		this.roleService.save(role2);
		this.roleService.save(role3);

		// 新增管理员信息
		User admin = new User();
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder(4);
		admin.setPassword(bc.encode(adminUserName));
		admin.setUsername(adminUserName);
		admin.setEmail("");
		admin.setNickName("丁国柱");
		admin.setSex("男");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role1);
		roles.add(role2);
		admin.setRoles(roles);

		userService.saveUser(admin);

		// 新增一些教师信息
		String[] teachers = { "教师1","教师2","教师3" };
		for (int i = 0; i < teachers.length; i++) {
			User user = new User();
			BCryptPasswordEncoder password = new BCryptPasswordEncoder(4);
			user.setPassword(password.encode("123456"));
			user.setUsername(teachers[i]);
			user.setEmail("");
			user.setRealname(teachers[i]);
			if (i >= 7) {
				user.setSex("女");
			} else {
				user.setSex("男");
			}
			roles.clear();
			roles.add(role3);
			user.setRoles(roles);
			userService.saveUser(user);
		}

		// 新增学生信息
		for (int i = 1; i <= 100; i++) {
			User user = new User();
			user.setCreateTime(new Date());
			BCryptPasswordEncoder password = new BCryptPasswordEncoder(4);
			user.setPassword(password.encode("123456"));
			user.setUsername("学生" + i);
			user.setEmail("student" + i + "@163.com");
			user.setNickName("学生" + i);
			user.setSex("男");
			roles.clear();
			roles.add(role1);
			user.setRoles(roles);
			this.userService.save(user);
			System.out.println("保存：" + i);
		}

	}
	
	/**
	 * 初始化学期
	 * @Description
	 * @author 丁国柱
	 * @date 2020年3月10日 下午10:46:53
	 */
	/*@Test
	public void intStudyTeam(){
		
		for (int i = 2019; i < 2025; i++) {
			StudyTeam studyTeam1  = new StudyTeam();
			String name1 = i+"年" +"-" +  (i+1)+"年第一学期";
			studyTeam1.setName(name1);
			studyTeam1.setYear(String.valueOf(i));
			this.studyTeamService.save(studyTeam1);
			
			StudyTeam studyTeam2  = new StudyTeam();
			String name2 = i+"年" +"-" +  (i+1)+"年第二学期";
			studyTeam2.setName(name2);
			studyTeam2.setYear(String.valueOf((i+1)));
			this.studyTeamService.save(studyTeam2);
			
		}
		
		
	}*/
	
}
