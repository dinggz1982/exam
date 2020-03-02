package gzhu.edu.cn.system.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import gzhu.edu.cn.base.entity.BaseEntity;
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="name",unique=true)
    private String name;
    
    @Lob   
    @Basic(fetch = FetchType.LAZY)   
    @Type(type="text")  
    @Column(name="description")  
    private String description;
    
    //角色具有的资源权限
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Set<Resource> resources;

    //角色对应的资源里的按钮权限
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Set<ResourceButton> resourceButtons;
    
    public Set<ResourceButton> getResourceButtons() {
		return resourceButtons;
	}

	public void setResourceButtons(Set<ResourceButton> resourceButtons) {
		this.resourceButtons = resourceButtons;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
