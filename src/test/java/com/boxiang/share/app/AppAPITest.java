package com.boxiang.share.app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.boxiang.share.utils.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AppAPITest {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static final String URL = "http://139.196.24.16/share/app/";

    @Test
    public void testAuthenticate (){
        String result =null;

        String url ="https://developer.api.bimbox.cn/authentication/v1/authenticate";//请求接口地址
        Map<String, Object> params = new LinkedHashMap<String, Object>();//请求参数
        params.put("client_id","4de26805-9e54-4bfb-aa36-32770d8c079f");
        params.put("client_secret","6adb615a-3b9c-4e0b-8004-f2af7199642e");
 
        try {
            result =net(url, params, "POST");
            System.out.println(result);
            JSONObject object = JSONObject.fromObject(result);
            System.out.println(object.get("errorNum")+":"+object.get("errorInfo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testEventpageList(){
        String result =null;
        String url ="carlife/eventpage/list";//请求接口地址
        Map<String, Object> params = new HashMap<String, Object>();//请求参数
        params.put("summary",MD5Util.sign("", null,DEF_CHATSET));
 
        try {
            result =net(URL+url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            JSONArray jsonR = JSONArray.fromObject(object.get("eventPageList")); 
            int size = jsonR.size();  
            for (int i = 0; i < size; i++) {  
                System.out.println(jsonR.getJSONObject(i).get("title"));  
            } 
            System.out.println(object.get("errorNum")+":"+object.get("errorInfo"));            
            //System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testNewtag(){
        String result =null;
        String url ="carlife/srvinfo/newtag";//请求接口地址
        Map<String, Object> params = new LinkedHashMap<String, Object>();//请求参数
        params.put("mobile","15073720748");
        params.put("srvId","1");
        params.put("summary",MD5Util.sign("150737207481", null,DEF_CHATSET));
 
        try {
            result =net(URL+url, params, "GET");
            System.out.println(result);
            JSONObject object = JSONObject.fromObject(result);
            System.out.println(object.get("errorNum")+":"+object.get("errorInfo"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testIntro(){
        String result =null;
        String url ="carlife/srvinfo/intro";//请求接口地址
        Map<String, Object> params = new LinkedHashMap<String, Object>();//请求参数
        params.put("parkingId","bflhygzld20151221000005");
        params.put("srvId","3");
        params.put("summary",MD5Util.sign("bflhygzld201512210000053", null,DEF_CHATSET));
 
        try {
            //result =net(URL+url, params, "GET");
            result ="{\"errorNum\":\"0\",\"errorInfo\":\"sucess\",\"srvInfo\":{\"srvName\":\"洗车\",\"srvId\":3,\"srvBilling\":[\"轿车 服务费0元 服务价格0元\",\"商务车 服务费10元 服务价格111元\"],\"intro\":\"钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱\"}}";
            System.out.println(result);
            JSONObject object = JSONObject.fromObject(result);
            System.out.println(object.get("errorNum")+":"+object.get("errorInfo"));
            
            JSONArray jsonR = JSONArray.fromObject(JSONObject.fromObject(object.get("srvInfo")).get("srvBilling"));
            int size = jsonR.size();
            for (int i = 0; i < size; i++) {
                System.out.println(jsonR.get(i));
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 
   /* public void testa(){
    	String a="{\"errorNum\":\"0\",\"errorInfo\":\"sucess\",\"srvInfo\":{\"srvName\":\"洗车\",\"srvId\":3,\"srvBilling\":[\"轿车 服务费0元 服务价格0元\",\"商务车 服务费10元 服务价格111元\"],\"intro\":\"钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱钱\"}}";
    	JSONObject object = JSONObject.fromObject(a);
    	
    }*/
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map<String, Object> params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"/"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                    out.writeBytes(urlencode(params));
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
    //将map型转为请求参数型
    public static String urlencode(Map<String, ?> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> i : data.entrySet()) {
            try {
                sb.append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("/");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
