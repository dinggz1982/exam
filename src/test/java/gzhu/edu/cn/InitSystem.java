package gzhu.edu.cn;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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

import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.College;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.profile.entity.Major;
import gzhu.edu.cn.profile.entity.School;
import gzhu.edu.cn.profile.entity.Subject;
import gzhu.edu.cn.profile.service.IClassInfoService;
import gzhu.edu.cn.profile.service.ICollegeService;
import gzhu.edu.cn.profile.service.ICourseService;
import gzhu.edu.cn.profile.service.IMajorService;
import gzhu.edu.cn.profile.service.ISchoolService;
import gzhu.edu.cn.profile.service.ISubjectService;
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

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IResourceService resourceService;

	@Autowired
	private ISchoolService schoolService;

	@Autowired
	private ICollegeService collegeService;

	@Autowired
	private IMajorService majorService;

	@Autowired
	private ISubjectService subjectService;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IClassInfoService classInfoService;

	@Test
	@Rollback(false)
	public void init() throws Exception {
		initUsers();
		initProfile();
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
		admin.setPassword(bc.encode("dgz123"));
		admin.setUsername("丁国柱");
		admin.setEmail("dgz888@163.com");
		admin.setNickName("丁国柱");
		admin.setSex("男");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role1);
		roles.add(role2);
		admin.setRoles(roles);

		userService.saveUser(admin);

		// 新增一些教师信息
		String[] teachers = { "曹卫真", "许易", "陈泽波", "曾亦琦", "张广生", "孔维宏", "梁斌", "杜玉霞", "黄琼珍", "梁瑞仪", "杨阳" };
		for (int i = 0; i < teachers.length; i++) {
			User user = new User();
			BCryptPasswordEncoder password = new BCryptPasswordEncoder(4);
			user.setPassword(bc.encode("password"));
			user.setUsername(teachers[i]);
			user.setEmail("");
			user.setRealname(teachers[i]);
			if (i >= 7) {
				user.setSex("女");
			} else {
				user.setSex("男");
			}
			roles.clear();
			roles.add(role2);
			user.setRoles(roles);
			userService.saveUser(user);
		}

		// 新增学生信息
		for (int i = 1; i <= 100; i++) {
			User user = new User();
			user.setCreateTime(new Date());
			user.setPassword(bc.encode("123456"));
			user.setUsername("学生" + i);
			user.setEmail("student" + i + "@163.com");
			user.setNickName("学生" + i);
			user.setSex("男");
			roles.clear();
			roles.add(role1);

			this.userService.save(user);
			System.out.println("保存：" + i);
		}

	}

	/**
	 * 初始化学校、学院、专业、课程等信息
	 */
	public void initProfile() {
		// 1.新增2所学校
		School gzhu = new School();
		gzhu.setAddress("广州大学城外环西路");
		gzhu.setName("广州大学");
		gzhu.setCreateTime(new Date());
		this.schoolService.save(gzhu);

		School sysu = new School();
		sysu.setAddress("广州大学城外环西路");
		sysu.setName("中山大学");
		sysu.setCreateTime(new Date());
		this.schoolService.save(sysu);

		/*
		 * //2.为广州大学新增几个学院 --这种新增方式会引起异常 String[] colleges =
		 * {"教育学院","计算机学院","土木工程学院","工商管理学院","机电工程学院"}; for (int i = 0; i <
		 * colleges.length; i++) { String collegeName = colleges[i]; College
		 * college = new College(); college.setName(collegeName);
		 * college.setSchool(gzhu); this.collegeService.save(college); }
		 */
		// 2.新增教育学院
		String collegeName = "教育学院";
		College college = new College();
		college.setName(collegeName);
		college.setSchool(gzhu);
		this.collegeService.save(college);

		// 3.为教育学院新增教育技术学专业
		String name = "教育技术学";
		Major major = new Major();
		major.setCode("100000");
		major.setName(name);
		major.setCollege(college);
		this.majorService.save(major);

		// 4.为教育技术学新增几个科目

		Subject javaSubject = new Subject();
		javaSubject.setName("Java程序设计");
		javaSubject.setDescription("这是Java程序设计课程");
		javaSubject.setMajor(major);
		this.subjectService.save(javaSubject);

		Subject pythonSubject = new Subject();
		pythonSubject.setName("Python程序设计");
		pythonSubject.setDescription("Python程序设计课程");
		pythonSubject.setMajor(major);
		this.subjectService.save(pythonSubject);

		// 5.创建班级
		//String[] classes = { "教技161", "教技171", "教技181", "教技191" };
		ClassInfo jiaoji181 = new ClassInfo();
		jiaoji181.setGrade("2018");
		jiaoji181.setMajor(major);
		jiaoji181.setName("教技181");
		this.classInfoService.save(jiaoji181);

		ClassInfo jiaoji191 = new ClassInfo();
		jiaoji191.setGrade("2019");
		jiaoji191.setMajor(major);
		jiaoji191.setName("教技191");
		this.classInfoService.save(jiaoji191);

		// 5.为科目创建课程
		String[] courses = { "2019-2020第二学期Java程序设计", "2019-2020第二学期Python程序设计" };
		// 教技181上python程序设计
		Course python = new Course();
		Set<ClassInfo> classInfos = new HashSet<ClassInfo>();
		classInfos.add(jiaoji181);
		python.setClassInfos(classInfos);
		//
		User user =this.userService.findByName("丁国柱");
		python.setTeacher(user);

		python.setSubject(pythonSubject);
		python.setStudyTime("2019-2020第二学期");
		python.setName(courses[1]);
		this.courseService.save(python);

		// 教技191上java程序设计
		Course java = new Course();
		classInfos.clear();
		classInfos.add(jiaoji191);
		java.setSubject(javaSubject);
		java.setStudyTime("2019-2020第二学期");
		java.setName(courses[0]);
		this.courseService.save(java);
	}

	/*
	*//**
		 * 新增学生用户
		 *//*
		 * @Test public void addStudent(){
		 * 
		 * for (int i = 1; i <= 100; i++) { User user = new User();
		 * user.setCreateTime(new Date()); BCryptPasswordEncoder bc = new
		 * BCryptPasswordEncoder(4); user.setPassword(bc.encode("123456"));
		 * user.setUsername("学生"+i); user.setEmail("student"+i+"@163.com");
		 * user.setNickName("学生"+i); user.setSex("男"); Set<Role> roles = new
		 * HashSet<Role>(); Role role1 = new Role(); role1.setId(2l);
		 * roles.add(role1); this.userService.save(user); }
		 * 
		 * }
		 */

	/*
	 * @Test public void getPageDataTest(){ PageData<User> data =
	 * this.userService.getPageData(1, 10, "");
	 * System.out.println(data.getTotalCount()); }
	 * 
	 * @Test
	 * 
	 * @Rollback(false) public void addUser() throws Exception{ for (int i = 0;
	 * i < 100; i++) { User user = new User(); BCryptPasswordEncoder bc = new
	 * BCryptPasswordEncoder(4); user.setPassword(bc.encode("123456"));
	 * user.setUsername("丁国柱"); user.setEmail("dgz888@163.com");
	 * user.setNickName("丁国柱"); user.setSex("男"); List<Role> roles = new
	 * ArrayList<Role>(); userService.saveUser(user); } }
	 * 
	 * 
	 * @Test
	 * 
	 * @Rollback(value=false) public void addRoleByUser() throws Exception{ User
	 * user = this.userService.findById(2l); List<Role> roles =
	 * this.roleService.findAll(); Set<Role> set = new HashSet<Role>(roles);
	 * user.setRoles(set); this.userService.update(user); }
	 */
}
