package com.boxiang.share.blue;

import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 这个拦截器是专门用来处理参数为JSON的
 */
public class SignInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = Logger.getLogger(SignInterceptor.class);

    public static final String PARAMS = "PARAMS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印url日志
        String jsonParams = JsonMapper.getJsonString(request);
        if (jsonParams == null || jsonParams.trim().length() == 0) {
            response.getWriter().print(ShangAnMessageType.operateToJson("2", "没有参数"));
            return false;
        }
        log.info(request.getRequestURL().toString() + "?" + jsonParams);

        Map<String, String> params = (Map<String, String>) JsonMapper.fromJson(jsonParams, Map.class);
        String sign = params.get("sign");
        List<String> keys = new ArrayList(params.keySet());
        keys.remove("sign");
        Collections.sort(keys);
        StringBuilder paramStr = new StringBuilder();
        for (String k : keys) {
            paramStr.append(k).append(params.get(k));
        }
        String localSign = DigestUtils.md5Hex(paramStr.toString().getBytes("UTF-8"));
        if (!localSign.equalsIgnoreCase(sign)) {
            response.getWriter().print(ShangAnMessageType.operateToJson("2", "验证不通过"));
            return false;
        }
        request.setAttribute(PARAMS, params);
        return super.preHandle(request, response, handler);
    }

}
