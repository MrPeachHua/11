package com.boxiang.share.product.car.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 节点编号 */
	private String nodeCode;

	/** 节点名称 */
	private String nodeName;

	/** 图标 */
	private String icon = "";

	/** 层级 */
	private String levels;

	/** 排序 */
	private String sort;

	/** 父节点的编号 */
	private String parentCode;

	/** 是否节点 */
	private String isLeaf;

	/** 是否可用 */
	private String isUsed;

    /** 子节点 */
	List<CarModel> childrenList = new ArrayList<>();

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeCode() {
		return this.nodeCode;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getLevels() {
		return this.levels;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSort() {
		return this.sort;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getIsUsed() {
		return this.isUsed;
	}

    public List<CarModel> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CarModel> childrenList) {
        this.childrenList = childrenList;
    }
}