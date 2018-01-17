/**
 * @description：统计报表：用户数据
 * @author：lkj
 * @create：2017.7.18
 */


new Vue({

	el: '#vm-user-data',
	//数据
	data: {

		updateTime: '--',//更新时间
		site_pv:0,//全站PV
		site_uv:0,//全站UV
		login_users:0,//登录用户
		tryon_users:0,//试穿用户
		registerUserCount: 0,//注册用户数量
		orderUserCount: 0,//下单用户数量
		buyerCount: 0//买家数量
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


