package com.boxiang.share.product.customer.service.imp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.boxiang.framework.base.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.customer.dao.RedDotDao;
import com.boxiang.share.product.customer.po.RedDot;
import com.boxiang.share.product.customer.service.RedDotService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("redDot")
public class RedDotServiceImpl implements RedDotService {
    @Resource(name = "redDotDao")
    private RedDotDao redDotDao;

    @Override
    public List<RedDot> selectList(RedDot redDot) {
        return redDotDao.selectList(redDot);
    }

    @Override
    public Page<RedDot> queryListPage(Page<RedDot> page) {
        page.setResultList(redDotDao.queryListPage(page));
        return page;
    }

    @Override
    public RedDot queryById(Integer id) {
        return redDotDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(RedDot redDot) {
        redDotDao.insert(redDot);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        redDotDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(RedDot redDot) {
        redDotDao.update(redDot);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<RedDot> redDotList) {
        redDotDao.batchUpdate(redDotList);
    }

    /**
     * 开启小红点
     * @param customerId 用户id
     * @param type 类型：1：优惠券
     * @param newCount 新的记录，未浏览的数量
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void openRedDot(String customerId, int type, int newCount) throws Exception {
        RedDot redDot = new RedDot();
        redDot.setIsUsed(Constants.TRUE);
        redDot.setType(type);
        redDot.setCustomerId(customerId);
        List<RedDot> list = this.selectList(redDot);
        if (list == null || list.size() == 0) { // 没有记录则新增
            redDot.setNewCount(newCount);
            redDot.setCreateor("admin");
            redDot.setCreateDate(new Date());
            this.add(redDot);
        } else { // 否则修改
            for (RedDot rd : list) {
                rd.setNewCount(rd.getNewCount() + newCount);
            }
            this.batchUpdate(list);
        }
    }

    /**
     * 关闭小红点
     * @param customerId 用户id
     * @param type 类型：1：优惠券
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void closeRedDot(String customerId, int type) throws Exception {
        RedDot redDot = new RedDot();
        redDot.setIsUsed(Constants.TRUE);
        redDot.setType(type);
        redDot.setCustomerId(customerId);
        List<RedDot> list = this.selectList(redDot);
        if (list == null || list.size() == 0) return;
        for (RedDot rd : list) {
            rd.setNewCount(0);
        }
        this.batchUpdate(list);
    }

    /**
     * 查询用户的小红点信息
     * @param customerId
     * @param type 类型：1：优惠券
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public RedDot queryRedDot(String customerId, int type) throws Exception {
        RedDot redDot = new RedDot();
        redDot.setType(type);
        redDot.setIsUsed(Constants.TRUE);
        redDot.setCustomerId(customerId);
        List<RedDot> list = this.selectList(redDot);
        if (list == null || list.size() == 0) return null;
        redDot = list.get(0);
        return redDot;
    }

}