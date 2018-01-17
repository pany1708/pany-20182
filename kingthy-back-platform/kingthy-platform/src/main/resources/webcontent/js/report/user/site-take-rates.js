
/**
 * @description：统计报表：网站转化效果
 * @author：lkj
 * @create：2017.7.18
 */
$(function () {

	var reportData = [
		{ name: '成功购买', value: 100 },
		{ name: '下单', value: 200 },
		{ name: '购物车', value: 300 },
		{ name: '试穿', value: 10 },
		{ name: '注册', value: 600 },
		{ name: '访问', value: 400 },
	];
	var itemName = [];
	for (var i in reportData) {

		var row = reportData[i];
		//横轴数据
		itemName.push(row.name);
	}
	//转化率
	var ratesData = [

		{ name: '成功购买/下单', value: 20 },
		{ name: '下单/购物车', value: 40 },
		{ name: '购物车/试穿', value: 10 },
		{ name: '试穿/注册', value: 20 },
		{ name: '注册/访问', value: 60 },
		{ name: '-----', value: 60 },

	];
	var ratesItem = [];
	for (var i in ratesData) {

		var row = ratesData[i];
		//横轴数据
		ratesItem.push(row.name);
	}
	var chartObj = echarts.init(document.getElementById('site-take-rates'));

	var option = {
		
		title: {
			text: '网站转化效果',
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: '#283b56'
				}
			}
		},
		legend: {
			data: ['用户数量', '转化率']
		},
		toolbox: {
			show: true,
			feature: {
				dataView: { readOnly: false },
				restore: {},
				saveAsImage: {}
			}
		},
		dataZoom: {
			show: false,
			start: 0,
			end: 100
		},
		xAxis: [
			{
				type: 'category',
				boundaryGap: true,
				data: itemName,
				boundaryGap: [0, 0]

			},
			{
				type: 'category',
				boundaryGap: true,
				data: ratesItem,
				boundaryGap: [100, 100]
			}
		],
		yAxis: [
			{
				type: 'value',
				scale: true,
				name: '转化率',
				max: 100,
				min: 0,
				splitNumber: 5,
				boundaryGap: [0.2, 0.2]
			},
			{
				type: 'value',
				scale: true,
				name: '用户数量',
				//max: 1200,
				min: 0,
				//splitNumber: 4,
				boundaryGap: [0.2, 0.2]
			}
		],
		series: [
			{
				name: '用户数量',
				type: 'bar',
				xAxisIndex: 1,
				yAxisIndex: 1,
				data: reportData
			},
			{
				name: '转化率',
				type: 'line',
				data: ratesData
			}
		]
	};

	chartObj.setOption(option);


})