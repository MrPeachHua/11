package com.boxiang.share.system.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.dao.ParkingTicketDao;
import com.boxiang.share.system.po.ParkingTicket;
import com.boxiang.share.system.service.ParkingTicketService;

@DataSource(DataSourceEnum.MASTER)
@Service("parkingTicket")
public class ParkingTicketServiceImpl implements ParkingTicketService {
//	private static final Logger logger = Logger.getLogger(ParkingTicketServiceImpl.class);
	@Resource(name="parkingTicketDao")
	private ParkingTicketDao parkingTicketDao;	
	@Override
	public List<ParkingTicket> selectList(ParkingTicket parkingTicket) {
		return parkingTicketDao.selectList(parkingTicket);
	}

	@Override
	public Page<ParkingTicket> queryListPage(Page<ParkingTicket> page) {
	    page.setResultList(parkingTicketDao.queryListPage(page));
		return page;
	}
	
	@Override
	public ParkingTicket queryById(java.lang.String id) {
		return parkingTicketDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(ParkingTicket parkingTicket) {
		parkingTicketDao.insert(parkingTicket);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		parkingTicketDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(ParkingTicket parkingTicket) {
		parkingTicketDao.update(parkingTicket);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<ParkingTicket> parkingTicketList) {
		parkingTicketDao.batchUpdate(parkingTicketList);
	}
  /**
   * 发送停车码信息
   */
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void pushPkingTicketMessage() {
		// TODO Auto-generated method stub
	  /*ParkingTicket pa = new ParkingTicket();
		String []mobile ={"11"}; 
		String content = "发送短信";
		//生成6位随机码
		StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		//随机生成数字，并添加到字符串
		for(int i=0;i<6;i++){
			str.append(random.nextInt(10));
		}
		int reCode = SendMessageUtils.sendMessage(mobile, content, 5);
		if(reCode==0){
			logger.info("发送成功");
		}*/	
	 
  }
}