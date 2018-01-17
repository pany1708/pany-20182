
/**
 * @description：统计报表：过去一周活跃用户分布
 * @author：lkj
 * @create：2017.7.18
 */


/**********Vue*********** */
var vm_active_users = new Vue({
	el: '#vm-active-users-distribution',
	//数据
	data: {
		//定义用于绑定的一个对象
		list: [],
		table1: [],//数据列表
		table2: [],//数据列表
		updateTime: '2017.7.19'
	},
	//加载页面时执行
	created: function () {
		//初始化部分参数值
		this.list = [
			{ name: '浙江', value:100,buyers:1 },
			{ name: '安徽', value:500,buyers:2 },
			{ name: '广西', value:20, buyers:1},
			{ name: '福建', value:400,buyers:1 },
			{ name: '台湾', value:250,buyers:2 },
			{ name: '香港', value:600,buyers:1 },
			{ name: '新疆', value:300,buyers:1 }
		]
	}
})
/********************* */

$(function () {

	var echartsObj = echarts.init(document.getElementById('active-users-distribution'));
    

    var max=500;
	var option = {
		title: {
			text: '过去一周活跃用户分布',
			left: 'left'
		},
		tooltip: {
			trigger: 'item'
		},
		visualMap: {
			type: 'piecewise',
			pieces: [
				{ gt: max * 3/4, lte: max, color: '#f6701c' },
				{ gt: max * 2/4, lte: max * 3/4, color: '#74c065' },
				{ gt: max * 1/4, lte: max * 2/4, color: '#6cc9e3' },
				{ gt: 0, lte: max * 1/4, color: '#beede5' },
				//{ lt: 10 }                 // (-Infinity, 5)
			],
			left: 'left',
			top: 'bottom',
			text: ['高', '低'],           // 文本，默认为数值文本
		},
		toolbox: {
			show: true,
			orient: 'vertical',
			left: 'left',
			top: 'center',
			feature: {
				dataView: { readOnly: false },
				restore: {},
				saveAsImage: {}
			}
		},
		series: [
			{
				name: '访客数',
				type: 'map',
				mapType: 'china',
				roam: false,
				label: {
					normal: {
						show: true
					},
					emphasis: {
						show: true
					}
				},
				data: vm_active_users.list
			}
		]
	};
	// 使用刚指定的配置项和数据显示图表。
	echartsObj.setOption(option);
})
