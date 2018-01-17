
/**
 * @description：统计报表：不同年龄分段购买情况
 * @author：lkj
 * @create：2017.7.18
 */
new Vue({
	el: '#vm-different-age-buy',
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
			axios.get(zmme + "/userReport/findBuyDataByAge").then(response => {
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
			var echartsObj = echarts.init(document.getElementById('different-age-buy'));

			//转换为插件报表数据结构
			var xdata = [], reportData = [];

			for (var i in list) {
				var row = list[i];
				var item = { 'name': row.ageGroup, 'value': (row.money / 10000).toFixed(2) };
				//var item = { 'name': row.ageGroup, 'value': row.money };
				reportData.push(item)
				//横轴数据
				xdata.push(row.ageGroup);
			}

			// 指定图表的配置项和数据
			var option = {
				//标题
				title: {
					text: '不同年龄分段购买情况'
				},
				//工具箱
				toolbox: {
					feature: {
						//保存图像按钮
						saveAsImage: {}
					}
				},
				//图例
				legend: {},
				//x轴
				xAxis: {
					name: '年龄',
					data: xdata
				},
				//y轴
				yAxis: {
					name: '单位：万',
					splitNumber: 4
			
					//interval:900,
					//data:['900','1.8','2.7','3.6','4.5']
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
								var colorList = [
									'#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
									'#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
									'#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
								];
								return colorList[params.dataIndex]
							},
							//以下为是否显示，显示位置和显示格式的设置了
							label: {
								show: true,
								position: 'top',
								formatter: '{c}'
							}
						}
					},
					//设置柱的宽度，要是数据太少，柱子太宽不美观~
					barWidth: 70,
				}]
			};
			// 使用刚指定的配置项和数据显示图表。
			echartsObj.setOption(option);

		}
	}
})
