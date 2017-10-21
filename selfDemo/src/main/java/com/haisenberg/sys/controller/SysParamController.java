package com.haisenberg.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.haisenberg.page.PageInfo;
import com.haisenberg.sys.model.SysParam;
import com.haisenberg.sys.service.SysParamService;

@Controller
@RequestMapping("/sys/param")
public class SysParamController {
	@Autowired
	private SysParamService sysParamService;
	
	@RequestMapping("/list")  
    public String list(Model model,int pageNum, int pageSize){  
		PageInfo<SysParam> page = sysParamService.findAllByPage(pageNum, pageSize);
		model.addAttribute("page", page);  
        return "sys/paramList";  
	}  

}
