package gzhu.edu.cn.system.security;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private ExamUserDetailsService userDetailsService;

	@Resource
	private ExamSecurityMetadataSource examSecurityMetadataSource;

	@Resource
	private ExamAccessDecisionManager examAccessDecisionManager;
	
	@Resource
	private ExamAccessDeniedHandler accessDeniedHandler;

	// http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
	// 再次访问index页面无需登录直接访问
	// 访问http://localhost:8080/home 不拦截，直接访问，
	// 访问http://localhost:8080/hello 需要登录验证后，且具备
	// “ADMIN”权限hasAuthority("ADMIN")才可以访问
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		//http.csrf().disable();// 停掉csrf，如正式使用，则应该配置csrf
		//http.authorizeRequests().antMatchers("/assets/**", "/static/**").permitAll().antMatchers("/**").authenticated();
	
		/*ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        registry.antMatchers("/assets/**", "/static/**","/login","/").permitAll().anyRequest().authenticated().and().csrf().disable();
		
		
		http
        .authorizeRequests() // 授权配置
       // .antMatchers("/assets/**", "/static/**","/login","/").permitAll() // 免认证静态资源路径
        .anyRequest()  // 所有请求
        .authenticated(); // 都需要认证
		
		

		http.formLogin().loginPage("/login")// 指定登录页是”/login”
				// .failureHandler(authenticationFailureHandler())
				.permitAll().successHandler(loginSuccessHandler()) // 登录成功后可使用loginSuccessHandler()存储用户信息，可选。
				.and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).expiredUrl("/login")
				.and().and().logout().logoutSuccessUrl("/login") // 退出登录后的默认网址是”/login”
				.permitAll().invalidateHttpSession(true).and().rememberMe()// 登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
				.tokenValiditySeconds(20);*/
		http.formLogin().loginPage("/login")// 指定登录页是”/login”
		.permitAll().successHandler(loginSuccessHandler()) // 登录成功后可使用loginSuccessHandler()存储用户信息，可选。
		.and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).expiredUrl("/login")
		.and().and().logout().logoutSuccessUrl("/login") // 退出登录后的默认网址是”/login”
		.permitAll().invalidateHttpSession(true).and().rememberMe()// 登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
		.tokenValiditySeconds(20);
		
		/*http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				o.setSecurityMetadataSource(examSecurityMetadataSource);
				o.setAccessDecisionManager(examAccessDecisionManager);
				return o;
			}
		});*/
		
		http.authorizeRequests()
        .antMatchers("/login").permitAll()  //主路径直接请求
        .anyRequest().authenticated()    //请他请求都要验证
        .and()
        .logout().permitAll()   //允许注销
        .and()
        .formLogin();  //允许表单登录
        http.csrf().disable();  //关闭csrf的认证
        
        //访问没有权处理器
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被SpringSecurity拦截的问题
        web.ignoring().antMatchers("/static/**","/assets/**");//这样我的webapp下static里的静态资源就不会被拦截了，也就不会导致我的网页的css全部失效了……
	}

	/**
	 * 认证管理
	 * <p>
	 * 方法名:configureGlobal
	 * </p>
	 * <p>
	 * Description :
	 * </p>
	 * <p>
	 * Company :
	 * </p>
	 * 
	 * @author 丁国柱
	 * @date 2017年12月11日 下午12:30:01
	 * @param auth
	 * @throws Exception
	 */

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 指定密码加密所使用的加密器为passwordEncoder()
		// 需要将密码加密后写入数据库
		// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
		auth.eraseCredentials(false).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}

	@Bean
	public ExamAuthenticationFailureHandler authenticationFailureHandler() {
		return new ExamAuthenticationFailureHandler();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

}