package com.haisenberg.sys.dao;

import java.util.List;

import com.haisenberg.sys.model.User;

public interface UserMapper {

	List<User> selectUserList();

	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	User selectByPrimaryKey(Integer userId);

	User selectByUserName(String username);

	int updateByPrimaryKey(User record);
	
	User selectURPByUserName(String username);
	
}