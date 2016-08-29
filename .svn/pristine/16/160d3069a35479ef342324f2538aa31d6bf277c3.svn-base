package com.boxiang.share.product.customer.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.customer.po.CustomerInfo;
import com.boxiang.share.product.customer.po.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerDao extends IMybatisBaseDao<Customer> {
    
    void batchUpdate(List<Customer> tCustomerList);
    Customer queryByCustomerId(String id);
    List<Customer> queryByC(Page<Customer> page);
    List<Customer> exportByC(Customer customer);
    Customer queryByWxId(Customer customer);
    List<Customer> queryByMobile(String customerMobile);
    List<Customer> selectByChannel(Page<Customer> page);
    List<CustomerInfo> exportCustomerInfo(CustomerInfo customerInfo);
    void addMoney(Customer customer);
    int subtractMoney(Map<String, Object> map);
    int updateRedeemCode(Customer customer);
    List<Customer> queryByNow(Map<String, Object> map);
    List<Customer> queryByNow2(Map<String, Object> map);
    List<Customer> customerWithInParkRule(Map<String, Object> map);
    List<Customer> customerByRester(Map<String, Object> map);

    List<Customer> customerWithParkingStatusPush(Map<String, Object> params);

    List<Customer> customerWithParkingStatusPushForCommonCustomer(Map<String, Object> params);

    List<Customer> customerWithRegisterRule(Map<String, Object> params);

    List<Customer> customerWithNotMonProRegisterRule(Map<String, Object> params);

    void updateLastLoginTime(@Param("customerId") String customerId, @Param("lastLoginTime") Date lastLoginTime);
    void updateLastLoginTime2(@Param("customerId") String customerId, @Param("lastLoginTime") Date lastLoginTime, @Param("lastLoginMachine") String lastLoginMachine, @Param("appVersion") String appVersion, @Param("lastLoginSys") String lastLoginSys);
    List<Map<String,Object>> customerWithOrderPush(Map<String, Object> params);

    List<Map<String,Object>> monthlyPropertyExpireBefore(Map<String, Object> params);

    List<Map<String,Object>> monthlyPropertyExpireAfter(Map<String, Object> params);

    List<Map<String,Object>> couponExpireAlert(Map<String, Object> params);
    List<Map<String,Object>> queryListPage2(Object page);
}