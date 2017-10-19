package com.haisenberg.sys.dao;

import java.util.List;

import com.haisenberg.sys.model.SysParam;

public interface SysParamMapper {
	List<SysParam> selectParamList();

	int deleteByPrimaryKey(Integer id);

	int insert(SysParam record);

	SysParam selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(SysParam record);
}