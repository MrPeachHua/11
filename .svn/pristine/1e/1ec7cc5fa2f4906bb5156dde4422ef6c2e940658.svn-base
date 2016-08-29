package com.boxiang.share.product.order.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.Cenuse;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.po.OrderMainExcel;
import org.apache.ibatis.annotations.Param;

public interface OrderMainDao extends IMybatisBaseDao<OrderMain> {
    
    void batchUpdate(List<OrderMain> orderMainList);
    OrderMain queryByIdAndType(OrderMain orderMain);
    int  finishPay(String orderId);
    List<OrderMain> queryListByPage(Page<OrderMain> page);
    List<OrderMain> queryListByTempPage(Page<OrderMain> page);
    //15分钟未付款
    List<OrderMain>  selectListByStatus();
    List<OrderMain> selectListExcel(OrderMain orderMain);
    /**
     * 查询历史订单
     * @param orderMain
     * @return
     */
    OrderMain selectOrderDetailT(OrderMain orderMain);
    OrderMain selectOrderDetailTS(OrderMain orderMain);
    OrderMain selectOrderDetailM(OrderMain orderMain);
    OrderMain selectOrderDetailE(OrderMain orderMain);
    //月租月销售
    OrderMain getMonthPaid(Map<String,Object> param);
    //产权月销售
    OrderMain getPropertyPaid(Map<String,Object> param);
    //月租月目标销售
    OrderMain getMonthPoint(Map<String,Object> param);
    //产权月目标销售
    OrderMain getPropertyPoint(Map<String,Object> param);
    //临停月销售
    OrderMain getTemPaid(Map<String,Object> param);
    List<OrderMain> queryListPageWithCarNumber(Page<OrderMain> page);
    List<OrderMain> selectListExcelWithCarNumber(OrderMain orderMain);
    //List<OrderMain> getOrderByParam(Map<String,String> param);
    List<OrderMain> getMonthlyOrderByParam(Map<String,String> param);
    List<OrderMain> getEquityOrderByParam(Map<String,String> param);
    List<OrderMain> getTempOrderByParam(Map<String,String> param);
    List<OrderMain> queryMonthlyEquityListPage(Object page);
    List<OrderMain> queryTempSharePage(Page<OrderMain> page);
    List<OrderMain> queryParkPage(Page<OrderMain> page);
    List<OrderMain> queryTempPage(Page<OrderMain> page);
    List<OrderMain> queryTempExcel(OrderMain orderMain);
    List<OrderMain> queryTempShareExcel(OrderMain orderMain);
    List<OrderMain> queryListPageForRecharge(Page<OrderMain> page);
    List<OrderMain> queryListPageForConsum(Page<OrderMain> page);
    List<OrderMain> rechargelist(Page<OrderMain> page);
    int updateUnpayToPaid(String orderId);
    int selectCount(OrderMain orderMain);
    List<OrderMainExcel> rechargeExcel(Map<String, Object> params);
    List<OrderMain> queryOrderStatus(Page<OrderMain> page);
    List<OrderMain> queryCarwashPage(Page<OrderMain> page);
    List<OrderMain> queryCarwasExport(OrderMain orderMain);
    List<OrderMainExcel> queryParkExport(Map<String, Object> params);
    List<OrderMain> queryStream(Page<OrderMain> page);
    List<OrderMain> queryListByStream(Page<OrderMain> page);
    List<OrderMain> queryAmountPaid(OrderMain orderMain);
    List<OrderMain> queryAmountPaidByMonth(OrderMain orderMain);
    OrderMain queryCarwashByOrderId(@Param("orderId") String orderId);
    /** 查询所有订单 */
    List<Object> queryAllOrder(Page<Object> page);
    //查询报表临停线上
    List<OrderMain> getTempOnlinePayment(Map<String,Object> param);
    //查询报表临停线上
    List<OrderMain> getTempOfflinePayment(Map<String,Object> param);
    //查询报表月租
    List<OrderMain> getMonthlyPayment(Map<String,Object> param);
    //查询报表产权
    List<OrderMain> getEquityPayment(Map<String,Object> param);
    // 查询 缴费流水 queryStatistics
    List<Map<String,Object>> queryStatistics(Object page);
    List<OrderMain> queryListPage2(Object page);
    List<OrderMain> queryListPage3(Object page);
    List<OrderMain> queryListPage4(Object page);
    Object queryMoney();
    public void getStatistics(Map<String,String> map);
    //查询微信缴费parkingList
    List<OrderMain> getWeixinPkList(Map<String,Object> param);
    List<Map<String,Object>> queryPayfeelsStatistics(Object page);
    int selectCountByCarCus(Map<String,Object> param);
    List<OrderMain> getBytemCarAdPk(Map<String,Object> param);
}