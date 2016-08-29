package com.boxiang.share.product.activity.service.impl;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.activity.dao.ActivityQrCodeDao;
import com.boxiang.share.product.activity.po.ActivityQrCode;
import com.boxiang.share.product.activity.service.ActivityQrCodeService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.QRCodeUtil;
import com.boxiang.share.utils.ZipUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@DataSource(DataSourceEnum.MASTER)
@Service("activityQrCode")
public class ActivityQrCodeServiceImpl implements ActivityQrCodeService {
    @Resource(name = "activityQrCodeDao")
    private ActivityQrCodeDao activityQrCodeDao;

    @Override
    public List<ActivityQrCode> selectList(ActivityQrCode activityQrCode) {
        return activityQrCodeDao.selectList(activityQrCode);
    }

    @Override
    public Page<ActivityQrCode> queryListPage(Page<ActivityQrCode> page) {
        page.setResultList(activityQrCodeDao.queryListPage(page));
        return page;
    }

    @Override
    public ActivityQrCode queryById(Integer id) {
        return activityQrCodeDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(ActivityQrCode activityQrCode) {
        activityQrCodeDao.insert(activityQrCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        activityQrCodeDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(ActivityQrCode activityQrCode) {
        activityQrCodeDao.update(activityQrCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<ActivityQrCode> activityQrCodeList) {
        activityQrCodeDao.batchUpdate(activityQrCodeList);
    }

    @Override
    public List<Map<String, Object>> queryListPageV2(Object page) {
        return activityQrCodeDao.queryListPageV2(page);
    }

    @Override
    public List<Map<String, Object>> queryByIdV2(String ids) {
        Map<String, Object> map = new HashMap<>(1);
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", ids);
        map.put("params", params);
        return activityQrCodeDao.queryListPageV2(map);
    }

    @Override
    public String getFileName(Map<String, Object> entity) {
        StringBuilder sb = new StringBuilder();
        String type = MapUtils.getString(entity, "type");
        switch (type) {
            case "1":
                sb.append(MapUtils.getString(entity, "title"));
                sb.append("_");
                sb.append(MapUtils.getString(entity, "parkingName"));
                sb.append("_");
                sb.append(MapUtils.getString(entity, "parkerName"));
                break;
            case "2":
                sb.append(MapUtils.getString(entity, "name"));
                sb.append("_");
                sb.append(MapUtils.getString(entity, "parkingName"));
                sb.append("_");
                sb.append(MapUtils.getString(entity, "parkerName"));
                break;
            case "3":
                sb.append(MapUtils.getString(entity, "title"));
                break;
            default:
                sb.append("未知类型");
                break;
        }
        sb.append("_");
        sb.append(entity.get("id"));
        sb.append(".jpg");
        return sb.toString();
    }

    @Override
    public void setBaseProperty(ActivityQrCode activityQrCode) {
        activityQrCode.setScanCount(0);
        activityQrCode.setIsUsed(Constants.TRUE);
        activityQrCode.setCreateDate(new Date());
        activityQrCode.setCreateor("admin");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public List<Integer> batchSave(String type, String activityId, String parkingId, String userId) {
        String[] pidArray = parkingId.split(",");
        String[] uidArray = userId.split(",");
        List<Integer> idList = new ArrayList<>(pidArray.length * uidArray.length);
        for (String pid : pidArray) {
            for (String uid : uidArray) {
                ActivityQrCode entity = new ActivityQrCode();
                this.setBaseProperty(entity);
                entity.setParkingId(pid);
                entity.setUserId(uid);
                entity.setType(type);
                if (type.equals("1")) {
                    entity.setTitle("微信");
                    entity.setUrl("http://p-share.cn/wx_share/html5/views/index.html#/home/payNumber");
                } else if (type.equals("2")) {
                    entity.setActivityId(Integer.parseInt(activityId));
                }
                this.add(entity);
                idList.add(entity.getId());
            }
        }
        return idList;
    }

    @Override
    public String getEncodeUrl(Map<String, Object> entity, HttpServletRequest request) {
        String type = MapUtils.getString(entity, "type");
        String jumpUrl;
        if (type.equals("2")) {
            jumpUrl = MapUtils.getString(entity, "activityUrl");
        } else {
            jumpUrl = MapUtils.getString(entity, "url");
        }
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        return (basePath + "other/scanQrCode?id=" + MapUtils.getString(entity, "id") + "&jumpUrl=" + jumpUrl);
    }

    @Override
    public String batchDownload(String ids, HttpServletRequest request) throws Exception {
        String dir = DateUtil.getCurrDate("yyyyMMddHHmmss") + (int) (Math.random() * 1000000);
        String path = "/uploadFiles/QrCode/" + dir;
        String savePath = request.getSession().getServletContext().getRealPath("/") + path;
        File directory = new File(savePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        List<Map<String, Object>> list = this.queryByIdV2(ids);
        for (Map<String, Object> entity : list) {
            String url = this.getEncodeUrl(entity, request);
            String fileName = this.getFileName(entity);
            QRCodeUtil.encode(url, savePath + "/" + fileName);
        }
        ZipUtil.zip(savePath + ".zip", new File(savePath));
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        return basePath + path + ".zip";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String batchSaveAndDownload(String type, String activityId, String parkingId, String userId, HttpServletRequest request) throws Exception {
        List<Integer> idList = this.batchSave(type, activityId, parkingId, userId);
        return this.batchDownload(StringUtils.join(idList, ","), request);
    }

    @Override
    public int updateScanCount(int id, int scanCount) {
        return activityQrCodeDao.updateScanCount(id, scanCount);
    }
}