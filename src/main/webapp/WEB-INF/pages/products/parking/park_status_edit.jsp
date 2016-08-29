<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>修改信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/pccs.js"></script>
		<style type="text/css">

			.editTable td,#mysTable td{
				border: 1px solid dodgerblue;
			}

		</style>
	</head>
	<body>
		<form action="<%=basePath%>products/parking/parkingStatus/update.html" method="post" name="form2" onsubmit="return validator(this)">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请修改车场信息
						<input type="hidden" name="parkingId" value="${parking.parkingId }" readonly="readonly">
						<%--<input type="hidden" name="isUsed" value="${parking.isUsed }" readonly="readonly">--%>
						<%--<input type="hidden" name="createDate" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parking.createDate}" />" readonly="readonly">--%>
						<%--<input type="hidden" name="createor" value="${parking.createor }" readonly="readonly">--%>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车场:</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" disabled="disabled" name="parkingCanUse" value="${parking.parkingName}">
						<input type="text"  style="width: 190px;display:none"  name="id" value="${parking.id}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center">空位状态：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="status" style="width: 140px">
								<option value="0" <c:if test="${parking.status eq '0'}">selected="selected"</c:if>>空</option>
								<option value="1" <c:if test="${parking.status eq '1'}">selected="selected"</c:if>>紧张</option>
						</select>

					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">时间段(24小时制):</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" disabled="disabled"  value="${parking.hourSection}">
						<input type="text"  style="width: 190px;display:none"  name="hourSection" value="${parking.hourSection}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center"></div></td>
					<td colspan="3" bgcolor="#FFFFFF">


					</td>
				</tr>

			</table>
		<%--	<div class="modal" id="mymodal"></div>--%>
		</div>
			<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
			<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
			<script>
				$(function(){
				<%--/*	$("#choosePaking").click(function(){--%>
						 <%--$("#mymodal").load("products/parking/toChooseParkingPage.html?id=${parking.parkingId}").css({"height":"100%","min-height":"100px","max-height":"450","overflowY":"scroll"}).modal("show");--%>
					<%--});*/--%>
					<%--$(document).on('click',"#savePark",function(){--%>
						<%--var parkingId=$("#parkingId").val();--%>
						<%--$("#choosePaking").html("");--%>
						<%--$.each($("#mysTable input:checked"),function(i,obj){--%>
							<%--$("#choosePaking").append("<input type='hidden' name='parkingList' value='"+obj.value+"'/>");--%>
							<%--var parkingList=obj.values()--%>
						<%--});--%>
						<%--$("#mymodal").modal("hide");--%>
				<%--/*		$.ajax({--%>
							<%--type: "POST",--%>
							<%--url: "<%=basePath%>products/parking/updateParking.html",--%>
							<%--data:{"parkingId":parkingId,"parkingList":parkingList},--%>
							<%--dataType: "json",--%>
							<%--success: function (jsonStr) {--%>

							<%--}--%>
						<%--})*/--%>

					<%--});--%>
				/*	$(document).on('click',"#selectPark",function(){
						$.post("products/parking/get.html",{parkName:$("#p_queryValue").val()},function(data){
							$("#mysTable tr:gt(0)").remove();
							$.each(eval('('+data+')'),function(i,obj){
								var uu="<tr> <td height='22' style='width: 3%'><div align='center'><span class='STYLE1'>"+(i+1)+"</span></div></td> <td height='22' style='width: 8%'><div align='center'><span class='STYLE1'>"+obj.parkingName+"</span></div></td> <td height='22' style='width: 5%'><div align='center'><input type='checkbox'";
							//	var ui= " value='"+obj.parking_id+"' /></div></td> </tr>"
                                *//*  if(obj.parkingId!=null&&obj.parkingId!=''){
									uu += "checked = 'checked'";
								  }*//*
								uu +=  "value='"+obj.parkingId+"' /></div></td> </tr>"
								$("#mysTable").append(uu);
							});
						});
					});*/
				});
				$(function(){
					$(".btn").click(function(){
						$("#mymodal").modal("toggle");
						$("#mymodal").css({"height":"80%","overflow":"auto"});
					});
				});
				function selectPark(){

				}
			</script>
			<div class="modal" id="mymodal">
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
												<td class="STYLE4">&nbsp;&nbsp;<input  type="button" onclick="selectDesc()" value="查询"  id="selectPark" style="width:50px"/></td>
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

											<c:forEach items="${ listParking}" var="parking" varStatus="i">
												<tr>
													<td height="22" style="width: 3%"><div align="center"><span class="STYLE1">${i.index + 1 }</span></div></td>
													<td height="22" style="width: 8%"><div align="center"><span class="STYLE1 desc">${parking.parking_name }</span></div></td>
													<td height="22" style="width: 5%"><div align="center"><input type="checkbox" <c:if test="${parking.parkingId!=null&&parking.parkingId!='' }">checked="checked" </c:if> name="parkingList" value="${parking.parking_id }" /></div></td>
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
							<button type="button" id="savePark" style="width: 50px;margin-top: -8px;"  data-dismiss="modal">确定</button>
							<button type="button" style="width: 50px;margin-top: -8px;margin-right: 10px;" data-dismiss="modal">关闭</button>
						</td>
					</tr>
				</table>
			</div>
				</div>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>
					<td width="17%"><input type="submit" value="提交"></td>
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="43%"></td>
				</tr>
			</table>
		</form>
		<script language="javascript" type="text/javascript">
			setup();
			$("#province").val($("#province_hidden").val());
			change(1);
			$("#city").val($("#city_hidden").val());
			change(2);
			$("#county").val($("#county_hidden").val());


			function selectDesc() {
				var desc = $("#p_queryValue").val();
				$(".desc").each(function () {
					var $tr = $(this).parent().parent().parent();
					if (desc == null || desc.length == 0 ) {
						$tr.show();
					} else if ($(this).text().indexOf(desc) == -1) {
						$tr.hide();
					} else {
						$tr.show();
					}
				});
			}
		</script>
	</body>
</html>
