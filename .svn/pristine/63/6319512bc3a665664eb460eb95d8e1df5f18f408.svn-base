package com.boxiang.framework.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.boxiang.share.system.po.SysUsers;
import com.boxiang.share.user.po.Menu;

public class MyUsers extends User {
	private static final long serialVersionUID = 1L;
	
	Map<Menu,List<Menu>> menuMap;
	
	public Map<Menu, List<Menu>> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<Menu, List<Menu>> menuMap) {
		this.menuMap = menuMap;
	}

	private SysUsers sysUsers;

	public SysUsers getSysUsers() {
		return sysUsers;
	}

	public MyUsers(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,SysUsers sysUsers) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.sysUsers = sysUsers;
	}

}
