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

  <title>修改角色信息</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
  <script type="text/javascript" src="<%=basePath%>/js/CheckForm.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript"
          src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
  <link href="<%=basePath%>/css/pages/style_v2.1.css" type=text/css rel=stylesheet>
  <script type="text/javascript" src="<%=basePath%>/js/pages/parkingView.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>

  <style type="text/css">
    .content {
      display: none;
      position: absolute;
      top: 10%;
      left: 20%;
      width: 642px;
      /* width: 50%; */
      /* height: 100%; */
      /* border: 1px solid black; */
      background-color: #E3F0F8;
      z-index: 1002;
      overflow: auto;
    }
   /* td{
      border: 0.5px solid dodgerblue;

    }*/
    #bg {
      background-color: #666;
      position: absolute;
      z-index: 99;
      left: 0;
      top: 0;
      display: none;
      width: 100%;
      height: 100%;
      opacity: 0.001;
      filter: alpha(opacity = 50);
    }
  </style>
  <script type="text/javascript">

    function shengwukelian(){
      document.getElementById('content').style.display='block';
      document.getElementById('bg').style.display='block';
    }
    function mengbi(){
      document.getElementById('content').style.display='none';
      document.getElementById('bg').style.display='none';
    }
  </script>
</head>

<body>
<form action="<%=basePath%>system/roles/update.html" method="post"name="form2" onsubmit="return checkForm('form2');">
  <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr class=editHeaderTr>
      <td class=editHeaderTd colSpan=7>  请输入角色的详细信息</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0" ><div align="center">角色名称：</div></td>
      <td colspan="3" bgcolor="#FFFFFF"><input id ="roleName" type="text" value="${sysRoles.roleName}"   check_str="资源名称" style="width: 138px"  name="roleName" >
        &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0" ><div align="center">所属系统：</div></td>

      <td colspan="3" bgcolor="#FFFFFF">
      <select name="module" style="width: 138px" class="dictionary" data-dict-code="module" data-current-value="${sysRoles.module}"  data-dict-value="${shareUser.module}" >
        <c:if test="${shareUser.module =='' || shareUser.module ==null }" ><option value=""></option></c:if>
      </select>
      &nbsp;
      </td>
      </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0" ><div align="center">是否启用：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">

        <select name="enabled"  style="width: 140px" >
          <c:if test="${sysRoles .enabled eq 1}">
            <option value="1">是</option>
            <option value="0">否</option>
          </c:if>
          <c:if test="${sysRoles.enabled eq 0}">
            <option value="0">否</option>
            <option value="1">是</option>
          </c:if>
          <c:if test="${sysRoles.enabled eq null}">
            <option value="1">是</option>
            <option value="0">否</option>
          </c:if>
        </select>
        &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0" ><div align="center">权限名称：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">
        <input type="button" class="btn btn-default" value="选择权限" onclick="shengwukelian()"  name="ai" style="width: 138px"  />
      </td>

      <input id ="resourceId" type="hidden" value="${sysRoles.roleId}"  name="roleId">
      <input id ="issys" type="hidden" value="${sysRoles.issys}"  name="issys" >
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0"  ><div align="center">角色描述：</div></td>
      <td colspan="6" bgcolor="#FFFFFF"><textarea id = "roleDesc"  check_type="string,0,50"  check_str="角色描述"  style="width: 138px"  name="roleDesc" >${sysRoles.roleDesc}</textarea>
        &nbsp;</td>
      <%--<td width="19%" bgcolor="#FFFDF0" ><div align="center">车场名称：</div></td>
      <td colspan="3" bgcolor="#FFFFFF"><input  type="text"  onclick="parkingView.show({parkingId: document.getElementById('parkingId'), parkingName: this})"     style="width: 138px" />
        &nbsp;</td>
      <td colspan="3" bgcolor="#FFFFFF"><input id="parkingId" name="parkingId"  type="hidden" value="${sysRoles.parkingId}"   />
        &nbsp;</td>--%>
    </tr>

  </table>
  <%--<table  class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr>
      <td width="19%" bgcolor="#FFFDF0" ><div align="center">已经拥有的权限：</div></td>
      <td colspan="3" bgcolor="#FFFFFF"  >
        <c:forEach items="${listSysAuthor}" var="listSysAuthor" varStatus="haha" >
          &lt;%&ndash;
                  <td  style=" border: 1px solid dodgerblue;"><span class="STYLE1">${listResources.authorityName}[${listResources.authorityDesc}]</span></td>
          &ndash;%&gt;
          <input  type="text"  style="width: 300px; border: 0.1px solid dodgerblue; " readonly="readonly" name="authorityName1" id="authorityName1" value="${listSysAuthor.authorityName}[${listSysAuthor.authorityDesc}]">
          <c:if test="${(haha.index+1) % 3 == 0}"><br></c:if></c:forEach>
      </td>
    </tr>

  </table>--%>
  <table   width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 0px">
    <tr>
            <td height="22"  bgcolor="#FFFFFF" style="width: 20%;border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">已经拥有的权限名称</span></div></td>
            <td height="22"  bgcolor="#FFFFFF" style="width: 20%;border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">已经拥有的权限描述 </span></div></td>
          </tr>
          <c:forEach items="${listSysAuthor}" var="listSysAuthor" varStatus="haha" >
                <tr>
                  <td height="20" bgcolor="#FFFFFF" style="width:  20%;border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">${listSysAuthor.authorityName}</span></div></td>
                  <td height="20" bgcolor="#FFFFFF" style="width:  20%;border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">${listSysAuthor.authorityDesc}</span></div></td>
                </tr>
           </c:forEach>
        </table>
  <div id="content" class="content" style="height: 400px; width: 800px">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="30" background="<%=imagePath%>tab_05.gif">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
              <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="STYLE4" align="center">&nbsp;&nbsp;权限描述：
                      <input id="authorityDesc"
                             name="authorityDesc"
                             style=" width: 145px" >
                    </td>
                    <td class="STYLE4" align="center"><input  type="button" onclick="selectDesc()" value="查询" style="width:90px"/> &nbsp;&nbsp;&nbsp;
                    </td>
                    <td class="STYLE4" align="center"><input  type="button" onclick="mengbi()" value="确定" style="width:90px"/> &nbsp;&nbsp;&nbsp;
                    </td>
                    <td class="STYLE4" align="center"><input  type="button" onclick="mengbi()" value="取消" style="width:90px"/> &nbsp;&nbsp;&nbsp;
                    </td>
                  </tr>

                </table>
              </td>
              <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" /></td>
            </tr>
          </table>
        </td>
      </tr>
      <div/>
      <tr>
        <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
              <td>
                <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
                  <tr>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">权限ID</span></div></td>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">权限名称</span></div></td>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">权限描述 </span></div></td>
                    <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><input type="checkbox" id="checkall" onclick="checkAll('authorityName')" />全选</div></td>
                  </tr>
                  <c:choose>
                    <c:when test="${fn:length(roles)>0}">
                      <c:forEach items="${roles}" var="row" varStatus="status">
                        <tr>
                          <td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center"><span class="STYLE1" >${row.authorityId}</span></div></td>
                          <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.authorityName}</span></div></td>
                          <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1 desc">${row.authorityDesc}</span></div></td>
                          <td height="20" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><input type="checkbox" <c:if test="${row.roleId != null&&row.roleId != ''}">checked="checked" </c:if> name="authorityName"  value="${row.authorityId}" /> </div></td>
                        </tr>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                      <tr>
                        <td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
                          <div align="center"><span class="STYLE1">没有信息</span></div>
                        </td>
                      </tr>
                    </c:otherwise>
                  </c:choose>

                </table>
              </td>
            </tr>
          </table>
    </table>
  </div>
  <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr bgcolor="#ECF3FD">
      <td width="10%">&nbsp;</td>
      <td width="12%"><input type="submit" name="Submit" value="提交"></td>
      <td width="12%"><input type="reset" name="button"    value="重置"></td>
      <td width="12%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
    </tr>
  </table>

</form>
<script type="text/javascript">
  function selectDesc() {
    var desc = $("#authorityDesc").val();
    $(".desc").each(function () {
      var $tr = $(this).parent().parent().parent();
      if (desc == null || desc.length == 0 ) {
        $tr.show();
      } else if ($(this).text().indexOf(desc) == -1) {
        $tr.hide();
      } else {
        $tr.addClass("myCheck");
        $tr.show();
      }
    });
  }
  function  checkAll(Obj) {
    var collid = document.getElementById("checkall");
    var coll = document.getElementsByName(Obj);
    var desc = $("#authorityDesc").val();
    if (collid.checked){
      console.log(coll.length);
      for(var i = 0; i < coll.length; i++){

        var p=$(coll[i]).closest('tr').attr("class");
        if(desc==null||desc==''){
          coll[i].checked = true;
        }else{
          if(p=="myCheck"){
            coll[i].checked = true;
          }
        }
      }
    }else{
      for(var i = 0; i < coll.length; i++){
        var p=$(coll[i]).closest('tr').attr("class");
        if(desc!=null&&desc!=''){
          if(p=="myCheck") {
            coll[i].checked = false;
          } else {
            coll[i].checked = true;
          }
        }else{
          coll[i].checked = false;
        }

      }
    }
  }
</script>
</body>
</html>
