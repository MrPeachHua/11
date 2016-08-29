package com.boxiang.share.product.parking.controller;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.boxiang.share.system.synwhite.po.Specialparkinginfo;
import com.boxiang.share.utils.DateUtil;

public class ExportSpecialExcel {
	/*************************************************************************** 
	  * @param fileName EXCEL文件名称 
	  * @param listTitle EXCEL文件第一行列标题集合 
	  * @param monthlyList EXCEL文件正文数据集合 
	  * @return 
	  */  
	 //导出优惠券
	 public  final static String exportSpecialExcel(String fileName,String[] Title, List<Specialparkinginfo> monthlyList,HttpServletResponse response) {  
	  String result="系统提示：Excel文件导出成功！";    
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
	   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
	  
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
	   sheet.addCell(new Label(0, 0, "代泊员订单报表", wcf_center));  
	   /** ***************以下是EXCEL第一行列标题********************* */  
	   for (int i = 0; i < Title.length; i++) {  
	    sheet.addCell(new Label(i, 0,Title[i],wcf_center));  
	   }     
	   /** ***************以下是EXCEL正文数据********************* */  
	   Field[] fields=null;  
	   int i=1;  
	   for(Object obj:monthlyList){  
	       fields=obj.getClass().getDeclaredFields();  
	       int j=0;  
	       for(Field v:fields){  
	    	   if(v.getName().equals("serialVersionUID")||v.getName().equals("villageId")) continue;
	           v.setAccessible(true);  
	           Object va=v.get(obj);
	           if(va==null){  
	               va="";  
	           }
	           if(j==6&&va.equals(1)){
	        	   va = "黑";
	           }else if(j==6&&va.equals(2)){
	        	   va ="白";
	           }else if(j==6&&va.equals(3)){
	        	   va ="其他";
	           } 
	           if(j==8&&va.equals("0")){
	        	   va="放行";
	           }else if(j==8&&va.equals("1")){
	        	   va="不允许进入";
	           } 
	           if(j==9&&va.equals("0")){
	        	   va="有效";
	           }else if(j==9&&va.equals("1")){
	        	   va="无效";
	           }  
	           if(j==10&&va!=null&&!va.equals("")){
	        	   va = DateUtil.date2str((Date)va, DateUtil.DATE_FORMAT);
	           }
	           if(j==11&&va!=null&&!va.equals("")){
	        	   va = DateUtil.date2str((Date)va, DateUtil.DATE_FORMAT);
	           }
	           if(j==12&&va!=null&&!va.equals("")){
	        	   va = DateUtil.date2str((Date)va, DateUtil.DATE_FORMAT);
	           }
			   if(j==13&&va.equals("0")){
				   va="是";
			   }else if(j==13&&va.equals("1")){
				   va="否";
			   }
			   if(j==7&&va.equals("0")){
				   va="无效";
			   }else if(j==7&&va.equals("1")){
				   va="有效";
			   }
			   if(j<Title.length){
				   sheet.addCell(new Label(j, i,va.toString(),wcf_left));
			   }
	           j++;  
	       }  
	       i++;  
	   }  
 
	   /** **********将以上缓存中的内容写到EXCEL文件中******** */  
	   workbook.write();  
	   /** *********关闭文件************* */  
	   workbook.close();     
	  
	  } catch (Exception e) {  
	   result="系统提示：Excel文件导出失败，原因："+ e.toString();  
	   System.out.println(result);   
	   e.printStackTrace();  
	  }  
	  return result;  
	 }
}
