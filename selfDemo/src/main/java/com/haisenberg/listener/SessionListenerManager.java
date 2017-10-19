package com.haisenberg.listener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.haisenberg.redis.RedisClient;

public class SessionListenerManager implements HttpSessionListener{
	public static final Logger LOG = Logger.getLogger(SessionListenerManager.class);
	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		String loginCode = (String)se.getSession().getAttribute("loginCode");
		boolean delKey = RedisClient.delKey(loginCode);//session失效后清空对应redis缓存
		if(delKey){
			LOG.info("session失效后清空对应redis缓存成功！");
		}else{
			LOG.error("session失效后清空对应redis缓存失败！");
		}
		
	}
}
