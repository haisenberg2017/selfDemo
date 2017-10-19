package com.haisenberg.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haisenberg.sys.model.SysParam;
import com.haisenberg.sys.service.SysParamService;

@Controller
@RequestMapping("/sys/param")
public class SysParamController {
	@Autowired
	private SysParamService sysParamService;
	
	@RequestMapping("/list")  
    public String list(HttpServletRequest request,Model model){  
		List<SysParam> list = sysParamService.selectParamList();
		model.addAttribute("list", list);  
        return "sys/paramList";  
	}  

}
