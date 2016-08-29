package com.boxiang.share.product.parking.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.product.order.service.OrderParkService;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.po.*;
import com.boxiang.share.product.parking.service.*;

import com.boxiang.share.utils.ShangAnMessageType;
import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.utils.json.JacksonUtil;

@Controller
@RequestMapping("products/parking")
public class ParkController extends BaseController {

    private static final Logger logger = Logger.getLogger(ParkController.class);

    @Resource
    private ParkingService parkingService;

    @Resource
    private ParkingPriceService parkingPriceService;
    @Resource
    private ParkingRelevanceService parkingRelevanceService;

    @Resource
    private VillageinfoService villageinfoService;
    @Resource
    private DiscountParkingPriceService discountParkingPriceService;
    @Resource(name="dataEhCache")
    private Cache ehCache;

    @Resource
    private ParkingStatusService parkingStatusService;

    @Resource
    private OrderParkService orderParkService;

    @Resource
    private ParkerService parkerService;

    /**
     * 查询代泊员
     */
    @RequestMapping("queryParker")
    public void queryParker(@RequestParam String parkingId,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String message;
        try {
            Parker entity = new Parker();
            entity.setParkingId(parkingId);
            entity.setParkerType("0");
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", parkerService.selectList(entity));
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 查询当日接单数
     */
    @RequestMapping("queryTodayCount")
    public void queryTodayCount(@RequestParam String parkingId,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String message;
        try {
            int num = orderParkService.queryTodayCount(parkingId);
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", num);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 查询单小时接单数
     */
    @RequestMapping("querySingleHourCount")
    public void querySingleHourCount(@RequestParam String parkingId,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException {
        String message;
        try {
            int num = orderParkService.querySingleHourCount(parkingId);
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", num);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 查询
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<Parking> page = new Page<Parking>();
            PageHelper.initPage(request, page);
            Map<String, Object> map = super.getParamMap(request);
            page.getParams().put("parkingCity", map.get("parkingCity"));
            page.getParams().put("parkingStatus", map.get("parkingStatus"));
            page.getParams().put("parkingAddress", map.get("parkingAddress"));
            page.getParams().put("parkingName", map.get("parkingName"));
            page = parkingService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("进入列表页出现异常", e);
        }
        return new ModelAndView("products/parking/park_list");
    }
    
    @RequestMapping("get.html")
    @ResponseBody
    public void getParkByName(@RequestParam("parkName") String parkName,HttpServletResponse response){
  
    	Page<Parking> page = new Page<Parking>();
    	page.getParams().put("parkingName", parkName);
    	
    	try {
    		response.setContentType("UTF-8");
			response.getWriter().write(JacksonUtil.toJson(parkingService.queryListPage(page).getResultList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/del.html")
    public ModelAndView del(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            parkingPriceService.delParkingAndParkingPrice(id);
            //删除关联价格表
            discountParkingPriceService.delete(id);
            request.setAttribute("info", "删除成功");
        } catch (Exception e) {
            logger.error("删除信息出现异常", e);
            request.setAttribute("info", "删除信息出现异常");
        }
        return new ModelAndView("products/parking/park_save");
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            Parking parking = parkingService.queryById(id);
            if (parking == null) {
                throw new NullPointerException("没有id为：" + id + "的数据!");
            }
            // 查询对应的价格规则信息
            ParkingPrice parkingPrice = new ParkingPrice();
            parkingPrice.setParkingId(id);
            List<ParkingPrice> priceList = parkingPriceService.selectList(parkingPrice);
            if (priceList != null && priceList.size() != 0) {
                for (ParkingPrice item : priceList) {
                    if (item.getSchemeId().equals("1")) {
                        parking.setScheme_init_price(item.getPrice());
                    } else if (item.getSchemeId().equals("2")) {
                        parking.setScheme_exceed_price(item.getPrice());
                    } else if (item.getSchemeId().equals("3")) {
                        parking.setScheme_proxy_price(item.getPrice());
                    } else if (item.getSchemeId().equals("4")) {
                        parking.setScheme_park_price(item.getPrice());
                    } else if (item.getSchemeId().equals("5")) {
                        parking.setScheme_exceed_night_price(item.getPrice());
                    } else if (item.getSchemeId().equals("6")) {
                        parking.setScheme_top_price(item.getPrice());
                    } else if (item.getSchemeId().equals("7")) {
                        parking.setScheme_discount(item.getPrice());
                    } else if (item.getSchemeId().equals("8")) {
                        parking.setScheme_init_hour(item.getPrice());
                    }
                }
            } 
            // 查询关联的车场id，如蓝卡云车场id
            Villageinfo villageinfo = villageinfoService.queryById(parking.getParkingId());
            parking.setVillageinfo(villageinfo);
            request.setAttribute("parking", parking);
            //查询优惠价格
           DiscountParkingPrice discountParkingPrice= discountParkingPriceService.queryById(id);
            if (discountParkingPrice != null) {
                if (discountParkingPrice.getMondayPrice() != null && !discountParkingPrice.getMondayPrice().equals("")) {
                    discountParkingPrice.setMondayPrice(discountParkingPrice.getMondayPrice() / 100);
                }
                if (discountParkingPrice.getTuesdayPrice() != null && !discountParkingPrice.getTuesdayPrice().equals("")) {
                    discountParkingPrice.setTuesdayPrice(discountParkingPrice.getTuesdayPrice() / 100);
                }
                if (discountParkingPrice.getWednesdayPrice() != null && !discountParkingPrice.getWednesdayPrice().equals("")) {
                    discountParkingPrice.setWednesdayPrice(discountParkingPrice.getWednesdayPrice() / 100);
                }
                if (discountParkingPrice.getThursdayPrice() != null && !discountParkingPrice.getThursdayPrice().equals("")) {
                    discountParkingPrice.setThursdayPrice(discountParkingPrice.getThursdayPrice() / 100);
                }
                if (discountParkingPrice.getFridayPrice() != null && !discountParkingPrice.getFridayPrice().equals("")) {
                    discountParkingPrice.setFridayPrice(discountParkingPrice.getFridayPrice() / 100);
                }
                if (discountParkingPrice.getSaturdayPrice() != null && !discountParkingPrice.getSaturdayPrice().equals("")) {
                    discountParkingPrice.setSaturdayPrice(discountParkingPrice.getSaturdayPrice() / 100);
                }
                if (discountParkingPrice.getSundayPrice() != null && !discountParkingPrice.getSundayPrice().equals("")) {
                    discountParkingPrice.setSundayPrice(discountParkingPrice.getSundayPrice() / 100);
                }
                request.setAttribute("discountParkingPrice", discountParkingPrice);
            }
            // 查询关联的车场id   自己的对应车场
            List<Map> listParking=parkingService.queryParkingRelevance(id);
            request.setAttribute("listParking", listParking);
        } catch (Exception e) {
            logger.error("信息出现异常", e);
        }
        return new ModelAndView("products/parking/park_edit");
    }

    @RequestMapping("toChooseParkingPage.html")
    public ModelAndView toChooseParkingPage(String id){
    	ModelAndView mv=new ModelAndView("products/parking/chooseParking");
    	mv.addObject("parkings", parkingService.queryParkingRelevance(id));
    	return mv;
    }
    //异步修改对应车场
    @RequestMapping("updateParking.html")
    public void updateParking(String parkingId, HttpServletRequest request) {
        //删除原有的对应车场
        parkingRelevanceService.del(parkingId);
        //修改对应车场
        String[] parkingName = request.getParameterValues("parkingList");
        if (parkingName != null && parkingName.length > 0) {
            for (int i = 0; i < parkingName.length; i++) {
                ParkingRelevance parkingRelevance = new ParkingRelevance();
                parkingRelevance.setCorrespondingId(parkingName[i]);
                parkingRelevance.setParkingId(parkingId);
                parkingRelevanceService.add(parkingRelevance);
            }
        }
    }
    /**
     * 修改
     */
    @RequestMapping("update.html")
    public ModelAndView update(@ModelAttribute("parking") Parking parking,@ModelAttribute("discountParkingPrice") DiscountParkingPrice discountParkingPrice, HttpServletRequest request, HttpServletResponse response) {
        try {
        	//清除缓存
        	 ehCache.remove("t_parking"+parking.getParkingId());
            //删除原有的对应车场
            parkingRelevanceService.del(parking.getParkingId());
            //修改对应车场
            String[] parkingName = request.getParameterValues("parkingList");
            if (parkingName != null && parkingName.length > 0) {
                for (int i = 0; i < parkingName.length; i++) {
                    ParkingRelevance parkingRelevance = new ParkingRelevance();
                    parkingRelevance.setCorrespondingId(parkingName[i]);
                    parkingRelevance.setParkingId(parking.getParkingId());
                    parkingRelevanceService.add(parkingRelevance);
                }
            }
          //添加关联价格
           // discountParkingPrice.setParkingId(parking.getParkingId());
            if(discountParkingPrice.getMondayPrice()!=null&&!discountParkingPrice.getMondayPrice().equals("")){
                discountParkingPrice.setMondayPrice(discountParkingPrice.getMondayPrice()*100);
            }
            if(discountParkingPrice.getTuesdayPrice()!=null&&!discountParkingPrice.getTuesdayPrice().equals("")){
                discountParkingPrice.setTuesdayPrice(discountParkingPrice.getTuesdayPrice()*100);
            }
            if(discountParkingPrice.getWednesdayPrice()!=null&&!discountParkingPrice.getWednesdayPrice().equals("")){
                discountParkingPrice.setWednesdayPrice(discountParkingPrice.getWednesdayPrice()*100);
            }
            if(discountParkingPrice.getThursdayPrice()!=null&&!discountParkingPrice.getThursdayPrice().equals("")){
                discountParkingPrice.setThursdayPrice(discountParkingPrice.getThursdayPrice()*100);
            }
            if(discountParkingPrice.getFridayPrice()!=null&&!discountParkingPrice.getFridayPrice().equals("")){
                discountParkingPrice.setFridayPrice(discountParkingPrice.getFridayPrice()*100);
            }
            if(discountParkingPrice.getSaturdayPrice()!=null&&!discountParkingPrice.getSaturdayPrice().equals("")){
                discountParkingPrice.setSaturdayPrice(discountParkingPrice.getSaturdayPrice()*100);
            }
            if(discountParkingPrice.getSundayPrice()!=null&&!discountParkingPrice.getSundayPrice().equals("")){
                discountParkingPrice.setSundayPrice(discountParkingPrice.getSundayPrice()*100);
            }
            //修改对应优惠价格
            DiscountParkingPrice discountParkingPrice2=  discountParkingPriceService.queryById(parking.getParkingId());
            if(discountParkingPrice2!=null&&!discountParkingPrice2.equals("")){
                discountParkingPriceService.update(discountParkingPrice);
            }else {
                discountParkingPriceService.add(discountParkingPrice);
            }
            parkingPriceService.updateParkingAndParkingPrice(parking);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            logger.error("修改信息出现异常", e);
            request.setAttribute("info", "修改信息出现异常");
        }
        return new ModelAndView("products/parking/park_save");
    }

    /**
     * 进入添加页
     */
    @RequestMapping("add.html")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> map = super.getParamMap(request);
    	Parking parking=new Parking();
    	List<Parking> list= parkingService.selectList(parking);
        map.put("list",list);
        return new ModelAndView("products/parking/park_add",map);
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("discountParkingPrice") DiscountParkingPrice discountParkingPrice, @ModelAttribute("parking") Parking parking) {
        try {
        	
            parkingPriceService.saveParkingAndParkingPrice(parking);
            //添加关联
            String[] parkingName= request.getParameterValues("parkingList");
            if(parkingName!=null&&parkingName.length>0){
                for (int i = 0; i < parkingName.length; i++) {
                    ParkingRelevance parkingRelevance=new ParkingRelevance();
                    parkingRelevance.setCorrespondingId(parkingName[i]);
                    parkingRelevance.setParkingId(parking.getParkingId());
                    parkingRelevanceService.add(parkingRelevance);
                }
            }
            //添加关联价格
            discountParkingPrice.setParkingId(parking.getParkingId());
            if(discountParkingPrice.getMondayPrice()!=null&&!discountParkingPrice.getMondayPrice().equals("")){
                discountParkingPrice.setMondayPrice(discountParkingPrice.getMondayPrice()*100);
            }
            if(discountParkingPrice.getTuesdayPrice()!=null&&!discountParkingPrice.getTuesdayPrice().equals("")){
                discountParkingPrice.setTuesdayPrice(discountParkingPrice.getTuesdayPrice()*100);
            }
            if(discountParkingPrice.getWednesdayPrice()!=null&&!discountParkingPrice.getWednesdayPrice().equals("")){
                discountParkingPrice.setWednesdayPrice(discountParkingPrice.getWednesdayPrice()*100);
            }
            if(discountParkingPrice.getThursdayPrice()!=null&&!discountParkingPrice.getThursdayPrice().equals("")){
                discountParkingPrice.setThursdayPrice(discountParkingPrice.getThursdayPrice()*100);
            }
            if(discountParkingPrice.getFridayPrice()!=null&&!discountParkingPrice.getFridayPrice().equals("")){
                discountParkingPrice.setFridayPrice(discountParkingPrice.getFridayPrice()*100);
            }
            if(discountParkingPrice.getSaturdayPrice()!=null&&!discountParkingPrice.getSaturdayPrice().equals("")){
                discountParkingPrice.setSaturdayPrice(discountParkingPrice.getSaturdayPrice()*100);
            }
            if(discountParkingPrice.getSundayPrice()!=null&&!discountParkingPrice.getSundayPrice().equals("")){
                discountParkingPrice.setSundayPrice(discountParkingPrice.getSundayPrice()*100);
            }
            discountParkingPriceService.add(discountParkingPrice);
            request.setAttribute("info", "添加成功");
            return new ModelAndView("products/parking/park_save");
        } catch (Exception e) {
            request.setAttribute("info", "添加失败，请检查参数是否正确");
            logger.error("添加信息出现异常", e);
            return new ModelAndView("products/parking/park_add");
        }
    }
    /**
     * 查询
     */
        @RequestMapping("parkingStatus/list.html")
    public ModelAndView parkingStatuslist(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<ParkingStatus> page = new Page<ParkingStatus>();
            PageHelper.initPage(request, page);
            Map<String, Object> map = super.getParamMap(request);
            //page.getParams().put("hourSection", map.get("hourSection"));
            //page.getParams().put("parkingStatus", map.get("parkingStatus"));
           // if(!StringUtils.isEmpty((String) map.get("parkingName"))){
           //     page.getParams().put("parkingName", map.get("parkingName"));
           // }
            page = parkingStatusService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("进入列表页出现异常", e);
        }
        return new ModelAndView("products/parking/park_status_list");
    }
    /**
     * 车场空位状态编辑页面
     */
    @RequestMapping("parkingStatus/{id}/edit.html")
    public ModelAndView parkingStatusEdit(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
        ParkingStatus ps = parkingStatusService.queryById(Integer.parseInt(id));
        request.setAttribute("parking",ps);
        return new ModelAndView("products/parking/park_status_edit");
    }

    /**
     * 车场空位状态编辑页面
     */
    @RequestMapping("parkingStatus/update.html")
    public ModelAndView parkingStatusUpdate(@ModelAttribute("parkingStatus") ParkingStatus parkingStatus, HttpServletResponse response,HttpServletRequest request) {
        try {
            parkingStatusService.update(parkingStatus);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            request.setAttribute("info", "修改失败");
            logger.error("",e);
        }

        return new ModelAndView("products/parking/park_status_save");
    }

    /**
     * 删除
     */
    @RequestMapping("parkingStatus/{id}/del.html")
    public ModelAndView parkingStatusDel(@PathVariable String id, HttpServletResponse response,HttpServletRequest request) {
        try {
            parkingStatusService.delete(Integer.parseInt(id));
            request.setAttribute("info", "删除成功");
        } catch (Exception e) {
            request.setAttribute("info", "删除失败");
            logger.error("",e);
        }

        return new ModelAndView("products/parking/park_status_save");
    }
    /**
     * 删除
     */
    @RequestMapping("parkingStatus/add.html")
    public ModelAndView parkingStatusAdd(HttpServletResponse response,HttpServletRequest request) {
        return new ModelAndView("products/parking/park_status_add");
    }

    @RequestMapping("parkingStatus/save.html")
    public ModelAndView parkingStatusSave(@ModelAttribute("parkingStatus") ParkingStatus parkingStatus, HttpServletResponse response,HttpServletRequest request) {
        try {
            ParkingStatus pkParam = new ParkingStatus();
            pkParam.setHourSection(parkingStatus.getHourSection());
            pkParam.setParkingId(parkingStatus.getParkingId());
            List<ParkingStatus> pkList = parkingStatusService.selectList(pkParam);
            if(null!= pkList && pkList.size()>0){
                request.setAttribute("info", "记录已存在");
            }else {
                parkingStatus.setCreateor("admin");
                parkingStatus.setCreateDate(new Date());
                parkingStatusService.add(parkingStatus);
                request.setAttribute("info", "添加成功");
            }
        } catch (Exception e) {
            request.setAttribute("info", "添加失败");
            logger.error("",e);
        }

        return new ModelAndView("products/parking/park_status_save");
    }
}
