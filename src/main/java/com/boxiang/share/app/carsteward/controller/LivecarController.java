package com.boxiang.share.app.carsteward.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.car.dao.CarDao;
import com.boxiang.share.product.car.dao.CarMainDao;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.car.po.CarMain;
import com.boxiang.share.product.car.service.CarMainService;
import com.boxiang.share.product.car.service.CarService;
import com.boxiang.share.product.carBrand.po.CarBrand;
import com.boxiang.share.product.carBrand.service.CarBrandService;
import com.boxiang.share.product.car.service.CarUpkeepService;
import com.boxiang.share.system.po.*;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaiser on 2016/7/29.
 */

@Controller
@RequestMapping("app/carsteward/livecar")
public class LivecarController extends BaseController {

    private static final Logger logger = Logger.getLogger(LivecarController.class);
    @Resource  private CarService carService;
    @Resource  private SequenceService sequenceService;
    @Resource  private CarBrandService carBrandService;
    @Resource private CarUpkeepService carUpkeepService;
    @Resource private CarMainService carMainService;
    @Resource private CarMainDao carMainDao;
    @Resource  private CarDao carDao;
    @Resource  private DictionaryService dictionaryService;
    /**
     * 查询爱车车场列表
     * @param customerId
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("parkinglist/{customerId}/{version}/{summary}")
    public void cardList(@PathVariable String customerId,@PathVariable String version,HttpServletRequest request,HttpServletResponse response){
        String message = null;
        try{
            message=carService.queryParkingList(customerId,request);
        }catch(Exception e){
            e.printStackTrace();
            message = ShangAnMessageType.operateToJson("2", "查询失败");
        }
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 设置默认车辆
     * @param request
     * @param response
     */
        @RequestMapping("defaultcar")
        public void defaultcar(HttpServletRequest request,HttpServletResponse response)
        {
            String message = null;
            String customerId=request.getParameter("customerId");
            String carId=request.getParameter("carId");
            String version=request.getParameter("version");
            try {
                Car car=carService.queryById(carId);
                car.setIsDefault(1);
                message = carService.defaultcar(car,carId,customerId);
            } catch (Exception e) {
                e.printStackTrace();
                message = ShangAnMessageType.operateToJson("2", "设置失败");
            }
            PrintWriter out;
            response.setContentType("text/html;charset=UTF-8");
            try {
                out = response.getWriter();
                out.print(message);
            } catch (IOException e) {
                logger.error("", e);
            }
        }

    /**
     * 修改行驶里程信息
     * @param carId 车辆ID
     * @param travlledDistance 行驶里程
     */
    @RequestMapping("modtravlleddistance")
    public void modtravlleddistance(@RequestParam String carId,
                                    @RequestParam String travlledDistance,
                                    String version,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        String msg;
        response.setContentType("application/json;charset=UTF-8");
        try {
            if (!travlledDistance.matches("^\\d{1,7}$")) {
                msg = ShangAnMessageType.operateToJson("1", "必须为数字,且长度为1-6");
                response.getWriter().print(msg);
                return;
            }
            Car car = carService.queryById(carId);
            if (car == null) {
                msg = ShangAnMessageType.operateToJson("1", "车辆不存在");
                response.getWriter().print(msg);
                return;
            }
            Integer historyTravlledDistance = car.getTravlledDistance() == null ? 0 : Integer.valueOf(car.getTravlledDistance());
            if (Integer.valueOf(travlledDistance) < historyTravlledDistance) {
                msg = ShangAnMessageType.operateToJson("1", "填写的里程数小于当前里程数");
                response.getWriter().print(msg);
                return;
            }
            car.setTravlledDistance(travlledDistance);
            carService.update(car);
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", new HashMap<>());
        } catch (Exception e) {
            logger.error("", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(msg);
    }

    /**
     * 修改上路时间信息
     * @param carId 车辆ID
     * @param carUseDate 上路时间 yyyyMM 或者 yyyy-MM
     */
    @RequestMapping("modcanusedate")
    public void modcanusedate(@RequestParam String carId,
                              @RequestParam String carUseDate,
                              String version,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String msg;
        response.setContentType("application/json;charset=UTF-8");
        try {
            Car car = carService.queryById(carId);
            if (car == null) {
                msg = ShangAnMessageType.operateToJson("1", "车辆不存在");
                response.getWriter().print(msg);
                return;
            }
            if (carUseDate.length()==6){
                carUseDate = carUseDate.substring(0,4)+"-"+carUseDate.substring(4,6);
            }
            String now = DateUtil.getCurrDate("yyyy-MM");
            if (carUseDate.compareTo(now) > 0) {
                msg = ShangAnMessageType.operateToJson("1", "上路时间必须小于等于当前年月");
                response.getWriter().print(msg);
                return;
            }
            if (car.getStyleYear() != null && car.getStyleYear().compareTo(carUseDate.substring(0, 4)) > 0) {
                msg = ShangAnMessageType.operateToJson("1", "当前填写时间小于该车型生产年份");
                response.getWriter().print(msg);
                return;
            }
           // car.setCarUseDate(carUseDate.substring(0,4)+"-"+carUseDate.substring(5,6));
            car.setCarUseDate(carUseDate);
            carService.update(car);
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", new HashMap<>());
        } catch (Exception e) {
            logger.error("", e);
           // e.printStackTrace();
            System.out.print(e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(msg);
    }

    /**
     * 删除绑定车辆
     * @param request
     * @param response
     */
    @RequestMapping("rmbinding")
    public void rmbinding(HttpServletRequest request,HttpServletResponse response)
    {
        String message = null;
        String customerId=request.getParameter("customerId");
        String carId=request.getParameter("carId");
        String version=request.getParameter("version");
        try {
            Car car= carService.queryById(carId);
            if(car!=null){
                if(car.getIsDefault()==1){
                    carService.delete(carId);
                    List<Car> list=  carDao.queryParkingList(customerId);
                    if(list!=null&&list.size()>0){
                        list.get(0).setIsDefault(1);
                        carService.update(list.get(0));
                    }
                }else{
                    carService.delete(carId);
                }

            }


            message= ShangAnMessageType.toShangAnJson("0", "success","data","{}");
        } catch (Exception e) {
            e.printStackTrace();
            message = ShangAnMessageType.operateToJson("2", "删除失败");
        }
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 获取保养提醒信息列表
     * @param customerId 客户ID
     * @param carId      车辆ID
     */
    @RequestMapping("gainupkeep/{customerId}/{carId}/{version}/{summary}")
    public void gainupkeep(@PathVariable String customerId,
                           @PathVariable String carId,
                           @PathVariable String version,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String msg;
        response.setContentType("application/json;charset=UTF-8");
        try {
            Car car = carService.queryById(carId);
            if (car == null) {
                msg = ShangAnMessageType.operateToJson("1", "车辆不存在");
                response.getWriter().print(msg);
                return;
            }
            CarMain carMain = new CarMain();
            carMain.setTradeName(car.getTradeName());
            carMain.setBrand(car.getCarBrand());
            carMain.setCarSeries(car.getCarSeries());
            carMain.setVehicleType(car.getVehicleType());
            carMain.setStyleYear(car.getStyleYear());
            carMain.setDisplacement(car.getDisplacement());
            List<CarMain> carMainList = carMainService.selectList(carMain);
            if (carMainList == null || carMainList.size() == 0) {
                msg = ShangAnMessageType.operateToJson("1", "没有符合条件的保养信息");
                response.getWriter().print(msg);
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (CarMain item : carMainList) {
                sb.append(",'");
                sb.append(item.getLevelId());
                sb.append("'");
            }
            String levelIds = sb.toString();
            if (levelIds.startsWith(",")) {
                levelIds = levelIds.substring(1);
            }
            Map<String, Object> params = new HashMap<>(3);
            params.put("levelIds", levelIds);
            params.put("travlledDistance", car.getTravlledDistance() == null ? 0 : Integer.valueOf(car.getTravlledDistance()));
            int monthInterval = 0;
            if (StringUtils.isNotEmpty(car.getCarUseDate())) {
                monthInterval = DateUtil.getDiffMonthsOfTwoDate(car.getCarUseDate(), DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
            }
            params.put("monthInterval", monthInterval);
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            params.put("basePath", basePath);
            List<Map<String, Object>> data = carUpkeepService.gainupkeep(params);
            if (data == null || data.size() == 0) {
                msg = ShangAnMessageType.operateToJson("1", "没有符合条件的保养信息");
            } else {
                msg = ShangAnMessageType.toShangAnJson("0", "success", "data", data);
            }
        } catch (Exception e) {
            logger.error("", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(msg);
    }

    /**
     * 进入车辆修改页
     * @param customerId
     * @param carId
     * @param version
     * @param request
     * @param response
     * @throws IOException
     */

           @RequestMapping("gainbindingcar/{customerId}/{carId}/{version}/{summary}")
           public void gainbindingcar(@PathVariable String customerId,
                                  @PathVariable String carId,
                                  @PathVariable String version,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
               String msg;
               response.setContentType("application/json;charset=UTF-8");
               try {
                   Map<String, Object> data = new HashMap<>();
                   Map<String, Object> map = new HashMap<>();
                   Car car= carService.queryById2(carId);
                   if(car!=null){
                   map.put("tradeName",car.getTradeName()!=null?car.getTradeName():"");
                   map.put("carBrand",car.getCarBrand()!=null?car.getCarBrand():"");
                   map.put("carSeries",car.getCarSeries()!=null?car.getCarSeries():"");
                   map.put("intakeType",car.getIntakeType()!=null?car.getIntakeType():"");
                   map.put("displacement",car.getDisplacement()!=null?car.getDisplacement():"");
                   map.put("displacementShow",(car.getDisplacement()!=null?car.getDisplacement():"")+(null!=car.getIntakeName()?car.getIntakeName():""));
                   map.put("styleYear", car.getStyleYear()!=null?car.getStyleYear():"");
                   data.put("carId",car.getCarId());
                   data.put("carNumber",car.getCarNumber());
                   data.put("carModel",map);
                   data.put("travlledDistance",car.getTravlledDistance()!=null?car.getTravlledDistance():"");
                   //data.put("carUseDate",car.getCarUseDate()!=null?DateUtil.date2str(DateUtil.str2date(car.getCarUseDate(), DateUtil.DATE_FORMAT_YEAR_MONTHLY),DateUtil.DATE_FORMAT_YEAR_MONTHLY):"");
                       if( car.getCarUseDate()!=null&& !car.getCarUseDate().equals("")){
                         //  carMap.put("carUseDate", car.getCarUseDate().substring(0,7));
                           data.put("carUseDate",car.getCarUseDate().substring(0,7));
                       }else {
                           data.put("carUseDate", "");
                       }
                   data.put("carUseDate",car.getCarUseDate()!=null?car.getCarUseDate().substring(0,7):"");
                   data.put("isAutoPay",car.getIsAutoPay()!=null?car.getIsAutoPay():"");
                   data.put("frameNum",car.getFrameNum()!=null?car.getFrameNum():"");
                   data.put("engineNum",car.getEngineNum()!=null?car.getEngineNum():"");
                   }
                   msg = ShangAnMessageType.toShangAnJson("0", "success", "data", data);
               } catch (Exception e) {
                   logger.error("", e);
                   msg = ShangAnMessageType.operateToJson("2", "异常");
               }
               response.getWriter().print(msg);
           }

    /**
     * 保存车信息
     * @param request
     * @param response
     */
    @RequestMapping("bindingcar")
    public void bindingcar (HttpServletRequest request, HttpServletResponse response)throws IOException{
        String msg;
        response.setContentType("application/json;charset=UTF-8");
        try {
            String carId=request.getParameter("carId");
            String customerId=request.getParameter("customerId");
            String carNumber=request.getParameter("carNumber");
            String tradeName=request.getParameter("tradeName");
            String carBrand=request.getParameter("carBrand");
            String carSeries=request.getParameter("carSeries");
            String displacement=request.getParameter("displacement");
            String styleYear=request.getParameter("styleYear");
            String travlledDistance=request.getParameter("travlledDistance");
            String carUseDate=request.getParameter("carUseDate");
            String isAutoPay=request.getParameter("isAutoPay");
            String frameNum=request.getParameter("frameNum");
            String engineNum=request.getParameter("engineNum");
            String intakeType=request.getParameter("intakeType");
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CAR);
            Car car=new Car();
            car.setIsDefault(0);
            if(carId!=null&&!carId.equals("")){
                car.setCarId(carId);
                Car car5 = carService.queryById(carId);
                if(car5!=null){
                    car.setIsDefault(car5.getIsDefault());
                }else {
                    car.setIsDefault(0);
                }
            }else {
                car.setCarId(sequence.getId());
            }
            car.setCarNumber(carNumber);
            car.setCustomerId(customerId);
            car.setTradeName(tradeName);
            car.setCarBrand(carBrand);
            car.setCarSeries(carSeries);
            car.setDisplacement(displacement);
            car.setStyleYear(styleYear);
            car.setTravlledDistance(travlledDistance);
            car.setCarUseDate(carUseDate);
            car.setIsAutoPay(isAutoPay);
            car.setIntakeType(intakeType);
            car.setFrameNum(frameNum);
            car.setEngineNum(engineNum);
            if(carId!=null&&!carId.equals("")) {
                Car car3 = carService.queryById(carId);
                if (travlledDistance != null && !("").equals(travlledDistance)) {
                    Integer historyTravlledDistance = car3.getTravlledDistance() == null ? 0 : Integer.valueOf(car3.getTravlledDistance());
                    if(travlledDistance.length()>7){
                        msg = ShangAnMessageType.operateToJson("1", "超出里程数范围");
                        response.getWriter().print(msg);
                        return;
                    }else {
                        if (Integer.parseInt(travlledDistance) < historyTravlledDistance) {
                            msg = ShangAnMessageType.operateToJson("1", "填写的里程数小于当前里程数");
                            response.getWriter().print(msg);
                            return;
                        }
                    }
                }
            }
            if (travlledDistance != null && !("").equals(travlledDistance)) {
            if(travlledDistance.length()>7){
                msg = ShangAnMessageType.operateToJson("1", "超出里程数范围");
                response.getWriter().print(msg);
                return;
            }
            }
            if(carUseDate!=null&&!carUseDate.equals("")){
                String now = DateUtil.getCurrDate("yyyyMM");
                if (carUseDate.compareTo(now) > 0) {
                    msg = ShangAnMessageType.operateToJson("1", "上路时间必须小于等于当前年月");
                    response.getWriter().print(msg);
                    return;
                }
                if (styleYear != null && styleYear.compareTo(carUseDate.substring(0, 4)) > 0) {
                    msg = ShangAnMessageType.operateToJson("1", "当前填写时间小于该车型生产年份");
                    response.getWriter().print(msg);
                    return;
                }
            }

            if (carNumber==null||carNumber.equals("")){
                msg = ShangAnMessageType.toShangAnJson("1", "车牌号不能为空", "data", "{}");
                response.getWriter().print(msg);
                return;
            }
            if(carId!=null&&!carId.equals("")){
                Car car2=carService.queryById(carId);
                if(car2.getCarNumber().equals(carNumber)){
                    carService.update(car);
                }else {
                    Car car1 = new Car();
                    car1.setCarNumber(carNumber);
                    car1.setCustomerId(customerId);
                    List list = carService.selectList(car1);
                    if (list != null && list.size() > 0) {
                        msg = ShangAnMessageType.toShangAnJson("1", "存在相同的车牌号", "data", "{}");
                        response.getWriter().print(msg);
                        return;
                    }
                    carService.update(car);
                }
            }else {
                Car car1 = new Car();
                car1.setCarNumber(carNumber);
                car1.setCustomerId(customerId);
                List list = carService.selectList(car1);
                if (list != null && list.size() > 0) {
                    msg = ShangAnMessageType.toShangAnJson("1", "存在相同的车牌号", "data", "{}");
                    response.getWriter().print(msg);
                    return;
                }
                carService.add(car);
            }
                msg = ShangAnMessageType.toShangAnJson("0", "success", "data", car.getCarId());
        } catch (Exception e) {
            logger.error("", e);
            e.printStackTrace();
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(msg);
    }
    @RequestMapping("gaincarbrand/{version}/{summary}")
    public void gaincarbrand (HttpServletRequest request, HttpServletResponse response)throws IOException{
        String msg;
        response.setContentType("application/json;charset=UTF-8");
        try {
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            Map<String, Object> data = new HashMap<>();
            //热门品牌
            CarBrand carBrand=new CarBrand();
            carBrand.setIsHot(1);
            List<CarBrand> hotCarBrand=   carBrandService.selectList2(carBrand);//热门
            CarBrand carBrand1=new CarBrand();
            List<CarBrand> listCarBrand=   carBrandService.selectList2(carBrand1);//所有车型
            List<CarBrand> listBrandA=new ArrayList<>();
            List<CarBrand> listBrandB=new ArrayList<>();
            List<CarBrand> listBrandC=new ArrayList<>();
            List<CarBrand> listBrandD=new ArrayList<>();
            List<CarBrand> listBrandE=new ArrayList<>();
            List<CarBrand> listBrandF=new ArrayList<>();
            List<CarBrand> listBrandG=new ArrayList<>();
            List<CarBrand> listBrandH=new ArrayList<>();
            List<CarBrand> listBrandI=new ArrayList<>();
            List<CarBrand> listBrandJ=new ArrayList<>();
            List<CarBrand> listBrandK=new ArrayList<>();
            List<CarBrand> listBrandL=new ArrayList<>();
            List<CarBrand> listBrandM=new ArrayList<>();
            List<CarBrand> listBrandN=new ArrayList<>();
            List<CarBrand> listBrandO=new ArrayList<>();
            List<CarBrand> listBrandP=new ArrayList<>();
            List<CarBrand> listBrandQ=new ArrayList<>();
            List<CarBrand> listBrandR=new ArrayList<>();
            List<CarBrand> listBrandS=new ArrayList<>();
            List<CarBrand> listBrandT=new ArrayList<>();
            List<CarBrand> listBrandU=new ArrayList<>();
            List<CarBrand> listBrandV=new ArrayList<>();
            List<CarBrand> listBrandW=new ArrayList<>();
            List<CarBrand> listBrandX=new ArrayList<>();
            List<CarBrand> listBrandY=new ArrayList<>();
            List<CarBrand> listBrandZ=new ArrayList<>();
            for (CarBrand carBrand2:listCarBrand){
                if(carBrand2.getBrandIcon()!=null&&!carBrand2.getBrandIcon().equals("")){
                    carBrand2.setBrandIcon(basePath+carBrand2.getBrandIcon());
                }
                if(carBrand2.getInitial().equals("A")){
                    listBrandA.add(carBrand2);
                }else if (carBrand2.getInitial().equals("B")){
                    listBrandB.add(carBrand2);

                }else if (carBrand2.getInitial().equals("C")){
                    listBrandC.add(carBrand2);

                }else if (carBrand2.getInitial().equals("D")){
                    listBrandD.add(carBrand2);

                }else if (carBrand2.getInitial().equals("E")){
                    listBrandE.add(carBrand2);

                }else if (carBrand2.getInitial().equals("F")){
                    listBrandF.add(carBrand2);

                }else if (carBrand2.getInitial().equals("G")){
                    listBrandG.add(carBrand2);

                }else if (carBrand2.getInitial().equals("H")){
                    listBrandH.add(carBrand2);

                }else if (carBrand2.getInitial().equals("I")){
                    listBrandI.add(carBrand2);

                }else if (carBrand2.getInitial().equals("J")){
                    listBrandJ.add(carBrand2);

                }else if (carBrand2.getInitial().equals("K")){
                    listBrandK.add(carBrand2);

                }else if (carBrand2.getInitial().equals("L")){
                    listBrandL.add(carBrand2);

                }else if (carBrand2.getInitial().equals("M")){
                    listBrandM.add(carBrand2);

                }else if (carBrand2.getInitial().equals("N")){
                    listBrandN.add(carBrand2);

                }else if (carBrand2.getInitial().equals("O")){
                    listBrandO.add(carBrand2);

                }else if (carBrand2.getInitial().equals("P")){
                    listBrandP.add(carBrand2);

                }else if (carBrand2.getInitial().equals("Q")){
                    listBrandQ.add(carBrand2);

                }else if (carBrand2.getInitial().equals("R")){
                    listBrandR.add(carBrand2);

                }else if (carBrand2.getInitial().equals("S")){
                    listBrandS.add(carBrand2);

                }else if (carBrand2.getInitial().equals("T")){
                    listBrandT.add(carBrand2);

                }else if (carBrand2.getInitial().equals("U")){
                    listBrandU.add(carBrand2);

                }else if (carBrand2.getInitial().equals("V")){
                    listBrandV.add(carBrand2);

                }else if (carBrand2.getInitial().equals("W")){
                    listBrandW.add(carBrand2);

                }else if (carBrand2.getInitial().equals("X")){
                    listBrandX.add(carBrand2);

                }else if (carBrand2.getInitial().equals("Y")){
                    listBrandY.add(carBrand2);

                }else if (carBrand2.getInitial().equals("Z")){
                    listBrandZ.add(carBrand2);
                }
            }
            if(hotCarBrand!=null&&hotCarBrand.size()>0){
                for(CarBrand carBrand3:hotCarBrand){
                    if(carBrand3.getBrandIcon()!=null){
                        carBrand3.setBrandIcon(basePath+carBrand3.getBrandIcon());
                    }
                }
                data.put("hotBrands",hotCarBrand);
            }
            if(listBrandA!=null&&listBrandA.size()>0) {
                data.put("A", listBrandA);
            }
            if(listBrandB!=null&&listBrandB.size()>0) {
                data.put("B", listBrandB);
            }
            if(listBrandC!=null&&listBrandC.size()>0) {
                data.put("C", listBrandC);
            }
            if(listBrandD!=null&&listBrandD.size()>0) {
                data.put("D", listBrandD);
            }
            if(listBrandE!=null&&listBrandE.size()>0) {
                data.put("E", listBrandE);
            }
            if(listBrandF!=null&&listBrandF.size()>0) {
                data.put("F", listBrandF);
            }
            if(listBrandG!=null&&listBrandG.size()>0) {
                data.put("G", listBrandG);
            }
            if(listBrandH!=null&&listBrandH.size()>0) {
                data.put("H", listBrandH);
            }
            if(listBrandI!=null&&listBrandI.size()>0) {
                data.put("I", listBrandI);
            }
            if(listBrandJ!=null&&listBrandJ.size()>0) {
                data.put("J", listBrandJ);
            }
            if(listBrandK!=null&&listBrandK.size()>0) {
                data.put("K", listBrandK);
            }
            if(listBrandL!=null&&listBrandL.size()>0) {
                data.put("L", listBrandL);
            }
            if(listBrandM!=null&&listBrandM.size()>0) {
                data.put("M", listBrandM);
            }
            if(listBrandN!=null&&listBrandN.size()>0) {
                data.put("N", listBrandN);
            }
            if(listBrandO!=null&&listBrandO.size()>0) {
                data.put("O", listBrandO);
            }
            if(listBrandP!=null&&listBrandP.size()>0) {
                data.put("P", listBrandP);
            }
            if(listBrandQ!=null&&listBrandQ.size()>0) {
                data.put("Q", listBrandQ);
            }
            if(listBrandR!=null&&listBrandR.size()>0) {
                data.put("R", listBrandR);
            }
            if(listBrandS!=null&&listBrandS.size()>0) {
                data.put("S", listBrandS);
            }
            if(listBrandT!=null&&listBrandT.size()>0) {
                data.put("T", listBrandT);
            }
            if(listBrandU!=null&&listBrandU.size()>0) {
                data.put("U", listBrandU);
            }
            if(listBrandV!=null&&listBrandV.size()>0) {
                data.put("V", listBrandV);
            }
            if(listBrandW!=null&&listBrandW.size()>0) {
                data.put("W", listBrandW);
            }
            if(listBrandX!=null&&listBrandX.size()>0) {
                data.put("X", listBrandX);
            }
            if(listBrandY!=null&&listBrandY.size()>0) {
                data.put("Y", listBrandY);
            }
            if(listBrandZ!=null&&listBrandZ.size()>0) {
                data.put("Z", listBrandZ);
            }
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", data);
        } catch (Exception e) {
            logger.error("", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(msg);
    }

    /**
     * 获取厂家和车系列表
     * @param
     * @param
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("gaincartrade")
    public void gaincartrade(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String carBrand=request.getParameter("carBrand");
        String version=request.getParameter("version");
        String msg;
        response.setContentType("application/json;charset=UTF-8");
        try {
           // Map<String, Object> data = new HashMap<>();
            List<Map<String,Object>> list=new ArrayList<>();
           // List<Map<String,Object>> list2=new ArrayList<>();
            List<Map<String,Object>> listTrandName=carMainDao.queryTrandName(carBrand);
            if(listTrandName!=null&&listTrandName.size()>0){
                for (Map map1:listTrandName){
                    Map<String, Object> map = new HashMap<>();
                  List<Map<String,Object>> listCarSeries=  carMainDao.queryCarSeries(map1.get("BRAND").toString(),map1.get("TRADE_NAME").toString());
                    String[] carSeries=new String[listCarSeries.size()];
                        for (int i = 0; i <listCarSeries.size(); i++) {
                            Map map2=listCarSeries.get(i);
                            carSeries[i]=map2.get("CAR_SERIES").toString();
                    }
                    map.put("tradeName",map1.get("TRADE_NAME").toString());
                    map.put("carSeries",carSeries);
                    list.add(map);
                }
            }
           // data.put("tradeSeries",list);
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", list!=null?list:new ArrayList<>());
        } catch (Exception e) {
            logger.error("", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(msg);
    }
    /**
     * 获取排量和年款
     * @param
     * @param
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("gaincardisplacement")
    public void gaincardisplacement(HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
        //{carBrand}/{tradeName}/{carSeries}/{version}/{summary}
        String carBrand=request.getParameter("carBrand");
        String tradeName=request.getParameter("tradeName");
        String carSeries=request.getParameter("carSeries");
        String version=request.getParameter("version");
        String msg;
        response.setContentType("application/json;charset=UTF-8");
        try {
          //  Map<String, Object> data = new HashMap<>();
            List<Map<String,Object>> list=new ArrayList<>();
            CarMain  carMain=new CarMain();
            carMain.setBrand(carBrand);
            carMain.setCarSeries(carSeries);
            carMain.setTradeName(tradeName);
            List<Map<String,Object>> listCarMain=carMainDao.queryDisplacement(carMain);
            if(listCarMain!=null&&listCarMain.size()>0){
                for (Map map1:listCarMain){
                    Map<String, Object> map = new HashMap<>();
                    if(!map1.get("DISPLACEMENT").toString().equals("")){
                    CarMain carMain2=new CarMain();
                    carMain2.setBrand(carBrand);
                    carMain2.setTradeName(tradeName);
                    carMain2.setCarSeries(carSeries);
                    carMain2.setDisplacement(map1.get("DISPLACEMENT").toString());
                    List<Map<String,Object>> listCarMain2=carMainDao.queryDisplacement2(carMain2);
                    String[] styleYear=new String[listCarMain2.size()];
                    for (int i = 0; i <listCarMain2.size(); i++) {
                        Map map2=listCarMain2.get(i);
                        styleYear[i]=map2.get("STYLE_YEAR").toString();
                    }
                    map.put("displacement",map1.get("DISPLACEMENT").toString());
                    map.put("displacementShow",map1.get("DISPLACEMENT").toString()+map1.get("intake_type").toString());
                   // map.put("intakeTypeCode",map1.get("intake_type").toString());
                    map.put("intakeType",map1.get("intakeTypeValue").toString());
                    map.put("styleYear",styleYear);
                    list.add(map);
                    }
                }
            }
           // data.put("tradeSeries",list2);
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", list!=null?list:new ArrayList<>());
        } catch (Exception e) {
            logger.error("", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(msg);
    }
    @RequestMapping("gaindefaultcar/{customerId}/{version}/{summary}")
    public void gaindefaultcar(@PathVariable String customerId,@PathVariable String version,HttpServletRequest request,HttpServletResponse response){
        String message = null;
        try{
            message=carService.queryParkingList2(customerId,request);
        }catch(Exception e){
            e.printStackTrace();
            message = ShangAnMessageType.operateToJson("2", "查询失败");
        }

        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
