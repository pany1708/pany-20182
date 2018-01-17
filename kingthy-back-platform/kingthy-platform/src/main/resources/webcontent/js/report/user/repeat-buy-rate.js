
/**
 * @description：统计报表：网站平均重复购买率
 * @author：lkj
 * @create：2017.7.18
 */
new Vue({
	el: '#vm-repeat-buy-rate',
	//数据
	data: {
		updateTime: '--',//更新时间
	},
	//加载页面时执行
	created: function () {
		this.getReportData();
	},
	methods: {
		//获取报表数据
		getReportData: function () {

			axios.get(zmme + "/userReport/repeatBuyRate").then(response => {
				var result = response.data;
				if (result.code == 0) {
					this.initReport(result.data.rate);
					this.updateTime = result.data.refreshTime;
				} else {
					swal("错误！", result.message, "error");
				}
			})
		},
		//初始化报表
		initReport: function (rate) {

			var val = 100 * rate;
			var chartObj = echarts.init(document.getElementById('repeat-buy-rate'));

			var option = {
				backgroundColor: '#1b1b1b',
				tooltip: {
					formatter: "{a} <br/>{c} {b}"
				},
				toolbox: {
					show: true,
					feature: {
						mark: { show: true },
						restore: { show: true },
						saveAsImage: { show: true }
					}
				},
				series: [
					{
						name: '重复购买率',
						type: 'gauge',
						min: 0,
						max: 100,
						splitNumber: 10,
						radius: '80%',


						axisLine: {            // 坐标轴线
							lineStyle: {       // 属性lineStyle控制线条样式
								color: [[0.09, 'lime'], [0.82, '#1e90ff'], [1, '#ff4500']],
								width: 3,
								shadowColor: '#fff', //默认透明
								shadowBlur: 10
							}
						},
						axisLabel: {            // 坐标轴小标记
							textStyle: {       // 属性lineStyle控制线条样式
								fontWeight: 'bolder',
								color: '#fff',
								shadowColor: '#fff', //默认透明
								shadowBlur: 10
							}
						},
						axisTick: {            // 坐标轴小标记
							length: 15,        // 属性length控制线长
							lineStyle: {       // 属性lineStyle控制线条样式
								color: 'auto',
								shadowColor: '#fff', //默认透明
								shadowBlur: 10
							}
						},
						splitLine: {           // 分隔线
							length: 25,         // 属性length控制线长
							lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
								width: 3,
								color: '#fff',
								shadowColor: '#fff', //默认透明
								shadowBlur: 10
							}
						},
						pointer: {           // 分隔线
							shadowColor: '#fff', //默认透明
							shadowBlur: 5
						},
						title: {
							textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
								fontWeight: 'bolder',
								fontSize: 20,
								fontStyle: 'italic',
								color: '#fff',
								shadowColor: '#fff', //默认透明
								shadowBlur: 10
							}
						},
						detail: {
							backgroundColor: 'rgba(30,144,255,0.8)',
							borderWidth: 1,
							borderColor: '#fff',
							shadowColor: '#fff', //默认透明
							shadowBlur: 5,
							offsetCenter: [0, '50%'],       // x, y，单位px
							textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
								fontWeight: 'bolder',
								color: '#fff'
							}
						},
						data: [{ value: val, name: '重复购买率' }]
					},
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			chartObj.setOption(option);

		}
	}
})

