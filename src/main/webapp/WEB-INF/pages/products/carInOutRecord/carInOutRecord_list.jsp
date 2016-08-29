<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>查询车辆进出信息</title>

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
        var  highlightcolor='#c1ebff';
        var  clickcolor='#51b2f6';
        function  changeto(){
            source=event.srcElement;
            if  (source.tagName=="TR"||source.tagName=="TABLE")
                return;
            while(source.tagName!="TD")
                source=source.parentElement;
            source=source.parentElement;
            cs  =  source.children;
            if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
                for(i=0;i<cs.length;i++){
                    cs[i].style.backgroundColor=highlightcolor;
                }
        }

        function  changeback(){
            if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
                return
            if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
                for(i=0;i<cs.length;i++){
                    cs[i].style.backgroundColor="";
                }
        }

        function  clickto(){
            source=event.srcElement;
            if  (source.tagName=="TR"||source.tagName=="TABLE")
                return;
            while(source.tagName!="TD")
                source=source.parentElement;
            source=source.parentElement;
            cs  =  source.children;
            if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
                for(i=0;i<cs.length;i++){
                    cs[i].style.backgroundColor=clickcolor;
                }
            else
                for(i=0;i<cs.length;i++){
                    cs[i].style.backgroundColor="";
                }
        }
        $(document).ready(function(){

        });
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            if($("#select3").val()!=null){
                $("#inorout").val($("#select3").val());
            }
            var selectValue = $("#inorout").val();
            if(selectValue == '1'){
                $("#form_beginTime_out").attr("disabled",true);
                $("#form_endTime_out").attr("disabled",true);
            }else /*if(selectValue == '2')*/{
                $("#form_beginTime_out").attr("disabled",null);
                $("#form_endTime_out").attr("disabled",null);
            }
        });
        function getValue(){
            var selectValue = $("#inorout").val();
            if(selectValue == '1'){
                $("#form_beginTime_out").attr("disabled",true);
                $("#form_endTime_out").attr("disabled",true);
            }else /*if(selectValue == '2')*/{
                $("#form_beginTime_out").attr("disabled",null);
                $("#form_endTime_out").attr("disabled",null);
            }
        }
        function excelExport(){
            var inorout = $("#inorout").val();
            var parkingName =$("#parkingName").val();
            var startInTime = $("#form_beginTime_in").val();
            var endInTime = $("#form_endTime_in").val();
            var startOutTime = $("#form_beginTime_out").val();
            var endOutTime = $("#form_endTime_out").val();
            var plateId = $("#plateId").val();
            window.location =
                    "<%=basePath%>products/carInOutRecord/excelExportCarRecord.html?inorout="+inorout+"&parkingName="+parkingName+"&startInTime="+startInTime+"&endInTime="+endInTime+"&startOutTime="+startOutTime+"&endOutTime="+endOutTime+"&plateId="+plateId;
        }
    </script>
</head>

<body>

<form  action="<%=basePath%>products/carInOutRecord/list.html" method="post">
    <div style="height: 100px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" height="60">
                                    <tr>
                                        <td class="STYLE4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出入状态：
                                            <select id="inorout" name="inorout" onchange="getValue();" style="width: 120px">
                                                <option value=""></option>
                                                <option value="1">入场</option>
                                                <option value="2">出场</option>
                                            </select>
                                            <input type="hidden" id="select3" value='${page.params["inorout"] }'/>
                                        </td>
                                        <td class="STYLE4">&nbsp;&nbsp;车场名称：
                                            <input type="text"  name="parkingName" id="parkingName" value='${page.params["parkingName"] }' class="btn_tb"/>
                                        </td>
                                        <td class="STYLE4">&nbsp;&nbsp;车牌号：
                                            <input type="text"  name="plateId" id="plateId" value='${page.params["plateId"] }' class="btn_tb"/>
                                        </td>
                                        <td class="STYLE4"><input  type="submit" value="查询" class="btn"/></td>
                                        <%--<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_EXP_0059')"><input  type="button" onclick="excelExport()" value="导出"  class="btn"/></security:authorize></td>--%>
                                    </tr>
                                    <tr>
                                        <td class="STYLE4"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入场时间： <input type="text" placeholder="开始时间" id="form_beginTime_in" name="form_beginTime_in" value='${page.params["startInTime"] }'    class="btn_tb" readonly="readonly"  onclick="WdatePicker({el:form_beginTime_in,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                            <font style="color: #39d5b8">—</font> &nbsp;&nbsp;&nbsp;
                                            <input type="text" placeholder="截止时间" id="form_endTime_in" name="form_endTime_in"  class="btn_tb" readonly="readonly" value='${page.params["endInTime"] }'  onclick="WdatePicker({el:form_endTime_in,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                        </td>
                                        <td class="STYLE4" > &nbsp;&nbsp;出场时间： <input type="text" placeholder="开始时间" id="form_beginTime_out" name="form_beginTime_out" value='${page.params["startOutTime"] }'    class="btn_tb" readonly="readonly"  onclick="WdatePicker({el:form_beginTime_out,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                            <font style="color: #39d5b8">—</font> &nbsp;&nbsp;&nbsp;
                                            <input type="text" placeholder="截止时间"  id="form_endTime_out" name="form_endTime_out"  class="btn_tb" readonly="readonly" value='${page.params["endOutTime"] }'  onclick="WdatePicker({el:form_endTime_out,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                                        </td>
                                        <td></td>
                                        <td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_EXP_0059')"><input  type="button" onclick="excelExport()" value="导出"  class="btn"/></security:authorize></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table  class="table_content" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" onmouseover="changeto()"  onmouseout="changeback()">
                                    <tr  height="50" style="background-color:#ecf6ff">
                                        <%--<td style="width: 5%"><div align="center"><span class="STYLE1">序号</span></div></td>--%>
                                        <td style="width: 5%"><div align="center"><span class="STYLE1">车场名称 </span></div></td>
                                        <td style="width: 5%"><div align="center"><span class="STYLE1">车牌号</span></div></td>
                                        <td style="width: 5%"><div align="center"><span class="STYLE1">进场/出场</span></div></td>
                                        <td style="width: 5%"><div align="center"><span class="STYLE1">入场时间 </span></div></td>
                                        <td style="width: 5%"><div align="center"><span class="STYLE1">出场时间 </span></div></td>
                                            <td style="width: 5%"><div align="center"><span class="STYLE1">应收金额 </span></div></td>
                                            <td style="width: 5%"><div align="center"><span class="STYLE1">实收金额 </span></div></td>
                                            <td style="width: 5%"><div align="center"><span class="STYLE1">停车时间 </span></div></td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${fn:length(page.resultList)>0}">
                                            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                                                <tr  class="underline" height="50" style="background-color: #FFF;">
                                                   <%-- <td style="width:  3%"><div align="center"><span class="STYLE1">
                                                    <a href="<%=basePath%>products/carInOutRecord/view.html?id=${row.id}">${status.count}</a>
                                                    </span></div>
                                                        </td>--%>
                                                    <td style="width:  5%"><div align="center" ><span  class="STYLE1"><a href="<%=basePath%>products/carInOutRecord/view.html?id=${row.id}">${row.parkingName}</a></span></div></td>
                                                        <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.plateId}</span></div></td>
                                                    <td style="width:  5%"><div align="center" ><span  class="STYLE1">
                                                        <c:if test="${row.inorout eq 1}">
                                                            入场
                                                        </c:if>
                                                        <c:if test="${row.inorout eq 2}">
                                                            出场
                                                        </c:if>
                                                    </span></div></td>
                                                    <td style="width:  8%"><div align="center" ><span class="STYLE1"><fmt:formatDate  value="${row.intime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> </span></div></td>
                                                    <td style="width:  8%"><div align="center" ><span class="STYLE1"><fmt:formatDate  value="${row.outtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> </span></div></td>
                                                       <td style="width:  5%"><div align="center" ><span  class="STYLE1">
${row.payCharge}
                                                    </span></div></td>
                                                       <td style="width:  5%"><div align="center" ><span  class="STYLE1">
                                                               ${row.realCharge}
                                                    </span></div></td>
                                                       <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.dayTime}</span></div></td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="21"  align="center">o
                                                    <div align="center"><span class="STYLE1">没有订单信息</span></div>
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
                    <jsp:include page="/frame/page.html" />
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
