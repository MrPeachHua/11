<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.crm.utils.Constants" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = Constants.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示客户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

	a{
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60; TEXT-DECORATION: underline
}

-->
</style>
<script type="text/javascript">
window.onload=function()
{
	var obtn=document.getElementById('check');
	var oinput=document.getElementsByTagName('input');
	var i=0;
	var countclick=0;
	obtn.onclick=function()
	{   
	   
	   countclick=countclick+1;
	   
	   if((countclick % 2)==1)
	   {
			for(i=0;i<oinput.length;i++)
			{
				oinput[i].checked=true;
			}
		}
		else
		{
			for(i=0;i<oinput.length;i++)
			{
				oinput[i].checked=false;
			}

		}
	}

}

</script>
<script>
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
		//获取表单对象			
		var form2 = document.getElementById("form2");
		form2.action = "<%=basePath%>products/attributegrp/attrisave.do";
		form2.submit();		
	}
	if('${info}' !=null && '${info}' !=""){
		alert('${info}');
	}
</script>
  </head>
  
  <body>
  <form action="<%=basePath%>products/attribute/select.do?q_id=<s:property value="id"/>"  id="form2" method="post">
<input type="hidden" name="productAttributeGroup.attriGrpCode" value="<s:property value="id"/>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="<%=imagePath%>tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="STYLE4" align="center">
						&nbsp;&nbsp;请输入产品属性名称：
						<input type="text" name="q_attriName" style="width: 290px" />
					</td>
					<td class="STYLE4">
						&nbsp;&nbsp;
						<input type="submit" value="查询" style="width: 50px" />
					</td>
					<td  class="STYLE4">
						&nbsp;&nbsp;
						<input type="button" value="添加" onclick="add()" style="width: 50px" />
						&nbsp;&nbsp;
						<input type="button" value="关闭" onclick="window.opener=null;window.close();" style="width: 50px" />
					</td>
				</tr>
			</table>
		</td>
        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" />
        </td>
      </tr>
    </table></td>
  </tr>


    <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
			<tr>
          		<td width="2%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center">
          			<input type="checkbox" id="check" name="checkbox1" value="checkbox1" /></div>
          		</td>
				<td width="5%"
					background="<%=imagePath%>bg2.gif"
					bgcolor="#FFFFFF" style="width: 5%; height: 22px;">
					<div align="center">
						<span class="STYLE1">序号</span>
					</div>
				</td>
				<td background="<%=imagePath%>bg2.gif"
					bgcolor="#FFFFFF" style="width: 15%; height: 22px;">
					<div align="center">
						<span class="STYLE1">产品属性代码</span>
					</div>
				</td>
				<td background="<%=imagePath%>bg2.gif"
					bgcolor="#FFFFFF" style="width: 25%; height: 22px;">
					<div align="center">
						<span class="STYLE1">产品属性名称</span>
					</div>
				</td>
				<td background="<%=imagePath%>bg2.gif"
					bgcolor="#FFFFFF" style="width: 53%; height: 22px;">
					<div align="center">
						<span class="STYLE1">描述</span>
					</div>
				</td>
			</tr>
		          <s:if test="#request.page.resultList.size>0">
		          <s:iterator value="#request.page.resultList" status="st">
 			<tr>
 				<td width="2%" height="22" background="images/bg.gif" bgcolor="#FFFFFF"><div align="center">
 					<input type="checkbox" name="attriCodes" value="<s:property value="attriCode"/>" /></div>
 				</td>
				<td height="20" bgcolor="#FFFFFF" style="width: 5%">
					<div align="center" class="STYLE1"><div align="center"><s:property value="#st.count"/></div></div>
				</td>
            	<td height="20" bgcolor="#FFFFFF" style="width: 15%"><div align="left">&nbsp;<span class="STYLE1"><s:property value="attriCode"/></span></div></td>
            	<td height="20" bgcolor="#FFFFFF" style="width: 25%"><div align="center"><span class="STYLE1"><s:property value="attriName"/></span></div></td>
            	<td height="20" bgcolor="#FFFFFF" style="width: 53%"><div align="center"><span class="STYLE1"><s:property value="description"/></span></div></td>
          	</tr>	          
		          </s:iterator>
		          </s:if>
		          <s:else>
     		<tr>
				<td height="20" bgcolor="#FFFFFF" colspan="22"  align="center">
					<div align="center">
						<span class="STYLE1">没有产品属性信息</span>
					</div>
				</td>
			</tr>
		          </s:else>
         </table></td>
        <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
   <tr><td height="35" background="<%=imagePath%>tab_19.gif"><jsp:include page="/frame/page.jsp" /></td></tr>
</table>
</form>
</body>
</html>
