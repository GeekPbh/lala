package com.pbh.Controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.pbh.dao.Daola;
import com.pbh.haha.Content;
import com.pbh.haha.Logining;
import com.pbh.haha.User;

@Controller
@ControllerAdvice
public class IndexController {


	@Autowired
	private Daola daola;
	
	/*@GetMapping("/")
	public String index() {
		return "index";
	}*/
//登录	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	@PostMapping("/login")
	public String logins(String name,String password,HttpSession session,Model m) {
		User user = daola.findByUsename(name);
		if(user != null) {
			if(password.equals(user.getPassword())) {
				session.setAttribute("sessuser", user);
				
				return "redirect:/content";
			}
			else
				return "error";
		}
		else
			return "Notname";
}
		
//查询个人内容
		@GetMapping("/find")
		public String find(HttpSession session,Model m) {
			User user =(User)session.getAttribute("sessuser");
			List<Content> content = daola.findOne(user);
			m.addAttribute("content",content);
			return "find";
		}
//下号		
	@GetMapping("/Cancellation")
		public String tuihcu(HttpSession session) {
			User user = new User();
			session.setAttribute("sessuser", user);
			session.removeAttribute("sessuser");
			return "login";
		}
		
	
//注册
	@GetMapping("/registered") ///////////////////////后台传前台数据
	public String add() {
		return "registered";
	} 
	@PostMapping("/save") ///////前台传后台数据
	public String Registered(@Valid Logining l,String name,String email,String code) {
		if(!email.equals(code)){
			return "error";
		}

		User user = new User();
	    user.setName(name);
	    if(daola.findByUsename(user.getName())!=null) {
	    	return "same";
	    }
		user.setPassword(l.getPassword());
		user.setPassword1(l.getPassword1());
		user.setPhone(l.getPhone());
		if(l.getPassword().equals(l.getPassword1())) {
			daola.save(user);
			return "redirect:/";
		}else {
			return "incorrect";
		}
		
	}
	
	
//注销账户
	@GetMapping("/del")
	public String del() {
		return "delete";
	}
	@PostMapping("/delete")
	public String dele(String name) {
		User user = daola.findByUsename(name);
		daola.delete(user);
			return "bye";
	}
	
	   @GetMapping("/deletes")
	   public String de(String id) {
		   Content content = daola.findByid(id);
		   daola.delete(content);
		   return "redirect:/find";
	   }


	
	
//修改密码
	@GetMapping("/change")//打开网页端口
	public String Change() {
	
		return "change";
	}               
	@PostMapping("/updates")//返回网页端口
	public String Up(@Valid Logining l, BindingResult bindingResult) {
		User user = daola.findByUsename(l.getName());
		user.setName(l.getName());
		if(bindingResult.hasErrors()){
			return "upwu";
		}
		user.setPassword2(l.getPassword2());	
		if(l.getPassword2().equals(user.getPassword())) {
			user.setPassword(l.getPassword());
			user.setPassword1(l.getPassword1());
				if(l.getPassword().equals(l.getPassword1())) {
					daola.save(user);
					return "update";
						}else
							return "upmi";
								}else
									return "upmicuo";
				}
	
//修改内容	
	@GetMapping("/up")
	public String Update(HttpSession session,Model m,String id){
		Content content = daola.findByid(id);
		m.addAttribute("update",content);
		User user = (User) session.getAttribute("sessuser");
		m.addAttribute("user",user);
		return "contupdate";
	}
   @PostMapping("/ups")
   public String Merge(String id,String contents){
	   Content content = daola.findByid(id);
	   content.setContents(contents);
	   daola.update(content);
	   return "redirect:/find";
   }
	
	
//写入内容
@GetMapping("/content")
	public String content(HttpSession session,Model m ) {		//session刷新页面不会丢失页面
		if(session.getAttribute("sessuser")==null){
			return "redirect:/login";
		}else {
			User user = (User) session.getAttribute("sessuser");
			m.addAttribute("user",user);
			return "content";
		}
}
	@PostMapping("/cont")
	 public String cont(String contents,HttpSession session)
	 {
		 User user = (User) session.getAttribute("sessuser");
		 Content content = new Content();
		 content.setId(UUID.randomUUID().toString().replace("-", ""));
		 content.setContents(contents);
		 content.setUser(user);
		 daola.save(content);
		 return "return";
	 }


	

	

}