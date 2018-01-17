
/**
 * 
 * 本地数据与百度地图数据映射
 */
var localProvince = [

    { name: '北京', value: '3227' },
    { name: '天津', value: '3228' },
    { name: '上海', value: '3231' },
    { name: '重庆', value: '3232' },
    { name: '河北', value: '3232' },
    { name: '河南', value: '1511' },
    { name: '云南', value: '2579' },
    { name: '辽宁', value: '465' },
    { name: '黑龙江', value: '650' },
    { name: '湖南', value: '1805' },
    { name: '安徽', value: '1026' },
    { name: '山东', value: '1355' },
    { name: '新疆', value: '3106' },
    { name: '江苏', value: '810' },
    { name: '浙江', value: '924' },
    { name: '江西', value: '1243' },
    { name: '湖北', value: '1688' },
    { name: '广西', value: '2085' },
    { name: '甘肃', value: '2925' },
    { name: '山西', value: '219' },
    { name: '内蒙古', value: '350' },
    { name: '陕西', value: '2807' },
    { name: '吉林', value: '580' },
    { name: '福建', value: '1148' },
    { name: '贵州', value: '2481' },
    { name: '广东', value: '1942' },
    { name: '青海', value: '3026' },
    { name: '西藏', value: '2725' },
    { name: '四川', value: '2276' },
    { name: '宁夏', value: '3078' },
    { name: '海南', value: '2210' },
    { name: '台湾', value: '3220' },
    { name: '香港', value: '3221' },
    { name: '澳门', value: '3222' }
];
function getProvinceNameById(pid){

    for(var i in localProvince){
        var item=localProvince[i];
        if(item.value==pid){
            return item.name;
        }
    }
    return '';
}



