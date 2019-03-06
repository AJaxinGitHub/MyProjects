// ==============================================================
// Bar chart option
// ==============================================================
var myChart
var option

$(function () {
    initChart()
    //基于准备好的dom，初始化echarts实例
    myChart = echarts.init(document.getElementById('myecharts'));
    $.ajax({
        type: "GET",
        url: "blocksAndTime",
        data: {},
        success: function (r) {
            option.legend.data[0] = "10.12.33.222"
            //pie是饼状图  line 是折线图  bar是柱状图
            for (var i in r) {
                option.xAxis[0].data[i] = r[i].time
                option.series[0].data[i] = r[i].blockcount
                //alert(r[i].time + ":" + r[i].blockcount)
            }
            myChart.setOption(option)
        }
    });
})

// specify chart configuration item and data
    function initChart(){
        option = {
            title: {
                text: "Regtest挖矿数历史记录",
                x: "center"
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: []
            },
            toolbox: {
                show: true,
                feature: {
                    magicType: {show: true, type: ['line', 'bar']},
                }
            },
            color: ["#55ce63", "#009efb"],
            calculable: true,
            xAxis: [
                {   name: "日期",
                    type: 'category',
                    data: []
                }
            ],
            yAxis: [
                {   name: "数量",
                    type: 'value',
                    axisLabel: {
                    formatter: '{value} 块'
                    }
                }
            ],
            series: [
                {
                    name: '数量',
                    type: 'bar',
                    data: [],
                    markPoint: {
                        data: [
                            {type: 'max', name: 'Max'},
                            {type: 'min', name: 'Min'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: 'Average'}
                        ]
                    }
                }
            ]
        }
    }





// //my echarts
// //==============================================================
// var myChart
// var option
//
// $(function () {
//     initChart()
//     //基于准备好的dom，初始化echarts实例
//     myChart = echarts.init(document.getElementById('myecharts'));
//     //myChart.setOption(option)
//     $.ajax({
//         type: "GET",
//         url: "blocksAndTime",
//         data: {},
//         success: function (r) {
//             initChart()
//             option.legend.data[0] = "10.12.33.222"
//             //pie是饼状图  line 是折线图  bar是柱状图
//             option.series[0] = {"name":"GG", "type":"line", "data": []}
//             for (var i in r) {
//                 option.series[0].data[i] = r[i].blockcount
//                 option.xAxis[0].data[i] = r[i].time
//             }
//             myChart.setOption(option)
//         }
//     });
// })
//
// function initChart() {
//     option = {
//         title: {
//             text: "Regtest挖矿数历史记录",
//             x: "center"
//         },
//         tooltip: {
//             trigger: "item",
//             formatter: "{a} <br/>{b} : {c}块"
//         },
//         legend: {
//             x: 'left',
//             data: []
//         },
//         xAxis: [
//             {
//                 type: "category",
//                 name: "日期",
//                 splitLine: {show: false},
//                 data: []
//             }
//         ],
//         yAxis: [
//             {
//                 type: "value",
//                 axisLabel: {
//                     formatter: '{value} 块'
//                 },
//                 name: "数量",
//                 max: 200,
//                 min: 0,
//                 splitNumber: 5
//             }
//         ],
//         toolbox: {
//             show: true,
//             feature: {
//                 mark: {
//                     show: true
//                 },
//                 dataView: {
//                     show: true,
//                     readOnly: true
//                 },
//                 restore: {
//                     show: true
//                 },
//                 saveAsImage: {
//                     show: true
//                 }
//             }
//         },
//         calculable: true,
//         series: []
//     };
// }
