package com.boxiang.share.user.po;

import java.io.Serializable;

public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 菜单编号  */
	private java.lang.String menuCode;

	/** 菜单名称  */
	private java.lang.String menuName;

	/** 菜单图标  */
	private java.lang.String icon;

	/** 层级 */
	private java.lang.String levels;

	/** 地址 */
	private java.lang.String urls;

	/** 排序 */
	private java.lang.String sort;

	/** 备注 */
	private java.lang.String memo;

	/** 父节点 */
	private java.lang.String parentCode;

	/** 是否节点 */
	private java.lang.String isLeaf;

	/** 是否可用 */
	private java.lang.String isUsed;

	/** 角色权限  */
	private java.lang.String rolePower;

	private MenuRole menuRole;

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setMenuCode(java.lang.String menuCode) {
		this.menuCode = menuCode;
	}
	
	public java.lang.String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuName(java.lang.String menuName) {
		this.menuName = menuName;
	}
	
	public java.lang.String getMenuName() {
		return this.menuName;
	}

	public void setIcon(java.lang.String icon) {
		this.icon = icon;
	}
	
	public java.lang.String getIcon() {
		return this.icon;
	}

	public void setLevels(java.lang.String levels) {
		this.levels = levels;
	}
	
	public java.lang.String getLevels() {
		return this.levels;
	}

	public void setUrls(java.lang.String urls) {
		this.urls = urls;
	}
	
	public java.lang.String getUrls() {
		return this.urls;
	}

	public void setSort(java.lang.String sort) {
		this.sort = sort;
	}
	
	public java.lang.String getSort() {
		return this.sort;
	}

	public void setMemo(java.lang.String memo) {
		this.memo = memo;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}

	public void setParentCode(java.lang.String parentCode) {
		this.parentCode = parentCode;
	}
	
	public java.lang.String getParentCode() {
		return this.parentCode;
	}

	public void setIsLeaf(java.lang.String isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public java.lang.String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsUsed(java.lang.String isUsed) {
		this.isUsed = isUsed;
	}
	
	public java.lang.String getIsUsed() {
		return this.isUsed;
	}

	public void setRolePower(java.lang.String rolePower) {
		this.rolePower = rolePower;
	}
	
	public java.lang.String getRolePower() {
		return this.rolePower;
	}

	public MenuRole getMenuRole() {
		return menuRole;
	}

	public void setMenuRole(MenuRole menuRole) {
		this.menuRole = menuRole;
	}
}