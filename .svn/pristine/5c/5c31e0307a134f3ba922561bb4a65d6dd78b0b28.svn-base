<%@page import="com.boxiang.share.system.po.Dictionary"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>客户信息</title>
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
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
	<script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

a {
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60;
	TEXT-DECORATION: underline
}

.table_border tr td {
	border: solid 1px #B5D6E6;
}

.table_border {
	border: solid 1px #B5D6E6;
}
</style>
<script type="text/javascript">
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

/* 导出excel表 */
function excelExport(){
	var customerMobile = $("#customerMobile").val();
	var customerCardId = $("#customerCardId").val();
	var appVersion = $("#appVersion").val();
	var registerBegin = $("#p_registerBegin").val();
	var registerEnd = $("#p_registerEnd").val();
	var loginBegin = $("#p_loginBegin").val();
	var loginEnd = $("#p_loginEnd").val();
	window.location="<%=basePath%>customer/info/excelExport.html?customerMobile="+customerMobile+"&customerCardId="+customerCardId+"&appVersion="+appVersion+"&registerBegin="+registerBegin+"&registerEnd="+registerEnd+"&loginBegin="+loginBegin+"&loginEnd="+loginEnd;
}
</script>
</head>
<body>
	<div style="height: 100px;">
		<form action="<%=basePath%>customer/info/list.html" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="60" background="<%=imagePath%>tab_05.gif">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="12" height="30"><img
									src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										height="60">
										<tr>
											<%--<td class="STYLE4">
												&nbsp;&nbsp;客户ID：<input type="text" name="customerId"
												id = "customerId" value='${page.params["customerId"] }'
												style="width: 130px;"/>
											</td>--%>
											<td class="STYLE4">&nbsp;&nbsp;手机号： <input type="text"
												name="p_customerMobile" id="customerMobile"
												value='${page.params["customerMobile"] }'
												style="width: 130px" />
											</td>
												<td class="STYLE4">
													身份证号码：<input type="text" name="p_customerCardId"
																			 id = "customerCardId" value='${page.params["customerCardId"] }'
																			 style="width: 130px;"/>
												</td>
											<%--<td class="STYLE4">
												&nbsp;&nbsp;用户级别：<input type="text" name="customerLeave"
												id = "customerLeave" value='${page.params["customerLeave"] }'
												style="width: 130px;"/>
											</td>--%>

												<td class="STYLE4">
													&nbsp;&nbsp;当前使用版本：<input type="text" name="p_appVersion"
																			  id = "appVersion" value='${page.params["appVersion"] }'
																			  style="width: 130px;"/>
												</td>
											<td class="STYLE4" style="width: 90px"></td>
											<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_VIEW_0071')"><input type="submit" value="查询"
												style="width: 90px" /></security:authorize></td>

											<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_EXP_0072')"><input type="button"
												onclick="excelExport()" value="导出Excel表" style="width: 90px" /></security:authorize></td>
										</tr>
										<tr>
											<td class="STYLE4" > &nbsp;&nbsp;注册时间： <input  style="width: 90px;" type="text" placeholder="开始时间" id="p_registerBegin" name="p_registerBegin" value='${page.params["registerBegin"] }'  readonly="readonly"  onclick="WdatePicker({el:p_registerBegin,dateFmt:'yyyy-MM-dd'})" />
												 —  &nbsp;&nbsp;&nbsp;
												<input type="text"  style="width: 90px;" placeholder="截止时间" id="p_registerEnd" name="p_registerEnd"   readonly="readonly" value='${page.params["registerEnd"] }'  onclick="WdatePicker({el:p_registerEnd,dateFmt:'yyyy-MM-dd'})"/>
											</td>
											<td class="STYLE4" > &nbsp;&nbsp;登录时间： <input type="text" style="width: 90px;" placeholder="开始时间" id="p_loginBegin" name="p_loginBegin" value='${page.params["loginBegin"] }'    readonly="readonly"  onclick="WdatePicker({el:p_loginBegin,dateFmt:'yyyy-MM-dd'})"/>
												 — &nbsp;&nbsp;&nbsp;
												<input type="text" placeholder="截止时间" style="width: 90px;" id="p_loginEnd" name="p_loginEnd"  readonly="readonly" value='${page.params["loginEnd"] }'  onclick="WdatePicker({el:p_loginEnd,dateFmt:'yyyy-MM-dd'})"/>
											</td>
											<td class="STYLE4">
												<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户级别：--%><%--<input type="text" name="p_customerLevel"
																		id = "customerLeave" value='${page.params["p_customerLevel"] }'
																		style="width: 130px;"/>--%>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户级别：
													<select name="p_customerLevel" class="dictionary" data-current-value="${page.params["customerLevel"] }" data-dict-code="customer_level" style="width: 130px">
														<option value=""></option>
													</select>
											</td>
										</tr>
									</table>
								</td>
								<td width="16"><img src="<%=imagePath%>tab_07.gif"
									width="16" height="30" /></td>
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
									<table width="100%" border="0" cellpadding="0" cellspacing="1"
										bgcolor="b5d6e6" onmouseover="changeto()"
										onmouseout="changeback()">
										<tr>
										<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
													<span class="STYLE1">客户ID</span>
												</div></td>
												<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
													<span class="STYLE1">客户昵称</span>
												</div></td>
										<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
													<span class="STYLE1">手机号</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
												<span class="STYLE1">身份证号</span>
											</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
												<span class="STYLE1">用户级别</span>
											</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
													<span class="STYLE1">注册时间</span>
												</div></td>
												<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
													<span class="STYLE1">最后登录时间</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
												<span class="STYLE1">当前使用版本</span>
											</div></td>
												<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
													<span class="STYLE1">余额</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
												<span class="STYLE1">优惠券</span>
											</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
												<span class="STYLE1">登录类型</span>
											</div></td>
												<%--<td height="22" background="<%=imagePath%>bg2.gif"--%>
												<%--bgcolor="#FFFFFF" ><div align="center">--%>
													<%--<span class="STYLE1">微信</span>--%>
												<%--</div></td>--%>
												<%--<td height="22" background="<%=imagePath%>bg2.gif"--%>
												<%--bgcolor="#FFFFFF" ><div align="center">--%>
													<%--<span class="STYLE1">QQ</span>--%>
												<%--</div></td>--%>
												<%--<td height="22" background="<%=imagePath%>bg2.gif"--%>
												<%--bgcolor="#FFFFFF" ><div align="center">--%>
													<%--<span class="STYLE1">微博</span>--%>
												<%--</div></td>--%>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" ><div align="center">
												<span class="STYLE1">终端设备机型</span>
											</div></td>

										</tr>
										<c:choose>
											<c:when test="${fn:length(page.resultList)>0}">
												<c:forEach items="${page.resultList}" var="row"
													varStatus="status">
													<tr>
													<td height="22" bgcolor="#FFFFFF"><div
																align="center">
																<span class="STYLE1">
																<a href="<%=basePath%>customer/info/customerInfoDetail?customerId=${row.customer_id}">${row.customer_id}</a>
																</span>
															</div></td>
														<td height="22" bgcolor="#FFFFFF"><div
																align="center">
																<span class="STYLE1">${row.customer_nickname}</span>
															</div></td>
														
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
																<span class="STYLE1">${row.customer_mobile}</span>
															</div></td>
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
															<span class="STYLE1">${row.customer_card_id}</span>
														</div></td>
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
															<span class="STYLE1">${row.customer_levelName}</span>
														</div></td>
														<td height="22" bgcolor="#FFFFFF"><div
																align="center">
																<span class="STYLE1"> <c:if
																		test="${row.created_at != '0000-00-00 00:00:00'}">
																	${row.created_at}
																	</c:if>
																</span>
															</div></td>
														
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
																<span class="STYLE1">
																<fmt:formatDate  value="${row.last_login_time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
																</span>
															</div></td>
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
															<span class="STYLE1">${row.app_version}</span>
														</div></td>
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
																<span class="STYLE1">
																	${row.money / 100}
																</span>
															</div></td>
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
															<span class="STYLE1">${row.coupon}</span>
														</div></td>
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
															<span class="STYLE1">${row.last_login_sys}</span>
														</div></td>
														<%--<td height="22" bgcolor="#FFFFFF"><div--%>
																<%--align="center">--%>
																<%--<span class="STYLE1">${row.wxId}</span>--%>
															<%--</div></td>--%>
															<%--<td height="22" bgcolor="#FFFFFF" ><div--%>
																<%--align="center">--%>
																<%--<span class="STYLE1">${row.qqId}</span>--%>
															<%--</div></td>--%>
															<%--<td height="22" bgcolor="#FFFFFF" ><div--%>
																<%--align="center">--%>
																<%--<span class="STYLE1">${row.sinaId}</span>--%>
															<%--</div></td>--%>
														<td height="22" bgcolor="#FFFFFF" ><div
																align="center">
																<span class="STYLE1">
																		${row.last_login_machine}
																</span>
														</div></td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td height="22" bgcolor="#FFFFFF" colspan="9"
														align="center">
														<div align="center">
															<span class="STYLE1">没有客户相关信息</span>
														</div>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</table>
								</td>
								<td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="35" background="<%=imagePath%>tab_19.gif"><jsp:include
							page="/frame/page.html" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
