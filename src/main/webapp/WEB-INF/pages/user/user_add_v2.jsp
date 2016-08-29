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
		<%--<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>--%>
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<%--<link rel="stylesheet" href="<%=basePath%>/css/pages/style_v2.2.css">--%>
		<link rel="stylesheet" href="<%=basePath%>/css/style_v2.css">
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/pages/parkingView.js"></script>
	</head>
	<style type="text/css">
.content {
	display: none;
	position: absolute;
	top: 10%;
	left: 5%;
	width: 90%;
	height: 80%;
	/* border: 1px solid black; */
	background-color: #EFEEEC;
	z-index: 1002;
	overflow: auto;
}
#bg{
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
body {
	padding: 15px 20px;
}
</style>
	<script type="text/javascript">
		function check(){
			$.ajax({
				cache: false,
				type: "POST",
				url:'users/check.html',
				data:$('#userNum').serialize(),// 你的formid
				async: false,
				error: function(request) {
					alert("Connection error");
				},
				success: function(data) {
					if(data=="0"){
						alert("该账号已存在，请重新输入!");
					}

				}
			});
		}
	</script>
	<body>
<div class="bodyColor">
		<form action="<%=basePath%>users/usersave.html" method="post" name="form1" onsubmit="return validator(this)">
			<table class='mainView' cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class='headerTitle' colSpan=7>
						请输入用户的详细信息
						<input type="hidden" name="userId" value="${userInfo.userId }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td  style="height: 15px;"></td>
					<td colspan="7" ></td>
				</tr>
				<tr>
					<td align="right" >
						<div>
							<span class="star">＊</span>姓名：
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userName"  maxlength="10" style="width: 191px" valid="required"  errmsg="员工姓名不能为空!" >
						&nbsp;
					</td>
					<td align="right">
						<div >
							<span class="star">＊</span>账号：
						</div>
					</td>
					<td  colspan="3" >
						<input type="text" name="userNum" id="userNum" maxlength="100" style="width: 191px" valid="required"  errmsg="账号不能为空!" onchange="check()">
					</td>
				</tr>
				<tr>
					<td align="right">
						<div >
							添加人：
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userAddman" style="width: 191px"   maxlength="20"  valid="required"  errmsg="添加人不能为空!"
							   value="${shareUser.userName}" disabled="disabled" readonly="readonly">
					</td>
					<td align="right">
						<div  >
							<span class="star">＊</span>密码：
						</div>
					</td>
					<td colspan="3" >
						<input type="password" name="userPw"  maxlength="20" style="width: 191px" valid="required"  errmsg="密码不能为空!">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right"><div><span class="star">＊</span>部门：</div></td>
					<td colspan="3" >
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
					<td align="right"><div><span class="star">＊</span>角色：</div></td>
					<td style="padding-left: 5px;" colspan="3" >
						<input type="button" class="btn btn-default" value="选择角色" style="width: 190px; height:30px; color:#6D7D83; border-radius: 5px;">
					</td>
				</tr>
				<tr>
						<td align="right" ><div><span class="star">＊</span>车场名称：</div></td>
						<td colspan="3" ><input  type="text"  onclick="parkingView.show({parkingId: document.getElementById('parkingId'), parkingName: this})"     style="width: 190px" />
							&nbsp;</td>
						<input id="parkingId" name="parkingId"  type="hidden" value="${sysRoles.parkingId}"   />
					<td align="right" >
						<div>
							E_mail：
						</div>
					</td>
					<td colspan="3" >
						<input  type="text" style="width: 190px"  maxlength="50" name="userEmail"
							value="${userInfo.userEmail}">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td align="right">
						<div>
							身份证：
						</div>
					</td>
					<td colspan="3" >
						<input  type="text"  style="width: 190px"  valid="isIdCard"   errmsg="请输入正确的身份证号码!" name="userIdnum"
							value="${userInfo.userIdnum}">
						&nbsp;
					</td>
					<td align="right"><div><span class="star">＊</span>年龄：</div>
					</td>
					<td colspan="3" >
						<input  type="text"  style="width: 190px" name="userAge" maxlength="2" v  valid="required|isNumber"
							errmsg="员工年龄不能为空!|请输入正确的年龄!" value="0">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td align="right" style="height: 21px"><div>性别：</div></td>
					<td colspan="3"  style="height: 21px; padding-left: 5px;">
						<input type="radio" name="userSex" value="男"
							 checked="checked"
							/>男
						&nbsp;
						<input type="radio" name="userSex" value="女"
							/>女
						<br />
					</td>
					<td align="right"><div>民族：</div></td>
					<td colspan="3" >
						<input  type="text"  style="width: 190px" name="userNation"
							value="${userInfo.userNation}">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td align="right"><div>学历：</div></td>
					<td colspan="3" >
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
					<td align="right"><div>婚姻：</div></td>
					<td colspan="3" >
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
					<td align="right"><div>座机：</div></td>
					<td colspan="3" >
						<input  type="text"  style="width: 190px"     valid="isPhone"   errmsg="请输入正确的座机号码!" name="userTel" 
							value="${userInfo.userTel}">
						&nbsp;
					</td>
					<td align="right">
						<div>
							手机：
						</div>
					</td>
					<td colspan="3" >
						<input  type="text"  style="width: 190px" valid="regexp"  regexp="^1[0-9][0-9]\d{8}$"   errmsg="请输入正确的手机号码!" name="userMobile"
							value="${userInfo.userMobile}">
						&nbsp;
					</td>
				</tr>
			
				<tr>
					<td align="right">
						<div>
							工资卡号：
						</div>
					</td>
					<td colspan="3" >
						<input  type="text" style="width: 190px"  valid="isNumber"   errmsg="请输入正确的工资卡号!"  maxlength="20"   name="userBankcard"
							value="${userInfo.userBankcard}">
						&nbsp;
					</td>
					<td align="right"><div>爱好：</div></td>
					<td colspan="3" >
						<input  type="text"  style="width: 190px"  maxlength="50"  name="userIntest"
							value="${userInfo.userIntest}">
						&nbsp;
					</td>

				</tr>

				<tr>
					<td align="right">
						<div>
							地址：
						</div>
					</td>
					<td colspan="3" >
						<input  type="text" style="width: 190px"  maxlength="50"  name="userAddress"
							value="${userInfo.userAddress}">
						&nbsp;
					</td>
					<td align="right">
						<div>
							所属系统：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="module" style="width: 190px" class="dictionary" data-dict-code="module" data-current-value="${sysUsers.module}"  data-dict-value="${shareUser.module}" >
							<c:if test="${shareUser.module =='' || shareUser.module ==null }" ><option value=""></option></c:if>
						</select>
						&nbsp;
					</td>
					<input type="hidden" name="sysUserId" value="${userInfo.sysUserId}" />
				</tr>

				<tr>
					<td ></td>
					<td colspan="3" ></td>
					<td colspan="4"  style="padding-bottom: 30px">
						<input class="greenBtn" type="submit" name="submit"  value="提&nbsp;&nbsp;交">
						<input class="greenBtn" type="button" name="button"  onClick="history.back() "  value="返&nbsp;&nbsp;回">
					</td>
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
				<%--<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>--%>
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

		</form>
</div>
	</body>
</html>
