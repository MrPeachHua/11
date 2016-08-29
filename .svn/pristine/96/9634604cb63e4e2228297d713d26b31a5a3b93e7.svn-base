package com.boxiang.share.product.villageowner.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.order.controller.ExportOrderExcel;
import com.boxiang.share.product.villageowner.po.VillageOwner;
import com.boxiang.share.product.villageowner.service.VillageOwnerService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("products/villageowner")
public class VillageOwnerController extends BaseController {

    private static final Logger logger = Logger.getLogger(VillageOwnerController.class);

    @Resource
    private VillageOwnerService villageOwnerService;

    /**
     * 查询列表
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            Page<Object> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            page.setResultList(villageOwnerService.queryListPageV2(page));
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/villageOwner/village_owner_list");
    }

    /**
     * 导出excel文档
     */
    @RequestMapping("excelList.html")
    public void excelRecharge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> map = new HashMap<>();
            map.put("params", super.getParamMap(request));
            final List<Object> list = villageOwnerService.queryListPageV2(map);
            if (list != null && list.size() > 0) {
                String[][] titleArray = {
                        {"小区", "parkingName"},
                        {"业主姓名", "name"},
                        {"手机号", "mobile"}
                };
                ExportOrderExcel.exportExcel(titleArray, list, response);
            } else {
                String js = "<script>alert('没有需要导出的内容');history.back();</script>";
                response.getWriter().print(js);
            }
        } catch (Exception e) {
            logger.error("异常", e);
        }
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/delete.html")
    public void delete(@PathVariable int id, HttpServletResponse response) throws IOException {
        String js;
        try {
            villageOwnerService.delete(id);
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            logger.error("异常", e);
            js = "<script>alert('异常');location.href='../list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    /**
     * 前往新增页面
     */
    @RequestMapping("/add.html")
    public ModelAndView add() {
        return new ModelAndView("products/villageOwner/village_owner_add");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public String save(HttpServletRequest request,
                       HttpServletResponse response,
                       @ModelAttribute("couponRule") VillageOwner villageOwner) {
        try {
            villageOwner.setIsUsed(Constants.TRUE);
            villageOwner.setCreateDate(new Date());
            villageOwner.setCreateor("admin");
            villageOwnerService.add(villageOwner);
            String js = "<script>alert('添加成功');location.href='list.html';</script>";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(js);
            return null;
        } catch (DuplicateKeyException e) {
            request.setAttribute("info", "已存在相同的手机号码和车场");
            return "products/villageOwner/village_owner_add";
        } catch (Exception e) {
            request.setAttribute("info", "异常，请检查参数是否填写正确");
            logger.error("异常", e);
            return "products/villageOwner/village_owner_add";
        }
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable int id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(1);
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        map.put("params", params);
        List<Object> list = villageOwnerService.queryListPageV2(map);
        request.setAttribute("entity", list.get(0));
        return new ModelAndView("products/villageOwner/village_owner_edit");
    }

    /**
     * 修改
     */
    @RequestMapping("/update.html")
    public String update(@ModelAttribute("villageOwner") VillageOwner villageOwner, HttpServletResponse response) throws IOException {
        try {
            villageOwner.setIsUsed(Constants.TRUE);
            villageOwnerService.update(villageOwner);
            return "redirect:list.html";
        } catch (Exception e) {
            logger.error("异常", e);
            String js = "<script>alert('异常');location.href='" + villageOwner.getId() + "/edit.html';</script>";
            response.getWriter().print(js);
            return null;
        }
    }

    /**
     * 导入excel文档
     */
    @RequestMapping("uploadExcel.html")
    public void uploadExcel(@RequestParam MultipartFile importFile,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String msg;
        try {
            // 保存文件
            String name = importFile.getOriginalFilename();
            String savePath = request.getSession().getServletContext().getRealPath("/uploadFiles/excel") + "/";
            String saveName = DateUtil.date2str(new Date(), "YYYYMMddHHmmss") + "_" + (int) (Math.random() * 10000) + "_" + name;
            String fullName = savePath + saveName;
            FileUtils.copyInputStreamToFile(importFile.getInputStream(), new File(fullName));

            // 读取Excel
            String extension = name.substring(name.lastIndexOf('.') + 1).toLowerCase();
            Workbook wb = null;
            if (extension.equals("xls")) {
                wb = new HSSFWorkbook(importFile.getInputStream());
            } else if (extension.equals("xlsx")) {
                wb = new XSSFWorkbook(importFile.getInputStream());
            }
            Sheet sheet = wb.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();

            // EXCEL转Model
            List<VillageOwner> list = new ArrayList<>(rowCount);
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                VillageOwner item = new VillageOwner();
                item.setIsUsed(Constants.TRUE);
                item.setCreateDate(new Date());
                item.setCreateor("admin");
                item.setId(i + 1);
                Cell parkingNameCell = row.getCell(0);
                if (parkingNameCell != null) {
                    parkingNameCell.setCellType(Cell.CELL_TYPE_STRING);
                    item.setParkingName(parkingNameCell.getStringCellValue().trim());
                }
                Cell nameCell = row.getCell(1);
                if (nameCell != null) {
                    nameCell.setCellType(Cell.CELL_TYPE_STRING);
                    item.setName(row.getCell(1).toString().trim());
                }
                Cell mobileCell = row.getCell(2);
                if (mobileCell != null) {
                    mobileCell.setCellType(Cell.CELL_TYPE_STRING);
                    item.setMobile(mobileCell.getStringCellValue().trim());
                }
                if (StringUtils.isNotEmpty(item.getParkingName()) ||
                        StringUtils.isNotEmpty(item.getName()) ||
                        StringUtils.isNotEmpty(item.getMobile())) {
                    list.add(item);
                }
            }

            // 插入数据库
            List<VillageOwner> errorList = villageOwnerService.batchInsert(list);
            if (errorList.size() == 0) { // 没有错误日志
                msg = ShangAnMessageType.operateToJson("0", "success");
            } else {
                // 写错误日志
                String logSavePath = fullName + ".txt";
                BufferedWriter bw = new BufferedWriter(new FileWriter(logSavePath));
                for (VillageOwner item : errorList) {
                    String line = String.format("Excel行号:%s, 小区名称:%s, 姓名:%s, 手机号:%s, 错误原因:%s\r\n",
                            item.getId(),
                            item.getParkingName(),
                            item.getName(),
                            item.getMobile(),
                            item.getErrorInfo());
                    bw.append(line);
                }
                bw.flush();
                bw.close();
                msg = ShangAnMessageType.toShangAnJson("1", "success", "data", saveName + ".txt");
            }
        } catch (Exception e) {
            logger.error("异常", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(msg);
    }

    /**
     * 下载错误日志
     */
    @RequestMapping("logDownload.html")
    public void logDownload(@RequestParam String fileName,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        try {
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            String savePath = request.getSession().getServletContext().getRealPath("/uploadFiles/excel") + "/";
            FileInputStream fis = new FileInputStream(savePath + fileName);
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) != -1) {
                response.getOutputStream().write(buf, 0, len);
            }
            response.getOutputStream().flush();
            fis.close();
        } catch (Exception e) {
            logger.error("异常", e);
            String js = "<script>alert('异常');history.back();</script>";
            response.getWriter().print(js);
        }
    }

}