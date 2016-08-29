package com.boxiang.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.system.po.SysUsers;
import com.boxiang.share.system.service.SysAuthoritiesService;
import com.boxiang.share.system.service.SysUsersService;
import com.boxiang.share.user.po.Menu;
import com.boxiang.share.user.service.MenuService;

public class CustomUserDetailsService implements UserDetailsService {
	protected static Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	
	/**
	 * 用户缓存
	 */
	@Resource
	private UserCache userCache;

	@Resource
	private SysAuthoritiesService sysAuthoritiesService;

	@Resource
	private SysUsersService sysUsersService;
	
	@Resource   private MenuService menuService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		MyUsers user = null;
		logger.info("loadUserByUsername start ... ..."+username);
		try {
			MyUsers user2 = (MyUsers) userCache.getUserFromCache(username);
			
			if(null != user2){
				logger.info("\n##################\nloadUserByUsername by cache ... ...\n##################");
				userCache.removeUserFromCache(username);
				Map<Menu,List<Menu>> menuMap = user2.getMenuMap();
				user = new MyUsers(username, user2.getSysUsers().getUserPassword(), true, true, true, true, user2.getAuthorities(),user2.getSysUsers());
				user.setMenuMap(menuMap);
			}else {
				logger.info("\n##################\nloadUserByUsername by database ... ...\n##################");
				List<SysUsers> sysusers = sysUsersService.queryUserRoleByAccount(username);
				for(SysUsers sysuser : sysusers){//pwd{key}
					user = new MyUsers(username, sysuser.getUserPassword(), true, true, true, true, getAuthorities(username),sysuser);
					user.setMenuMap(getMenuMap(user.getSysUsers().getSysRolesList()));
					break;
				}
			}
			userCache.putUserInCache(user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}

		return user;
	}
	public Map<Menu, List<Menu>> getMenuMap(List<SysRoles> sysRolesList){
		Map<Menu,List<Menu>> menuMap = new LinkedHashMap<Menu,List<Menu>>();
		Menu menu = new Menu();
		menu.setParentCode("10");
		StringBuffer buff = new StringBuffer("(");
		for(SysRoles role:sysRolesList){
			buff.append("'").append(role.getRoleName()).append("',");
		}
		buff.deleteCharAt(buff.length()-1).append(")");
		menu.setRolePower(buff.toString());
		List<Menu> menuList = menuService.queryByPower(menu);
		List<Menu> tmpList = null;
		for(Menu me:menuList){
			menu = new Menu();
			menu.setParentCode(me.getMenuCode());
			menu.setRolePower(buff.toString());
			tmpList = menuService.queryByPower(menu);
			if(tmpList!=null && tmpList.size()>0){
				menuMap.put(me, tmpList);
			}
		}
		return menuMap;
	}
	/**
	 * 获取用户权限
	 * @param username
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		List<String> authorities = sysAuthoritiesService.loadUserAuthoritiesByAccount(username);
		for(String auth:authorities){
			authList.add(new SimpleGrantedAuthority(auth));
		}
		return authList;
	}
}
