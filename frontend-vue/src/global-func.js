export const func_global = {
    mapSex(sex){
        if(sex == 'WOMAN')
            return "Kobieta"
        else if(sex == 'MAN')
            return "Mężczyzna"
        else
            return 'Brak informacji'
    },
    mapBoolean(state){
        if(state)
            return "Tak"
        else
            return "Nie"
    },
    mapActivity(activity){
        if(activity == 'VERY_LOW')
            return "Bardzo niska"
        else if(activity == "LOW")
            return "Niska"
        else if(activity == "MEDIUM")
            return "Średnia"
        else if(activity == "HIGH")
            return "Wysoka"
        else if(activity == "VERY_HIGH")
            return "Bardzo wysoka"
        else
            return "Brak informacji"
    },
    mapDietGoal(goal){
        if(goal == 'FAST_LOSE_WEIGHT')
            return "Szybka utrata wagi"
        else if(goal == "LOSE_WEIGHT")
            return "Utrata wagi"
        else if(goal == "KEEP_WEIGHT")
            return "Utrzymanie wagi"
        else if(goal == "GAIN_WEIGHT")
            return "Przybranie na wadze"
        else if(goal == "FAST_GAIN_WEIGHT")
            return "Szybkie przybranie na wadze"
        else if(goal == "GAIN_MUSCLES")
            return "Budowa masy mięśniowej"
        else
            return "Brak informacji"
    },
    mapAilmentType(ailmentType){
        if(ailmentType == 'ALLERGY')
            return "Alergia"
        else if(ailmentType == "ILLNESS")
            return "Choroba"
        else if(ailmentType == "INJURY")
            return "Kontuzja"
        else if(ailmentType == "PHYSICAL_CONDITION")
            return "Stan fizyczny"
        else
            return "Brak informacji"
    },
    mapBMIResult(result){
        if(result == 'SEVERELY_UNDERWEIGHT')
            return "Wychudzenie"
        else if(result == 'UNDERWEIGHT')
            return "Niedowaga"
        else if(result == "HEALTHY")
            return "Waga w normie"
        else if(result == "OVERWEIGHT")
            return "Nadwaga"
        else if(result == "OBESE")
            return "Otyłość"
        else
            return "Nieznany"
    },
    mapMeal(meal){
        if(meal == 'BREAKFAST')
            return "ŚNIADANIE"
        else if(meal == 'LUNCH')
            return "LUNCH"
        else if(meal == "DINNER")
            return "OBIAD"
        else if(meal == "SNACK")
            return "PRZEKĄSKA"
        else if(meal == "SUPPER")
            return "KOLACJA"
        else
            return "Nieznany"
    },
    mapGlycemicIndex(index){
        if(index == 'LOW')
            return "Niski"
        else if(index == 'MEDIUM')
            return "Średni"
        else if(index == "HIGH")
            return "Wysoki"
        else if(index == "ANY_RECOMMENDED")
            return "Niski/średni"
        else if(index == "ANY")
            return "Dowolny"
        else
            return "Nieznany"
    },
    mapMeasure(measure){
        if(measure == 'GRAM')
            return "g"
        else if(measure == 'MILLI_GRAM')
            return "mg"
        else if(measure == "MICRO_GRAM")
            return "mcg"
        else if(measure == "KILO_GRAM")
            return "kg"
        else
            return "Nieznana"
    },
    proteinCalories(){
        return 4
    },
    fatCalories(){
        return 9
    },
    carbCalories(){
        return 4
    }
 }
import moment from "moment";

const apiURL = 'http://localhost:8090/'
import axios from "axios";
export const func_global = {

    async importData(myfile, token, type, requestId) {
        // let myfile = this.$refs.myfile;
        let files = myfile.files;
        let file = files[0];
        var formData = new FormData();
        formData.append("file", file);
        return this.uploadFile(formData, type, token, requestId).then((resp) => {
            console.log(resp)
        })

    },

     async uploadFile (data, type, token, requestId) {
        let url;
        if (type === 'roleRequest')
            url = `${apiURL}role-request/import/${requestId}/`
        else if (type === 'profilePicture')
            url = `${apiURL}profile/import`
         return axios.post(url, data, {headers: {Authorization: `Bearer ${token}`, 'Content-Type': 'multipart/form-data'}}).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error.response)
        });
    },
    downloadPdfFile (url, token) {
        axios.get(url, {headers: {Authorization: `Bearer ${token}`, 'Accept': 'application/pdf'}, responseType: 'arraybuffer'}).then((response) => {
            console.log(response.data)
            const blob = new Blob([response.data], { type: 'application/pdf' })
            const objectUrl = window.URL.createObjectURL(blob)
            window.open(objectUrl)
        }).catch(error => {
            console.log(error.response.status)
        });
    },
    async downloadPhoto (url, token) {
        let data
        const urlCreator = window.URL || window.webkitURL;
        return axios.get(url, {headers: {Authorization: `Bearer ${token}`, 'Accept': 'image/png'}, responseType: 'arraybuffer'}).then((response) => {
            data = new Blob([response.data], { type: 'image/png' })
            return urlCreator.createObjectURL(data);
        }).catch(error => {
            console.log(error.response.status)
            return data
        });
    },
    formatDate(date) {
        if (date) {
            return moment(String(date)).format('DD/MM/YYYY')
        }
    },
    formatTime(date){
        if (date) {
            return moment(String(date)).format('HH:mm')
        }
    },
    formatDateTime(date){
        if (date) {
            return moment(String(date)).format('DD/MM/YYYY HH:mm')
        }
    },
    formatDateDatePicker(date) {
        if (date) {
            return moment(String(date)).format('YYYY-MM-DD')
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
    mapSportTag(tag) {
        if(tag === 'WEIGHT_TRAINING')
            return 'Trening siłowy'
        else if(tag === 'CARDIO')
            return 'Trening kardio'
        else if(tag === 'PILATES')
            return 'Pilates'
        else if(tag === 'YOGA')
            return 'Joga'
        else
            return 'Brak informacji'
    },
    mapNutritionTag(tag) {
        if(tag === 'VEGETARIAN')
            return 'Dieta wegetariańska'
        else if(tag === 'VEGAN')
            return 'Dieta wegańska'
        else if(tag === 'GLUTEN_FREE')
            return 'Dieta bezglutenowa'
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
