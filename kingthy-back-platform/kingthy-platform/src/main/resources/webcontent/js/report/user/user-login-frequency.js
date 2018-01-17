
/**
 * @description：统计报表：过去一周用户登录频次分布
 * @author：lkj
 * @create：2017.7.18
 */
$(function () {


	var reportData = [
		{ name: '登录1次', value: 60 },
		{ name: '登录2-3次', value: 20 },
		{ name: '登录4-5次', value: 10 },
		{ name: '登录6次以上', value: 5 },
	];
	var textData = [], valueData = [];

	for (var i in reportData) {

		var row = reportData[i];
		//横轴数据
		textData.push(row.name);
		//纵轴数据
		valueData.push(row.value);
	}
	var chartObj = echarts.init(document.getElementById('user-login-frequency'));


	var option = {
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)",
			
		},
		legend: {
			orient: 'vertical',
			x: 'left',
			data: textData
		},
		series: [
			{
				name: '访问来源',
				type: 'pie',
				radius: ['50%', '70%'],
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: true,
						position: 'inside',
						formatter:'{d}%'
					},
					emphasis: {
						show: true,
						textStyle: {
							fontSize: '30',
							fontWeight: 'bold'
						}
					}
				},
				labelLine: {
					normal: {
						show: false
					}
				},
				data:reportData
			}
		]
	};

	/*var option = {

		title: {
			text: '过去一周用户登录频次分布',
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			x: 'left',
			data: ['登录1次','登录2-3次','登录4-5次','登录6次以上']
		},
		series: [
			{
				name: 'type',
				type: 'pie',
				radius: ['50%', '70%'],
				avoidLabelOverlap: false,
				label: {
					normal: {
						show: true,
						position: 'inner',
						formatter: '{d}%'
					},
					emphasis: {
						show: true,
						textStyle: {
							fontSize: '30',
							fontWeight: 'bold'
						}
					}
				},
				labelLine: {
					normal: {
						show: false
					}
				},
				data: reportData
			}
		]
	};*/



	chartObj.setOption(option);

})