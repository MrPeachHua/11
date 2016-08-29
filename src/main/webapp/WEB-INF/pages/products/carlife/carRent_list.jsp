<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

    <title></title>

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

    <%--<script>--%>
        <%--function add(){--%>
            <%--window.location="<%=basePath%>system/appversion/add.html";--%>
        <%--}--%>
        <%--var  highlightcolor='#c1ebff';--%>
        <%--//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(--%>
        <%--var  clickcolor='#51b2f6';--%>
        <%--function  changeto(){--%>
            <%--source=event.srcElement;--%>
            <%--if  (source.tagName=="TR"||source.tagName=="TABLE")--%>
                <%--return;--%>
            <%--while(source.tagName!="TD")--%>
                <%--source=source.parentElement;--%>
            <%--source=source.parentElement;--%>
            <%--cs  =  source.children;--%>
<%--//alert(cs.length);--%>
            <%--if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)--%>
                <%--for(i=0;i<cs.length;i++){--%>
                    <%--cs[i].style.backgroundColor=highlightcolor;--%>
                <%--}--%>
        <%--}--%>

        <%--function  changeback(){--%>
            <%--if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")--%>
                <%--return--%>
            <%--if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)--%>
<%--//source.style.backgroundColor=originalcolor--%>
                <%--for(i=0;i<cs.length;i++){--%>
                    <%--cs[i].style.backgroundColor="";--%>
                <%--}--%>
        <%--}--%>
        <%--&lt;%&ndash;function ptMessage(){&ndash;%&gt;--%>
            <%--&lt;%&ndash;location.href = "<%=basePath%>system/sms/parkingTicketMessage.html";&ndash;%&gt;--%>
        <%--&lt;%&ndash;}&ndash;%&gt;--%>
    <%--</script>--%>

</head>

<body>

<form  action="<%=basePath%>/products/carlife/carRent/list.html" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" background="<%=imagePath%>tab_05.gif">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="STYLE4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型：
                                        <select name="p_type">
                                            <option value="">全部</option>
                                            <option <c:if test="${param.p_type eq 1}">selected="selected"</c:if> value="1">常规租车</option>
                                            <option <c:if test="${param.p_type eq 2}">selected="selected"</c:if> value="2">按小时租车</option>
                                        </select>
                                        <%--<input type="text" name="p_platformCode" value="${param.p_platformCode}">--%>
                                    </td>
                                    <td class="STYLE4">名称：
                                        <input type="text" name="p_name" value="${param.p_name}">
                                    </td>
                                    <%--<td class="STYLE4">开始时间：<input type="text" name="p_startDate" valid="required" value="${param.p_startDate}"--%>
                                                                      <%--errmsg="查询日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:p_startDate,dateFmt:'yyyy-MM-dd'})"/></td>--%>
                                    <%--<td class="STYLE4">结束时间：<input type="text" name="p_endDate" valid="required" value="${param.p_endDate}"--%>
                                                                     <%--errmsg="查询日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:p_endDate,dateFmt:'yyyy-MM-dd'})"/></td>--%>
                                    <td class="STYLE4">
                                        <input  type="submit" value="查询" style="width:50px;"/>
                                    </td>
                                    <td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_ADD_0033')">
                                        <input type="button" value="添加" onclick="window.location='<%=basePath%>products/carlife/carRent/add.html'" style="width: 50px" /></security:authorize>
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
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">名称</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">价格</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">租聘类型</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">图片</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">跳转链接</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">创建日期</span></div></td>
                                    <security:authorize access="hasAnyRole('AUTH_DEL_0034','AUTH_EDIT_0035')">
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 10%"><div align="center">基本操作</div></td>
                                    </security:authorize>
                                </tr>
                                <c:choose>
                                    <c:when test="${fn:length(page.resultList)>0}">
                                        <c:forEach items="${page.resultList}" var="row" varStatus="status">
                                            <tr>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.id}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.name}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1"><c:if test="${row.type eq 2}">每小时</c:if>${row.price / 100.00}元起</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.type eq 1 ? '常规租车' : row.type eq 2 ? '按小时租车' : ''}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1"><img width="50" height="50" src="<%=basePath%>${row.imagePath}"></span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.jumpUrl}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">
                                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${row.createDate}" />
                                                </span></div></td>
                                                <security:authorize access="hasAnyRole('AUTH_DEL_0034','AUTH_EDIT_0035')">
                                                <td <%--<c:if test="${shareUser.rolePower > 2}"> style=" display: none  "  </c:if>--%> height="20" bgcolor="#FFFFFF" style="width: 4%"><div align="center"><span class="STYLE4">
                                                     <security:authorize access="hasAnyRole('AUTH_EDIT_0035')">
                                                     <img src="<%=imagePath%>edt.gif" width="16" height="16" />
                                                     <a href="<%=basePath %>products/carlife/carRent/${row.id}/edit.html">编辑</a>
                                                     </security:authorize>
                                                     <security:authorize access="hasAnyRole('AUTH_DEL_0034')">
                                                     <img src="<%=imagePath%>del.gif" width="16" height="16" />
                                                     <a href="<%=basePath %>products/carlife/carRent/${row.id}/del.html">删除</a>
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

        <tr>
            <td height="35" background="<%=imagePath%>tab_19.gif">
                <jsp:include page="/frame/page.html" />
            </td>
        </tr>

    </table>
</form>
</body>
</html>
