package com.boxiang.share.product.parking.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.parking.po.PackagePrice;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.PackagePriceService;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.system.po.SysUsers;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by kaiser on 2016/5/20.
 */
@Controller
@RequestMapping("products/price")
public class PackagePriceController extends BaseController {
    private static final Logger logger = Logger.getLogger(PackagePriceController.class);

    @Resource
    private PackagePriceService packagePriceService;
    @Resource
    private ParkingService parkingService;
    /**
     * 查询
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<PackagePrice> page = new Page<PackagePrice>();
            PageHelper.initPage(request, page);
            Map<String, Object> map = super.getParamMap(request);
            page = packagePriceService.queryListPage(page);
            for (PackagePrice packagePrice :page.getResultList()){
                Parking parking=parkingService.queryById(packagePrice.getParkingId());
                if(parking!=null&&!parking.equals("")){
                    packagePrice.setParkingName(parking.getParkingName());
                }
            }
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("进入列表页出现异常", e);
        }
        return new ModelAndView("products/parking/park_package_list");
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/del.html")
    public void del(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        String js;
        try {
            //删除关联价格表
            packagePriceService.delete(id);
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            logger.error("删除信息出现异常", e);
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public String save(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("packagePrice") PackagePrice packagePrice) throws IOException{
        String js = null;
        try {
            PackagePrice queryEntity = new PackagePrice();
            queryEntity.setParkingId(packagePrice.getParkingId());
            queryEntity.setWeek(packagePrice.getWeek());
            queryEntity.setWeek1(packagePrice.getWeek1());
            List<?> list = packagePriceService.selectList(queryEntity);
            if (list != null && list.size() > 0) {
                request.setAttribute("msg", "添加失败，已存在相同的套餐");
                request.setAttribute("packagePrice", packagePrice);
                return "products/parking/park_package_add";
            }
            if (packagePrice.getPrice() != null) {
                packagePrice.setPrice(packagePrice.getPrice() * 100);
            }
            packagePriceService.add(packagePrice);
            js = "<script>alert('添加成功');location.href='list.html';</script>";
        } catch (Exception e) {
            js = "<script>alert('添加失败');location.href='list.html';</script>";
            logger.error("添加信息出现异常", e);
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
        return null;
    }
    /**
     * 添加
     */
    @RequestMapping("update.html")
    public String update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("packagePrice") PackagePrice packagePrice) throws IOException{
        String js;
        try {
            if (packagePrice.getPrice() != null) {
                packagePrice.setPrice(packagePrice.getPrice() * 100);
            }
            PackagePrice queryEntity = new PackagePrice();
            queryEntity.setId(packagePrice.getId());
            queryEntity.setParkingId(packagePrice.getParkingId());
            queryEntity.setWeek(packagePrice.getWeek());
            queryEntity.setWeek1(packagePrice.getWeek1());
            List<PackagePrice> list = packagePriceService.selectList(queryEntity);
            if (list != null && list.size() > 0) {
                request.setAttribute("msg", "修改失败，已存在相同的套餐");
                request.setAttribute("packagePrice", packagePrice);
                return "products/parking/park_package_edit";
            }
            packagePriceService.update(packagePrice);
            js = "<script>alert('修改成功');location.href='list.html';</script>";
        } catch (Exception e) {
            js = "<script>alert('修改失败');location.href='list.html';</script>";
            logger.error("添加信息出现异常", e);
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
        return null;
    }

    //跳转添加
    @RequestMapping("add.html")
    public ModelAndView msgadd(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("products/parking/park_package_add");
    }
    //跳转编辑
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) {
        if(id==null){
            throw new NullPointerException("ID不能为空!");
        }
        Map<String,Object> map = super.getParamMap(request);
         PackagePrice packagePrice=   packagePriceService.queryById(id);
         Parking parking=parkingService.queryById(packagePrice.getParkingId());
        packagePrice.setParkingName(parking.getParkingName());
        request.setAttribute("packagePrice",packagePrice);
        return new ModelAndView("products/parking/park_package_edit",map);
    }


}












