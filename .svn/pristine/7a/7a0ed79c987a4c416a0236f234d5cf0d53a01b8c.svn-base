package com.boxiang.share.product.order.controller;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.product.parking.po.CarInOutRecord;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.utils.DateUtil;
import jxl.write.Number;

public class ExportOrderExcel {
    /**
     * ************************************************************************
     *
     * @param fileName    EXCEL文件名称
     *
     * @param listContent EXCEL文件正文数据集合
     * @return
     */
    public final static String exportCarRecordExcel(String fileName, String[] Title, List<CarInOutRecord> listContent, HttpServletResponse response){

        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=new.xls");
            // 设定输出文件头
            response.setContentType("application/xls");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行


            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            sheet.addCell(new Label(0, 0, "车辆进出场报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.length; i++) {
                sheet.addCell(new Label(i, 0, Title[i], wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            Field[] fields = null;
            int i = 1;
            for (Object obj : listContent) {
                if (obj !=null){
                    fields = obj.getClass().getDeclaredFields();
                    int j = 0;
                    for (Field v : fields) {
                        v.setAccessible(true);
                        Object va = v.get(obj);
                        if (va == null) {
                            va = "";
                        }
                        if (j == 2 && va.equals("1")) {
                            va = "入场";
                        } else if (j == 2 && va.equals("2")) {
                            va = "出场";
                        }

                        if (j == 3 && va != null && !va.equals("")) {
                            va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                        }
                        if (j == 4 && va != null && !va.equals("")) {
                            va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                        }
                        if (j==5 && va !=null && !va.equals("")){
                            va = Integer.parseInt(va+"")/100;
                        }
                        if (j==6 && va !=null && !va.equals("")){
                            va = Integer.parseInt(va+"")/100;
                        }
                        sheet.addCell(new Label(j, i, va.toString(), wcf_left));
                        j++;
                        if(j>7)
                            break;
                    }
                    i++;
                }
            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }
    public final static String exportOrderExcel1(String fileName, String[] Title, List<OrderMain
            > listContent, HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=new.xls");
            // 设定输出文件头
            response.setContentType("application/xls");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行


            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            sheet.addCell(new Label(0, 0, "订单报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.length; i++) {
                sheet.addCell(new Label(i, 0, Title[i], wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            Field[] fields = null;
            int i = 1;
            for (Object obj : listContent) {
                fields = obj.getClass().getDeclaredFields();
                int j = 0;
                for (Field v : fields) {
                    v.setAccessible(true);
                    Object va = v.get(obj);
                    if (va == null) {
                        va = "";
                    }
                    if (j == 1 && va.equals("10")) {
                        va = "共享临停";
                    } else if (j == 1 && va.equals("11")) {
                        va = "临停缴费";
                    } else if (j == 1 && va.equals("12")) {
                        va = "代泊";
                    } else if (j == 1 && va.equals("13")) {
                        va = "月租";
                    } else if (j == 1 && va.equals("14")) {
                        va = "产权";
                    } else if (j == 1 && va.equals("15")) {
                        va = "加油卡";
                    }
                    if (j == 2 && va.equals("1")) {
                        va = "已预约";
                    } else if (j == 2 && va.equals("2")) {
                        va = "已接车";
                    } else if (j == 2 && va.equals("3")) {
                        va = "已交车";
                    } else if (j == 2 && va.equals("4")) {
                        va = "已停车";
                    } else if (j == 2 && va.equals("5")) {
                        va = "已完成";
                    } else if (j == 2 && va.equals("6")) {
                        va = "已取车";
                    } else if (j == 2 && va.equals("7")) {
                        va = "已拒绝";
                    } else if (j == 2 && va.equals("8")) {
                        va = "待取车";
                    } else if (j == 2 && va.equals("9")) {
                        va = "提车中";
                    } else if (j == 2 && va.equals("10")) {
                        va = "未付款";
                    } else if (j == 2 && va.equals("11")) {
                        va = "已付款";
                    } else if (j == 2 && va.equals("12")) {
                        va = "已取消";
                    }else if (j == 2 && va.equals("13")) {
                        va = "数据异常";
                    }
                    if (j == 3 && va.equals("0")) {
                        va = "未开具";
                    } else if (j == 3 && va.equals("1")) {
                        va = "已开具";
                    }
                    if (j == 9 && va.equals("00")) {
                        va = "支付宝";
                    } else if (j == 9 && va.equals("01")) {
                        va = "微信";
                    } else if (j == 9 && va.equals("02")) {
                        va = "银联";
                    }else if (j == 9 && va.equals("05")) {
                        va = "钱包";
                    }
                    if (j == 10 && va != null && !va.equals("")) {
                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                    }
                    //567
                    if (j == 6 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 7 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 8 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if(j==14 && va.equals("0")){
                        va = "未使用";
                    }else if(j==14 && va.equals("1")){
                        va = "已使用";
                    }else if(j==14 && va.equals("2")){
                        va = "已失效";
                    }
                    if (va instanceof Date) {
                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                    }
//	           if(j==9){
//	        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        	  String date=sdf.format(va);
//	        	  va=sdf.parse(date);
//
//	           }
                    if(j==6||j==7||j==8){
                        Double num;
                        if (va != null && va.toString().trim().length() != 0) {
                            num = Double.parseDouble(va.toString());
                        } else {
                            num = 0.0;
                        }
                        sheet.addCell(new Number(j, i, num, wcf_left));
                    }else {
                        sheet.addCell(new Label(j, i, va.toString(), wcf_left));
                    }
                    j++;
                    if(j>=15)
                        break;
                }
                i++;
            }
//	   int j=0;
//	   for (Coupon coupon : listContent) {
//		   sheet.addCell(new Label(j, i,coupon.getCoupon_id(),wcf_left));
//		   sheet.addCell(new Label(j, i,coupon.getCustomer_name(),wcf_left));
//		   i++;
//
//	}
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }
    //导出订单
    public final static String exportOrderExcel(String fileName, String[] Title, List<OrderMain
            > listContent, HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=new.xls");
            // 设定输出文件头
            response.setContentType("application/xls");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行


            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            sheet.addCell(new Label(0, 0, "订单报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.length; i++) {
                sheet.addCell(new Label(i, 0, Title[i], wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            Field[] fields = null;
            int i = 1;
            for (Object obj : listContent) {
                fields = obj.getClass().getDeclaredFields();
                int j = 0;
                for (Field v : fields) {
                    v.setAccessible(true);
                    Object va = v.get(obj);
                    if (va == null) {
                        va = "";
                    }
                    if (j == 1 && va.equals("10")) {
                        va = "共享临停";
                    } else if (j == 1 && va.equals("11")) {
                        va = "临停缴费";
                    } else if (j == 1 && va.equals("12")) {
                        va = "代泊";
                    } else if (j == 1 && va.equals("13")) {
                        va = "月租";
                    } else if (j == 1 && va.equals("14")) {
                        va = "产权";
                    } else if (j == 1 && va.equals("15")) {
                        va = "加油卡";
                    }else if (j == 1 && va.equals("16")) {
                        va = "钱包充值";
                    }else if (j == 1 && va.equals("17")) {
                        va = "洗车";
                    }
                    
                    if (j == 2 && va.equals("1")) {
                        va = "已预约";
                    } else if (j == 2 && va.equals("2")) {
                        va = "已接车";
                    } else if (j == 2 && va.equals("3")) {
                        va = "已交车";
                    } else if (j == 2 && va.equals("4")) {
                        va = "已停车";
                    } else if (j == 2 && va.equals("5")) {
                        va = "已完成";
                    } else if (j == 2 && va.equals("6")) {
                        va = "已取车";
                    } else if (j == 2 && va.equals("7")) {
                        va = "已拒绝";
                    } else if (j == 2 && va.equals("8")) {
                        va = "待取车";
                    } else if (j == 2 && va.equals("9")) {
                        va = "提车中";
                    } else if (j == 2 && va.equals("10")) {
                        va = "未付款";
                    } else if (j == 2 && va.equals("11")) {
                        va = "已付款";
                    } else if (j == 2 && va.equals("12")) {
                        va = "已取消";
                    }else if (j == 2 && va.equals("13")) {
                        va = "数据异常";
                    }
                    if (j == 3 && va.equals("0")) {
                        va = "未开具";
                    } else if (j == 3 && va.equals("1")) {
                        va = "已开具";
                    }
                    if (j == 9 && va.equals("00")) {
                        va = "支付宝";
                    } else if (j == 9 && va.equals("01")) {
                        va = "微信";
                    } else if (j == 9 && va.equals("02")) {
                        va = "银联";
                    }
                    else if (j == 9 && va.equals("03")) {
                        va = "线下";
                    }
                    else if (j == 9 && va.equals("05")) {
                        va = "钱包";
                    }
                    if (j == 10 && va != null && !va.equals("")) {
                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                    }
                    //567
                    if (j == 6 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 7 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 8 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (va instanceof Date) {
                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                    }
//	           if(j==9){
//	        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        	  String date=sdf.format(va);
//	        	  va=sdf.parse(date);
//	        	  
//	           }
                    if(j==6||j==7||j==8){
                        Double num;
                        if (va != null && va.toString().trim().length() != 0) {
                            num = Double.parseDouble(va.toString());
                        } else {
                            num = 0.0;
                        }
                        sheet.addCell(new Number(j, i, num, wcf_left));
                    }else {
                        sheet.addCell(new Label(j, i, va.toString(), wcf_left));
                    }
                    j++;
                }
                i++;
            }
//	   int j=0;
//	   for (Coupon coupon : listContent) {
//		   sheet.addCell(new Label(j, i,coupon.getCoupon_id(),wcf_left));
//		   sheet.addCell(new Label(j, i,coupon.getCustomer_name(),wcf_left));
//		   i++;
//		   
//	}
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 导出EXCEL
     * @param titleArray  EXCEL文件第一行列标题集合
     * @param listContent EXCEL文件正文数据集合
     * @return
     */
    public static String exportExcel(String[][] titleArray,
                                     List<?> listContent,
                                     HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=new.xls");
            // 设定输出文件头
            response.setContentType("application/xls");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行

            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            //sheet.addCell(new Label(0, 0, "", wcf_center));

            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < titleArray.length; i++) {
                sheet.addCell(new Label(i, 0, titleArray[i][0], wcf_center));
            }

            /** ***************以下是EXCEL正文数据********************* */
            for (int i = 0; i < listContent.size(); i++) {
                Object obj = listContent.get(i);
                if (obj instanceof Map) {
                    for (int j = 0; j < titleArray.length; j++) {
                        String cellName = titleArray[j][1];
                        Object value = ((Map) obj).get(cellName);
                        sheet.addCell(new Label(j, i + 1, value == null ? "" : value.toString(), wcf_left));
                    }
                } else {
                    for (int j = 0; j < titleArray.length; j++) {
                        String cellName = titleArray[j][1];
                        Field field = findField(obj.getClass(), cellName);
                        Object value = field.get(obj);
                        sheet.addCell(new Label(j, i + 1, value == null ? "" : value.toString(), wcf_left));
                    }
                }
            }

            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }

    private static Field findField(Class clazz, String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = findField(clazz.getSuperclass(), fieldName);
        }
        field.setAccessible(true);
        return field;
    }


    /**
     * 导出EXCEL
     */
    public static String exportExcel(ExcelObject excelObject, HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=new.xls");
            // 设定输出文件头
            response.setContentType("application/xls");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行

            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            //sheet.addCell(new Label(0, 0, "", wcf_center));

            excelObject.dataSource(sheet, wcf_center, wcf_left);

            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }

    public interface ExcelObject {
        public void dataSource(WritableSheet sheet,
                               WritableCellFormat wcf_center,
                               WritableCellFormat wcf_left) throws Exception;
    }
  //导出洗车订单
    public final static String exportOrderCarwashExcel(String fileName, String[] Title, List<OrderMain
            > listContent, HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=new.xls");
            // 设定输出文件头
            response.setContentType("application/xls");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行


            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            sheet.addCell(new Label(0, 0, "订单报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.length; i++) {
                sheet.addCell(new Label(i, 0, Title[i], wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            Field[] fields = null;
            int i = 1;
            for (Object obj : listContent) {
                fields = obj.getClass().getDeclaredFields();
                int j = 0;
                for (Field v : fields) {
                    v.setAccessible(true);
                    Object va = v.get(obj);
                    if (va == null) {
                        va = "";
                    }
                    if (j == 1 && va.equals("10")) {
                        va = "共享临停";
                    } else if (j == 1 && va.equals("11")) {
                        va = "临停缴费";
                    } else if (j == 1 && va.equals("12")) {
                        va = "代泊";
                    } else if (j == 1 && va.equals("13")) {
                        va = "月租";
                    } else if (j == 1 && va.equals("14")) {
                        va = "产权";
                    } else if (j == 1 && va.equals("15")) {
                        va = "加油卡";
                    } else if (j == 1 && va.equals("17")) {
                        va = "洗车";
                    }
                    if (j == 2 && va.equals("1")) {
                        va = "已预约";
                    } else if (j == 2 && va.equals("2")) {
                        va = "已接车";
                    } else if (j == 2 && va.equals("3")) {
                        va = "已交车";
                    } else if (j == 2 && va.equals("4")) {
                        va = "已停车";
                    } else if (j == 2 && va.equals("5")) {
                        va = "已完成";
                    } else if (j == 2 && va.equals("6")) {
                        va = "已取车";
                    } else if (j == 2 && va.equals("7")) {
                        va = "已拒绝";
                    } else if (j == 2 && va.equals("8")) {
                        va = "待取车";
                    } else if (j == 2 && va.equals("9")) {
                        va = "提车中";
                    } else if (j == 2 && va.equals("10")) {
                        va = "未付款";
                    } else if (j == 2 && va.equals("11")) {
                        va = "已付款";
                    } else if (j == 2 && va.equals("12")) {
                        va = "已取消";
                    }else if (j == 2 && va.equals("13")) {
                        va = "数据异常";
                    }
                    if (j == 3 && va.equals("0")) {
                        va = "未开具";
                    } else if (j == 3 && va.equals("1")) {
                        va = "已开具";
                    }
                    if (j == 6 && va.equals("1")) {
                        va = "轿车";
                    } else if (j == 6 && va.equals("2")) {
                        va = "商务车";
                    }
                    if (j == 13 && va.equals("00")) {
                        va = "支付宝";
                    } else if (j == 13 && va.equals("01")) {
                        va = "微信";
                    } else if (j == 13 && va.equals("02")) {
                        va = "银联";
                    }else if (j == 13 && va.equals("05")) {
                        va = "钱包";
                    }
//                    if (j == 4 && va != null && !va.equals("")) {
//                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
//                    }
//                    if (j == 14 && va != null && !va.equals("")) {
//                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
//                    }
                    //567
                    if (j == 10 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 11 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 12 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (va instanceof Date) {
                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                    }
//	           if(j==9){
//	        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        	  String date=sdf.format(va);
//	        	  va=sdf.parse(date);
//	        	  
//	           }
                    if(j==10||j==11||j==12){
                        sheet.addCell(new Number(j, i, Double.parseDouble(va.toString()), wcf_left));
                    }else {
                        sheet.addCell(new Label(j, i, va.toString(), wcf_left));
                    }
                    j++;
                }
                i++;
            }
//	   int j=0;
//	   for (Coupon coupon : listContent) {
//		   sheet.addCell(new Label(j, i,coupon.getCoupon_id(),wcf_left));
//		   sheet.addCell(new Label(j, i,coupon.getCustomer_name(),wcf_left));
//		   i++;
//		   
//	}
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }
    //导出代泊订单
    
    public final static String exportOrderParkExcel(String fileName, String[] Title, List<OrderMain
            > listContent, HttpServletResponse response) {
        String result = "系统提示：Excel文件导出成功！";
        // 以下开始输出到EXCEL
        try {
            //定义输出流，以便打开保存对话框______________________begin
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=new.xls");
            // 设定输出文件头
            response.setContentType("application/xls");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            WritableWorkbook workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);


            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行

            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行


            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            sheet.addCell(new Label(0, 0, "订单报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
            for (int i = 0; i < Title.length; i++) {
                sheet.addCell(new Label(i, 0, Title[i], wcf_center));
            }
            /** ***************以下是EXCEL正文数据********************* */
            Field[] fields = null;
            int i = 1;
            for (Object obj : listContent) {
                fields = obj.getClass().getDeclaredFields();
                int j = 0;
                for (Field v : fields) {
                    v.setAccessible(true);
                    Object va = v.get(obj);
                    if (va == null) {
                        va = "";
                    }
                    if (j == 1 && va.equals("10")) {
                        va = "共享临停";
                    } else if (j == 1 && va.equals("11")) {
                        va = "临停缴费";
                    } else if (j == 1 && va.equals("12")) {
                        va = "代泊";
                    } else if (j == 1 && va.equals("13")) {
                        va = "月租";
                    } else if (j == 1 && va.equals("14")) {
                        va = "产权";
                    } else if (j == 1 && va.equals("15")) {
                        va = "加油卡";
                    }else if (j == 1 && va.equals("16")) {
                        va = "钱包充值";
                    }else if (j == 1 && va.equals("17")) {
                        va = "洗车";
                    }
                    
                    if (j == 2 && va.equals("1")) {
                        va = "已预约";
                    } else if (j == 2 && va.equals("2")) {
                        va = "已接车";
                    } else if (j == 2 && va.equals("3")) {
                        va = "已交车";
                    } else if (j == 2 && va.equals("4")) {
                        va = "已停车";
                    } else if (j == 2 && va.equals("5")) {
                        va = "已完成";
                    } else if (j == 2 && va.equals("6")) {
                        va = "已取车";
                    } else if (j == 2 && va.equals("7")) {
                        va = "已拒绝";
                    } else if (j == 2 && va.equals("8")) {
                        va = "待取车";
                    } else if (j == 2 && va.equals("9")) {
                        va = "提车中";
                    } else if (j == 2 && va.equals("10")) {
                        va = "未付款";
                    } else if (j == 2 && va.equals("11")) {
                        va = "已付款";
                    } else if (j == 2 && va.equals("12")) {
                        va = "已取消";
                    }else if (j == 2 && va.equals("13")) {
                        va = "数据异常";
                    }
                    if (j == 3 && va.equals("0")) {
                        va = "未开具";
                    } else if (j == 3 && va.equals("1")) {
                        va = "已开具";
                    }
                    if (j == 9 && va.equals("00")) {
                        va = "支付宝";
                    } else if (j == 9 && va.equals("01")) {
                        va = "微信";
                    } else if (j == 9 && va.equals("02")) {
                        va = "银联";
                    }
                    else if (j == 9 && va.equals("03")) {
                        va = "线下";
                    }
                    else if (j == 9 && va.equals("05")) {
                        va = "钱包";
                    }
                    if (j == 10 && va != null && !va.equals("")) {
                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                    }
                    //567
                    if (j == 6 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 7 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (j == 8 && va != null && !va.equals("")) {
                        BigDecimal bd = new BigDecimal((int) va);
                        va = bd.divide(new BigDecimal(100));

                    }
                    if (va instanceof Date) {
                        va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
                    }
//	           if(j==9){
//	        	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        	  String date=sdf.format(va);
//	        	  va=sdf.parse(date);
//	        	  
//	           }
                    if(j==6||j==7||j==8){
                        Double num;
                        if (va != null && va.toString().trim().length() != 0) {
                            num = Double.parseDouble(va.toString());
                        } else {
                            num = 0.0;
                        }
                        sheet.addCell(new Number(j, i, num, wcf_left));
                    }else {
                        sheet.addCell(new Label(j, i, va.toString(), wcf_left));
                    }
                    j++;
                }
                i++;
            }
//	   int j=0;
//	   for (Coupon coupon : listContent) {
//		   sheet.addCell(new Label(j, i,coupon.getCoupon_id(),wcf_left));
//		   sheet.addCell(new Label(j, i,coupon.getCustomer_name(),wcf_left));
//		   i++;
//		   
//	}
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();

        } catch (Exception e) {
            result = "系统提示：Excel文件导出失败，原因：" + e.toString();
            System.out.println(result);
            e.printStackTrace();
        }
        return result;
    }
}
