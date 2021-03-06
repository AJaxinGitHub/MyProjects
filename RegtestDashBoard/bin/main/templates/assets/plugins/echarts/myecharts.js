// my echarts
// ============================================================== 
var myChart 
var option
initChart()
myChart = echarts.init(document.getElementById('myecharts'));
initChart() {
        option = {
            title: {
                text: "Regtest挖矿数历史记录",
                x: "center"
            },
            tooltip: {
                trigger: "item",
                formatter: "{a} <br/>{b} : {c}块"
            },
            legend: {
                x: 'left',
                data: []
            },
            xAxis: [
                {
                    type: "category",
                    name: "日期",
                    splitLine: {show: false},
                    data: []
                }
            ],
            yAxis: [
                {
                    type: "value",
                    axisLabel: {
                        formatter: '{value} 块'
                    },
                    name: "数量",
                    max: 200,
                    min: 0,
                    splitNumber: 5
                }
            ],
            toolbox: {
                show: true,
                feature: {
                    mark: {
                        show: true
                    },
                    dataView: {
                        show: true,
                        readOnly: true
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            calculable: true,
            series: []
        };
}


 $.ajax({
    type: "GET",
    url: "blocksAndTime",
    data: {},
    success: function (r) {
    	initChart()
        option.legend.data[0] = "10.12.33.222"
        //pie是饼状图  line 是折线图  bar是柱状图
        option.series[0] = {"name":"GG", "type":"line", "data": []}
        for (var i in r) {
            option.series[0].data[i] = r[i].blockcount
            //alert(r[i].blockcount);
            option.xAxis[0].data[i] = r[i].time
        }
    	myChart.setOption(option)
    }
});
        
// use configuration item and data specified to show chart
myChart.setOption(option)
    

    