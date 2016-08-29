package com.boxiang.share.product.coupon.controller;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.customer.po.CustomerInfo;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.utils.DateUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Number;

public class ExportExcel {
	/*************************************************************************** 
	  * @param fileName EXCEL文件名称 
	  * @param Title EXCEL文件第一行列标题集合
	  * @param listContent EXCEL文件正文数据集合 
	  * @return 
	  */  
	public final static String exportCustomerInfo(String fileName,String[] Title,List<CustomerInfo> listContent,HttpServletResponse response){
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
		   for(Object obj:listContent){  
		       fields=obj.getClass().getDeclaredFields();  
		       int j=0;  
		       for(Field v:fields){  
		           v.setAccessible(true);  
		           Object va=v.get(obj);  
		           if(va==null){  
		               va="";  
		           }else{
		        	   if(j==3 && va.toString().equals("0000-00-00 00:00:00")){
		        		   va ="";
		        	   }
		        	   if(j==4){
						   va = DateUtil.date2str((Date) va, DateUtil.DATETIME_FORMAT);
		        	   }
		        	   if(j==5 && !va.toString().equals("0")){
		        		   va = (Integer.parseInt(va.toString())/100)+"";
		        	   }
		           }
				   if (j == 5) {
					   sheet.addCell(new Number(j, i, Double.valueOf(va.toString()), wcf_left));
				   } else {
					   sheet.addCell(new Label(j, i,va.toString(),wcf_left));
				   }
		           j++;
		           if(j>9){
		        	   break;
		           }
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
	 //导出优惠券
	 public  final static String exportYouhuiExcel(String fileName,String[] Title, List<Coupon
			 > listContent,HttpServletResponse response) {  
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
	   for(Object obj:listContent){  
	       fields=obj.getClass().getDeclaredFields();  
	       int j=0;  
	       for(Field v:fields){  
	           v.setAccessible(true);  
	           Object va=v.get(obj);  
	           if(va==null){  
	               va="";  
	           }
	           if(j==2&&va.equals("1")){
	        	   va = "停车券";
	           }else if(j==2 && va.equals("0")){
	        	   va ="通用券";
	           }else if(j==2 && va.equals("2")){
	        	   va ="月租产权券";
	           }else if(j==2 && va.equals("3")){
	        	   va ="代泊券";
	           }else if(j==2 && va.equals("4")){
	        	   va="车管家券";
	           }else if(j==2 && va.equals("5")){
				   va="洗车券";
			   }
	          
	           if(j==3&&va.equals("1")){
	        	   va="注册";
	           }else if(j==3&&va.equals("2")){
	        	   va="交易";
	           }else if(j==3&&va.equals("3")){
	        	   va="活动";
	           }else if(j==3&&va.equals("4")){
	        	   va="分享";
	           }
	           sheet.addCell(new Label(j, i,va.toString(),wcf_left));  
	           j++;  
	           if(j>12){
	        	   break;
	           }
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
	   result="系统提示：Excel文件导出失败，原因："+ e.toString();  
	   System.out.println(result);   
	   e.printStackTrace();  
	  }  
	  return result;  
	 }
	 
	 /** 导出渠道*/
	 public  final static String exportQDExcel(String fileName,String[] Title, List<Customer
			 > listContent,HttpServletResponse response) {  
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
	   for(Object obj:listContent){  
	       fields=obj.getClass().getDeclaredFields();  
	       int j=0;  
	       for(Field v:fields){  
	           v.setAccessible(true);  
	           Object va=v.get(obj);
	           sheet.addCell(new Label(j, i,va.toString(),wcf_left));  
	           j++; 
	           if(j>2){
	        	   break;
	           }
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
