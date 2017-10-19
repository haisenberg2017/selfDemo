package com.haisenberg.sys.service;

import java.util.List;

import com.haisenberg.sys.model.User;

public interface UserService {
	

	List<User> selectUserList();

	int insert(User record);

	User selectByPrimaryKey(Integer userId);

	User selectByUserName(String userName);

	int updateByPrimaryKey(User record);
	
	User selectURPByUserName(String username);
	
	int insertByMd5Password(User record);
}