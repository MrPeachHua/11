(function (window, $) {
    var document = window.document,
        fade = C('modal fade'),
        dialog = C('modal-dialog'),
        content = C('modal-content'),
        header = C('modal-header'),
        lblClose = C('close', 'button', {'type': 'button', 'data-dismiss': 'modal', 'aria-hidden': 'true'}),
        title = C('modal-title', 'h4'),
        body = C('modal-body'),
        footer = C('modal-footer'),
        btnClose = C('btn btn-default', 'button', {'data-dismiss': 'modal'}),
        btnEnter = C('btn btn-primary', 'button'),
        obj = {$id: null, $name: null},
        data = null;
    fade.appendChild(dialog);
    dialog.appendChild(content);
    content.appendChild(header);
    header.appendChild(lblClose);
    header.appendChild(title);
    content.appendChild(body);
    content.appendChild(footer);
    footer.appendChild(btnClose);
    footer.appendChild(btnEnter);
    lblClose.innerHTML = '&times;';
    title.innerHTML = '选择所属系统';
    btnClose.innerHTML = '关闭';
    btnEnter.innerHTML = '确定';
    btnEnter.onclick = function () {
        var name = '';
        var id = '';
        $(body).find('input').each(function () {
            if (this.checked) {
                id += this.value + ',';
                name += $(this).next().text() + ',';
            }
        });
        if (id.endsWith(',')) id = id.substring(0, id.length - 1);
        if (name.endsWith(',')) name = name.substring(0, name.length - 1);
        obj.$name.val(name);
        obj.$id.val(id);
        $(fade).modal('toggle');
        obj.$id = null;
        obj.$name = null;
    };
    function C(clazz, tagName, attr) {
        var element = document.createElement(tagName == null ? 'div' : tagName);
        element.setAttribute('class', clazz);
        if (attr != null) {
            for (var key in attr) {
                element.setAttribute(key, attr[key]);
            }
        }
        return element;
    }
    function requestData() {
        var url = '/share/system/dict/queryDictionary?dictCode=module';
        $.get(url, function (result) {
            result = $.parseJSON(result);
            if (result.errorNum == '0') {
                data = result.data;
                layout();
            }
        });
    }
    function layout() {
        var html = '<ul>';
        var idArray = obj.$id.val().split(',');
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            if ($.inArray(item.dictValue, idArray) == -1) {
                html += '<li><input type="checkbox" value="' + item.dictValue + '"><span>' + item.dictName + '</span></li>';
            } else {
                html += '<li><input checked="checked" type="checkbox" value="' + item.dictValue + '"><span>' + item.dictName + '</span></li>';
            }
        }
        html += '</ul>';
        body.innerHTML = html;
    }
    window.moduleView = {
        show: function ($id, $name) {
            obj.$id = $id;
            obj.$name = $name;
            data == null ? requestData() : layout();
            $(fade).modal('toggle');
        }
    };
})(window, $);