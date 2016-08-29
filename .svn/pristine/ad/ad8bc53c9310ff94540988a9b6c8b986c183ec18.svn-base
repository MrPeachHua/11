package com.boxiang.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.boxiang.share.system.service.SysAuthoritiesService;
import com.boxiang.share.system.service.SysResourcesService;
import com.boxiang.share.system.service.impl.SysAuthoritiesServiceImpl;
import com.boxiang.share.system.service.impl.SysResourcesServiceImpl;
import com.boxiang.share.utils.ApplicationContextUtil;
/** 
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。  
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。  
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，  
 * 供Spring Security使用，用于权限校验。 
 */
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	protected static Logger logger = Logger.getLogger(MyInvocationSecurityMetadataSourceService.class);
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MyInvocationSecurityMetadataSourceService() {
		logger.info("start......");
		loadResourceDefine();
	}
	public void refresh(){
		logger.info("refresh......");
		loadResourceDefine();
	}
	
	/**
	 * 加载所有资源与权限的关系 
	 */
	private void loadResourceDefine() {
		/**
		 * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>(); 
		SysAuthoritiesService sysAuthoritiesService = ApplicationContextUtil.getBean("sysAuthoritiesService", SysAuthoritiesServiceImpl.class); 
		SysResourcesService sysResourcesService = ApplicationContextUtil.getBean("sysResourcesService", SysResourcesServiceImpl.class);
		
		// 在Web服务器启动时，提取系统中的所有权限。
		List<String> query = sysAuthoritiesService.queryAllAuthName();
		for (String authName : query) {
			ConfigAttribute ca = new SecurityConfig(authName);
			List<String> query1 = sysResourcesService.queryUrlByAuthName(authName);
			for (String url : query1) {
				/**
				 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，
				 * 将权限增加到权限集合中。
				 */
				if (resourceMap.containsKey(url)) {
					Collection<ConfigAttribute> atts = resourceMap.get(url);
					atts.add(ca);
					resourceMap.put(url, atts);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	/**
	 * 根据URL，找到相关的权限配置。
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。		
		FilterInvocation filterInvocation = (FilterInvocation) object;
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
		    String requestURL = ite.next();
		    RequestMatcher requestMatcher = new AntPathRequestMatcher(requestURL);
		    if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
		        return resourceMap.get(requestURL);
		    }
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
