package com.haisenberg.sys.service;

import java.util.List;

import com.haisenberg.sys.model.SysParam;

public interface SysParamService {

	List<SysParam> selectParamList();

	int deleteByPrimaryKey(Integer id);

	int insert(SysParam record);

	SysParam selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(SysParam record);
}
