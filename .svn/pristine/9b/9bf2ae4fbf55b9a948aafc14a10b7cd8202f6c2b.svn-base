package com.boxiang.share.utils.synwhite;

/**
 * 订单状态 枚举
 * 
 * @author lizheng
 *
 */
public enum CarColorEnum {

	BLACK("1", "黑"), // 黑
	WHITE("2", "白"), // 白
	OTHER("3", "其他"), // 其他

	UNKNOWN("9", "未知"); // 未知

	// 不能修改枚举的值
	private final String id;// id
	private final String desc;// 描述

	/**
	 * 构造器私有化
	 * 
	 * @param Status
	 * @param StatusDesc
	 */
	private CarColorEnum(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	// 根据传入的描述，返回对应的ID
	public static String getCarColorId(String desc) {

		if (CarColorEnum.BLACK.getDesc().equals(desc.trim())) {
			return CarColorEnum.BLACK.getId();
		}

		if (CarColorEnum.WHITE.getDesc().equals(desc.trim())) {
			return CarColorEnum.WHITE.getId();
		}

		if (CarColorEnum.OTHER.getDesc().equals(desc.trim())) {
			return CarColorEnum.OTHER.getId();
		}

		return CarColorEnum.UNKNOWN.getId();
	}

	// 根据传入的ID，返回对应的描述
	public static String getCarColorDesc(String id) {

		if (CarColorEnum.BLACK.getId().equals(id.trim())) {
			return CarColorEnum.BLACK.getDesc();
		}

		if (CarColorEnum.WHITE.getId().equals(id.trim())) {
			return CarColorEnum.WHITE.getDesc();
		}

		if (CarColorEnum.OTHER.getId().equals(id.trim())) {
			return CarColorEnum.OTHER.getDesc();
		}

		return CarColorEnum.UNKNOWN.getDesc();
	}

	// 下面是枚举属性取值的两个方法
	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
}
