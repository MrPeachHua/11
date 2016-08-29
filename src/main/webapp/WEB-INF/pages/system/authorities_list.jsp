<%@page import="com.boxiang.share.system.po.Dictionary"%>
<%@page import="com.boxiang.share.product.coupon.po.Coupon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
  <title>系统权限</title>
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
  <style type="text/css">
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

  </style>
  <script type="text/javascript">
	$(document).ready(function(){
		$("#addAuthorities").click(function(){
			window.location="<%=basePath%>system/authority/add.html";
		});
	});

    /*function pp(data){
      //var channel = $("#channel").val();
      //alert(data);onclick="pp('${row.channel}')"
      var loginBegin = $("#loginBegin").val();
      var loginEnd = $("#loginEnd").val();
      var registerBegin = $("#registerBegin").val();
      var registerEnd = $("#registerEnd").val();
      $.ajax({
        cache: false,
        type: "POST",
        url:'reports/channel/view.html',
        data:{"channel":data,"loginBegin":loginBegin,"loginEnd":loginEnd,"registerBegin":registerBegin,"registerEnd":registerEnd},
        dataType: "json",
        success: function (jsonStr) {

        }
      });

    }*/
    /* 导出excel表 */
   /* function excelExport1(){
      var channel = $("#select1").val();
      var loginBegin = $("#loginBegin").val();
      var loginEnd = $("#loginEnd").val();
      var registerBegin = $("#registerBegin").val();
      var registerEnd = $("#registerEnd").val();
      window.location="<%=basePath%>reports/channel/excel_export.html?channel="+channel+"&loginBegin="+loginBegin+"&loginEnd="+loginEnd+"&registerBegin="+registerBegin+"&registerEnd="+registerEnd;
    }*/
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

  </script>
</head>
<body>
  <form  action="<%=basePath%>system/authority/list.html" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	  <td height="30" background="<%=imagePath%>tab_05.gif">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
	        <td>
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			      <td class="STYLE4" align="center">&nbsp;&nbsp;权限名称：
                      <input id="authorityName"
                             name="authorityName"
                             style=" width: 145px"  value='${page.params["authorityName"] }'>
                    </td>
                    <td class="STYLE4" align="center">&nbsp;&nbsp;权限描述：
                      <input id="authorityDesc"
                             name="authorityDesc"
                             style=" width: 145px"  value='${page.params["authorityDesc"] }'>
                    </td>
                    <%--<td class="STYLE4">&nbsp;&nbsp;是否启用：
                     &lt;%&ndash; <input id="enabled"
                             name="enabled"
                             style=" width: 145px"   value='${page.params["enabled"] }'>&ndash;%&gt;
                      <select id="enabled" name="enabled"  style=" width: 145px">
                        <option value="1">是</option>
                        <option value="0">否</option>
                      </select>
                    </td>--%>
                    <td></td>
                    <td class="STYLE4" align="center"><input  type="submit" value="查询" style="width:90px"/> &nbsp;&nbsp;&nbsp;
                     </td>
                    	<td class="STYLE4" align="center"><security:authorize access="hasAnyRole('AUTH_ADD_0080')">
                    		<input  type="button" value="新增" id ="addAuthorities" style="width:90px"/></security:authorize>
                    	</td>
			      </tr>
			    </table>
			</td>
	        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" /></td>
	      </tr>
    	</table>
    	</td>
      </tr>
      <div/>
      <tr>
        <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
              <td>
                <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
                  <tr>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">权限ID</span></div></td>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">权限名称</span></div></td>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">权限描述 </span></div></td>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">所属系统 </span></div></td>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">是否启用 </span></div></td>
                    <security:authorize access="hasAnyRole('AUTH_DEL_0081','AUTH_EDIT_0082')">
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">操作</span></div></td>
                    </security:authorize>
                  </tr>
                  <c:choose>
                    <c:when test="${fn:length(page.resultList)>0}">
                      <c:forEach items="${page.resultList}" var="row" varStatus="status">
                        <tr>
                          <td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center"><span class="STYLE1" >${row.authorityId}</span></div></td>
                          <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.authorityName}</span></div></td>
                          <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.authorityDesc}</span></div></td>
                          <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.module}</span></div></td>
                          <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1"><c:if test="${row.enabled eq 1}">是</c:if><c:if test="${row.enabled eq 0}">否</c:if></span></div></td>
                          <security:authorize access="hasAnyRole('AUTH_DEL_0081','AUTH_EDIT_0082')">
                          <td height="20" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE4">
                          <security:authorize access="hasAnyRole('AUTH_EDIT_0082')">
            	          <img src="<%=imagePath%>edt.gif" width="16" height="16" />
            	          <a href="<%=basePath %>system/authority/${row.authorityId}/edit.html">编辑</a>
            	          </security:authorize>
            	          <security:authorize access="hasAnyRole('AUTH_DEL_0081')">
            	          <img src="<%=imagePath%>del.gif" width="16" height="16" />
            	         <a href="<%=basePath %>system/authority/${row.authorityId}/del.html">删除</a>
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
              <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td height="35" background="<%=imagePath%>tab_19.gif">
          <jsp:include page="/frame/page.html" />
        </td>
      </tr>

    </table>
  </form>
</body>
</html>
