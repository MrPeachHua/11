<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <title>区域用户</title>
  <link href="<%=basePath%>/css/pages/style_v2.2.css" type=text/css rel=stylesheet>
  <script src="<%=basePath%>/js/jquery-1.11.1.min.js" type="text/javascript"></script>
  <script src="<%=basePath%>/js/pages/parkingViewV2.js" type="text/javascript"></script>
  <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
  <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>

  <script>
    function exportExcel(link){
      location.href=link;
    }
  </script>
</head>
<body>
<div>
<form method="post" action="<%=basePath%>regionUser/list.html">
  <div class="queryView">
    <ul>
      <li>
        <span>车场:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <input type="text" name="parkingName" value="${param.parkingName}">
      </li>
      <li>
        <span>项目经理:</span>
        <input type="text" name="regionManager" value="${param.regionManager}">
      </li>

      <li>
        <span>区域经理:</span>
        <input type="text" name="regionLeader" value="${param.regionLeader}">
      </li>

      <li style="float: right">
        <input class="searchBtn" type="submit" value="查&nbsp;询">
        <!--<input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">-->
        <input class="searchBtn" type="button" value="新&nbsp;增" onclick="location='<%=basePath%>regionUser/add.html'">
      </li>
    </ul>
  </div>
  <div class="contentView">
    <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
      <thead>
      <tr>
        <td>序号</td>
        <td>车场名称</td>
        <td>区域经理</td>
        <td>项目经理</td>
        <!--<td>日期</td>-->
        <td>操作</td>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${page.resultList}" var="row" varStatus="status">
        <tr>
          <td>${status.count}</td>
          <td>${row.parkingName}</td>
          <td>${row.regionManager}</td>
          <td>${row.regionLeader}</td>
          <!--<td><fmt:formatDate  value="${row.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>-->
          <td>
            <input class="edit" type="button" onclick="location='<%=basePath%>regionUser/${row.id}/edit.html'">
            <input class="delete" type="button" onclick="confirm('你确定？')?location='<%=basePath%>regionUser/${row.id}/delete.html':void(0)">
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <c:if test="${fn:length(page.resultList) == 0}">
      <div class="contentLine">
        没有任何数据
      </div>
    </c:if>
  </div>
  <jsp:include page="/frame/page.html"/>
</form>
</div>
</body>
</html>
