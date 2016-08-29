$(function () {
    (function (window, $) {
        var document = window.document,
            _$parkingIdInput = null,
            _$parkingNameInput = null,
            _isMultiSelect = false,
            _jsonData = null,
            _$alertView = initAlertView(),
            _$tbody = _$alertView.find('.contentView .contentTable tbody'),
            _$searchBtn = _$alertView.find('.queryView ul li.right .searchBtn.search'),
            _$queryType = _$alertView.find('.queryView ul li .queryType'),
            _$queryValue = _$alertView.find('.queryView ul li .queryValue'),
            _$checkAll = _$alertView.find('.contentView .contentTable thead tr td.isMultiSelect .checkAll'),
            _$multiEnterBtn = _$alertView.find('.queryView ul li.right .isMultiSelect.searchBtn');

        function C(clazz, tagName, attr) {
            var element = document.createElement(tagName == null ? 'div' : tagName);
            if (clazz != null && clazz.length > 0) {
                element.setAttribute('class', clazz);
            }
            if (attr != null) {
                for (var key in attr) {
                    element.setAttribute(key, attr[key]);
                }
            }
            return element;
        }

        function initAlertView() {
            var div = C('alertView');
            div.innerHTML =
                "    <div class=\"queryView\">\n" +
                "        <ul>\n" +
                "            <li>\n" +
                "                <span>请输入查询内容：</span>\n" +
                "                <input class='queryValue' style=\"width: 200px\" type=\"text\">\n" +
                "            </li>\n" +
                "            <li>\n" +
                "                <span>请选择查询方式：</span>\n" +
                "                <select class='queryType' style=\"width: 90px\">\n" +
                "                    <option value=\"2\">车场名称</option>\n" +
                "                    <option value=\"1\">车场代码</option>\n" +
                "                    <option value=\"3\">车场地址</option>\n" +
                "                </select>\n" +
                "            </li>\n" +
                "            <li class=\"right\">\n" +
                "                <input class=\"searchBtn search\" type=\"button\" value=\"查&nbsp;询\">\n" +
                "                <input class=\"searchBtn\" type=\"button\" value=\"取&nbsp;消\" onclick='pv.hide()'>\n" +
                "                <input class=\"isMultiSelect searchBtn\" type=\"button\" value=\"确&nbsp;定\">\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"contentView\">\n" +
                "        <table class=\"contentTable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <td class=\"isMultiSelect\"><input class='checkAll' type=\"checkbox\">全选</td>\n" +
                "                <td>车场代码</td>\n" +
                "                <td>车场名称</td>\n" +
                "                <td>小区</td>\n" +
                "                <td>地址</td>\n" +
                "                <td>车位数</td>\n" +
                "                <td>空位数</td>\n" +
                "                <td>车位状态</td>\n" +
                "                <td class=\"isSingleSelect\">操作</td>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>";
            $('body').append(div);
            return $(div);
        }

        function setDivCenter() {
            var scrollTop = $(document).scrollTop();
            var scrollLeft = $(document).scrollLeft();
            var top = 25;
            var left = ($(window).width() - scrollLeft - _$alertView.width()) / 2;
            _$alertView.css({position: 'absolute', 'top': top + scrollTop, left: left + scrollLeft});
        }

        function requestData() {
            if (_jsonData == null) {
                $.ajax({
                    type: "POST",
                    url: "/share/products/carlife/srvbilling/parkingData.html",
                    dataType: "json",
                    success: function (jsonStr) {
                        _jsonData = jsonStr;
                        layout();
                    }
                });
            } else {
                layout();
            }
        }

        function layout() {
            var parkingIds = _$parkingIdInput.val();
            _$tbody.html("");
            var flag = true;
            for (var i = 0; i < _jsonData.length; i++) {
                var item = _jsonData[i];
                var tr = C(null, 'tr');
                var firstTd = C('isMultiSelect', 'td');
                var checkbox = C(null, 'input', {
                    "type": "checkbox",
                    "value": item.parkingId,
                    "data-name": item.parkingName,
                    "data-address": item.parkingAddress
                });
                if (_isMultiSelect && parkingIds != null && parkingIds.length > 0 && parkingIds.indexOf(item.parkingId) >= 0) {
                    checkbox.setAttribute("checked", "checked");
                } else {
                    flag = false;
                }
                firstTd.appendChild(checkbox);
                tr.appendChild(firstTd);
                $(tr).append('<td>' + item.parkingId + '</td>');
                $(tr).append('<td>' + item.parkingName + '</td>');
                $(tr).append('<td>' + item.parkingArea + '</td>');
                $(tr).append('<td>' + item.parkingAddress + '</td>');
                $(tr).append('<td>' + item.parkingCount + '</td>');
                $(tr).append('<td>' + item.parkingCanUse + '</td>');
                $(tr).append('<td>' + item.parkingStatus + '</td>');
                var lastTd = C('isSingleSelect', 'td');
                var choseBtn = C('chose', 'input', {
                    "type": "button",
                    "value": "选择",
                    "data-id": item.parkingId,
                    "data-name": item.parkingName
                });
                $(choseBtn).css({"margin-right": "10px"});
                lastTd.appendChild(choseBtn);
                tr.appendChild(lastTd);
                _$tbody.append(tr);
            }
            _$checkAll.get(0).checked = flag;
            singleOrMulti();
        }

        function singleCheck() {
            _$tbody.find('.chose').click(function () {
                _$parkingIdInput.val(this.dataset.id);
                _$parkingNameInput.val(this.dataset.name);
                pv.hide();
            });
        }

        function singleOrMulti() {
            if (_isMultiSelect) {
                _$alertView.find('.isMultiSelect').show();
                _$alertView.find('.isSingleSelect').hide();
            } else {
                _$alertView.find('.isMultiSelect').hide();
                _$alertView.find('.isSingleSelect').show();
                singleCheck();
            }
        }

        _$searchBtn.click(function () {
            var queryType = _$queryType.val();
            var queryValue = _$queryValue.val();
            var $checkBox = _$tbody.find('.isMultiSelect input');

            function visible(obj, value) {
                var $parent = $(obj).parents('tr');
                value.indexOf(queryValue) >= 0 ? $parent.show() : $parent.hide();
            }

            if (queryType == '1') {
                $checkBox.each(function () {
                    visible(this, this.value);
                });
            } else if (queryType == '2') {
                $checkBox.each(function () {
                    visible(this, this.dataset.name);
                });
            } else if (queryType == '3') {
                $checkBox.each(function () {
                    visible(this, this.dataset.address);
                });
            }
        });
        _$checkAll.click(function () {
            if (this.checked) {
                _$tbody.find('.isMultiSelect input').each(function () {
                    this.checked = !$(this).parents('tr').is(":hidden");
                });
            } else {
                _$tbody.find('.isMultiSelect input').each(function () {
                    this.checked = false;
                });
            }
        });
        _$multiEnterBtn.click(function () {
            var strId = "";
            var strName = "";
            _$tbody.find('.isMultiSelect input:checked').each(function (index, element) {
                if ($(this).val().length > 0) {
                    strId += $(this).val();
                    strId += ",";
                    strName += this.dataset.name;
                    strName += ",";
                }
            });
            if (strId.length > 0) {
                strId = strId.substring(0, strId.length - 1);
            }
            if (strName.length > 0) {
                strName = strName.substring(0, strName.length - 1);
            }
            _$parkingNameInput.val(strName);
            _$parkingIdInput.val(strId);
            pv.hide();
        });
        window.pv = {
            show: function ($parkingIdInput, $parkingNameInput, isMultiSelect) {
                _$parkingIdInput = $parkingIdInput;
                _$parkingNameInput = $parkingNameInput;
                _isMultiSelect = isMultiSelect;
                requestData();
                setDivCenter();
                _$alertView.show();
            },
            hide: function () {
                _$parkingIdInput = null;
                _$parkingNameInput = null;
                _$alertView.hide();
                _$queryValue.val("");
                _$checkAll.removeAttr('checked');
            }
        };
    })(window, $);
});