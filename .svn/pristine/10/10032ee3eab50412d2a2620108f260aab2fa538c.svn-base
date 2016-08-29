<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<LINK href="<%=basePath%>css/image_upload.css" type=text/css rel=stylesheet> 
<div class="content_upload">
	<ul>
		<li>
			<div class="content_upload_button">	
				<label id="label_${imagePathId}" for="upload">
				<span></span>	
				</label>
               	<b id="closeimg_${imagePathId}"              	
					  <c:choose>		
					  <c:when test="${imagePath !=null && imagePath != ''}">
			               	style="display:block"
					  </c:when>
			          <c:otherwise>
							style="display:none"
				      </c:otherwise>	
					  </c:choose>
               		onclick="cleanImage('${imagePathId}')">关闭</b>
				<img id="img_${imagePathId}"              	
					  <c:choose>		
					  <c:when test="${imagePath !=null && imagePath != ''}">
			               	src="<%=basePath%>${imagePath}"
					  </c:when>
			          <c:otherwise>
							src="<%=basePath %>/images/upload_image_default.png"
				      </c:otherwise>	
					  </c:choose>
				/>
				<input type="file" id="upload" name="myfiles" onchange="fileUpload('${imagePathId}')"/>
			</div>
            <p>${imageLabelName}</p>
		</li>
	</ul>
</div>
<script type="text/javascript">
	function fileUpload(imagePathId) {
		//1、验证图片格式
		var imagePath = $('#upload').val();
		var imageExtName = imagePath.substring(imagePath.lastIndexOf('.') + 1).toUpperCase();
		var imageExtNameReg = /^JPG|GIF|PNG$/;
		if(!imageExtNameReg.test(imageExtName))
		{
			alert('只能上传JPG,GIF,PNG格式的图片！');
			return false;
		}
		// 图片大小限制,在js,和Controller中都做了限制,要修改的时候请一并修改掉
		if (document.getElementById('upload').files[0].size > 524288) {
			alert('图片大小请不要超过512K ！');
			return false;
		}
		//2、文件上传
        $.ajaxFileUpload({ 
        	url : '<%=basePath%>uploadImage/fileUpload.html',
            secureuri : false,  
            fileElementId : 'upload',// 上传控件的id  
            data:{"savePath":'${savePath}'},
            dataType : 'text',  
            success : function(data, status) {
				if(data != null) {//alert(data.substring(data.indexOf(">")+1));
		            data = data.substring(data.indexOf(">")+1);  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
		            data = data.replace("</PRE>", '');
		            data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
		            
					if(data.substring(0, 1) == 1) {
						//显示图片
						$('#img_'+imagePathId+'').attr('src',"<%=basePath%>"+data.substring(2));

						//将上传完成后的图片路径设置到对应的HTML元素中
						$('#'+imagePathId+'').val(data.substring(2));
						$('#closeimg_'+imagePathId+'').attr('style', 'display:block');
					}
					else
						alert(data);
				}
				else
					alert('图片上传失败！');
            },  
            error : function(data, status, e) {  
                alert(data.result +'上传出错'+e);  
            }  
        }); 
  
        return false;  
  
    }  
	function cleanImage(imagePathId){
		var imagePath = $('#'+imagePathId+'').val();
		if(imagePath !=null && imagePath != ""){
            $('#img_'+imagePathId+'').attr('src', '<%=basePath %>/images/upload_image_default.png');
            //将上传完成后的图片路径设置到对应的HTML元素中
            $('#'+imagePathId+'').val("");
            $('#closeimg_'+imagePathId+'').attr('style', 'display:none');
			//图片清理
			<%-- $.ajax({
				type:'POST',
        		url : 'uploadImage/clean.do',
				dataType:'json',
            	data:{"savePath":imagePath},
				success:function(data)
				{
					if(data != null)
					{
						if(data.result == 'success')
						{
				            //显示图片
				            $('#img_'+imagePathId+'').attr('src', '<%=imagePath %>upload_image_default.png');
				            //将上传完成后的图片路径设置到对应的HTML元素中
				            $('#'+imagePathId+'').val("");
				            $('#closeimg_'+imagePathId+'').attr('style', 'display:none');
						}
						else
							alert(data.result);
					}
					else
						alert('图片清理失败！');
				}
			});	 --%>	
		}
	}
</script>