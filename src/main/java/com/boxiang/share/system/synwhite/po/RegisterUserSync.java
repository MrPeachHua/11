package com.boxiang.share.system.synwhite.po;

/**
 * 白名单同步接口实体
 * 
 * @author lizheng
 *
 */
public class RegisterUserSync {

	public RegisterUserSync() {
	}

	public RegisterUserSync(String key, String parkCode, String datas) {

		this.key = key;
		this.parkCode = parkCode;
		this.datas = datas;
	}

	/**
	 * 重写toString
	 */
	@Override
	public String toString() {
		return "RegisterUserSync [key=" + key + ", parkCode=" + parkCode + ", datas=" + datas + "]";
	}

	/**
	 * 管理账户apikey 注册，激活账户后获得
	 */
	private String key;

	/**
	 * 停车场编号停车场编号
	 */
	private String parkCode;

	/**
	 * 需要同步的信息(加密) "datas"：[{第一条记录}，{第二}.......]
	 */
	private String datas;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}

}
