<template>
    <apexchart type="line" :options="chartOptions" :series="chartSeries"></apexchart>
</template>

<script>
import pl from "apexcharts/dist/locales/pl.json";

export default {
    name: "AnalysisChartComponent",
    props: {
        data: {},
        hideYAxis: Boolean,
    },
    watch: {
        data: function() {
            console.log("Data changed!");
        },
        hideYAxis: function() {
            let yaxis = this.chartOptions.yaxis;
            yaxis.forEach(yax => {
                yax.show = !this.hideYAxis;

            })
            this.chartOptions = {yaxis: yaxis};
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
                        enabled: true,
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
                    categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998],
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
                            }
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
                            }
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
                            }
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
                            }
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
                        show: true
                    }
                },
            },
            chartSeries: [{
                name: 'series-1',
                data: [30, 40, 45, 50, 49, 60, 70, 91]
            },{
                name: 'series-2',
                data: [135, 133, 121, 150, 155, 168, 179, 199]
            },{
                name: 'series-3',
                data: [325, 331, 211, 530, 535, 638, 739, 939]
            },{
                name: 'series-4',
                data: [5, 9, 12, 7, 5, 4, 2, 2]
            }]
        }
    },
    methods: {

    },
    created() {

    }
}
</script>

<style scoped>
</style>