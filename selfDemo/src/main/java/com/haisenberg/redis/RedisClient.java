package com.haisenberg.redis;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.haisenberg.sys.model.User;
import com.haisenberg.utils.JsonHelper;
import com.haisenberg.utils.MD5EncryptUtil;
import com.mysql.jdbc.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

	private static Logger logger = Logger.getLogger(RedisClient.class);
	private static JedisPool pool;
	private static String IP_ADDRESS = null;

	static {
		initialPool();
	}

	@PostConstruct
	public static void initialPool() {
		try {
			logger.info("------------- redis pool init start------------- ");

			Properties props = new Properties();
			props.load(RedisClient.class.getClassLoader().getResourceAsStream("redis.properties"));
			IP_ADDRESS = props.getProperty("redis.ip");
			// 创建jedis池配置实例
			JedisPoolConfig config = new JedisPoolConfig();

			// 设置池配置项值
			config.setTestWhileIdle(false);
			config.setMaxTotal(Integer.valueOf(props.getProperty("redis.pool.maxTotal")));
			config.setMaxIdle(Integer.valueOf(props.getProperty("redis.pool.maxIdle")));
			config.setMaxWaitMillis(Long.valueOf(props.getProperty("redis.pool.maxWaitMillis")));
			config.setTestOnBorrow(Boolean.valueOf(props.getProperty("redis.pool.testOnBorrow")));
			config.setTestOnReturn(Boolean.valueOf(props.getProperty("redis.pool.testOnReturn")));

			pool = new JedisPool(config, IP_ADDRESS, Integer.valueOf(props.getProperty("redis.port")));

			boolean connected = isConnected();
			if (!connected) {
				logger.error("redis 初始化出错 缓存服务器连接不上！ ");
				throw new Exception("IP:" + IP_ADDRESS + ", redis服务器不可以连接~~~，请检查配置 与redis 服务器");
			}

			logger.info("------------- redis pool init end------------- ");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Error("IP:" + IP_ADDRESS + ",设置redis服务器出错", e);
		}
	}

	public static boolean isConnected() {
		return getJedis().isConnected();
	}

	public void destory() {
		pool.destroy();
	}

	/**
	 * 获取连接
	 */
	public static synchronized Jedis getJedis() {
		try {
			if (pool != null) {
				return pool.getResource();
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.info("连接池连接异常");
			return null;
		}

	}

	/**
	 * @Description:设置失效时间
	 * @param @param
	 *            key
	 * @param @param
	 *            seconds
	 * @param @return
	 * @return boolean 返回类型
	 */
	public static void disableTime(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.expire(key, seconds);

		} catch (Exception e) {
			logger.debug("设置失效失败.");
		} finally {
			getColse(jedis);
		}
	}

	/**
	 * @Description:插入对象
	 * @param @param
	 *            key
	 * @param @param
	 *            obj
	 * @param @return
	 * @return boolean 返回类型
	 */
	public static boolean addObject(String key, Object obj) {
		boolean flag=false;
		Jedis jedis = null;
		String value = JSONObject.toJSONString(obj);
		try {
			jedis = getJedis();
			String code = jedis.set(key, value);
			if (code.equalsIgnoreCase("ok")) {
				flag=true;
			}else{
				flag=false;
			}
		} catch (Exception e) {
			logger.debug("插入数据有异常.");
			flag=false;
		} finally {
			getColse(jedis);
		}
		return flag;
	}

	/**
	 * @Description:插入对象
	 * @param @param
	 *            key
	 * @param @param
	 *            obj
	 * @param @return
	 * @return boolean 返回类型
	 */
	public static String getObject(String key) {

		Jedis jedis = null;
		String str = null;
		try {
			jedis = getJedis();
			str = jedis.get(key);
		} catch (Exception e) {
			logger.debug("插入数据有异常.");
		} finally {
			getColse(jedis);
		}
		return str;
	}

	/**
	 * @Description:存储key~value
	 * @param @param
	 *            key
	 * @param @param
	 *            value
	 * @return void 返回类型
	 */

	public static boolean addValue(String key, String value) {
		boolean flag=false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			String code = jedis.set(key, value);
			if (code.equalsIgnoreCase("ok")) {
				flag=true;
			}else{
				flag=false;
			}
		} catch (Exception e) {
			logger.debug("插入数据有异常.");
			flag=false;
		} finally {
			getColse(jedis);
		}
		return flag;

	}

	/**
	 * @Description:删除key
	 * @param @param
	 *            key
	 * @param @return
	 * @return boolean 返回类型
	 */
	public static boolean delKey(String key) {
		boolean flag=false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Long code = jedis.del(key);
			if (code >= 1) {
				flag=true;
			}else{
				flag=false;
			}
		} catch (Exception e) {
			logger.debug("删除key异常.");
			flag=false;
		} finally {
			getColse(jedis);
		}
		return flag;

	}

	/**
	 * 判断key在redis中是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		boolean flag=false;
		if (StringUtils.isNullOrEmpty(key))
			flag=false;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			flag=jedis.exists(key);
		}  catch (Exception e) {
			logger.debug("查询key是否存在异常.");
			flag=false;
		}finally {
			getColse(jedis);
		}
		return flag;
	}

	/**
	 * @Description: 关闭连接
	 * @param @param
	 *            jedis
	 * @return void 返回类型
	 */

	public static void getColse(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 通过session获取redis中的用互信息
	 * @param request
	 * @return
	 */
	public static User getLoginUserBySession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userCache = null;
		if(session!=null){
			String json = RedisClient.getObject("loginUser");
			userCache = JsonHelper.jsonToBean(json, User.class);
		}
		return userCache;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String loginCode=MD5EncryptUtil.getEncryption(4+"admin");
		System.out.println(loginCode);
		boolean delKey = RedisClient.delKey(loginCode);
		System.out.println(delKey);
	}

}