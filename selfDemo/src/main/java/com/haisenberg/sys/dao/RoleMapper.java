package com.haisenberg.sys.dao;

import com.haisenberg.sys.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKey(Role record);
}