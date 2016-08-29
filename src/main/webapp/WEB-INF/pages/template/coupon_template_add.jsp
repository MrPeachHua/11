<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<script type="text/javascript">
    function add_line() {
        var html = "<tr class=\"line\">\n" +
                "            <td>\n" +
                "                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                    <tr>\n" +
                "                        <td>\n" +
                "                            <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\" bgcolor=\"b5d6e6\">\n" +
                "                                <tr>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">优惠券种类：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                    <%--0：通用卷 1：停车卷 2：月租产权劵 3：代泊券 4：车管家券--%>\n" +
                "                                       <select class=\"couponTemplateList_couponKind\">\n" +

                                                            <c:forEach items="${requestScope.dict}" var="d">
                "                                                <option value='${d.dictValue}'>${d.dictName}</option>" +
                                                            </c:forEach>

                "                                        </select>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">券名：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <input type=\"text\" class=\"couponTemplateList_couponName\"/>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">面值(元)：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <input type=\"text\" required=\"required\" pattern=\"\\d+\" check_str=\"面值\" check_type=\"integer,0,\" class=\"couponTemplateList_parAmount\"/>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">最低消费(元)：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <input type=\"text\" required=\"required\" pattern=\"\\d+\" check_str=\"最低消费\" check_type=\"integer,0,\" class=\"couponTemplateList_minconsumption\"/>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">有效类型：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <%--有效类型 1：使用开始结束时间 2：使用有效天数--%>\n" +
                "                                        <select onchange=\"effective_type_change(this)\" class=\"couponTemplateList_effectiveType\">\n" +
                "                                            <option value=\"1\">一般使用有效期</option>\n" +
                "                                            <option value=\"2\">带领取的有效期</option>\n" +
                "                                        </select>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">有效开始时间：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <input class=\"couponTemplateList_effectiveBegin\" readonly=\"readonly\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\">\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">自激活之日起有效期天数(天)：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <input type=\"text\"  class=\"couponTemplateList_effectiveDay\" disabled=\"disabled\"/>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">有效结束时间：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <input class=\"couponTemplateList_effectiveEnd\" readonly=\"readonly\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\">\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">互斥规则：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <select class=\"couponTemplateList_exclusionRule\">\n" +
                "                                            <option value=\"\">无</option>\n" +
                "                                            <option value=\"不得与其他优惠同时使用\">不得与其他优惠同时使用</option>\n" +
                "                                        </select>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFDF0\">\n" +
                "                                        <div align=\"center\">备注说明：</div>\n" +
                "                                    </td>\n" +
                "                                    <td bgcolor=\"#FFFFFF\">\n" +
                "                                        <input type=\"text\" class=\"couponTemplateList_info\"/>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "            <tr>\n" +
                "              <td bgcolor=\"#FFFDF0\">\n" +
                "                <div align=\"center\">车场ID：</div>\n" +
                "              </td>\n" +
                "              <td bgcolor=\"#FFFFFF\">\n" +
                "                <input type=\"text\" class=\"couponTemplateList_parkingId\"/>\n" +
                "              </td>\n" +
                "              <td bgcolor=\"#FFFDF0\">\n" +
                "                <div align=\"center\">车场名称：</div>\n" +
                "              </td>\n" +
                "              <td bgcolor=\"#FFFFFF\">\n" +
                "                <input type=\"text\" class=\"couponTemplateList_parkingName\"/>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "                                <tr>\n" +
                "                                    <td bgcolor=\"#FFFFFF\" colspan=\"4\" align=\"right\">\n" +
                "                                        <div style=\"background-color:#D5EFFF;\">\n" +
                "                                            <input onclick=\"del_line(this)\" type=\"button\" value=\"删除\">\n" +
                "                                        </div>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>";
        $("#tab").append(html);
    }

    function del_line(obj) {
//        if ($(".line").length > 1) {
            $(obj).parents('.line').remove();
//        } else {
//            alert('至少要填写一种优惠券吧！');
//        }
    }

    function del(id, obj) {
//        if ($(".line").length > 1) {
            $(obj).attr("disabled", true);
            $.get("<%=basePath%>/products/redeemRule/" + id + "/delTemplate.html", function (data, status) {
                if (data == '0') {
                    $(obj).parents('.line').remove();
                } else {
                    alert('出错了');
                    location.reload();
                }
            });
//        } else {
//            alert('至少要填写一种优惠券吧！');
//        }
    }

    function effective_type_change(obj) {
        $(obj).parents('.line').find(".couponTemplateList_effectiveDay").attr("disabled", obj.value != 2);
        $(obj).parents('.line').find(".couponTemplateList_effectiveBegin").attr("disabled", obj.value != 1);
        $(obj).parents('.line').find(".couponTemplateList_effectiveEnd").attr("disabled", obj.value != 1);
    }
    $('.couponTemplateList_effectiveType').each(function () {
        effective_type_change(this);
    });

    function form_submit(obj) {
        if (on_submit($(obj).parents('form'))) {
            $(obj).parents('form').submit();
        }
    }

    function on_submit(obj) {
        var $line = $('.line');
        for (var j = 0; j < $line.length; j++) {
            var type = $($line[j]).find('.couponTemplateList_effectiveType').val();
            if (type == '1') {
                var effectiveBegin = $($line[j]).find('.couponTemplateList_effectiveBegin').val();
                var effectiveEnd = $($line[j]).find('.couponTemplateList_effectiveEnd').val();
                if (effectiveBegin.trim().length == 0 || effectiveEnd.trim().length == 0) {
                    alert('请填写优惠券的有效开始时间和结束时间!');
                    return false;
                }
            } else if (type == '2') {
                var effectiveDay = $($line[j]).find('.couponTemplateList_effectiveDay').val();
                if (!(/^\d+$/.test(effectiveDay) && effectiveDay > 0)) {
                    alert('请填写自激活之日起有效期天数(天)，且必须大于0!');
                    return false;
                }
            }
        }
        var form_name = 'obj_form_name';
        $(obj).attr("name", form_name);
        if (!checkForm(form_name)) {
            return false;
        }
        var i = 0;
        $line.each(function () {
            $(this).find(".couponTemplateList_couponKind").attr("name", "couponTemplateList[" + i + "].couponKind");
            $(this).find(".couponTemplateList_couponName").attr("name", "couponTemplateList[" + i + "].couponName");
            $(this).find(".couponTemplateList_parAmount").attr("name", "couponTemplateList[" + i + "].parAmount");
            $(this).find(".couponTemplateList_minconsumption").attr("name", "couponTemplateList[" + i + "].minconsumption");
            $(this).find(".couponTemplateList_effectiveType").attr("name", "couponTemplateList[" + i + "].effectiveType");
            $(this).find(".couponTemplateList_effectiveBegin").attr("name", "couponTemplateList[" + i + "].effectiveBegin");
            $(this).find(".couponTemplateList_effectiveEnd").attr("name", "couponTemplateList[" + i + "].effectiveEnd");
            $(this).find(".couponTemplateList_effectiveDay").attr("name", "couponTemplateList[" + i + "].effectiveDay");
            $(this).find(".couponTemplateList_exclusionRule").attr("name", "couponTemplateList[" + i + "].exclusionRule");
            $(this).find(".couponTemplateList_info").attr("name", "couponTemplateList[" + i + "].info");
            $(this).find(".couponTemplateList_id").attr("name", "couponTemplateList[" + i + "].id");
            $(this).find(".couponTemplateList_createDate").attr("name", "couponTemplateList[" + i + "].createDate");
            $(this).find(".couponTemplateList_createor").attr("name", "couponTemplateList[" + i + "].createor");
            $(this).find(".couponTemplateList_isUsed").attr("name", "couponTemplateList[" + i + "].isUsed");
            $(this).find(".couponTemplateList_type").attr("name", "couponTemplateList[" + i + "].type");
            $(this).find(".couponTemplateList_typeId").attr("name", "couponTemplateList[" + i + "].typeId");
            $(this).find(".couponTemplateList_parkingId").attr("name", "couponTemplateList[" + i + "].parkingId");
            $(this).find(".couponTemplateList_parkingName").attr("name", "couponTemplateList[" + i + "].parkingName");
            i++;
        });
        return true;
    }
</script>
<link href="<%=basePath%>/css/pages/style_v2.1.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=basePath%>/js/pages/parkingView.js"></script>
<script type="text/javascript">
    $(document).on("click", ".couponTemplateList_parkingId", function () {
        var parkingName = $(this).parents('.line').find('.couponTemplateList_parkingName').get(0);
        parkingView.show({parkingId: this, parkingName: parkingName});
    });
    $(document).on("click", ".couponTemplateList_parkingName", function () {
        var parkingId = $(this).parents('.line').find('.couponTemplateList_parkingId').get(0);
        parkingView.show({parkingId: parkingId, parkingName: this});
    });
</script>
