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

  <title>车场信息</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>
	/css/base.css"/>
  <LINK href="<%=basePath%>css/fileList.css" type=text/css rel=stylesheet>
  <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  <LINK href="<%=basePath%>js/My97DatePicker/skin/WdatePicker.css" type=text/css rel=stylesheet>
  <script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
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
      window.location="<%=basePath%>products/parking/parkingStatus/add.html";
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
    function excelExport() {
      var p_parkingName = $("#p_parkingName").val();
      var p_hourSection = $("#p_hourSection").val();
      var p_parkingStatus = $("#p_parkingStatus").val();
      window.location = "<%=basePath%>products/parking/parkingStatus/excelExport.html?p_parkingName=" + p_parkingName + "&p_hourSection=" + p_hourSection + "&p_parkingStatus=" + p_parkingStatus;
    }
    function uploadExport() {
      document.getElementById('upload').style.display = 'block';
      document.getElementById('bg').style.display = 'block';
      //$("#bg").css("display", "none");
    }
    function cencel() {
      document.getElementById('upload').style.display = 'none';
      document.getElementById('bg').style.display = 'none';
    }
    // function checkCsv(){
    // 	var filepath= $("#file").val();
    // 	filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length)
    // 	filepath = filepath.replace(/[ ]/g,"");//去空格
    // 	if(filepath.toLowerCase()!=="csv"){//转小写
    // 		alert("只能上传csv文件");
    // 		return false;
    // 	}
    // 	return true;
    // }
    /**
     *
     * @param text1 小区名称
     * @param text2 车牌号
     * @param text3 有效开始时间
     * @param text4 有效结束时间
     * @param text5 单价
     */
    function addFree(text1, text2, text3, text4, text5, text6, text7) {
      document.getElementById('addCoupon').style.display = 'block';
      if (text7 != "") {
        var str = text7.replace(/-/g, "/");
        var date = new Date(str);
        date.setDate(date.getDate() + 1);
        var dateTime = "";
        if (date.getMonth() < 9) {
          if(date.getDate()<10){
            dateTime = date.getFullYear() + "-0" + (date.getMonth() + 1) + "-0" + date.getDate();
          }else{
            dateTime = date.getFullYear() + "-0" + (date.getMonth() + 1) +"-"+ date.getDate();
          }
        } else {
          if(date.getDate()<10){
            dateTime = date.getFullYear() + "-" + (date.getMonth() + 1) + "-0" + date.getDate();
          }else{
            dateTime = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"+date.getDate();
          }
        }
        $("#form_beginTime1").val(dateTime);
      }

      $("#form_beginTime").val(text3);
      $("#carNumber").val(text2);
      $("#price").val(text5);
      $(".villageName").val(text6);
      $("#text1").val(text1);
      $("#bg").css({
        display: "block"
      });
    }
    function getmonthly() {
      var beginTime = $("#form_beginTime1").val();
      var endTime = $("#form_endTime").val();
      if (beginTime != "" && endTime != "") {
        if (endTime.split("-")[0] >= beginTime.split("-")[0]) {
          var monthNumYear = (endTime.split("-")[0] - beginTime.split("-")[0]) * 12;
          var monthNum = monthNumYear + (endTime.split("-")[1] - beginTime.split("-")[1]);
          $("#monthNum").val(monthNum + 1);
        } else if (endTime.split("-")[0] < beginTime.split("-")[0]) {
          $("#monthNum").val("0");
        }
      } else {
        $("#monthNum").val("0");
      }
    }
    function uploadMonth() {
      var filepath = $("#file").val();
      filepath = filepath.substring(filepath.lastIndexOf('.') + 1, filepath.length)
      filepath = filepath.replace(/[ ]/g, "");//去空格
      if (filepath == null || filepath == '') {
        alert("导入文件不能为空！");
      } else if (filepath.toLowerCase() !== "csv") {//转小写
        alert("只能上传csv文件")
      } else {
        $.ajaxFileUpload({
          type: "POST",
          url: "<%=basePath%>products/parking/parkingStatus/uploadExcel.html",
          data: {},
          secureuri: false,
          fileElementId: "file",
          dataType: 'text',
          success: function (jsonStr) {
            if (jsonStr == "0") {
              //alert("导入成功");
              document.getElementById('upload').style.display = 'none';
              document.getElementById('bg').style.display = 'none';
              alert("导入成功");
              history.go(0);
            } else {
              if (jsonStr == "1") {
                alert("导入失败！");
              } else {
                alert(jsonStr);
              }
              document.getElementById('upload').style.display = 'none';
              document.getElementById('bg').style.display = 'none';
              history.go(0);
            }

          },
          error: function (data) {

          }
        });
      }
    }
  </script>

</head>
<div class="content" id="upload">
  <div style="height: 50px;background-color:#3f4752">
    <img onclick="cencel()" onmouseover="this.style.cursor='pointer'" src="<%=basePath%>/images/new/close.png"
         class="close_img">
  </div>
  <div class="file_img">
    <input type="file" name="file" id="file"/>

    <p>&nbsp;&nbsp;&nbsp;&nbsp;选择上传文件</p>
    <%--<img src="<%=basePath%>/images/new/file.png">--%>
  </div>
  <div class="check_btn">
    <span><a onclick="uploadMonth()" onmouseover="this.style.cursor='pointer'">导 入</a></span>
    <span><a onclick="cencel()" onmouseover="this.style.cursor='pointer'">取 消</a></span>
  </div>

</div>
<body>
<div id="bg"></div>
<form  action="<%=basePath%>products/parking/parkingStatus/list.html" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr height="60">
            <td>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td class="STYLE4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车场名称：
                    <input type="text" id="p_parkingName" name="p_parkingName" value="${param.p_parkingName}">
                  </td>
                  <td class="STYLE4">时段：
                    <input type="text" id="p_hourSection" name="p_hourSection" value="${param.p_hourSection}">
                  </td>
                  <td class="STYLE4">车位状态：
                    <%--<input type="text" name="p_parkingStatus" value="${param.p_parkingStatus}">--%>
                    <select name="p_parkingStatus" id="p_parkingStatus">
                      <option value="">全部</option>
                      <option <c:if test="${param.p_parkingStatus eq '0'}">selected="selected"</c:if> value="0">空</option>
                      <option <c:if test="${param.p_parkingStatus eq '1'}">selected="selected"</c:if> value="1">紧张</option>
                    </select>
                  </td>

                  <%--<td class="STYLE4">开始时间：<input type="text" name="p_startDate" valid="required" value="${param.p_startDate}"--%>
                                                 <%--errmsg="查询日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:p_startDate,dateFmt:'yyyy-MM-dd'})"/></td>--%>
                  <%--<td class="STYLE4">结束时间：<input type="text" name="p_endDate" valid="required" value="${param.p_endDate}"--%>
                                                 <%--errmsg="查询日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:p_endDate,dateFmt:'yyyy-MM-dd'})"/></td>--%>

                  <td class="STYLE4">
                    <input  type="submit" value="查询" class="btn" />
                  </td>
                  <td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_ADD_0029')">
                    <input type="button" class="btn" value="添加" onclick="add()" style="width: 50px" /></security:authorize>
                  </td>
                  <td class="STYLE4"><input class="btn" type="button" onclick="uploadExport()" value="导入"/>
                  </td>
                  </td>
                  <td class="STYLE4"><input class="btn" type="button" onclick="excelExport()" value="导出"/>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>


        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr  height="50" style="background-color:#ecf6ff">
            <td width="8"></td>
            <td>
              <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
                <tr>
                  <td style="width:5%" ><div align="center"><span class="STYLE1">车场名称</span></div></td>
                  <td style="width:5%" ><div align="center"><span class="STYLE1">时段(24小时制)</span></div></td>
                  <td style="width:5%" ><div align="center"><span class="STYLE1">状态</span></div></td>
                  <td style="width:2%" ><div align="center"><span class="STYLE1">基本操作</div></td>
                </tr>
                <c:choose>
                  <c:when test="${fn:length(page.resultList)>0}">
                    <c:forEach items="${page.resultList}" var="row" varStatus="status">
                      <tr>
                        <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.parkingName}</span></div></td>
                        <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.hourSection}</span></div></td>
                        <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">
                          <c:if test="${row.status eq '0'}">空</c:if>
                          <c:if test="${row.status eq '1'}">紧张</c:if>
                          <c:if test="${row.status eq '2'}">满</c:if>
                        </span></div></td>
                        <security:authorize access="hasAnyRole('AUTH_DEL_0030','AUTH_EDIT_0031')">
                        <td height="20" bgcolor="#FFFFFF" style="width: 4%"><div align="center"><span class="STYLE4">
                        	<security:authorize access="hasAnyRole('AUTH_EDIT_0031')">
                                                     <img src="<%=imagePath%>edt.gif" width="16" height="16" />
                                                     <a href="<%=basePath %>products/parking/parkingStatus/${row.id}/edit.html">编辑</a>
			            	</security:authorize>
			       			<security:authorize access="hasAnyRole('AUTH_DEL_0030')">
                                                     <img src="<%=imagePath%>del.gif" width="16" height="16" />
                                                     <a href="<%=basePath %>products/parking/parkingStatus/${row.id}/del.html">删除</a>
                            </security:authorize></span></div>
                        </td>
                        </security:authorize>
                      </tr>
                    </c:forEach>
                  </c:when>
                  <c:otherwise>
                    <tr>
                      <td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
                        <div align="center"><span class="STYLE1">没有相关信息</span></div>
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
