<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    
    <title>查询月租车位信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	  <LINK href="<%=basePath%>css/base.css" type=text/css rel=stylesheet>
	  <LINK href="<%=basePath%>css/fileList.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
<script>
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

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
<script type="text/javascript">
	function add(){  
			window.location="<%=basePath%>products/parking/specialCar/add.html";
		}
function excelExport(){
	var p_queryValue = $("#p_queryValue").val();
	var p_queryType =$("#p_queryType").val();
	var isUsed = $("#isUsed").val();
	window.location="<%=basePath%>products/parking/specialCar/excelExport.html?queryValue="+p_queryValue+"&queryType="+p_queryType+"&isUsed="+isUsed;
}
function uploadExport(){
document.getElementById('upload').style.display='block';
document.getElementById('bg').style.display='block';
  //$("#bg").css("display", "none");
}
function cencel(){
document.getElementById('upload').style.display='none';
document.getElementById('bg').style.display='none';
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
function uploadMonth(){
	var filepath= $("#file").val();
	filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length)
	filepath = filepath.replace(/[ ]/g,"");//去空格
	if(filepath==null||filepath==''){
		alert("导入文件不能为空！");
	}else if(filepath.toLowerCase()!=="csv"){//转小写
		alert("只能上传csv文件")
	} else{
  $.ajaxFileUpload({  
        type: "POST",  
        url: "<%=basePath%>products/parking/specialCar/uploadExcel.html",
        data:{},
        secureuri:false,
		fileElementId : "file",
		dataType: 'text',
        success: function (jsonStr){
       if(jsonStr=="0"){
					//alert("导入成功");
					document.getElementById('upload').style.display='none';
                    document.getElementById('bg').style.display='none';
                    alert("导入成功");
                    history.go(0);
				}else{
				if(jsonStr=="1"){
				alert("导入失败！");
				}else{
				    alert(jsonStr);
				    }
					document.getElementById('upload').style.display='none';
                    document.getElementById('bg').style.display='none';
                     history.go(0);
				} 
				 
			},
			error: function (data){
				 
			}
        });
	}
}
</script>
  </head>
  <div class="content" id="upload">
	  <div style="height: 50px;background-color:#3f4752">
		  <img  onclick="cencel()" onmouseover="this.style.cursor='pointer'" src="<%=basePath%>/images/new/close.png" class="close_img">
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
  	<form  action="<%=basePath%>products/parking/specialCar/list.html" method="post">
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr height="60">
			        <td class="STYLE4" align="center">请输入查询内容：<input class="btn_tb" type="text" id="p_queryValue" name="p_queryValue" value="${page.params["queryValue"] }"/></td>
			        <td class="STYLE4">请选择查询方式：
			        	<select class="btn_tb" name="p_queryType" id="p_queryType">
			        	<c:if test='${page.params["queryType"] eq null}'>
							<option  value="2">车牌号</option>
			        	    <option  value="1">小区名称</option>
			        	</c:if>
			        	<c:if test='${page.params["queryType"] eq 1}'>
			        	    <option  value="1" selected="selected">小区名称</option>
			  				<option  value="2" >车牌号</option>
			        	</c:if>
			  			<c:if test='${page.params["queryType"] eq 2}'>
			        	    <option  value="1">小区名称</option>
			  				<option  value="2" selected="selected">车牌号</option>
			        	</c:if>
						</select>            
					</td>
					<td class="STYLE4" align="center">记录状态：</td>
					<td class="STYLE4">
						  <select class="btn_tb" name="isUsed" id="isUsed">
							  <option  value="1">有效</option>
							  <%--<c:if test='${!(page.params["isUsed"] eq "3") && !(page.params["isUsed"] eq "0") && !(page.params["isUsed"] eq "1")}'>--%>
								  <%--<option  value="3" >全部</option>--%>
								  <%--<option  value="1" selected>有效</option>--%>
								  <%--<option  value="0">无效</option>--%>
							  <%--</c:if>--%>
							  <%--<c:if test='${page.params["isUsed"] eq "0"}'>--%>
								  <%--<option  value="3">全部</option>--%>
								  <%--<option  value="0" selected="selected">无效</option>--%>
								  <%--<option  value="1" >有效</option>--%>

							  <%--</c:if>--%>
							  <%--<c:if test='${page.params["isUsed"] eq "3"}'>--%>
								  <%--<option  value="3" selected="selected">全部</option>--%>
								  <%--<option  value="0" >无效</option>--%>
								  <%--<option  value="1" >有效</option>--%>

							  <%--</c:if>--%>
							  <%--<c:if test='${page.params["isUsed"] eq "1"}'>--%>
								  <%--<option  value="3">全部</option>--%>
								  <%--<option  value="0">无效</option>--%>
								  <%--<option  value="1" selected="selected">有效</option>--%>
							  <%--</c:if>--%>
						  </select>
					</td>
			        <td class="STYLE4">
						<input class="btn"  type="submit" value="查询"/></td>
			        <td class="STYLE4">
						<security:authorize access="hasAnyRole('AUTH_ADD_0045')">
						<input class="btn" type="button" value="添加" onclick="add()"/></security:authorize>
					</td>
					<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_IMP_0048')">
						<input class="btn" type="button" onclick="uploadExport()" value="导入"/></security:authorize>
					</td>
					<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_EXP_0049')">
						<input class="btn"  type="button" onclick="excelExport()" value="导出"/></security:authorize>
					</td>
			      </tr>
			    </table>
    <table class="table_content" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="0" onmouseover="changeto()"  onmouseout="changeback()">
          <tr height="50" style="background-color: #ecf6ff">
            <td style="width:2%"><div align="center"><span class="STYLE1">序号</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">小区名称</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">车牌号</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">车主姓名</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">车主身份信息</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">车主联系地址</span></div></td>
	    	<td style="width:5%"><div align="center"><span class="STYLE1">车主联系电话</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">车颜色</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">创建时间</span></div></td>
			<td style="width:5%"><div align="center"><span class="STYLE1">记录状态</span></div></td>
			  <security:authorize access="hasAnyRole('AUTH_DEL_0046','AUTH_EDIT_0047')">
			<td class="STYLE1" style="width: 10%"><div align="center">基本操作</div></td></security:authorize>
          </tr>
		  <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
          <tr class="underline" height="50" style="background-color:#FFF">
            <td style="width:2%"><div align="center"><span class="STYLE1">${status.count}</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">${row.parkingName}</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">${row.carNumber}</span></div></td>
	        <td style="width:5%"><div align="center"><span class="STYLE1">${row.owner}</span></div></td>
	        <td style="width:5%"><div align="center"><span class="STYLE1">${row.certificate}</span></div></td>
	        <td style="width:5%"><div align="center"><span class="STYLE1">${row.address}</span></div></td>
            <td style="width:5%"><div align="center"><span class="STYLE1">${row.phone}</span></div></td>
	  	  	<td style="width:5%"><div align="center"><span class="STYLE1">
	  	  	<c:if test="${row.carColor eq 1}">黑</c:if>
	  	  	<c:if test="${row.carColor eq 2}">白</c:if>
	  	  	<c:if test="${row.carColor eq 3}">其他</c:if>
	  	  	</span> </div></td>
	  	  	<td style="width:5%"><div align="center"><span class="STYLE1"><fmt:formatDate  value="${row.createTime}" type="both" pattern="yyyy-MM-dd"/></span></div></td>
			<td style="width:5%"><div align="center"><span class="STYLE1">
				  <c:if test="${row.isUsed eq 0}">
					  无效
				  </c:if>
				  <c:if test="${row.isUsed eq 1}">
					  有效
				  </c:if>
			  </span></div></td>
            <security:authorize access="hasAnyRole('AUTH_DEL_0046','AUTH_EDIT_0047')">
            <td style="width:5%"><div align="center"><span class="STYLE4">
            	<security:authorize access="hasAnyRole('AUTH_EDIT_0047')">
					<c:if test="${row.isUsed eq 1}">
            	<a href="<%=basePath %>products/parking/specialCar/toedit.html?villageId=${row.villageId}&carNumber=${row.carNumber } ">
					<span class="icon_bg blue"><img  class="icon" title="编辑" src="<%=basePath%>/images/new/compile.png"></span></a>
					</c:if>
            	</security:authorize>            	
            	<security:authorize access="hasAnyRole('AUTH_DEL_0046')">
					<a href="javascript:if (confirm('你确定？')) location.href='<%=basePath %>products/parking/specialCar/del.html?villageId=${row.villageId}&carNumber=${row.carNumber }&isUsed=${row.isUsed}';">
						<c:if test="${row.isUsed eq 1}"><span  class="icon_bg red"> <img  class="icon" title="作废" src="<%=basePath%>/images/new/delete.png"></span></c:if>
						<c:if test="${row.isUsed eq 0}">   <span class="icon_bg_recover caolv">
                                <img class="icon" title="恢复" src="<%=basePath%>/images/new/recover.png">
                               <div class="btn_hf">恢复</div>
                            </span></c:if>
					</a>
            	<%--<a href="<%=basePath %>products/parking/specialCar/del.html?villageId=${row.villageId}&carNumber=${row.carNumber }">删除</a>--%>
            	</security:authorize>
            	</span></div>
            </td>
            </security:authorize>
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
    </td>
  </tr>
  <tr>
	<jsp:include page="/frame/page.html" />
    </td>
  </tr>

    </table>  
    </form>
    </div>
</body>
</html>
