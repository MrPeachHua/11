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

		<title>角色界面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyUI/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyUI/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyUI/demo.css">
		<script type="text/javascript" src="<%=basePath%>/js/easyUI/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/easyUI/jquery.easyui.min.js"></script>

		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

	a{
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60; TEXT-DECORATION: underline
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
-->
#treeView {
	display: none;
	position: absolute;
	top: 0%;
	left: 40%;
	width:250px;
	/*height: 550px;*/
	/* width: 50%; */
	/* height: 100%; */
	/* border: 1px solid black; */
	background-color: #E3F0F8;
	z-index: 1002;
	overflow: auto;
}
</style>

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
	function add(){  
			window.location="<%=basePath%>users/roleadd.html";
		}

</script>
</head>

	<body>
	<form action="<%=basePath%>users/rolelist.html"  method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" background="<%=imagePath%>tab_05.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30">
								<img src="<%=imagePath%>tab_03.gif" width="12"
									height="30" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="STYLE4" align="center">
											&nbsp;&nbsp;请输入角色名称：
											<input type="text" name="p_roleName" style="width: 290px" value='${page.params["roleName"] }'/>
										</td>
										<td class="STYLE4">
											&nbsp;&nbsp;
											<input type="submit"  value="查询" style="width: 50px" />
										</td>
										<td class="STYLE4">
											&nbsp;&nbsp;
											<input type="button" value="添加" onclick="add()" style="width: 50px; 
												<c:if test='${shareUser.roleId == 3 }'> display:none; </c:if> " />
										</td>
									</tr>
								</table>
							</td>
							<td width="16">
								<img src="<%=imagePath%>tab_07.gif" width="16"
									height="30" />
							</td>
						</tr>
					</table>
				</td>
			</tr>

 			
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8"
								background="<%=imagePath%>tab_12.gif">
								&nbsp;
							</td>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="1"
									bgcolor="b5d6e6" onmouseover="changeto()"
									onmouseout="changeback()">
									<tr>
										<td width="5%"
											background="<%=imagePath%>bg2.gif"
											bgcolor="#FFFFFF" style="width: 5%; height: 22px;">
											<div align="center">
												<span class="STYLE1">权限ID</span>
											</div>
										</td>
										<td background="<%=imagePath%>bg2.gif"
											bgcolor="#FFFFFF" style="width: 55%; height: 22px;">
											<div align="center">
												<span class="STYLE1">权限名</span>
											</div>
										</td>
										<td background="<%=imagePath%>bg2.gif"
											bgcolor="#FFFFFF" style="width: 25%; height: 22px;">
											<div align="center">
												<span class="STYLE1">权限等级</span>
											</div>
										</td>
										<td width="15%"
											background="<%=imagePath%>bg2.gif"
											bgcolor="#FFFFFF" class="STYLE1"
											style="width: 15%; height: 22px;
											<c:if test='${shareUser.roleId == 3 }'> display:none; </c:if> ">
											<div align="center">
												基本操作
											</div>
										</td>
									</tr>
								  <c:choose>		
								  <c:when test="${fn:length(page.resultList)>0}">
								  <c:forEach items="${page.resultList}" var="row" varStatus="status">
									<tr>
										<td height="20" bgcolor="#FFFFFF" style="width:5%">
											<div align="center" class="STYLE1">
												<div align="center">
													<span class="STYLE1">${row.roleId}</span>
												</div>
											</div>
										</td>
										<td height="20" bgcolor="#FFFFFF" style="width: 55%">
											<div align="center">
												<span class="STYLE1"><a href="javascript:showView('${row.roleName}','${row.roleId}')">${row.roleName}</a></span>
											</div>
										</td>
										<td height="20" bgcolor="#FFFFFF" style="width: 25%">
											<div align="center">
												<span class="STYLE1">${row.rolePower}</span>
											</div>
										</td>
										<td height="20" bgcolor="#FFFFFF" style="width: 15%;
											<c:if test='${shareUser.roleId == 3 }'> display:none; </c:if> ">
											<div align="center">
												<span class="STYLE4"><img src="<%=imagePath%>del.gif"
														width="16" height="16" /><a href="<%=basePath %>users/${row.roleId}/roledel.html">删除</a>
												</span>
											</div>
										</td>
									</tr>	          
							      </c:forEach>
								  </c:when>
						          <c:otherwise>
									<tr>
										<td height="20" bgcolor="#FFFFFF" colspan="4"  align="center">
											<div align="center">
												<span class="STYLE1">没有查询到等级信息</span>
											</div>
										</td>
									</tr>
							      </c:otherwise>	
								  </c:choose>
								</table>
							</td>	
							<td width="8"
								background="<%=imagePath%>tab_15.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>

			
			<tr>
				<td height="35" background="<%=imagePath%>tab_19.gif">
		  			<jsp:include page="/frame/page.html" />
				</td>
			</tr>
		</table>
		</form>

	<!-- 弹出层的开始 -->
	<div class="treeView" id="treeView">
		<div class="easyui-panel" style="padding:5px; width:100%;">
			<ul id="tt" class="easyui-tree"
				<%--data-options="--%>
				<%--url: '<%=basePath%>/users/treeMenu.html',--%>
				<%--method: 'get',--%>
				<%--animate: true,--%>
				<%--checkbox:true,--%>
				<%--onlyLeafCheck:true"--%>
					></ul>
		</div>
		<div style="text-align: center;">
			<form id="roleFrom" action="<%=basePath%>/users/updateRole.html" method="post">
				<input id="menuCodeString" name="menuCodeString" type="hidden">
				<input id="roleName" name="roleName" type="hidden">
				<input id="roleId" name="roleId" type="hidden">
				<input type="button" onclick="getChecked()" value="确定" style="width: 110px;height:25px;" />
				<input type="button" onclick="hideView()" style="width: 110px;height:25px;" value="取消"/>
			</form>
		</div>
	</div>
	<!-- 弹出层的结束 -->
	<script type="text/javascript">
		function hideView() {
			document.getElementById('treeView').style.display = 'none';
		}

		function showView(roleName, roleId) {
			$("#roleName").val(roleName);
			$("#roleId").val(roleId);
			document.getElementById('treeView').style.display = 'block';
			$("#tt").tree({
				url: '<%=basePath%>/users/treeMenu.html',
				queryParams: {"roleName": roleName},
				method: 'post',
				animate: true,
				checkbox:true
//				onlyLeafCheck:true
			});
		}

		function getChecked(){
			var nodes = $('#tt').tree('getChecked');
			var s = '';
			for(var i=0; i<nodes.length; i++){
				if (s != '') s += ',';
				s += nodes[i].id;
			}
//			alert(s);
			$("#menuCodeString").val(s);
			$("#roleFrom").submit();
		}
	</script>

	</body>
</html>
