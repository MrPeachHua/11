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
  <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
<%--  <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">--%>
  <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  <script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
  <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  <script type="text/javascript">

  </script>
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
    function queryResources(){
      var  authorityNames=$("#authorityNames").val();
      $.ajax({


      })
    }
  </script>
  <script type="text/javascript">
    function smg(){
      var resourceName=$("#resourceName").val();
      $.ajax({
        type: "POST",
        url: "<%=basePath%>system/resources/repeat.html",
        data:{"resourceName":resourceName},
        dataType: "json",
        success: function (jsonStr) {
          if(jsonStr==1){
            alert("存在重复数据！")
          }
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
</head>

<body>
<form action="<%=basePath%>system/resources/update.html" method="post"name="form2" onsubmit="return checkForm('form2');">
  <table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
    <tr class=editHeaderTr>
      <td class=editHeaderTd colSpan=7>  请输入资源的详细信息</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0"  style=" border: 1px solid dodgerblue;"><div align="center" >资源名称：</div></td>
      <td colspan="3" bgcolor="#FFFFFF"><input id ="resourceName" type="text" value="${sysResources.resourceName}"   check_str="资源名称" style="width: 138px"  name="resourceName"  >
        &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;" ><div align="center">优先级：</div></td>

      <td colspan="3" bgcolor="#FFFFFF"><input id = "priority" type="text" value="${sysResources.priority}"  style="width: 138px"  name="priority" >
        &nbsp;</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;"><div align="center">资源类型：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">
        <select  id="resourceType"  name="resourceType" style="width: 135px">
          <c:forEach var="list" items="${list}">
            <option <c:if test="${list.dictValue eq sysResources.resourceType}">selected="selected" </c:if> value="${list.dictValue}">${list.dictName}</option>
          </c:forEach>
        </select>
        <%--<input id ="resourceType" type="text" value="${sysResources.resourceType}"   check_str="资源类型" style="width: 138px" required name="resourceType" >--%>
        &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;" ><div align="center">是否启用：</div></td>
      <td colspan="3" bgcolor="#FFFFFF">

        <select name="enabled"  style="width: 138px" >
          <c:if test="${sysResources .enabled eq 1}">
            <option value="1">是</option>
            <option value="0">否</option>
          </c:if>
          <c:if test="${sysResources.enabled eq 0}">
            <option value="0">否</option>
            <option value="1">是</option>
          </c:if>
          <c:if test="${sysResources.enabled eq null}">
            <option value="1">是</option>
            <option value="0">否</option>
          </c:if>
        </select>
        &nbsp;</td>
    </tr>
    <tr>
     <%-- <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;" ><div align="center">所属系统：</div></td>

      <td colspan="3" bgcolor="#FFFFFF">
        <script type="text/javascript" src="<%=basePath%>/js/pages/moduleView.js"></script>
        <input readonly="readonly" style="width: 138px" type="text" onclick="moduleView.show($('#module'), $(this))" />
        <input id="module" type="hidden" name="module" value="${sysResources.module}" />
    </td>--%>
      <td width="19%"  style=" border: 1px solid dodgerblue;" bgcolor="#FFFDF0"  ><div align="center">权限名称：</div></td>
      <td colspan="6" bgcolor="#FFFFFF"  >
        <input class="btn btn-default" type="button" readonly="readonly" name="ai" onclick="shengwukelian()" value="选择权限" style="width: 138px"   />
      </td>
    </tr>
    <tr>
    <td width="19%" bgcolor="#FFFDF0"  style=" border: 1px solid dodgerblue;"><div align="center">资源描述：</div></td>
    <td colspan="6" bgcolor="#FFFFFF"><textarea id = "resourceDesc" type="text" value=""   check_type="string,0,50"  check_str="资源描述"  style="width: 722px"  name="resourceDesc" >${sysResources.resourceDesc}</textarea>
      &nbsp;</td></tr>
      <tr>
      <td width="19%" bgcolor="#FFFDF0"  style=" border: 1px solid dodgerblue;" ><div align="center">访问路径：</div></td>
      <td colspan="6" bgcolor="#FFFFFF"><input id = "resourceString" type="text" value="${sysResources.resourceString}"   check_str="访问路径"  style="width: 722px"  name="resourceString" >
        &nbsp;</td>
      <input id ="resourceId" type="hidden" value="${sysResources.resourceId}"  name="resourceId">
      <input id ="issys" type="hidden" value="${sysResources.issys}"  name="issys" >
    </tr>

  <%--  <div id="content" class="content" style="display: none;width: 500px;height: 200px;" align="left">
      <c:forEach items="${listResources}" var="listResources" >
        <input <c:if test="${listResources.resourceId != null&&listResources.resourceId != ''}"> checked = "checked"</c:if> type="checkbox"  style="width: 50px" name="authorityName" id="authorityName" value="${listResources.authorityId}">${listResources.authorityName}
      </c:forEach>
      <div align="right" style="margin-top: 80px">
        <input type="button" onclick="mengbi()" s value="确定">
        <input type="button" onclick="mengbi()" value="取消">
      </div>
    </div>--%>


  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 0px">
    <tr>
      <td height="22"  bgcolor="#FFFFFF" style="width: 20%; border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">已经拥有的权限名称</span></div></td>
      <td height="22"  bgcolor="#FFFFFF" style="width: 20%; border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">已经拥有的权限描述 </span></div></td>
    </tr>
    <c:forEach items="${listSysAuthor}" var="listSysAuthor" varStatus="haha" >
      <tr>
        <td height="20" bgcolor="#FFFFFF" style="width:  20%; border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">${listSysAuthor.authorityName}</span></div></td>
        <td height="20" bgcolor="#FFFFFF" style="width:  20%; border: 0.5px solid dodgerblue;"><div align="center"><span class="STYLE1">${listSysAuthor.authorityDesc}</span></div></td>
      </tr>
     <%-- <c:if test="${(haha.index+1) % 3 == 0}"><br></c:if>--%></c:forEach>
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
                               style=" width: 145px">
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
                      <c:when test="${fn:length(listResources)>0}">
                        <c:forEach items="${listResources}" var="row" varStatus="status">
                          <tr>
                            <td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center"><span class="STYLE1" >${row.authorityId}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.authorityName}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1 desc">${row.authorityDesc}</span></div></td>
                            <td height="20" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><input type="checkbox" <c:if test="${row.resourceId != null&&row.resourceId != ''}">checked="checked" </c:if> name="authorityName" value="${row.authorityId}" /> </div></td>
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
</script>
</body>
</html>
