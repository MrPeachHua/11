package com.boxiang.share.product.activity.service;

import com.boxiang.share.product.activity.po.ActivityQrCode;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface ActivityQrCodeService {

    List<ActivityQrCode> selectList(ActivityQrCode activityQrCode);

    Page<ActivityQrCode> queryListPage(Page<ActivityQrCode> page);

    ActivityQrCode queryById(Integer id);

    void add(ActivityQrCode activityQrCode);

    void delete(Integer id);

    void update(ActivityQrCode activityQrCode);

    void batchUpdate(List<ActivityQrCode> activityQrCodeList);

    List<Map<String, Object>> queryListPageV2(Object page);

    List<Integer> batchSave(String type, String activityId, String parkingId, String userId);

    String getFileName(Map<String, Object> entity);

    void setBaseProperty(ActivityQrCode activityQrCode);

    List<Map<String, Object>> queryByIdV2(String ids);

    String getEncodeUrl(Map<String, Object> entity, HttpServletRequest request);

    String batchDownload(String ids, HttpServletRequest request) throws Exception;

    String batchSaveAndDownload(String type, String activityId, String parkingId, String userId, HttpServletRequest request) throws Exception;

    int updateScanCount(int id, int scanCount);
}