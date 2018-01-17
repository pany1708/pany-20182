/**
 * @description：统计报表：过去一周多次登录用户数
 * @author：lkj
 * @create：2017.7.18
 */
$(function () {

	// 基于准备好的dom，初始化echarts实例
	var chartObj = echarts.init(document.getElementById('user-many-login'));

	var reportData = [{ date: '2017.1.1', value: '10' }, { date: '2017.1.2', value: '20' }, { date: '2017.1.3', value: '30' }, { date: '2017.1.4', value: '40' }];

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
		//标题
		title: {
			text: '过去一周多次登录用户数'
		},
		//图例
		legend: {},
		//x轴
		xAxis: {
			name: '访问量',
		},
		//y轴
		yAxis: {
			name: '日期',
			data: xdata
		},
		//系列列表
		series: [{
			type: 'bar',
			data: reportData,
			itemStyle: {
				normal: {
					//定义一个list，然后根据所以取得不同的值，这样就实现了，
					color: function (params) {
						// build a color map as your need.
						var colorList = ['#26C0C0'
							//'#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0',
							//'#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
							//'#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
							
						];
						return colorList[params.dataIndex]
					},
				}
			},
			//设置柱的宽度，要是数据太少，柱子太宽不美观~
			barWidth: 30,
		}]
	};
	// 使用刚指定的配置项和数据显示图表。
	chartObj.setOption(option);
})