package com.boxiang.share.utils.synwhite;

/**
 * 定时器间隔值枚举
 * 
 * @author lizheng
 *
 */
public enum TimerValueEnum {

	FIVE		 ("5", "0 0/5 * * * ?","间隔5分钟"), // 
	TEN			("10", "0 0/10 * * * ?","间隔10分钟"), // 
	FIFTEEN		("15", "0 0/15 * * * ?","间隔15分钟"), // 
	TWENTY		("20", "0 0/20 * * * ?","间隔20分钟"), // 
	TWENTY_FIVE	("25", "0 0/25 * * * ?","间隔25分钟"), // 
	THIRTY		("30", "0 0/30 * * * ?","间隔30分钟"), // 
	OHOUR		("1", "0 0 */1 * * ?","间隔1小时"), // 
	

	UNKNOWN("100009","0 0/30 * * * ?", "未知"); // 未知

	// 不能修改枚举的值
	private final String id;// id
	private final String value;
	private final String desc;// 描述

	/**
	 * 构造器私有化
	 * 
	 * @param Status
	 * @param StatusDesc
	 */
	private TimerValueEnum(String id, String value,String desc) {
		this.id = id;
		this.value = value;
		this.desc = desc;
	}


	// 根据传入的ID，返回对应的值
	public static String getTimerValue(String id) {

		if (TimerValueEnum.FIVE.getId().equals(id)) {
			return TimerValueEnum.FIVE.getValue();
		}

		if (TimerValueEnum.TEN.getId().equals(id)) {
			return TimerValueEnum.TEN.getValue();
		}

		if (TimerValueEnum.FIFTEEN.getId().equals(id)) {
			return TimerValueEnum.FIFTEEN.getValue();
		}
		
		if (TimerValueEnum.TWENTY.getId().equals(id)) {
			return TimerValueEnum.TWENTY.getValue();
		}
		
		if (TimerValueEnum.FIVE.getId().equals(id)) {
			return TimerValueEnum.FIVE.getValue();
		}
		
		if (TimerValueEnum.TWENTY_FIVE.getId().equals(id)) {
			return TimerValueEnum.TWENTY_FIVE.getValue();
		}
		
		if (TimerValueEnum.THIRTY.getId().equals(id)) {
			return TimerValueEnum.THIRTY.getValue();
		}
		
		return TimerValueEnum.UNKNOWN.getValue();
	}
	
	
	
	// 根据传入的ID，返回对应的值
		public static String getTimerId(String value) {

			if (TimerValueEnum.FIVE.getValue().equals(value)) {
				return TimerValueEnum.FIVE.getId();
			}

			if (TimerValueEnum.TEN.getValue().equals(value)) {
				return TimerValueEnum.TEN.getId();
			}

			if (TimerValueEnum.FIFTEEN.getValue().equals(value)) {
				return TimerValueEnum.FIFTEEN.getId();
			}
			
			if (TimerValueEnum.TWENTY.getValue().equals(value)) {
				return TimerValueEnum.TWENTY.getId();
			}
			
			if (TimerValueEnum.FIVE.getValue().equals(value)) {
				return TimerValueEnum.FIVE.getId();
			}
			
			if (TimerValueEnum.TWENTY_FIVE.getValue().equals(value)) {
				return TimerValueEnum.TWENTY_FIVE.getId();
			}
			
			if (TimerValueEnum.THIRTY.getValue().equals(value)) {
				return TimerValueEnum.THIRTY.getId();
			}
			
			return TimerValueEnum.UNKNOWN.getId();
		}

	// 下面是枚举属性取值的两个方法
	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}


	public String getDesc() {
		return desc;
	}
}
