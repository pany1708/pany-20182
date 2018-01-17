/**
 * @description：统计报表：用户数据
 * @author：lkj
 * @create：2017.7.18
 */


new Vue({

	el: '#vm-order-data',
	//数据
	data: {

		updateTime: '--',//更新时间
		orderCount:0, //订单数量
		cancelCount:0,//取消数量
		payCount:0,//支付数量
		afterSaleCount:0//售后数量
	},
	//加载页面时执行
	created: function () {
		this.getReportData("yesterday");
	},
	methods: {

		getReportData: function (type) {
			var data = {
				"timeType": type
			};
			axios.post(zmme + "/userReport/userData", data).then(response => {
				var result = response.data;
				if (result.code == 0) {
					var list = result.data;
					if (list.length > 0) {
						this.updateTime = list[0].refreshTime;
					}
					for (var i in list) {
						var item = list[i];
						if (item.dataType == 2) {
							this.registerUserCount = item.number;
						}
						if (item.dataType == 5) {
							this.orderUserCount = item.number;
						}
						if (item.dataType == 6) {
							this.buyerCount = item.number;
						}
					}


				} else {
					swal("错误！", result.message, "error");
				}
			})
		}
	}
})


