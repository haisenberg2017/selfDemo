package com.haisenberg.sys.dao;

import java.util.List;
import java.util.Map;

import com.haisenberg.sys.model.Menu;

public interface MenuMapper {
	int deleteByPrimaryKey(Integer menuId);

	int insert(Menu record);

	Menu selectByPrimaryKey(Integer menuId);

	int updateByPrimaryKey(Menu record);

	List<Menu> selectByIdOrPid(Map<String, Object> map);
}