<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <style type="text/css">
        html, body {
            width: 100%;
            margin: 0px;
            padding: 0px;
            border: 0px;
            overflow-x: hidden;
            font-size: 16px;
            background-color: #FBFBFC;
            -webkit-overflow-scrolling: touch;
        }

        @media (max-width: 300px) {
            html, body {
                width: 300px;
                overflow-x: scroll;
            }
        }

        #main {
            width: 80%;
            margin: auto;
            padding-bottom: 10px;
        }

        #main .text_field {
            font-size: 16px;
            width: 100%;
            outline: none;
            font-weight: 500;
            border: solid 1px deepskyblue;
            border-radius: 10em;
            -moz-border-radius: 10em;
            -o-border-radius: 10em;
            -webkit-border-radius: 10em;
            margin: 7px 0px;
            background: no-repeat #ffffff;
            -webkit-tap-highlight-color: transparent;
        }

        #main .phone {
            padding: 10px 10px 10px 60px;
            background: url("../images/register_02.png") no-repeat -65px -25px #ffffff;
        }

        #main input[type="password"] {
            padding: 10px 10px 10px 60px;
            background: url("../images/register_05.png") no-repeat -65px -23px #ffffff;
        }

        #main .valid_code {
            padding: 10px 60px 10px 25px;
        }

        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none !important;
            margin: 0;
        }

        #main .valid {
            position: relative;
        }

        #main .valid_btn {
            position: absolute;
            top: 15px;
            right: 7px;
            border-left: solid 1px #A9A9A9;
        }

        #main .valid_btn input[type="button"] {
            border: none;
            color: deepskyblue;
            font-size: 16px;
            background-color: #ffffff;
            text-decoration: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            -webkit-tap-highlight-color: transparent;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
        }

        #main a {
            -webkit-tap-highlight-color: transparent;
            -webkit-touch-callout: none;
            -webkit-user-select: none;
        }
    </style>
</head>
<body>

<div id="top">
    <img width="100%" src="../images/register_01.png">
</div>
<div id="bottom">
    <div id="main">
        <input class="text_field phone" type="tel" placeholder="请输入手机号">
        <input class="text_field" type="password" placeholder="请输入8-20位密码">

        <div class="valid">
            <input class="text_field valid_code" type="tel" placeholder="请输入验证码">

            <div class="valid_btn">
                <input type="button" id="btn" value="获取验证码" onclick="getValid(this)"/>
            </div>
        </div>

        <div style="text-align: center;">
            <a href="javascript:alert('success');"><img width="70px" height="70px" src="../images/register_13.png"></a>
            <%--<input width="70px" height="70px" type="image" src="../images/register_13.png">--%>
        </div>
    </div>
</div>

<script type="text/javascript">
    var countdown = 60;
    function setTime(val) {
        if (countdown == 0) {
            val.style.color = "#00BFFF";
            val.removeAttribute("disabled");
            val.value = "获取验证码";
            countdown = 60;
            return;
        }
        val.style.color = "#A9A9A9";
        val.setAttribute("disabled", true);
        val.value = "重新发送(" + countdown + ")";
        countdown--;
        setTimeout(function () {
            setTime(val)
        }, 1000)
    }

    function getValid(obj) {
        alert("验证码：2222");
        setTime(obj);
    }
</script>

</body>
</html>
