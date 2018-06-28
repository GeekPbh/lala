package com.pbh.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pbh.haha.Content;
import com.pbh.haha.User;

@Component
@Transactional
public class Daola {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession(){
   	 return entityManager.unwrap(Session.class);
   }
	
	public void save(User user){
		this.getSession().save(user);
    }
	public void save(Content content) {
		this.getSession().save(content);
	}
	
	
	public void delete(User user) {
		this.getSession().delete(user);
	}
	public void delete(Content content) {
		this.getSession().delete(content);
	}
	
	public void update(User user) {
		this.getSession().update(user);
	}
	public void update(Content content) {
		this.getSession().update(content);
	}
	
	public List<Content>findOne(User user){
		DetachedCriteria ha = DetachedCriteria.forClass(Content.class);
		Criteria criteria = ha.getExecutableCriteria(getSession());
		ha.add(Restrictions.eq("user",user));
		List<Content> list = criteria.list();
		return list;
	}
	
	public User findByUsename(String name){										//查询数据库某字段数据
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
       	dc.add(Restrictions.eq("name",name));
       	Criteria b = dc.getExecutableCriteria(getSession());
       	List<User>list=b.list();
       	if(list != null && list.size()>0) {
       		return list.get(0);
       	}else{
       		return null ;
       	}
      
     }
	
	public User findById(int id) {
		User user = (User)getSession().get(User.class, id);
		return user;
	}
	
	public Content findByid(String id) {
		Content content = (Content)getSession().get(Content.class, id);
		return content;
	}

}
