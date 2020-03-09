package gzhu.edu.cn;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import gzhu.edu.cn.profile.entity.Major;
import gzhu.edu.cn.profile.service.IMajorService;

/**
 * 检索查询测试
 * @author dingguozhu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@Transactional
@EnableTransactionManagement
public class SearchTest {
	
	@Autowired
	private IMajorService majorService;
	
	@Test
	public void searchBySchoolId(){
		
		List<Major> majors = this.majorService.find(" college.school.id=1");
		System.out.println(majors.size());
		
		
	}

}
