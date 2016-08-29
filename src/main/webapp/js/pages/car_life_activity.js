$(function () {
    function typeChange() {
        var type = $('select[name=type]')[0];
        var startDate = $('input[name=startDate]')[0];
        var endDate = $('input[name=endDate]')[0];
        if (type.value == '1') {
            startDate.disabled = false;
            endDate.disabled = false;
        } else if (type.value == '2') {
            startDate.value = "";
            endDate.value = "";
            startDate.disabled = true;
            endDate.disabled = true;
        }
    }
    $('select[name=type]').change(typeChange);
    typeChange();
    var um = UM.getEditor('myEditor');
    var contentType1 = $('input[type=radio][name=contentType][value="1"]')[0];
    function contentTypeChange() {
        var urlInput = $('input[name=url]')[0];
        if (contentType1.checked) {
            urlInput.removeAttribute("required");
            urlInput.readOnly = true;
            urlInput.style.backgroundColor = '#EBEBE4';
            //urlInput.value = "";
            um.setShow();
        } else {
            urlInput.setAttribute("required", "required");
            urlInput.readOnly = false;
            urlInput.style.backgroundColor = '#FFFFFF';
            um.setHide();
        }
    }
    $('input[type=radio][name=contentType]').click(contentTypeChange);
    contentTypeChange();
    $('form').submit(function () {
        if (contentType1.checked) {
            var html = um.getContent();
            $('input[name=html]').val(html);
        }
        return checkForm('form');
    });
});
