package com.boxiang.share.product.order.po;

import com.boxiang.share.utils.OrderConstants;

public class OrderMainExcel extends OrderMain {

    @Override
    public void setOrderStatus(String orderStatus) {
        super.setOrderStatus(OrderConstants.getOrderStatusName(orderStatus));
    }

    @Override
    public void setInvoiceStatus(String invoiceStatus) {
        if (invoiceStatus == null) {
            invoiceStatus = "";
        } else if (invoiceStatus.equals("0")) {
            invoiceStatus = "未开具";
        } else if (invoiceStatus.equals("1")) {
            invoiceStatus = "已开具";
        }
        super.setInvoiceStatus(invoiceStatus);
    }

    @Override
    public void setPayType(String payType) {
        if (payType == null) {
            payType = "";
        } else if (payType.equals("00")) {
            payType = "支付宝";
        } else if (payType.equals("01")) {
            payType = "微信";
        } else if (payType.equals("02")) {
            payType = "银联";
        }else if (payType.equals("05")) {
            payType = "钱包";
        }
        super.setPayType(payType);
    }
    @Override
    public void setOrderType(String orderType) {
        if (orderType == null) {
        	orderType = "";
        } else if (orderType.equals("12")) {
        	orderType = "代泊";
        } 
        super.setOrderType(orderType);
    }
}
