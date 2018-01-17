
/**
 * @description：商品数据：商品排行
 * @author：lkj
 * @create：2017.7.27
 */
new Vue({
	el: '#vm_product-rankings',
	//数据
	data: {
		updateTime: '2017.7.27',//更新时间
		payAmountData:[
			{name:'2017夏季最新款公主裙',value:100},
			{name:'2017夏季最新款水晶鞋',value:200},
			{name:'2017夏季最新款连衣裙',value:100},
			{name:'2017夏季最新款裤',value:300},
			{name:'2017夏季最新款T1212554',value:100},
			{name:'2017夏季最新款o1253',value:400},
			{name:'2017夏季最新款',value:100},
			{name:'2017夏季最新款',value:500},
			{name:'2017夏季最新款',value:100},
			{name:'2017夏季最新款',value:600},
		],//支付金额排行
		visitorData:[]//访客排行
	},
	//加载页面时执行
	created: function () {
		
	},
	//挂载完成时执行
	mounted:function(){
		//this.initReport();
	},
	methods: {

		getReportData: function () {
			axios.get(zmme + "/userReport/newUserNum/").then(response => {
				var result = response.data;
				if (result.code == 0) {
					
					if (result.data.length > 0) {
						this.updateTime = result.data[0].refreshTime;
					}
				} else {
					swal("错误！", result.message, "error");
				}
			})
		}
	}
})
