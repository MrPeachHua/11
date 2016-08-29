package com.boxiang.share.app.car.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.car.po.CarModel;
import com.boxiang.share.product.car.service.CarModelService;
import com.boxiang.share.product.car.service.CarService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import com.boxiang.share.product.order.po.Propertyparkinginfo;
import com.boxiang.share.product.order.service.MonthlyparkinginfoService;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.order.service.PropertyparkinginfoService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("app/car")
public class CarController extends BaseController {

    private static final Logger logger = Logger.getLogger(CarController.class);

    @Resource
    private CarService carService;
    @Resource
    private MonthlyparkinginfoService monthlyparkinginfoService;
    @Resource
    private PropertyparkinginfoService propertyparkinginfoService;
    @Resource
    private OrderMainService orderMainService;

    @Resource
    private CarModelService carModelService;
    @Resource
    private ParkingService parkingService;
    @Resource
    private CustomerService customerService;

//    @Resource
//    private SequenceService sequenceService;
//
//    @Resource
//    private CustomerService customerService;

//    /**
//     * 添加车辆
//     */
//    @RequestMapping("add")
//    public void add(HttpServletRequest request, HttpServletResponse response) {
//        String message = "";
//        try {
//            String customerId = request.getParameter("customerId");
//            String carBrand = request.getParameter("carBrand");
//            String carNumber = request.getParameter("carNumber");
//
//            Customer customer = customerService.queryByCustomerId(customerId);
//            if (customer == null) {
//                message = ShangAnMessageType.operateToJson("1", "customer不存在");
//                print(message, response);
//                return;
//            }
//
//            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CAR);
//            Car car = new Car();
//            car.setCarId(sequence.getId());
//            car.setCustomerId(customerId);
//            car.setCarBrand(carBrand);
//            car.setCarNumber(carNumber);
//            carService.add(car);
//
//            car = carService.queryById(car.getCarId());
//            car.setIdentity(customer.getIdentity());
//
//            message = ShangAnMessageType.toShangAnJson("0", "success", "data", car);
//        } catch (Exception e) {
//            logger.error("异常", e);
//            message = ShangAnMessageType.operateToJson("2", "异常");
//        }
//        print(message, response);
//    }

    /**
     * 删除车辆
     */
    @RequestMapping("delete/{carId}/{summary}")
    public void delete(@PathVariable String carId,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        String message = "";
        try {
            carService.delete(carId);
            message = ShangAnMessageType.operateToJson("0", "删除成功");
        } catch (Exception e) {
            logger.error("异常", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        print(message, response);
    }

    /**
     * 车辆列表
     */
    @RequestMapping("list/{customerId}/{pageIndex}/{summary}")
    public void carList(@PathVariable String customerId,
                        @PathVariable String pageIndex,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String message = null;
        try {
            Page<Car> page = new Page<Car>();
            page.getParams().put("customerId", customerId);
            page.setCurrentPage(Integer.valueOf(pageIndex));
            Page<Car> result = carService.queryListPage(page);
            if (result.getResultList() != null && result.getResultList().size() > 0) {
                List<Map<String, Object>> list = new ArrayList<>(result.getResultList().size());
                for (Car car : result.getResultList()) {
                    Map<String, Object> map = new HashMap<>(17);
                    map.put("carId", car.getCarId() == null ? "" : car.getCarId());
                    map.put("customerId", car.getCustomerId() == null ? "" : car.getCustomerId());
                    map.put("carBrand", car.getCarBrand() == null ? "" : car.getCarBrand());
                    map.put("carColor", car.getCarColor() == null ? 0 : car.getCarColor());
                    map.put("carNumber", car.getCarNumber() == null ? "" : car.getCarNumber());
                    map.put("carBuyDate", car.getCarBuyDate() == null ? "" : car.getCarBuyDate());
                    map.put("carUseDate", car.getCarUseDate() == null ? "" : car.getCarUseDate());
                    map.put("carLasts", car.getCarLasts() == null ? "" : car.getCarLasts());
                    map.put("carUseType", car.getCarUseType() == null ? 0 : car.getCarUseType());
                    map.put("carSize", car.getCarSize() == null ? 0 : car.getCarSize());
                    map.put("carIssueDate", car.getCarIssueDate() == null ? "" : car.getCarIssueDate());
                    map.put("carImage", car.getCarImage() == null ? "" : car.getCarImage());
                    map.put("carMineImage", car.getCarMineImage() == null ? "" : car.getCarMineImage());
                    map.put("carDriverImage", car.getCarDriverImage() == null ? "" : car.getCarDriverImage());
                    map.put("ownerIdNumber", car.getOwnerIdNumber() == null ? "" : car.getOwnerIdNumber());
                    map.put("identity", car.getIdentity() == null ? "" : car.getIdentity());
                    map.put("isAutoPay", car.getIsAutoPay() == null ? "" : car.getIsAutoPay());
                    list.add(map);
                }
                message = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
            } else {
                message = ShangAnMessageType.operateToJson("1", "");
            }
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "查询失败");
        }
        print(message, response);
    }

    /**
     * 开启或关闭自动支付
     *
     * @param isAutoPay 0:关闭 1:开启
     */
    @RequestMapping("isAutoPay/{carId}/{isAutoPay}/{summary}")
    public void isAutoPay(@PathVariable String carId,
                          @PathVariable String isAutoPay,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        String message = "";
        try {
            if (!(isAutoPay.equals(Constants.TRUE) || isAutoPay.equals(Constants.FALSE))) {
                message = ShangAnMessageType.operateToJson("1", "isAutoPay只能是0或1");
                print(message, response);
                return;
            }
            Car car = carService.queryById(carId);
            if (car == null) {
                message = ShangAnMessageType.operateToJson("1", "查询不到车辆信息");
                print(message, response);
                return;
            }
            if (car.getIsAutoPay().equals(isAutoPay)) {
                message = ShangAnMessageType.operateToJson("0", "当前已经是" + (Constants.TRUE.equals(isAutoPay) ? "开启" : "关闭") + "状态");
                print(message, response);
                return;
            }
//            Car queryEntity = new Car();
//            queryEntity.setCarNumber(car.getCarNumber());
//            List<Car> carList = carService.selectList(queryEntity);
//            for (Car carItem : carList) {
//                if (isAutoPay.equals(Constants.TRUE) && carItem.getIsAutoPay().equals(Constants.TRUE)) {
//                    message = ShangAnMessageType.operateToJson("1", "该车牌号已经开启了自动支付");
//                    print(message, response);
//                    return;
//                }
//            }
            car.setIsAutoPay(isAutoPay);
            carService.update(car);
            message = ShangAnMessageType.operateToJson("0", "修改成功");
        } catch (Exception e) {
            logger.error("异常", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        print(message, response);
    }

    private void print(String message, HttpServletResponse response) {
        PrintWriter out;
        response.setContentType("application/json;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 获得递归之后的车型选择列表
     */
    @RequestMapping("getRecursionCarModel/{version}/{summary}")
    public void getRecursionCarModel(HttpServletResponse response) {
        String message;
        try {
            List<CarModel> list = carModelService.getRecursionCarModel();
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
        } catch (Exception e) {
            logger.error("异常", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        print(message, response);
    }
    //v2.0  是否是月租产权及临停
    @RequestMapping("getMonthlyEquity/{customerId}/{carNumber}/{version}/{summary}")
    public void getMonthlyEquity(HttpServletResponse response,@PathVariable String customerId,@PathVariable String carNumber) {
        String message;
        try {
            Map<String, Object> map = new HashMap<>();
            Map<String,Object> map1 = new HashMap<String,Object>();
            Map<String,Object> map2 = new HashMap<String,Object>();
            Monthlyparkinginfo  monthlyparkinginfo =new Monthlyparkinginfo();
            monthlyparkinginfo.setCarNumber(carNumber);
            monthlyparkinginfo.setIsUsed(Constants.TRUE);
            List<Monthlyparkinginfo> list=   monthlyparkinginfoService.selectList(monthlyparkinginfo);
            Customer customer = customerService.queryByCustomerId(customerId);
            if(list!=null&&list.size()>0){
                Monthlyparkinginfo  monthlyparkinginfo2=list.get(0);
                Parking parking=parkingService.queryById(monthlyparkinginfo2.getVillageId());
                map1.put("carNumber",carNumber);
                map1.put("parkingId",parking.getParkingId());
                map1.put("price",null==monthlyparkinginfo2.getMonthlyRentalPrice()?0:monthlyparkinginfo2.getMonthlyRentalPrice());
                map1.put("mobile",null==monthlyparkinginfo2.getPhone()?"":monthlyparkinginfo2.getPhone());
                map1.put("parkingName",null==parking.getParkingName()?"":parking.getParkingName());
                map1.put("maxDate", null==monthlyparkinginfo2.getMax_date()?"":DateUtil.date2str(monthlyparkinginfo2.getMax_date(),DateUtil.DATETIME_FORMAT));
                map1.put("beginDate", null==monthlyparkinginfo2.getEffect_begin_time()?"":DateUtil.date2str(monthlyparkinginfo2.getEffect_begin_time(),DateUtil.DATETIME_FORMAT));
                map1.put("endDate",null==monthlyparkinginfo2.getEffect_end_time()?"":DateUtil.date2str(monthlyparkinginfo2.getEffect_end_time(),DateUtil.DATETIME_FORMAT));
                if((monthlyparkinginfo2.getPhone()!=null&&customer.getCustomerMobile().equals(monthlyparkinginfo2.getPhone()))
                ||(monthlyparkinginfo2.getItem01()!=null && monthlyparkinginfo2.getItem01().indexOf(customerId)!=-1)){
                    map1.put("allow","1");
                }else {
                    map1.put("allow","0");
                }
            }
            Propertyparkinginfo  propertyparkinginfo=new Propertyparkinginfo();
            propertyparkinginfo.setCarNumber(carNumber);
            propertyparkinginfo.setIsUsed(Constants.TRUE);
            List<Propertyparkinginfo> list1= propertyparkinginfoService.selectList(propertyparkinginfo);
            if(list1!=null&&list1.size()>0){
                Propertyparkinginfo  propertyparkinginfo2=list1.get(0);
                Parking parking1=parkingService.queryById(propertyparkinginfo2.getVillageId());
                map2.put("beginDate",null==propertyparkinginfo2.getEffect_begin_time()?"":DateUtil.date2str(propertyparkinginfo2.getEffect_begin_time(), DateUtil.DATETIME_FORMAT));
                map2.put("endDate",null==propertyparkinginfo2.getEffect_end_time()?"":DateUtil.date2str(propertyparkinginfo2.getEffect_end_time(), DateUtil.DATETIME_FORMAT));
                map2.put("carNumber",carNumber);
                map2.put("parkingId",parking1.getParkingId());
                map2.put("price",null==propertyparkinginfo2.getManagementFeeMonthlyUnit()?0:propertyparkinginfo2.getManagementFeeMonthlyUnit());
                map2.put("mobile",null==propertyparkinginfo2.getPhone()?"":propertyparkinginfo2.getPhone());
                map2.put("parkingName",null==parking1.getParkingName()?"":parking1.getParkingName());
                map2.put("maxDate", null==propertyparkinginfo2.getMax_date()?"":DateUtil.date2str(propertyparkinginfo2.getMax_date(),DateUtil.DATETIME_FORMAT));
                if((propertyparkinginfo2.getPhone()!=null&&customer.getCustomerMobile().equals(propertyparkinginfo2.getPhone()))
                        ||(propertyparkinginfo2.getItem01()!=null && propertyparkinginfo2.getItem01().indexOf(customerId)!=-1)){
                    map2.put("allow","1");
                }else {
                    map2.put("allow","0");
                }
            }
            map.put("monthly",map1);
            map.put("equity",map2);
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", map);
        } catch (Exception e) {
            logger.error("异常", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        print(message, response);
    }

    @RequestMapping(value = "getMonthlyEquity", method = RequestMethod.POST)
    public void getMonthlyEquity(@RequestParam String customerId,
                                 String version,
                                 HttpServletResponse response,
                                 HttpServletRequest request) {
        String message;
        try {
            Customer customer = customerService.queryByCustomerId(customerId);
            if (customer == null) {
                message = ShangAnMessageType.toShangAnJson("1", "用户不存在", "data", "");
            } else {
                List<Map<String, Object>> list = monthlyparkinginfoService.getMonthlyEquity(customer);
                message = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
            }
        } catch (Exception e) {
            logger.error("异常", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        print(message, response);
    }

}
