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
import java.util.Scanner;
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




		public static void main(String[] args) {
			Scanner in =new Scanner(System.in);
			int a;                                      //定义数1
			int b;                                      //定义数2
			a = in.nextInt();                           //数1 输入
			b = in.nextInt();                           //数2 输入
			if(a>b)                                     //if语句  比较大小
			{
				System.out.println(a);
			}
			else
			{
				System.out.print(b);
			}
		}


}
