<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java"%>
<tr class="line">
  <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
            <tr>
              <td bgcolor="#FFFDF0">
                <div align="center">优惠券种类：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <%--0：通用卷 1：停车卷 2：月租产权劵 3：代泊券 4：车管家券--%>
                <select class="couponTemplateList_couponKind">
                  <c:forEach items="${requestScope.dict}" var="d">
                    <option <c:if test="${item.couponKind eq d.dictValue}">selected="selected"</c:if> value="${d.dictValue}">${d.dictName}</option>
                  </c:forEach>
                </select>
              </td>
              <td bgcolor="#FFFDF0">
                <div align="center">券名：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input type="text" class="couponTemplateList_couponName" value="${item.couponName}"/>
              </td>
            </tr>
            <tr>
              <td bgcolor="#FFFDF0">
                <div align="center">面值(元)：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input type="text" required="required" pattern="\d+" check_str="面值" check_type="integer,0," class="couponTemplateList_parAmount" value="<fmt:parseNumber value="${item.parAmount}" integerOnly="true"/>"/>
              </td>
              <td bgcolor="#FFFDF0">
                <div align="center">最低消费(元)：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input type="text" required="required" pattern="\d+" check_str="最低消费" check_type="integer,0," class="couponTemplateList_minconsumption" value="<fmt:parseNumber value="${item.minconsumption}" integerOnly="true"/>"/>
              </td>
            </tr>
            <tr>
              <td bgcolor="#FFFDF0">
                <div align="center">有效类型：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <%--有效类型 1：使用开始结束时间 2：使用有效天数--%>
                <select onchange="effective_type_change(this)" class="couponTemplateList_effectiveType">
                  <option <c:if test="${item.effectiveType eq '1'}">selected="selected"</c:if> value="1">一般使用有效期</option>
                  <option <c:if test="${item.effectiveType eq '2'}">selected="selected"</c:if> value="2">带领取的有效期</option>
                </select>
              </td>
              <td bgcolor="#FFFDF0">
                <div align="center">有效开始时间：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input class="couponTemplateList_effectiveBegin"
                       value='<fmt:formatDate value="${item.effectiveBegin}" type="both" pattern="yyyy-MM-dd"/>'
                       readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
              </td>
            </tr>
            <tr>
              <td bgcolor="#FFFDF0">
                <div align="center">自激活之日起有效期天数(天)：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input type="text"  class="couponTemplateList_effectiveDay" value="${item.effectiveDay}" disabled="disabled"/>
              </td>
              <td bgcolor="#FFFDF0">
                <div align="center">有效结束时间：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input class="couponTemplateList_effectiveEnd"
                       value='<fmt:formatDate value="${item.effectiveEnd}" type="both" pattern="yyyy-MM-dd"/>'
                       readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
              </td>
            </tr>
            <tr>
              <td bgcolor="#FFFDF0">
                <div align="center">互斥规则：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <select class="couponTemplateList_exclusionRule">
                  <option value="">无</option>
                  <option <c:if test="${fn:length(item.exclusionRule) > 0}">selected="selected"</c:if> value="不得与其他优惠同时使用">不得与其他优惠同时使用</option>
                </select>
              </td>
              <td bgcolor="#FFFDF0">
                <div align="center">备注说明：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input type="text" class="couponTemplateList_info" value="${item.info}"/>
              </td>
            </tr>
            <tr>
              <td bgcolor="#FFFDF0">
                <div align="center">车场ID：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input type="text" class="couponTemplateList_parkingId" value="${item.parkingId}"/>
              </td>
              <td bgcolor="#FFFDF0">
                <div align="center">车场名称：</div>
              </td>
              <td bgcolor="#FFFFFF">
                <input type="text" class="couponTemplateList_parkingName" value="${item.parkingName}"/>
              </td>
            </tr>
            <tr>
              <td bgcolor="#FFFFFF" colspan="4" align="right">
                <div style="background-color:#D5EFFF;">
                  <input onclick="del(${item.id}, this)" type="button" value="删除">
                  <input type="hidden" class="couponTemplateList_id" value="${item.id}"/>
                  <input type="hidden" class="couponTemplateList_createDate" value="<fmt:formatDate value="${item.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                  <input type="hidden" class="couponTemplateList_createor" value="${item.createor}"/>
                  <input type="hidden" class="couponTemplateList_isUsed" value="${item.isUsed}"/>
                  <input type="hidden" class="couponTemplateList_type" value="${item.type}"/>
                  <input type="hidden" class="couponTemplateList_typeId" value="${item.typeId}"/>
                </div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>