package com.boxiang.share.utils.synwhite;

/**
 * 定时器间隔值枚举
 * 
 * @author lizheng
 *
 */
public enum TriggerStateEnum {

	NONE		 ("0","不存在"), // 
	NORMAL			("1","正常"), // 
	PAUSED		("2","暂停"), // 
	COMPLETE		("3","完成"), // 
	ERROR	("4","错误"), // 
	BLOCKED		("5","阻塞"), // 
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
	private TriggerStateEnum(String id,String desc) {
		this.id = id;
		this.desc = desc;
	}


	// 根据传入的ID，返回对应的值
	public static String getTriggerStateDesc(String id) {

		if (TriggerStateEnum.NONE.getId().equals(id)) {
			return TriggerStateEnum.NONE.getDesc();
		}

		if (TriggerStateEnum.NORMAL.getId().equals(id)) {
			return TriggerStateEnum.NORMAL.getDesc();
		}

		if (TriggerStateEnum.PAUSED.getId().equals(id)) {
			return TriggerStateEnum.PAUSED.getDesc();
		}
		
		if (TriggerStateEnum.COMPLETE.getId().equals(id)) {
			return TriggerStateEnum.COMPLETE.getDesc();
		}
		
		if (TriggerStateEnum.ERROR.getId().equals(id)) {
			return TriggerStateEnum.ERROR.getDesc();
		}
		
		if (TriggerStateEnum.BLOCKED.getId().equals(id)) {
			return TriggerStateEnum.BLOCKED.getDesc();
		}

		return TriggerStateEnum.UNKNOWN.getDesc();
	}
	
	// 下面是枚举属性取值的两个方法
	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}
}
