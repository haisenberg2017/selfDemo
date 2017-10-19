package com.haisenberg.sys.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {
    
    /**
     * 定义一个跳转到WEB-INF/jsp目录下，名为resultPage.jsp的页面
     * @param request
     * @return
     */
    @RequestMapping("/test")
    public String resultPage(HttpServletRequest request,Model model){
         model.addAttribute("info", "此信息来自DemoController 的 test方法");
        return "resultPage";
    }
    
}