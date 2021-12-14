<template>
    <div id="login-admin-page" class="container-fluid mw-100">
        <div class="row justify-content-center">
            <div class="col-4 mt-5 text-start">
                <label for="mailInput" class="form-label">Adres e-mail</label>
                <input
                    type="email"
                    class="form-control"
                    id="mailInput"
                    placeholder="Adres e-mail"
                    v-model="email"
                    @keydown.enter="login"
                >
            </div>

        </div>
        <div class="row justify-content-center">
            <div class="col-4 mt-4 text-start">
                <label for="passwordInput" class="form-label">Hasło</label>
                <input
                    type="password"
                    class="form-control"
                    id="passwordInput"
                    placeholder="Hasło"
                    v-model="password"
                    @keydown.enter="login"
                >
            </div>
        </div>
        <div class="row justify-content-center" v-if="errorLogin">
            <div class="col-4 mt-1 text-start">
                <p class="has-error">
                    Niepoprane dane!
                </p>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-4 mt-3 text-end">
                <button type="submit" class="btn btn-primary" @click="login">Zaloguj się</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "LoginAdminView",
    data () {
        return {
            email: "",
            password: "",
            errorLogin: false,
            submittingLogin: false
        }
    },
    methods: {
        login() {
            console.log('email: ' + this.email + ' pass: ' + this.password)
            this.submittingLogin = true
            this.errorLogin = false
            const data = {
                "email": this.email,
                "password": this.password
            }
            const url = `${this.apiURL}admin`
            this.axios.post(url, data).then((response) => {
                this.$store.commit('setToken', response.data['jwt']);
                console.log(this.$store.getters.getToken)
                this.getUserInfo()
            }).catch(error => {
                console.log(error.response);
                this.errorLogin = true
            });

            this.submittingLogin = false
        },
        getUserInfo () {
            const url = `${this.apiURL}profile/my`

            this.axios.get(url, {headers: {Authorization: `Bearer ${this.$store.getters.getToken}`}}).then((response) => {
                this.$store.commit('setFirstName', response.data['firstName']);
                this.$store.commit('setLastName', response.data['lastName']);
                let roles = []
                response.data['roles'].forEach((e) => {
                    roles.push(e['role'])
                })
                this.$store.commit('setRoles', roles);
                console.log('role', this.$store.getters.getRoles)
                this.$router.push({name: 'RoleRequestsViewAdmin'})
            }).catch(error => {
                console.log(error.response);
            });
            return ''
        },
    }
}
</script>

<style scoped>
label {
    text-align: left;
}
</style>