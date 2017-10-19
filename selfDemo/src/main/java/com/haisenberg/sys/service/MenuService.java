package com.haisenberg.sys.service;

import java.util.List;

import com.haisenberg.sys.model.Menu;
import com.haisenberg.vo.TreeVo;

public interface MenuService {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> selectById (int menuId);
    
    List<Menu> selectByPid (int menuPid);
    
    List<TreeVo> menuTree();
    
    String menuJson();
}