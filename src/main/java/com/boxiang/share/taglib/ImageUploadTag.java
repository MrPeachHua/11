package com.boxiang.share.taglib;

import javax.servlet.http.HttpServletRequest;

import com.boxiang.framework.taglib.BaseTag;

public class ImageUploadTag extends BaseTag {

	private static final long serialVersionUID = 1L;
	
	/** 图片对应的标签名 */
	private String imageLabelName;
	
	/** 图片已有路径 */
	private String imagePath;

	/** 图片上传完成后设置图片路径的元素的id，一般为隐藏域对应的id */
	private String imagePathId;
	
	/**图片上传后只在的路径*/
	private String savePath;

	@Override
	public String setTagPagePath() {
		return "/common/tag/imageUploadTag.jsp";
	}

	@Override
	public void handlerStartTag() {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		//设置标签参数到页面
		request.setAttribute("imagePathId", imagePathId);
		request.setAttribute("imageLabelName", imageLabelName);
		request.setAttribute("imagePath", imagePath);
		request.setAttribute("savePath", savePath);
	}

	public String getImagePathId() {
		return imagePathId;
	}

	public void setImagePathId(String imagePathId) {
		this.imagePathId = imagePathId;
	}

	public String getImageLabelName() {
		return imageLabelName;
	}

	public void setImageLabelName(String imageLabelName) {
		this.imageLabelName = imageLabelName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
