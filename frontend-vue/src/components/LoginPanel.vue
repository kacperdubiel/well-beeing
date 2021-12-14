<template>
    <div id="panel">

        <div class="row w-80 mb-3">
            <label class="form-label text-start text-white fw-bolder" for="login-mail">Adres e-mail:</label>
            <input
                id="login-mail"
                v-model="email"
                :class="{ 'has-error': submittingLogin && invalidEmail || wrongLoginData}"
                class="login-input"
                placeholder="Adres-email"
                type="email"
                @focus="clearStatus"
                @keypress="clearStatus"
                @keydown.enter="login"
            />
        </div>
        <div class="row w-80 mb-1">
            <label class="form-label text-start text-white fw-bolder" for="login-password">Hasło:</label>
            <input
                id="login-password"
                v-model="password"
                :class="{ 'has-error': submittingLogin && invalidPassword || wrongLoginData}"
                class="login-input"
                placeholder="Hasło"
                type="password"
                @focus="clearStatus"
                @keypress="clearStatus"
                @keydown.enter="login"
            />
        </div>
        <div class="row w-80 text-end">
            <p v-if="wrongLoginData" class="has-error">
                Niepoprawne dane logowania!
            </p>
            <p v-if="errorLogin" class="has-error">
                Proszę uzupełnić wszystkie dane poprawnie!
            </p>
        </div>
        <div class="row w-80 mt-4 mb-5">
            <button class="account-btn pink-btn" @click="login">
                Zaloguj się
            </button>
        </div>

        <div class="row w-80">
            <button class="account-btn grey-btn" data-bs-target="#registerModal" data-bs-toggle="modal"
                    @click="this.submittingLogin=false">
                Utwórz nowe konto
            </button>

            <!-- Modal -->
            <div id="registerModal" aria-hidden="true" aria-labelledby="registerModalLabel" class="modal fade"
                 tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 id="registerModalLabel" class="modal-title ms-2">
                                {{ !successRegister ? 'Podaj dane do rejestracji' : '' }}</h5>
                            <button aria-label="Close" class="btn-close" data-bs-dismiss="modal" type="button"
                                    @click="clearInputs(); clearStatusWithTimeout()"></button>
                        </div>
                        <div class="modal-body">

                            <div id="modal-container" class="container-fluid">
                                <div v-if="!successRegister" class="col-11 mx-auto">

                                    <div class="row">
                                        <label class="form-label text-start" for="register-mail">Adres e-mail:</label>
                                        <input
                                            id="register-mail"
                                            v-model="newEmail"
                                            :class="{ 'has-error': submittingRegister && invalidNewEmail || emailTaken}"
                                            class="register-input p-2"
                                            placeholder="Adres-email"
                                            type="email"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                            @keydown.enter="register"
                                        />
                                    </div>
                                    <div class="row text-end">
                                        <p v-if="emailTaken" class="has-error m-0">
                                            Do tego e-mail'a jest już przypisane konto!
                                        </p>
                                    </div>
                                    <div class="row mt-2">
                                        <label class="form-label text-start" for="register-password">Hasło:</label>
                                        <input
                                            id="register-password"
                                            v-model="newPassword"
                                            :class="{ 'has-error': submittingRegister && (invalidNewPassword || invalidEqualPassword)}"
                                            class="register-input p-2"
                                            placeholder="Hasło"
                                            type="password"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                            @keydown.enter="register"
                                        />
                                    </div>
                                    <div class="row mt-2">
                                        <label class="form-label text-start" for="register-password-second">Powtórz
                                            hasło:</label>
                                        <input
                                            id="register-password-second"
                                            v-model="newPasswordSecond"
                                            :class="{ 'has-error': submittingRegister && (invalidNewPasswordSecond || invalidEqualPassword)}"
                                            class="register-input p-2"
                                            placeholder="Powtórz hasło"
                                            type="password"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                            @keydown.enter="register"
                                        />
                                    </div>
                                    <div v-if="errorRegister" class="row text-end">
                                        <p class="has-error m-0">
                                            Proszę uzupełnić wszystkie dane poprawnie!
                                        </p>
                                    </div>
                                    <div v-if="errorEqualPassword" class="row text-end">
                                        <p class="has-error m-0">
                                            Podane hasła się różnią!
                                        </p>
                                    </div>
                                    <div class="row justify-content-center mt-5">
                                        <div class="col-6">
                                            <button class="register-btn grey-btn p-2"
                                                    data-bs-dismiss="modal"
                                                    @click="clearInputs(); clearStatusWithTimeout()">Anuluj
                                            </button>
                                        </div>
                                        <div class="col-6">
                                            <button class="register-btn pink-btn p-2" @click="register">Rejestruj
                                            </button>
                                        </div>
                                    </div>


                                </div>

                                <div v-if="successRegister" class="col-11 mx-auto">
                                    <p>
                                        Rejestracja przebiegła pomyślnie!
                                    </p>
                                    <p>
                                        Możesz zalogować się swoimi danymi.
                                    </p>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "LoginPanel",
    data() {
        return {
            email: "",
            password: "",
            newEmail: "",
            newPassword: "",
            newPasswordSecond: "",
            wrongLoginData: false,
            errorLogin: false,
            errorRegister: false,
            errorEqualPassword: false,
            successRegister: false,
            emailTaken: false,
            submittingLogin: false,
            submittingRegister: false
        }
    },
    methods: {
        login() {
            this.submittingLogin = true
            this.clearStatus()
            if (this.invalidEmail || this.invalidPassword) {
                this.errorLogin = true
                return
            }
            const data = {
                "email": this.email,
                "password": this.password
            }
            const url = `${this.apiURL}authenticate`
            this.axios.post(url, data).then((response) => {
                this.$store.commit('setToken', response.data['jwt']);
                localStorage.setItem('token', response.data.jwt)
                this.$store.commit('setEmail', this.email);
                this.getUserInfo()
                this.clearInputs()
            }).catch(error => {
                console.log(error.response);
                this.wrongLoginData = true
            });

            this.submittingLogin = false
        },
        register() {
            this.submittingRegister = true
            this.clearStatus()
            if (this.invalidNewEmail || this.invalidNewPassword || this.invalidNewPasswordSecond) {
                this.errorRegister = true
                return
            }

            if (this.invalidEqualPassword) {
                this.errorEqualPassword = true
                return
            }

            const data = {
                "email": this.newEmail,
                "password": this.newPassword
            }
            const url = `${this.apiURL}register`
            this.axios.post(url, data).then((response) => {
                console.log(response.data)
                this.successRegister = true
                this.clearInputs()
            }).catch(error => {
                if (error.response.status === 409) {
                    this.emailTaken = true
                }
            });
            this.submittingRegister = false
        },
        getUserInfo() {
            const url = `${this.apiURL}profile/my`

            this.axios.get(url, {headers: {Authorization: `Bearer ${this.$store.getters.getToken}`}}).then((response) => {
                this.$store.commit('setFirstName', response.data['firstName']);
                this.$store.commit('setLastName', response.data['lastName']);
                this.$store.commit('setProfileId', response.data['id']);
                this.$store.commit('setProfileImageSrc', response.data['profileImgPath']);
                this.$store.commit('setDoctorProfile', response.data['doctorProfile']);
                this.$store.commit('setDieticianProfile', response.data['dieticianProfile']);
                this.$store.commit('setTrainerProfile', response.data['trainerProfile']);
                this.$store.commit('setSex', response.data['esex']);

                localStorage.setItem('userId', response.data['id'])
                let roles = []
                response.data['roles'].forEach((e) => {
                    roles.push(e['role'])
                })
                this.$store.commit('setRoles', roles);
                let specializations = []
                this.$store.commit('setSpecialization', specializations);
                if (response.data['doctorProfile'] !== null) {
                    response.data['doctorProfile']['specializations'].forEach((e) => {
                        specializations.push(e['name'])
                    })
                    this.$store.commit('setSpecialization', specializations);
                }
                this.$router.push({name: 'FeedView'})
            }).catch(error => {
                console.log(error.response);
            });
        },
        clearStatus() {
            this.errorLogin = false
            this.errorRegister = false
            this.errorEqualPassword = false
            this.wrongLoginData = false
            this.successRegister = false
            this.emailTaken = false
        },
        clearStatusWithTimeout() {
            setTimeout(() => {
                this.clearStatus()
            }, 500)
        },
        clearInputs() {
            this.email = ""
            this.password = ""
            this.newEmail = ""
            this.newPassword = ""
            this.newPasswordSecond = ""
        }

    },
    computed: {
        invalidEmail() {
            return this.email === '' || !this.email.includes('@')
        },
        invalidNewEmail() {
            return this.newEmail === '' || !this.newEmail.includes('@')
        },
        invalidPassword() {
            return this.password === ''
        },
        invalidNewPassword() {
            return this.newPassword === ''
        },
        invalidNewPasswordSecond() {
            return this.newPasswordSecond === ''
        },
        invalidEqualPassword() {
            return this.newPassword !== this.newPasswordSecond
        },
    }
}
</script>

<style scoped>
#panel {
    height: 100%;
}

.w-80 {
    width: 80%;
}

.login-input, .account-btn {
    border-radius: 15px;
    border: none;

}

.login-input {
    box-shadow: 0 0.5rem 0.5rem rgba(0, 0, 0, 0.15) !important;
    padding: 15px 10px;
}

.register-input {
    border-radius: 25px;
    border-color: var(--GREY2);
}

.account-btn {
    color: white;
    font-size: 2vw;
    font-weight: bold;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
    padding: 5px;
}

.pink-btn {
    background: var(--INTENSE-PINK);
}

.grey-btn {
    background: var(--GREY2);
}

input.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

p.has-error {
    color: var(--INTENSE-PINK);
    /*font-weight: bold;*/
}

.register-btn {
    color: white;
    font-size: 120%;
    font-weight: bold;
    border-radius: 15px;
    border: none;
    width: 100%;
}
</style>
