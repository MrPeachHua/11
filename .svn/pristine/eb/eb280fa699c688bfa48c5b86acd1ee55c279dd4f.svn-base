function exportExcel(url) {
    var params = '?';
    $("input[type='text'][name^='p_'], select[name^='p_']").each(function () {
        var name = $(this).attr('name');
        var value = $(this).val();
        params += (name + '=' + value + '&');
    });
    window.location.href = url + params;
}
function clearParams() {
    $("input[type='text'][name^='p_'], select[name^='p_']").val('');
}