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

		<title>添加员工信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
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
	            	for(var i=0;i<jsonStr.length;i++){ 
	            		$("table#myTable tr:last").after('<tr class = "parkingListTr" style="text-align:center"><td bgcolor="#FFFFFF">'+jsonStr[i].parkingId+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingName+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingArea+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingAddress+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCount+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCanUse+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingStatus+'</td><td bgcolor="#FFFFFF">'+
	            				'<img src="<%=imagePath%>edt.gif" width="16" height="16" /><span style="cursor:pointer" onclick="choiceParkingCode(\''+jsonStr[i].parkingName+'\',\''+jsonStr[i].parkingId+'\')">选择</span></td></tr>');
	            }
	        },
	    });
		$("#bg").css({
	        display: "block"
	    });
	  }
  function choiceParkingCode(text1,text2){
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
  function onParkerType(){
	  var parkerType = $("#parkerType option:selected").val();
	  if(parkerType=="0"){
		  //$("#parkerLevel").show();
		  $("#parkerLevel").attr("disabled",false); 
	  }else{
		  //$("#parkerLevel").hide();
		  $("#parkerLevel").attr("disabled",true); 
	  }
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
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
											<td width="10%" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" class="STYLE1"
												style="width: 10%; height: 22px;">
												<div align="center">基本操作</div>
											</td>
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
		<form action="<%=basePath%>users/parkerupdate.html"  name="form1" onsubmit="return validator(this)"   method="post"
			onsubmit="return checkForm('form2');">
			<input type="text" name="parkerId" value="${parker.parkerId}" style="display:none" >
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请输入新用户的详细信息
					</td>
				</tr>

				<tr>
					<td width="13%" bgcolor="#FFFDF0">
						<div align="center">
							<font color="red">*</font>姓名：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" name="parkerName" value="${parker.parkerName}"  maxlength="10" style="width: 145px" valid="required"  errmsg="员工姓名不能为空!" >
						&nbsp;
					</td>
					<td width="13%" bgcolor="#FFFDF0">
						<div align="center">
							<font color="red">*</font>车场名称：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input  readonly="readonly"
						onclick="parkingPop()" style="width: 165px" id="parkingCode" value="${parker.parkingName}"/>
						<input type="hidden" valid="required"
						errmsg="车场名称不能为空!" name = "parkingId" id="parkingId" value="${parker.parkingId}">
					</td>
				</tr>
				<!-- begin -->
				<tr>
					<td width="13%" bgcolor="#FFFDF0">
						<div align="center">
							<font color="red">*</font>年龄：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" name="parkerAge" value="${parker.parkerAge}" style="width: 145px"   maxlength="2"  valid="required|isNumber"  errmsg="年龄不能为空!|请输入正确的年龄!" >						&nbsp;
					</td>
					<td bgcolor="#FFFDF0">
						<div align="center">
							角色：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select id="parkerType" onchange="onParkerType()" name="parkerType" style="width: 145px">
							<option value="0" <c:if test="${parker.parkerType==0}">selected="selected"</c:if>>
								代泊员
							</option>
							<option value="1" <c:if test="${parker.parkerType==1}">selected="selected"</c:if>>
								车管家
							</option>
							<option value="2" <c:if test="${parker.parkerType==2}">selected="selected"</c:if>>
								洗车管理员
							</option>
						</select>
						&nbsp;
							<select id="parkerLevel" name="parkerLevel" <c:if test="${parker.parkerType!=0}">disabled="disabled"</c:if>>
							  <option value ="A" <c:if test="${parker.parkerLevel eq 'A'}">selected="selected"</c:if>>代泊员主管</option>
							  <option value ="B" <c:if test="${parker.parkerLevel eq 'B' }">selected="selected"</c:if>>高级代泊员</option>
							  <option value="C" <c:if test="${parker.parkerLevel eq 'C' }">selected="selected"</c:if>>初级代泊员</option>
							</select>
						
					</td>
					<td colspan="3" bgcolor="#FFFFFF">

					</td>
				</tr>
				<!-- end -->
				
				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px">
						<div align="center">
							性别：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px">
						<select name="parkerSex" style="width: 145px">
							<option value="1" <c:if test="${parker.parkerSex==1} }">selected="selected"</c:if>>
								男
							</option>
							<option value="2" <c:if test="${parker.parkerSex==2} }">selected="selected"</c:if>>
								女
							</option>
						</select>
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0">
						<div align="center">
							身份证号：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input name="parkerCardid" value="${parker.parkerCardid}" type="text" style="width: 145px"  maxlength="18">
						&nbsp;
					</td>
				</tr>

				

				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							驾龄：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" value="${parker.parkerDriverAge}" name="parkerDriverAge"  maxlength="10" style="width: 145px" value="0"  errmsg="员工姓名不能为空!" >
						&nbsp;
					</td>

					<td bgcolor="#FFFDF0">
						<div align="center">
						手机号：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" value="${parker.parkerMobile}" name="parkerMobile"  maxlength="11" style="width: 145px" value="0"  errmsg="员工姓名不能为空!" >
					</td>
				</tr>
				
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							<font color="red">*</font>密码：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "parkerPassword"  type="password" autocomplete="on"  maxlength="20"  check_str="密码" style="width: 145px" name="parkerPassword" >
						&nbsp;
					</td>

					<td bgcolor="#FFFDF0">
						<div align="center">
							头像是否显示
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="radio" <c:if test="${fn:contains(parker.isDisplay, '1')}">checked="checked"</c:if> name="isDisplay" value="1" />是
						<input type="radio"  <c:if test="${fn:contains(parker.isDisplay, '0')}">checked="checked"</c:if> name="isDisplay" value="0" />否
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0">
						<div align="center">
							身份头像：
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
					<share:imageUpload imageLabelName="身份头像" imagePathId="imagePath" savePath="product/" imagePath="${parker.parkerHead}" />
					<input type="hidden"  id="imagePath" name="parkerHead" value="${parker.parkerHead}"/>
					&nbsp;
					</td>
				
					<td bgcolor="#FFFDF0">
						<div align="center">
							
						</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						
					</td>
			   </tr>
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="12%" style="text-align: center;"><input type="submit" name="submit"  onclick="javascript:alert('修改成功')" value="提交"></td>
					<!-- <td width="17%"><input type="reset" name="reset"  value="重置"></td> -->
					<td width="12%" style="text-align: center;"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
				</tr>
			</table>

		</form>
	</body>
</html>
