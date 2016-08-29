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

    <title>修改短信名单</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

</head>
<script type="text/javascript">

    function parkingPop(){
        var queryValue=$("#queryValue").val();
        var queryType = $("#queryType").val();
        document.getElementById('parkingForm').style.display='block';
        $.ajax({
            type: "POST",
            url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
            data:{"queryValue":queryValue,"queryType":queryType},
            dataType: "json",
            success: function (jsonStr) {
                $(".parkingListTr").remove();
                for(var i=0;i<jsonStr.length;i++){
                    $("table#myTable tr:last").after('<tr class = "parkingListTr" style="text-align:center"><td bgcolor="#FFFFFF">'+jsonStr[i].parkingId+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingName+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingArea+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingAddress+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCount+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCanUse+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingStatus+'</td><td bgcolor="#FFFFFF">'+
                    '<img src="<%=imagePath%>edt.gif" width="16" height="16" /><span style="cursor:pointer" onclick="choiceParkingCode(\''+jsonStr[i].parkingName+'\',\''+jsonStr[i].parkingId+'\')">选择</span></td></tr>');
                }
            }
        });
        $("#bg").css({
            display: "block"
        });
    }

    function choiceParkingCode(text1,text2){
        $("#parkingCode").val(text1);
        $("#parkingId").val(text2);
        $("#parkingForm").hide();
        $(".parkingListTr").remove();
        $("#bg").css("display", "none");
    }

    function cancelButton(){
        $("#parkingForm").hide();
        $(".parkingListTr").remove();
        $("#bg").css("display", "none");
    }

</script>
<style type="text/css">
    .content {
        display: none;
        position: absolute;
        top: 10%;
        left: 12%;
        width: 80%;
        height: 70%;
        /* border: 1px solid black; */
        background-color: white;
        z-index: 1002;
        overflow: auto;
    }
    #bg{
        background-color:#666;
        position:absolute;
        z-index:99;
        left:0;
        top:0;
        display:none;
        width:100%;
        height:100%;
        opacity:0.001;
        filter: alpha(opacity=50);
    }
</style>
<body>
<div id="bg"></div>
<div class="content" id="parkingForm">
    <form action="<%-- <%=basePath%>products/parking/list.html --%>"
          method="post">

        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td height="30" margin="0" padding="0"background="<%=imagePath%>tab_05.gif"><table
                        width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img
                                src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="STYLE4" align="center">&nbsp;&nbsp;请输入查询内容：<input
                                            type="text" name="queryValue" id="queryValue"style="width: 290px" /></td>
                                    <td class="STYLE4">&nbsp;&nbsp;请选择查询方式：<select
                                            name="queryType"  id="queryType" style="width: 100px">
                                        <option value="1">车场代码</option>
                                        <option value="2">车场名称</option>
                                        <option value="3">车场地址</option>
                                    </select>
                                    </td>
                                    <td class="STYLE4">&nbsp;&nbsp; <input type="button" onclick="parkingPop()"
                                                                           value="查询" style="width: 50px" />
                                    </td>
                                    <td class="STYLE4">&nbsp;&nbsp; <input type="button"
                                                                           value="取消" style="width: 50px" onclick="cancelButton()"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="16"><img src="<%=imagePath%>tab_07.gif"
                                            width="16" height="30" /></td>
                    </tr>
                </table></td>
            </tr>

            <tr>
                <td><table width="100%" border="0" cellspacing="0"
                           cellpadding="0">
                    <tr>
                        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
                        <td><table id="mytable" width="100%" border="0" cellpadding="0"
                                   cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"
                                   onmouseout="changeback()">
                            <tr>
                                <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
                                    style="width: 10%; height: 22px;">
                                    <div align="center">
                                        <span class="STYLE1">车场代码</span>
                                    </div>
                                </td>
                                <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
                                    style="width: 15%; height: 22px;">
                                    <div align="center">
                                        <span class="STYLE1">车场名称</span>
                                    </div>
                                </td>
                                <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
                                    style="width: 15%; height: 22px;">
                                    <div align="center">
                                        <span class="STYLE1">小区</span>
                                    </div>
                                </td>
                                <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
                                    style="width: 20%; height: 22px;">
                                    <div align="center">
                                        <span class="STYLE1">地址</span>
                                    </div>
                                </td>
                                <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
                                    style="width: 10%; height: 22px;">
                                    <div align="center">
                                        <span class="STYLE1">车位数</span>
                                    </div>
                                </td>
                                <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
                                    style="width: 10%; height: 22px;">
                                    <div align="center">
                                        <span class="STYLE1">空位数</span>
                                    </div>
                                </td>
                                <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
                                    style="width: 10%; height: 22px;">
                                    <div align="center">
                                        <span class="STYLE1">车位状态</span>
                                    </div>
                                </td>
                                <td width="10%" background="<%=imagePath%>bg2.gif"
                                    bgcolor="#FFFFFF" class="STYLE1"
                                    style="width: 10%; height: 22px;">
                                    <div align="center">基本操作</div>
                                </td>
                            </tr>
                        </table></td>
                        <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
                    </tr>
                </table></td>
            </tr>
            <tr><td height="35" background="<%=imagePath%>tab_19.gif"><%-- <jsp:include page="/frame/page.html" /> --%></td></tr>
        </table>
    </form>
</div>
<body>
<form action="<%=basePath%>system/sendMessage/update.html"  name="form1" onsubmit="return validator(this)" method="post" >
    <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
        <tr class=editHeaderTr>
            <td class=editHeaderTd colSpan=7>  请输入短信名单<span style="color: red;"><s:fielderror /></span></td>
            <td><input type="hidden" name="id" value="${sendMessage.id}"   /></td>
            <td><input type="hidden" name="createor" value="${sendMessage.createUser}"/></td>
            <td><input type="hidden" name="createDate" value="${sendMessage.createTime}"   /></td>
        </tr>
        <tr>
            <td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
            <td colspan="3" bgcolor="#FFFFFF">
                <input type="text"    style=" width: 165px"  readonly="readonly"
                       id="parkingCode" value="${sendMessage.parkingName}" onclick="parkingPop()">
                <input type="hidden" name = "parkingId" id="parkingId" value="${sendMessage.parkingId}"/>
            </td>
            <td bgcolor="#FFFDF0"><div align="center">订单类型：</div></td>
            <td colspan="3" bgcolor="#FFFFFF">
                <select style=" width: 165px" name="orderType" required>
                    <c:forEach var="dictionary" items="${dictionary}">
                        <c:choose>
                            <c:when test="${dictionary.dictValue eq sendMessage.orderType}">
                                <OPTION value=${dictionary.dictValue } selected="selected">${dictionary.dictName}</OPTION>
                            </c:when>
                            <c:otherwise>
                                <OPTION value=${dictionary.dictValue }>${dictionary.dictName}</OPTION>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td bgcolor="#FFFDF0"><div align="center">人员：</div></td>
            <td colspan="3" bgcolor="#FFFFFF">
                <input type="text" name ="personName" id="personName" value="${sendMessage.personName}" style="width: 165px" required/>
            </td>
            <td bgcolor="#FFFDF0"><div align="center">手机号：</div></td>
            <td colspan="3" bgcolor="#FFFFFF">
                <input type="text" name ="personMobile" id="personMobile" value="${sendMessage.personMobile}" style="width: 165px" required/>
            </td>
        </tr>

    </table>
    <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
        <tr bgcolor="#ECF3FD">
            <td width="25%"></td>
            <td width="17%"><input type="submit" name="submit"  value="提交"></td>
            <td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
            <td width="43%"></td>
        </tr>
    </table>
</form>
</body>
</html>

