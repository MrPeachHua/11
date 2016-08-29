<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal-dialog" style="width:700px;">

		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background: white;">
			<tr>
		   		<td height="30" background="images/tab_05.gif">
		   	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="12" height="30"><img src="images/tab_03.gif" width="12" height="30" /></td>
		        <td>
		        	<table width="100%" cellspacing="0" cellpadding="0" height="30">
				      <tr>
				        <td class="STYLE4" align="center">&nbsp;&nbsp;请输入查询内容：<input type="text" id="p_queryValue" name="p_queryValue" style="width: 290px"/></td>
				        <td class="STYLE4">&nbsp;&nbsp;请选择查询方式：
				        	<select id="p_queryType" name="p_queryType" style="width: 100px">
				  				<option  value="1">车场名称</option>
							</select>            
						</td>
				        <td class="STYLE4">&nbsp;&nbsp;<input  type="button" value="查询" onclick="selectDesc()"  id="selectPark" style="width:50px"/></td>
				      </tr>
				    </table>
				</td>
		        <td width="16"><img src="images/tab_07.gif" width="16" height="30" /></td>
		      </tr>
		   	</table>
		   	</td>
		  </tr>
		
		  <tr>
		    <td>
		    <table width="100%" height="100" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="8" background="images/tab_12.gif">&nbsp;</td>
		        <td>
		          <table width="100%" border="0" id="mysTable" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
		          <tr>
		            <td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">序号</span></div></td>
		            <td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">车场名称</span></div></td>
			    	<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">选择</span></div></td>
		          </tr>
		          
		          <c:forEach items="${ parkings}" var="parking" varStatus="i">
					<tr>
						<td height="22" style="width: 3%"><div align="center"><span class="STYLE1">${i.index + 1 }</span></div></td>
			            <td height="22" style="width: 8%"><div align="center"><span class="STYLE1 desc">${parking.parking_name }</span></div></td>
				    	<td height="22" style="width: 5%"><div align="center"><input type="checkbox" <c:if test="${parking.parkingId!=null&&parking.parkingId!='' }">checked="checked" </c:if> value="${parking.parking_id }" /></div></td>
					</tr>
		          </c:forEach>
					
		          </table>
		        </td>
		        <td width="8" background="images/tab_15.gif">&nbsp;</td>
		      </tr>
		    </table>
		    </td>
		  </tr>
		  <tr>
		    <td height="32" background="images/tab_19.gif" style="text-align: right;">
	    		<button type="button" id="savePark" style="width: 50px;margin-top: -8px;">确定</button>
	    		<button type="button" style="width: 50px;margin-top: -8px;margin-right: 10px;" data-dismiss="modal">关闭</button>
		    </td>
		  </tr>
		</table>
</div>