package com.boxiang.share.utils.jpush;

import java.util.HashMap;
import java.util.Map;

import com.boxiang.share.utils.ypush.PushClient;
import com.boxiang.share.utils.ypush.android.AndroidCustomizedcast;
import org.apache.log4j.Logger;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.utils.DateUtil;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author junior.pan
 *         极光推送工具类
 */
public enum JPush {
    CUSTERMER("客户端", "1"), PARKER("代泊员端", "2");

    private static final Logger logger = Logger.getLogger(JPush.class);
    private String name;
    private String index;
    //代泊员端
    private static final String masterSecret = "013caf6cee00b918912694f5";
    //代泊员端
    private static final String appKey = "dc9b5a097946617e57bdae7e";
    //客户端
    private static final String masterSecretCus = "e27202ec6645b07c0b611445";
    //客户端
    private static final String appKeyCus = "68d3b6ab608d1635d484267a";
    //友盟 app
    private static final String appkey = "5784927c67e58e195e001b10";
    //
    private static final String appMasterSecret = "j1ze8rp61w2jmeyhzwiwet3ivhvlotgk";

    private PushClient client = new PushClient();
    private JPush(String name, String index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return this.index + "_" + this.name;
    }

    /**
     * 通知代泊员接单
     *
     * @param message
     * @param parkerId
     * @param extraValue
     */
    private void sendPushToParker(String message, String parkerId, String extraValue) {
        Map<String, String> extra = new HashMap<>(1);
        extra.put("key", extraValue);
        PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.android())
                .setAudience(Audience.alias(parkerId)).setNotification(Notification.android(message, null, extra)).build();
        JPushClient jpushClient = getClient();
        try {
            PushResult result = jpushClient.sendPush(pushPayload);
            //sendAndroidCustomizedcast(parkerId,message,extraValue);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            logger.error("Should review the error, and fix the request", e);
            logger.error("HTTP Status: " + e.getStatus());
            logger.error("Error Code: " + e.getErrorCode());
            logger.error("Error Message: " + e.getErrorMessage());
        } catch (Exception e) {
            logger.info("-------");
            e.printStackTrace();
        }
    }

    public void sendPushToParkerWithStartPark(String message, String parkerId) {
        this.sendPushToParker(message, parkerId, "daibo");
    }

    public void sendPushToParkerWithGetCar(String message, String parkerId) {
        this.sendPushToParker(message, parkerId, "quche");
    }
    public void sendPushToParkerOther(String message, String parkerId) {
        this.sendPushToParker(message, parkerId, "other");
    }
    /**
     * 推送消息
     *
     * @param alert
     * @param alias
     */
    public void sendPushByAlias(String alert, String alias) {
        JPushClient jpushClient = getClient();
        PushPayload payload = buildPushObject_android_and_ios_alias(alert, alias);
        try {
            logger.info("sendPushByAlias - alert:" + alert + " - alias:" + alias);
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            logger.error("Should review the error, and fix the request", e);
            logger.error("HTTP Status: " + e.getStatus());
            logger.error("Error Code: " + e.getErrorCode());
            logger.error("Error Message: " + e.getErrorMessage());
        }
    }
    public void sendPushByAlias(String alert, String title, String alias) {
        JPushClient jpushClient = getClient();
        PushPayload payload = buildPushObject_android_and_ios_alias(alert, title, alias);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            logger.error("Should review the error, and fix the request", e);
            logger.error("HTTP Status: " + e.getStatus());
            logger.error("Error Code: " + e.getErrorCode());
            logger.error("Error Message: " + e.getErrorMessage());
        }
    }
    public void sendPushByAlias(String alert, String title, String extraKey, String extraValue, String alias) {
        JPushClient jpushClient = getClient();
        PushPayload payload = buildPushObject_android_and_ios_alias(alert, title, extraKey, extraValue, alias);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            logger.error("Should review the error, and fix the request", e);
            logger.error("HTTP Status: " + e.getStatus());
            logger.error("Error Code: " + e.getErrorCode());
            logger.error("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 推送消息
     *
     * @param alert
     * @param tags
     */
    public void sendPushByTags(String alert, String tags) {
        JPushClient jpushClient = getClient();
        PushPayload payload = buildPushObject_all_tags_alert(alert, tags);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            logger.error("Should review the error, and fix the request", e);
            logger.error("HTTP Status: " + e.getStatus());
            logger.error("Error Code: " + e.getErrorCode());
            logger.error("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 向所有设备推送消息
     *
     * @param alert
     */
    public void sendPushAll(String alert) {
        JPushClient jpushClient = getClient();
        PushPayload payload = buildPushObject_all_all_alert(alert);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
        } catch (APIConnectionException e) {
            logger.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            logger.error("Should review the error, and fix the request", e);
            logger.error("HTTP Status: " + e.getStatus());
            logger.error("Error Code: " + e.getErrorCode());
            logger.error("Error Message: " + e.getErrorMessage());
        }
    }

    private JPushClient getClient() {
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(5);
		config.setConnectionTimeout(10 * 1000);	// 10 seconds
		config.setSSLVersion("TLSv1.1");		// JPush server supports SSLv3, TLSv1, TLSv1.1, TLSv1.2
        logger.info("JPushClient getClient()------------------------------"+ 
        		JPushConstants.profileId+":"+Constants.PROFILE_ID_PROD.equalsIgnoreCase(JPushConstants.profileId));
        config.setApnsProduction(Constants.PROFILE_ID_PROD.equalsIgnoreCase(JPushConstants.profileId));
        if ("1".equals(index)) {
            return new JPushClient(masterSecretCus, appKeyCus, null, config);
        } else if ("2".equals(index)) {
            return new JPushClient(masterSecret, appKey, null, config);
        } else {
            return null;
        }
    }

    /**
     * 构建推送对象：所有平台，推送目标是设备别名为 "alias"，通知内容为 ALERT
     *
     * @return
     */
    @SuppressWarnings("unused")
	private PushPayload buildPushObject_all_alias_alert(String alert, String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }

    /**
     * 构建推送对象：所有平台，推送目标是设备标签为 "tags"，通知内容为 ALERT
     *
     * @return
     */
    private PushPayload buildPushObject_all_tags_alert(String alert, String tags) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag(tags))
                .setNotification(Notification.alert(alert))
                .build();
    }

    /**
     * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
     *
     * @return
     */
    private PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }
    
    public PushPayload buildPushObject_android_and_ios_alias(String alert, String title, String extraKey, String extraValue, String alias) {    	
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                		.setAlert(alert)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle(title).build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra(extraKey, extraValue).build())
                		.build())
                .build();
    }
    
    public PushPayload buildPushObject_android_and_ios_alias(String alert, String title, String alias) {    	
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                		.setAlert(alert)
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle(title).build())
                		.build())
                .build();
    }
    
    public PushPayload buildPushObject_android_and_ios_alias(String alert, String alias) {    	
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                		.setAlert(alert)
                		.build())
                .build();
    }

	public static void main(String[] args) {
		//JPush.CUSTERMER.sendPushByAlias(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "感谢您使用口袋停此次代泊业务...",
		//		"P2015120100000001");
       // new JPush(null, null).sendAndroidCustomizedcast("2016051200000029", "推送友盟", "daibo");
		 //JPush.PARKER.sendPushToParkerWithStartPark("TUIJJJKLJK", "2016051100000026");
		// JPush.PARKER.sendPushToParkerWithStartPark("推推推推推",
		// "2016051100000026");
		// JPush.PARKER.sendPushToParkerWithGetCar("倒倒倒倒倒", "2016051100000026");

        String content1 = "请尽快更新社群二维码提醒";
        JPush.PARKER.sendPushToParkerOther(content1, "2016051100000026");
	}

    public void sendAndroidCustomizedcast(String parkerId,String content,String type) throws Exception {
        String content1 = "请尽快更新社群二维码提醒";
        JPush.CUSTERMER.sendPushByAlias(content1, "2016051100000026");
        /*logger.info("-----------------友盟推送");
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
        customizedcast.setAlias(parkerId, "umeng_alias_p_share_helper");
        customizedcast.setTicker(content);
        customizedcast.setTitle("口袋停");
        customizedcast.setText(content);
        customizedcast.setExtraField("p_share_helper", type);
        logger.info("-----------友盟1");
        customizedcast.goAppAfterOpen();
        logger.info("-----------友盟2");
        customizedcast.setDisplayType(com.boxiang.share.utils.ypush.AndroidNotification.DisplayType.NOTIFICATION);
        logger.info("-----------友盟3");
        customizedcast.setProductionMode();
        client.send(customizedcast);*/
    }

}
