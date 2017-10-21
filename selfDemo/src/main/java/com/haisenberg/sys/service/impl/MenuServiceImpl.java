package com.haisenberg.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haisenberg.sys.dao.MenuMapper;
import com.haisenberg.sys.model.Menu;
import com.haisenberg.sys.service.MenuService;
import com.haisenberg.vo.TreeVo;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	final int maxTreeLevel=3;
	@Resource
	private MenuMapper menuMapper;
	private final int rootPid = 0;

	@Override
	public int deleteByPrimaryKey(Integer menuId) {
		return menuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int insert(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.insert(record);
	}


	@Override
	public Menu selectByPrimaryKey(Integer menuId) {
		// TODO Auto-generated method stub
		return menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.updateByPrimaryKey(record);
	}

	public List<Menu> selectById(int menuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuId", menuId);
		return menuMapper.selectByIdOrPid(map);
	}

	public List<Menu> selectByPid(int menuPid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuPid", menuPid);
		return menuMapper.selectByIdOrPid(map);
	}

	@Override
	public List<TreeVo> menuTree() {
		return covertToTree(rootPid);
	}
	
	@Override
	public String menuJson() {
		List<TreeVo> treeList = covertToTree(rootPid);
		String jsonStr = getChildrenJsonArr(treeList,null);
		return jsonStr;
	}

	private String getChildrenJsonArr(List<TreeVo> treeList,StringBuffer json) {
		StringBuffer jsonChildren=new StringBuffer();
		if (treeList != null && treeList.size() > 0) {
			jsonChildren.append("[");
			for (TreeVo treeVo : treeList) {
					jsonChildren.append("{");
				jsonChildren.append("\"id\":\""+treeVo.getId()+"\"");
				//jsonChildren.append(",\"text\":\""+treeVo.getName()+"\"");
				jsonChildren.append(",\"text\":\"<span class='menu-span'><ul class='menu-ul'><li>"+treeVo.getName()+"</li><li>"+treeVo.getLinkUrl()+"</li><li>"+treeVo.gettSeq()+"</li></ul></span>\"");
				if (treeVo.getChildren() != null && treeVo.getChildren().size() > 0) {
					jsonChildren.append(",\"nodes\":"+getChildrenJsonArr(treeVo.getChildren(),jsonChildren));
					jsonChildren.append(",\"hasChild\":true");
				}else{
					jsonChildren.append(",\"hasChild\":false");
				}
				jsonChildren.append(",\"tlevel\":\""+treeVo.gettLevel()+"\"");
				jsonChildren.append(",\"icon\":\""+treeVo.getIconCls()+"\"");
				if(treeVo.gettLevel()==3||StringUtils.isNotBlank(treeVo.getLinkUrl())){
					jsonChildren.append(",\"tags\":[\"<a onclick='nodeDel("+treeVo.getId()+");'>删除</a>\",\"<a onclick='nodeEdit("+treeVo.getId()+");'>修改</a>\"]");
				}else{
					jsonChildren.append(",\"tags\":[\"<a onclick='nodeDel("+treeVo.getId()+");'>删除</a>\",\"<a onclick='nodeEdit("+treeVo.getId()+");'>修改</a>\",\"<a onclick='nodeAdd(this);'>添加</a>\"]");
				}
				jsonChildren.append("},");
			}			
			if(jsonChildren.lastIndexOf(",")==jsonChildren.length()-1) {
				jsonChildren = jsonChildren.deleteCharAt(jsonChildren.length()-1);
			}
			jsonChildren.append("]");
		}
		return jsonChildren.toString();

	}
	
	private List<TreeVo> covertToTree(int pid){
		List<TreeVo> voList=new ArrayList<>();
		List<Menu> nodeList = selectByPid(pid);
		for (Menu menu : nodeList) {
			TreeVo vo=new TreeVo();
			vo.setId(menu.getMenuId());
			vo.setName(menu.getMenuName());
			vo.setIconCls(menu.getIconCls());
			vo.setLinkUrl(menu.getLinkUrl());
			vo.settLevel(menu.gettLevel());
			vo.settSeq(menu.getSeqId());
			List<TreeVo> covertToTree = covertToTree(menu.getMenuId());
			if(covertToTree!=null&&covertToTree.size()>0){
				vo.setChildren(covertToTree);	
			}
			voList.add(vo);
		}
		return voList;
	}
}