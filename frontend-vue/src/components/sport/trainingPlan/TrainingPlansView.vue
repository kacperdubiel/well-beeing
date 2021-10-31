<template>
    <div>
        <div class="m-3 mx-4 header">
            <span >Aktualny plan treningowy </span>
            <span class="week">({{this.getDateRangeOfWeek(week)}})</span>
        </div>
        <div class="container" style="overflow: auto">
            <div class="row-fluid">
                <div v-for="day in days" :key="day" class="col-4 day mx-1  mb-3 p-3">
                    <TrainingPlanDay :day=day>

                    </TrainingPlanDay>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TrainingPlanDay from "@/components/sport/trainingPlan/TrainingPlanDayComponent";
export default {
    name: "TrainingPlansView",
    components: {TrainingPlanDay},
    data () {
        return {
            week: 43,
            year: 2021,
            days: ['Poniedziałek', 'Wtorek', 'Środa', 'Czwartek', 'Piątek', 'Sobota', 'Niedziela']
        }
    },
    methods: {
        getDateRangeOfWeek(weekNo){
            var d1 = new Date();
            var numOfdaysPastSinceLastMonday = eval(d1.getDay()- 1);
            d1.setDate(d1.getDate() - numOfdaysPastSinceLastMonday);
            var weekNoToday = d1.getWeek();
            var weeksInTheFuture = eval( weekNo - weekNoToday );
            d1.setDate(d1.getDate() + eval( 7 * weeksInTheFuture ));
            var rangeIsFrom = d1.getDate().toString().padStart(2, '0') + '.' + eval(d1.getMonth()+1).toString().padStart(2, '0');
            d1.setDate(d1.getDate() + 6);
            var rangeIsTo = d1.getDate().toString().padStart(2, '0') + '.' + eval(d1.getMonth()+1).toString().padStart(2, '0');
            return rangeIsFrom + " - "+rangeIsTo;
        }
    }
}
Date.prototype.getWeek = function() {
    var date = new Date(this.getTime());
    date.setHours(0, 0, 0, 0);
    // Thursday in current week decides the year.
    date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
    // January 4 is always in week 1.
    var week1 = new Date(date.getFullYear(), 0, 4);
    // Adjust to Thursday in week 1 and count number of weeks from date to week1.
    return 1 + Math.round(((date.getTime() - week1.getTime()) / 86400000 - 3 + (week1.getDay() + 6) % 7) / 7);
}
</script>

<style scoped>

.header {
    text-align: left;
    font-size: 36px;
    font-weight: bold;
}
.week {
    font-size: 20px;
}
.row-fluid{
    white-space: nowrap;
}
.row-fluid .col-4{
    display: inline-block;
}
.col-4 { white-space: normal; }
.day {
    background-color: var(--GREY3);
    border-radius: 5px;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}
</style>
