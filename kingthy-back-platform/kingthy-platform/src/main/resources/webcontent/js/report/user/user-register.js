/**
 * @description：统计报表：过去一周每日注册用户数
 * @author：lkj
 * @create：2017.7.18
 */
 new Vue({
	el: '#vm_user_gister',
	//数据
	data: {
		updateTime: '--',//更新时间
	},
	//加载页面时执行
	created: function () {
		this.getReportData();
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
		initReport: function (list) {

			var myChart = echarts.init(document.getElementById('user-register'));

			var reportData = [], xdata = []

			for (var i in list) {
				var item = list[i];
				var rtpItem = { name: item.registerDate, value: item.number };
				reportData.push(rtpItem);
				xdata.push(item.registerDate);
			}
			// 指定图表的配置项和数据
			var option = {
				title: {
					text: '过去一周每日注册用户数'
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
					name: "用户数量",
					minInterval:1
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
		}
	}
})



