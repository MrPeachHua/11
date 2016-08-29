<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>查询产权车位信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"
			src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<LINK href="<%=basePath%>css/base.css" type=text/css rel=stylesheet>
	<LINK href="<%=basePath%>css/fileList.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
	<script type="text/javascript"
			src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>

	<script>
		var  highlightcolor='#c1ebff';
		//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
		var  clickcolor='#51b2f6';
		function  changeto(){
			source=event.srcElement;
			if  (source.tagName=="TR"||source.tagName=="TABLE")
				return;
			while(source.tagName!="TD")
				source=source.parentElement;
			source=source.parentElement;
			cs  =  source.children;
//alert(cs.length);
			if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
				for(i=0;i<cs.length;i++){
					cs[i].style.backgroundColor=highlightcolor;
				}
		}

		function  changeback(){
			if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
				return
			if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
				for(i=0;i<cs.length;i++){
					cs[i].style.backgroundColor="";
				}
		}
		function cencelInfoContent(){
			document.getElementById('infoContent').style.display = 'none';
			document.getElementById('bg').style.display = 'none';
		}
		function  clickto(){
			source=event.srcElement;
			if  (source.tagName=="TR"||source.tagName=="TABLE")
				return;
			while(source.tagName!="TD")
				source=source.parentElement;
			source=source.parentElement;
			cs  =  source.children;
//alert(cs.length);
			if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
				for(i=0;i<cs.length;i++){
					cs[i].style.backgroundColor=clickcolor;
				}
			else
				for(i=0;i<cs.length;i++){
					cs[i].style.backgroundColor="";
				}
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			var info = '${info}';
			if(info == "1"){
				document.getElementById('infoContent').style.display = 'block';
				document.getElementById('bg').style.display = 'block';
			}
			$("#saveCoupon").click(function(){
				if($("#monthNum").val()<=0)
				{
					alert("开始时间不能小于结束时间");
				}else{
					$("#formActions").submit();
				}
			});
			$("#cancel").click(function(){
				$("#addCoupon").hide();
				$("#form_beginTime").val("");
				$("#form_endTime").val("");
				$("#bg").css("display", "none");
			});
			$("#close").click(function () {
				$("#addCoupon").hide();
				$("#bg").css("display", "none");
				$("#form_beginTime").val("");
				$("#form_endTime").val("");
			});
		});
		function add(){
			window.location="<%=basePath%>products/parking/equity/add.html";
		}
		function addFree(text1,text2,text3,text4,text5,text6,text7){
			document.getElementById('addCoupon').style.display='block';
			if(text7!="")
			{
				var str = text7.replace(/-/g,"/");
				var date = new Date(str);
				date.setDate(date.getDate()+1);
				var dateTime = "";
				if (date.getMonth() < 9) {
					if(date.getDate()<10){
						dateTime = date.getFullYear() + "-0" + (date.getMonth() + 1) + "-0" + date.getDate();
					}else{
						dateTime = date.getFullYear() + "-0" + (date.getMonth() + 1) +"-"+ date.getDate();
					}
				} else {
					if(date.getDate()<10){
						dateTime = date.getFullYear() + "-" + (date.getMonth() + 1) + "-0" + date.getDate();
					}else{
						dateTime = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"+date.getDate();
					}
				}
				$("#form_beginTime1").val(dateTime);
			}
			$("#form_beginTime").val(text3);
			$("#carNumber").val(text2);
			$(".villageName").val(text6);
			$("#text1").val(text1);
			$("#price").val(text5);
			$("#bg").css({
				display: "block"
			});
		}
		function getmonthly(){
			var beginTime = $("#form_beginTime1").val();
			var endTime = $("#form_endTime").val();
			if(beginTime!="" && endTime!="")
			{
				if(endTime.split("-")[0]>=beginTime.split("-")[0]){
					var monthNumYear = (endTime.split("-")[0]-beginTime.split("-")[0])*12;
					var monthNum = monthNumYear+(endTime.split("-")[1]-beginTime.split("-")[1]);
					$("#monthNum").val(monthNum+1);
				}else if(endTime.split("-")[0]<beginTime.split("-")[0]){
					$("#monthNum").val("0");
				}
			}else{
				$("#monthNum").val("0");
			}
		}
		function excelExport(){
			var queryType = $("#p_queryType").val();
			var queryValue=$("#p_queryValue").val();
			var isUsed = $("#isUsed").val();
			window.location="<%=basePath%>products/parking/equity/excelExport.html?queryType="+queryType+"&queryValue="+queryValue+"&isUsed="+isUsed;;
		}
		function uploadExport(){
			document.getElementById('upload').style.display='block';
			document.getElementById('bg').style.display='block';
			//$("#bg").css("display", "none");
		}
		function cencel(){
			document.getElementById('upload').style.display='none';
			document.getElementById('bg').style.display='none';
		}
		// function checkCsv(){
		// 	var filepath= $("#file").val();
		// 	filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length)
		// 	filepath = filepath.replace(/[ ]/g,"");//去空格
		// 	if(filepath.toLowerCase()!=="csv"){//转小写
		// 		alert("只能上传csv文件");
		// 		return false;
		// 	}
		// 	return true;
		// }
		function uploadEquity(){
			var filepath= $("#file").val();
			filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length)
			filepath = filepath.replace(/[ ]/g,"");//去空格
			if(filepath==null||filepath==''){
				alert("导入文件不能为空！");
			}else if(filepath.toLowerCase()!=="csv"){//转小写
				alert("只能上传csv文件")
			} else{
				$.ajaxFileUpload({
					type: "POST",
					url: "<%=basePath%>products/parking/equity/uploadExcel.html",
					data:{},
					secureuri:false,
					fileElementId : "file",
					dataType: 'text',
					success: function (jsonStr){
						if(jsonStr=="0"){
							//alert("导入成功");
							document.getElementById('upload').style.display='none';
							document.getElementById('bg').style.display='none';
							alert("导入成功");
							history.go(0);
						}else{
							if(jsonStr=="1"){
								alert("导入失败！");
							}else{
								alert(jsonStr);
							}
							//alert("导入失败");
							document.getElementById('upload').style.display='none';
							document.getElementById('bg').style.display='none';
							history.go(0);
						}

					},
					error: function (data){

					}
				});
			}
		}
	</script>
</head>
<div class="content" id="upload">
	<div style="height: 50px;background-color:#3f4752">
		<img  onclick="cencel()" onmouseover="this.style.cursor='pointer'" src="<%=basePath%>/images/new/close.png" class="close_img">
	</div>
	<div class="file_img">
		<input type="file" name="file" id="file"/>
		<p>&nbsp;&nbsp;&nbsp;&nbsp;选择上传文件</p>
		<%--<img src="<%=basePath%>/images/new/file.png">--%>
	</div>
	<div class="check_btn">
		<span><a onclick="uploadEquity()" onmouseover="this.style.cursor='pointer'">导 入</a></span>
		<span><a onclick="cencel()" onmouseover="this.style.cursor='pointer'">取 消</a></span>
	</div>

</div>
<div class="content" id="infoContent" style="height: 280px">
	<div style="height: 50px;background-color:#3f4752">
		<img onclick="cencelInfoContent()" onmouseover="this.style.cursor='pointer'" src="<%=basePath%>/images/new/close.png"
			 class="close_img">
	</div>
	<div >
		<img src="<%=basePath%>/images/copy.png"
			 style="float:left;margin-left: 50px;margin-top: 35px;"><span style="line-height: 150px;font-size: 30px">您 的 续 费 操 作 已 成 功 !</span>
	</div>
	<div class="check_btn9">
		<span><a onclick="cencelInfoContent()" onmouseover="this.style.cursor='pointer'">确 定</a></span>
	</div>

</div>
<body>
<div id="bg"></div>
<form  action="<%=basePath%>products/parking/equity/list.html" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr height="60">
			<td class="STYLE4" >&nbsp;&nbsp;&nbsp;&nbsp;请输入查询内容：<input type="text"  class="btn_tb" name="p_queryValue" id= "p_queryValue" value="${page.params["queryValue"] }"   /></td>
			<td class="STYLE4">请选择查询方式：
				<select name="p_queryType" id="p_queryType"   class="btn_tb">
					<c:if test='${page.params["queryType"] eq null}'>
						<option  value="2">车牌号</option>
						<option  value="1">小区名称</option>
					</c:if>
					<c:if test='${page.params["queryType"] eq 1}'>
						<option  value="1" selected="selected">小区名称</option>
						<option  value="2" >车牌号</option>
					</c:if>
					<c:if test='${page.params["queryType"] eq 2}'>
						<option  value="1">小区名称</option>
						<option  value="2" selected="selected">车牌号</option>
					</c:if>
				</select>
			</td>
			<td class="STYLE4" align="center">记录状态：
				<select name="isUsed" id="isUsed"  class="btn_tb">
					<c:if test='${!(page.params["isUsed"] eq "3") && !(page.params["isUsed"] eq "0") && !(page.params["isUsed"] eq "1")}'>
						<option  value="3" >全部</option>
						<option  value="1" selected>有效</option>
						<option  value="0">无效</option>
					</c:if>
					<c:if test='${page.params["isUsed"] eq "0"}'>
						<option  value="3">全部</option>
						<option  value="0" selected="selected">无效</option>
						<option  value="1" >有效</option>

					</c:if>
					<c:if test='${page.params["isUsed"] eq "3"}'>
						<option  value="3" selected="selected">全部</option>
						<option  value="0" >无效</option>
						<option  value="1" >有效</option>

					</c:if>
					<c:if test='${page.params["isUsed"] eq "1"}'>
						<option  value="3">全部</option>
						<option  value="0">无效</option>
						<option  value="1" selected="selected">有效</option>
					</c:if>
				</select>
			</td>
			<td class="STYLE4"><input class="btn"  type="submit" value="查询"/></td>
			<td  class="STYLE4">
				<security:authorize access="hasAnyRole('AUTH_ADD_0023')"><input class="btn" type="button" value="添加" onclick="add()"/></security:authorize>
			</td>
			<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_IMP_0026')"><input class="btn"  type="button" onclick="uploadExport()" value="导入"/></security:authorize></td>
			<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_EXP_0027')"><input class="btn"  type="button" onclick="excelExport()" value="导出"/></security:authorize></td>
		</tr>
	</table>
	<td>
		<table class="table_content" border="0" cellspacing="0" cellpadding="0" style="margin: 0 auto;">
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" onmouseover="changeto()"  onmouseout="changeback()">
						<tr height="50" style="background-color: #ecf6ff;">
							<td style="width:2%"><div align="center"><span class="STYLE1">序号</span></div></td>
							<td style="width:3%"><div align="center"><span class="STYLE1">小区名称</span></div></td>
							<td style="width:3%"><div align="center"><span class="STYLE1">车牌号</span></div></td>
							<td style="width:3%"><div align="center"><span class="STYLE1">车位号</span></div></td>
							<td style="width:3%"><div align="center"><span class="STYLE1">车主姓名</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">车主联系电话</span></div></td>
							<td style="width:4%"><div align="center"><span class="STYLE1">月租单价</span></div></td>
							<td style="width:4%"><div align="center"><span class="STYLE1">是否违规</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">当月有效情况</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">车位地址信息</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">车位产权信息</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">车位物业信息</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">有效开始时间</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">有效结束时间</span></div></td>
							<td style="width:7%"><div align="center"><span class="STYLE1">记录状态</span></div></td>
							<security:authorize access="hasAnyRole('AUTH_DEL_0024','AUTH_EDIT_0025','AUTH_EDIT_0119')">
								<td  class="STYLE1" style="width: 10%"><div align="center">基本操作</div></td></security:authorize>
						</tr>
						<c:choose>
							<c:when test="${fn:length(page.resultList)>0}">
								<c:forEach items="${page.resultList}" var="row" varStatus="status">
									<tr class="underline" height="50" style="background-color: #FFF"<c:if test="${row.isExpired eq 0}"> style="color:red;font-weight: bold"</c:if>>
										<td style="width:3%"><div align="center"><span class="STYLE1">${status.count}</span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.villageName}</span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.carNumber}</span></div></td>
										<td style="width:2%"><div align="center"><span class="STYLE1">${row.parkingNumber}</span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.owner}</span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.phone}</span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.managementFeeMonthlyUnit}</span> </div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1"><c:if test="${row.iillegalFlg eq 0}">放行</c:if><c:if test="${row.iillegalFlg eq 1}">不允许进入</c:if></span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1"><c:if test="${row.validityFlg eq 0}">有效</c:if><c:if test="${row.validityFlg eq 1}">无效</c:if></span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.parkingAddrInfo}</span> </div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.parkingPropertyInfo}</span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">${row.parkingInfo}</span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1"><fmt:formatDate  value="${row.effect_begin_time}" type="both" pattern="yyyy-MM-dd"/></span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1"r><fmt:formatDate  value="${row.effect_end_time}" type="both" pattern="yyyy-MM-dd"/></span></div></td>
										<td style="width:5%"><div align="center"><span class="STYLE1">
				  <c:if test="${row.isUsed eq 0}">
					  无效
				  </c:if>
				  <c:if test="${row.isUsed eq 1}">
					  有效
				  </c:if>
			  </span></div></td>
											<%-- <td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1"><c:if test="${row.onlinePayment eq 0 }">不开通</c:if><c:if test="${row.onlinePayment eq 1 }">开通</c:if></span></div></td> --%>
										<security:authorize access="hasAnyRole('AUTH_DEL_0024','AUTH_EDIT_0025','AUTH_EDIT_0119')">
											<td style="width: 13%"><div align="center"><span class="STYLE4">
            	<security:authorize access="hasAnyRole('AUTH_EDIT_0025')">
					<c:if test="${row.isUsed eq 1}">
						<a href="<%=basePath %>products/parking/equity/toedit.html?villageId=${row.villageId}&carNumber=${row.carNumber }"><span class="icon_bg blue"><img  class="icon" title="编辑" src="<%=basePath%>/images/new/compile.png"></span></a>
					</c:if>
				</security:authorize>
       			<security:authorize access="hasAnyRole('AUTH_EDIT_0119')">
					<c:if test="${row.isUsed eq 1}">
						<a onclick="addFree('${row.villageId}',
								'${row.carNumber}',
								'<fmt:formatDate  value="${row.effect_begin_time}" type="both" pattern="yyyy-MM-dd"/>',
								'<fmt:formatDate  value="${row.effect_end_time}" type="both" pattern="yyyy-MM"/>',
								'${row.managementFeeMonthlyUnit}','${row.villageName}',
								'<fmt:formatDate  value="${row.effect_end_time}" type="both" pattern="yyyy-MM-dd"/>')" >
							<span  class="icon_bg green"><img  class="icon" title="续费" src="<%=basePath%>/images/new/need_fee.png" style="cursor: pointer;"></span></a>
					</c:if>
				</security:authorize>
       			<security:authorize access="hasAnyRole('AUTH_DEL_0024')">
					<a href="javascript:if (confirm('你确定？')) location.href='<%=basePath %>products/parking/equity/del.html?villageId=${row.villageId}&carNumber=${row.carNumber }&isUsed=${row.isUsed}';">
						<c:if test="${row.isUsed eq 1}"><span  class="icon_bg red"> <img  class="icon" title="作废" src="<%=basePath%>/images/new/delete.png"></span></c:if>
						<c:if test="${row.isUsed eq 0}">   <span class="icon_bg_recover caolv">
                                <img class="icon" title="恢复" src="<%=basePath%>/images/new/recover.png">
                               <div class="btn_hf">恢复</div>
                            </span></c:if>
					</a>
				</security:authorize></span></div>
											</td>
										</security:authorize>

									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
										<div align="center"><span class="STYLE1">没有信息</span></div>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>

					</table>
				</td>
			</tr>
		</table>
	</td>
	</tr>
	<tr>
		<jsp:include page="/frame/page.html" />
		</td>
	</tr>

	</table>
</form>
<div class="content1" id="addCoupon">
	<div>
		<!-- 弹出层名称 -->
		<div style="height: 50px;background-color:#3f4752;line-height: 50px;text-indent:1em;text-align: left;">
			<span  style="color: #FFF">车位续费</span>
			<img  id="close" src="<%=basePath%>/images/new/close.png" class="close_img"  onmouseover="this.style.cursor='pointer'">
		</div>
		<div style="margin-top: 15px; margin-left: 30px;">
			<form method="post"
				  action="<%=basePath%>products/parking/equity/addFree.html" id="formActions">
				<table>
					<tr>
						<td ><div align="center">订单类型：</div></td>
						<td >
							<select name="orderType" id="orderType"  class="select_cc"  readonly>
								<option value="14" selected>产权</option>
							</select>
						</td>
						<td ><div style="float: right">车牌号：</div></td>
						<td >
							<input type="text"  readonly maxlength="20"  class="select_cc"
								   id="carNumber" name="carNumber">
						</td>
					</tr>
					<tr>
						<td ><div >小区名称：</div></td>
						<td >
							<input type="text" readonly    maxlength="20"  class="select_cc villageName"
								    name="villageName" >
						</td>

						<td ><div  >收款平台：</div></td>
						<td >
							<select name="payType" class="select_cc" id="payType"  readonly>
								<option value="03" selected>线下</option>
								<%--<option value="04">其他</option>--%>
							</select>
						</td>
					</tr>
					<tr>
						<td><div >单价：</div></td>
						<td >
							<input type="text"   maxlength="40" readonly  class="select_cc"
								   name="price" id="price" >
						</td>

						<td ><div>有效期开始时间：</div></td>
						<td >
							<input type="hidden" id="form_beginTime1" name="form_beginTime1"/>
							<input type="text" value="" valid="required"  class="select_cc"    errmsg="有效开始时间不能为空!"
								   id="form_beginTime" name="form_beginTime"
								   style="" <%--onchange="getmonthly()"
								   onclick="WdatePicker({el:form_beginTime,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'form_endTime\')}'})"--%> />
						</td>
					</tr>
					<tr>
						<td ><div >缴纳月份：</div></td>
						<td >
							<input type="text"  class="select_cc"  readonly name="monthNum" id="monthNum"/>
						</td>

						<td ><div >有效期结束时间：</div></td>
						<td >
							<input type="text" valid="required"  class="select_cc"   errmsg="有效结束时间不能为空!"
								   id="form_endTime" name="form_endTime"
								   style="" onchange="getmonthly()"
								   onclick="WdatePicker({el:form_endTime,dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'form_beginTime1\')}'})" />
						</td>
					</tr>
					<tr>
						<td ><div  >订单状态：</div></td>
						<td >
							<select name = "orderStatus" id="orderStatus" class="select_cc"   readonly >
								<option value="11" selected >已付款</option>
							</select>
						</td>
						<td ><div >优惠金额：</div></td>
						<td >
							<input type="text" id="amountDiscount"  class="select_cc"   readonly   value="0"
								   name="amountDiscount">
						</td>
					</tr>
					<tr>
						<td ><div >发票开具信息：</div></td>
						<td >
							<select name="invoiceStatus" id="invoiceStatus"  class="select_cc">
								<option value="0" selected>未开具</option>
								<option value="1">已开具</option>
							</select>
						</td>
						<td ><div >支付时间：</div></td>
						<td >
							<input type="text" id="payTime"  class="select_cc"  valid="required"  errmsg="支付时间不能为空!" maxlength="20"   value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %>" onclick="WdatePicker({minDate: '%y-%M-%d',el:payTime,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
								   name="payTime"  />
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
						</td>
						<td>
						</td>
						<td>
							<input class="btn_check" style="letter-spacing: 5px; font-weight: bold;" type="button" value="确定" id="saveCoupon" />
							<input class="btn_check"  style="letter-spacing: 5px; font-weight: bold;" type="button" value="取消" id="cancel" />
							<input class="btn_check" type="hidden" id="text1" name = "text1"/>
						</td>
					</tr>


				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>
