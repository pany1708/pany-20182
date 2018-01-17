
/**
 * @description：统计报表：过去一周每日全站PV/UV
 * @author：lkj
 * @create：2017.7.18
 */
$(function () {

	// 基于准备好的dom，初始化echarts实例
	var chartObj = echarts.init(document.getElementById('site-pv-uv'));

	var reportData = [
		{ date: '2017.1.1', pv: 1000, uv: 10000 },
		{ date: '2017.1.2', pv: 3000, uv: 15000 },
		{ date: '2017.1.3', pv: 2220, uv: 20000 },
		{ date: '2017.1.4', pv: 5000, uv: 13000 }];

	var xdata = [], ydata_pv = [], ydata_uv = [];

	for (var i in reportData) {
		var row = reportData[i];
		//横轴数据
		xdata.push(row.date);
		//纵轴数据
		ydata_pv.push(row.pv);
		ydata_uv.push(row.uv);
	}
	// 指定图表的配置项和数据
	var option = {
		//标题
		title: {
			text: '过去一周每日全站PV/UV情况'
		},
		//工具箱
		toolbox: {
			feature: {
				//保存图像按钮
				saveAsImage: {}
			}
		},
		legend: {
			data: ['PV','UV']
		},
		//x轴
		xAxis: {
			name: '日期',
			type: 'category',
			data: xdata
		},
		//y轴
		yAxis: {

		},
		//系列列表
		series: [
			{
				type: 'bar',
				name:'PV',
				data: ydata_pv,
				itemStyle: {
					normal: {
						color: '#3f67d2',
						//以下为是否显示，显示位置和显示格式的设置了
						label: {
							show: true,
							position: 'top',
							formatter: '{b}\n{c}'
						}
					}
				},
				//设置柱的宽度，要是数据太少，柱子太宽不美观~
				barWidth: 30,
			},
			{
				type: 'bar',
				name:'UV',
				data: ydata_uv,
				itemStyle: {
					normal: {
						color: '#274552',
						//以下为是否显示，显示位置和显示格式的设置了
						label: {
							show: true,
							position: 'top',
							formatter: '{b}\n{c}'
						}
					}
				},
				//设置柱的宽度，要是数据太少，柱子太宽不美观~
				barWidth: 30,
			}
		]
	};
	// 使用刚指定的配置项和数据显示图表。
	chartObj.setOption(option);
})