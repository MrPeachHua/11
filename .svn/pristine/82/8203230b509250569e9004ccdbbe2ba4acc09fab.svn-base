<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>center</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<style type="text/css">
	#div1{
	
		width:100%;
		height:80%;
	
	}
	#div2{
	
		width:100%;
		height:80%;
	
	}

</style>

  </head>
  
  <body>
    <form action="<%=basePath%>desktop/condition.do" method="post"  >
	<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr><td class=editHeaderTd >  关怀提醒</td><td class=editHeaderTd >  联系提醒</td></tr>	
	<tr>
    	<td width="50%" bgcolor="#FFFDF0" valign="top">
		<div align="center" id="div1">
		<table  class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
			<tr>
				<td bgcolor="#FFFDF0"  colspan="2">
					<div align="center">查询方式：
					<select name="addTime" style=" width: 145px">
						<option <s:if test='addTime == 0 '>selected="selected"</s:if> value="0">当天</option>
						<option <s:if test='addTime == 7 '>selected="selected"</s:if> value="7">一周内</option>
						<option <s:if test='addTime == 15 '>selected="selected"</s:if> value="15">半月内</option>
						<option <s:if test='addTime == 30 '>selected="selected"</s:if> value="30">一月内</option>
					</select>				
					</div>
				</td>
				<td colspan="2" bgcolor="#FFFFFF"><div align="center"><input style=" width: 45px" type="submit"   value="查询"></div></td>
   			</tr>
   			 <tr>
 				 <td bgcolor="#FFFDF0"><div align="center">关怀主题</div></td>
	 			 <td bgcolor="#FFFDF0"><div align="center">关怀时间</div></td>
	 			 <td bgcolor="#FFFDF0"><div align="center">关怀对象</div></td>
			 </tr>
	
			<s:if test="customerCares.size>0">
			<s:iterator value="customerCares" >
			<tr>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="careTheme"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:date name="careNexttime" format="yyyy-MM-dd HH:mm:ss" /></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="customerName"/></div></td>
			</tr>			
			</s:iterator>
			</s:if>
			<s:else>
    		<tr>
				<td height="20" bgcolor="#FFFFFF" colspan="22"  align="center">
					<div align="center"><span class="STYLE1">没有要关怀的对象！</span></div>
				</td>
			</tr>
			</s:else> 
          			
   		 </table>
		</div>
		</td>
    	<td bgcolor="#FFFDF0" valign="top">
		<div align="center" id="div2">
		<table  class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
			<tr>
				<td bgcolor="#FFFDF0"  colspan="3">
					<div align="center">查询方式：
						<select name="addTime1" style=" width: 145px">
						<option <s:if test='addTime1 == 0 '>selected="selected"</s:if> value="0">当天</option>
						<option <s:if test='addTime1 == 7 '>selected="selected"</s:if> value="7">一周内</option>
						<option <s:if test='addTime1 == 15 '>selected="selected"</s:if> value="15">半月内</option>
						<option <s:if test='addTime1 == 30 '>selected="selected"</s:if> value="30">一月内</option>
						</select>				
					</div>
				</td>
				<td colspan="3" bgcolor="#FFFFFF"><div align="center"><input style=" width: 45px" type="submit"   value="查询"></div></td>
    		</tr>
    		<tr>
	 			<td bgcolor="#FFFDF0"><div align="center">联系主题</div></td>
		 		<td bgcolor="#FFFDF0"><div align="center">联系方式</div></td>
		 		<td bgcolor="#FFFDF0"><div align="center">应联系时间</div></td>
		 	   	<td bgcolor="#FFFDF0"><div align="center">联系对象</div></td>
   			</tr>
			<s:if test="customerLinkrecords.size>0">
				<s:iterator value="customerLinkrecords">
    		<tr><s:property value="careTheme"/>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="linkTheme"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="linkType"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="linkNexttime"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="customerName"/></div></td>
   	 		</tr>
				</s:iterator>
			</s:if>
			<s:else>
    		<tr>
				<td height="20" bgcolor="#FFFFFF" colspan="22"  align="center">
					<div align="center"><span class="STYLE1">没有要联系的对象！</span></div>
				</td>
			</tr>
			</s:else>
   		 </table>
		</div>
		</td>
    </tr>
    <tr class=editHeaderTr><td class=editHeaderTd >  有效公告</td><td class=editHeaderTd >  生日提醒</td></tr>	
	<tr>
    	<td width="50%" bgcolor="#FFFDF0" valign="top">
		<div align="center" id="div1">
		<table  class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>			
   			<tr>
 				<td bgcolor="#FFFDF0"><div align="center">公告主题</div></td>
	 			<td bgcolor="#FFFDF0"><div align="center">公告内容</div></td>
	 			<td bgcolor="#FFFDF0"><div align="center">截止时间</div></td>
	 			<td bgcolor="#FFFDF0"><div align="center">公告人</div></td>
  			 </tr>
			<s:if test="noticeInfos.size>0">
				<s:iterator value="noticeInfos" >
			<tr>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="noticeItem"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="noticeContent"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:date name="noticeEndtime" format="yyyy-MM-dd HH:mm:ss" /></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="userName"/></div></td>
			</tr>
				</s:iterator>
			</s:if>
    		<s:else>
     		<tr>
				<td height="20" bgcolor="#FFFFFF" colspan="22"  align="center">
					<div align="center"><span class="STYLE1">没有有效的公告！</span></div>
				</td>
			</tr>
    		</s:else>          			
   		 </table>
		</div>
		</td>
    	<td bgcolor="#FFFDF0" valign="top">
		<div align="center" id="div2">
		<table  class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
			<tr>
				<td bgcolor="#FFFDF0"  colspan="3">
					<div align="center">查询方式：
					<select name="addTime2" style=" width: 145px">
						<option <s:if test='addTime2 == 0 '>selected="selected"</s:if> value="0">当天</option>
						<option <s:if test='addTime2 == 7 '>selected="selected"</s:if> value="7">一周内</option>
						<option <s:if test='addTime2 == 15 '>selected="selected"</s:if> value="15">半月内</option>
						<option <s:if test='addTime2 == 30 '>selected="selected"</s:if> value="30">一月内</option>
					</select>				
					</div>
				</td>
				<td colspan="3" bgcolor="#FFFFFF"><div align="center"><input style=" width: 45px" type="submit"   value="查询"></div></td>
    		</tr>
    		<tr>
	 			<td bgcolor="#FFFDF0"><div align="center">过生的人</div></td>
		 		<td bgcolor="#FFFDF0"><div align="center">生日时间</div></td>
		 		<td bgcolor="#FFFDF0"><div align="center">手机号码</div></td>
		 	   	<td bgcolor="#FFFDF0"><div align="center">客户状态</div></td>
   			 </tr>
			<s:if test="customerInfos.size>0">
				<s:iterator value="customerInfos">
    		<tr>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="customerName"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:date name="birthDay" format="yyyy-MM-dd" /></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="customerMobile"/></div></td>
				<td colspan="1" bgcolor="#FFFFFF"><div align="center"><s:property value="conditionName"/></div></td>
   	 		</tr>
				</s:iterator>
			</s:if>
			<s:else>
    		<tr>
				<td height="20" bgcolor="#FFFFFF" colspan="22"  align="center">
					<div align="center"><span class="STYLE1">没有要过生日的人！</span></div>
				</td>
			</tr>
			</s:else>
   		 </table>
		</div>
		</td>
    </tr>
  </table>
  </form>
  </body>
</html>
