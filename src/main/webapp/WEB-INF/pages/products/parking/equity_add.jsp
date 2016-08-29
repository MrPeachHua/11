<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
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
    
    <title>增加产权车位信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<link href="<%=basePath%>css/carList.css" ype=text/css rel=stylesheet>
	  <LINK href="<%=basePath%>css/file.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
	  <script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>
  </head>
  <body>
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
            	for (var i = 0; i < jsonStr.length; i++) {
						$("table#myTable tr:last").after('<tr class = "parkingListTr" style="text-align:center"><td bgcolor="#FFFFFF">' + jsonStr[i].parkingId + '</td><td bgcolor="#FFFFFF">' + jsonStr[i].parkingName + '</td><td bgcolor="#FFFFFF">' + jsonStr[i].parkingArea + '</td><td bgcolor="#FFFFFF">' + jsonStr[i].parkingAddress + '</td><td bgcolor="#FFFFFF">' + jsonStr[i].parkingCount + '</td><td bgcolor="#FFFFFF">' + jsonStr[i].parkingCanUse + '</td><td bgcolor="#FFFFFF">' + jsonStr[i].parkingStatus + '</td><td bgcolor="#FFFFFF">' +
							'<div class="btn_cao"><img src="<%=imagePath%>new/compile.png" width="16" height="16" /><span style="cursor:pointer" onclick="choiceParkingCode(\'' + jsonStr[i].parkingName + '\',\'' + jsonStr[i].parkingId + '\')">选择</span></div></td></tr>');
					}
        }
    });
	$("#bg").css({
        display: "block"
    });
  }

  function choiceParkingCode(text1,text2){
	  $("#villageName").val(text1);
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
   </script>
<body>
<div id="bg"></div>
		<div class="content" id="parkingForm">
			<form action="<%-- <%=basePath%>products/parking/list.html --%>" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="table_head" height="30" margin="0" padding="0">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr class="tb_head_h">
									<td width="12" height="30">
										<!--<img src="<%=imagePath%>tab_03.gif" width="12" height="30" />-->
									</td>
									<td>
										<table  border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="STYLE4">&nbsp;&nbsp;<span class="head_text_comm">请输入查询内容：</span><input type="text" name="queryValue" id="queryValue" /></td>
												<td class="STYLE4">&nbsp;&nbsp;<span class="head_text_comm">请选择查询方式：</span><select name="queryType" id="queryType" style="width: 100px">
													<option value="1">车场代码</option>
													<option value="2">车场名称</option>
													<option value="3">车场地址</option>
											</select>
												</td>
												<td class="STYLE4">&nbsp;&nbsp; <input class="btn_comm" type="button" onclick="parkingPop()" value="查询" />
												</td>
												<td class="STYLE4">&nbsp;&nbsp; <input class="btn_comm" type="button" value="取消"  onclick="cancelButton()" />
												</td>
											</tr>
										</table>
									</td>
									<td width="16">
										<!--<img src="<%=imagePath%>tab_07.gif" width="16" height="30" />-->
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<!--<td width="8">&nbsp;</td>-->
									<td>
										<table  id="mytable" width="100%" border="0" cellpadding="0" cellspacing="0"  onmouseover="changeto()" onmouseout="changeback()">
											<tr class="mytable_head">
												<td class="table_cont_head" bgcolor="#FFFFFF" style="width: 10%; height: 22px;">
													<div align="center">
														<span class="STYLE1">车场代码</span>
													</div>
												</td>
												<td class="table_cont_head" bgcolor="#FFFFFF" style="width: 15%; height: 22px;">
													<div align="center">
														<span class="STYLE1">车场名称</span>
													</div>
												</td>
												<td class="table_cont_head" bgcolor="#FFFFFF" style="width: 15%; height: 22px;">
													<div align="center">
														<span class="STYLE1">小区</span>
													</div>
												</td>
												<td class="table_cont_head" bgcolor="#FFFFFF" style="width: 20%; height: 22px;">
													<div align="center">
														<span class="STYLE1">地址</span>
													</div>
												</td>
												<td class="table_cont_head" bgcolor="#FFFFFF" style="width: 10%; height: 22px;">
													<div align="center">
														<span class="STYLE1">车位数</span>
													</div>
												</td>
												<td class="table_cont_head" bgcolor="#FFFFFF" style="width: 10%; height: 22px;">
													<div align="center">
														<span class="STYLE1">空位数</span>
													</div>
												</td>
												<td class="table_cont_head" bgcolor="#FFFFFF" style="width: 10%; height: 22px;">
													<div align="center">
														<span class="STYLE1">车位状态</span>
													</div>
												</td>
												<td class="table_cont_head" width="10%"  bgcolor="#FFFFFF" class="STYLE1" style="width: 10%; height: 22px;">
													<div align="center">基本操作</div>
												</td>
											</tr>
										</table>
									</td>
									<!--<td width="8"></td>-->
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="35">
							<%-- <jsp:include page="/frame/page.html" /> --%>
						</td>
					</tr>
				</table>
			</form>	
			<script>
        var highlightcolor = '#c1ebff';
        //此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
        var clickcolor = '#51b2f6';
        function changeto() {
            source = event.srcElement;
            if (source.tagName == "TR" || source.tagName == "TABLE")
                return;
            while (source.tagName != "TD")
                source = source.parentElement;
            source = source.parentElement;
            cs = source.children;
//alert(cs.length);
            if (cs[1].style.backgroundColor != highlightcolor && source.id != "nc" && cs[1].style.backgroundColor != clickcolor)
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = highlightcolor;
                }
        }

        function changeback() {
            if (event.fromElement.contains(event.toElement) || source.contains(event.toElement) || source.id == "nc")
                return
            if (event.toElement != source && cs[1].style.backgroundColor != clickcolor)
//source.style.backgroundColor=originalcolor
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = "";
                }
        }
    </script>	
		</div>


<form action="<%=basePath%>products/parking/equity/save.html"  name="form1" onsubmit="return validator(this)" method="post" >
<table class=editTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入车位信息<span style="color: red;"></span></td>
	</tr>
    <tr>
     <td><div><font color="#39d5b8">*</font>小区名称：</div></td>
		<td colspan="3">
			 <input type="text"   maxlength="20" class="selectList"   valid="required"  errmsg="小区名称不能为空!"
				id="villageName" name="villageName" onclick="parkingPop()" >
				<input type="hidden" valid="required"
					errmsg="车场名称不能为空!" name = "villageId" id="parkingId"/>
		</td>
	  	<td><div><font color="#39d5b8">*</font>车牌号：</div></td>
		<td colspan="3">
			<input type="text"   maxlength="20" class="selectList"    valid="required"  errmsg="车牌号不能为空!"
				id="carNumber" name="carNumber" >
		</td>
		 </tr>
    <tr>
    <td><div>车主姓名：</div></td>
		<td colspan="3">
					<input class="selectList"
						   id="owner" name="owner"  />

				</td>

	  	<td><div>身份信息：</div></td>
		<td colspan="3">
			 <input class="selectList"
					id="certificate" name="certificate"  />
		</td>
   </tr>
		  <tr>
	  	<td><div>车主联系地址：</div></td>
		<td colspan="3">
			<input type="text"   maxlength="40"  class="selectList"
				name="address" id="address" >
		</td>

	  	<td><div>车主联系电话：</div></td>
		<td colspan="3">
			<input type="text" id="phone" class="selectList"
				   name="phone" >
		</td>
   </tr>
		    <tr>
	  	<td><div>车辆颜色：</div></td>
		<td colspan="3">
		   <select name="carColor" id="carColor" class="selectList" >
			 <option value="1">黑色</option>
			 <option value="2">白色</option>
			 <option value="3">其他</option>
			 </select>
		</td>

	  	<td><div><font color="#39d5b8">*</font>月租单价：</div></td>
		<td colspan="3">
			<input type="text" id="managementFeeMonthlyUnit"  class="selectList"       valid="required"  errmsg="月租单价不能为空!"
				name="managementFeeMonthlyUnit"   />
		</td>
	  </tr>
    <tr>
	  	<td><div>是否有违规情况：</div></td>
		<td colspan="3">
			<select name = "iillegalFlg" id="iillegalFlg" class="selectList" >
			        		<option value="0">放行 </option>
			        		<option value="1">不允许放行</option>
			        	</select>
		</td>

	  	<td><div>当月有效标识：</div></td>
		<td colspan="3">
			<select name = "validityFlg" id="validityFlg"  class="selectList"  >
			        		<option value="0">有效 </option>
			        		<option value="1">无效</option>
			        	</select>
		</td>

	  	<!-- <td><div>实付金额 ：</div></td>
		<td colspan="3">
			<input type="text" id="amountPaid"  maxlength="40"  style=" width: 165px"  readonly="readonly"
				name="amountPaid" >
		</td> -->
   	 </tr>
   	 <tr>
	  	<td><div>车位地址信息：</div></td>
		<td colspan="3">
				<input type="text" id="parkingAddrInfo" class="selectList"   maxlength="20"
				name="parkingAddrInfo"  />
		</td>
		<td><div>车位产权信息：</div></td>
		<td colspan="3">
				<input type="text" id="parkingPropertyInfo"  class="selectList"  maxlength="20"
				name="parkingPropertyInfo"  />
		</td>
    </tr>
    <tr>
	  	<td><div>购买日期：</div></td>
		<td colspan="3">
				<input type="text" id="purchaseDate" class="selectList"   maxlength="20"
				name="purchaseDate1"  onclick="WdatePicker({el:purchaseDate,dateFmt:'yyyy-MM-dd'})"  />
		</td>
		<td><div>车位物业信息：</div></td>
		<td colspan="3">
				<input type="text" id="parkingInfo" class="selectList"   maxlength="20"
				name="parkingInfo"  />
		</td>
    </tr>
    <tr>
	  	<td><div>开始时间：</div></td>
		<td colspan="3">
				<input type="text" id="effect_begin_time" class="selectList"   maxlength="20" value="${date}"     onclick="WdatePicker({el:effect_begin_time,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'effect_end_time\')}'})"
				name="effect_begin_time1"  />
		</td>
			<td><div>结束时间：</div></td>
		<td colspan="3">
			<input type="text" id="effect_end_time" class="selectList"   maxlength="20" value="${date}"   onclick="WdatePicker({el:effect_end_time,dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'effect_begin_time\')}'})"
				name="effect_end_time1"  />
		</td>
    </tr>
      <tr>
	  	<td><div>车位号：</div></td>
		<td colspan="3">
				<input type="text" id="parkingNumber"
				name="parkingNumber" class="selectList"   />
		</td>
		<td><div>最大付款时间：</div></td>
		<td colspan="3">
			<input type="text" id="max_date" class="selectList"   readonly="readonly"  onclick="WdatePicker({el:max_date,dateFmt:'yyyy-MM'})"
				name="max_date1"  />
		</td>
		</tr>
		<tr>
		<td><div>是否开通线上支付：</div></td>
		<td colspan="3">
		<select name = "onlinePayment" id="onlinePayment"   class="selectList"   >
		<option value="1">开通</option>
			        		<option value="0">不开通 </option>

			        	</select>

		</td>
		<td><div>开票抬头：</div></td>
		<td colspan="6">
			<input type="text" id="makeUp" class="selectList"    maxlength="40"     value=""
				name="makeUp">
		</td>
    </tr>
	<tr>
		<td align="right">
			<div>
				所属系统：
			</div>
		</td>
		<td colspan="7" bgcolor="#FFFFFF">
			<select name="module"   class="dictionary selectList" data-dict-code="module" data-current-value="${shareUser.module}"  data-dict-value="${shareUser.module}" >
				<c:if test="${shareUser.module =='' || shareUser.module ==null }" ><option value=""></option></c:if>
			</select>
			&nbsp;
		</td>
	</tr>
	<tr class="bottom_btn">
		<td colspan="12">
			<div>
				<input class="btn_blue" type="submit" name="submit"  value="添加">
				<input class="btn_blue" type="reset" name="reset"  value="重置">
				<input class="btn_blue" type="button" name="back" onclick="history.back()" value="返回">
			</div>
		</td>
	</tr>
</table>
</form>
  </body>
</html>

