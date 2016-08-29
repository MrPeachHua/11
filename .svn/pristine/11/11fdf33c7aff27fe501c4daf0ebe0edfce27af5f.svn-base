<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>短信名单</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script language="JavaScript" type="text/javascript"
            src="<%=basePath%>js/FormValid.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
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
        $(document).ready(function(){
            if($("#select2").val()!=null){
                $("#orderType").val($("#select2").val());
            }
        });
        function add(){
            window.location="<%=basePath%>system/sendMessage/add.html";
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
    </script>

</head>

<body>

<form  action="<%=basePath%>system/sendMessage/list.html" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" background="<%=imagePath%>tab_05.gif">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="STYLE4" align="center">&nbsp;&nbsp;停车场：
                                        <input type="text" name="parkingName" value = "${page.params["parkingName"] }" style="width: 120px"/>
                                    </td>
                                    <td class="STYLE4">&nbsp;&nbsp;订单类型：
                                        <select name="orderType" id="orderType" style="width: 120px">
                                            <option value=""></option>
                                            <c:forEach items="${dictList}" var="dict">
                                                <option value="${dict.dictValue}">${dict.dictName}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" id="select2"
                                               value="${page.params['orderType'] }" />
                                    </td>
                                    <td class="STYLE4">&nbsp;&nbsp;用户名：
                                        <input type="text" name="personName" value = "${page.params["personName"] }" style="width: 120px"/>
                                    </td>
                                    <td class="STYLE4">&nbsp;&nbsp;手机号：
                                        <input type="text" name="personMobile" value = "${page.params["personMobile"] }" style="width: 120px"/>
                                    </td>
                                    <td class="STYLE4">&nbsp;&nbsp;<input  type="submit" value="查询" style="width:50px"/></td>
                                    <td class="STYLE4">&nbsp;&nbsp;<security:authorize access="hasAnyRole('AUTH_ADD_0115')">
                                        <input  type="button" onclick="add()" value="添加" style="width:50px"/></security:authorize></td>
                                    <td class="STYLE4">&nbsp;&nbsp;
                                        <input  type="reset" name="reset" value="重置" style="width:50px"/></td>

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
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:10%"><div align="center"><span class="STYLE1">车场名称</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 6%"><div align="center"><span class="STYLE1">订单类型</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:6%"><div align="center"><span class="STYLE1">人员</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:8%"><div align="center"><span class="STYLE1">手机号</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:6%"><div align="center"><span class="STYLE1">创建人</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:12%"><div align="center"><span class="STYLE1">创建时间</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:6%"><div align="center"><span class="STYLE1">修改人</span></div></td>
                                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:12%"><div align="center"><span class="STYLE1">修改时间</span></div></td>
                                    <security:authorize access="hasAnyRole('AUTH_DEL_0116','AUTH_EDIT_0117')">
                                        <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" <%--style="width: 4%"--%>><div align="center">基本操作</div></td>
                                    </security:authorize>
                                </tr>
                                <c:choose>
                                    <c:when test="${fn:length(page.resultList)>0}">
                                        <c:forEach items="${page.resultList}" var="row" varStatus="status">
                                            <tr>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.parkingName}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">
                                                <c:if test="${row.orderType eq 10}">共享临停</c:if>
            <c:if test="${row.orderType eq 11}">临停缴费</c:if>
            <c:if test="${row.orderType eq 12}">代泊</c:if>
            <c:if test="${row.orderType eq 13}">月租</c:if>
            <c:if test="${row.orderType eq 14}">产权</c:if>
            <c:if test="${row.orderType eq 15}">加油卡</c:if>
		    <c:if test="${row.orderType eq 16}">钱包充值</c:if>
			<c:if test="${row.orderType eq 17}">洗车</c:if>


                                                </span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.personName}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.personMobile}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  12%"><div align="center"><span class="STYLE1">

                                                ${row.createUser}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  12%"><div align="center"><span class="STYLE1">
                                                    <fmt:formatDate  value="${row.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                               </span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  12%"><div align="center"><span class="STYLE1">${row.updateUser}</span></div></td>
                                                <td height="20" bgcolor="#FFFFFF" style="width:  12%"><div align="center"><span class="STYLE1">
                                                <fmt:formatDate  value="${row.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                                               </span></div></td>
                                                <security:authorize access="hasAnyRole('AUTH_DEL_0116','AUTH_EDIT_0117')">
                                                    <td height="20" bgcolor="#FFFFFF" ><div align="center"><span class="STYLE4">
           		<security:authorize access="hasAnyRole('AUTH_EDIT_0117')">
                    <img src="<%=imagePath%>edt.gif" width="16" height="16" />
                    <a href="<%=basePath %>system/sendMessage/${row.id}/edit.html">编辑</a>
                </security:authorize>
            	<security:authorize access="hasAnyRole('AUTH_DEL_0116')">
                    <img src="<%=imagePath%>del.gif" width="16" height="16" />
                    <a href="<%=basePath %>system/sendMessage/${row.id}/del.html">删除</a>
                </security:authorize></span></div>
                                                    </td>
                                                </security:authorize>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
                                                <div align="center"><span class="STYLE1">没有短信名单相关信息</span></div>
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
