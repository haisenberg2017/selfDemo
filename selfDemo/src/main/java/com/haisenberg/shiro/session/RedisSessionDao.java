package com.haisenberg.shiro.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import com.haisenberg.redis.RedisClient;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {
	private static Logger logger = Logger.getLogger(RedisSessionDao.class);
	private int expire;

	// 创建session，保存到数据库
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.doCreate(session);
		try {
			RedisClient.addObjectExpire(sessionId.toString(), serialize(session), expire);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sessionId;
	}

	// 获取session
	@Override
	protected Session doReadSession(Serializable sessionId) {
		// 先从缓存中获取session，如果没有再去数据库中获取
		Session session = super.doReadSession(sessionId);
		if (session == null) {
			try {
				session = (Session) deserialize(RedisClient.getObject(sessionId.toString()));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return session;
	}

	// 更新session的最后一次访问时间
	@Override
	protected void doUpdate(Session session) {
		super.doUpdate(session);
		try {
			RedisClient.updateObjectExpire(session.getId().toString(), serialize(session), expire);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// 删除session
	@Override
	protected void doDelete(Session session) {
		super.doDelete(session);
		try {
			RedisClient.delKey(session.getId() + "");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	// session序列化
	private static String serialize(Object obj) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return Base64.encodeToString(bos.toByteArray());
		} catch (Exception e) {
			throw new RuntimeException("serialize session error", e);
		} finally {
			try {
				oos.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	// session反序列化
	private static Object deserialize(String str) {
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(Base64.decode(str));
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("deserialize session error", e);
		} finally {
			try {
				ois.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

}
