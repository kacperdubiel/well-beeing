<template>
    <apexchart ref="realtimeChart" type="line" :options="chartOptions" :series="chartSeries"></apexchart>
</template>

<script>
import pl from "apexcharts/dist/locales/pl.json";    // ref="realtimeChart"

export default {
    name: "AnalysisChartComponent",
    props: {
        data: {},
        hideYAxis: Boolean,
    },
    watch: {
        data: function() {
            this.chartSeries = this.data;
            this.updateYAxis();
        },
        hideYAxis: function() {
            this.updateYAxis();
        },
    },
    data() {
        return {
            chartOptions: {
                chart: {
                    dropShadow: {
                        enabled: true,
                        top: 1,
                        left: 1,
                        blur: 2,
                        color: '#000',
                        opacity: 0.25
                    },
                    animations: {
                        enabled: false,
                        easing: 'easein',
                        speed: 600,
                        animateGradually: {
                            enabled: true,
                            delay: 100
                        },
                        dynamicAnimation: {
                            enabled: true,
                            speed: 400
                        }
                    },
                    toolbar: {
                        offsetX: 0,
                        offsetY: -10,
                        export: {
                            csv: {
                                columnDelimiter: ';',
                                headerCategory: 'typ pomiaru',
                                headerValue: 'wartość',
                                dateFormatter(timestamp) {
                                    return new Date(timestamp).toLocaleString()
                                }
                            },
                        },
                    },
                    locales: [pl],
                    defaultLocale: "pl",
                },
                stroke: {
                    curve: 'smooth',
                    width: 3,
                },
                markers: {
                    size: 4,
                    strokeWidth: 1,
                    strokeColors: '#2c2c2c',
                    hover: {
                        size: 8,
                    }
                },
                dataLabels: {
                    enabled: false
                },
                legend: {
                    show: true,
                    position: 'bottom',
                    horizontalAlign: 'center',
                    floating: false,
                    fontSize: '14px',
                    fontFamily: 'Helvetica, Arial',
                    fontWeight: 400,
                    labels: {
                        // colors: "#fff",
                        useSeriesColors: true
                    },
                    markers: {
                        width: 16,
                        height: 16,
                        strokeColor: '#fff',
                    },
                    onItemHover: {
                        highlightDataSeries: true
                    },
                },
                xaxis: {
                    type: "datetime",
                    labels: {
                        style: {
                            colors: "#fff",
                        },
                    },
                },
                yaxis: [
                    {
                        show: false,
                        opposite: false,
                        tickAmount: 6,
                        axisTicks: {
                            show: true
                        },
                        axisBorder: {
                            show: false,
                            color: "#008FFB"
                        },
                        labels: {
                            style: {
                                colors: "#008FFB",
                            },
                            formatter: (val) => { return val.toFixed(1)},
                        },
                        title: {
                            text: "Series A",
                            style: {
                                color: "#008FFB",
                                fontSize: '15px',
                                fontWeight: 400,
                            }
                        }
                    },
                    {
                        show: false,
                        opposite: true,
                        tickAmount: 6,
                        axisTicks: {
                            show: true
                        },
                        axisBorder: {
                            show: false,
                            color: "#00E396"
                        },
                        labels: {
                            style: {
                                colors: "#00E396"
                            },
                            formatter: (val) => { return val.toFixed(1)},
                        },
                        title: {
                            text: "Series B",
                            style: {
                                color: "#00E396",
                                fontSize: '15px',
                                fontWeight: 400,
                            }
                        }
                    },
                    {
                        show: false,
                        opposite: false,
                        tickAmount: 6,
                        axisTicks: {
                            show: true
                        },
                        axisBorder: {
                            show: false,
                            color: "#FEB019"
                        },
                        labels: {
                            style: {
                                colors: "#FEB019"
                            },
                            formatter: (val) => { return val.toFixed(1)},
                        },
                        title: {
                            text: "Series C",
                            style: {
                                color: "#FEB019",
                                fontSize: '15px',
                                fontWeight: 400,
                            }
                        }
                    },
                    {
                        show: false,
                        opposite: true,
                        tickAmount: 6,
                        axisTicks: {
                            show: true
                        },
                        axisBorder: {
                            show: false,
                            color: "#FF4560"
                        },
                        labels: {
                            style: {
                                colors: "#FF4560"
                            },
                            formatter: (val) => { return val.toFixed(1)},
                        },
                        title: {
                            text: "Series D",
                            style: {
                                color: "#FF4560",
                                fontSize: '15px',
                                fontWeight: 400,
                            }
                        }
                    },
                ],
                tooltip: {
                    shared: false,
                    intersect: true,
                    theme: "dark",
                    x: {
                        show: true,
                        format: 'dd MMM - HH:mm'
                    },
                    y: {
                        title: {
                            formatter: function(seriesName, opts) {
                                let description = opts.w.config.series[opts.seriesIndex].data[opts.dataPointIndex]
                                    .description;

                                if(description){
                                    return description + '<br/>' + seriesName + ': ';
                                }
                                return seriesName;
                            },
                        }
                    },
                },
            },
            chartSeries: []
        }
    },
    methods: {
        updateYAxis() {
            if(this.hideYAxis){
                this.hideAxis();
            } else {
                this.showAxis();
            }
        },
        hideAxis(){
            let yaxis = this.chartOptions.yaxis;
            yaxis.forEach(yax => {
                yax.show = false;

            })
            this.chartOptions = {
                ...this.chartOptions,
                ...{
                    yaxis: yaxis,
                },
            };
        },
        showAxis(){
            let yaxis = this.chartOptions.yaxis;
            for(let i = 0; i < 4; i++){
                if(i < this.chartSeries.length){
                    yaxis[i].show = true;
                    yaxis[i].title.text = this.chartSeries[i].name;
                    if(this.chartSeries[i].data[0] && this.chartSeries[i].data[0].min >= 0){
                        yaxis[i].min = this.chartSeries[i].data[0].min;
                    } else {
                        yaxis[i].min = 0;
                    }

                    if(this.chartSeries[i].data[0] && this.chartSeries[i].data[0].precision >= 0){
                        yaxis[i].labels.formatter = (val) => { return val.toFixed(this.chartSeries[i].data[0].precision)};
                    } else {
                        yaxis[i].labels.formatter = (val) => { return val.toFixed(1)};
                    }
                } else {
                    yaxis[i].show = false;
                    yaxis[i].title.text = "";
                }
            }

            this.chartOptions = {
                ...this.chartOptions,
                ...{
                    yaxis: yaxis,
                },
            };
        },
    },
    created() {

    }
}
</script>

<style>

div.apexcharts-menu-item{
    color: black !important;
}

</style>