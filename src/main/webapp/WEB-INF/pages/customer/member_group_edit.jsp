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

    <title>修改会员分组信息</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <%--<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>--%>
    <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/css/style_v2.css">
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/pages/parkingView.js"></script>
</head>
<style type="text/css">

    body {
        padding: 15px 20px;
    }
</style>
<script type="text/javascript">
    var numTest = /^[0-9]*$/;
    function check1(){
        var growthValue = $("#growthValue").val();
        if(!numTest.test(growthValue)){
            alert("请输入整数类型！");
            $("#growthValue").val("");
        }
    }
</script>
<body>
<div class="bodyColor">
    <form action="<%=basePath%>customer/member_group/update.html" method="post" name="form2" >
        <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
            <tr class=editHeaderTr>
                <td class=editHeaderTd colSpan=7> 修改会员分组的详细信息</td>
                <input type="hidden" name="id" value="${memberGroup.id }" readonly="readonly">
                <input type="hidden" name="createor" value="${memberGroup.createor }" readonly="readonly">
                <input type="hidden" name="createDate" value="${memberGroup.createDate }" readonly="readonly">
            </tr>
            <tr>
                <td align="right" >
                    <div>
                        <span class="star">＊</span> <font size="4px"> 分组代码：</font>
                    </div>
                </td>
                <td colspan="3" >
                    <input type="text" name="groupCode"  maxlength="10" style="width: 191px" value="${memberGroup.groupCode }" readonly="true" >
                    &nbsp;
                </td>
                <td align="right">
                    <div >
                        <span class="star">＊</span><font size="4px">分组名称：</font>
                    </div>
                </td>
                <td  colspan="3" >
                    <input type="text" name="groupName" value="${memberGroup.groupName }"  id="groupName" readonly="true" style="width: 191px">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <div >
                        <span class="star">＊</span><font size="4px">每日获得成长值上限：</font>
                    </div>
                </td>
                <td colspan="3" >
                    <input type="text" name="growthValue" id="growthValue" required value="${memberGroup.growthValue }" style="width: 191px"  onblur="check1()">
                </td>

                <td  align="right">
                    <div >
                        <span class="star">＊</span><font size="4px">状态：</font>
                    </div>
                </td>
                <td colspan="3"  style="height: 21px">
                    <select name="isValid" value="${memberGroup.isValid }"  required  style="width: 191px">
                        <option value="0">
                            失效
                        </option>
                        <option value="1">
                            有效
                        </option>
                    </select>
                    &nbsp;
                </td>
            </tr>
        </table>
        <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
            <tr bgcolor="#ECF3FD">
                <td width="25%"></td>
                <td width="17%"><input type="submit" name="submit" value="修改"></td>
                <td width="17%"><input type="reset" name="reset" value="重置"></td>
                <td width="4%"><input type="button" name="button" onClick="history.back() " value="返回"></td>
                <td width="43%"></td>
            </tr>
        </table>

    </form>
</div>
</body>
</html>
