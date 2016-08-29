var parkingView = {
    parking: {parkingId: null, parkingName: null},
    $view: null,
    jsonData: null,
    init: function () {
        var html = "<div id=\"parkingView\" class=\"content defaultShadow bodyColor\" style=\"position:absolute; width:80%; height:450px; overflow: auto; left:10%; top:10%; display:none;\">\n" +
            "    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "        <tr>\n" +
            "            <td height=\"30\" margin=\"0\" padding=\"0\">\n" +
            "                <table width=\"100%\" height=\"60\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "                    <tr>\n" +
            "                        <td width=\"12\" height=\"30\"></td>\n" +
            "                        <td>\n" +
            "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td class=\"STYLE4\">&nbsp;&nbsp;请输入查询内容：<input\n" +
            "                                            type=\"text\" name=\"queryValue\" id=\"queryValue\" style=\"width: 290px\"/></td>\n" +
            "                                    <td class=\"STYLE4\">&nbsp;&nbsp;请选择查询方式：<select\n" +
            "                                            name=\"queryType\" id=\"queryType\" style=\"width: 100px\">\n" +
            "                                        <option value=\"1\">车场代码</option>\n" +
            "                                        <option value=\"2\">车场名称</option>\n" +
            "                                        <option value=\"3\">车场地址</option>\n" +
            "                                    </select>\n" +
            "                                    </td>\n" +
            "                                    <td class=\"STYLE4\">&nbsp;&nbsp; <input class=\"searchBtn\" type=\"button\"\n" +
            "                                                                           onclick=\"parkingView.search()\"\n" +
            "                                                                           value=\"查&nbsp;询\"/>\n" +
            "                                    </td>\n" +
            "                                    <td class=\"STYLE4\">&nbsp;&nbsp; <input class=\"searchBtn\" type=\"button\"\n" +
            "                                                                           value=\"取&nbsp;消\" onclick=\"parkingView.hide()\"/>\n" +
            "                                    </td>\n" +
            "                                    <td class=\"STYLE4\">&nbsp;&nbsp; <input class=\"searchBtn\" type=\"button\"\n" +
            "                                                                           value=\"确&nbsp;定\" onclick=\"parkingView.enter()\"/>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                        <td width=\"16\"></td>\n" +
            "                    </tr>\n" +
            "                </table>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td>\n" +
            "                <table width=\"100%\" border=\"0\" cellspacing=\"0\"\n" +
            "                       cellpadding=\"0\">\n" +
            "                    <tr>\n" +
            "                        <td width=\"8\">&nbsp;</td>\n" +
            "                        <td>\n" +
            "                            <table style='margin-bottom:7px;' class=\"mainView tableView\" id=\"mytable\" width=\"100%\" border=\"0\" cellpadding=\"0\"\n" +
            "                                   cellspacing=\"0\">\n" +
            "                                <tr class=\"tableHeader\" style=\"border: 0;\">\n" +
            "                                    <td style=\"width: 10%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">\n" +
            "                                                <input onclick=\"parkingView.checkAll(this)\" type=\"checkbox\">&nbsp;全选\n" +
            "                                            </span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"width: 10%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">车场代码</span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"width: 15%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">车场名称</span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"width: 15%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">小区</span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"width: 20%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">地址</span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"width: 10%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">车位数</span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"width: 10%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">空位数</span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                    <td style=\"width: 10%; \">\n" +
            "                                        <div>\n" +
            "                                            <span class=\"STYLE1\">车位状态</span>\n" +
            "                                        </div>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                            </table>\n" +
            "                        </td>\n" +
            "                        <td width=\"8\">&nbsp;</td>\n" +
            "                    </tr>\n" +
            "                </table>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "</div>";
        $('body').append(html);
        this.$view = $('#parkingView');
    },
    search: function () {
        var queryType = $('#queryType').val();
        var queryValue = $('#queryValue').val();
        if (queryType == '1') {
            $("#mytable .parking_check").each(function () {
                var $parent = $(this).parents('.parkingListTr');
                if ($(this).val().indexOf(queryValue) >= 0) {
                    $parent.show();
                } else {
                    $parent.hide();
                }
            });
        } else if (queryType == '2') {
            $("#mytable .parking_check").each(function () {
                var $parent = $(this).parents('.parkingListTr');
                if ($(this).attr("name").indexOf(queryValue) >= 0) {
                    $parent.show();
                } else {
                    $parent.hide();
                }
            });
        } else if (queryType == '3') {
            $("#mytable .parking_address").each(function () {
                var $parent = $(this).parents('.parkingListTr');
                if ($(this).text().trim().indexOf(queryValue) >= 0) {
                    $parent.show();
                } else {
                    $parent.hide();
                }
            });
        }
    },
    checkAll: function (obj) {
        if (obj.checked) {
            $("#mytable .parking_check").each(function () {
                if ($(this).parents('.parkingListTr').is(":hidden")) {
                    this.checked = false;
                } else {
                    this.checked = true;
                }
            });
        } else {
            $("#mytable .parking_check").each(function () {
                this.checked = false;
            });
        }
    },
    requestData: function () {
        if (this.jsonData == null) {
            var local = (window.location + '').split('/');
            var basePath = local[0] + '//' + local[2] + '/' + local[3];
            $.ajax({
                type: "POST",
                url: basePath + "/products/carlife/srvbilling/parkingData.html",
                dataType: "json",
                success: function (jsonStr) {
                    parkingView.jsonData = jsonStr;
                    parkingView.layout();
                }
            });
        } else {
            this.layout();
        }
    },
    layout: function () {
        var jsonStr = this.jsonData;
        var parkingIds = this.parking.parkingId.value;
        $(".parkingListTr").remove();
        for (var i = 0; i < jsonStr.length; i++) {
            if (parkingIds != null && parkingIds.length > 0 && parkingIds.indexOf(jsonStr[i].parkingId) >= 0) {
                var checkStatus = '<td><input class="parking_check" type="checkbox" checked="checked" name="' + jsonStr[i].parkingName + '" value=' + jsonStr[i].parkingId + '></td>';
            } else {
                var checkStatus = '<td><input class="parking_check" type="checkbox"  name="' + jsonStr[i].parkingName + '" value=' + jsonStr[i].parkingId + '></td>';
            }
            $("table#myTable tr:last").after('<tr class = "parkingListTr" style="text-align:center;">' + checkStatus + '<td >' + jsonStr[i].parkingId + '</td><td >' + jsonStr[i].parkingName + '</td><td>' + jsonStr[i].parkingArea + '</td><td class="parking_address">' + jsonStr[i].parkingAddress + '</td><td >' + jsonStr[i].parkingCount + '</td><td >' + jsonStr[i].parkingCanUse + '</td><td >' + jsonStr[i].parkingStatus + '</td>' + '</tr>');
        }
    },
    enter: function () {
        var strId = "";
        var strName = "";
        $("#mytable .parking_check:checked").each(function (index, element) {
            if ($(this).val().length > 0) {
                strId += $(this).val();
                strId += ",";
                if (parkingView.parking.parkingName != null) {
                    strName += $(this).attr("name");
                    strName += ",";
                }
            }
        });
        if (strId.length > 0) {
            strId = strId.substring(0, strId.length - 1);
        }
        if (this.parking.parkingName != null) {
            if (strName.length > 0) {
                strName = strName.substring(0, strName.length - 1);
            }
            this.parking.parkingName.value = strName;
        }
        this.parking.parkingId.value = strId;
        this.hide();
    },
    show: function (parking) {
        this.parking = parking;
        if (!this.$view) {
            this.init();
        }
        this.setDivCenter(this.$view);
        this.$view.show();
        this.requestData();
    },
    hide: function () {
        this.parking = null;
        this.$view.hide();
    },
    setDivCenter: function ($div) {
        var scrollTop = $(document).scrollTop();
        var scrollLeft = $(document).scrollLeft();
        var top = 25;
        var left = ($(window).width() - scrollLeft - $div.width()) / 2;
        $div.css({position: 'absolute', 'top': top + scrollTop, left: left + scrollLeft});
    }
};
