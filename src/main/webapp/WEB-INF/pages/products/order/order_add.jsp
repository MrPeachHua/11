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
    
    <title>增加订单信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	  <link href="<%=basePath%>css/file.css" type=text/css rel=stylesheet>
	  <link href="<%=basePath%>css/carList.css" ype=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
  </head>
  <script type="text/javascript">
  function getPrice(){
  var orderType=$("#orderType").val();
  var carNumber=$("#carNumber").val();
  var parkingId=$("#parkingId").val();
//   var monthNum=$("#monthNum").val();
  if(carNumber!=null&&carNumber!=''&&parkingId!=null&&parkingId!=''){
	  $.ajax({
          cache: false,
          type: "POST",
          url:'products/order/getPrice.html',
          data:{"carNumber":carNumber,"orderType":orderType,"parkingId":parkingId},
         dataType: "json",   
        success: function (jsonStr) {
        	 $("#price").val(jsonStr);
//         	 $("#amountPayable").val(jsonStr.yingfu);
//         	 $("#amountPaid").val(jsonStrshifu);
          }
      });
      }else{
    	  alert("请先选择小区！");
    	  $("#carNumber").val(null);
      }
  }
  
// function   youhui(){
// var amountPayable= $("#amountPayable").val();
// var amountDiscount= $("#amountDiscount").val();
// if(amountPayable!=null&&amountPayable!=''){
// $("#amountPaid").val(amountPayable-amountDiscount);
// }
// }
   function   getMonthNum(){
  var beginDate=$("#beginTime").val();
  var monthNum=$("#monthNum").val();
  // var price=$("#price").val();
   if(price==null||price==''){
   price=0;
   }
   if(beginDate!=null&&beginDate!=''){
	  $.ajax({
          cache: false,
          type: "POST",
          url:'products/order/getMonthNum.html',
          data:{"beginDate":beginDate,"monthNum":monthNum},
         dataType: "json",   
        success: function (jsonStr) {
        	 $("#endTime").val(jsonStr.endDate);
//         	 $("#amountPayable").val(jsonStr.amountPayable);
//         	 $("#amountPaid").val(jsonStr.amountPaid);
          }
      });
      }
  }
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
	        },
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
  <body>
  
<form action="<%=basePath%>products/order/save.html"  name="form1" onsubmit="return validator(this)" method="post" >
<table class=editTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入订单信息<span style="color: red;"></span></td>
	</tr>  
    <tr>
     <td ><div align="center">订单类型：</div></td>
		<td colspan="3">
			  <select name="orderType" id="orderType" class="selectList" >
			 <option value="13">月租</option>
			 <option value="14">产权</option>
			 </select>
		</td>
	  	<td ><div align="center">车牌号：</div></td>
		<td colspan="3">
			<input type="text"   maxlength="20"  class="selectList"   valid="required"  errmsg="车牌号不能为空!"
				id="carNumber" name="carNumber" onBlur="getPrice()">
		</td>
		 </tr>    
    <tr>
     <td ><div align="center">小区名称：</div></td>
		<td colspan="3">
			 <input type="text"   maxlength="20"  class="selectList"   valid="required"  errmsg="小区名称不能为空!"
				id="villageName" name="villageName" onclick="parkingPop()" >
				<input type="hidden"  
					  name = "parkingId" id="parkingId"/>
		</td>
	  	<td ><div align="center">收款平台：</div></td>
		<td colspan="3">
			 <select name="payType" id="payType"  class="selectList">
			 <option value="03">线下</option>
			 <option value="04">其他</option>
			 </select>
		</td>
   </tr>
		  <tr>
	  	<td ><div align="center">单价：</div></td>
		<td colspan="3">
			<input type="text"   maxlength="40"  class="selectList"    readonly="readonly"
				name="price" id="price" >
		</td>
		
	  	<td ><div align="center">有效期开始时间：</div></td>
		<td colspan="3">
			<input type="text" id="beginTime"  maxlength="20"  class="selectList"    onclick="WdatePicker({el:beginTime,dateFmt:'yyyy-MM'})"
				name="beginTime" onchange="getMonthNum()" valid="required"  errmsg="有效开始时间不能为空!" >
		</td>
   </tr>  
		    <tr>
	  	<td ><div align="center">缴纳月份：</div></td>
		<td colspan="3">
		   <select name="monthNum" id="monthNum" class="selectList"  onchange="getMonthNum()">
			 <option value="1">1</option>
			 <option value="2">2</option>
			  <option value="3">3</option>
			 <option value="4">4</option>
			  <option value="5">5</option>
			 <option value="6">6</option>
			  <option value="7">7</option>
			 <option value="8">8</option>
			  <option value="9">9</option>
			 <option value="10">10</option>
			  <option value="11">11</option>
			 <option value="12">12</option>
			 
			 </select>
		</td>
		
	  	<td ><div align="center">有效期结束时间：</div></td>
		<td colspan="3">
			<input type="text" id="endTime"  maxlength="20"   class="selectList"   readonly="readonly"
				name="endTime"   >
		</td>
	  </tr>
    <tr>
	  	<td ><div align="center">订单状态：</div></td>
		<td colspan="3">
			<select name = "orderStatus" id="orderStatus" class="selectList" valid="required"  errmsg="订单状态不能为空!">
			        		<option value=""> </option>
			        		<c:forEach items="${orderStatus}" var="orderStatus">
			        		<option value="${orderStatus.dictValue }">${orderStatus.dictName}</option>
			        		</c:forEach>
			        	</select> 
		</td>
	  	 
    <!-- 
	  	<td ><div align="center">应付金额：</div></td>
		<td colspan="3">
			<input type="text"   maxlength="40"    readonly="readonly"
				name="amountPayable"  id="amountPayable">
		</td>
    </tr>   
    <tr> -->
	  	<td ><div align="center">优惠金额：</div></td>
		<td colspan="3">
			<input type="text" id="amountDiscount"  class="selectList"   maxlength="40"    value="0" onchange="youhui()"
				name="amountDiscount">
		</td>
	
	  	<!-- <td ><div align="center">实付金额 ：</div></td>
		<td colspan="3">
			<input type="text" id="amountPaid"  maxlength="40"    readonly="readonly"
				name="amountPaid" >
		</td> -->
   	 </tr>   
    <tr>
	  	<td ><div align="center">发票开具信息：</div></td>
		<td colspan="3">
			  <select name="invoiceStatus" id="invoiceStatus" class="selectList" >
			 <option value="0">未开具</option>
			 <option value="1">已开具</option>
			 </select>
		</td>
			<td ><div align="center">支付时间：</div></td>
		<td colspan="3">
			<input type="text" id="payTime"  maxlength="20"   class="selectList"   onclick="WdatePicker({el:payTime,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
				name="payTime"  />
		</td>
    </tr>
	<tr class="bottom_btn">
		<td colspan="12">
			<div>
				<input class="btn_blue" type="submit" name="submit"  value="提交">
				<input class="btn_blue" type="reset" name="reset"  value="重置">
			</div>
		</td>
	</tr>
</table>

</form>
  </body>
</html>

