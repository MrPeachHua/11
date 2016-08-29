package com.boxiang.share.product.villageowner.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.villageowner.dao.InvitationDao;
import com.boxiang.share.product.villageowner.po.Invitation;
import com.boxiang.share.product.villageowner.service.InvitationService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("invitation")
public class InvitationServiceImpl implements InvitationService {
    @Resource(name = "invitationDao")
    private InvitationDao invitationDao;

    @Override
    public List<Invitation> selectList(Invitation invitation) {
        return invitationDao.selectList(invitation);
    }

    @Override
    public Page<Invitation> queryListPage(Page<Invitation> page) {
        page.setResultList(invitationDao.queryListPage(page));
        return page;
    }

    @Override
    public Invitation queryById(Integer id) {
        return invitationDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(Invitation invitation) {
        invitationDao.insert(invitation);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        invitationDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(Invitation invitation) {
        invitationDao.update(invitation);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<Invitation> invitationList) {
        invitationDao.batchUpdate(invitationList);
    }

    @Override
    public int queryTodayCount(String customerId) {
        return invitationDao.queryTodayCount(customerId);
    }

    @Override
    public Map<String, Object> paramsFilter(Invitation invitation) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", invitation.getId());
        map.put("customerId", invitation.getCustomerId());
        map.put("carNumber", StringUtils.defaultString(invitation.getCarNumber()));
        map.put("createDate", DateUtil.date2str(invitation.getCreateDate(), DateUtil.DATETIME_FORMAT));
        map.put("inviteDate", invitation.getInviteDate() == null ? "" : DateUtil.date2str(invitation.getInviteDate(), "YYYY.MM.dd"));
        map.put("mobile", StringUtils.defaultString(invitation.getMobile()));
        map.put("name", StringUtils.defaultString(invitation.getName()));
        map.put("parkingId", StringUtils.defaultString(invitation.getParkingId()));
        map.put("parkingName", StringUtils.defaultString(invitation.getParkingName()));
        map.put("peacetimePrice", StringUtils.defaultString(invitation.getPeacetimePrice()));
        map.put("inTime", invitation.getInTime() == null ? "" : DateUtil.date2str(invitation.getInTime(), "yyyy-MM-dd HH:mm"));
        map.put("outTime", invitation.getOutTime() == null ? "" : DateUtil.date2str(invitation.getOutTime(), "yyyy-MM-dd HH:mm"));
        return map;
    }

    @Override
    public Invitation queryUnExpire(int id) {
        return invitationDao.queryUnExpire(id);
    }

    @Override
    public List<Object> queryHistory(Object page) {
        return invitationDao.queryHistory(page);
    }
}