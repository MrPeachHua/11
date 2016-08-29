package com.boxiang.share.system.synwhite.po;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import com.boxiang.share.product.order.po.Propertyparkinginfo;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.synwhite.CarColorEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 白名单同步接口实体datas部分的信息实体
 * 
 * @author lizheng
 *
 */
// @JsonIgnoreProperties(value = { "word" }) //表示序列化时忽略的属性
public class RegisterUserSyncData {

	public RegisterUserSyncData() {
	}

	// 2:包月车辆
	public RegisterUserSyncData(Monthlyparkinginfo m) {
		this.typeDesc = "月租车辆";
		this.villageId = m.getVillageId();
		this.parkCode = m.getParkCode();
		this.plateId = m.getCarNumber();
		this.type = "2";
		this.owner = m.getOwner();
		this.certificate = m.getCertificate();
		this.address = m.getAddress();
		this.phone = m.getPhone();
		this.carColor = CarColorEnum.getCarColorDesc(m.getCarColor() == null ? "1" : m.getCarColor().toString());
		//this.effectieTime = m.getValidityStartTime();
		//this.expiredTime = m.getValidityEndTime();
		Calendar cal = Calendar.getInstance();
		//cal.setTime(new Date());
		//cal.set(Calendar.DAY_OF_MONTH, 1);
		//this.effectieTime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		this.effectieTime = DateUtil.date2str(m.getEffect_begin_time(), DateUtil.DATE_FORMAT);
		this.expiredTime = DateUtil.date2str(m.getEffect_end_time(), DateUtil.DATE_FORMAT);
		//cal.roll(Calendar.DAY_OF_MONTH, -1);
		//this.expiredTime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		this.inOuts = new String[] {};
	}

	// 1:产权车辆
	public RegisterUserSyncData(Propertyparkinginfo p) {
		this.typeDesc = "产权车辆";
		this.villageId = p.getVillageId();
		this.parkCode = p.getParkCode();
		this.plateId = p.getCarNumber();
		this.type = "1";
		this.owner = p.getOwner();
		this.certificate = p.getCertificate();
		this.address = p.getAddress();
		this.phone = p.getPhone();
		this.carColor = CarColorEnum.getCarColorDesc(p.getCarColor() == null ? "1" : p.getCarColor().toString());
		//this.effectieTime = p.getValidityStartTime();
		//this.expiredTime = p.getValidityEndTime();
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		this.effectieTime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		this.expiredTime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());*/
		this.effectieTime = DateUtil.date2str(p.getEffect_begin_time(), DateUtil.DATE_FORMAT);
		this.expiredTime = DateUtil.date2str(p.getEffect_end_time(), DateUtil.DATE_FORMAT);
		
		this.inOuts = new String[] {};
	}

	// 1:特殊车辆(也是固定车辆)
	public RegisterUserSyncData(Specialparkinginfo s) {
		this.typeDesc = "特殊车辆";
		this.villageId = s.getVillageId();
		this.parkCode = s.getParkCode();
		this.plateId = s.getCarNumber();
		this.type = "1";
		this.owner = s.getOwner();
		this.certificate = s.getCertificate();
		this.address = s.getAddress();
		this.phone = s.getPhone();
		this.carColor = CarColorEnum.getCarColorDesc(s.getCarColor() == null ? "1" : s.getCarColor().toString());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		this.effectieTime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		this.expiredTime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		this.inOuts = new String[] {};
	}

	// 小区ID
	@JsonIgnore // 表示序列化时忽略的属性
	private String villageId;
	// 停车场编号
	@JsonIgnore // 表示序列化时忽略的属性
	private String parkCode;
	// 车位类型描述
	@JsonIgnore // 表示序列化时忽略的属性
	private String typeDesc;

	@Override
	public String toString() {
		return "RegisterUserSyncData [villageId=" + villageId + ", parkCode=" + parkCode + ", typeDesc=" + typeDesc
				+ ", plateId=" + plateId + ", effectieTime=" + effectieTime + ", expiredTime=" + expiredTime + "]";
	}

	/**
	 * 非空，车牌号码如：京B12345
	 */
	private String plateId;

	/**
	 * 非空，白名单类型1:固定车辆2:包月车辆
	 */
	private String type;

	/**
	 * 非空，车主姓名车主姓名
	 */
	private String owner;

	/**
	 * 非空，身份信息
	 */
	private String certificate;

	/**
	 * 非空，车主联系地址
	 */
	private String address;

	/**
	 * 非空，车主联系电话
	 */
	private String phone;

	/**
	 * 非空，车牌号码车辆颜色如‘白色’
	 */
	private String carColor;

	/**
	 * 生效时间yyyy-MM-dd 格式
	 */
	private String effectieTime;

	/**
	 * 过期时间yyyy-MM-dd 格式
	 */
	private String expiredTime;

	/**
	 * 微信openId 用于微信推送
	 */
	private String openId;

	/**
	 * 保留字段暂无实际意义传0
	 */
	private int state;

	/**
	 * 租/购车位名车位名称
	 */
	private String parkSpase;

	/**
	 * 账户余额（暂不支持） 账户余额
	 */
	private int balance;

	/**
	 * 车辆可通过的通道编号如："inOuts" : ["A1","A2","A3"]
	 */
	private String[] inOuts;

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getEffectieTime() {
		return effectieTime;
	}

	public void setEffectieTime(String effectieTime) {
		this.effectieTime = effectieTime;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getParkSpase() {
		return parkSpase;
	}

	public void setParkSpase(String parkSpase) {
		this.parkSpase = parkSpase;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String[] getInOuts() {
		return inOuts;
	}

	public void setInOuts(String[] inOuts) {
		this.inOuts = inOuts;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((plateId == null) ? 0 : plateId.hashCode());
		result = (prime * result) + ((villageId == null) ? 0 : villageId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RegisterUserSyncData other = (RegisterUserSyncData) obj;
		if (plateId == null) {
			if (other.plateId != null) {
				return false;
			}
		} else if (!plateId.equals(other.plateId)) {
			return false;
		}
		if (villageId == null) {
			if (other.villageId != null) {
				return false;
			}
		} else if (!villageId.equals(other.villageId)) {
			return false;
		}
		return true;
	}

}
