package com.haisenberg.sys.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request, Model model) {
		return "login";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model, String userName, String password, boolean rememberMe)
			throws UnsupportedEncodingException {

		UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray());
		token.setRememberMe(rememberMe);
		Subject currentUser = SecurityUtils.getSubject();
		String error = "";
		try {
			currentUser.login(token);
			return "redirect:/menu/menuTree";
		} catch (UnknownAccountException ex) {// 用户名没有找到
			error = "您输入的用户名不存在！";
		} catch (IncorrectCredentialsException ex) {// 用户名密码不匹配
			error = "用户名密码不匹配 ！";
		} catch (ExcessiveAttemptsException e) {
			error = "密码错误次数已超五次，账号锁定1小时！";
		} catch (AuthenticationException ex) {// 其他的登录错误
			ex.printStackTrace();
			error = "其他的登录错误  ！";
		}
		model.addAttribute("message", error);
		currentUser.logout();
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "login";
	}
}
