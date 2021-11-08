import moment from "moment";

const apiURL = 'http://localhost:8090/'
import axios from "axios";
export const func_global = {

    importData(requestId, myfile, token) {
        // let myfile = this.$refs.myfile;
        let files = myfile.files;
        let file = files[0];
        var formData = new FormData();
        formData.append("file", file);
        this.uploadFileRequest(formData, requestId, token)
    },

    uploadFileRequest (data, requestId, token) {
        const url = `${apiURL}role-request/import/${requestId}/`
        // const token = this.$store.getters.getToken;
        axios.post(url, data, {headers: {Authorization: `Bearer ${token}`, 'Content-Type': 'multipart/form-data'}}).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error.response)
        });
    },
    formatDate(date) {
        if (date) {
            return moment(String(date)).format('DD/MM/YYYY')
        }
    },
    mapRole(role) {
        if(role === 'ROLE_DIETICIAN')
            return 'Dietetyk'
        else if(role === 'ROLE_DOCTOR')
            return 'Lekarz'
        else if(role === 'ROLE_TRAINER')
            return 'Trener'
        else if(role === 'ROLE_ADMIN')
            return 'Admin'
        else if(role === 'ROLE_BASIC_USER')
            return 'Podstawowy użytkownik'
        else
            return 'Brak informacji'
    },
    mapRoleRequestStatus(status) {
        if(status === 'ACCEPTED')
            return 'Zaakceptowano'
        else if(status === 'REJECTED')
            return 'Odrzucono'
        else if(status === 'PENDING')
            return 'Oczekujące'
        else if(status === 'CANCELLED')
            return 'Anulowano'
        else
            return 'Brak informacji'
    },
    mapExerciseType(type) {
        if(type === 'STRENGTH')
            return 'Siłowe'
        else if(type === 'CARDIO')
            return 'Kardio'
        else if(type === 'OTHER')
            return 'Inne'
        else
            return 'Brak informacji'
    },
    mapTrainingDifficulty(difficulty) {
        if(difficulty === 'EASY')
            return 'Łatwy'
        else if(difficulty === 'MEDIUM')
            return 'Średni'
        else if(difficulty === 'HARD')
            return 'Trudny'
        else
            return 'Brak informacji'
    },
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
    getWeekRangeFromMonday(mondayDate){
        console.log('range date', mondayDate)
        let from = mondayDate.getDate().toString().padStart(2, '0') + '.' + eval(mondayDate.getMonth()+1).toString().padStart(2, '0');
        mondayDate.setDate(mondayDate.getDate() + 6);
        let to = mondayDate.getDate().toString().padStart(2, '0') + '.' + eval(mondayDate.getMonth()+1).toString().padStart(2, '0');
        return from + " - "+ to
    },
    getDatesArrayFromMonday(d1){
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
