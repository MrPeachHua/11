<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  --%>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>增加车场服务信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>js/FormValid.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>

<script type="text/javascript">
  var  highlightcolor='#c1ebff';
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
  function SrvinfoPop(){
  var queryValue=$("#p_queryValue").val();
	var queryType = $("#p_queryType").val();
	document.getElementById('srvinfo').style.display='block';
	$.ajax({  
        type: "POST",  
        url: "<%=basePath%>products/carlife/srvbilling/srvinfoPop.html",
        data:{"queryValue":queryValue,"queryType":queryType},
        dataType: "json",   
        success: function (jsonStr) {
          $(".srvinfoListTr").remove();
            	for(var i=0;i<jsonStr.length;i++){ 
            	var a;
            	var b;
            	var c;
            	if(jsonStr[i].status==1){
            	 a ='已上线';
            	}else{
            	 a='预上线';
            	}
                if(jsonStr[i].srvType==10){
            	 b ='保养类';
            	}else{
            	 b='咨询类';
            	}
            	 if(jsonStr[i].srvLink==null){
            	 c ='';
            	}else{
            	 c=jsonStr[i].srvLink;
            	}
            		$("table#mysTable tr:last").after('<tr class = "srvinfoListTr" style="text-align:center"><td bgcolor="#FFFFFF"><div style="width:80px">'+jsonStr[i].id+'</div></td><td bgcolor="#FFFFFF">'+jsonStr[i].srvName+'</td><td bgcolor="#FFFFFF">'+b+'</td><td bgcolor="#FFFFFF"><span title='+jsonStr[i].intro+' ><div align="center" style="width:180px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis;">'+jsonStr[i].intro+'</div></span></td><td bgcolor="#FFFFFF">'+a+'</td><td bgcolor="#FFFFFF"><span title='+jsonStr[i].description+' ><div align="center" style="width:180px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis;">'+jsonStr[i].description+'</div></span></td><td bgcolor="#FFFFFF"><span title='+jsonStr[i].srvLink+' ><div align="center" style="width:120px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis;">'+c+'</div></span></td><td bgcolor="#FFFFFF">'+
            				'<img src="<%=imagePath%>edt.gif" width="16" height="16" /><span style="cursor:pointer" onclick="choiceSrvinfoId(\''+jsonStr[i].srvName+'\',\''+jsonStr[i].id+'\')">选择</span></td></tr>');
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
   function choiceSrvinfoId(text1,text2){
	  $("#srvinfoCode").val(text1);
	  $("#srvId").val(text2);
	  $("#srvinfo").hide();
	  $(".srvinfoListTr").remove();
	  $("#bg").css("display", "none");
  }
  function cancelButton(){
	  $("#parkingForm").hide();
	  $(".parkingListTr").remove();
	  $("#bg").css("display", "none");
  }
   function cancel(){
	  $("#srvinfo").hide();
	  $(".srvinfoListTr").remove();
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
	<div  class="content" id="srvinfo">
  	<form  action="<%-- <%=basePath%>products/carlife/srvinfo/srvinfoPop.html" method="post" --%>">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
    	<td height="30" background="<%=imagePath%>tab_05.gif">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
	        <td>
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td class="STYLE4" align="center">&nbsp;&nbsp;请输入查询内容：<input type="text" id="p_queryValue" name="p_queryValue" style="width: 290px"/></td>
			        <td class="STYLE4">&nbsp;&nbsp;请选择查询方式：
			        	<select id="p_queryType" name="p_queryType" style="width: 100px">
			  				<option  value="1">服务名称</option>
						</select>            
					</td>
			        <td class="STYLE4">&nbsp;&nbsp;<input  type="button" value="查询"  onclick="SrvinfoPop()" style="width:50px"/></td>    
			       <td class="STYLE4">&nbsp;&nbsp; <input type="button"
												value="取消" style="width: 50px" onclick="cancel()"/>
											</td>       
			      </tr>
			    </table>
			</td>
	        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" /></td>
	      </tr>
    	</table>
    	</td>
  	  </tr>

  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
        <td>
          <table width="100%" border="0" id="mysTable" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">服务ID</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1"> 服务名称</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">所属分类</span></div></td> 
	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">服务简介</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">服务状态</span></div></td> 
	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">服务标准介绍</span></div></td>
	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 10%"><div align="center"><span class="STYLE1">服务链接地址</span></div></td>
           <!-- <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">修改日期</span></div></td> -->
         <%--  <td <c:if test="${shareUser.rolePower > 2}"> style=" display: none  "  </c:if> --%><td  height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 10%"><div align="center">基本操作</div></td>
          </tr>
		 <%--    <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
          <tr>
           <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${status.count}</span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.srvName}</span></div></td>
	        <td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center"><c:if test="${row.srvType eq 10}">保养类</c:if> <c:if test="${row.srvType eq 11}">咨询类</c:if></div></td> 
	  	  	<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.intro}</span> </div></td>
	        <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><c:if test="${row.status eq 1}">已上线</c:if> <c:if test="${row.status eq 0}">预上线</c:if></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.description}</span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  10%"><div align="center"><span class="STYLE1"><a href="${row.srvLink}">${row.srvLink}</a></span></div></td>
	    <!--	<td height="20" bgcolor="#FFFFFF" style="width: 10%"><div align="center"><span class="STYLE1">${row.modifyDate}</span></div></td>-->
            <td <c:if test="${shareUser.rolePower > 2}"> style=" display: none  "  </c:if> height="20" bgcolor="#FFFFFF" style="width: 10%"><div align="center"><span class="STYLE4">
            	<img src="<%=imagePath%>edt.gif" width="16" height="16" />
            	<a href="<%=basePath %>products/carlife/srvinfo/${row.id}/edit.html">编辑</a>
            	&nbsp;
            	<img src="<%=imagePath%>del.gif" width="16" height="16" />
            	<a href="<%=basePath %>products/carlife/srvinfo/${row.id}/del.html">删除</a></span></div>
            </td>
          </tr>
	      </c:forEach>
		  </c:when>
          <c:otherwise> --%>
          </table>
        </td>
        <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="35" background="<%=imagePath%>tab_19.gif">
	<%-- <jsp:include page="/frame/page.html" /> --%>
    </td>
  </tr>

    </table>  
    </form>
	
	</div>
	<form action="<%=basePath%>products/carlife/srvbilling/save.html"
		name="form1" id = "formSubmit" onsubmit="return validator(this)" method="post">
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr class=editHeaderTr>
				<td class=editHeaderTd colSpan=7>请输入车场服务费信息<span
					style="color: red;"><s:fielderror /></span></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input  readonly="readonly"
					onclick="parkingPop()" style="width: 165px" id="parkingCode"/>
					<input type="hidden" valid="required"
					errmsg="车场名称不能为空!" name = "parkingId" id="parkingId"/>
				</td>
				<td bgcolor="#FFFDF0"><div align="center">服务名称：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					   style="width: 165px" onclick="SrvinfoPop()" readonly="readonly"
					id="srvinfoCode"></td>
					<input type="hidden" valid="required"
					errmsg="车场名称不能为空!" name = "srvId" id="srvId"/>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">车型：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><select style="width: 165px"
					name="carType">
						<c:forEach var="dictionary" items="${dictionary }">
							<option value="${dictionary.dictValue}">${dictionary.dictName}</option>
						</c:forEach>
				</select></td>
				<td bgcolor="#FFFDF0"><div align="center">服务费：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text" value="0"
					valid="required" errmsg="服务费不能为空!"  style="width: 165px" name="srvFee"onkeyup="value=value.replace(/[^\d]/g,'') "
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">服务价格：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text" value="0"
					valid="required" errmsg="服务价格不能为空!" name="srvPrice"   style="width: 165px" onkeyup="value=value.replace(/[^\d]/g,'') "
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"></td>
				<td bgcolor="#FFFDF0"><div align="center">现在价格：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text" value="0"
														 valid="required" errmsg="现在价格不能为空!" name="nowPrice"   style="width: 165px" onkeyup="value=value.replace(/[^\d]/g,'') "
														 onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"></td>
			</tr>
		</table>

		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr bgcolor="#ECF3FD">
				<td width="25%"></td>
				<td width="17%"><input type="submit" name="submit" value="添加"></td>
				<td width="17%"><input type="reset" name="reset" value="重置"></td>
				<td width="4%"><input type="button" name="button"
					onClick="history.back() " value="返回"></td>
				<td width="43%"></td>
			</tr>
		</table>
	</form>

</body>
</html>

