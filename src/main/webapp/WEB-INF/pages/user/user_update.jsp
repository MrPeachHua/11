<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

		<title>修改员工信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
	</head>
	<style type="text/css">
.content {
	display: none;
	position: absolute;
	top: 10%;
	left: 12%;
	width: 80%;
	height: 70%;
	/* border: 1px solid black; */
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
#bg{
background-color:#666;
position:absolute;
z-index:99;
left:0;
top:0;
display:none;
width:100%;
height:100%;
opacity:0.001;
filter: alpha(opacity=50);
}
td{
	border: 1px solid #B1CEEE;

}
/* td { text-overflow: ellipsis; white-space: nowrap; overflow: hidden;width: 200px; } */
</style>
	<script type="text/javascript">
	function parkingPop(){
		var queryValue=$("#queryValue").val();
		var queryType = $("#queryType").val();
		document.getElementById('parkingForm').style.display='block';
		$.ajax({  
	        type: "POST",  
	        url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
	        data:{"queryValue":queryValue,"queryType":queryType},
	        dataType: "json",   
	        success: function (jsonStr) {
	        	$(".parkingListTr").remove();
	        	var parkings = $("#parkingId").val();
	            	for(var i=0;i<jsonStr.length;i++){
	            		if(parkings.indexOf(jsonStr[i].parkingId)>=0){
	            			var checkStatus = '<td><input type="checkbox" checked="checked" name="'+jsonStr[i].parkingName+'" value='+jsonStr[i].parkingId+'></td>';
	            		}else{
	            			var checkStatus = '<td><input type="checkbox"  name="'+jsonStr[i].parkingName+'" value='+jsonStr[i].parkingId+'></td>';
	            		}
	            		$("table#myTable tr:last").after('<tr class = "parkingListTr" style="text-align:center">'+checkStatus+'<td bgcolor="#FFFFFF">'+jsonStr[i].parkingId+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingName+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingArea+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingAddress+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCount+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCanUse+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingStatus+'</td>'+
	            		<%-- <td bgcolor="#FFFFFF">'+'<img src="<%=imagePath%>edt.gif" width="16" height="16" /><span style="cursor:pointer" onclick="choiceParkingCode(\''+jsonStr[i].parkingName+'\',\''+jsonStr[i].parkingId+'\')">选择</span></td> --%>
	            		'</tr>');
	            }
	        }
	    });
		$("#bg").css({
	        display: "block"
	    });
	  }
	function choiceParkingCode(text1,text2){
		var pkcode = 
		  $("#parkingCode").val(text1);
		  $("#parkingId").val(text2);
		  $("#parkingForm").hide();
		  $(".parkingListTr").remove();
		  $("#bg").css("display", "none");
	  }
   
	function cancelButton(){
		  $("#parkingForm").hide();
		  $(".parkingListTr").remove();
		  $("#bg").css("display", "none");
	  }
	function chooseAll(){
		if($("#chooseAllBox").is(':checked')){
			$("#mytable input:checkbox").prop('checked', true);
	
		}else{
			$("#mytable input:checkbox").prop('checked', false);
		}
	}
	function confButton(){
		//获取parkingId
		var strName = "";
		var strId = "";
		$("#mytable input:checkbox:checked:not('#chooseAllBox')").each(function(index,element){
			if($(this).val()!=""){
				strId+=$(this).val();
				strId+=",";
			}
			if($(this).attr("name")!=""){
				strName += $(this).attr("name");
				strName += ",";
			}
		})
		if(strId!=""){
			strId = strId.substring(0,strId.length-1);
		}
		if(strName!=""){
			strName = strName.substring(0,strName.length-1);
		}
		$("#parkingCode").val(strName);
		 $("#parkingId").val(strId);
		 $("#parkingForm").hide();
		  $(".parkingListTr").remove();
		  $("#bg").css("display", "none");
	}
	</script>
	<body>
	<div id="bg"></div>
	<div class="content" id="parkingForm">
		<form action="<%-- <%=basePath%>products/parking/list.html --%>"
			method="post">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30" margin="0" padding="0"background="<%=imagePath%>tab_05.gif"><table
							width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="12" height="30"><img
									src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
								<td>
									<table  width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="STYLE4" align="center">&nbsp;&nbsp;请输入查询内容：<input
												type="text" name="queryValue" id="queryValue"style="width: 290px" /></td>
											<td class="STYLE4">&nbsp;&nbsp;请选择查询方式：<select
												name="queryType"  id="queryType" style="width: 100px">
													<option value="1">车场代码</option>
													<option value="2">车场名称</option>
													<option value="3">车场地址</option>
											</select>
											</td>
											<td class="STYLE4">&nbsp;&nbsp; <input type="button" onclick="parkingPop()"
												value="查询" style="width: 50px" />
											</td>
											<td class="STYLE4">&nbsp;&nbsp; <input type="button"
												value="取消" style="width: 50px" onclick="cancelButton()"/>
											</td>
											<td class="STYLE4">&nbsp;&nbsp; <input type="button"
												value="确定" style="width: 50px" onclick="confButton()"/>
											</td>
										</tr>
									</table>
								</td>
								<td width="16"><img src="<%=imagePath%>tab_07.gif"
									width="16" height="30" /></td>
							</tr>
						</table></td>
				</tr>

				<tr>
					<td><table width="100%" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
								<td><table id="mytable" width="100%" border="0" cellpadding="0"
										cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"
										onmouseout="changeback()">
										<tr>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 10%; height: 22px;">
												<div align="center">
													<span class="STYLE1"><input id="chooseAllBox" onclick="chooseAll()" type="checkbox">全选</span>
												</div>
											</td>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 10%; height: 22px;">
												<div align="center">
													<span class="STYLE1">车场代码</span>
												</div>
											</td>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 15%; height: 22px;">
												<div align="center">
													<span class="STYLE1">车场名称</span>
												</div>
											</td>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 15%; height: 22px;">
												<div align="center">
													<span class="STYLE1">小区</span>
												</div>
											</td>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 20%; height: 22px;">
												<div align="center">
													<span class="STYLE1">地址</span>
												</div>
											</td>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 10%; height: 22px;">
												<div align="center">
													<span class="STYLE1">车位数</span>
												</div>
											</td>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 10%; height: 22px;">
												<div align="center">
													<span class="STYLE1">空位数</span>
												</div>
											</td>
											<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
												style="width: 10%; height: 22px;">
												<div align="center">
													<span class="STYLE1">车位状态</span>
												</div>
											</td>
											<!-- <td width="10%" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" class="STYLE1"
												style="width: 10%; height: 22px;">
												<div align="center">基本操作</div>
											</td> -->
										</tr>
									</table></td>
								<td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
							</tr>
						</table></td>
				</tr>
				<tr><td height="35" background="<%=imagePath%>tab_19.gif"><%-- <jsp:include page="/frame/page.html" /> --%></td></tr>
			</table>
		</form>
	</div>
		<form action="<%=basePath%>users/userupdate.html" method="post" name="form2" onsubmit="return validator(this)">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请修改用户的详细信息
						<input type="hidden" name="userId" value="${userInfo.userId }" readonly="readonly">
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center"><font color="red">*</font>姓名：</div></td>
					<td colspan="7" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px" name="userName" disabled="disabled" readonly="readonly"
							value="${userInfo.userName}">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center"><font color="red">*</font>部门：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="departmentId" style="width: 190px">
							<c:forEach items="${departInfos}" var="row" varStatus="status">
							<option value="<c:out value="${row.departmentId}"/>"
								<c:if test='${userInfo.departmentId == row.departmentId  }'>selected="selected"</c:if>
								><c:out value="${row.departmentName}"/>
							</option>
							</c:forEach>
						</select>
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0"><div align="center"><font color="red">*</font>角色：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<%--<c:set var="role" value="${userInfo.roleId}"></c:set>
						<c:choose>
							<c:when test="${shareUser.roleId > 1}">
								<select name="roleId" style="width: 190px" disabled="disabled">
							</c:when>
							<c:otherwise>
								<select name="roleId" style="width: 190px" checked="checked">
							</c:otherwise>
						</c:choose>
							<c:forEach items="${userRoles}" var="row" varStatus="status" >
								<option value="<c:out value="${row.roleId}"/>"
									<c:if test='${!empty role and row.roleId == role }'>selected="selected"</c:if>
									><c:out value="${row.roleName}"/>
								</option>
							</c:forEach>
						</select>
						&nbsp;--%>
						<input type="button" class="btn btn-default" value="选择角色" style="width: 190px">
					</td>
				</tr>
				<%--<!--  	<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<c:choose>--%>
							<%--<c:when test="${shareUser.roleId == 3}">--%>
								<%--<c:set var="role" value="2"/>--%>
								<%--<select name="roleId" style="width: 190px" disabled="disabled">--%>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<select name="roleId" style="width: 190px" checked="checked">--%>
							<%--</c:otherwise>--%>
						<%--</c:choose>--%>
							<%--<c:forEach items="${userRoles}" var="row" varStatus="status" >--%>
							<%--<option value="<c:out value="${row.roleId}"/>"--%>
								<%--<c:if test='${userInfo.roleId == (!empty role ? role : row.roleId) }'>selected="selected"</c:if>--%>
								<%--><c:out value="${row.roleName}"/>--%>
							<%--</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
						<%----%>
						<%--&lt;%&ndash; <c:choose>	当登录的用户角色为区域管理员时,停用下拉框--%>
							<%--<c:when test="${shareUser.roleId == 3}">--%>
								<%--<select name="roleId" style="width: 190px" disabled="disabled">--%>
									<%--<c:forEach items="${userRoles}" var="row" varStatus="status" >--%>
										<%--<option value="<c:out value="${row.roleId}"/>"--%>
											<%--<c:if test='${row.roleId = 2}'>selected="selected"</c:if>--%>
											<%--><c:out value="${row.roleName}"/>--%>
										<%--</option>--%>
									<%--</c:forEach>--%>
								<%--</select>--%>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<select name="roleId" style="width: 190px" checked="checked">--%>
									<%--<c:forEach items="${userRoles}" var="row" varStatus="status" >--%>
										<%--<option value="<c:out value="${row.roleId}"/>"--%>
											<%--<c:if test='${userInfo.roleId == row.roleId }'>selected="selected"</c:if>--%>
											<%--><c:out value="${row.roleName}"/>--%>
										<%--</option>--%>
									<%--</c:forEach>--%>
								<%--</select>--%>
							<%--</c:otherwise>--%>
						<%--</c:choose> &ndash;%&gt;--%>
						<%----%>
					<%--</td>-->--%>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input  readonly="readonly"
						onclick="parkingPop()" style="width: 190px" id="parkingCode"
						value="${parkingName}"/>
						<input type="hidden" name = "parkingId" id="parkingId" value="${userInfo.parkingId}"/>
					</td>
					<td bgcolor="#FFFDF0">
						<div align="center">
							E_mail：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px"  maxlength="50" name="userEmail" 
							value="${userInfo.userEmail}">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							身份证：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px"  valid="isIdCard"   errmsg="请输入正确的身份证号码!" name="userIdnum" 
							value="${userInfo.userIdnum}">
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0"><div align="center">年龄：</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="userAge" maxlength="2"  valid="required|isNumber"  
							errmsg="员工年龄不能为空!|请输入正确的年龄!" value="${userInfo.userAge}">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center">性别：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px">
						<input type="radio" name="userSex" value="男"
							<c:if test='${userInfo.userSex == "男" }'>checked="checked"</c:if>
							/>男
						<input type="radio" name="userSex" value="女"
							<c:if test='${userInfo.userSex == "女" }'>checked="checked"</c:if>
							/>女
						<br />
					</td>
					<td bgcolor="#FFFDF0"><div align="center">民族：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="userNation"
							value="${userInfo.userNation}">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">学历：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="userDiploma" style="width: 190px">
							<option <c:if test='${userInfo.userDiploma == "初中" }'>selected="selected"</c:if>
								>初中
							</option>
							<option <c:if test='${userInfo.userDiploma == "高中" }'>selected="selected"</c:if>
								>高中
							</option>
							<option <c:if test='${userInfo.userDiploma == "本科" }'>selected="selected"</c:if>
								>本科
							</option>
							<option <c:if test='${userInfo.userDiploma == "博士" }'>selected="selected"</c:if>
								>博士
							</option>
							<option <c:if test='${userInfo.userDiploma == "硕士" }'>selected="selected"</c:if>
								>硕士
							</option>
						</select>
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0"><div align="center">婚姻：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="isMarried" style="width: 190px">
							<option <c:if test='${userInfo.isMarried == "已婚" }'>selected="selected"</c:if>
								>已婚
							</option>
							<option <c:if test='${userInfo.isMarried == "未婚" }'>selected="selected"</c:if>
								>未婚
							</option>
							<option <c:if test='${userInfo.isMarried == "离异" }'>selected="selected"</c:if>
								>离异
							</option>
						</select>
						&nbsp;
					</td>
				</tr>


	
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">座机：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px"     valid="isPhone"   errmsg="请输入正确的座机号码!" name="userTel" 
							value="${userInfo.userTel}">
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0">
						<div align="center">
							手机：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" valid="regexp"  regexp="^1[0-9][0-9]\d{8}$"   errmsg="请输入正确的手机号码!" name="userMobile"
							value="${userInfo.userMobile}">
						&nbsp;
					</td>
				</tr>
			
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							工资卡号：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px"  valid="isNumber"   errmsg="请输入正确的工资卡号!"  maxlength="20"   name="userBankcard"
							value="${userInfo.userBankcard}">
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0"><div align="center">爱好：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px"  maxlength="50"  name="userIntest"
							value="${userInfo.userIntest}">
						&nbsp;
					</td>

				</tr>

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							地址：
						</div>
					</td>
					<td colspan="7" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px"  maxlength="50"  name="userAddress"
							value="${userInfo.userAddress}">
						&nbsp;
					</td>
					<input type="hidden" name="sysUserId" value="${userInfo.sysUserId}" />
				</tr>
				<div class="modal" id="mymodal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
								<h4 class="modal-title">请选择</h4>
							</div>
							<div class="modal-body">
								<p><c:forEach items="${userRoles}" var="userRoles" >
									<input <c:if test="${userRoles.userId != null&&userRoles.userId != ''}"> checked = "checked"</c:if> type="checkbox"  style="width: 50px" name="sysRoles" id="sysRoles" value="${userRoles.roleId}">${userRoles.roleName}[${userRoles.roleDesc}]</br>
								</c:forEach></p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-primary">保存</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
				</div>
				<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
				<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
				<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
				<script>

					$(function(){
						$(".btn").click(function(){
							$("#mymodal").modal("toggle");
						});
					});
				</script>
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="40%"></td>
					<td width="17%"><input type="submit" name="submit"  value="提交"></td>
					<td width="12%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<!-- <td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td> -->
					<td width="43%"></td>
				</tr>
			</table>

		</form>

	</body>
</html>
