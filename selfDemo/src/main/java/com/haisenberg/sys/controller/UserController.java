package com.haisenberg.sys.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haisenberg.sys.model.User;
import com.haisenberg.sys.service.UserService;
@Controller  
@RequestMapping("/user") 
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        User user = userService.selectByPrimaryKey(2);
        model.addAttribute("user", user);  
        return "showUser";  
	}  
}