package com.iot.spring.controller;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class UrlController {
	@Autowired
	private SqlSessionFactory ssf;
	
	private static final Logger logger = LoggerFactory.getLogger(UrlController.class);	
	
	private String getUrl(String url, String rootPath) {
		
		
		return  url.replace(rootPath + "/path",""); 
		
	}
	
	@RequestMapping("/path/**")
	public String forwardJsp(HttpServletRequest request) {		
		String url = request.getRequestURI();
		String rootPath = request.getContextPath();
		url = getUrl(url,rootPath);
		logger.info("path=>{}", url);
		return url;				
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String home(Locale locale, Model model) {
		//model.addAttribute("userList", ssf.openSession().selectList("user.selectuser"));
		model.addAttribute("title","IOT-SPRING-2");
		return "index";
	}
}
