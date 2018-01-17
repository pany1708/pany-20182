/*!
 * Bootstrap-table 
 */

//初始化表格参数，只执行一次
function initTable(setting, queryConditon) {

	var tableId = setting.tableId;
	var url = setting.url;
	var method = setting.method;
	var pagination = setting.pagination;

	var clickToSelect = true;
	var singleSelect = false;
	var pageSize = 10;

	if (setting.clickToSelect) {
		clickToSelect = setting.clickToSelect;
	}
	if (setting.singleSelect) {
		singleSelect = setting.singleSelect;
	}
	if (setting.pageSize) {
		pageSize = setting.pageSize;
	}

	$(tableId).bootstrapTable({


		url: url,
		method: method,
		pagination: pagination,//是否分页
		clickToSelect: clickToSelect,      //是否启用点击选中行
		singleSelect: singleSelect,//禁止多选
		toolbar: "#toolbar",      //设置工具栏的Id或者class
		sidePagination: 'server', //设置为服务器端分页
		pageList: [5, 10, 20],
		pageSize: pageSize,
		pageNumber: 1,
		uniqueId: "id",
		silent: true,
		//返回值配置，插件只接受返回值rows,total
		responseHandler: function responseHandler(res) {
			//自定义返回值
			if (res.code == 0) {
				if (pagination) {
					return {
						"rows": res.data.list,//分页数据
						"total": res.data.total//总数量
					};
				} else {
					return {
						"rows": res.data,
					};
				}
			} else {
				return {
					"rows": [],
					"total": 0
				};
			}
		},
		//参数配置
		queryParams: function queryParams(params) {

			var newParams = {};
			if (queryConditon != '' && queryConditon != null) {
				newParams = queryConditon;
			}
			if (params.limit) {
				//页面大小
				newParams.pageSize = params.limit;
				//当前页面索引
				newParams.pageNum = (params.offset / params.limit) + 1;
			}
			return newParams;
		},
		/**单击行事件****/
		onClickRow: function (row, $element) {

		},
		onCheck: function (row) {

		},
		onLoadSuccess: function (data) {

		},
	});
}
//刷新表格数据
function refreshTable(tableId) {
	$(tableId).bootstrapTable('refresh');
}
//查询表格数据
function searchTable(setting, queryConditon) {

	var tableId = setting.tableId;
	var url = setting.url;
	$(tableId).bootstrapTable('refreshOptions', {
		url: url,
		pageNumber: 1,
		//参数配置
		queryParams: function queryParams(params) {

			var newParams = {};
			if (queryConditon != '' && queryConditon != null) {
				newParams = queryConditon;
			}
			if (params.limit) {
				//页面大小
				newParams.pageSize = params.limit;
				//当前页面索引
				newParams.pageNum = (params.offset / params.limit) + 1;
			}
			return newParams;
		},

	});
}
//设置行号
function indexFormatter(value, row, index) {
	return index + 1;
}
/*  以下为自定义部分，应写在相应JS文件中
 //添加操作按钮
 function actionFormatter(value, row, index) {
 var html = '<div>';
 html += '<a class="btnEdit btn"  title="编辑"   ><i  class="glyphicon glyphicon-edit"></i></a>';
 html += '<a class="btnDel  btn"  title="删除"   ><i class="glyphicon glyphicon-trash"></i></a>';
 html += '</div>';
 return [
 html
 ].join('');
 }
 //注册按钮点击事件
 window.operateEvents = {
 'click .btnEdit': function (e, value, row, index) {
 edit(row);
 },
 'click .btnDel': function (e, value, row, index) {
 del(row);
 }
 };
 //编辑
 function edit(row) {
 //row为当前行数据，json数据
 }
 //删除
 function del(row) {
 //row为当前行数据，json数据
 }*/
