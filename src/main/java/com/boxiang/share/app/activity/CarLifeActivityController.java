package com.boxiang.share.app.activity;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.activity.po.CarLifeActivity;
import com.boxiang.share.product.activity.service.CarLifeActivityService;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("app/carLifeActivity")
public class CarLifeActivityController extends BaseController {

    private static final Logger logger = Logger.getLogger(CarLifeActivityController.class);

    @Resource
    private CarLifeActivityService carLifeActivityService;

    /**
     * 查询优惠活动用车心得
     *
     * @param parkingId 车场Id
     * @param type      类型: 1.优惠活动 2.用车心得
     */
    @RequestMapping("queryActivity")
    public void queryActivity(String parkingId,
                              String type,
                              @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              Integer pageSize,
                              String version,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String msg;
        try {
            Page<CarLifeActivity> page = new Page<>();
            if (pageSize != null) {
                page.setPageSize(pageSize);
            }
            page.setCurrentPage(pageIndex);
            page.getParams().put("parkingId", parkingId);
            Map<String, List> map = new HashMap<>(type == null ? 2 : 1);
            if (StringUtils.isEmpty(type)) {
                page.getParams().put("type", 1);
                List<CarLifeActivity> list1 = carLifeActivityService.queryListPageV2(page);
                page.getParams().put("type", 2);
                List<CarLifeActivity> list2 = carLifeActivityService.queryListPageV2(page);
                map.put("type1", carLifeActivityService.paramsFilter(list1, request));
                map.put("type2", carLifeActivityService.paramsFilter(list2, request));
            } else {
                page.getParams().put("type", type);
                List<CarLifeActivity> list = carLifeActivityService.queryListPageV2(page);
                map.put("type" + type, carLifeActivityService.paramsFilter(list, request));
            }
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", map);
        } catch (Exception e) {
            logger.error("", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(msg);
    }

}
