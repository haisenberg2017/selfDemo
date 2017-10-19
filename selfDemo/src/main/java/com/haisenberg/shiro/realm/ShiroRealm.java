package com.haisenberg.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.haisenberg.sys.model.Permission;
import com.haisenberg.sys.model.Role;
import com.haisenberg.sys.model.User;
import com.haisenberg.sys.service.UserService;

public class ShiroRealm extends AuthorizingRealm {
	private static Logger logger = Logger.getLogger(ShiroRealm.class);
	@Autowired
	private UserService userService;	
	
	/*
	 * 认证（登陆）
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = (String) token.getPrincipal();
		System.out.println("pwd:" + token.getCredentials().toString());
		logger.info("[用户:" + username + "|系统权限认证]");
		User sqluser = userService.selectByUserName(username);
		if (sqluser != null) {
			// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
			System.out.println("Realm:" + ByteSource.Util.bytes(sqluser.getCredentialsSalt()));
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sqluser.getUsername(), sqluser.getPassword(),
					ByteSource.Util.bytes(sqluser.getCredentialsSalt()), this.getName());// realm
			logger.info("[用户:" + username + "|系统权限认证完成]");
			return authenticationInfo;
		}
		return null;
	}
	
	
	/*
	 * 授权
	 *
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 直接调用getPrimaryPrincipal得到之前传入的用户名
		String username = (String) principals.getPrimaryPrincipal();
		logger.info("[用户:" + username + "|权限授权]");
		User user = userService.selectURPByUserName(username);
		if (user != null) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			for (Role role : user.getRoles()) {
				// 根据用户名调用UserService接口获取角色及权限信息
				authorizationInfo.addRole(role.getRoleId().toString());
				List<String> idList = new ArrayList<>();
				for (Permission permission : role.getPermissions()) {
					idList.add("" + permission.getPermissionId());
				}
				authorizationInfo.addStringPermissions(idList);
			}
			logger.info("[用户:" + username + "|权限授权完成]");
			return authorizationInfo;
		} else {
			return null;
		}

	}



}
