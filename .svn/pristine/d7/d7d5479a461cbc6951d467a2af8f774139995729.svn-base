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
<form action="<%=basePath%>products/redeemRule/list.html" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" style="background-image: url('<%=imagePath%>tab_05.gif');">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" alt=""/></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                	<td class="STYLE4" align="center">&nbsp;&nbsp;规则ID： <input
										type="text" name="id" id="id"
										value='${page.params["id"] }' style="width: 130px" />
									</td>
									<td class="STYLE4" align="center">&nbsp;&nbsp;类型： 
										<select id="type" name="type" style="width: 130px">
											<option value="" <c:if test="${empty page.params['type'] }">selected</c:if> >全部</option>
											<option value="1" <c:if test="${page.params['type'] == '1' }">selected</c:if> >新注册用户使用兑换码获取的优惠券</option>
											<option value="2" <c:if test="${page.params['type'] == '2' }">selected</c:if> >老用户兑换码被兑换一定次数后可领取的优惠券</option>
										</select>
									</td>
                                    <td class="STYLE4"></td>
                                    <td width="10%" class="STYLE4" align="center">
                                        <input type="submit" value="查询" style="width:90px"/>
                                    </td>
                                    <td width="10%" class="STYLE4" align="center"><security:authorize access="hasAnyRole('AUTH_ADD_0041')">
                                        <input type="button" value="新增" onclick="location.href='<%=basePath %>/products/redeemRule/add.html';" style="width:90px"/>
                                    	</security:authorize>
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
                                    <td height="22" bgcolor="#FFFFFF" style="width: 8%; background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">规则ID</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="width: 20%; background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">类型</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="width: 20%; background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">老用户领取优惠券需要达到的兑换次数</span></div>
                                    </td>
                                    <td height="22" bgcolor="#FFFFFF" style="width: 20%; background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">创建日期</span></div>
                                    </td>
                                    <security:authorize access="hasAnyRole('AUTH_DEL_0042','AUTH_EDIT_0043')">
                                    <td height="22" bgcolor="#FFFFFF" style="width: 20%; background-image: url('<%=imagePath%>bg2.gif');">
                                        <div align="center"><span class="STYLE1">操作</span></div>
                                    </td>
                                    </security:authorize>
                                </tr>
                                <c:choose>
                                    <c:when test="${fn:length(requestScope.page.resultList)>0}">
                                        <c:forEach items="${requestScope.page.resultList}" var="row" varStatus="status">
                                            <tr>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  8%">
                                                    <div align="center">
                                                        <span class="STYLE1">${row.id}</span>
                                                    </div>
                                                </td>
                                                <td height="" bgcolor="#FFFFFF">
                                                    <div align="center">
                                                        <span class="STYLE1">
                                                            <c:if test="${row.type eq '1'}">新注册用户使用兑换码获取的优惠券</c:if>
                                                            <c:if test="${row.type eq '2'}">老用户兑换码被兑换一定次数后可领取的优惠券</c:if>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  20%">
                                                    <div align="center">
                                                        <span class="STYLE1">
                                                            <c:if test="${row.type eq '2'}">${row.redeemCount}</c:if>
                                                        </span>
                                                    </div>
                                                </td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  20%">
                                                    <div align="center">
                                                        <span class="STYLE1">
                                                            <fmt:formatDate value="${row.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                        </span>
                                                    </div>
                                                </td>
                                                <security:authorize access="hasAnyRole('AUTH_DEL_0042','AUTH_EDIT_0043')">
                                                <td height="20" bgcolor="#FFFFFF" style="width: 8%">
                                                    <div align="center">
                                                        <span class="STYLE4">
                                                        	<security:authorize access="hasAnyRole('AUTH_EDIT_0043')">
                                                            <img src="<%=imagePath%>edt.gif" width="16" height="16" alt=""/>
                                                            <a href="<%=basePath %>products/redeemRule/${row.id}/edit.html">编辑</a>
                                                            </security:authorize>
                                                            <security:authorize access="hasAnyRole('AUTH_DEL_0042')">
                                                            <img src="<%=imagePath%>del.gif" width="16" height="16" alt=""/>
                                                            <a href="<%=basePath %>products/redeemRule/${row.id}/del.html">删除</a>
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
