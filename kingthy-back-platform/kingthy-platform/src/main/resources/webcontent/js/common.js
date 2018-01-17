//请求域名
var zmme = "http://192.168.7.200:7081";

//文件上传/下载服务地址
var fileServiceURL = "http://192.168.7.200:7903";
//请求操作人员
var memberUuid = "97071061572518195";

//通过参数名获取url地址中的参数值
function getUrlParmsByName(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}
//把URL参数转为Json字符串
function getUrlParms(url) {
    var obj = {};
    var keyvalue = [];
    var key = "", value = "";
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    for (var i in paraString) {
        keyvalue = paraString[i].split("=");
        key = keyvalue[0];
        value = keyvalue[1];
        obj[key] = value;
    }
    return obj;
}
//把当前页面的URL参数转为Json字符串
function getLocalUrlParms() {
    var obj = {};
    var keyvalue = [];
    var key = "", value = "";
    var url = window.location.search;
    var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
    for (var i in paraString) {
        keyvalue = paraString[i].split("=");
        key = keyvalue[0];
        value = keyvalue[1];
        obj[key] = value;
    }
    return obj;
}

/*弹出层*/
/*
 参数解释：
 title	标题
 url		请求的url
 id		需要操作的数据id
 w		弹出层宽度（缺省调默认值）
 h		弹出层高度（缺省调默认值）
 fun		弹出层销毁回调方法
 */
function layer_show(title, url, w, h, fun) {
    if (title == null || title == '') {
        title = false;
    }
    ;
    if (url == null || url == '') {
        url = "404.html";
    }
    ;
    if (w == null || w == '') {
        w = 800;
    }
    ;
    if (h == null || h == '') {
        h = ($(window).height() - 50);
    }
    ;
    layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false, //不固定
        maxmin: true,
        shade: 0.4,
        title: title,
        content: [url, 'no'],
        end: fun
    });
}
/*关闭弹出框口*/
function layer_close() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
/*最大化layer*/
function layer_full(type, title, content) {
    var index = layer.open({
        type: type,
        title: title,
        content: content
    });
    layer.full(index);
}
//删除json 中的空属性
function deleteEmptyProperty(object){
    for (var i in object) {
        var value = object[i];
        if (typeof value === 'object') {
            if (Array.isArray(value)) {
                if (value.length == 0) {
                    delete object[i];
                    continue;
                }
            }
            deleteEmptyProperty(value);
            if (isEmpty(value)) {
                delete object[i];
            }
        } else {
            if (value === '' || value === null || value === undefined) {
                delete object[i];
            } else {
            }
        }
    }
}
function isEmpty(object) {
    for (var name in object) {
        return false;
    }
    return true;
}
// 文件单个上传
function readAsDataURL(target, isImg, storeField) {
    var name = target.value;
    var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
    if (isImg && fileName != "jpg" && fileName != "jpeg" && fileName != "png") {
        swal("请选择图片格式文件上传(jpg,png)！", "", "info");
        return;
    }
    var file = target.files[0];
    var reader = new FileReader();
    //将文件以Data URL形式读入页面
    reader.readAsDataURL(file);
    reader.onload = function (e) {
        var obj = {
            "fileData": this.result.split(",")[1],
            "fileName": file.name,
            "funcFlag": ""
        };
        var param = JSON.stringify(obj);
        $.ajax({
            url: fileServiceURL + "/fileUpload/upload",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: param,
            success: function (data) {
                if (data.code == 0) {
                    if (isImg)
                        vm.imageUrl = data.data.fileUrl;
                    else {
                        switch (storeField) {
                            case 0:
                                vm.data.dataFile = data.data.fileUrl;
                                break;
                            case 1:
                                vm.data.chartletFile = data.data.fileUrl;
                                break;
                        }

                    }
                } else {
                    swal("上传失败！", "", "error");
                }
            },
            complete: function () {

            },
            error: function () {
                swal("上传失败！", "", "error");
            }
        })
    }
}