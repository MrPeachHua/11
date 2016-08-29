<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

		<title>top</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script language=JavaScript>
		var timerID = null;
		var timerRunning = false;
		function stopclock (){
		if(timerRunning)
		clearTimeout(timerID);
		timerRunning = false;}
		function startclock () {
		stopclock();
		showtime();}
		function showtime () {
		var now = new Date();
		var months = now.getMonth()+1;
		var days = now.getDate();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds()
		var timeValue =now.getFullYear()
		timeValue +="-"+months+"-"+days+" "
		timeValue += "" +((hours >= 12) ? "下午 " : "上午 " )
		timeValue += ((hours >12) ? hours -12 :hours)
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds
		document.clock.thetime.value = timeValue;
		timerID = setTimeout("showtime()",1000);
		timerRunning = true;}

		function exit(){
		
		}
</SCRIPT>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}

a {
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

.STYLE2 {
	font-size: 9px
}

.STYLE3 {
	color: #033d61;
	font-size: 12px;
}
-->
</style>

	</head>


	<body onload="startclock()">
		<form name=clock>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="70"
						background="<%=imagePath%>main_05.gif">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="24">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="270" height="24"
												background="<%=imagePath%>main_03.gif">
												&nbsp;
											</td>
											<td width="505"
												background="<%=imagePath%>main_04.gif">
												&nbsp;
											</td>
											<td>
												&nbsp;
											</td>
											<td width="21">
												<img src="<%=imagePath%>main_07.gif"
													width="21" height="24">
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="38">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="270" height="38"
												background="<%=imagePath%>main_09.gif">
												&nbsp;
											</td>
											<td>
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="77%" height="25" valign="bottom">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="50" height="19">
																		<div align="center">
																			<a href="<%=basePath%>frame/desktop.html" target="mainFrame">
																				<img src="<%=imagePath%>main_12.gif" width="49" height="19">
																			</a>
																		</div>
																	</td>
																	<td width="50">
																		<div align="center">
																			<a href="<%=basePath%>auth/logout" target="_parent">
																				<img src="<%=imagePath%>main_20.gif" width="48" height="19">
																			</a>
																		</div>
																	</td>
																	<td width="26">
																		<div align="center">
																			<img src="<%=imagePath%>main_21.gif" width="26" height="19">
																		</div>
																	</td>
																	<td width="100">
																		<div align="center">
																			<a href="<%=basePath%>users/${shareUser.userId}/userself.html" target="mainFrame">
																				<img src="<%=imagePath%>main_22.gif" width="98" height="19">
																			</a>
																		</div>
																	</td>
																	<td>
																		&nbsp;
																	</td>
																</tr>
															</table>
														</td>
														<td valign="bottom" nowrap="nowrap">
															<div align="right">
																<span class="STYLE1"><span class="STYLE2">■</span>
																	今天是：<input disabled="disabled" name=thetime
																		style="font-size: 9pt; background-color: #11c1a0; color: #FFFFFF; border: 0"
																		size=25> </span>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="21">
												<img src="<%=imagePath%>main_11.gif"
													width="21" height="38">
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="8" style="line-height: 8px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="270"
												background="<%=imagePath%>main_29.gif"
												style="line-height: 8px;">
												&nbsp;
											</td>
											<td width="505"
												background="<%=imagePath%>main_30.gif"
												style="line-height: 8px;">
												&nbsp;
											</td>
											<td style="line-height: 8px;">
												&nbsp;
											</td>
											<td width="21" style="line-height: 8px;">
												<img src="<%=imagePath%>main_31.gif"
													width="21" height="8">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="28"
						background="<%=imagePath%>main_36.gif">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="177" height="28"
									background="<%=imagePath%>main_32.gif">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="20%" height="22">
												&nbsp;
											</td>
											<td width="59%" valign="bottom">
												<div align="center" class="STYLE1">
													用户:${shareUser.userName}</div>
											</td>
											<td width="21%">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="65" height="28">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="23" valign="bottom">
															<table width="58" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td height="20" style="cursor: hand"
																		onMouseOver="this.style.backgroundImage='url(<%=imagePath%>bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#a6d0e7'; "
																		onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																		<div align="center" class="STYLE3">
																			<a href="<%=basePath%>frame/desktop.html"
																				target="mainFrame">工作台</a>
																		</div>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
											<td width="3">
												<img src="<%=imagePath%>main_34.gif"
													width="3" height="28">
											</td>
											<td width="63">
												<table width="58" border="0" align="center" cellpadding="0" cellspacing="0">
													<tr>
														<td height="20" style="cursor: hand"
															onMouseOver="this.style.backgroundImage='url(<%=imagePath%>bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#a6d0e7'; "
															onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
															<div align="center" class="STYLE3">
																<a href="<%=basePath%>desktop/reportforms.do" target="mainFrame">报表</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="3">
												<img src="<%=imagePath%>main_34.gif" width="3" height="28">
											</td>
											<td width="63">
												<table width="58" border="0" align="center" cellpadding="0" cellspacing="0">
													<tr>
														<td height="20" style="cursor: hand"
															onMouseOver="this.style.backgroundImage='url(<%=imagePath%>bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#a6d0e7'; "
															onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
															<div align="center" class="STYLE3">
																<a href="<%=basePath%>customer/info/list.html"
																	target="mainFrame">客户信息</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="3">
												<img src="<%=imagePath%>main_34.gif" width="3" height="28">
											</td>
											<td width="63">
												<table width="58" border="0" align="center" cellpadding="0" cellspacing="0">
													<tr>
														<td height="20" style="cursor: hand"
															onMouseOver="this.style.backgroundImage='url(<%=imagePath%>bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#a6d0e7'; "
															onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
															<div align="center" class="STYLE3">
																<a href="<%=basePath %>users/${shareUser.userId}/userview.html"
																	target="mainFrame">员工信息</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="3">
												<img src="<%=imagePath%>main_34.gif" width="3" height="28">
											</td>
											<td width="63">
												<table width="58" border="0" align="center" cellpadding="0" cellspacing="0">
													<tr>
														<td height="20" style="cursor: hand"
															onMouseOver="this.style.backgroundImage='url(<%=imagePath%>bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#a6d0e7'; "
															onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
															<div align="center" class="STYLE3">
																<a href="<%=basePath%>system/msgpub/list.html"
																	target="mainFrame">公告信息</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="3">
												<img src="<%=imagePath%>main_34.gif" width="3" height="28">
											</td>
											<td width="63">
												<table width="58" border="0" align="center" cellpadding="0" cellspacing="0">
													<tr>
														<td height="20" style="cursor: hand"
															onMouseOver="this.style.backgroundImage='url(<%=imagePath%>bg.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#a6d0e7'; "
															onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
															<div align="center" class="STYLE3">
																<a href="<%=basePath%>users/${shareUser.userId}/resetpwd.html"
																	target="mainFrame">密码重置</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
											
											
											<td>
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
								<td width="21">
									<img src="<%=imagePath%>main_37.gif" width="21" height="28">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
