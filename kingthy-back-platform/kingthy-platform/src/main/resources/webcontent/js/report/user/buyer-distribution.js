
/**
 * @description：统计报表：过去一周活跃用户分布
 * @author：lkj
 * @create：2017.7.18
 */

/**********Vue*********** */
new Vue({
	el: '#vm-buyer-distribution',
	//数据
	data: {
		table1: [],//数据列表
		table2: [],//数据列表
		updateTime: '2017.7.19',//更新时间
	},
	//加载页面时执行
	created: function () {
		this.getReportData();
	},
	methods: {
		//获取报表数据
		getReportData: function () {
			axios.get(zmme + "/userReport/findOrdersByArea").then(response => {
				var result = response.data;
				if (result.code == 0) {
					var data = result.data;
					var arealist=[],max=0;
					if (data.length > 0) {
						this.updateTime = data[0].refreshTime;
						max= data[0].num;
					}
					for (var i in data) {
						var item = data[i];
						//转换本地地图数据
						var mapItem = { name: getProvinceNameById(item.province), value: item.num }
						arealist.push(mapItem);
						if (i < 17) {
							this.table1.push(mapItem);
						} else {
							this.table2.push(mapItem);
						}
					}
					this.intiReport(arealist,max);
				} else {
					swal("错误！", result.message, "error");
				}
			})
		},
		//初始化图表
		intiReport: function (list,max_val) {
			var echartsObj = echarts.init(document.getElementById('buyer-distribution'));
			var max = max_val;//最大值
			var option = {
				title: {
					text: '过去一周买家分布',
					left: 'left'
				},
				tooltip: {
					trigger: 'item'
				},
				visualMap: {
					type: 'piecewise',
					pieces: [
						{ gt: max * 3 / 4, lte: max, color: '#f6701c' },
						{ gt: max * 2 / 4, lte: max * 3 / 4, color: '#74c065' },
						{ gt: max * 1 / 4, lte: max * 2 / 4, color: '#6cc9e3' },
						{ gt: 0, lte: max * 1 / 4, color: '#beede5' },
						//{ lt: 10 }                 // (-Infinity, 5)
					],
					left: 'left',
					top: 'bottom',
					text: ['高', '低'],           // 文本，默认为数值文本
				},
				toolbox: {
					show: true,
					orient: 'vertical',
					left: 'left',
					top: 'center',
					feature: {
						dataView: { readOnly: false },
						restore: {},
						saveAsImage: {}
					}
				},
				series: [
					{
						name: '访客数',
						type: 'map',
						mapType: 'china',
						roam: false,
						label: {
							normal: {
								show: true
							},
							emphasis: {
								show: true
							}
						},
						data: list
					}
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			echartsObj.setOption(option);
		}
	}
})
/********************* */
