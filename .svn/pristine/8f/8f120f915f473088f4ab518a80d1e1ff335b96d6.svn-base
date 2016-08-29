package com.boxiang.share.product.carlife.dao;
import java.util.List;
import java.util.Map;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.carlife.po.OrderRefuel;
import com.boxiang.share.product.carlife.po.Refuel;
import org.apache.ibatis.annotations.Param;

public interface OrderRefuelDao extends IMybatisBaseDao<OrderRefuel> {
    
    void batchUpdate(List<OrderRefuel> tOrderRefuelList);
    //查询支付完成的加油卡订单
    List<OrderRefuel> selectListForQuartz(Map<String,Object> param);
   List<Refuel> queryListByRefuelPage(Page<OrderRefuel> page);
   OrderRefuel queryByOrderId(String id);
    Refuel queryRefuelByOrderId(@Param("orderId") String orderId);
}