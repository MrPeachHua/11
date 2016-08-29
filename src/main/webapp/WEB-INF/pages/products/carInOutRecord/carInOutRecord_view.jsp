<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>共享临停订单信息</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=basePath%>/css/style_v2.css">
  <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  <style type="text/css">
    body {
      padding: 15px 20px;
    }
    td {
      padding: 10px;
    }
  </style>
</head>

<body>
<div class="mainView">
  <form action=" <%=basePath%>servlet/UserUpdateServlet" method="get" name="form2"  >
    <table class=editTable cellSpacing=0 cellPadding=0 width="100%"
           align=center border='0'>
      <tr class=editHeaderTr>
        <td class='headerTitle' colSpan=7>
          车辆进出场详细信息
        </td>
      </tr>
      <tr>
        <td align="right"></td>
        <td colspan="3" ></td>
        <td align="right"></td>
        <td colspan="3" ></td>
      </tr>
      <tr>
        <td align="right" style="text-align: right;"><div >CAR_TYPE：</div></td>
        <td colspan="3"  >${carInOutRecord.carType}</td>
        <td align="right" ><div >CONFIDENCE：</div></td>
        <td colspan="3"  >${carInOutRecord.confidence}</td>
      </tr>
      <tr>
        <td  align="right" style="height: 21px"><div >DISCOUNT_CHARGE：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.discountCharge}</td>
        <td align="right" ><div >DISCOUNT_CODE：</div></td>
        <td colspan="3"  >${carInOutRecord.discountCode}</td>
      </tr>
      <tr>
        <td align="right" ><div > DISCOUNT_TIME：</div></td>
        <td colspan="3"  >${carInOutRecord.discountTime}</td>
        <td align="right" ><div >EDIT_FLAG：</div></td>
        <td colspan="3"  >${carInOutRecord.editFlag}</td>
      </tr>
      <tr>
        <td align="right" ><div > INGATE_ID：</div></td>
        <td colspan="3"  >${carInOutRecord.ingateId}</td>
        <%--<td colspan="3"  ><fmt:formatDate  value="${carInOutRecord.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
        <td align="right" ><div >IN_IMAGENAME：</div></td>
        <td colspan="3"  >${carInOutRecord.inImagename}</td>
      </tr>
      <tr>
        <td align="right"  style="height: 21px"><div >IN_LEDID：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.inLedid}</td>
        <td align="right"  style="height: 21px"><div >INOROUT：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.inorout}</td>
      </tr>
      <tr>
        <td align="right" style="text-align: right;"><div >INREADER_ID：</div></td>
        <td colspan="3"  >${carInOutRecord.inreaderId}</td>
        <td align="right" ><div >INTIME：</div></td>
        <td colspan="3"  ><fmt:formatDate  value="${carInOutRecord.intime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      </tr>
      <tr>
        <td  align="right" style="height: 21px"><div >INVIDEO_ID：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.invideoId}</td>
        <td align="right" ><div >INVOICE_CODE：</div></td>
        <td colspan="3"  >${carInOutRecord.invoiceCode}</td>
      </tr>
      <tr>
        <td align="right" ><div > OFFLINE_CHARGE：</div></td>
        <td colspan="3"  >${carInOutRecord.offlineCharge}</td>
        <td align="right" ><div >ONLINE_CHARGE：</div></td>
        <td colspan="3"  >${carInOutRecord.onlineCharge}</td>
      </tr>
      <tr>
        <td align="right" ><div > ORDER_ID：</div></td>
        <td colspan="3"  >${carInOutRecord.orderId}</td>
        <td align="right" ><div >OUTGATE_ID：</div></td>
        <td colspan="3"  >${carInOutRecord.outgateId}</td>
      </tr>
      <tr>
        <td align="right"  style="height: 21px"><div >OUT_IMAGENAME：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.outImagename}</td>
        <td align="right"  style="height: 21px"><div >OUTLED_ID：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.outledId}</td>
      </tr>
      <tr>
        <td align="right"  style="height: 21px"><div >OUTREADER_ID：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.outreaderId}</td>
        <td align="right"  style="height: 21px"><div >OUTTIME：</div></td>
        <td colspan="3"  style="height: 21px" ><fmt:formatDate  value="${carInOutRecord.outtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      </tr>
      <tr>
        <td align="right" style="text-align: right;"><div >OUTVIDEO_ID：</div></td>
        <td colspan="3"  >${carInOutRecord.outvideoId}</td>
        <td align="right" ><div >PARK_ID：</div></td>
        <td colspan="3"  >${carInOutRecord.parkId}</td>
      </tr>
      <tr>
        <td  align="right" style="height: 21px"><div >PARK_SPACE_NUM：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.parkSpaceNum}</td>
        <td align="right" ><div >PAY_CHARGE：</div></td>
        <td colspan="3"  >${carInOutRecord.payCharge}</td>
      </tr>
      <tr>
        <td align="right" ><div > PLATE_COLOR：</div></td>
        <td colspan="3"  >${carInOutRecord.plateColor}</td>
        <td align="right" ><div >PLATE_ID：</div></td>
        <td colspan="3"  >${carInOutRecord.plateId}</td>
      </tr>
      <tr>
        <td align="right" ><div > PLATEK：</div></td>
        <td colspan="3"  >${carInOutRecord.platek}</td>
        <td align="right" ><div >REAL_CHARGE：</div></td>
        <td colspan="3"  >${carInOutRecord.realCharge}</td>
      </tr>
      <tr>
        <td align="right"  style="heig，ht: 21px"><div >RECORD_ID：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.recordId}</td>
        <td align="right"  style="height: 21px"><div >SYS_WRITE_DATE：</div></td>
        <td colspan="3"  style="height: 21px" ><fmt:formatDate  value="${carInOutRecord.sysWriteDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
      </tr>
      <tr>
        <td align="right"  style="height: 21px"><div >USER_TYPE：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord.userType}</td>
        <td align="right"  style="height: 21px"><div >parkingName：</div></td>
        <td colspan="3"  style="height: 21px" >${carInOutRecord2.parkingName}</td>
      </tr>
      <tr>
        <td align="right"></td>
        <td colspan="3" ></td>
        <td align="right"></td>
        <td colspan="3" >
          <div style="padding: 20px 20px 20px 0px;">
            <input class="greenBtn" type="button" name="back" onclick="history.back()" value="返&nbsp;&nbsp;回">
          </div>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
