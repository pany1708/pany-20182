/**
 * @description：统计报表：过去一周每日活跃用户数
 * @author：lkj
 * @create：2017.7.18
 */
$(function () {

	var myChart = echarts.init(document.getElementById('day-active-users'));

	var reportData = [
		{ date: '2017.1.1', value: '10' },
		{ date: '2017.1.2', value: '20' },
		{ date: '2017.1.3', value: '30' },
		{ date: '2017.1.4', value: '40' },
		{ date: '2017.1.5', value: '60' },
		{ date: '2017.1.6', value: '100'},
		{ date: '2017.1.7', value: '80' }
	];
	var xdata = [], ydata = [];
	for (var i in reportData) {
		var row = reportData[i];
		//横轴数据
		xdata.push(row.date);
		//纵轴数据
		ydata.push(row.value);
	}
	// 指定图表的配置项和数据
	var option = {
		title: {
			text: '过去一周每日活跃用户数'
		},
		tooltip: {
			trigger: 'axis'
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		toolbox: {
			feature: {
				saveAsImage: {}
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: xdata
		},
		yAxis: {
			name:"用户数量"
		},
		series: [
			{
				name: 'value',
				type: 'line',
				stack: '总量',
				data: reportData
			}

		]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
})