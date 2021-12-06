<template>
    <div class="section-bg">
        <div class="text-start p-3">
            <button class="no-bg">
                <router-link :to="{ name: 'MyProfileView' }" class="d-inline-flex align-items-center">
                    <font-awesome-icon :icon="['fa', 'chevron-left']" />
                    <h3 class="ms-2 mb-0">Profil</h3>
                </router-link>

            </button>

        </div>
        <div class="accordion accordion-flush p-3" id="accordion-profile-edit">
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-mail">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-mail" aria-expanded="true" aria-controls="collapse-mail">
                        E-mail
                    </button>
                </h2>
                <div id="collapse-mail" class="accordion-collapse collapse" aria-labelledby="heading-mail" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">

                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-8 ">
                                <label for="input-mail" class="form-label">Adres e-mail:</label>
                                <input
                                    type="email"
                                    class="form-control"
                                    id="input-mail"
                                    v-model="email"
                                    :class="{ 'has-error': (submittingChangeEmail && (invalidEmail || errorEmailTaken))}"
                                    @focus="clearStatusEmail"
                                    @keypress="clearStatusEmail"
                                    @keyup.enter="changeEmail"
                                >
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorEmailTaken">
                            <div class="col">
                                <p class="has-error m-0">
                                    Istnieje już konto przypisane do tego e-maila!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorChangeEmail">
                            <div class="col">
                                <p class="has-error m-0">
                                    Proszę poprawnie uzupełnić wszystkie pola!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorSameEmail">
                            <div class="col">
                                <p class="has-error m-0">
                                    Proszę podać nowego maila, ten używany jest obecnie!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="successChangeEmail">
                            <div class="col">
                                <p class="success-social m-0">
                                    Zmiana e-maila przebiegła pomyślnie!
                                </p>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <button class="btn-panel-social-outline ms-auto" @click="changeEmail">
                                Zapisz zmiany
                            </button>
                        </div>


                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-password">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-password" aria-expanded="false" aria-controls="collapse-password">
                        Hasło
                    </button>
                </h2>
                <div id="collapse-password" class="accordion-collapse collapse" aria-labelledby="heading-password" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-8 ">
                                <label for="input-curr-pass" class="form-label">Aktualne hasło:</label>
                                <input
                                    type="password"
                                    autocomplete="new-password"
                                    class="form-control"
                                    id="input-curr-pass"
                                    v-model="currentPassword"
                                    :class="{ 'has-error': (submittingChangePassword && (invalidCurrentPassword || errorCurrentPassword))}"
                                    @focus="clearStatusPassword"
                                    @keypress="clearStatusPassword"
                                    @keydown.enter="changePassword"
                                >
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-8 ">
                                <label for="input-new-pass" class="form-label">Nowe hasło:</label>
                                <input
                                    type="password"
                                    autocomplete="new-password"
                                    class="form-control"
                                    id="input-new-pass"
                                    v-model="newPasswordFirst"
                                    :class="{ 'has-error': (submittingChangePassword && (invalidNewPasswordFirst || invalidEqualNewPassword))}"
                                    @focus="clearStatusPassword"
                                    @keypress="clearStatusPassword"
                                    @keydown.enter="changePassword"
                                >
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-8 ">
                                <label for="input-new-pass-again" class="form-label">Powtórz nowe hasło:</label>
                                <input
                                    type="password"
                                    autocomplete="new-password"
                                    class="form-control"
                                    id="input-new-pass-again"
                                    v-model="newPasswordSecond"
                                    :class="{ 'has-error': (submittingChangePassword && (invalidNewPasswordSecond || invalidEqualNewPassword))}"
                                    @focus="clearStatusPassword"
                                    @keypress="clearStatusPassword"
                                    @keydown.enter="changePassword"
                                >
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorCurrentPassword">
                            <div class="col">
                                <p class="has-error m-0">
                                    Niepoprawne aktualne hasło!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorEqualNewPassword">
                            <div class="col">
                                <p class="has-error m-0">
                                    Nowe hasła się różnią!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorPassword">
                            <div class="col">
                                <p class="has-error m-0">
                                    Proszę uzupełnić wszystkie pola!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="successChangePassword">
                            <div class="col">
                                <p class="success-social m-0">
                                    Zmiana hasła przebiegła pomyślnie!
                                </p>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <button class="btn-panel-social-outline ms-auto" @click="changePassword">
                                Zapisz zmiany
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-photo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-photo" aria-expanded="false" aria-controls="collapse-photo">
                        Zdjęcie profilowe
                    </button>
                </h2>
                <div id="collapse-photo" class="accordion-collapse collapse" aria-labelledby="heading-photo" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-8 ">
                                <label for="input-picture" class="form-label">Wybierz zdjęcie:</label>
                                <input
                                    class="form-control"
                                    type="file"
                                    ref="profilePicture"
                                    id="input-picture"
                                    accept="image/png, image/jpeg, image/png"
                                    @focus="clearStatusPicture"
                                >
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorPicture">
                            <div class="col">
                                <p class="has-error m-0">
                                    Proszę załączyć plik!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="successChangePicture">
                            <div class="col">
                                <p class="success-social m-0">
                                    Pomyślnie zmieniono zdjęcie profilowe!
                                </p>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <button class="btn-panel-social-outline ms-auto" @click="changeProfilePicture">
                                Zapisz zmiany
                            </button>
                        </div>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-personal">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-personal" aria-expanded="false" aria-controls="collapse-personal">
                        Dane personalne
                    </button>
                </h2>
                <div id="collapse-personal" class="accordion-collapse collapse" aria-labelledby="heading-personal" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-8 ">
                                <label for="input-first-name" class="form-label">Imię:</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="input-first-name"
                                    v-model="firstName"
                                    :class="{ 'has-error': (submittingChangePersonalInfo && invalidFirstName)}"
                                    @focus="clearStatusPersonalInfo"
                                    @keypress="clearStatusPersonalInfo"
                                >
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-8 ">
                                <label for="input-last-name" class="form-label">Nazwisko:</label>
                                <input
                                    type="text"
                                    class="form-control"
                                    id="input-last-name"
                                    v-model="lastName"
                                    :class="{ 'has-error': (submittingChangePersonalInfo && invalidLastName)}"
                                    @focus="clearStatusPersonalInfo"
                                    @keypress="clearStatusPersonalInfo"
                                >
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12">
                                <label for="input-description" class="form-label">Opis:</label>
                                <textarea
                                    class="form-control"
                                    id="input-description"
                                    rows="3"
                                    v-model="description"
                                    @focus="clearStatusPersonalInfo"
                                    @keypress="clearStatusPersonalInfo"
                                >
                                </textarea>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-6">
                                <label for="input-birthday" class="form-label">Data urodzenia:</label>
                                <DatePicker
                                    v-if="birthday"
                                    class="d-block"
                                    id="input-birthday"
                                    v-model="birthday"
                                    @focus="clearStatusPersonalInfo"
                                    @keypress="clearStatusPersonalInfo"
                                    @keydown.enter="changePersonalInfo"
                                />
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="input-sex" class="form-label">Płeć:</label>
                                <div class="form-check" id="input-sex">
                                    <input
                                        class="form-check-input"
                                        type="radio"
                                        name="flexRadioDefault"
                                        id="input-sex-woman"
                                        v-model="sex"
                                        value="WOMAN"
                                        @focus="clearStatusPersonalInfo"
                                        @keypress="clearStatusPersonalInfo"
                                    >
                                    <label class="form-check-label" for="input-sex-woman">
                                        Kobieta
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input
                                        class="form-check-input"
                                        type="radio"
                                        name="flexRadioDefault"
                                        id="input-sex-man"
                                        v-model="sex"
                                        value="MAN"
                                        @focus="clearStatusPersonalInfo"
                                    >
                                    <label class="form-check-label" for="input-sex-man">
                                        Mężczyzna
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorChangePersonalInfo">
                            <div class="col">
                                <p class="has-error m-0">
                                    Proszę uzupełnić obowiązkowe pola!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="successChangePersonalInfo">
                            <div class="col">
                                <p class="success-social m-0">
                                    Pomyślnie zmieniono dane osobowe!
                                </p>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <button class="btn-panel-social-outline ms-auto" @click="changePersonalInfo">
                                Zapisz zmiany
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="heading-tags">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse-tags" aria-expanded="false" aria-controls="collapse-tags">
                        Tagi
                    </button>
                </h2>
                <div id="collapse-tags" class="accordion-collapse collapse" aria-labelledby="heading-tags" data-bs-parent="#accordion-profile-edit">
                    <div class="accordion-body">
                        <div class="row text-start mb-3 px-2">
                            <div class="col-12 col-md-6">
                                <label for="input-tag-sport" class="form-label">Tag sportowy:</label>
                                <select
                                    class="form-select"
                                    aria-label="Default select example"
                                    id="input-tag-sport"
                                    v-model="sportTag"
                                    @focus="clearStatusTags"
                                    @keypress="clearStatusTags"
                                >
                                    <option v-for="tag in sportTags" :key="tag.label" :value="tag.value">{{ tag.label }}</option>
                                </select>
                            </div>
                            <div class="col-12 col-md-6">
                                <label for="input-tag-nutrition" class="form-label">Tag żywieniowy:</label>
                                <select
                                    class="form-select"
                                    aria-label="Default select example"
                                    id="input-tag-nutrition"
                                    v-model="nutritionTag"
                                    @focus="clearStatusTags"
                                    @keypress="clearStatusTags"
                                >
                                    <option v-for="tag in nutritionTags" :key="tag.label" :value="tag.value">{{ tag.label }}</option>
                                </select>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="errorChangeTags">
                            <div class="col">
                                <p class="has-error m-0">
                                    Proszę wybrać tagi!
                                </p>
                            </div>
                        </div>
                        <div class="row text-start mb-3 px-2" v-if="successChangeTags">
                            <div class="col">
                                <p class="success-social m-0">
                                    Pomyślnie zmieniono tagi!
                                </p>
                            </div>
                        </div>
                        <div class="d-flex flex-row">
                            <button class="btn-panel-social-outline ms-auto" @click="changeTags">
                                Zapisz zmiany
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
import {DatePicker} from 'v-calendar';
export default {
    name: "ProfileEdit",
    components: {
        DatePicker
    },
    data() {
        return {
            email: "",
            currentPassword: "",
            newPasswordFirst: "",
            newPasswordSecond: "",
            picturePath: "",
            firstName: "",
            lastName: "",
            description: "",
            sex: "",
            birthday: null,
            sportTag: "",
            sportTags: [
                {label:'-', value:'NONE'},
                {label:'Trening siłowy', value:'WEIGHT_TRAINING'},
                {label:'Kardio', value:'CARDIO'},
                {label:'Pilates', value:'PILATES'},
                {label:'Joga', value:'YOGA'}
            ],
            nutritionTag: "",
            nutritionTags: [
                {label:'-', value:'NONE'},
                {label:'Dieta wegetariańska', value:'VEGETARIAN'},
                {label:'Dieta wegańska', value:'VEGAN'},
                {label:'Dieta bezglutenowa', value:'GLUTEN_FREE'}
            ],

            submittingChangeEmail: false,
            successChangeEmail: false,
            errorChangeEmail: false,
            errorEmailTaken: false,
            errorSameEmail: false,

            submittingChangePassword: false,
            successChangePassword: false,
            errorPassword: false,
            errorCurrentPassword: false,
            errorEqualNewPassword: false,

            submittingChangePicture: false,
            successChangePicture: false,
            errorPicture: false,

            submittingChangePersonalInfo: false,
            successChangePersonalInfo: false,
            errorChangePersonalInfo: false,

            submittingChangeTags: false,
            successChangeTags: false,
            errorChangeTags: false
        }
    },
    methods: {
        getMyProfile() {
            const url = `${this.apiURL}profile/my`
            const token = this.$store.getters.getToken;
            this.axios.get(url, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                this.firstName = response.data['firstName']
                this.lastName = response.data['lastName']
                this.description = response.data['description']
                this.sex = response.data['esex']
                this.birthday = response.data['birthday'] === null ? new Date() : this.$func_global.formatDateDatePicker(response.data['birthday'])
                this.sportTag = response.data['esportTag']
                this.nutritionTag = response.data['enutritionTag']
            }).catch(error => {
                console.log(error.response.status)
            });
        },
        changeEmail() {
            this.submittingChangeEmail = true
            this.clearStatusEmail()

            if (this.invalidEmail) {
                this.errorChangeEmail = true
                return
            }

            if (this.invalidSameEmail) {
                this.errorSameEmail = true
                return
            }

            const url = `${this.apiURL}user/update-email`
            const token = this.$store.getters.getToken;
            const myParams = {
                email: this.email,
            }
            this.axios.post(url, null, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successChangeEmail = true
                this.$store.commit('setEmail', this.email);
                // this.clearInputs()
                this.$store.commit('setToken', response.data['jwt']);
                this.submittingChangeEmail = false
            }).catch(error => {
                console.log(error.response.status)
                this.errorEmailTaken = true
            });
        },
        changePassword() {
            this.submittingChangePassword = true
            this.clearStatusPassword()

            if (this.invalidCurrentPassword || this.invalidNewPasswordFirst || this.invalidNewPasswordSecond) {
                this.errorPassword = true
                return
            }

            if (this.invalidEqualNewPassword) {
                this.errorEqualNewPassword = true
                return
            }

            const url = `${this.apiURL}user/update-password`
            const token = this.$store.getters.getToken;
            const myParams = {
                oldPassword: this.currentPassword,
                password: this.newPasswordFirst
            }
            this.axios.post(url, null, {params: myParams, headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successChangePassword = true
                this.clearInputs()
                this.submittingChangePassword = false
            }).catch(error => {
                console.log(error.response.status)
                this.errorCurrentPassword = true
            });

        },
        changeProfilePicture() {
            this.submittingChangePicture = true
            this.clearStatusPicture()

            if (this.$refs.profilePicture.files.length > 0) {
                this.picturePath = this.$refs.profilePicture.files[0].name
            }

            if (this.invalidProfilePicture) {
                this.errorPicture = true
                return
            }

            this.$func_global.importData(this.$refs.profilePicture, this.$store.getters.getToken, 'profilePicture').then((resp) => {
                const url = `${this.apiURL}profile/export/${this.$store.getters.getProfileId}`
                const token = this.$store.getters.getToken;
                this.$func_global.downloadPhoto(url, token).then(result => {
                    this.$store.commit('setProfileImageSrc', result)
                })
                this.clearInputs()
                this.successChangePicture = true
                console.log(resp)
            })
        },
        changePersonalInfo() {
            this.submittingChangePersonalInfo = true
            this.clearStatusPersonalInfo()

            if (this.invalidFirstName || this.invalidLastName || this.invalidSex || this.invalidBirthday) {
                this.errorChangePersonalInfo = true
                return
            }

            const url = `${this.apiURL}profile`
            const token = this.$store.getters.getToken;
            const updatedFields = {
                firstName: this.firstName,
                lastName: this.lastName,
                description: this.description,
                ESex: this.sex,
                birthday: this.birthday
            }
            this.axios.patch(url, updatedFields, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successChangePersonalInfo = true
                this.submittingChangePersonalInfo = false
                this.$store.commit('setFirstName', this.firstName)
                this.$store.commit('setLastName', this.lastName)
            }).catch(error => {
                console.log(error.response.status)
                this.errorChangePersonalInfo = true
            });
        },
        changeTags() {
            this.submittingChangeTags = true
            this.clearStatusTags()

            if (this.invalidSportTag || this.invalidNutritionTag) {
                this.errorChangeTags = true
                return
            }

            const url = `${this.apiURL}profile`
            const token = this.$store.getters.getToken;
            const updatedFields = {
                eSportTag: this.sportTag,
                eNutritionTag: this.nutritionTag
            }

            this.axios.patch(url, updatedFields, {headers: {Authorization: `Bearer ${token}`}}).then((response) => {
                console.log(response.data)
                this.successChangeTags = true
                this.submittingChangeTags = false
            }).catch(error => {
                console.log(error.response.status)
                this.errorChangeTags = true
            });

        },
        clearStatusEmail() {
            this.successChangeEmail = false
            this.errorChangeEmail = false
            this.errorEmailTaken = false
            this.errorSameEmail = false
        },
        clearStatusPassword() {
            this.errorPassword = false
            this.errorCurrentPassword = false
            this.errorEqualNewPassword = false
            this.successChangePassword = false
        },
        clearStatusPicture() {
            this.successChangePicture = false
            this.errorPicture = false
        },
        clearStatusPersonalInfo() {
            this.successChangePersonalInfo = false
            this.errorChangePersonalInfo = false
        },
        clearStatusTags() {
            this.successChangeTags = false
            this.errorChangeTags = false
        },
        clearInputs() {
            this.currentPassword = ""
            this.newPasswordFirst = ""
            this.newPasswordSecond = ""
            this.picturePath = ""
            this.$refs.profilePicture.value = null
        }
    },
    computed: {
        invalidEmail() {
            return this.email === '' || !this.email.includes('@')
        },
        invalidSameEmail() {
            return this.email === this.$store.getters.getEmail
        },
        invalidCurrentPassword() {
            return this.currentPassword === ''
        },
        invalidNewPasswordFirst() {
            return this.newPasswordFirst.length < 1
        },
        invalidNewPasswordSecond() {
            return this.newPasswordSecond.length < 1
        },
        invalidEqualNewPassword() {
            return this.newPasswordFirst !== this.newPasswordSecond
        },
        invalidProfilePicture() {
            return this.picturePath === ""
        },


        invalidFirstName() {
            return this.firstName === ""
        },
        invalidLastName() {
            return this.lastName === ""
        },
        invalidSex() {
            return this.sex === ""
        },
        invalidBirthday() {
            return this.birthday === null
        },
        invalidSportTag() {
            return this.sportTag === ""
        },
        invalidNutritionTag() {
            return this.nutritionTag === ""
        }
    },
    mounted() {
        this.email = this.$store.getters.getEmail
        this.getMyProfile()
    }
}
</script>

<style scoped>
.accordion-item, .accordion-button {
    background-color: var(--GREY2);
    color: white;
}

.accordion-button:not(.collapsed) {
    color: var(--DARK-YELLOW);
    font-weight: bolder;
}

.accordion-button:not(.collapsed)::after {
    background-image: url("data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='%23F9BA0F'><path fill-rule='evenodd' d='M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z'/></svg>");
}

.accordion-button:focus {
    box-shadow: none;
}

.accordion-button::after {

    background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16' fill='%23ffffff'%3e%3cpath fill-rule='evenodd' d='M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z'/%3e%3c/svg%3e");
}
a {
    color: white;
    text-decoration: none;
}


</style>