<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

    <title>版本控制</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <LINK href="<%=basePath%>js/My97DatePicker/skin/WdatePicker.css" type=text/css rel=stylesheet>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
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

    <script>
        function add(){
            window.location="<%=basePath%>system/appversion/add.html";
        }
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
        function ptMessage(){
            location.href = "<%=basePath%>system/sms/parkingTicketMessage.html";
        }
    </script>

</head>

<body>

<form  action="<%=basePath%>system/appversion/list.html" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" background="<%=imagePath%>tab_05.gif">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="STYLE4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;平台：
                                        <select name="p_platformCode">
                                            <option value="">全部</option>
                                            <c:forEach items="${requestScope.platformCodeList}" var="item">
                                                <option <c:if test="${param.p_platformCode eq item.dictValue}">selected="selected"</c:if> value="${item.dictValue}">${item.dictName}</option>
                                            </c:forEach>
                                        </select>
                                        <%--<input type="text" name="p_platformCode" value="${param.p_platformCode}">--%>
                                    </td>
                                    <td class="STYLE4">渠道：
                                        <%--<select name="p_versionChannel">--%>
                                            <%--<option value="">全部</option>--%>
                                            <%--<c:forEach items="${requestScope.versionChannelList}" var="item">--%>
                                                <%--<option <c:if test="${param.p_versionChannel eq item.dictValue}">selected="selected"</c:if> value="${item.dictValue}">${item.dictName}</option>--%>
                                            <%--</c:forEach>--%>
                                        <%--</select>--%>
                                        <input type="text" name="p_versionChannel" value="${param.p_versionChannel}">
                                    </td>
                                    <td class="STYLE4">开始时间：<input type="text" name="p_startDate" valid="required" value="${param.p_startDate}"
                                                                      errmsg="查询日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:p_startDate,dateFmt:'yyyy-MM-dd'})"/></td>
                                    <td class="STYLE4">结束时间：<input type="text" name="p_endDate" valid="required" value="${param.p_endDate}"
                                                                     errmsg="查询日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:p_endDate,dateFmt:'yyyy-MM-dd'})"/></td>
                                    <td class="STYLE4">
                                        <input  type="submit" value="查询" style="width:50px;"/>
                                    </td>
                                    <td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_ADD_0097')">
                                        <input type="button" value="添加" onclick="add()" style="width: 50px" /></security:authorize>
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
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
                                <tr>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">序号</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">平台</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">版本号</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">渠道</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">更新提示内容</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">是否强制更新</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">更新地址</span></div></td>
                                    <%--<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">是否可用</span></div></td>--%>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">创建人</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">创建日期</span></div></td>
                                    <security:authorize access="hasAnyRole('AUTH_DEL_0098','AUTH_EDIT_0099')">
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 10%"><div align="center">基本操作</div></td>
                                    </security:authorize>
                                </tr>
                                <c:choose>
                                    <c:when test="${fn:length(page.resultList)>0}">
                                        <c:forEach items="${page.resultList}" var="row" varStatus="status">
                                            <tr>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.id}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.platformCode}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.versionCode}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.versionChannel}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.notice}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">
                                                    <c:if test="${row.isNeeded eq 1}">是</c:if> <c:if test="${row.isNeeded eq 0}">否</c:if>
                                                </span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.updateUrl}</span></div></td>
                                                <%--<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">--%>
                                                    <%--<c:if test="${row.isUsed eq 1}">是</c:if> <c:if test="${row.isUsed eq 0}">否</c:if>--%>
                                                <%--</span></div></td>--%>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.createor}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">
                                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${row.createDate}" />
                                                </span></div></td>
												<security:authorize access="hasAnyRole('AUTH_DEL_0098','AUTH_EDIT_0099')">
                                                <td height="20" bgcolor="#FFFFFF" style="width: 4%"><div align="center"><span class="STYLE4">
                                                	<security:authorize access="hasAnyRole('AUTH_EDIT_0099')">
                                                     <img src="<%=imagePath%>edt.gif" width="16" height="16" />
                                                     <a href="<%=basePath %>system/appversion/${row.id}/edit.html">编辑</a>
                                                    </security:authorize>
                                                    <security:authorize access="hasAnyRole('AUTH_DEL_0098')">
                                                     <img src="<%=imagePath%>del.gif" width="16" height="16" />
                                                     <a href="<%=basePath %>system/appversion/${row.id}/del.html">删除</a>
                                                    </security:authorize></span></div>
                                                </td>
                                                </security:authorize>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
                                                <div align="center"><span class="STYLE1">没有版本控制相关信息</span></div>
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

        <tr>
            <td height="35" background="<%=imagePath%>tab_19.gif">
                <jsp:include page="/frame/page.html" />
            </td>
        </tr>

    </table>
</form>
</body>
</html>
