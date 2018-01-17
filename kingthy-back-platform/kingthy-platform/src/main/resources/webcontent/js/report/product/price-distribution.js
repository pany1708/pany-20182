
/**
 * @description：统计报表：不同年龄分段购买情况
 * @author：lkj
 * @create：2017.7.18
 */
new Vue({
	el: '#vm_price-distribution',
	//数据
	data: {
		updateTime: '--',//更新时间
		list:[
			{name:'0-1000',value:'700'},
			{name:'1001-3000',value:'400'},
			{name:'3001-5000',value:'300'},
			{name:'5001-8000',value:'200'},
			{name:'8000-10000',value:'100'},
			{name:'大于10000',value:'50'},
		]
	},
	//加载页面时执行
	created: function () {
		//this.getReportData();
	},
	//挂载完成时执行
	mounted: function () {
		this.initReport(this.list);
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
			var echartsObj = echarts.init(document.getElementById('price-distribution'));

			//转换为插件报表数据结构
			var xdata = [], reportData = [];

			for (var i in list) {
				var row = list[i];
				var item = { 'name': row.name, 'value': row.value };
				reportData.push(item)
				//横轴数据
				xdata.push(row.name);
			}

			// 指定图表的配置项和数据
			var option = {
				//标题
				title: {
					text: '上架商品价格分布'
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
					name: '价格区间',
					data: xdata
				},
				//y轴
				yAxis: {
					name: '数量',
					splitNumber: 4
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
