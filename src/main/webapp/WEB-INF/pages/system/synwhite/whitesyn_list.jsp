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
    <title>白名单同步</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <LINK href="<%=basePath%>js/My97DatePicker/skin/WdatePicker.css" type=text/css rel=stylesheet>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
			background-color: #eeeeee;
        }
        .STYLE1 {font-size: 12px}
        .STYLE3 {font-size: 12px; font-weight: bold; }
        .STYLE4 {
            color: #03515d;
            font-size: 12px;
        }
		li {list-style-type:none;}
        a{
            text-decoration: none;
            color: #033d61;
            font-size: 12px;
        }

        A:hover {
            COLOR: #f60; TEXT-DECORATION: underline
        }
		.head{
			width: 100%;
			height: 20px;
		}
		.table_white{
			width: 98%;
			margin: 0 auto;
			background-color: #FFF;
		}
		.head_title td{
			height: 50px;
			background-color:#ecf6ff;
		}
		.ui_input_btn01{
			width: 80px;
			height:20px;
			color: #FFF;
			background-color:#39d5b8;
			border-radius: 2px;
			border: none;
			margin: 5px 35px;
			-moz-box-shadow:0 10px 3px #888888; /* 老的 Firefox */
			box-shadow: 0 3px 2px #30b199;
		}
		-->
    </style>

    <script type="text/javascript">
	function update(){
		pk();
		var oldCronExpression = $("#old_cron_expression_id").val();
		var newCronExpression = $("#cron_expression").val();
		
		if (oldCronExpression == newCronExpression)
		{
			alert('请选择新的间隔时间');
			return ;
		}
		var formData = $("#update_form").serialize();  //获取本页表单数据
		$.ajax({
			type : "POST", // 请求提交的 GET OR POST
			url : "<%=basePath%>system/whitesyn/rescheduleJobForm", // 请求的地址，如果提交方式为GET，则在URL上带参数
			data : formData, // 以POST方式提交时所带的参数
			dataType:"text",
			success : function(data) {
				alert(data);
				window.location.href="<%=basePath%>system/whitesyn/list.html";
			}
		});
	}
	/**
	*立刻执行一次
	*/
	function triggerJob(){
		pk();
		var formData = $("#update_form").serialize();  //获取本页表单数据
		$.ajax({
			type : "POST", // 请求提交的 GET OR POST
			url : "<%=basePath%>system/whitesyn/excuteQuatzOnece", // 请求的地址，如果提交方式为GET，则在URL上带参数
			data : formData, // 以POST方式提交时所带的参数
			dataType:"text",
			success : function(data) {
				alert(data);
				window.location.href="<%=basePath%>system/whitesyn/list.html";
			}
		});
	}
		
	
	/**
	*暂停定时任务
	*/
	function pauseJob()
	{
		var formData = $("#update_form").serialize();  //获取本页表单数据
		$.ajax({
			type : "POST", // 请求提交的 GET OR POST
			url : "<%=basePath%>system/whitesyn/pauseJobForm", // 请求的地址，如果提交方式为GET，则在URL上带参数
			data : formData, // 以POST方式提交时所带的参数
			dataType:"text",
			success : function(data) {
				
				window.location.href="<%=basePath%>system/whitesyn/list.html";
			}
		});

	}
	
	
	function resumeJob()
	{	
		var formData = $("#update_form").serialize();  //获取本页表单数据
		$.ajax({
			type : "POST", // 请求提交的 GET OR POST
			url : "<%=basePath%>system/whitesyn/resumeJobForm", // 请求的地址，如果提交方式为GET，则在URL上带参数
			data : formData, // 以POST方式提交时所带的参数
			dataType:"text",
			success : function(data) {
				
				window.location.href="<%=basePath%>system/whitesyn/list.html";
			}
		});
	}
	
	function choseParkings(){
		var parkingStr = $("#getParkingStr").val();
		$.ajax({  
	        type: "POST",  
	        url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
	        async:true,
	        data:{},
	        dataType: "json",   
	        success: function (jsonStr) {
	        	//$(".parkingListTr").remove();
	        	console.log($("#parkingScope").html());
	        	var parkingIds = $("#userParkingId").val();
	        	var html = '<li><input id="indch" onclick="chall()" type="checkbox"/><span class="STYLE1">全选</span></li>';
	        	if(null!=parkingIds&parkingIds!=""){
	        		//隐藏时间
	        		$("#timeTd1").hide();
	        		$("#timeTd2").hide();
	        		//隐藏运行和暂停
	        		$("#excu").hide();
	        		$("#pause").hide();
		        	for(var i=0;i<jsonStr.length;i++){
		        		if(parkingIds.indexOf(jsonStr[i].parkingId)>=0){
		        			if(parkingStr.indexOf(jsonStr[i].parkingId)>=0){
			            		html += '<li><input type="checkbox" checked="checked" value="'+jsonStr[i].parkingId+'"/><span class="STYLE1">'+jsonStr[i].parkingName+'</span></li>';
			        		}else{
		            			html += '<li><input type="checkbox" value="'+jsonStr[i].parkingId+'"/><span class="STYLE1">'+jsonStr[i].parkingName+'</span></li>';
			        		}
		        		}
		        	}
		        }else{
		        	for(var i=0;i<jsonStr.length;i++){ 
	        			if(parkingStr.indexOf(jsonStr[i].parkingId)>=0){
		            		html += '<li><input type="checkbox" checked="checked" value="'+jsonStr[i].parkingId+'"/><span class="STYLE1">'+jsonStr[i].parkingName+'</span></li>';

		        		}else{
	            			html += '<li><input type="checkbox" value="'+jsonStr[i].parkingId+'"/><span class="STYLE1">'+jsonStr[i].parkingName+'</span></li>';
		        			
		        		}
	        	}
		        }
            
	        	$("#parkingScope").html(html);	
	        },
	    });
	}
	$(function(){
		choseParkings();
	})
	function pk(){
		var boxes = $("#parkingScope input:checkbox:checked");
		var str = "";
		if(boxes.length>0){
			if($(boxes[0]).val()!=""){
				for(var i=0;i<boxes.length;i++){
					str+=$(boxes[i]).val();
					str+=",";
				}
			}
			
		}else{
			alert("请选择车场");
			return;
		}
		str = str.substring(0,str.length-1);
		$("#parkingStr").val(str);
	}
	function chall(){
		if($("#indch").is(':checked')){
			console.log($("#indch").siblings());
			$("#parkingScope input").prop('checked', true);
	
		}else{
			$("#parkingScope input").prop('checked', false);
		}
	}
    </script>

</head>

<body>
<input id="getParkingStr" value="${parkingStr}" style="display:none"/>
<!-- 用户绑定的车场 -->
<input id="userParkingId" value="${user.parkingId}" style="display:none" />
<div class="head"></div>
<form id="update_form" action="<%=basePath%>system/whitesyn/list.html" method="post">
    <table class="table_white" width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr class="head_title">
                                    <td style="width:10%"><div align="center"><span class="STYLE1">任务名称</span></div></td>
                                    <td style="width:10%"><div align="center"><span class="STYLE1">任务分组</span></div></td>
                                    <td style="width:10%"><div align="center"><span class="STYLE1">任务状态</span></div></td>
                                    <td style="width:10%"><div align="center"><span class="STYLE1">任务描述</span></div></td>
                                    <td style="width:20%" id="timeTd1"><div align="center"><span class="STYLE1">任务间隔运行时间</span></div></td>
									<td style="width:20%"><div align="center"><span class="STYLE1">车场范围</span></div></td>
                                    <security:authorize access="hasAnyRole('AUTH_EDIT_0100')">
                                    <td style="width:20%><div lign="center"><span class="STYLE1">操作</span></div></td>
                                    </security:authorize>
                                </tr>
                                    <c:if test="${jobList ne null}">
							        <c:forEach items="${jobList}" var="job">
								<tr class="table_content">
								    <td><div align="center"><span class="STYLE1">${job.jobName}</span></div> <input type="hidden" id="job_name" name="jobName" value="${job.jobName}"></td>
								    <td><div align="center"><span class="STYLE1">${job.jobGroup}</span></div> <input type="hidden" id="job_group" name="jobGroup" value="${job.jobGroup}"></td>
								    <td><div align="center"><span class="STYLE1">${job.jobStatusDesc}</span></div>
								    	<input type="hidden" id="trigger_name" name="triggerName" value="${job.triggerName}">
								    	<input type="hidden" id="trigger_group" name="triggerGroup" value="${job.triggerGroup}">
								    	<input type="hidden" id="old_cron_expression_id" name="oldCronExpressionId" value="${job.cronExpressionId}">
								    </td>
								    <td><div align="center"><span class="STYLE1">${job.desc}</span></div></td>
								    <td id="timeTd2"><div align="center"><span class="STYLE1"><select id="cron_expression" name="cronExpression" class="ui_select01">
								    	<option <c:if test="${job.cronExpressionId == '5' }">selected='selected'</c:if> value="5">5</option>
								    	<option <c:if test="${job.cronExpressionId == '10' }">selected='selected'</c:if> value="10">10</option>
								    	<option <c:if test="${job.cronExpressionId == '15' }">selected='selected'</c:if> value="15">15</option>
								    	<option <c:if test="${job.cronExpressionId == '20' }">selected='selected'</c:if> value="20">20</option>
								    	<option <c:if test="${job.cronExpressionId == '25' }">selected='selected'</c:if> value="25">25</option>
								    	<option <c:if test="${job.cronExpressionId == '30' }">selected='selected'</c:if> value="30">30</option>
								    </select>分</span></div></td>
								    <td><!-- <div align="center"><input type="button" onclick="choseParkings()" value="选择车场"/></div> -->
								        <div id="parkingScope" style="width:300px;height:100px;OVERFLOW-Y: auto; OVERFLOW-X:hidden;"><ul></ul></div>
								    </td>
								    <input id="parkingStr" style="display:none" name="parkingStr" onclick="pk()"/>
								    <security:authorize access="hasAnyRole('AUTH_EDIT_0100')">
								    <td>
								    	<!-- <input type="button" class="ui_input_btn01" onclick="update()" value="保存"> -->
								    	<!-- <input type="button" class="ui_input_btn01" onclick="triggerJob()" value="执行"> -->
								    	<c:if test="${job.jobStatus == '1'}">
								    		<input id="excu" type="button" class="ui_input_btn01" onclick="update()" value="保存"/>
								    		<input id="pause" type="button" class="ui_input_btn01" onclick="pauseJob()" value="暂停"/>
								    		<input type="button" class="ui_input_btn01" onclick="triggerJob()" value="立即运行1次"/>
								    	</c:if>

								    	<c:if test="${job.jobStatus == '2'}">
								    		<input type="button" class="ui_input_btn01" onclick="resumeJob()" value="运行">
								    	</c:if>

								    	<c:if test="${job.jobStatus == '5'}">
								    		<input type="button" class="ui_input_btn01" onclick="query()" value="刷新">
								    	</c:if>
								    </td>
								    </security:authorize>
								</tr>
								</c:forEach>
								</c:if>
								<c:if test="${empty jobList}">
								<tr>
									<td colspan="14">暂无数据</td>
								</tr>
								</c:if>
	</table>
</form>
<div id="bkhide" style="display:none;position:absolute;top:0;left:0;background:black;opacity:0.3;width:100%;height:100%">
</div>
</body>
</html>
