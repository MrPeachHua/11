$(function () {
    $(".commentTags").each(function () {
        var text = $(this).text();
        if (text.trim().length > 0) {
            var html = '';
            var tags = text.split('=');
            for (var i = 0; i < tags.length; i++) {
                var item = tags[i];
                if (item.trim().length > 0) {
                    var span = '';
                    if (item.endsWith("0")) {
                        span = '<span class="commentTagDown"></span>';
                    } else if (item.endsWith("1")) {
                        span = '<span class="commentTagUp"></span>';
                    }
                    item = item.substr(0, item.length - 1);
                    html += '<div class="commentTag">' + item + span + '</div><br/>';
                }
            }
            $(this).html(html);
        }
    });
});