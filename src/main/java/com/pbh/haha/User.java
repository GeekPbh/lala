package com.pbh.haha;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class User {
	  @Id
	  private int id;
	    private String name;
	    private String password;
	    private String password1;
	    private String password2;
	    private String phone;
	    private String salt;
	    private String headUrl;
	    private String role;
	    @OneToMany(mappedBy="user") ///一对多
	    private Set<Content> content;


	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	    
	    public String getPassword1() {
	        return password1;
	    }

	    public void setPassword1(String password1) {
	        this.password1 = password1;
	    }
	    
	    public String getPassword2() {
	        return password2;
	    }

	    public void setPassword2(String password2) {
	        this.password2 = password2;
	    }
	    
	    public String GetPhone() {
	    	return phone;
	    }
	    
	    public void setPhone(String phone) {
	    	this.phone = phone;
	    }

	    public String getSalt() {
	        return salt;
	    }

	    public void setSalt(String salt) {
	        this.salt = salt;
	    }

	    public String getHeadUrl() {
	        return headUrl;
	    }

	    public void setHeadUrl(String headUrl) {
	        this.headUrl = headUrl;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }

		public Set<Content> getContent() {
			return content;
		}

		public void setContent(Set<Content> content) {
			this.content = content;
		}
	}