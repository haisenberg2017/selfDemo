package com.haisenberg.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haisenberg.sys.dao.UserMapper;
import com.haisenberg.sys.model.User;
import com.haisenberg.sys.service.UserService;
@Service  
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;
	

	public List<User> selectUserList(){
		return userMapper.selectUserList();
	}

	public int deleteByPrimaryKey(Integer id){
		return userMapper.deleteByPrimaryKey(id);
	}

	public int insert(User record){
		return userMapper.insert(record);
	}


	public User selectByPrimaryKey(Integer id){
		return userMapper.selectByPrimaryKey(id);
	}

	public User selectByUserName(String userName){
		return userMapper.selectByUserName(userName);
	}

	public int updateByPrimaryKey(User record){
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
	public User selectURPByUserName(String username) {
		return userMapper.selectURPByUserName(username);
	}
	
	@Override
	public int insertByMd5Password(User user) {
		//随机数生成盐
		SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
      //组合username,两次迭代，对密码进行加密
        String password_cipherText= new Md5Hash(user.getPassword(),user.getUsername()+salt,2).toHex();
        user.setPassword(password_cipherText);
        user.setSalt(salt);
		return userMapper.insert(user);
	}
}