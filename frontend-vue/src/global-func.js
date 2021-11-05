export const func_global = {
    dateDayMonth(date) {
        return date.getDate().toString().padStart(2, '0') + '.' + eval(date.getMonth()+1).toString().padStart(2, '0');
    },
    getTimePrettyFromSeconds(seconds) {
        if (seconds < 60) {
            return seconds + ' s'
        }
        else if (seconds < 3600) {
            return Math.floor(seconds/60) + ' min'
        }
        else if (seconds >= 3600) {
            let hours = Math.floor(seconds/3600)
            let minutes = Math.floor((seconds - hours*3600)/60)
            return hours + ' h ' + (minutes !== 0 ? minutes + ' min': '')
        }
    },
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
    },
    getDatesArrayOfWeek(weekNo){
        var d1 = new Date();
        var numOfdaysPastSinceLastMonday = eval(d1.getDay()- 1);
        d1.setDate(d1.getDate() - numOfdaysPastSinceLastMonday);
        var weekNoToday = d1.getWeek();
        var weeksInTheFuture = eval( weekNo - weekNoToday );
        d1.setDate(d1.getDate() + eval( 7 * weeksInTheFuture ));
        let weekDays = []
        for (let i = 0; i < 7; i++) {
            weekDays.push({
                day: this.days[i],
                date: d1.addDays(i)
            })
        }
        return weekDays;
    },
    days: [
        {
            num:1,
            name: 'Poniedziałek'
        },
        {
            num:2,
            name: 'Wtorek'
        },
        {
            num:3,
            name: 'Środa'
        },
        {
            num:4,
            name: 'Czwartek'
        },
        {
            num:5,
            name: 'Piątek'
        },
        {
            num:6,
            name: 'Sobota'
        },
        {
            num:0,
            'name': 'Niedziela'
        }
    ]
}
