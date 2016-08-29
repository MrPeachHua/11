package cn.b2m.eucp.client;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.system.po.Sms;
import com.boxiang.share.system.po.ValidMessage;
import com.boxiang.share.system.service.SmsService;
import com.boxiang.share.system.service.impl.SmsServiceImpl;
import com.boxiang.share.utils.ApplicationContextUtil;
import com.boxiang.share.utils.DateUtil;

public class SingletonClient {
	
	
	
	private static final Logger logger = Logger.getLogger(SingletonClient.class);
	private static Client client=null;
	private static SingletonClient instance = new SingletonClient();
	
	private SingletonClient(){
	}
	public static SingletonClient getInstance(){
		return instance;
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	/***
	 *  发送短信
	 * @param phoneNumbers 手机号
	 * @param content 发送内容
	 * @param priority 优先级
	 * @return 返回码
	 */
	public synchronized int sendMessage(String[] phoneNumbers,String content,int priority) {
		int i = -3;
		try {
			i= SingletonClient.getClient(Constants.SMS_SERIAL_NO,Constants.SMS_KEY).sendSMS(phoneNumbers, content, "",priority);// 带扩展码
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SmsService smsService = ApplicationContextUtil.getBean("sms", SmsServiceImpl.class);
			for(int j=0 ; j<phoneNumbers.length ;j++){
				Sms sms = new Sms();
				sms.setAddTime(DateUtil.date2str(new Date(), DateUtil.DATETIME_FORMAT));
				sms.setContent(content+"~result="+i);
				sms.setMobile(phoneNumbers[j]);
				try {
					smsService.add(sms);
				} catch (Exception e) {
					logger.error("插入短信异常", e);
				}
			}			
		}
		return i;
	}

	
	/**
	 * 正确换回码
	 * */
	public static String SUCCESSCODE="000000";
	
	/**
	 * 异常返回码
	 * */
	public static String FAILCODE="111111";
	
	
	/**
	 * 存放短信验证码信息
	 * */
	public static ConcurrentHashMap<String, ValidMessage> valideCode = new ConcurrentHashMap<String, ValidMessage>(); 
	
	/**
	 * 短信验证有效时常
	 * */
	public static long MESSAGEVALIDTIME=1000*60*15;
		
	/**
	 * 生成手机验证码
	 * */
	private String createCode(){
		int code =  NextInt(1000, 9999);
		return code+"";
	}
	/**
	 * 生成指定位数的随机数
	 * */
	public int NextInt(final int min, final int max){
		Random rand;
		rand= new Random(); 
	    int tmp = Math.abs(rand.nextInt());
	    return tmp % (max - min + 1) + min;
	}
	
	/***
	 * 发送手机验证码
	 * @param mobile
	 * @return
	 */
	public String sendMessageCode(String mobile){
		ValidMessage validMessage = valideCode.get(mobile);
		if (validMessage == null) {
			logger.info("mobile :" + mobile + "  start send validate message ");
			validMessage = new ValidMessage();
			validMessage.setCode(createCode());
			validMessage.setTime(new Date().getTime() + MESSAGEVALIDTIME);//计算此验证码的失效时间
			//缓存服务器
			logger.info(mobile + " and " + validMessage + " start saving!");
			valideCode.put(mobile, validMessage);
			logger.info(valideCode.get(mobile).getCode() + " have been saved!");
		}
		String code = validMessage.getCode();

		//发送短信：
		String messageContent = "【口袋停】" + code + "，为您的手机验证码， 请在15分钟内输入验证码，15分钟后验证码失效";
		String str = null;
		try {
			int returnCode = sendMessage(new String[]{mobile}, messageContent, 5);
			logger.info("mobile: " + mobile + " finish send message code ,return code :" + returnCode);
			if (returnCode == 0) {
				str = "0";
				//str = ShangAnMessageType.toShangAnJson(SUCCESSCODE, "验证码已经发送", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT), "1");
			} else {
				str = "1";
				//str = ShangAnMessageType.toShangAnJson(SUCCESSCODE, "验证码发送失败", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT), "{}");
			}
		} catch (Exception e) {
			logger.info("mobile: " + mobile + " send message code fail Error message: " + e.getMessage());
			str = "1";
			//str = ShangAnMessageType.toShangAnJson(FAILCODE, "系统异常", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT),"{}");
		}
		return str;
	}
	
	/**
	 * 验证短信验证码
	 * */
	public static int validMessageCode(String mobile,String code){
		ValidMessage validMessage =  valideCode.get(mobile);
		Date date = new Date();
		logger.info("the mobile is "+mobile);
		if(validMessage!=null){
			logger.info(validMessage.getCode()+" is exist");
		}
		if(validMessage!=null && (validMessage.getCode().equals(code))) {
			//比较时间
			if(date.getTime()>validMessage.getTime()) {
				//短信验证码失效：
				//str = ShangAnMessageType.toShangAnJson(SUCCESSCODE, "验证码失效", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT), "{}");
				valideCode.remove(mobile);
				return 2;
			}else {
				//str = ShangAnMessageType.toShangAnJson(SUCCESSCODE, "验证成功", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT), "1");
				valideCode.remove(mobile);
				return 1;
			}
			
		} else {
			logger.info("the phone not found");
			//str = ShangAnMessageType.toShangAnJson(SUCCESSCODE, "验证失效", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT), "{}");
			return 3;
		}
	}
	
	
	
	public static void main(String[] args) {
		String[] numbers = new String[]{"13917045017","13482301239"};
		String st = "【口袋停】亲爱的小伙伴，恭喜你获得了口袋停提供的价值20元停车代金券一份。兑换码为56"+SingletonClient.getInstance().createCode();
		// 推送优惠码测试
		System.out.println(SingletonClient.getInstance().sendMessage(numbers, st, 5));
		// 推送验证码测试
		System.out.println(SingletonClient.getInstance().sendMessageCode(numbers[0]));
		// 验证短信验证码测试
		//System.out.println(SingletonClient.getInstance().validMessageCode(numbers[0], code));
	}	
	
}
