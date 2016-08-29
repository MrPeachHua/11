var dictionary = {
    requestData: function (dictCode, dictValue, callback) {
        var url = '/share/system/dict/queryDictionary?dictCode=' + dictCode;
        if (dictValue != null) {
            url += '&dictValue=' + dictValue;
        }
        $.get(url, function (result) {
            result = $.parseJSON(result);
            if (result.errorNum == '0') {
                callback(result.data);
            }
        });
    },
    appendOption: function (data, select, currentValue) {
        for (var i = 0; i < data.length; i++) {
            var item = data[i];
            var option;
            if (currentValue != null && currentValue == item.dictValue) {
                option = '<option selected="selected" value="' + item.dictValue + '">' + item.dictName + '</option>';
            } else {
                option = '<option value="' + item.dictValue + '">' + item.dictName + '</option>';
            }
            $(select).append(option);
        }
    }
};
$(function () {
    $('.dictionary').each(function (index, element) {
        var dictCode = this.dataset.dictCode ? this.dataset.dictCode : $(this).attr('data-dict-code');
        var dictValue = this.dataset.dictValue ? this.dataset.dictValue : $(this).attr('data-dict-value');
        var currentValue = this.dataset.currentValue ? this.dataset.currentValue : $(this).attr('data-current-value');
        dictionary.requestData(dictCode, dictValue, function (data) {
            dictionary.appendOption(data, element, currentValue);
        });
    });
});