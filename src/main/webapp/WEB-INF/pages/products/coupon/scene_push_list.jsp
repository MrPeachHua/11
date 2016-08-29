<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
    <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
    <%--<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>--%>
    <%--<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>--%>
    <%--<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>--%>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <%--<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>--%>
    <style type="text/css">
        body {
            margin: 0;
        }

        .STYLE1 {
            font-size: 12px
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

    </style>
</head>

<body>
<form action="<%=basePath%>products/scenePush/list.html" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" style="background-image: url('<%=imagePath%>tab_05.gif');">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" alt=""/></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="STYLE4"></td>
                                    <td class="STYLE4"></td>
                                    <td class="STYLE4"></td>
                                    <td width="10%" class="STYLE4" align="center">
                                    	<security:authorize access="hasAnyRole('AUTH_ADD_0051')">
                                        <input type="button" value="新增" onclick="location.href='<%=basePath %>/products/scenePush/add.html';" style="width:90px"/>
                                        </security:authorize>
                                    </td>
                                    <td width="10%" class="STYLE4" align="center">
                                        <input type="submit" value="查询" style="width:90px"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" alt=""/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="8" style="background-image: url('<%=imagePath%>tab_12.gif');"></td>
                        <td>
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
                                <tr>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">序号</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">蓝卡云车场ID</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">车场ID</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">车场名称</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">场景模式</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">赠送的用户类型</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">推送方式</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">开始时间</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">结束时间</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">推送内容</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">创建日期</span></div>
                                    </td>
                                    <security:authorize access="hasAnyRole('AUTH_DEL_0052','AUTH_EDIT_0053')">
                                    <td height="22" bgcolor="#FFFFFF" style="background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">操作</span></div>
                                    </td>
                                    </security:authorize>
                                </tr>
                                <c:choose>
                                    <c:when test="${fn:length(requestScope.page.resultList)>0}">
                                        <c:forEach items="${requestScope.page.resultList}" var="row" varStatus="status">
                                            <tr>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">${row.id}</span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">${row.blueParkingId}</span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">${row.parkingId}</span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">${row.parkingName}</span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">
                                                            <%--场景模式：1：入场 2：出场 3：注册--%>
                                                            <c:if test="${fn:contains(row.sceneMode, '1')}">入场</c:if>
                                                            <%--<c:if test="${fn:contains(row.sceneMode, '2')}">出场</c:if>--%>
                                                            <c:if test="${fn:contains(row.sceneMode, '3')}">注册</c:if>
                                                            <c:if test="${fn:contains(row.sceneMode, '4')}">车位状态</c:if>
                                                            <c:if test="${fn:contains(row.sceneMode, '5')}">下单推送</c:if>
                                                            <c:if test="${fn:contains(row.sceneMode, '6')}">月租产权缴费提醒</c:if>
                                                            <c:if test="${fn:contains(row.sceneMode, '7')}">月租产权过期后提醒</c:if>
                                                            <c:if test="${fn:contains(row.sceneMode, '8')}">优惠券到期前提醒</c:if>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <%--赠送的用户类型：1:月租 2:产权--%>
                                                        <span class="STYLE1">
                                                            <c:if test="${fn:contains(row.userType, '1')}">月租</c:if>
                                                            <c:if test="${fn:contains(row.userType, '2')}">产权</c:if>
                                                            <c:if test="${fn:contains(row.userType, '3')}">非月租产权用户</c:if>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <%--推送方式：0：不推送 1：短信 2：APP极光推送--%>
                                                        <span class="STYLE1">
                                                            <c:if test="${fn:contains(row.pushMode, '1')}">短信</c:if>
                                                            <c:if test="${fn:contains(row.pushMode, '2')}">app推送</c:if>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">
                                                            <fmt:formatDate value="${row.beginDate}" type="both" pattern="yyyy-MM-dd"/>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">
                                                            <fmt:formatDate value="${row.endDate}" type="both" pattern="yyyy-MM-dd"/>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">${row.pushContent}</span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">
                                                            <fmt:formatDate value="${row.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                        </span>
                                                    </div>
                                                </td>
                                                <security:authorize access="hasAnyRole('AUTH_DEL_0052','AUTH_EDIT_0053')">
                                                <td height="20" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE4">
                                                        	<security:authorize access="hasAnyRole('AUTH_EDIT_0053')">
                                                            <img src="<%=imagePath%>edt.gif" width="16" height="16" alt=""/>
                                                            <a href="<%=basePath %>products/scenePush/${row.id}/edit.html">编辑</a>
                                                            </security:authorize>
                                                            <security:authorize access="hasAnyRole('AUTH_DEL_0052')">
                                                            <img src="<%=imagePath%>del.gif" width="16" height="16" alt=""/>
                                                            <a href="<%=basePath %>products/scenePush/${row.id}/del.html">删除</a>
                                                            </security:authorize>
                                                        </span>
                                                    </div>
                                                </td>
                                                </security:authorize>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td height="20" bgcolor="#FFFFFF" colspan="21" align="center">
                                                <div align="center"><span class="STYLE1">没有信息</span></div>
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </td>
                        <td width="8" style="background-image: url('<%=imagePath%>tab_15.gif');"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="35" style="background-image: url('<%=imagePath%>tab_19.gif');">
                <jsp:include page="/frame/page.html"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>