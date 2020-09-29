package gzhu.edu.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExamGzhuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamGzhuApplication.class, args);
	}

}
