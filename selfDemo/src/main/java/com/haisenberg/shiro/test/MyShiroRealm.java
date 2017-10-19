package com.haisenberg.shiro.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.haisenberg.utils.MD5EncryptUtil;

public class MyShiroRealm extends AuthorizingRealm {
	private static final String USER_NAME = "admin";
	private static final String PASSWORD = "abc123";

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("administrator");//添加角色
        permissions.add("newPage.jhtml");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        return info; 
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		 UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	        if(token.getUsername().equals(USER_NAME)){
	            return new SimpleAuthenticationInfo(USER_NAME, MD5EncryptUtil.getEncryption(PASSWORD), getName());  
	        }else{
	            throw new AuthenticationException();  
	        }
	}

}
