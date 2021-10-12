<template>
    <div id="panel">
        <div class="row text-end align-content-end">
            <p v-if="wrongData" class="has-error">
                Niepoprawne dane logowania!
            </p>
        </div>

        <div class="row mb-3">
            <input
            type="text"
            placeholder="Adres-email"
            v-model="email"
            class="login-input"
            :class="{ 'has-error': wrongData}"
            @focus="clearStatus"
            @keypress="clearStatus"
            />

        </div>
        <div class="row mb-4">
            <input
            type="password"
            placeholder="Hasło"
            v-model="password"
            class="login-input"
            :class="{ 'has-error': wrongData}"
            @focus="clearStatus"
            @keypress="clearStatus"
            />
        </div>
        <div class="row mb-5">
            <button id="login-btn" class="account-btn" @click="login">
                Zaloguj się
            </button>
        </div>
        <div class="row">
            <button id="register-btn" class="account-btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Utwórz nowe konto
            </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Podaj dane do rejestracji</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="col-11 text-center">
                                <div class="row mb-4">
                                    <input
                                        type="text"
                                        placeholder="Adres-email"
                                        v-model="newEmail"
                                        class="register-input"
                                    />

                                </div>
                                <div class="row mb-4">
                                    <input
                                        type="password"
                                        placeholder="Hasło"
                                        v-model="newPassword"
                                        class="register-input"
                                    />
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Anuluj</button>
                            <button type="button" class="btn btn-primary" @click="register">Rejestruj</button>
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
            wrongData: false
        }
    },
    methods: {
        login() {
            // this.axios.post()
            console.log('email: ' + this.email + ' pass: ' + this.password)
            const data = {
                "email": this.email,
                "password": this.password
            }
            this.axios.post('http://localhost:8090/' + 'authenticate', data).then((response) => {
                this.$store.commit('setToken', response.data['jwt']);
                console.log(this.$store.getters.getToken)
                this.$router.push({name: 'Feed'})
            }).catch(error => {
                console.log(error.response);
                this.wrongData = true
                console.log(this.wrongData)
            });
            this.email = ""
            this.password = ""
        },
        register() {
            console.log('email: ' + this.newEmail + ' pass: ' + this.newPassword)
            const data = {
                "email": this.newEmail,
                "password": this.newPassword
            }
            this.axios.post('http://localhost:8090/' + 'register', data).then((response) => {
                console.log(response.data)
            })
            this.newEmail = ""
            this.newPassword = ""
        },
        clearStatus() {
            this.wrongData = false
        }

    }
}
</script>

<style scoped>
#panel {
    height: 100%;
}

.row {
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
    height: 100%;
    width: 100%;
    border-radius: 25px;
    border-color: var(--GREY2);
}

.account-btn {
    color: white;
    font-size: 2vw;
    font-weight: bold;
}

#login-btn {
    background: var(--INTENSE-PINK);
}

#register-btn {
    background: var(--GREY2);
}

input.has-error {
    border: 1px solid var(--INTENSE-PINK);
}

p.has-error {
    color: var(--INTENSE-PINK);
    font-weight: bold;
}


</style>