package com.boxiang.share.product.parking.controller;

import java.util.regex.Pattern;

public class NumberOfRegular {
	
	/**
	 * 判断是否是正浮点数或正整数
	 * @param number
	 * @return
	 */
	public static boolean IsDouble(String number){
        String reg = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";
        return Pattern.compile(reg).matcher(number).find();
    }
	
	/**
	 * 是否是一位数字(0或1)
	 * @param number
	 * @return
	 */
	public static boolean IsOneNumber(String number){
        String reg = "^[0-1]$";
        return Pattern.compile(reg).matcher(number).find();
    }
	
	/**
	 * 验证输入身份证号
	 * @param number
	 * @return
	 */
	public static boolean IsIDcard(String number){
        String reg = "(^\\d{18}$)|(^\\d{15}$)|(^\\d{17}(\\d|X|x))";
        return Pattern.compile(reg).matcher(number).find();
    }
	
	/**
	 * 验证输入电话号码
	 * @param number
	 * @return
	 */
	public static boolean IsPhoneNumber(String number){
        String reg = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        return Pattern.compile(reg).matcher(number).find();
    }
	
	/**
	 * 校验经度
	 */
	public static boolean IsLatitude(String str){
        String reg = "^[-]?(\\d|([1-9]\\d)|(1[0-7]\\d)|(180))(\\.\\d*)?$";
        return Pattern.compile(reg).matcher(str).find();
    }
	
	/**
	 * 校验纬度
	 */
	public static boolean IsLongitude(String str){
        String reg = "^[-]?(\\d|([1-8]\\d)|(90))(\\.\\d*)?$";
        return Pattern.compile(reg).matcher(str).find();
    }
	
	
	/**
	 * 校验车牌号
	 */
	public static boolean IsCarNumber(String str){
        String reg = "^[\\u4E00-\\u9FA5][\\da-zA-Z]{6}$";
        return Pattern.compile(reg).matcher(str).find();
    }
}
