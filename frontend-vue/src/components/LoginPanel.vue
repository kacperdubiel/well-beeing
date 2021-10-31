<template>
    <div id="panel">


        <div class="row w-80 mb-3">
            <input
            type="email"
            placeholder="Adres-email"
            v-model="email"
            class="login-input"
            :class="{ 'has-error': submittingLogin && invalidEmail || wrongLoginData}"
            @focus="clearStatus"
            @keypress="clearStatus"
            />

        </div>
        <div class="row w-80">
            <input
            type="password"
            placeholder="Hasło"
            v-model="password"
            class="login-input"
            :class="{ 'has-error': submittingLogin && invalidPassword || wrongLoginData}"
            @focus="clearStatus"
            @keypress="clearStatus"
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
        <div class="row w-80 mt-2 mb-5">
            <button class="account-btn pink-btn" @click="login">
                Zaloguj się
            </button>
        </div>
        <div class="row w-80">
            <button class="account-btn grey-btn" data-bs-toggle="modal" data-bs-target="#registerModal" @click="this.submittingLogin=false">
                Utwórz nowe konto
            </button>

            <!-- Modal -->
            <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title ms-2" id="registerModalLabel">{{!successRegister ? 'Podaj dane do rejestracji' : ''}}</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearInputs(); clearStatusWithTimeout()"></button>
                        </div>
                        <div class="modal-body">

                            <div class="container-fluid" id="modal-container">
                                <div v-if="!successRegister" class="col-11 mx-auto">
                                    <div class="row">
                                        <input
                                            type="email"
                                            placeholder="Adres-email"
                                            v-model="newEmail"
                                            class="register-input p-2"
                                            :class="{ 'has-error': submittingRegister && invalidNewEmail || emailTaken}"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                    <div class="row text-end">
                                        <p v-if="emailTaken" class="has-error m-0">
                                            Do tego e-mail'a jest już przypisane konto!
                                        </p>
                                    </div>
                                    <div class="row mt-3">
                                        <input
                                            type="password"
                                            placeholder="Hasło"
                                            v-model="newPassword"
                                            class="register-input p-2"
                                            :class="{ 'has-error': submittingRegister && invalidNewPassword}"
                                            @focus="clearStatus"
                                            @keypress="clearStatus"
                                        />
                                    </div>
                                    <div v-if="errorRegister" class="row text-end">
                                        <p class="has-error m-0">
                                            Proszę uzupełnić wszystkie dane poprawnie!
                                        </p>
                                    </div>
                                    <div class="row justify-content-end mt-3">
                                        <div class="col-4">
                                            <button class="register-btn grey-btn p-2" @click="clearInputs(); clearStatusWithTimeout()" data-bs-dismiss="modal">Anuluj</button>
                                        </div>
                                        <div class="col-4">
                                            <button class="register-btn pink-btn p-2" @click="register">Rejestruj</button>
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
    data () {
        return {
            email: "",
            password: "",
            newEmail: "",
            newPassword: "",
            wrongLoginData: false,
            errorLogin: false,
            errorRegister: false,
            successRegister: false,
            emailTaken: false,
            submittingLogin: false,
            submittingRegister: false
        }
    },
    methods: {
        login() {
            console.log('email: ' + this.email + ' pass: ' + this.password)
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
                console.log(this.$store.getters.getToken)
                this.clearInputs()
                this.getUserInfo()
                this.$router.push({name: 'Feed'})
            }).catch(error => {
                console.log(error.response);
                this.wrongLoginData = true
            });

            this.submittingLogin = false
        },
        register() {
            console.log('email: ' + this.newEmail + ' pass: ' + this.newPassword)
            this.submittingRegister = true
            this.clearStatus()
            if (this.invalidNewEmail || this.invalidNewPassword) {
                this.errorRegister = true
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
            document.getElementsByClassName('')
        },
        getUserInfo () {
            const url = `${this.apiURL}profile`

            this.axios.get(url, {headers: {Authorization: `Bearer ${this.$store.getters.getToken}`}}).then((response) => {
                this.$store.commit('setFirstName', response.data['firstName']);
                this.$store.commit('setLastName', response.data['lastName']);
                console.log(this.$store.getters.getFirstName)
                console.log(this.$store.getters.getLastName)
                let roles = []
                response.data['roles'].forEach((e) => {
                    roles.push(e['role'])
                })
                this.$store.commit('setRoles', roles);
                console.log('role', this.$store.getters.getRoles)
            }).catch(error => {
                console.log(error.response);
            });
        },
        clearStatus() {
            this.errorLogin = false
            this.errorRegister = false
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
        }
    }
}
</script>

<style scoped>
#panel {
    height: 100%;
}

.w-80 {
    height: 8%;
    width: 80%;
}

.login-input, .account-btn {
    height: 100%;
    width: 100%;
    border-radius: 25px;
    border: none;
}

.register-input {
    height: 200%;
    width: 100%;
    border-radius: 25px;
    border-color: var(--GREY2);
}

.account-btn {
    color: white;
    font-size: 2vw;
    font-weight: bold;
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
    border-radius: 25px;
    border: none;
    width: 100%;
}
</style>