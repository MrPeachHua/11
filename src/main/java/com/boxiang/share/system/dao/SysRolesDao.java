package com.boxiang.share.system.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysRoles;
import org.apache.ibatis.annotations.Param;

public interface SysRolesDao extends IMybatisBaseDao<SysRoles> {
    
    void batchUpdate(List<SysRoles> sysRolesList);
    List<SysRoles> queryList(@Param("roleId")String roleId,@Param("userId")String userId);
    List<SysRoles> queryListRoles(Map<String,Object> map);
    List<SysRoles> queryListRoles2(Map<String,Object> map);
    List<SysRoles> selectListRoles(String userId);
}