package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;
import java.util.Date;

import com.boxiang.share.utils.xml.DateXStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * 车辆信息
 * @author junior
 *
 */
public class VehicleInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 车辆VIN码  */
	private String vin;

	/** 车牌号  */
	private String licenseNo;

	/** 车辆行驶城市  */
	private String carCity;

	/** 车辆行驶省份  */
	private String carProvince;

	/** 车辆型号  */
	private String carType;

	/** 发动机号  */
	private String engineNo;

	/** 排量  */
	private String displacement;

	/** 初登日期  */
	@XStreamConverter(value=DateXStreamConverter.class)
	private Date firstRegisterDate;

	/** 新车购置价  */
	private String seatNumber;

	/** 座位数  */
	private String purchasePrice;

	/** 是否为过户车标志  */
	private String transferFlag;

	/** 过户日期  */
	@XStreamConverter(value=DateXStreamConverter.class)
	private Date transferDate;

	/** 车主姓名  */
	private String driverName;

	/** 联系人电话  */
	private String mobile;

	/** 联系人邮箱  */
	private String email;
	
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getCarCity() {
		return carCity;
	}

	public void setCarCity(String carCity) {
		this.carCity = carCity;
	}

	public String getCarProvince() {
		return carProvince;
	}

	public void setCarProvince(String carProvince) {
		this.carProvince = carProvince;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public Date getFirstRegisterDate() {
		return firstRegisterDate;
	}

	public void setFirstRegisterDate(Date firstRegisterDate) {
		this.firstRegisterDate = firstRegisterDate;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getTransferFlag() {
		return transferFlag;
	}

	public void setTransferFlag(String transferFlag) {
		this.transferFlag = transferFlag;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
