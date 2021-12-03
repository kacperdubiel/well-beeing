<template>
    <div v-if="data" id="chart">
        <apexchart :options="chartOptions" :series="series" height="350" type="bar"></apexchart>
    </div>
</template>

<script>
import pl from "apexcharts/dist/locales/pl.json";

export default {
    name: "StatsColumnChart",
    props: {
        data: {} //{series:[], yTitle:'',  labelUnits:''}
    },
    methods: {
        updateData() {
            if (this.data === null || this.data.series === null)
                return
            this.series = this.data.series
            this.chartOptions.yaxis.formatter = function (val) {
                return val.toFixed(0) + ' ' + this.data.unitName
            }
            // this.chartOptions.title.text = this.data.chartTitle
        }
    },
    data() {
        return {
            series: [{
                name: 'Ty',
                data: [19002]
            }, {
                name: 'Pozostali użytkownicy',
                data: [16940]
            }],
            chartOptions: {
                colors: ["var(--SPORT)", "var(--DARK-YELLOW)"],
                chart: {
                    toolbar: {
                        show: false
                    },
                    parentHeightOffset: 0,
                    type: 'bar',
                    locales: [pl],
                    defaultLocale: "pl",
                    selection: {
                        enabled: false
                    }
                },
                plotOptions: {
                    bar: {
                        horizontal: false,
                        columnWidth: '70%',
                        endingShape: 'rounded',
                        dataLabels: {
                            position: 'top', // top, center, bottom
                        },
                    },
                },
                dataLabels: {
                    enabled: true,
                    offsetY: -20,
                    style: {
                        fontSize: '12px',
                        colors: ["white"]
                    },
                    formatter: function (val, {seriesIndex, dataPointIndex, w}) {
                        return val + ' ' + w.config.series[seriesIndex].units
                    },
                },
                stroke: {
                    show: true,
                    width: 2,
                    colors: ['transparent']
                },
                xaxis: {
                    categories: [''],
                    labels: {
                        show: false
                    },
                    axisBorder: {
                        show: false
                    },
                    axisTicks: {
                        show: false
                    },
                    crosshairs: {
                        show: false
                    }
                },
                legend: {
                    show: true,
                    labels: {
                        colors: "white"
                    },
                    onItemClick: {
                        toggleDataSeries: false
                    },
                    onItemHover: {
                        highlightDataSeries: false
                    },

                },
                grid: {
                    xaxis: {
                        lines: {
                            show: false
                        }
                    },
                    yaxis: {
                        lines: {
                            show: false
                        }
                    },
                },
                yaxis: {
                    labels: {
                        show: false,
                        style: {
                            colors: "white"
                        },
                        formatter: (val) => {
                            if (this.data != null)
                                return val.toFixed(0) + ' ' + this.data.unitName
                            return val.toFixed(0)
                        },
                    }
                },
                fill: {
                    opacity: 1,
                },
                tooltip: {
                    shared: false,
                    intersect: true,
                    theme: "dark",
                    y: {
                        title: {
                            formatter: function (seriesName, opts) {
                                let description = opts.w.config.series[opts.seriesIndex].data[opts.dataPointIndex]
                                    .description;

                                if (description) {
                                    return description + '<br/>' + seriesName + ': ';
                                }
                                return seriesName + ': ';
                            },
                        }
                    },
                },
            }
        }
    },
    watch: {
        data: function () {
            console.log('Update')
            this.updateData()
        }
    },
    created() {
        this.updateData()
    }
}
</script>

<style scoped>

</style>
