package com.boxiang.share.taglib.controller;

import java.io.*;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.utils.ImageProcess;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.taglib.service.ImageUploadService;
import com.boxiang.share.utils.DateUtil;

@Controller
@RequestMapping("uploadImage")
public class ImageUploadController extends BaseController {
	@Resource
	private ImageUploadService imageUploadService;

	@Resource
	private String uploadImageType;

	@Resource
	private String uploadImagePath;

	private static final Logger logger = Logger.getLogger(ImageUploadController.class);

	@RequestMapping(value="up.html",method=RequestMethod.POST)
	@ResponseBody 
	public String up(@RequestParam("savePath") String savePath, @RequestParam(value = "upload", required = false) MultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
		//可以在上传文件的同时接收其它参数
		/*logger.info("收到[" + savePath + "]文件上传路径");
		if(upload.isEmpty()){
			
		}
		FileOutputStream os = null;FileInputStream in = null;
		Map<String,String> map = new HashMap<String,String>();
		try {  
			//设置响应给前台内容的数据格式
			response.setContentType("text/plain; charset=UTF-8");
			//设置响应给前台内容的PrintWriter对象
			PrintWriter out = response.getWriter();
			String previewPath = uploadImagePath + new Date().getTime() + upload.getOriginalFilename();
            //拿到输出流，同时重命名上传的文件  
            os = new FileOutputStream(uploadImagePath + new Date().getTime() + upload.getOriginalFilename());  
            //拿到上传文件的输入流  
            in = (FileInputStream) upload.getInputStream();  
              
            //以写字节的方式写文件  
            int b = 0;  
            while((b=in.read()) != -1){  
                os.write(b);  
            }  
            os.flush();        

    		//PrintWriter out = response.getWriter();
    		//response.setContentType("text/html;charset=UTF-8");
    		map.put("previewPath", previewPath);
    		map.put("result", "success");
    		//out.print(JacksonUtil.toJson(map));
			//out.close();
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("上传出错");  
        } finally{
        	if(os!=null)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        }*/
		//return JacksonUtil.toJson(map);
		return null;
	} 

	public String clean() {
		logger.debug("clean image start.......");
		try {
			//imageUploadService.delete(savePath.replace(Constants.IMAGE_PATH, ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	
	@RequestMapping("upload.html")
	public ModelAndView ajaxTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("fileupload");
		return mv;
	}
    /**
     * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用<input type="file" name="myfiles"/>
     * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
     */
    @RequestMapping(value="/fileUpload.html")
    public String fileUpload(@RequestParam("savePath") String savePath, @RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) {
		try {
			//设置响应给前台内容的数据格式
			response.setContentType("text/plain; charset=UTF-8");
			//设置响应给前台内容的PrintWriter对象
			PrintWriter out = response.getWriter();
			//上传文件的原名(即上传前的文件名字)
			String originalFilename = null;
			//如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
			//如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
			//上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
	        for(MultipartFile myfile : myfiles){
	            if(myfile.isEmpty()){
	                out.print("0:请选择文件后上传");
	                out.flush();
	                return null;
	            }else{
	                originalFilename = new Date().getTime() + myfile.getOriginalFilename();
	                logger.debug("文件原名: " + originalFilename);
	                logger.debug("文件名称: " + myfile.getName());
	                logger.debug("文件长度: " + myfile.getSize());
	                logger.debug("文件类型: " + myfile.getContentType());
					// 图片大小限制,在js,和Controller中都做了限制,要修改的时候请一并修改掉
					if (myfile.getSize() > 524288) {
						out.print("图片大小请不要超过512K");
						out.flush();
						return null;
					}
	                try {
	                	//这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
	                    //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
	                	System.out.println(System.getProperty("user.dir")+"========================");
	                	String mulu = savePath + DateUtil.getCurrDate(DateUtil.DATE_FORMAT) + "/";
						String path;
	                	if(Constants.TRUE.equals(uploadImageType)){
	                		path="/usr/local/nginx/html/" + uploadImagePath +mulu;
	    	                logger.debug("2========================================"+path+originalFilename);
	                		FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(path, originalFilename));
	                	}else {
	                		path=request.getSession().getServletContext().getRealPath("/")+ (uploadImagePath + mulu);
	    	                logger.debug("3========================================"+path+originalFilename);
	                		FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(path, originalFilename));
	                	}
	                	String realPath = uploadImagePath + mulu + originalFilename;
						// 小区车场的图片需要进行压缩
						if (savePath.equals("product/min/")) {
							String minImageName = "min_" + originalFilename;

							InputStream io = new FileInputStream(path + originalFilename);
							ImageProcess imageProcess = new ImageProcess(io, originalFilename);
							byte[] b = imageProcess.resizeByWidth(500);
							OutputStream os = new FileOutputStream(path + minImageName);
							os.write(b);

							realPath = uploadImagePath + mulu + minImageName;
						}
						logger.debug("4========================================"+realPath);
	        	        out.print("1:" + realPath);
	        	        out.flush();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    logger.error("文件[" + originalFilename + "]上传失败,堆栈轨迹如下",e);
	                    out.print("0:文件上传失败，请重试！！");
	                    out.flush();
	                    return null;
	                }
	            }
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("",e1);
		}
        return null;
    }	
}
