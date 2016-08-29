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

  <title>修改权限信息</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
  <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
  <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  <script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
  <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  <%--<script type="text/javascript">--%>
  <%--function query(){--%>
  <%--window.location="<%=basePath%>users/rolelist.html";--%>
  <%--}--%>

  <%--</script>--%>
  <style type="text/css">
    td{
      border: 1px solid dodgerblue;
    }
  </style>

</head>

<body>
<form action="<%=basePath%>system/authority/update.html" method="post"name="form2" onsubmit="return checkForm('form2');">
  <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr class=editHeaderTr>
      <td class=editHeaderTd colSpan=7>  请输入权限控制的详细信息</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;"><div align="center">权限名称：</div></td>
      <td colspan="3" bgcolor="#FFFFFF"><input id ="authorityName" type="text" <c:if test="${shareUser.module != null || !shareUser.module eq '' }">readonly="readonly" </c:if>  value="${sysAuthorities.authorityName}"   check_str="权限名称" style="width: 138px"  name="authorityName" >
        &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;"><div align="center">权限描述：</div></td>
      <td colspan="3" bgcolor="#FFFFFF"><input id = "authorityDesc" type="text" value="${sysAuthorities.authorityDesc}" <c:if test="${shareUser.module != null || !shareUser.module eq '' }">readonly="readonly" </c:if>  check_str="权限描述"  style="width: 138px"  name="authorityDesc" >
        &nbsp;</td>
    </tr>
    <tr>
      <%--<td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;"><div align="center">所属系统：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">
        <script type="text/javascript" src="<%=basePath%>/js/pages/moduleView.js"></script>
        <input readonly="readonly" type="text" style="width: 138px" <c:if test="${shareUser.module ==null || shareUser.module =='' }"> onclick="moduleView.show($('#module'), $(this))" </c:if>/>
        <input id="module" type="hidden" name="module" value="${sysResources.module}" />
      </td>--%>
      <%--<td colspan="3" bgcolor="#FFFFFF">
        <select name="module" style="width: 138px" <c:if test="${shareUser.module != null || !shareUser.module eq '' }">disabled="disabled" </c:if>>
        <c:forEach items="${module}" var="row" varStatus="status">
          <option value="<c:out value="${row.dictValue}"/>"><c:out value="${row.dictName}"/></option>
        </c:forEach>
      </select>
        &nbsp;
      </td>--%>
      <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;"><div align="center">是否启用：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">

          <select name="enabled"  style="width: 140px"  <c:if test="${shareUser.module != null || !shareUser.module eq '' }">disabled="disabled" </c:if> >
            <c:if test="${sysAuthorities.enabled eq 1}">
            <option value="1">是</option>
            <option value="0">否</option>
            </c:if>
            <c:if test="${sysAuthorities.enabled eq 0}">
            <option value="0">否</option>
            <option value="1">是</option>
            </c:if>
          </select>
        &nbsp;</td>
      <input id ="authorityId" type="hidden" value="${sysAuthorities.authorityId}"  name="authorityId">
      <input id ="issys" type="hidden" value="${sysAuthorities.issys}"  name="issys" >
   <%-- </tr>
    <tr>--%>
      <td bgcolor="#FFFDF0">
        <div align="center">
          角色：
        </div>
      </td>

      <td colspan="6" bgcolor="#FFFFFF" style=" border: 1px solid dodgerblue;">
        <input type="button" class="btn btn-default" value="选择角色" style="width: 140px"  />
      </td>
    </tr>
  </table>
  <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr bgcolor="#ECF3FD">
      <td width="10%">&nbsp;</td>
      <td width="12%"><input type="submit" name="Submit" value="提交"></td>
      <td width="12%"><input type="reset" name="button"    value="重置"></td>
      <td width="12%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
    </tr>
  </table>
  <div class="modal" id="mymodal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title">请选择</h4>
        </div>
        <div class="modal-body">
          <p><c:forEach items="${roles}" var="roles" >
            <input <c:if test="${roles.authorityId != null&&roles.authorityId != ''}"> checked = "checked"</c:if> type="checkbox"  style="width: 50px" name="sysRoles" id="sysRoles" value="${roles.roleId}">${roles.roleName}[${roles.roleDesc}]</br>
          </c:forEach></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <button type="button" class="btn btn-primary">保存</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
  </div>
  <script>

    $(function(){
      $(".btn").click(function(){
        $("#mymodal").modal("toggle");
      });
    });
  </script>
</form>
</body>
</html>
