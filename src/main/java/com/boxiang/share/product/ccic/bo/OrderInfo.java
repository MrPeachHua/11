package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;
import java.util.Date;

import com.boxiang.share.utils.xml.DateTimeXStreamConverter;
import com.boxiang.share.utils.xml.DateXStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 商机进入时间  */
	@XStreamConverter(value=DateXStreamConverter.class)
	private Date chanceCreateTime;

	/** 起保日期  */
	@XStreamConverter(value=DateXStreamConverter.class)
	private Date startDate;

	/** 投保单号  */
	private String insuranceApplicantNo;

	/** 总保费  */
	private String premium;

	/** 保单号  */
	private String policyNo;

	/** 保单生效时间  */
	@XStreamConverter(value=DateTimeXStreamConverter.class)
	private Date effectdate;

	/** 投保单类型‘1’单交强险；‘2’单商业险；‘3’交强险+商业险  */
	private String insuranceType;

	/** 合作方订单号  */
	private String utmsn;

	/** 商业险信息  */
	private Commercial commercial;

	/** 交强险信息  */
	private Compulsory compulsory;

	/** 车辆信息  */
	private VehicleInfo vehicleInfo;

	/** 投保人信息 */
	private ApplicantInfo applicantInfo;

	/** 被保人信息  */
	private InsuredInfo insuredInfo;

	/** 配送信息  */
	private DistributionInfo distributionInfo;

	/** 支付申请号  */
	private String paymentNo;

	/** 支付申请时间  */
	@XStreamConverter(value=DateTimeXStreamConverter.class)
	private Date paymentTime;

	/** 支付时间  */
	@XStreamConverter(value=DateTimeXStreamConverter.class)
	private Date payTime;

	/** 支付类型  */
	private String payType;

	/** 订单关闭时间  */
	@XStreamConverter(value=DateTimeXStreamConverter.class)
	private Date payOutTime;

	/** 支付页面链接  */
	private String payUrl;

	/** 用户名称  */
	private String name;

	/** 手机号码  */
	private String mobile;

	/** 电子邮件地址  */
	private String email;

	/** 车牌号  */
	private String carLicence;

	/** 车辆行驶城市代码 六位 */
	private String cityCode;

	/** 车辆登记日期，到日或者到月  */
	private String registerDate;

	/** 商业险生效日期  */
	@XStreamConverter(value=DateXStreamConverter.class)
	private Date bizBeginDate;

	/** 交强险生效日期  */
	@XStreamConverter(value=DateXStreamConverter.class)
	private Date forBeginDate;

	public Date getChanceCreateTime() {
		return chanceCreateTime;
	}

	public void setChanceCreateTime(Date chanceCreateTime) {
		this.chanceCreateTime = chanceCreateTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getInsuranceApplicantNo() {
		return insuranceApplicantNo;
	}

	public void setInsuranceApplicantNo(String insuranceApplicantNo) {
		this.insuranceApplicantNo = insuranceApplicantNo;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getUtmsn() {
		return utmsn;
	}

	public void setUtmsn(String utmsn) {
		this.utmsn = utmsn;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Date getEffectdate() {
		return effectdate;
	}

	public void setEffectdate(Date effectdate) {
		this.effectdate = effectdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCarLicence() {
		return carLicence;
	}

	public void setCarLicence(String carLicence) {
		this.carLicence = carLicence;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public Date getBizBeginDate() {
		return bizBeginDate;
	}

	public void setBizBeginDate(Date bizBeginDate) {
		this.bizBeginDate = bizBeginDate;
	}

	public Date getForBeginDate() {
		return forBeginDate;
	}

	public void setForBeginDate(Date forBeginDate) {
		this.forBeginDate = forBeginDate;
	}

	public ApplicantInfo getApplicantInfo() {
		return applicantInfo;
	}

	public void setApplicantInfo(ApplicantInfo applicantInfo) {
		this.applicantInfo = applicantInfo;
	}

	public InsuredInfo getInsuredInfo() {
		return insuredInfo;
	}

	public void setInsuredInfo(InsuredInfo insuredInfo) {
		this.insuredInfo = insuredInfo;
	}

	public DistributionInfo getDistributionInfo() {
		return distributionInfo;
	}

	public void setDistributionInfo(DistributionInfo distributionInfo) {
		this.distributionInfo = distributionInfo;
	}

	public Date getPayOutTime() {
		return payOutTime;
	}

	public void setPayOutTime(Date payOutTime) {
		this.payOutTime = payOutTime;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	public Compulsory getCompulsory() {
		return compulsory;
	}

	public void setCompulsory(Compulsory compulsory) {
		this.compulsory = compulsory;
	}

	public VehicleInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	
}
