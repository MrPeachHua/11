<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

  <title>修改套餐信息</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
  <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
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
      $("#villageName").val(text1);
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
    /* td { text-overflow: ellipsis; white-space: nowrap; overflow: hidden;width: 200px; } */
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
<form action="<%=basePath%>products/price/update.html" method="post"name="form2" onsubmit="return checkForm('form2');">
  <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr class=editHeaderTr>
      <td class=editHeaderTd colSpan=7>  请输入套餐的详细信息</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">
        <input type="text"   maxlength="20"  style=" width: 165px"  required
               id="villageName" value="${packagePrice.parkingName}" name="parkingName" onclick="parkingPop()" >
        <input type="hidden"
               name = "parkingId" value="${packagePrice.parkingId}" id="parkingId"/>
      </td>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">选择套餐：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">
        <input   type="checkbox"  <c:if test="${fn:contains(packagePrice.week, '周一')}">checked="checked"</c:if>    style="width: 40px" value="周一"  name="week" >周一
        <input   type="checkbox"  <c:if test="${fn:contains(packagePrice.week, '周二')}">checked="checked"</c:if>    style="width: 40px"  value="周二" name="week" >周二
        <input   type="checkbox" <c:if test="${fn:contains(packagePrice.week, '周三')}">checked="checked"</c:if>     style="width: 40px" value="周三"  name="week" >周三
        <input  type="checkbox" <c:if test="${fn:contains(packagePrice.week, '周四')}">checked="checked"</c:if>     style="width: 40px"  value="周四" name="week" >周四
        <input  type="checkbox"  <c:if test="${fn:contains(packagePrice.week, '周五')}">checked="checked"</c:if>    style="width: 40px"  value="周五" name="week" >周五
        <input   type="checkbox" <c:if test="${fn:contains(packagePrice.week, '周六')}">checked="checked"</c:if>     style="width: 40px"  value="周六" name="week" >周六
        <input   type="checkbox"  <c:if test="${fn:contains(packagePrice.week, '周日')}">checked="checked"</c:if>    style="width: 40px" value="周日"  name="week" >周日
        &nbsp;</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">价格：</div></td>

      <td colspan="3" bgcolor="#FFFFFF"><input id = "price" type="text" required  value="<fmt:parseNumber integerOnly="true" value="${packagePrice.price/100}"/>"   style="width: 165px"  name="price" >
        &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">选择属于哪一天：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">
        <input  type="radio" <c:if test="${fn:contains(packagePrice.week1, '周一')}">checked="checked"</c:if>     style="width: 40px" value="周一"  name="week1" >周一
        <input   type="radio" <c:if test="${fn:contains(packagePrice.week1, '周二')}">checked="checked"</c:if>     style="width: 40px"  value="周二" name="week1" >周二
        <input   type="radio" <c:if test="${fn:contains(packagePrice.week1, '周三')}">checked="checked"</c:if>      style="width: 40px" value="周三"  name="week1" >周三
        <input  type="radio"  <c:if test="${fn:contains(packagePrice.week1, '周四')}">checked="checked"</c:if>    style="width: 40px"  value="周四" name="week1" >周四
        <input  type="radio"  <c:if test="${fn:contains(packagePrice.week1, '周五')}">checked="checked"</c:if>    style="width: 40px"  value="周五" name="week1" >周五
        <input   type="radio" <c:if test="${fn:contains(packagePrice.week1, '周六')}">checked="checked"</c:if>     style="width: 40px"  value="周六" name="week1" >周六
        <input   type="radio" <c:if test="${fn:contains(packagePrice.week1, '周日')}">checked="checked"</c:if>     style="width: 40px" value="周日"  name="week1" >周日
        &nbsp;</td>
      <input type="hidden" name="id" value="${packagePrice.id}"/>

    </tr>
  </table>
  <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr bgcolor="#ECF3FD">
      <td width="10%">&nbsp;</td>
      <td width="12%"><input type="submit" name="Submit" value="提交"></td>
      <td width="12%"><input type="reset" name="button"    value="重置"></td>
      <td width="12%"><input type="button" name="button"  onClick="location.href='<%=basePath%>products/price/list.html'"  value="返回"></td>
    </tr>
  </table>

</form>
<script type="text/javascript">
  var msg = '${requestScope.msg}';
  if (msg != null && msg.length > 0) {
    alert(msg);
  }

  $("form").submit(function () {
    var count = 0;
    $("input[name=week]").each(function () {
      if (this.checked) {
        if (++count > 3) {
          return;
        }
      }
    });
    if (count < 2 || count > 3) {
      alert('套餐至少要选2天,但也不能超过3天');
      return false;
    }
    return true;
  });
</script>
</body>
</html>
