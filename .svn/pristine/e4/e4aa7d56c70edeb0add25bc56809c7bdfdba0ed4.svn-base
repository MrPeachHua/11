<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="bgTransparent" id="bgTransparent"></div>
<div class="importView" id="importView">
    <div class="importHead">
        <img src="<%=basePath%>/images/new/close.png" class="importCancel"/>
    </div>
    <div class="importBody" id="importBody">
    </div>
    <div class="importFoot">
        <input type="button" class="greenBtn importCancel" value="取&nbsp;消"/>
    </div>
</div>
<script type="text/javascript">
    function importExcel(url) {
        $('#importBody').html(
                "<div class=\"importContent\">\n" +
                "   <p>选择上传文件</p>\n" +
                "</div>\n" +
                "<input data-url=\"" + url + "\" class=\"fileInput\" type=\"file\" id=\"importFile\" name=\"importFile\"/>");
        $('#bgTransparent').show();
        $('#importView').show();
    }
    $('.importCancel').click(function () {
        $('#bgTransparent').hide();
        $('#importView').hide();
        location.reload();
    });
    $(document).on("change", "#importFile", function () {
        var filePath = $(this).val().trim();
        var extension = filePath.substr(filePath.lastIndexOf('.') + 1).toLowerCase();
        if (extension != 'xls' && extension != 'xlsx') {
            alert('格式不正确，只支持xls和xlsx格式！');
            return;
        }
        $('#importBody').append(
                "<div style='text-align: center;'>\n" +
                "   <p>上传中...请稍后...</p>\n" +
                "</div>");
        $('#importBody .importContent').hide();
        $.ajaxFileUpload({
            type: "POST",
            url: this.dataset.url,
            data: {},
            secureuri: false,
            fileElementId: "importFile",
            dataType: 'json',
            success: function (jsonStr) {
                if (jsonStr.errorNum == '1') {
                    $('#importBody').html(
                            "<div style='text-align: center;'>\n" +
                            "   <p>上传成功！</p>\n" +
                            "   <p>错误日志：<a href='<%=basePath%>products/villageowner/logDownload.html?fileName=" + jsonStr.data + "'>点击下载</a></p>\n" +
                            <%--"   <p>直接查看：<a href='<%=basePath%>uploadFiles/excel/" + jsonStr.data + "'>点击查看</a></p>\n" +--%>
                            "</div>");
                } else if (jsonStr.errorNum == '0') {
                    $('#importBody').html(
                            "<div style='text-align: center;'>\n" +
                            "   <p>上传成功，无错误！</p>\n" +
                            "</div>");
                } else {
                    $('#importBody').html(
                            "<div style='text-align: center;'>\n" +
                            "   <p>异常！</p>\n" +
                            "</div>");
                }
            },
            error: function () {
                $('#importBody').html(
                        "<div style='text-align: center;'>\n" +
                        "   <p>出错了，服务器异常！</p>\n" +
                        "</div>");
            }
        });
    });
</script>