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

		<title>添加员工信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=basePath%>/css/style_v2.css">
		<%--<link rel="stylesheet" href="<%=basePath%>/css/pages/style_v2.2.css">--%>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/pages/parkingView.js"></script>
		<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
		<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
	</head>
	<style type="text/css">
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
  function yanzheng(){
	  var  departmentId= $("#departmentId").val();
	  var  parkingId= $("#parkingId").val();
	  var  module1= $("#module1").val();
	  if(departmentId == null || departmentId == ''){
		  alert("请选择部门！")
	  }else if((parkingId== null || parkingId == '')&&module1!=null&&module1!=''){
		  alert("车场不能为空！");
	  } else{
		 $("#form1").submit();
	  }
  }
  </script>
	<body>
	<div class="bodyColor">
		<form action="<%=basePath%>users/usersave.html"  name="form1" id="form1"<%-- onsubmit="return validator(this)"--%>   method="post"
			>
			<table class='mainView' cellSpacing=1 cellPadding=0 width="100%" align=center border=0
				>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请输入新用户的详细信息
					</td>
				</tr>

				<tr>
					<td align="right" >
						<div>
							<span class="star">＊</span> <font size="4px"> 姓名：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userName"  maxlength="10" style="width: 191px" valid="required"  errmsg="员工姓名不能为空!" >
						&nbsp;
					</td>
					<td align="right">
						<div >
							<span class="star">＊</span><font size="4px">账号：</font>
						</div>
					</td>
					<td  colspan="3" >
						<input type="text" name="userNum" id="userNum" maxlength="100" style="width: 191px" valid="required"  errmsg="账号不能为空!" onchange="check()">
					</td>
				</tr>
				<tr>
					<td align="right">
						<div >
							<span class="star">＊</span><font size="4px">年龄：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userAge" style="width: 191px" value="0"  maxlength="2"  valid="required|isNumber"  errmsg="年龄不能为空!|请输入正确的年龄!">
						&nbsp;
					</td>

					<td align="right">
						<div  >
							<span class="star">＊</span><font size="4px">密码：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="password" name="userPw"  maxlength="20" style="width: 191px" valid="required"  errmsg="密码不能为空!">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td  align="right">
						<div >
							<font size="4px">性别：</font>
						</div>
					</td>
					<td colspan="3"  style="height: 21px">
						<select name="userSex" style="width: 191px">
							<option value="男">
								男
							</option>
							<option value="女">
								女
							</option>
						</select>
						&nbsp;
					</td>
					<td align="right">
						<div >
							<font size="4px">民族：</font>
						</div>
					</td>
					<td colspan="3" >
						<input name="userNation" type="text" style="width: 191px"  maxlength="5">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td align="right">
						<div >
							<font size="4px">学历：</font>
						</div>
					</td>
					<td colspan="3" >
						<select name="userDiploma" style="width: 191px">
							<option>
								初中
							</option>
							<option>
								高中
							</option>
							<option>
								本科
							</option>
							<option>
								博士
							</option>
							<option>
								硕士
							</option>
						</select>
					</td>
					<td align="right">
						<div >
							<font size="4px">婚姻：</font>
						</div>
					</td>
					<td colspan="3" >
						<select name="isMarried" style="width: 191px">
							<option>
								已婚
							</option>
							<option>
								未婚
							</option>
							<option>
								离异
							</option>
						</select>
						&nbsp;
					</td>
				</tr>

				<tr>
					<td align="right">
						<div >
							<span class="star">*</span><font size="4px">部门：</font>
						</div>
					</td>
					<td colspan="3" >
						<select name="departmentId" id="departmentId" style="width: 191px" v>
							<c:forEach items="${departInfos}" var="row" varStatus="status">
							<option value="<c:out value="${row.departmentId}"/>"><c:out value="${row.departmentName}"/></option>
							</c:forEach>
						</select>
						&nbsp;
					</td>
					<%--<td ><div >车场名称：</div></td>
					<td colspan="3" >
						<input  readonly="readonly"
								onclick="parkingPop()" style="width: 191px" id="parkingCode"/>
						<input type="hidden" name = "parkingId" id="parkingId"/>
					</td>--%>
					<td align="right" ><div><span class="star">＊</span><font size="4px">车场名称：</font></div></td>
					<td colspan="3" ><input  type="text"  onclick="parkingView.show({parkingId: document.getElementById('parkingId'), parkingName: this})"   style="width: 190px" />
						&nbsp;</td>
					<input id="parkingId" name="parkingId"  type="hidden" value=""   />
				</tr>
				<tr>
					<td align="right">
						<div >
							<font size="4px">座机：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userTel" style="width: 191px"    valid="isPhone"   errmsg="请输入正确的座机号码!" >
						&nbsp;
					</td>
					<td align="right">
						<div >
							<font size="4px">爱好：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userIntest" style="width: 191px"  maxlength="50">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td align="right">
						<div >
							<font size="4px">工资卡号：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userBankcard" style="width: 191px"  valid="isNumber"   errmsg="请输入正确的工资卡号!"  maxlength="20"  >
						&nbsp;
					</td>
					<td align="right">
						<div >
							<font size="4px">手机：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userMobile" style="width: 191px"  valid="regexp"  regexp="^1[0-9][0-9]\d{8}$"   errmsg="请输入正确的手机号码!" >
						&nbsp;
					</td>

				</tr>

				<tr>
					<td align="right">
						<div >
							<font size="4px">身份证：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userIdnum" style="width: 191px"   valid="isIdCard"   errmsg="请输入正确的身份证号码!">
					</td>
					<td align="right">
						<div >
							<font size="4px">地址：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userAddress"  maxlength="50" style="width: 191px">
					</td>
				</tr>

				<tr>
					<td align="right">
						<div >
							<font size="4px">添加人：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userAddman" style="width: 191px"   maxlength="20"  valid="required"  errmsg="添加人不能为空!" 
							value="${shareUser.userName}" disabled="disabled" readonly="readonly">
					</td>
					<td align="right">
						<div >
							<font size="4px">E_mail：</font>
						</div>
					</td>
					<td colspan="3" >
						<input type="text" name="userEmail" style="width: 191px"  valid="isEmail"  errmsg="Email格式不对!">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td align="right">
						<div >
							<span class="star">＊</span><font size="4px">角色：</font>
						</div>
					</td>

					<td colspan="3" >
						<input type="button" class="btn btn-default" value="选择角色" style="width: 191px"  />
					</td>
					<td align="right">
						<div >
							<span class="star">＊</span><font size="4px">所属系统：</font>
						</div>
					</td>
					<td colspan="3" >
						<select name="module" style="width: 191px" class="dictionary" data-dict-code="module" data-current-value="${shareUser.module}"  data-dict-value="${shareUser.module}" >
							<c:if test="${shareUser.module =='' || shareUser.module ==null }" ><option value=""></option></c:if>
						</select>
						&nbsp;
					</td>
					<input type="hidden" id="module1" name="module1" value="${shareUser.module}" />
				</tr>
				<tr>
					<td colspan="3" ></td>
					<td colspan="4"  style="padding-bottom: 30px">
						<input type="button" name="submit1" class="greenBtn" onclick="yanzheng()" value="添加">
						<input type="reset" name="reset" class="greenBtn" value="重置">
						<input type="button" name="button" class="greenBtn"  onClick="history.back() "  value="返回"></td>
					<!-- <td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td> -->
				</tr>
			</table>


	<div class="modal" id="mymodal"  style="overflow:auto">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title">请选择</h4>
				</div>
				<div class="modal-body">
					<p><c:forEach items="${userRoles}" var="userRoles" >
						<input type="checkbox" style="width: 50px" name="sysRoles" id="sysRoles" value="${userRoles.roleId}">${userRoles.roleName}[${userRoles.roleDesc}]</br>
					</c:forEach></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">保存</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<script>
		$(function(){
			$(".btn").click(function(){
				$("#mymodal").modal("toggle");
			});
		});
	</script>
		</form>
		</div>
	</body>
</html>
