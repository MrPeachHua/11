package com.boxiang.share.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.boxiang.framework.base.Constants;

import cn.jpush.api.utils.StringUtils;

public class MD5Util {
	private static final String key = "Boxiang2016";

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
    	text = text + ((key==null)?MD5Util.key:key);
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }
    
    /**
     * 比较签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
    	text = text + ((key==null)?MD5Util.key:key);
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }     
    public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
    
    /**
	 * 获取summary加密码
	 */
	public static String getSummary(Map<String,String[]> pramMap,String getUrl){
		//getUrl 不为空  返回get加密串
		if(!StringUtils.isEmpty(getUrl)){
			String text = getUrl + ((key==null)?MD5Util.key:key);
	    	String mysign = DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));
	    	return mysign;
		}
		//String summary = null;
		StringBuffer sbValues = new StringBuffer("");
		String text = "";
		
		List<String> keyList = new ArrayList<String>(pramMap.keySet());
		Collections.sort(keyList);
		for(String name:keyList){
			//logger.info(name +"========="+ pramMap.get(name)[0]);
			if(!"summary".equalsIgnoreCase(name)){
				//summary = pramMap.get(name)[0];
			//}else {
				sbValues.append(pramMap.get(name)[0]);
			}
		}	
		text = sbValues + ((key==null)?MD5Util.key:key);
    	String mysign = DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));
    	
		return mysign;
	}
	
	public static String getLoginPwd(String pwd){
    	Md5PasswordEncoder md5 = new Md5PasswordEncoder(); 
    	md5.setEncodeHashAsBase64(false);   
    	return md5.encodePassword(pwd, MD5Util.key);   
	}
	
    public static void main(String[] args) {
    	System.out.println(MD5Util.md5("123456{Boxiang2016}"));
    	Md5PasswordEncoder md5 = new Md5PasswordEncoder(); 
    	md5.setEncodeHashAsBase64(false);   
    	String pwd = md5.encodePassword("123456", MD5Util.key);     
        System.out.println("MD5 SystemWideSaltSource: " + pwd + " len=" + pwd.length());  
        
		System.out.println("123456::"+MD5Util.sign("123456", null, Constants.CHARACTER_ENCODING_UTF8));
		System.out.println("123::"+MD5Util.sign("123", null, Constants.CHARACTER_ENCODING_UTF8));
		System.out.println("456::"+MD5Util.sign("456", null, Constants.CHARACTER_ENCODING_UTF8));
		System.out.println("3::"+MD5Util.sign("2f5fe77b3cbc46148ea3a23fe19c587f沪JC1795", null, Constants.CHARACTER_ENCODING_UTF8));
    }
}