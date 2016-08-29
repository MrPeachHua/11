<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>成长流水值信息</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <LINK href="<%=basePath%>css/base.css" type=text/css rel=stylesheet>
    <LINK href="<%=basePath%>css/fileList.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
    <script>
        var highlightcolor = '#c1ebff';
        var clickcolor = '#51b2f6';
        function changeto() {
            source = event.srcElement;
            if (source.tagName == "TR" || source.tagName == "TABLE")
                return;
            while (source.tagName != "TD")
                source = source.parentElement;
            source = source.parentElement;
            cs = source.children;
            if (cs[1].style.backgroundColor != highlightcolor && source.id != "nc" && cs[1].style.backgroundColor != clickcolor)
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = highlightcolor;
                }
        }

        function changeback() {
            if (event.fromElement.contains(event.toElement) || source.contains(event.toElement) || source.id == "nc")
                return
            if (event.toElement != source && cs[1].style.backgroundColor != clickcolor)
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = "";
                }
        }

        function clickto() {
            source = event.srcElement;
            if (source.tagName == "TR" || source.tagName == "TABLE")
                return;
            while (source.tagName != "TD")
                source = source.parentElement;
            source = source.parentElement;
            cs = source.children;
            if (cs[1].style.backgroundColor != clickcolor && source.id != "nc")
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = clickcolor;
                }
            else
                for (i = 0; i < cs.length; i++) {
                    cs[i].style.backgroundColor = "";
                }
        }
        $(document).ready(function () {

        });
    </script>
    <script type="text/javascript">
        function excelExport() {
            //var inorout = $("#inorout").val();
            window.location = "<%=basePath%>customer/excelGrowthValueReport.html";}
    </script>
</head>

<body>

<form action="<%=basePath%>customer/customer_growth/list.html" method="post">
    <div style="height: 100px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" height="60">
                                    <tr>
                                        <td class="STYLE4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户当前级别：
                                            <select name="p_queryLevel" style="width: 120px">
                                                <option value=""></option>
                                                <option value="1001">银卡</option>
                                                <option value="1002">金卡</option>
                                                <option value="1003">白金</option>
                                                <option value="1004">钻石</option>
                                                <option value="1005">黑卡</option>
                                            </select>
                                        </td>
                                        <td class="STYLE4">&nbsp;&nbsp;分组：
                                            <select name="p_queryGroup" style="width: 120px">
                                                <option value=""></option>
                                                <option value="1">系统</option>
                                                <option value="2">用户</option>
                                                <option value="3">车辆</option>
                                            </select>
                                        </td>
                                        <td class="STYLE4">&nbsp;&nbsp;手机号：
                                            <input type="text" name="p_queryMobile"/>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td class="STYLE4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期： <input type="text"
                                                                                                           placeholder="开始时间"
                                                                                                           id="form_beginTime_in"
                                                                                                           name="p_beginTime"
                                                                                                           class="btn_tb"
                                                                                                           readonly="readonly"
                                                                                                           onclick="WdatePicker({el:form_beginTime_in,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                            <font style="color: #39d5b8">—</font> &nbsp;&nbsp;&nbsp;
                                            <input type="text" placeholder="截止时间" id="form_endTime_in" name="p_endTime"
                                                   class="btn_tb" readonly="readonly"
                                                   onclick="WdatePicker({el:form_endTime_in,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                        </td>
                                        <td align="right" class="STYLE4">
                                         <security:authorize access="hasAnyRole('AUTH_VIEW_0126')"><input type="submit" value="查询" class="btn"/>
                                         </security:authorize>
                                        </td>
                                        <td align="left"><security:authorize access="hasAnyRole('AUTH_EXP_0127')"><input
                                                type="button" onclick="excelExport()" value="导出"
                                                class="btn"/></security:authorize></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table class="table_content" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" onmouseover="changeto()"
                                       onmouseout="changeback()">
                                    <tr height="50" style="background-color:#ecf6ff">
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">用户ID </span></div>
                                        </td>
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">手机号码</span></div>
                                        </td>
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">获取时间</span></div>
                                        </td>
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">动作 </span></div>
                                        </td>
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">分组 </span></div>
                                        </td>
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">明细 </span></div>
                                        </td>
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">成长值 </span></div>
                                        </td>
                                        <td style="width: 5%">
                                            <div align="center"><span class="STYLE1">备注 </span></div>
                                        </td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${fn:length(page.resultList)>0}">
                                            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                                                <tr class="underline" height="50" style="background-color: #FFF;">
                                                    <td style="width:  5%">
                                                        <div align="center"><span
                                                                class="STYLE1">${row.customerId}</span></div>
                                                    </td>
                                                    <td style="width:  5%">
                                                        <div align="center"><span
                                                                class="STYLE1">${row.customerMobile}</span></div>
                                                    </td>
                                                    <td style="width:  5%">
                                                        <div align="center"><span class="STYLE1"><fmt:formatDate
                                                                value="${row.growthTime}" type="both"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/> </span></div>
                                                    </td>
                                                    <td style="width:  8%">
                                                        <div align="center"><span class="STYLE1">${row.actionId}</span>
                                                        </div>
                                                    </td>
                                                    <td style="width:  8%">
                                                        <div align="center"><span
                                                                class="STYLE1"> ${row.groupName}</span></div>
                                                    </td>
                                                    <td style="width:  5%">
                                                        <div align="center"><span
                                                                class="STYLE1">${row.detailInfo}</span></div>
                                                    </td>
                                                    <td style="width:  5%">
                                                        <div align="center"><span
                                                                class="STYLE1">${row.growthValue}</span></div>
                                                    </td>
                                                    <td style="width:  5%">
                                                        <div align="center"><span class="STYLE1">${row.memo}</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="21" align="center">
                                                    <div align="center"><span class="STYLE1">没有成长流水值信息</span></div>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <jsp:include page="/frame/page.html"/>
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
