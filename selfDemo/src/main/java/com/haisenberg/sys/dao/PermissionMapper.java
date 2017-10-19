package com.haisenberg.sys.dao;

import com.haisenberg.sys.model.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKey(Permission record);
}