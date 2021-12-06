<template>
    <div class="ps-3 col-12 mb-2 header">
        Podsumowanie Twojej aktywności
    </div>
    <hr/>
    <div class="row ps-3">
        <div class="col align-self-start">
            <p id="short" class="text-start">Skróty</p>
            <ol class="text-start">
                <li @click="scrollMeTo('calories-burned-section')">Spalone kalorie</li>
                <li @click="scrollMeTo('training-section')">Czas spędzony w treningu</li>
                <li @click="scrollMeTo('favourite-training-section')">Ulubiony trening</li>
                <li @click="scrollMeTo('active-days-section')">Dni aktywności</li>
                <li @click="scrollMeTo('longest-streak-section')">Najdłuższa seria</li>
            </ol>
        </div>
        <div class="col">
            <date-picker v-model="dateRange" :model-config="datePickerConfig" is-range/>
            <br/>
            <button class="btn-panel-sport mt-2" @click="getUsersStatistics">
                Pobierz podsumowanie
            </button>
        </div>

    </div>
    <div v-if="noDataInRangeError" class="alert alert-warning m-3 alert-dismissible fade show"
         role="alert">
        Nie masz zarejestrowanej aktywności fizycznej w tym okresie!
    </div>
    <div v-if="newDataInRangeSuccess" class="alert alert-success m-3 alert-dismissible fade show"
         role="alert">
        Zaktualizowaliśmy Twoje statystyki!
    </div>
    <hr/>
    <div v-if="userStatistics != null && !noDataInRangeError">
        <div class="container my-4">
            <div ref="calories-burned-section" class="row header">
                Od {{ this.$func_global.formatDate(userStatistics ? userStatistics.from : dateRange.start) }}
                do {{ this.$func_global.formatDate(userStatistics ? userStatistics.to : dateRange.end) }}
            </div>
            <div class="row my-3">
                <div class="col-lg-6 col-sm-12 col-12 my-3 ">
                    <label class="stat-label" for="calories">{{ sex === 'WOMAN' ? 'Spaliłaś' : 'Spaliłeś' }}</label>
                    <br/>
                    <p id="calories" class="stat-value value-me">{{ Math.trunc(userStatistics.userCalories) }} kcal</p>

                </div>
                <div class="col-lg-6 col-sm-12 col-12 my-3 ">
                    <label class="stat-label" for="calories-average">Inni użytkownicy spalili średnio</label>
                    <br/>
                    <p id="calories-average" class="stat-value value-others">
                        {{ Math.trunc(userStatistics.totalCaloriesAverage) }}
                        kcal</p>
                </div>
                <hr/>
                <div class="col-lg-6 col-sm-12 col-12 my-3  justify-content-center">
                    <label class="stat-label" for="calories-average">Spalone kalorie</label>
                    <stats-column-chart
                        :data="newSeriesData(userStatistics.userCalories, userStatistics.totalCaloriesAverage, 'kcal' ,'Spalone kalorie' )"/>

                </div>
                <div class="col-lg-6 col-sm-12 col-12 my-3 justify-content-center">
                    <label class="stat-label" for="calories-percent">{{ sex === 'WOMAN' ? 'Spaliłaś' : 'Spaliłeś' }}
                        więcej kalorii od</label>
                    <br/>
                    <p id="calories-percent" class="stat-value value-me justify-content-center">
                        {{ Math.trunc(userStatistics.userCaloriesBetterThanPercent * 100) }}
                        %</p>
                    <label class="stat-label" for="calories-percent">pozostałych użytkowników</label>

                </div>
                <hr class="between-sections"/>
                <div ref="training-section" class="col-lg-6 col-sm-12 col-12 my-3 ">
                    <label class="stat-label" for="trainings">{{ sex === 'WOMAN' ? 'Trenowałaś' : 'Trenowałeś' }}
                        przez</label>
                    <br/>
                    <p id="trainings" class="stat-value value-me">{{ Math.trunc(userStatistics.userTrainingMinutes) }}
                        minut</p>

                </div>
                <div class="col-lg-6 col-sm-12 col-12 my-3 ">
                    <label class="stat-label" for="trainings-average">Inni użytkownicy trenowali średnio</label>
                    <br/>
                    <p id="trainings-average" class="stat-value value-others">
                        {{ Math.trunc(userStatistics.totalTrainingMinutesAverage) }}
                        minut</p>
                </div>
                <hr/>
                <div class="col-lg-6 col-sm-12 col-12 my-3  justify-content-center">
                    <label class="stat-label" for="calories-average">Aktywność treningowa</label>
                    <stats-column-chart
                        :data="newSeriesData(userStatistics.userTrainingMinutes, userStatistics.totalTrainingMinutesAverage, 'min' ,'Czas treningu' )"/>
                </div>
                <div class="col-lg-6 col-sm-12 col-12 my-3 justify-content-center">
                    <label class="stat-label" for="trainings-percent">{{
                            sex === 'WOMAN' ? 'Trenowałaś' : 'Trenowałeś'
                        }} dłużej od</label>
                    <br/>
                    <p id="trainings-percent" class="stat-value value-me justify-content-center">
                        {{ Math.trunc(userStatistics.userTrainingMinutesBetterThanPercent * 100) }}
                        %</p>
                    <label class="stat-label" for="trainings-percent">pozostałych użytkowników</label>
                </div>
                <hr v-if="userStatistics.userFavouriteTraining" class="between-sections"/>
                <div v-if="userStatistics.userFavouriteTraining"
                     ref="favourite-training-section" class="col-lg-6 col-sm-12 col-12 my-3 ">
                    <label class="stat-label" for="favourite-training">Twoim ulubionym treningiem był</label>
                    <br/>
                    <p id="favourite-training" class="stat-value value-me">{{
                            userStatistics.userFavouriteTraining.name
                        }}</p>
                </div>
                <div v-if="userStatistics.userFavouriteTraining" class="col-lg-6 col-sm-12 col-12 my-3">
                    <label class="stat-label"
                           for="favourite-training-count">{{ sex === 'WOMAN' ? 'Zrealizowałaś' : 'Zrealizowałeś' }}
                        go</label>
                    <br/>
                    <p id="favourite-training-count" class="stat-value value-me">
                        {{ userStatistics.userFavouriteTrainingCount }}
                        {{ userStatistics.userFavouriteTrainingCount > 1 ? 'razy' : 'raz' }} </p>
                </div>
                <hr class="between-sections"/>
                <div ref="active-days-section" class="col-lg-6 col-sm-12 col-12 my-3 ">
                    <label class="stat-label" for="days">{{ sex === 'WOMAN' ? 'Byłaś aktywna' : 'Byłeś aktywny' }}
                        przez</label>
                    <br/>
                    <p id="days" class="stat-value value-me justify-content-center">
                        {{ userStatistics.userTrainingDays }} {{
                            userStatistics.userTrainingDays > 1 ? 'dni' : 'dzień'
                        }}</p>

                </div>
                <div class="col-lg-6 col-sm-12 col-12 my-3 ">
                    <label class="stat-label" for="days-average">Pozostali byli aktywni średnio przez</label>
                    <br/>
                    <p id="days-average" class="stat-value value-others justify-content-center">
                        {{ Math.trunc(userStatistics.avgTrainingDays) }} {{
                            userStatistics.avgTrainingDays > 1 ? 'dni' : 'dzień'
                        }}</p>

                </div>
            </div>
            <hr class="between-sections"/>
            <div ref="longest-streak-section" class="row mt-5 justify-content-center">
                <p class="header text-center">Twoja najdłuższa seria aktywności trwała
                    {{ userStatistics.userOverallLongestTrainingStreak }}
                    {{ userStatistics.userOverallLongestTrainingStreak > 1 ? 'dni' : 'dzień' }}!</p>
                <br/>
                <p v-if="userStatistics.userOverallLongestTrainingStreak > 1" class="header text-center">
                    {{ this.$func_global.formatDate(userStatistics.userOverallLongestTrainingStreakDates[0]) }} -
                    {{
                        this.$func_global.formatDate(userStatistics.userOverallLongestTrainingStreakDates[userStatistics.userOverallLongestTrainingStreakDates.length - 1])
                    }}
                </p>
            </div>

        </div>

    </div>
</template>

<script>
import moment from "moment";
import {DatePicker} from "v-calendar";
import StatsColumnChart from "@/components/sport/StatsColumnChart";

export default {
    name: "SportStatsView",
    components: {
        StatsColumnChart,
        DatePicker,
    },
    data() {
        return {
            dateRange: {
                start: moment().startOf('month').format('YYYY-MM-DD'),
                end: moment().endOf('month').format('YYYY-MM-DD')
            },
            datePickerConfig: {
                start: {
                    timeAdjust: '00:00:00',
                },
                end: {
                    timeAdjust: '23:59:59',
                },
            },
            userStatistics: null,
            noDataInRangeError: false,
            newDataInRangeSuccess: false,
            firstCall: true,
            sex: this.$store.getters.getSex
        }
    },
    methods: {
        async getUsersStatistics() {
            this.resetStatus()
            const url = `${this.apiURL}sport/report/stats`
            const token = this.$store.getters.getToken;
            let params = {
                date_from: moment(this.dateRange.start).format('YYYY-MM-DD'),
                date_to: moment(this.dateRange.end).format('YYYY-MM-DD')
            }
            await this.axios.get(url, {
                params: params,
                headers: {Authorization: `Bearer ${token}`}
            }).then((response) => {
                this.userStatistics = response.data
                if (!this.firstCall)
                    this.newDataInRangeSuccess = true
            }).catch(error => {
                console.log(error.response);
                if (error.response.status === 409) {
                    this.noDataInRangeError = true
                }
            });
            this.firstCall = false
        },
        resetStatus() {
            this.newDataInRangeSuccess = false
            this.noDataInRangeError = false
        },
        newSeriesData(data1, data2, unitName, title) {
            return {
                series: [{
                    name: 'Ty',
                    data: [Math.trunc(data1)],
                    units: unitName
                }, {
                    name: 'Pozostali użytkownicy',
                    data: [Math.trunc(data2)],
                    units: unitName
                }],
                unitName: unitName,
                chartTitle: title
            }

        },
        scrollMeTo(refName) {
            let element = this.$refs[refName];
            let top = element.offsetTop - 120;

            window.scrollTo(0, top);
        }
    },
    mounted() {
        this.getUsersStatistics()
    }
}
</script>

<style scoped>

.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}

p.stat-value {
    width: 250px;
    height: 250px;
    border-radius: 50%;
    display: inline-flex; /* or inline-flex */
    align-items: center;
    justify-content: center;
    font-weight: bold;
    font-size: 35px;
    opacity: 0.9;
}

.value-others {
    background: var(--DARK-YELLOW);
}

.value-me {
    background: var(--SPORT);
}

.stat-label {
    height: 50px;
    color: white;
    font-weight: bold;
    font-size: 20px;
}

.border-end {
    border-right-color: var(--SPORT) !important;
}

hr.between-sections {
    background: var(--GREY2);
    border-top: 7px dashed var(--SPORT);
    opacity: 100%;
    margin-top: 20px;
    margin-bottom: 20px;
}

ol {
    font-size: 20px;
}

li:hover {
    cursor: pointer;
    color: var(--SPORT);
}

#short {
    font-weight: bold;
    font-size: 35px;
}
</style>
