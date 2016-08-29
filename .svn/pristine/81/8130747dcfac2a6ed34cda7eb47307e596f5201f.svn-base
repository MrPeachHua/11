package com.boxiang.share.utils.synwhite;

/**
 * 订单状态 枚举
 * 
 * @author lizheng
 *
 */
public enum ErrorCodeEnum {

	ONE("100001", "Api key 验证失败"), // 
	TWO("100002", "参数有误"), // 
	THREE("100003", "传入参数格式错误为空值、格式错误"), // 
	FOUR("100004", "停车场编号不存在"), // 
	FIVE("100005", "车牌号为空"), // 
	SIX("100006", "图片解析失败"), // 
	SEVEN("100007", "停车场用户名不存在"), // 
	EIGHT("100008", "发送实时命令异常"), // 

	UNKNOWN("100009", "未知"); // 未知

	// 不能修改枚举的值
	private final String id;// id
	private final String desc;// 描述

	/**
	 * 构造器私有化
	 * 
	 * @param Status
	 * @param StatusDesc
	 */
	private ErrorCodeEnum(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}


	// 根据传入的ID，返回对应的描述
	public static String getErrorDesc(String id) {

		if (ErrorCodeEnum.ONE.getId().equals(id.trim())) {
			return ErrorCodeEnum.ONE.getDesc();
		}

		if (ErrorCodeEnum.TWO.getId().equals(id.trim())) {
			return ErrorCodeEnum.TWO.getDesc();
		}

		if (ErrorCodeEnum.THREE.getId().equals(id.trim())) {
			return ErrorCodeEnum.THREE.getDesc();
		}
		
		if (ErrorCodeEnum.FOUR.getId().equals(id.trim())) {
			return ErrorCodeEnum.FOUR.getDesc();
		}
		
		if (ErrorCodeEnum.FIVE.getId().equals(id.trim())) {
			return ErrorCodeEnum.FIVE.getDesc();
		}
		
		if (ErrorCodeEnum.SIX.getId().equals(id.trim())) {
			return ErrorCodeEnum.SIX.getDesc();
		}
		
		if (ErrorCodeEnum.SEVEN.getId().equals(id.trim())) {
			return ErrorCodeEnum.SEVEN.getDesc();
		}
		
		if (ErrorCodeEnum.EIGHT.getId().equals(id.trim())) {
			return ErrorCodeEnum.EIGHT.getDesc();
		}

		return ErrorCodeEnum.UNKNOWN.getDesc();
	}

	// 下面是枚举属性取值的两个方法
	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
}
