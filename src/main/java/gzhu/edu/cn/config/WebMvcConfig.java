package gzhu.edu.cn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

	/**
	 * 将assets映射为静态地址
	 */
	public  void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
        
     // 注意如果filePath是写死在这里，一定不要忘记尾部的/或者\\，这样才能读取其目录下的文件
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/META-INF/resources/",
                "classpath:/resources/",
                "classpath:/static/",
                "classpath:/public/",
                "classpath:/webapp/");
        //一定要加上**，这样才能涵盖uploadFolder的所有文件
        registry.addResourceHandler(staticAccessPath+"**").addResourceLocations("file:/" + uploadFolder);
        
    }



}