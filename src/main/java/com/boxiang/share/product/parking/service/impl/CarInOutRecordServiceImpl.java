package com.boxiang.share.product.parking.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.boxiang.share.product.coupon.service.CouponRuleService;
import com.boxiang.share.product.order.dao.MonthlyparkinginfoDao;
import com.boxiang.share.product.order.dao.PropertyparkinginfoDao;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import com.boxiang.share.product.order.po.Propertyparkinginfo;
import com.boxiang.share.product.order.service.MonthlyparkinginfoService;
import com.boxiang.share.product.order.service.PropertyparkinginfoService;
import com.boxiang.share.product.parking.dao.CarInOutRecordDao;
import com.boxiang.share.product.parking.dao.CarInOutRecordV2Dao;
import com.boxiang.share.product.parking.po.CarInOutRecord;
import com.boxiang.share.product.parking.po.CarInOutRecordV2;
import com.boxiang.share.product.parking.service.CarInOutRecordService;
import com.boxiang.share.system.dao.DictionaryDao;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.HttpUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.Dictionary;
@DataSource(DataSourceEnum.MASTER)
@Service("carInOutRecordService")
public class CarInOutRecordServiceImpl implements CarInOutRecordService {
	@Resource(name="carInOutRecordDao")
	private CarInOutRecordDao carInOutRecordDao;
	@Resource(name="carInOutRecordV2Dao")
	private CarInOutRecordV2Dao carInOutRecordV2Dao;
	@Resource
	private MonthlyparkinginfoDao monthlyparkinginfoDao;
	@Resource
	private PropertyparkinginfoDao propertyparkinginfoDao;
	@Override
	public String queryRecordPage(Page<CarInOutRecordV2> page) {
		String mess = null;
		List<Map<String,Object>> mapList = new ArrayList<>();
		List<CarInOutRecordV2> list = carInOutRecordV2Dao.selectCarRecordApp(page);
		if (list!=null && list.size()>0){
			for (CarInOutRecordV2 carInOutRecord : list){
				if (carInOutRecord!=null){
					Map<String,Object> paraMap = new HashMap<String,Object>();
					paraMap.put("carNumber",carInOutRecord.getPlateId());
					paraMap.put("dayTime",StringUtils.defaultString(carInOutRecord.getStayTime()));
					/*CarInOutRecord c1 = carInOutRecordDao.selectParkingName(carInOutRecord);
					if (c1!=null){
						String parkingId = c1.getParkingId();
						if (parkingId!=null && !"".equals(parkingId) && carInOutRecord.getPlateId()!=null && !"".equals(carInOutRecord.getPlateId())){
							Monthlyparkinginfo monthlyparkinginfo = new Monthlyparkinginfo();
							monthlyparkinginfo.setVillageId(parkingId);
							monthlyparkinginfo.setCarNumber(carInOutRecord.getPlateId());
							List<Monthlyparkinginfo> list1 = monthlyparkinginfoDao.selectList(monthlyparkinginfo);
							Propertyparkinginfo propertyparkinginfo = new Propertyparkinginfo();
							propertyparkinginfo.setVillageId(parkingId);
							propertyparkinginfo.setCarNumber(carInOutRecord.getPlateId());
							List<Propertyparkinginfo> list2 = propertyparkinginfoDao.selectList(propertyparkinginfo);
							if (list1!=null){
								paraMap.put("type","月租");
							}else if (list2!=null){
								paraMap.put("type","产权");
							}else {
								paraMap.put("type","临停");
							}
						}
					}*/
					paraMap.put("orderType",StringUtils.defaultString(carInOutRecord.getCarType()));
					paraMap.put("freeType",StringUtils.defaultString(carInOutRecord.getPayType()));
					paraMap.put("freeInfo",StringUtils.defaultString(carInOutRecord.getRemark()));
					mapList.add(paraMap);
				}
			}
			mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "mapList", mapList);
		}else {
			mess = ShangAnMessageType.operateToJson("1","无数据");
		}
		return mess;
	}

	@Resource
	private DictionaryService dictionaryService;
	private static final Logger logger = Logger.getLogger(CarInOutRecordServiceImpl.class);
	@Override
	public List<CarInOutRecord> selectList(CarInOutRecord carInOutRecord) {
		return carInOutRecordDao.selectList(carInOutRecord);
	}

	@Override
	public Page<CarInOutRecord> queryListPage(Page<CarInOutRecord> page) {
	    page.setResultList(carInOutRecordDao.queryListPage(page));
		return page;
	}

	@Override
	public Page<CarInOutRecord> selectCarRecord(Page<CarInOutRecord> page) {
		page.setResultList(carInOutRecordDao.selectCarRecord(page));
		return page;
	}

	@Override
	public List<CarInOutRecord> queryCarRecordList(CarInOutRecord carInOutRecord) {
		return carInOutRecordDao.queryCarRecordList(carInOutRecord);
	}

	@Override
	public CarInOutRecord queryById(Integer id) {
		return carInOutRecordDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarInOutRecord tCarInOutRecord) {
		carInOutRecordDao.insert(tCarInOutRecord);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		carInOutRecordDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarInOutRecord tCarInOutRecord) {
		carInOutRecordDao.update(tCarInOutRecord);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarInOutRecord> carInOutRecordList) {
		carInOutRecordDao.batchUpdate(carInOutRecordList);
	}


	@Override
	public CarInOutRecord selectParkingName(CarInOutRecord carInOutRecord) {
		return carInOutRecordDao.selectParkingName(carInOutRecord);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})


	/**
	 * 记录进出场车辆数据
	 */
	public synchronized void  recordInOrOutCar() throws Exception {
		String preTime = null;
		String nowTime = null;
		Calendar ca = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		nowTime = DateUtil.date2str(ca.getTime(), DateUtil.DATETIME_FORMAT);
		cal.add(Calendar.MINUTE, -1);
		//获取最后一次记录的数据
		Dictionary dicParam = new Dictionary();
		dicParam.setDictCode("car_in_out_time");
		List<Dictionary> cir = dictionaryService.selectList(dicParam);
		if(null!=cir&&cir.size()>0){
			Date lastTime = DateUtil.str2date(cir.get(0).getDictValue(), DateUtil.DATETIME_FORMAT);
			if(lastTime.getTime()<cal.getTime().getTime()){
				preTime = DateUtil.date2str(lastTime,DateUtil.DATETIME_FORMAT);
			}else{
				preTime = DateUtil.date2str(cal.getTime(),DateUtil.DATETIME_FORMAT);
			}
		}else{
			preTime = DateUtil.date2str(cal.getTime(),DateUtil.DATETIME_FORMAT);
		}
		//获取入场出场车辆数据
		Map resultMap = this.getInParkInfo(preTime,nowTime);
		List<Map<String, Object>> carInMap = null;
		List<Map<String, Object>> carOutMap = null;
		if (resultMap.get("status").equals("success")) {
			//noinspection unchecked
			Object obCarIn = resultMap.get("inparkJson");
			Object obCarOut = resultMap.get("outparkJson");
			if("null".equals(obCarIn) && "null".equals(obCarOut)){
			//无数据是记录空数据
				//CarInOutRecord co = new CarInOutRecord();
				///co.setCreateDate(new Date());
				//co.setCreateor("admin");
				//this.add(co);
				Dictionary dicParam1 = new Dictionary();
				dicParam1.setDictCode("car_in_out_time");
				List<Dictionary> dList = dictionaryService.selectList(dicParam1);
				if(null!=dList&&dList.size()>0){
					Dictionary dt = dList.get(0);
					dt.setDictValue(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
					dictionaryService.update(dt);
				}
				logger.info("--------------入场出场无数据");
				return;
			}
			carInMap =null;
			if(!"null".equals(obCarIn)){
				carInMap = (List<Map<String, Object>>) resultMap.get("inparkJson");
			}
			carOutMap =null;
			if(!"null".equals(obCarOut)) {
				carOutMap = (List<Map<String, Object>>) resultMap.get("outparkJson");
			}
			if(null!=carInMap&&carInMap.size()>0){
			for(Map<String,Object> ma :carInMap){
				//如果数据已存则不添加
				CarInOutRecord cParam = new CarInOutRecord();
				cParam.setPlateId((String) ma.get("plateId"));
				cParam.setIntime(StringUtils.isEmpty((String) ma.get("inTime"))?null: DateUtil.str2date((String) ma.get("inTime"), DateUtil.DATETIME_FORMAT));
				List<CarInOutRecord> list = carInOutRecordDao.selectList(cParam);
				if(null!=list&&list.size()>0){
					continue;
				}
				CarInOutRecord co = new CarInOutRecord();
				co.setCreateDate(new Date());
				co.setCreateor("admin");
				co.setCarType((String) ma.get("carType"));
				co.setCarType((String) ma.get("confidence"));
				co.setDiscountCharge(ma.get("discountCharge")+"");
				co.setDiscountCode((String) ma.get("discountCode"));
				co.setDiscountTime(StringUtils.isEmpty(""+ma.get("discountTime"))?null:Integer.parseInt(""+ma.get("discountTime")));
				co.setEditFlag(ma.get("editFlag")+"");
				co.setIngateId((String) ma.get("inGateId"));
				co.setInImagename((String) ma.get("inImageName"));
				co.setInLedid((String) ma.get("inLedId"));
				co.setInorout((String) ma.get("inOrOut"));
				co.setInreaderId((String) ma.get("inReaderId"));
				co.setIntime(StringUtils.isEmpty((String) ma.get("inTime"))?null: DateUtil.str2date((String) ma.get("inTime"), DateUtil.DATETIME_FORMAT));
				co.setInvideoId((String) ma.get("inVideoId"));
				co.setInvoiceCode((String) ma.get("invoiceCode"));
				co.setOrderId((String) ma.get("orderId"));
				co.setParkId((String) ma.get("parkId"));
				co.setParkSpaceNum(StringUtils.isEmpty(ma.get("parkSpaceNum")+"")?null:Integer.parseInt(ma.get("parkSpaceNum")+""));
				co.setPayCharge(StringUtils.isEmpty(ma.get("payCharge")+"")?null:Integer.parseInt(ma.get("payCharge")+""));
				co.setPlateColor((String) ma.get("plateColor"));
				co.setPlateId((String) ma.get("plateId"));
				co.setPlatek((String) ma.get("platek"));
				co.setRealCharge(StringUtils.isEmpty(""+ ma.get("realCharge"))?null:Integer.parseInt(""+ma.get("realCharge")));
				co.setRecordId((String) ma.get("recordId"));
				co.setSysWriteDate(StringUtils.isEmpty((String) ma.get("sysWriteDate")) ? null : DateUtil.str2date((String) ma.get("sysWriteDate"), DateUtil.DATETIME_FORMAT));
				co.setUserType((String) ma.get("userType"));
				this.add(co);
			}
			}
			if(null!=carOutMap&&carOutMap.size()>0){
				//添加出场的
				for(Map<String,Object> ma :carOutMap){
					//如果数据已存则不添加
					CarInOutRecord cParam = new CarInOutRecord();
					cParam.setPlateId((String) ma.get("plateId"));
					cParam.setOuttime(StringUtils.isEmpty((String) ma.get("outTime")) ? null : DateUtil.str2date((String) ma.get("outTime"), DateUtil.DATETIME_FORMAT));
					List<CarInOutRecord> list = carInOutRecordDao.selectList(cParam);
					if(null!=list&&list.size()>0){
						continue;
					}
					CarInOutRecord co = new CarInOutRecord();
					co.setCreateDate(new Date());
					co.setCreateor("admin");
					co.setCarType((String) ma.get("carType"));
					co.setCarType((String) ma.get("confidence"));
					co.setDiscountCharge(""+ma.get("discountCharge"));
					co.setDiscountCode((String) ma.get("discountCode"));
					co.setDiscountTime(StringUtils.isEmpty(""+ ma.get("discountTime")) ? null : Integer.parseInt(""+ ma.get("discountTime")));
					co.setInorout((String) ma.get("inOrOut"));
					co.setIntime(StringUtils.isEmpty((String) ma.get("inTime")) ? null : DateUtil.str2date((String) ma.get("inTime"), DateUtil.DATETIME_FORMAT));
					co.setInvoiceCode((String) ma.get("invoiceCode"));
					//co.setOfflineCharge(StringUtils.isEmpty(""+ ma.get("offLineCharge")) ? null : Integer.parseInt(""+ ma.get("offLineCharge")));
					//co.setOnlineCharge(StringUtils.isEmpty(""+ ma.get("onLineCharge")) ? null : Integer.parseInt(""+ ma.get("onLineCharge")));
					co.setOrderId((String) ma.get("orderId"));
					co.setOutgateId((String) ma.get("outGateId"));
					co.setOutImagename((String) ma.get("outImageName"));
					co.setOutledId((String) ma.get("outLedId"));
					co.setOutreaderId((String) ma.get("outReaderId"));
					co.setOuttime(StringUtils.isEmpty((String) ma.get("outTime")) ? null : DateUtil.str2date((String) ma.get("outTime"), DateUtil.DATETIME_FORMAT));
					co.setOutvideoId((String) ma.get("outVideoId"));
					co.setParkId((String) ma.get("parkId"));
					co.setParkSpaceNum(StringUtils.isEmpty(""+ ma.get("parkSpaceNum")) ? null : Integer.parseInt(""+ma.get("parkSpaceNum")));
					co.setPayCharge(StringUtils.isEmpty(""+ ma.get("payCharge")) ? null : Integer.parseInt(""+ ma.get("payCharge")));
					co.setPlateColor((String) ma.get("plateColor"));
					co.setPlateId((String) ma.get("plateId"));
					co.setPlatek((String) ma.get("platek"));
					co.setRealCharge(StringUtils.isEmpty(""+ ma.get("realCharge")) ? null : Integer.parseInt(""+ ma.get("realCharge")));
					co.setRecordId((String) ma.get("recordId"));
					co.setUserType((String) ma.get("userType"));
					this.add(co);
				}
			}
			Dictionary dicParam1 = new Dictionary();
			dicParam1.setDictCode("car_in_out_time");
			List<Dictionary> dList = dictionaryService.selectList(dicParam1);
			if(null!=dList&&dList.size()>0){
				Dictionary dt = dList.get(0);
				dt.setDictValue(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
				dictionaryService.update(dt);
			}
		}else{
			logger.info("进出场数据异常------------");
		}
	}

	/**
	 * 获取进出场记录
	 *
	 * @param beginTime 格式 yyyy-MM-dd HH:mm:ss
	 * @param endTime   格式 yyyy-MM-dd HH:mm:ss
	 */
	public Map getInParkInfo(String beginTime, String endTime) throws Exception {
		Map<String, String> params = new HashMap<>(3);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("status", "2"); // 2,返回对应所有进场记录
		String result = HttpUtil.netWithJsonParam("http://139.196.47.68/main/open/getInOrOut2", params);
		Map resultMap = (Map) JsonMapper.fromJson(result, Map.class);

		return resultMap;
	}

}