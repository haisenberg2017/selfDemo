package com.haisenberg.test;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.haisenberg.sys.model.User;
import com.haisenberg.sys.service.MenuService;
import com.haisenberg.sys.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	//  private ApplicationContext ac = null;
	    @Autowired
		private UserService userService;
	    @Autowired
		private MenuService menuService;
/*	    @Test
	    public void test1() {
	        User user = userMapper.selectByPrimaryKey(2);
	        System.out.println(user.getUserName());
	        logger.info("值："+user.getUserName());
	        logger.info(user.toString());
	    	List<User> list = userService.selectUserList();
	    	for (User user : list) {
	    		logger.info(user);
			}
	    	 String menuJson = menuService.menuJson();
	    	logger.info(menuJson.toString());
	    }*/
	    

}
