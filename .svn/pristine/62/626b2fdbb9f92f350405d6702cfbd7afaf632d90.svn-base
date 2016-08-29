<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <meta http-equiv="description" content="This is my page">
    <link href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <style type="text/css">
        body {
            margin: 0;
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

<form action="<%=basePath%>products/redeemRule/update.html" method="post">
    <input type="hidden" name="id" value="${entity.id}"/>
    <input type="hidden" name="createDate" value="<fmt:formatDate value="${entity.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
    <input type="hidden" name="createor" value="${entity.createor}"/>
    <input type="hidden" name="isUsed" value="${entity.isUsed}"/>
    <table id="tab" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" style="background-image:url('<%=imagePath%>tab_05.gif');">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" alt=""/></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="STYLE4"></td>
                                    <td class="STYLE4"></td>
                                    <td align="right" class="STYLE4">
                                        <input type="button" value="提交" onclick="form_submit(this)">
                                        <input type="button" value="返回" onclick="history.back()">
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
                        <td width="8" style="background-image:url('<%=imagePath%>tab_12.gif');"></td>
                        <td>
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
                                <tr>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">类型：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <select name="type" onchange="type_change(this)">
                                            <option <c:if test="${entity.type eq '1'}">selected="selected"</c:if> value="1">新注册用户使用兑换码获取的优惠券</option>
                                            <option <c:if test="${entity.type eq '2'}">selected="selected"</c:if> value="2">老用户兑换码被兑换一定次数后可领取的优惠券</option>
                                        </select>
                                    </td>
                                    <td bgcolor="#FFFDF0" rowspan="2">
                                        <div align="center">需要达到的兑换次数：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF" rowspan="2">
                                        <input type="text" name="redeemCount" value="${entity.redeemCount}"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="8" style="background-image:url('<%=imagePath%>tab_15.gif');"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="35" style="background-image:url('<%=imagePath%>tab_19.gif');">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="35"><img src="<%=imagePath%>tab_18.gif" width="12" height="35" alt=""/></td>
                        <td align="right"><input onclick="add_line()" type="button" value="新增"></td>
                        <td width="16"><img src="<%=imagePath%>tab_20.gif" width="16" height="35" alt=""/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <c:forEach var="item" items="${entity.couponTemplateList}">
            <%@ include file="../../template/coupon_template_edit.jsp" %>
        </c:forEach>
    </table>
</form>
<%@ include file="../../template/coupon_template_add.jsp" %>
<script type="text/javascript">
    function type_change(obj) {
        $("input[name=redeemCount]").attr("disabled", obj.value != 2);
    }
    type_change($("select[name=type]").get(0));
</script>
</body>
</html>