package com.boxiang.share.product.car.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.car.po.CarModel;
import com.boxiang.share.product.car.service.CarModelService;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products/carModel")
public class CarModelController {

    private static final Logger log = Logger.getLogger(CarModelController.class);

    @Resource
    private CarModelService carModelService;

    /**
     * 顶级节点列表
     */
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            CarModel queryModel = new CarModel();
            queryModel.setIsUsed(Constants.TRUE);
            queryModel.setParentCode("0");
            List<CarModel> list = carModelService.selectList(queryModel);
            String carList = JsonMapper.toJson(list, false);
            request.setAttribute("carList", carList);
        } catch (Exception e) {
            log.error("异常", e);
        }
        return new ModelAndView("products/car/car_model_list");
    }

    /**
     * 拿到当前节点下的子节点
     */
    @RequestMapping("/listWithParentCode.html")
    public void listWithParentCode(@RequestParam String parentCode,
                                   HttpServletResponse response) throws IOException {
        String carList = null;
        try {
            CarModel queryModel = new CarModel();
            queryModel.setIsUsed(Constants.TRUE);
            queryModel.setParentCode(parentCode);
            List<CarModel> list = carModelService.selectList(queryModel);
            carList = JsonMapper.toJson(list, false);
        } catch (Exception e) {
            log.error("异常", e);
        }
        response.getWriter().print(carList);
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/del.html")
    public void del(@PathVariable int id, HttpServletResponse response) throws IOException {
        String msg;
        try {
            CarModel carModel = carModelService.queryById(id);
            carModel.setIsUsed(Constants.FALSE);
            carModelService.update(carModel);
            msg = "0";
        } catch (Exception e) {
            log.error("异常", e);
            msg = "1";
        }
        response.getWriter().print(msg);
    }

    /**
     * 修改名称
     */
    @RequestMapping("{id}/edit.html")
    public void edit(@PathVariable int id, @RequestParam String nodeName, HttpServletResponse response) throws IOException {
        String msg;
        try {
            CarModel carModel = carModelService.queryById(id);
            carModel.setNodeName(nodeName);
            carModelService.update(carModel);
            msg = "0";
        } catch (Exception e) {
            log.error("异常", e);
            msg = "1";
        }
        response.getWriter().print(msg);
    }

    /**
     * 添加
     */
    @RequestMapping("{parentId}/add.html")
    public void add(@PathVariable int parentId, @RequestParam String addedNodeName, HttpServletResponse response) throws IOException {
        String msg;
        try {
            String[] nameArray = addedNodeName.split("=");
            List<CarModel> list = new ArrayList<>(nameArray.length);
            for (String name : nameArray) {
                CarModel parentNode = carModelService.queryById(parentId);
                Map<String, String> map = carModelService.selectMax(parentNode.getNodeCode());
                CarModel carModel = new CarModel();
                carModel.setNodeName(name);
                carModel.setIsUsed(Constants.TRUE);
                carModel.setParentCode(parentNode.getNodeCode());
                carModel.setLevels(Integer.toString(Integer.parseInt(parentNode.getLevels()) + 1));
                carModel.setIsLeaf(parentNode.getIsLeaf().equals("0") ? "1" : "0");
                if (map != null && map.size() > 0) {
                    carModel.setNodeCode(Long.toString(Long.valueOf(map.get("maxCode")) + 1));
                    carModel.setSort(Integer.toString(Integer.valueOf(map.get("maxSort")) + 1));
                } else {
                    carModel.setSort("1");
                    Long parentNodeCode = Long.valueOf(parentNode.getNodeCode());
                    carModel.setNodeCode(Long.toString(parentNodeCode * 1000L + 1L));
                }
                carModelService.add(carModel);
                list.add(carModel);
            }
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
        } catch (Exception e) {
            log.error("异常", e);
            msg = ShangAnMessageType.operateToJson("1", "error");
        }
        response.getWriter().print(msg);
    }

}