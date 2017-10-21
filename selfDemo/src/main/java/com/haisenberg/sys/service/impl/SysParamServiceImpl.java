package com.haisenberg.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.haisenberg.page.PageInfo;
import com.haisenberg.sys.dao.SysParamMapper;
import com.haisenberg.sys.model.SysParam;
import com.haisenberg.sys.service.SysParamService;

@Service
@Transactional
public class SysParamServiceImpl implements SysParamService {
	@Resource
	private SysParamMapper sysParamMapper;

	@Override
	public List<SysParam> selectParamList() {
		// TODO Auto-generated method stub
		return sysParamMapper.selectParamList();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysParamMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysParam record) {
		// TODO Auto-generated method stub
		return sysParamMapper.insert(record);
	}

	@Override
	public SysParam selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysParamMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(SysParam record) {
		// TODO Auto-generated method stub
		return sysParamMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public PageInfo<SysParam> findAllByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysParam> list = sysParamMapper.selectParamList();
        return new PageInfo<>(list);
    }

}
