package gzhu.edu.cn.system.entity;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import gzhu.edu.cn.base.entity.BaseEntity;


/**
 * 用户类
 * <p>
 * Title : User
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Company :
 * </p>
 * @author 丁国柱
 * @date 2017年12月27日 下午6:09:13
 */
@Entity(name = "sys_user")
public class User extends BaseEntity implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@OrderBy("id asc")
	private Set<Role> roles;
	
	@Column(name = "state", columnDefinition = ("bit(1) comment '用户状态' default 0 "))
	private boolean state;

	private Date createTime;

	public User() {
	}

	@Column(name = "username", unique = true, columnDefinition = ("varchar(255) comment '用户名'"))
	private String username;
	
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	private String realname;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "nickname")
	private String nickName;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "url")
	private String url;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(columnDefinition = "varchar(255) comment '民族'")
	private String ethnicity;
	
	@Column(columnDefinition = "varchar(255) comment '学号'",unique=true)
	private String xuehao;
	
	@Column(columnDefinition = "bit(1) DEFAULT 1 comment '账号是否过期'")
	private boolean accountNonExpired;
	
	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getXuehao() {
		return xuehao;
	}

	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}

	@Column(name = "sex")
	private String sex;
	
	@Column(columnDefinition = "varchar(255) comment '个人头像地址' DEFAULT '/assets/images/logo.png'")
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(String userName, String password, Collection<GrantedAuthority> grantedAuths) {
		this.username = userName;
		this.setGrantedAuths(grantedAuths);
		this.password = password;
	}

	// URL权限
	@Transient
	private Collection<GrantedAuthority> grantedAuths;

	public Collection<GrantedAuthority> getGrantedAuths() {
		return grantedAuths;
	}

	public void setGrantedAuths(Collection<GrantedAuthority> grantedAuths) {
		this.grantedAuths = grantedAuths;
	}


	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuths;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	@Column(columnDefinition = "bit(1) DEFAULT 1 comment '账号是否被锁定'")
	private boolean accountNonLocked;
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}
	@Column(columnDefinition = "bit(1) DEFAULT 1 comment '账号的资格证明是否过期'")
	private boolean credentialsNonExpired;
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}

	@Column(columnDefinition = "bit(1) DEFAULT 1 comment '账号是否被启用'")
	private boolean enabled;

	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		
		return enabled;
	}

}
