package com.boxiang.share.product.parking.po;

import java.io.Serializable;
import java.util.Date;

public class CarInOutRecordV2 implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ID主键,自增长 */
	private Integer id;

	/** 车类型 临时车/固定车 */
	private String carType;

	/** 置信度 */
	private String confidence;

	/** 优惠金额 单位分 */
	private Integer discountCharge;

	/** 优惠编码 */
	private String discountCode;

	/** 优惠时间 */
	private Integer discountTime;

	/** 是否修改标识 0:没有修改  1:有修改 */
	private String editFlag;

	/** 序列号 */
	private Integer inparkId;

	/** 入口道闸ID */
	private String inGateId;

	/** 入场抓拍照片名称 */
	private String inImageName;

	/** 入口LED灯Id */
	private String inLedId;

	/** 入场标志 1:入场 2:出场 */
	private String inOrOut;

	/** 入口读卡器ID */
	private String inReaderId;

	/** 入场时间 */
	private java.util.Date inTime;

	/** 入口摄像机ID */
	private String inVideoId;

	/** 发票代码 */
	private String invoiceCode;

	/** 交易订单ID,订单唯一标识 */
	private String orderId;

	/** 停车场编号 */
	private String parkId;

	/** 实时剩余车位数量 */
	private Integer parkSpaceNum;

	/** 应收停车费用 单位（分） 支付金额(以分为单位) */
	private Integer payCharge;

	/** 车辆颜色 */
	private String plateColor;

	/** 车牌号 */
	private String plateId;

	/** 车牌号关键字 */
	private String platek;

	/** 实付金额 实收金额(以分为单位) */
	private Integer realCharge;

	/** 记录的唯一标识ID */
	private String recordId;

	/** 记录上传时间 */
	private java.util.Date sysWriteDate;

	/** 车辆用户类型 */
	private String userType;

	/** 支付历史表中的id */
	private String outparkId;

	/** 入场时的通道名称 */
	private String inPassageway;

	/** 入场识别的车牌号 */
	private String inPlateId;

	/** 车主姓名 */
	private String name;

	/** 岗亭操作员姓名 */
	private String operator;

	/** 出场时的通道名称 */
	private String outPassageway;

	/** 出场识别的车牌号 */
	private String outPlateId;

	/** 出场时间 */
	private java.util.Date outTime;

	/** 支付类型(收费方式) 云收费,或是线下岗亭(现金...) */
	private String payType;

	/** 优惠编码 */
	private String profitNo;

	/** 优惠金额,单位分 */
	private Integer profitNum;

	/** 备注 */
	private String remark;

	/** 停留时间 */
	private String stayTime;

	/** 创建人 */
	private String createor;

	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

	private java.util.Date createDate;

	private String parkingName;

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	public String getCarType() {
		return this.carType;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	
	public String getConfidence() {
		return this.confidence;
	}

	public void setDiscountCharge(Integer discountCharge) {
		this.discountCharge = discountCharge;
	}
	
	public Integer getDiscountCharge() {
		return this.discountCharge;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
	public String getDiscountCode() {
		return this.discountCode;
	}

	public void setDiscountTime(Integer discountTime) {
		this.discountTime = discountTime;
	}
	
	public Integer getDiscountTime() {
		return this.discountTime;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	
	public String getEditFlag() {
		return this.editFlag;
	}

	public void setInparkId(Integer inparkId) {
		this.inparkId = inparkId;
	}
	
	public Integer getInparkId() {
		return this.inparkId;
	}

	public void setInGateId(String inGateId) {
		this.inGateId = inGateId;
	}
	
	public String getInGateId() {
		return this.inGateId;
	}

	public void setInImageName(String inImageName) {
		this.inImageName = inImageName;
	}
	
	public String getInImageName() {
		return this.inImageName;
	}

	public void setInLedId(String inLedId) {
		this.inLedId = inLedId;
	}
	
	public String getInLedId() {
		return this.inLedId;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}
	
	public String getInOrOut() {
		return this.inOrOut;
	}

	public void setInReaderId(String inReaderId) {
		this.inReaderId = inReaderId;
	}
	
	public String getInReaderId() {
		return this.inReaderId;
	}

	public void setInTime(java.util.Date inTime) {
		this.inTime = inTime;
	}
	
	public java.util.Date getInTime() {
		return this.inTime;
	}

	public void setInVideoId(String inVideoId) {
		this.inVideoId = inVideoId;
	}
	
	public String getInVideoId() {
		return this.inVideoId;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	
	public String getInvoiceCode() {
		return this.invoiceCode;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	
	public String getParkId() {
		return this.parkId;
	}

	public void setParkSpaceNum(Integer parkSpaceNum) {
		this.parkSpaceNum = parkSpaceNum;
	}
	
	public Integer getParkSpaceNum() {
		return this.parkSpaceNum;
	}

	public void setPayCharge(Integer payCharge) {
		this.payCharge = payCharge;
	}
	
	public Integer getPayCharge() {
		return this.payCharge;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	
	public String getPlateColor() {
		return this.plateColor;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}
	
	public String getPlateId() {
		return this.plateId;
	}

	public void setPlatek(String platek) {
		this.platek = platek;
	}
	
	public String getPlatek() {
		return this.platek;
	}

	public void setRealCharge(Integer realCharge) {
		this.realCharge = realCharge;
	}
	
	public Integer getRealCharge() {
		return this.realCharge;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	public String getRecordId() {
		return this.recordId;
	}

	public void setSysWriteDate(java.util.Date sysWriteDate) {
		this.sysWriteDate = sysWriteDate;
	}
	
	public java.util.Date getSysWriteDate() {
		return this.sysWriteDate;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserType() {
		return this.userType;
	}

	public void setOutparkId(String outparkId) {
		this.outparkId = outparkId;
	}
	
	public String getOutparkId() {
		return this.outparkId;
	}

	public void setInPassageway(String inPassageway) {
		this.inPassageway = inPassageway;
	}
	
	public String getInPassageway() {
		return this.inPassageway;
	}

	public void setInPlateId(String inPlateId) {
		this.inPlateId = inPlateId;
	}
	
	public String getInPlateId() {
		return this.inPlateId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getOperator() {
		return this.operator;
	}

	public void setOutPassageway(String outPassageway) {
		this.outPassageway = outPassageway;
	}
	
	public String getOutPassageway() {
		return this.outPassageway;
	}

	public void setOutPlateId(String outPlateId) {
		this.outPlateId = outPlateId;
	}
	
	public String getOutPlateId() {
		return this.outPlateId;
	}

	public void setOutTime(java.util.Date outTime) {
		this.outTime = outTime;
	}
	
	public java.util.Date getOutTime() {
		return this.outTime;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getPayType() {
		return this.payType;
	}

	public void setProfitNo(String profitNo) {
		this.profitNo = profitNo;
	}
	
	public String getProfitNo() {
		return this.profitNo;
	}

	public void setProfitNum(Integer profitNum) {
		this.profitNum = profitNum;
	}
	
	public Integer getProfitNum() {
		return this.profitNum;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setStayTime(String stayTime) {
		this.stayTime = stayTime;
	}
	
	public String getStayTime() {
		return this.stayTime;
	}

	public void setCreateor(String createor) {
		this.createor = createor;
	}
	
	public String getCreateor() {
		return this.createor;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
	public String getModified() {
		return this.modified;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

}