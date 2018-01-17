
/**
 * @description：订单统计：不同终端数据
 * @author：lkj
 * @create：2017.7.27
 */
new Vue({
	el: '#vm_differ_terminal',
	//数据
	data: {
		list: [
			{ value: 335, name: 'PC' },
			{ value: 310, name: '移动端' }
		],
		updateTime: '2017.7.27',//更新时间
	},
	//加载页面时执行
	created: function () {

	},
	//挂载完成时执行
	mounted: function () {
		this.initReport(this.list);
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
		initReport: function (reportData) {

			var chartObj = echarts.init(document.getElementById('differ_terminal'));

			var legend_data = [];
			for (var i in reportData) {
				var row = reportData[i];
				legend_data.push(row.name);
			}
			// 指定图表的配置项和数据

			var option = {
				title: {
					text: '不同终端数据',
					x: 'left'
				},
				//工具箱
				toolbox: {
					feature: {
						//保存图像按钮
						saveAsImage: {}
					}
				},
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'horizontal',
					top: 'bottom',
					data: legend_data
				},
				series: [
					{
						name: '总金额',
						type: 'pie',

						radius: '55%',
						center: ['50%', '60%'],
						data: reportData,
						itemStyle: {

							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}
				]
			};
			// 使用刚指定的配置项和数据显示图表。

			chartObj.setOption(option);
		}
	}
})
