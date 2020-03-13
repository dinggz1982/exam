package gzhu.edu.cn;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.profile.service.IClassInfoService;

/**
* @author : 丁国柱  
* @email : dinggz@gzhu.edu.cn
* @version : 2020年3月11日 上午4:44:14
* @description :  测试班级树
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement
public class ClassTreeTest {
	
	@Autowired
	private IClassInfoService classInfoService;
	
	@Test
	public void test() throws SQLException{
		
		System.out.println(this.classInfoService.getClassTreeByCourseId(null));
		
	}

}
