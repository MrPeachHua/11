package com.boxiang.share.product.ccic.service.impl;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.ccic.bo.CarMessageSync;
import com.boxiang.share.product.ccic.bo.CarResponse;
import com.boxiang.share.product.ccic.bo.Insure;
import com.boxiang.share.product.ccic.bo.OrderInfo;
import com.boxiang.share.product.ccic.dao.CcicOrderInfoDao;
import com.boxiang.share.product.ccic.po.CcicInsure;
import com.boxiang.share.product.ccic.po.CcicOrderInfo;
import com.boxiang.share.product.ccic.service.CcicInsureService;
import com.boxiang.share.product.ccic.service.CcicOrderInfoService;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.xml.XmlStreamFactory;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@DataSource(DataSourceEnum.MASTER)
@Service("ccicOrderInfo")
public class CcicOrderInfoServiceImpl implements CcicOrderInfoService {
	private static final Logger logger = LoggerFactory.getLogger(CcicOrderInfoServiceImpl.class);
    @Resource(name = "ccicOrderInfoDao")
    private CcicOrderInfoDao ccicOrderInfoDao;

    @Resource
    private CcicInsureService insureService;

    @Resource
    private OrderMainService orderMainService;

    @Override
    public List<CcicOrderInfo> selectList(CcicOrderInfo ccicOrderInfo) {
        return ccicOrderInfoDao.selectList(ccicOrderInfo);
    }

    @Override
    public Page<CcicOrderInfo> queryListPage(Page<CcicOrderInfo> page) {
        page.setResultList(ccicOrderInfoDao.queryListPage(page));
        return page;
    }

    @Override
    public CcicOrderInfo queryById(Integer id) {
        return ccicOrderInfoDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CcicOrderInfo ccicOrderInfo) {
        ccicOrderInfoDao.insert(ccicOrderInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        ccicOrderInfoDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CcicOrderInfo ccicOrderInfo) {
        ccicOrderInfoDao.update(ccicOrderInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CcicOrderInfo> ccicOrderInfoList) {
        ccicOrderInfoDao.batchUpdate(ccicOrderInfoList);
    }

    @Override
    public Map getOrderInfo(Map map) {
        Map request = (Map) map.get("request");
        Object content = request.get("content");
        if (content instanceof List) {
            return (Map) ((List) content).get(0);
        } else {
            return (Map) ((Map) content).get("orderInfo");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String saveCheck(Map map) {
        Map orderInfo = getOrderInfo(map);
        String utmsn = MapUtils.getString(orderInfo, "utmsn");
        String insuranceApplicantNo = MapUtils.getString(orderInfo, "insuranceApplicantNo");
        if (orderMainService.queryById(utmsn) == null) {
            return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                    "<isSuccess>F</isSuccess>" +
                    "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                    "<errorCode>2000</errorCode>" +
                    "<errorReason>合作方无此订单</errorReason>" +
                    "</response>";
        }
        CcicOrderInfo entity = new CcicOrderInfo();
        entity.setInsuranceApplicantNo(insuranceApplicantNo);
        List<CcicOrderInfo> list = ccicOrderInfoDao.selectList(entity);
        if (list != null && list.size() > 0) {
            entity = list.get(0);
            if (StringUtils.isNotEmpty(entity.getPayTime())) {
                return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                        "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                        "<isSuccess>F</isSuccess>" +
                        "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                        "<errorCode>2000</errorCode>" +
                        "<errorReason>已存在该订单,并且已经支付</errorReason>" +
                        "</response>";
            } else {
                ccicOrderInfoDao.delete(entity.getId());
                insureService.deleteByInsuranceApplicantNo(insuranceApplicantNo);
            }
        }
        CcicOrderInfo ccicOrderInfo = new CcicOrderInfo();
        ccicOrderInfo.setChanceCreateTime(MapUtils.getString(orderInfo, "chanceCreateTime"));
        ccicOrderInfo.setStartDate(MapUtils.getString(orderInfo, "startDate"));
        ccicOrderInfo.setInsuranceApplicantNo(insuranceApplicantNo);
        ccicOrderInfo.setPremium(MapUtils.getString(orderInfo, "premium"));
        ccicOrderInfo.setInsuranceType(MapUtils.getString(orderInfo, "insuranceType"));
        ccicOrderInfo.setUtmsn(utmsn);
        Map commercial = (Map) orderInfo.get("commercial");
        ccicOrderInfo.setCommercialInsurePremium(MapUtils.getString(commercial, "insurePremium"));
        Map compulsory = (Map) orderInfo.get("compulsory");
        ccicOrderInfo.setCompulsoryInsurePremium(MapUtils.getString(compulsory, "insurePremium"));
        ccicOrderInfo.setCompulsoryTravelTax(MapUtils.getString(compulsory, "travelTax"));
        Map vehicleInfo = (Map) orderInfo.get("vehicleInfo");
        ccicOrderInfo.setVin(MapUtils.getString(vehicleInfo, "VIN"));
        ccicOrderInfo.setLicenseNo(MapUtils.getString(vehicleInfo, "licenseNo"));
        ccicOrderInfo.setCarCity(MapUtils.getString(vehicleInfo, "carCity"));
        ccicOrderInfo.setCarProvince(MapUtils.getString(vehicleInfo, "carProvince"));
        ccicOrderInfo.setCarType(MapUtils.getString(vehicleInfo, "carType"));
        ccicOrderInfo.setEngineNo(MapUtils.getString(vehicleInfo, "engineNo"));
        ccicOrderInfo.setDisplacement(MapUtils.getString(vehicleInfo, "displacement"));
        ccicOrderInfo.setFirstRegisterDate(MapUtils.getString(vehicleInfo, "firstRegisterDate"));
        ccicOrderInfo.setPurchasePrice(MapUtils.getString(vehicleInfo, "purchasePrice"));
        ccicOrderInfo.setSeatNumber(MapUtils.getString(vehicleInfo, "seatNumber"));
        ccicOrderInfo.setTransferFlag(MapUtils.getString(vehicleInfo, "transferFlag"));
        ccicOrderInfo.setTransferDate(MapUtils.getString(vehicleInfo, "transferDate"));
        ccicOrderInfo.setDriverName(MapUtils.getString(vehicleInfo, "driverName"));
        ccicOrderInfo.setPaymentNo(MapUtils.getString(orderInfo, "paymentNo"));
        ccicOrderInfo.setPaymentTime(MapUtils.getString(orderInfo, "paymentTime"));
        ccicOrderInfo.setCreateor("admin");
        ccicOrderInfo.setCreateDate(new Date());
        ccicOrderInfoDao.insert(ccicOrderInfo);
        List<Map> insuranceList = (List<Map>) commercial.get("insuranceList");
        for (Map insure : insuranceList) {
            CcicInsure ccicInsure = new CcicInsure();
            ccicInsure.setInsuranceApplicantNo(ccicOrderInfo.getInsuranceApplicantNo());
            ccicInsure.setInsureCode(MapUtils.getString(insure, "insureCode"));
            ccicInsure.setInsureName(MapUtils.getString(insure, "insureName"));
            ccicInsure.setInsureAmount(MapUtils.getString(insure, "insureAmount"));
            ccicInsure.setInsurePremium(MapUtils.getString(insure, "insurePremium"));
            ccicInsure.setInsureFlag(MapUtils.getString(insure, "insureFlag"));
            ccicInsure.setCreateor("admin");
            ccicInsure.setCreateDate(new Date());
            insureService.add(ccicInsure);
        }
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                "<isSuccess>T</isSuccess>" +
                "<insuranceApplicantNo>" + ccicOrderInfo.getInsuranceApplicantNo() + "</insuranceApplicantNo>" +
                "<errorCode></errorCode>" +
                "<errorReason></errorReason>" +
                "</response>";
    }
    @Override
    public String savePay(Map map) {
        Map orderInfo = getOrderInfo(map);
        String insuranceApplicantNo = MapUtils.getString(orderInfo, "insuranceApplicantNo");
        String utmsn = MapUtils.getString(orderInfo, "utmsn");
        CcicOrderInfo ccicOrderInfo = new CcicOrderInfo();
        ccicOrderInfo.setInsuranceApplicantNo(insuranceApplicantNo);
        List<CcicOrderInfo> list = ccicOrderInfoDao.selectList(ccicOrderInfo);
        if (list == null || list.size() == 0) {
            return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                    "<isSuccess>F</isSuccess>" +
                    "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                    "<errorCode>2000</errorCode>" +
                    "<errorReason>合作方无此投保单号</errorReason>" +
                    "</response>";
        }
        if (orderMainService.queryById(utmsn) == null) {
            return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                    "<isSuccess>F</isSuccess>" +
                    "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                    "<errorCode>2000</errorCode>" +
                    "<errorReason>合作方无此订单号</errorReason>" +
                    "</response>";
        }
        ccicOrderInfo = list.get(0);
        ccicOrderInfo.setPaymentNo(MapUtils.getString(orderInfo, "paymentNo"));
        ccicOrderInfo.setPaymentTime(MapUtils.getString(orderInfo, "paymentTime"));
        ccicOrderInfo.setPayTime(MapUtils.getString(orderInfo, "payTime"));
        ccicOrderInfo.setPayType(MapUtils.getString(orderInfo, "payType"));
        ccicOrderInfo.setUtmsn(utmsn);
        ccicOrderInfo.setModified("admin");
        ccicOrderInfo.setModifyDate(new Date());
        ccicOrderInfoDao.update(ccicOrderInfo);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                "<isSuccess>T</isSuccess>" +
                "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                "<errorCode></errorCode>" +
                "<errorReason></errorReason>" +
                "</response>";
    }

    @Override
    public String savePolicy(Map map) {
        Map orderInfo = getOrderInfo(map);
        String insuranceApplicantNo = MapUtils.getString(orderInfo, "insuranceApplicantNo");
        String utmsn = MapUtils.getString(orderInfo, "utmsn");
        CcicOrderInfo ccicOrderInfo = new CcicOrderInfo();
        ccicOrderInfo.setInsuranceApplicantNo(insuranceApplicantNo);
        List<CcicOrderInfo> list = ccicOrderInfoDao.selectList(ccicOrderInfo);
        if (list == null || list.size() == 0) {
            return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                    "<isSuccess>F</isSuccess>" +
                    "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                    "<errorCode>2000</errorCode>" +
                    "<errorReason>合作方无此投保单号</errorReason>" +
                    "</response>";
        }
        if (orderMainService.queryById(utmsn) == null) {
            return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                    "<isSuccess>F</isSuccess>" +
                    "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                    "<errorCode>2000</errorCode>" +
                    "<errorReason>合作方无此订单号</errorReason>" +
                    "</response>";
        }
        ccicOrderInfo = list.get(0);
        ccicOrderInfo.setPolicyNo(MapUtils.getString(orderInfo, "policyNo"));
        ccicOrderInfo.setEffectDate(MapUtils.getString(orderInfo, "effectdate"));
        ccicOrderInfo.setModified("admin");
        ccicOrderInfo.setModifyDate(new Date());
        ccicOrderInfoDao.update(ccicOrderInfo);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                "<isSuccess>T</isSuccess>" +
                "<insuranceApplicantNo>" + insuranceApplicantNo + "</insuranceApplicantNo>" +
                "<errorCode></errorCode>" +
                "<errorReason></errorReason>" +
                "</response>";
    }
    



    private void initOrderInfo(CcicOrderInfo ccicOrderInfo,OrderInfo orderInfo){
        if(orderInfo.getChanceCreateTime()!=null){
        	ccicOrderInfo.setChanceCreateTime(DateUtil.date2str(orderInfo.getChanceCreateTime(), DateUtil.DATE_FORMAT));
        }
        if(orderInfo.getStartDate()!=null){
        	ccicOrderInfo.setStartDate(DateUtil.date2str(orderInfo.getStartDate(), DateUtil.DATE_FORMAT));
        }
        if(orderInfo.getInsuranceApplicantNo()!=null){
	        ccicOrderInfo.setInsuranceApplicantNo(orderInfo.getInsuranceApplicantNo());
        }
		if (orderInfo.getPremium() != null) {
			ccicOrderInfo.setPremium(orderInfo.getPremium());
		}
		if (orderInfo.getPolicyNo() != null) {
			ccicOrderInfo.setPolicyNo(orderInfo.getPolicyNo());
		}
		if (orderInfo.getEffectdate() != null) {
			ccicOrderInfo.setEffectDate(DateUtil.date2str(orderInfo.getEffectdate(), DateUtil.DATETIME_FORMAT));
		}
		if (orderInfo.getInsuranceType() != null) {
			ccicOrderInfo.setInsuranceType(orderInfo.getInsuranceType());
		}
		if (orderInfo.getUtmsn() != null) {
			ccicOrderInfo.setUtmsn(orderInfo.getUtmsn());
		}
		
		//商业险信息
		if (orderInfo.getCommercial()!=null && orderInfo.getCommercial().getInsurePremium() != null) {
			ccicOrderInfo.setCommercialInsurePremium(orderInfo.getCommercial().getInsurePremium());
		}
		
		//交强险信息
		if(orderInfo.getCompulsory()!=null){
			if (orderInfo.getCompulsory().getInsurePremium() != null) {
				ccicOrderInfo.setCompulsoryInsurePremium(orderInfo.getCompulsory().getInsurePremium());
			}
			if (orderInfo.getCompulsory().getTravelTax() != null) {
				ccicOrderInfo.setCompulsoryTravelTax(orderInfo.getCompulsory().getTravelTax());
			}
		}
		
		//车辆信息
		if(orderInfo.getVehicleInfo()!=null){
			if (orderInfo.getVehicleInfo().getVin() != null) {
				ccicOrderInfo.setVin(orderInfo.getVehicleInfo().getVin());
			}
			if (orderInfo.getVehicleInfo().getLicenseNo() != null) {
				ccicOrderInfo.setLicenseNo(orderInfo.getVehicleInfo().getLicenseNo());
			}
			if (orderInfo.getVehicleInfo().getCarCity() != null) {
				ccicOrderInfo.setCarCity(orderInfo.getVehicleInfo().getCarCity());
			}
			if (orderInfo.getVehicleInfo().getCarProvince() != null) {
				ccicOrderInfo.setCarProvince(orderInfo.getVehicleInfo().getCarProvince());
			}
			if (orderInfo.getVehicleInfo().getCarType() != null) {
				ccicOrderInfo.setCarType(orderInfo.getVehicleInfo().getCarType());
			}
			if (orderInfo.getVehicleInfo().getEngineNo() != null) {
				ccicOrderInfo.setEngineNo(orderInfo.getVehicleInfo().getEngineNo());
			}
			if (orderInfo.getVehicleInfo().getDisplacement() != null) {
				ccicOrderInfo.setDisplacement(orderInfo.getVehicleInfo().getDisplacement());
			}
	        if(orderInfo.getVehicleInfo().getFirstRegisterDate()!=null){
	        	ccicOrderInfo.setFirstRegisterDate(DateUtil.date2str(orderInfo.getVehicleInfo().getFirstRegisterDate(), DateUtil.DATE_FORMAT));
	        }
	        if(orderInfo.getVehicleInfo().getSeatNumber()!=null){
		        ccicOrderInfo.setSeatNumber(orderInfo.getVehicleInfo().getSeatNumber());
	        }
			if (orderInfo.getVehicleInfo().getPurchasePrice() != null) {
				ccicOrderInfo.setPurchasePrice(orderInfo.getVehicleInfo().getPurchasePrice());
			}
			if (orderInfo.getVehicleInfo().getTransferFlag() != null) {
				ccicOrderInfo.setTransferFlag(orderInfo.getVehicleInfo().getTransferFlag());
			}
			if (orderInfo.getVehicleInfo().getTransferDate() != null) {
				ccicOrderInfo.setTransferDate(
						DateUtil.date2str(orderInfo.getVehicleInfo().getTransferDate(), DateUtil.DATE_FORMAT));
			}
			if (orderInfo.getVehicleInfo().getDriverName() != null) {
				ccicOrderInfo.setDriverName(orderInfo.getVehicleInfo().getDriverName());
			}
			if (orderInfo.getVehicleInfo().getMobile() != null) {
				ccicOrderInfo.setMobile(orderInfo.getVehicleInfo().getMobile());
			}
			if (orderInfo.getVehicleInfo().getEmail() != null) {
				ccicOrderInfo.setEmail(orderInfo.getVehicleInfo().getEmail());
			}
		}
		
		//投保人信息
		if(orderInfo.getApplicantInfo()!=null){
			if (orderInfo.getApplicantInfo().getApplicantName() != null) {
				ccicOrderInfo.setApplicantName(orderInfo.getApplicantInfo().getApplicantName());
			}
			if (orderInfo.getApplicantInfo().getApplicantIdentifyType() != null) {
				ccicOrderInfo.setApplicantIdentifyType(orderInfo.getApplicantInfo().getApplicantIdentifyType());
			}
			if (orderInfo.getApplicantInfo().getApplicantIdentifyNumber() != null) {
				ccicOrderInfo.setApplicantIdentifyNumber(orderInfo.getApplicantInfo().getApplicantIdentifyNumber());
			}
			if (orderInfo.getApplicantInfo().getApplicantMobile() != null) {
				ccicOrderInfo.setApplicantMobile(orderInfo.getApplicantInfo().getApplicantMobile());
			}
			if (orderInfo.getApplicantInfo().getApplicantEmail() != null) {
				ccicOrderInfo.setApplicantEmail(orderInfo.getApplicantInfo().getApplicantEmail());
			}
		}
		
		//被保人信息
		if(orderInfo.getInsuredInfo()!=null){
			if (orderInfo.getInsuredInfo().getInsuredName() != null) {
				ccicOrderInfo.setInsuredName(orderInfo.getInsuredInfo().getInsuredName());
			}
			if (orderInfo.getInsuredInfo().getInsuredIdentifyType() != null) {
				ccicOrderInfo.setInsuredIdentifyType(orderInfo.getInsuredInfo().getInsuredIdentifyType());
			}
			if (orderInfo.getInsuredInfo().getInsuredIdentifyNumber() != null) {
				ccicOrderInfo.setInsuredIdentifyNumber(orderInfo.getInsuredInfo().getInsuredIdentifyNumber());
			}
			if (orderInfo.getInsuredInfo().getInsuredMobile() != null) {
				ccicOrderInfo.setInsuredMobile(orderInfo.getInsuredInfo().getInsuredMobile());
			}
			if (orderInfo.getInsuredInfo().getInsuredEmail() != null) {
				ccicOrderInfo.setInsuredEmail(orderInfo.getInsuredInfo().getInsuredEmail());
			}
		}
		
		//配送信息
		if(orderInfo.getDistributionInfo()!=null){
			if (orderInfo.getDistributionInfo().getReceiverName() != null) {
				ccicOrderInfo.setReceiverName(orderInfo.getDistributionInfo().getReceiverName());
			}
			if (orderInfo.getDistributionInfo().getReceiverMoblie() != null) {
				ccicOrderInfo.setReceiverMobile(orderInfo.getDistributionInfo().getReceiverMoblie());
			}
			if (orderInfo.getDistributionInfo().getReceiverAddress() != null) {
				ccicOrderInfo.setReceiverAddress(orderInfo.getDistributionInfo().getReceiverAddress());
			}
		}
		
		if (orderInfo.getPaymentNo() != null) {
			ccicOrderInfo.setPaymentNo(orderInfo.getPaymentNo());
		}
		if (orderInfo.getPaymentTime() != null) {
			ccicOrderInfo.setPaymentTime(DateUtil.date2str(orderInfo.getPaymentTime(), DateUtil.DATETIME_FORMAT));
		}
		if (orderInfo.getPayTime() != null) {
			ccicOrderInfo.setPayTime(DateUtil.date2str(orderInfo.getPayTime(), DateUtil.DATETIME_FORMAT));
		}
		if (orderInfo.getPayOutTime() != null) {
			ccicOrderInfo.setPayOutTime(DateUtil.date2str(orderInfo.getPayOutTime(), DateUtil.DATETIME_FORMAT));
		}
		if (orderInfo.getPayUrl() != null) {
			ccicOrderInfo.setPayUrl(orderInfo.getPayUrl());
		}
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public CarResponse carUnderwriteSync(CarMessageSync msg) {
		CarResponse carResponse = new CarResponse();
    	OrderInfo orderInfo = msg.getRequest().getContent().getOrderInfo();
        String utmsn = orderInfo.getUtmsn();
        String insuranceApplicantNo = orderInfo.getInsuranceApplicantNo();
        //订单号不存在，直接返回
        if (orderMainService.queryById(utmsn) == null) {
        	logger.warn("**********************合作方无此订单**********************"+utmsn);
			carResponse.setFinishTime(new Date());
			carResponse.setIsSuccess("F");
			carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
			carResponse.setErrorCode("2000");
			carResponse.setErrorReason("合作方无此订单");
			return carResponse;
        }
        
        //重复被调（1.支付未同步，删除旧数据重新录入。2.支付已同步，返回错误信息，说明已存在支付的核保信息）
        CcicOrderInfo entity = new CcicOrderInfo();
        entity.setInsuranceApplicantNo(insuranceApplicantNo);
        entity.setUtmsn(utmsn);
        List<CcicOrderInfo> list = ccicOrderInfoDao.selectList(entity);
        if (list != null && list.size() > 0) {
            entity = list.get(0);
            if (StringUtils.isNotEmpty(entity.getPayTime())) {
            	logger.warn("**********************已存在该订单,并且已经支付**********************"+utmsn);
    			carResponse.setFinishTime(new Date());
    			carResponse.setIsSuccess("F");
    			carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
    			carResponse.setErrorCode("2000");
    			carResponse.setErrorReason("已存在该订单,并且已经支付");
    			return carResponse;
            } else {
                ccicOrderInfoDao.delete(entity.getId());
                insureService.deleteByInsuranceApplicantNo(insuranceApplicantNo);
            }
        }
        CcicOrderInfo ccicOrderInfo = new CcicOrderInfo();
        ccicOrderInfo.setCreateor("admin");
        ccicOrderInfo.setCreateDate(new Date());
        initOrderInfo(ccicOrderInfo,orderInfo);
        ccicOrderInfoDao.insert(ccicOrderInfo);
        List<Insure> insures = orderInfo.getCommercial().getInsuranceList();
        List<CcicInsure> ccicInsures = new ArrayList<>();
        for(Insure insure:insures){
            CcicInsure ccicInsure = new CcicInsure();
            ccicInsure.setInsuranceApplicantNo(insuranceApplicantNo);
            ccicInsure.setInsureCode(insure.getInsureCode());
            ccicInsure.setInsureName(insure.getInsureName());
            ccicInsure.setInsureAmount(insure.getInsureAmount());
            ccicInsure.setInsurePremium(insure.getInsurePremium());
            ccicInsure.setInsureFlag(insure.getInsureFlag());
            ccicInsure.setCreateor("admin");
            ccicInsure.setCreateDate(new Date());
            ccicInsures.add(ccicInsure);
        }
        insureService.batchAdd(ccicInsures);
		carResponse.setFinishTime(new Date());
		carResponse.setIsSuccess("T");
		carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
		carResponse.setErrorCode("");
		carResponse.setErrorReason("");
		return carResponse;
    }
    
    @Override
    public CarResponse carPaySync(CarMessageSync msg){
		CarResponse carResponse = new CarResponse();
    	OrderInfo orderInfo = msg.getRequest().getContent().getOrderInfo();
        String utmsn = orderInfo.getUtmsn();
        String insuranceApplicantNo = orderInfo.getInsuranceApplicantNo();        
        
        //订单号和投保单号都匹配才能修改
        CcicOrderInfo ccicOrderInfo = new CcicOrderInfo();
        ccicOrderInfo.setInsuranceApplicantNo(insuranceApplicantNo);
        ccicOrderInfo.setUtmsn(utmsn);
        List<CcicOrderInfo> list = ccicOrderInfoDao.selectList(ccicOrderInfo);
        if (list == null || list.size() == 0) {
        	logger.warn("**********************找不到匹配的投保单号和订单号**********************"+utmsn);
			carResponse.setFinishTime(new Date());
			carResponse.setIsSuccess("F");
			carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
			carResponse.setErrorCode("2000");
			carResponse.setErrorReason("找不到匹配的投保单号和订单号");
			return carResponse;
        }
        ccicOrderInfo = list.get(0);
        if(ccicOrderInfo.getPayTime()!=null){
        	logger.warn("**********************该投保单号和订单号已存在支付时间**********************"+utmsn);
			carResponse.setFinishTime(new Date());
			carResponse.setIsSuccess("F");
			carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
			carResponse.setErrorCode("2000");
			carResponse.setErrorReason("该投保单号和订单号已存在支付时间");
			return carResponse;
        }
        ccicOrderInfo.setPaymentNo(orderInfo.getPaymentNo());
        ccicOrderInfo.setPaymentTime(DateUtil.date2str(orderInfo.getPaymentTime(), DateUtil.DATETIME_FORMAT));
        ccicOrderInfo.setPayTime(DateUtil.date2str(orderInfo.getPayTime(), DateUtil.DATETIME_FORMAT));
        ccicOrderInfo.setPayType(orderInfo.getPayType());
        ccicOrderInfo.setUtmsn(utmsn);
        ccicOrderInfo.setModified("admin");
        ccicOrderInfo.setModifyDate(new Date());
        ccicOrderInfoDao.update(ccicOrderInfo);
		carResponse.setFinishTime(new Date());
		carResponse.setIsSuccess("T");
		carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
		carResponse.setErrorCode("");
		carResponse.setErrorReason("");
		return carResponse;
    }

    @Override
    public CarResponse carPolicySync(CarMessageSync msg) {
		CarResponse carResponse = new CarResponse();
    	OrderInfo orderInfo = msg.getRequest().getContent().getOrderInfo();
        String utmsn = orderInfo.getUtmsn();
        String insuranceApplicantNo = orderInfo.getInsuranceApplicantNo();   

        //订单号和投保单号都匹配才能修改
        CcicOrderInfo ccicOrderInfo = new CcicOrderInfo();
        ccicOrderInfo.setInsuranceApplicantNo(insuranceApplicantNo);
        ccicOrderInfo.setUtmsn(utmsn);
        List<CcicOrderInfo> list = ccicOrderInfoDao.selectList(ccicOrderInfo);
        if (list == null || list.size() == 0) {
        	logger.warn("**********************找不到匹配的投保单号和订单号**********************"+utmsn);
			carResponse.setFinishTime(new Date());
			carResponse.setIsSuccess("F");
			carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
			carResponse.setErrorCode("2000");
			carResponse.setErrorReason("找不到匹配的投保单号和订单号");
			return carResponse;
        }
        ccicOrderInfo = list.get(0);
        if(!StringUtils.isEmpty(ccicOrderInfo.getPolicyNo())){
        	logger.warn("**********************该投保单号和订单号已存在保单号**********************"+utmsn);
			carResponse.setFinishTime(new Date());
			carResponse.setIsSuccess("F");
			carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
			carResponse.setErrorCode("2000");
			carResponse.setErrorReason("该投保单号和订单号已存在保单号");
			return carResponse;
        }
        ccicOrderInfo.setModified("admin");
        ccicOrderInfo.setModifyDate(new Date());
        initOrderInfo(ccicOrderInfo,orderInfo);
        ccicOrderInfoDao.update(ccicOrderInfo);
        
        List<Insure> insures = orderInfo.getCommercial().getInsuranceList();
        List<CcicInsure> ccicInsures = new ArrayList<>();
        for(Insure insure:insures){
            CcicInsure ccicInsure = new CcicInsure();
            ccicInsure.setInsuranceApplicantNo(insuranceApplicantNo);
            ccicInsure.setInsureCode(insure.getInsureCode());
            List<CcicInsure> tmp = insureService.selectList(ccicInsure);
            if(tmp!=null &&tmp.size()>0){
            	ccicInsure = tmp.get(0);
                ccicInsure.setInsureName(insure.getInsureName());
                ccicInsure.setInsureAmount(insure.getInsureAmount());
                ccicInsure.setInsurePremium(insure.getInsurePremium());
                ccicInsure.setInsureFlag(insure.getInsureFlag());
                ccicInsure.setModified("admin");
                ccicInsure.setModifyDate(new Date());
                ccicInsures.add(ccicInsure);
            }else {
                ccicInsure.setInsureName(insure.getInsureName());
                ccicInsure.setInsureAmount(insure.getInsureAmount());
                ccicInsure.setInsurePremium(insure.getInsurePremium());
                ccicInsure.setInsureFlag(insure.getInsureFlag());
                ccicInsure.setCreateor("admin");
                ccicInsure.setCreateDate(new Date());
                insureService.add(ccicInsure);
            }
        }
        insureService.batchUpdate(ccicInsures);
        
		carResponse.setFinishTime(new Date());
		carResponse.setIsSuccess("T");
		carResponse.setInsuranceApplicantNo(insuranceApplicantNo);
		carResponse.setErrorCode("");
		carResponse.setErrorReason("");
		return carResponse;
    }
}