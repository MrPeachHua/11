$(function () {
    var couponRule = {
        blueParkingId: document.getElementsByName('blueParkingId'),
        parkingId: document.getElementsByName('parkingId'),
        parkingName: document.getElementsByName('parkingName'),
        sceneMode: document.getElementsByName('sceneMode'),
        userType: document.getElementsByName('userType'),
        orderType: document.getElementsByName('orderType'),
        orderCount: document.getElementsByName('orderCount'),
        monthlyPropertyExpireBefore: document.getElementsByName('monthlyPropertyExpireBefore'),
        monthlyPropertyExpireAfter: document.getElementsByName('monthlyPropertyExpireAfter'),
        couponExpireBefore: document.getElementsByName('couponExpireBefore'),
        couponExpireType: document.getElementsByName('couponExpireType'),
        all: function () {
            return [
                this.blueParkingId,
                this.parkingId,
                this.parkingName,
                this.sceneMode,
                this.userType,
                this.orderType,
                this.orderCount,
                this.monthlyPropertyExpireBefore,
                this.monthlyPropertyExpireAfter,
                this.couponExpireBefore,
                this.couponExpireType
            ]
        },
        isDisable: function (list, flag) {
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                for (var j = 0; j < item.length; j++) {
                    item[j].disabled = flag;
                }
            }
        }
    };

    var $couponExpireAlert = $("input[name='sceneMode'][value='8']");
    $couponExpireAlert.click(couponExpireAfterClick);
    function couponExpireAfterClick(e) {
        if ($couponExpireAlert[0].checked) {
            couponRule.isDisable(couponRule.all(), true);
            couponRule.isDisable([
                couponRule.couponExpireBefore,
                couponRule.couponExpireType
            ], false);
            $couponExpireAlert[0].disabled = false;
        } else if (e) {
            couponRule.isDisable(couponRule.all(), false);
        }
        var $couponLine = $('#couponLine');
        $couponExpireAlert[0].checked ? $couponLine.show() : $couponLine.hide();
    }
    couponExpireAfterClick();

    var $allCouponType = $('input[name="couponExpireType"][value=""]');
    function allCouponTypeClick(e) {
        if ($allCouponType[0].checked) {
            for (var i = 1; i < couponRule.couponExpireType.length; i++) {
                couponRule.couponExpireType[i].disabled = true;
            }
        } else if (e) {
            for (var j = 1; j < couponRule.couponExpireType.length; j++) {
                couponRule.couponExpireType[j].disabled = false;
            }
        }
    }
    $allCouponType.click(allCouponTypeClick);
    allCouponTypeClick();

    var $monthlyPropertyExpireAfter = $("input[name='sceneMode'][value='7']");
    $monthlyPropertyExpireAfter.click(monthlyPropertyExpireAfterClick);
    function monthlyPropertyExpireAfterClick(e) {
        if ($monthlyPropertyExpireAfter[0].checked) {
            couponRule.isDisable(couponRule.all(), true);
            couponRule.isDisable([
                couponRule.monthlyPropertyExpireAfter,
                couponRule.blueParkingId,
                couponRule.parkingId,
                couponRule.parkingName,
                couponRule.userType
            ], false);
            document.getElementById("notMP").disabled = true;
            $monthlyPropertyExpireAfter[0].disabled = false;
        } else if (e) {
            couponRule.isDisable(couponRule.all(), false);
        }
        var $monthlyPropertyLine = $('#monthlyPropertyLine');
        ($monthlyPropertyExpireAfter[0].checked || $("input[name='sceneMode'][value='6']")[0].checked) ? $monthlyPropertyLine.show() : $monthlyPropertyLine.hide();
    }
    monthlyPropertyExpireAfterClick();

    var $monthlyPropertyExpireBefore = $("input[name='sceneMode'][value='6']");
    $monthlyPropertyExpireBefore.click(monthlyPropertyExpireBeforeClick);
    function monthlyPropertyExpireBeforeClick(e) {
        if ($monthlyPropertyExpireBefore[0].checked) {
            couponRule.isDisable(couponRule.all(), true);
            couponRule.isDisable([
                couponRule.monthlyPropertyExpireBefore,
                couponRule.blueParkingId,
                couponRule.parkingId,
                couponRule.parkingName,
                couponRule.userType
            ], false);
            document.getElementById("notMP").disabled = true;
            $monthlyPropertyExpireBefore[0].disabled = false;
        } else if (e) {
            couponRule.isDisable(couponRule.all(), false);
        }
        var $monthlyPropertyLine = $('#monthlyPropertyLine');
        ($monthlyPropertyExpireAfter[0].checked || $monthlyPropertyExpireBefore[0].checked) ? $monthlyPropertyLine.show() : $monthlyPropertyLine.hide();
    }
    monthlyPropertyExpireBeforeClick();

    $("#orderPush").click(orderPushClick);
    function orderPushClick(e) {
        var orderPush = document.getElementById('orderPush');
        if (orderPush.checked) {
            couponRule.isDisable(couponRule.all(), true);
            couponRule.isDisable([couponRule.orderType, couponRule.orderCount], false);
            orderPush.disabled = false;
        } else if (e) {
            couponRule.isDisable(couponRule.all(), false);
        }
        var $orderLine = $('#orderLine');
        orderPush.checked ? $orderLine.show() : $orderLine.hide();
    }
    orderPushClick();

    $("#parkStatus").click(parkStatusCheck);
    function parkStatusCheck(e) {
        var notMP = document.getElementById("notMP");
        var parkStatus = document.getElementById("parkStatus");
        if (parkStatus.checked) {
            notMP.checked = false;
            notMP.disabled = true;
        } else if (e) {
            notMP.disabled = false;
        }
    }
    parkStatusCheck();

    $("#register").click(registerCheck);
    function registerCheck(e) {
        var jPush = document.getElementById("jPush");
        var register = document.getElementById("register");
        if (register.checked) {
            jPush.checked = false;
            jPush.disabled = true;
        } else if (e) {
            jPush.disabled = false;
        }
    }
    registerCheck();

    $("#inPark").click(inParkCheck);
    function inParkCheck() {
        document.getElementById('inParkPushCount').disabled = !document.getElementById('inPark').checked;
    }
    inParkCheck();


    var submitValid = {
        registerSubmit: function () {
            if (!$("input[name='sceneMode'][value='3']")[0].checked) {
                return true;
            }
            if (!$("input[name='userType'][value='1']")[0].checked &&
                !$("input[name='userType'][value='2']")[0].checked &&
                !$("input[name='userType'][value='3']")[0].checked ) {
                alert('请勾选一下用户类型，如：月租、产权用户!');
                return false;
            }
            return true;
        },
        parkStatusSubmit: function () {
            if (!$("input[name='sceneMode'][value='4']")[0].checked) {
                return true;
            }
            if ($("input[name='parkingId']").val().trim().length == 0) {
                alert("选择入场推送，必须要有车场ID");
                return false;
            }
            if (!$("input[name='userType'][value='1']")[0].checked &&
                !$("input[name='userType'][value='2']")[0].checked) {
                alert('请勾选一下用户类型，如：月租、产权用户!');
                return false;
            }
            return true;
        },
        couponExpireAfterSubmit: function () {
            if (!$couponExpireAlert[0].checked) {
                return true;
            }
            var couponExpireBefore = couponRule.couponExpireBefore[0].value;
            if (!(/^\d+$/.test(couponExpireBefore) && couponExpireBefore > 0)) {
                alert('选择优惠券到期前提醒后，必须填写优惠券到期前(天)，且必须大于0!');
                return false;
            }
            var couponTypeNotChecked = true;
            for (var i = 0; i < couponRule.couponExpireType.length; i++) {
                if (couponRule.couponExpireType[i].checked) {
                    couponTypeNotChecked = false;
                    break;
                }
            }
            if (couponTypeNotChecked) {
                alert('至少选择一个优惠券类型!');
                return false;
            }
            return true;
        },
        monthlyPropertyExpireAfterSubmit: function () {
            if (!$monthlyPropertyExpireAfter[0].checked) {
                return true;
            }
            var monthlyPropertyExpireAfter = couponRule.monthlyPropertyExpireAfter[0].value;
            if (!(/^\d+$/.test(monthlyPropertyExpireAfter) && monthlyPropertyExpireAfter > 0)) {
                alert('选择月租、产权过期后提醒，必须填写月租、产权过期日后(天)，且必须大于0!');
                return false;
            }
            if (!$("input[name='userType'][value='1']")[0].checked && !$("input[name='userType'][value='2']")[0].checked) {
                alert('请勾选一下用户类型，如：月租、产权用户!');
                return false;
            }
            return true;
        },
        monthlyPropertyExpireBeforeSubmit: function () {
            if (!$monthlyPropertyExpireBefore[0].checked) {
                return true;
            }
            var monthlyPropertyExpireBefore = couponRule.monthlyPropertyExpireBefore[0].value;
            if (!(/^\d+$/.test(monthlyPropertyExpireBefore) && monthlyPropertyExpireBefore > 0)) {
                alert('选择月租、产权缴费提醒后，必须填写月租、产权到期日前(天)，且必须大于0!');
                return false;
            }
            if (!$("input[name='userType'][value='1']")[0].checked && !$("input[name='userType'][value='2']")[0].checked) {
                alert('请勾选一下用户类型，如：月租、产权用户!');
                return false;
            }
            return true;
        },
        inParkSubmit: function () {
            if (!$('#inPark').get(0).checked) {
                return true;
            }
            var inParkPushCount = $('#inParkPushCount').val();
            if (!(/^\d+$/.test(inParkPushCount) && inParkPushCount > 0)) {
                alert('选择入场后，必须填写推送次数，且必须大于0');
                return false;
            }
            if ($("input[name='blueParkingId']").val().trim().length == 0) {
                alert("选择入场推送，必须要有蓝卡云车场ID");
                return false;
            }
            if (!$("input[name='userType'][value='1']")[0].checked &&
                !$("input[name='userType'][value='2']")[0].checked &&
                !$("input[name='userType'][value='3']")[0].checked ) {
                alert('请勾选一下用户类型，如：月租、产权用户!');
                return false;
            }
            return true;
        },
        orderPushSubmit: function () {
            var orderPush = document.getElementById('orderPush');
            if (!orderPush.checked) {
                return true;
            }
            var orderCount = couponRule.orderCount[0].value;
            if (!(/^\d+$/.test(orderCount) && orderCount > 0)) {
                alert('选择下单推送后，下单次数必须大于0!');
                return false;
            }
            var orderTypes = couponRule.orderType;
            var notChecked = true;
            for (var i = 0; i < orderTypes.length; i++) {
                if (orderTypes[i].checked) {
                    notChecked = false;
                    break;
                }
            }
            if (notChecked) {
                alert('选择下单推送后，必须要选择一种订单类型!');
                return false;
            }
            return true;
        }
    };

    $('#mainForm').submit(function () {
        for (var key in submitValid) {
            if (!submitValid[key]()) return false;
        }
        if (!$('input[name=pushMode][value="1"]')[0].checked && !$('input[name=pushMode][value="2"]')[0].checked) {
            return confirm("推送方式没有勾选，确定要默默的送优惠券也不告诉用户一声么？");
        }
    });

});