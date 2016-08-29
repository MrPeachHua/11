package com.boxiang.share.product.carlife.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.dao.CarlifeEventPageDao;
import com.boxiang.share.product.carlife.po.CarlifeEventPage;
import com.boxiang.share.product.carlife.service.CarlifeEventPageService;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("carlifeEventPage")
public class CarlifeEventPageServiceImpl implements CarlifeEventPageService {
	@Resource(name="carlifeEventPageDao")
	private CarlifeEventPageDao carlifeEventPageDao;
	
	@Override
	public List<CarlifeEventPage> selectList(CarlifeEventPage carlifeEventPage) {
		return carlifeEventPageDao.selectList(carlifeEventPage);
	}

	@Override
	public Page<CarlifeEventPage> queryListPage(Page<CarlifeEventPage> page) {
	    page.setResultList(carlifeEventPageDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarlifeEventPage queryById(java.lang.Integer id) {
		return carlifeEventPageDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarlifeEventPage carlifeEventPage) {
		carlifeEventPageDao.insert(carlifeEventPage);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		carlifeEventPageDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarlifeEventPage carlifeEventPage) {
		carlifeEventPageDao.update(carlifeEventPage);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarlifeEventPage> tCarlifeEventPageList) {
		carlifeEventPageDao.batchUpdate(tCarlifeEventPageList);
	}
  /**
   *查询车管家轮播图片
   */
  @Override
	public List<CarlifeEventPage> selectListForLoop(CarlifeEventPage carlifeEventPage) {
		return carlifeEventPageDao.selectListForLoop(carlifeEventPage);
	}
  /**
   * 车管家轮播图片接口
   */
  public String getEventPageMessage(HttpServletRequest request, HttpServletResponse response){
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    CarlifeEventPage eventPageParam = new CarlifeEventPage();
    List<CarlifeEventPage> list = null;
	String mess="";
		//查询可用的车管家轮播图片（可用）
		eventPageParam.setIsUsed(Constants.TRUE);
		try {
			list = carlifeEventPageDao.selectListForLoop(eventPageParam);
		} catch (Exception e) {
			// TODO: handle exception
			mess = ShangAnMessageType.toShangAnJson("2","erro","eventPageList","");
			return mess;
		}
		//有数据返回数据
		if(null!=list&&list.size()>0){
			List<Map<String,Object>> paraList = new ArrayList<Map<String,Object>>();
			//修改图片路径
			for(CarlifeEventPage cl :list){
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("title",(null!=cl.getTitle())?cl.getTitle():"");
				if(null!=cl.getImagePath()){
					paraMap.put("imagePath",basePath+cl.getImagePath());
				}else{
					paraMap.put("imagePath","");
				}
				paraMap.put("imageLink",(null!=cl.getImageLink())?cl.getImageLink():"");
				paraMap.put("sort",(null!=cl.getSort())?cl.getSort():"");
				paraMap.put("imageType", (null!=cl.getImageType())?cl.getImageType():"");//1为轮播图2位车管家
				paraList.add(paraMap);
			}
			mess = ShangAnMessageType.toShangAnJson("0","sucess","eventPageList",paraList);
		}else{
			mess = ShangAnMessageType.toShangAnJson("1","none","eventPageList","");
		}
		return mess;
  }
}