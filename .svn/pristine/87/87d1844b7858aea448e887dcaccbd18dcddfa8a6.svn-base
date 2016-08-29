package com.boxiang.share.product.ccic.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.product.ccic.service.CcicOrderInfoService;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.utils.DateUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.ccic.bo.CarMessageSync;
import com.boxiang.share.product.ccic.bo.CarResponse;
import com.boxiang.share.product.ccic.bo.OrderInfo;
import com.boxiang.share.product.ccic.dao.CcicCustomerDao;
import com.boxiang.share.product.ccic.po.CcicCustomer;
import com.boxiang.share.product.ccic.service.CcicCustomerService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("ccicCustomer")
public class CcicCustomerServiceImpl implements CcicCustomerService {
    @Resource(name = "ccicCustomerDao")
    private CcicCustomerDao ccicCustomerDao;

    @Resource
    private OrderMainService orderMainService;

    @Resource
    private CcicOrderInfoService ccicOrderInfoService;

    @Override
    public List<CcicCustomer> selectList(CcicCustomer ccicCustomer) {
        return ccicCustomerDao.selectList(ccicCustomer);
    }

    @Override
    public Page<CcicCustomer> queryListPage(Page<CcicCustomer> page) {
        page.setResultList(ccicCustomerDao.queryListPage(page));
        return page;
    }

    @Override
    public CcicCustomer queryById(Integer id) {
        return ccicCustomerDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CcicCustomer ccicCustomer) {
        ccicCustomerDao.insert(ccicCustomer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        ccicCustomerDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CcicCustomer ccicCustomer) {
        ccicCustomerDao.update(ccicCustomer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CcicCustomer> ccicCustomerList) {
        ccicCustomerDao.batchUpdate(ccicCustomerList);
    }

    @Override
    public String saveInfo(Map map) {
        Map orderInfo = ccicOrderInfoService.getOrderInfo(map);
        String utmsn = MapUtils.getString(orderInfo, "utmsn");
        OrderMain orderMain = orderMainService.queryById(utmsn);
        if (orderMain == null) {
            return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                    "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                    "<isSuccess>F</isSuccess>" +
                    "<errorCode>2000</errorCode>" +
                    "<errorReason>无此订单</errorReason>" +
                    "</response>";
        }
        CcicCustomer query = new CcicCustomer();
        query.setUtmsn(utmsn);
        List<CcicCustomer> list = ccicCustomerDao.selectList(query);
        CcicCustomer entity;
        if (list != null && list.size() > 0) {
            entity = list.get(0);
        } else {
            entity = new CcicCustomer();
        }
        entity.setCustomerId(orderMain.getCustomerId());
        entity.setName(MapUtils.getString(orderInfo, "name"));
        entity.setMobile(MapUtils.getString(orderInfo, "mobile"));
        entity.setEmail(MapUtils.getString(orderInfo, "email"));
        entity.setCarLicence(MapUtils.getString(orderInfo, "carLicence"));
        entity.setCityCode(MapUtils.getString(orderInfo, "cityCode"));
        entity.setRegisterDate(MapUtils.getString(orderInfo, "registerDate"));
        entity.setBizBeginDate(MapUtils.getString(orderInfo, "bizBeginDate"));
        entity.setForBeginDate(MapUtils.getString(orderInfo, "forBeginDate"));
        entity.setUtmsn(utmsn);
        if (list != null && list.size() > 0) {
            entity.setModified("admin");
            entity.setModifyDate(new Date());
            ccicCustomerDao.update(entity);
        } else {
            entity.setCreateor("admin");
            entity.setCreateDate(new Date());
            ccicCustomerDao.insert(entity);
        }
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
                "<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
                "<isSuccess>T</isSuccess>" +
                "<errorCode></errorCode>" +
                "<errorReason></errorReason>" +
                "</response>";
    }
    @Override
    public CarResponse carInfoSync(CarMessageSync msg){
		CarResponse carResponse = new CarResponse();
    	OrderInfo orderInfo = msg.getRequest().getContent().getOrderInfo();
        String utmsn = orderInfo.getUtmsn();        
        
        CcicCustomer query = new CcicCustomer();
        query.setUtmsn(utmsn);
        List<CcicCustomer> list = ccicCustomerDao.selectList(query);
        CcicCustomer entity;
        if (list != null && list.size() > 0) {
            entity = list.get(0);
        } else {
            entity = new CcicCustomer();
        }
        OrderMain orderMain = orderMainService.queryById(utmsn);
        if(orderMain!=null){
        	entity.setCustomerId(orderMain.getCustomerId());
        }
        entity.setUtmsn(utmsn);
        entity.setName(orderInfo.getName());
        entity.setMobile(orderInfo.getMobile());
        entity.setEmail(orderInfo.getEmail());
        entity.setCarLicence(orderInfo.getCarLicence());
        entity.setCityCode(orderInfo.getCityCode());
        entity.setRegisterDate(orderInfo.getRegisterDate());
        if(orderInfo.getBizBeginDate()!=null){
        	entity.setBizBeginDate(DateUtil.date2str(orderInfo.getBizBeginDate(), DateUtil.DATE_FORMAT));
        }
        if(orderInfo.getForBeginDate()!=null){
        	entity.setForBeginDate(DateUtil.date2str(orderInfo.getForBeginDate(), DateUtil.DATE_FORMAT));
        }
        if (list != null && list.size() > 0) {
            entity.setModified("admin");
            entity.setModifyDate(new Date());
            ccicCustomerDao.update(entity);
        } else {
            entity.setCreateor("admin");
            entity.setCreateDate(new Date());
            ccicCustomerDao.insert(entity);
        }
		carResponse.setFinishTime(new Date());
		carResponse.setIsSuccess("T");
		carResponse.setErrorCode("");
		carResponse.setErrorReason("");
		return carResponse;
    }
}
