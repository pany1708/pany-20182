
// /****
//  * 公共js导入
//  *
//  */
// document.write('<script type="text/javascript" src="../../js/jquery.min.js?v=2.1.4"></script>');
// document.write('<script type="text/javascript" src="../../js/bootstrap.min.js"></script>')
// /****弹出层插件 */
// document.write('<script type="text/javascript" src="../../plugins/layer/2.4/layer.js"></script>')
// /****公共js */
// document.write('<script type="text/javascript" src="../../js/common.js"></script>')
// /****用于构建用户界面，view层，数据请求需要其他框架 */
// document.write('<script type="text/javascript" src="../../js/vue.min.js"></script>')
// /****数据请求，路由，拦截器 */
// document.write('<script type="text/javascript" src="../../js/axios.min.js"></script>')
// document.write('<script type="text/javascript" src="../../js/axios-config.js"></script>')
// /****轻巧的消息提示框 */
// document.write('<script type="text/javascript" src="../../plugins/sweetalert/sweetalert.min.js"></script>')

/**
 * 引用JS和CSS头文件
 */
var rootPath = getRootPath(); //项目路径

/**
 * 动态加载CSS和JS文件
 */
var dynamicLoading = {
    meta: function () {
        document.write('<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">');
    },
    css: function (path) {
        if (!path || path.length === 0) {
            throw new Error('argument "path" is required!');
        }
        document.write('<link rel="stylesheet" type="text/css" href="' + path + '">');
    },
    js: function (path, charset) {
        if (!path || path.length === 0) {
            throw new Error('argument "path" is required!');
        }
        document.write('<script charset="' + (charset ? charset : "utf-8") + '" src="' + path + '"></script>');
    }
};

/**
 * 取得项目路径
 * @author wul
 */
function getRootPath() {
    //取得当前URL
    var path = window.document.location.href;
    //取得主机地址后的目录
    var pathName = window.document.location.pathname;
    var post = path.indexOf(pathName);
    //取得主机地址
    var hostPath = path.substring(0, post);
    //取得项目名
    var name = pathName.substring(0, pathName.substr(1).indexOf("/") + 1);
    return hostPath + name + "/";
}

//动态生成meta
dynamicLoading.meta();

//动态加载项目 JS文件
dynamicLoading.js(rootPath + "/js/jquery.min.js?v=2.1.4", "utf-8");
dynamicLoading.js(rootPath + "/js/bootstrap.min.js", "utf-8");
dynamicLoading.js(rootPath + "/plugins/layer/2.4/layer.js", "utf-8");
dynamicLoading.js(rootPath + "/js/common.js", "utf-8");
dynamicLoading.js(rootPath + "/js/vue.min.js", "utf-8");
dynamicLoading.js(rootPath + "/js/axios.min.js", "utf-8");
dynamicLoading.js(rootPath + "/js/axios-config.js", "utf-8");
dynamicLoading.js(rootPath + "/plugins/sweetalert/sweetalert.min.js", "utf-8");

