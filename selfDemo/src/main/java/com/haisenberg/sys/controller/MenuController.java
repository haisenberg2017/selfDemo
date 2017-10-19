package com.haisenberg.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haisenberg.redis.RedisClient;
import com.haisenberg.sys.model.User;
import com.haisenberg.sys.service.MenuService;
import com.haisenberg.vo.TreeVo;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	
    @RequestMapping("/menuTree")
    public String menuTree(HttpServletRequest request,Model model,String userName,String password){
    	List<TreeVo> menuList = menuService.menuTree(); 
    	/*User user = RedisClient.getLoginUserBySession(request);
    	if(user==null){
    		return "redirect:/login/logout";
    	}
    	model.addAttribute("loginUser", user);    */	
        model.addAttribute("menuList", menuList);  
        return "menu";
    }
    
    @RequestMapping("/menuJson")
    public String menuJson(HttpServletRequest request,Model model){
    	String menuJson = menuService.menuJson();  	
        model.addAttribute("menuJson",menuJson);  
        return "sys/menuTree";
    }
}
