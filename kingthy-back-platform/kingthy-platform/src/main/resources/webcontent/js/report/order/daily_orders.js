
/**
 * @description：统计报表：每日订单统计
 * @author：lkj
 * @create：2017.7.27
 */
new Vue({
	el: '#vm_daily_orders',
	//数据
	data: {
		updateTime: '2017.7.27',//更新时间
	},
	//加载页面时执行
	created: function () {
		
	},
	//挂载完成时执行
	mounted:function(){
		this.initReport();
	},
	methods: {

		getReportData: function () {
			axios.get(zmme + "/userReport/newUserNum/").then(response => {
				var result = response.data;
				if (result.code == 0) {
					this.initReport(result.data);
					if (result.data.length > 0) {
						this.updateTime = result.data[0].refreshTime;
					}
				} else {
					swal("错误！", result.message, "error");
				}
			})
		},

		//初始化报表
		initReport: function () {

			var chartObj = echarts.init(document.getElementById('daily_orders'));
			
			var option = {
				title: {
					text: '每日订单统计',
					left: 'left'
				},
				tooltip: {
					trigger: 'axis'
				},
				toolbox: {
					show: true,
					feature: {
						mark: { show: true },
						dataView: { show: true, readOnly: false },
						magicType: { show: true, type: ['line', 'bar'] },
						restore: { show: true },
						saveAsImage: { show: true }
					}
				},
				calculable: true,
				legend: {
					data: ['每日订单量', '每日订单环比']
				},
				xAxis: [
					{
						type: 'category',
						data: ['2017.7.1', '2017.7.2', '2017.7.3', '2017.7.4', '2017.7.5', '2017.7.6', '2017.7.7']
					}
				],
				yAxis: [
					{
						type: 'value',
						name: '每日订单量',
						axisLabel: {
							formatter: '{value} '
						}
					},
					{
						type: 'value',
						name: '每日订单环比',
						axisLabel: {
							formatter: '{value} %'
						}
					}
				],
				series: [

					{
						name: '每日订单量',
						type: 'bar',
						barWidth: 30,
						itemStyle: {
							normal: {
								color: '#3398DB',
								//以下为是否显示，显示位置和显示格式的设置了
								label: {
									show: true,
									position: 'top',
									formatter: '{c}'
								}
							}
						},
						data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6]
					},
					{
						name: '每日订单环比',
						type: 'line',
						yAxisIndex: 1,
						data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3]
					}
				]
			};
			chartObj.setOption(option);
		}
	}
})
