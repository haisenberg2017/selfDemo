package com.haisenberg.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 树的数据模型
 */
public class TreeVo {
	private int id;
	private String name;// 名称
	private String iconCls;// 图标
	private String linkUrl;// 链接地址
	private int tLevel;// 层级
	private List<TreeVo> children;// 孩子节点集合


    public TreeVo() {  
        this.children = new ArrayList<TreeVo>();  
    }  
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public int gettLevel() {
		return tLevel;
	}

	public void settLevel(int tLevel) {
		this.tLevel = tLevel;
	}

	public List<TreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}

}
