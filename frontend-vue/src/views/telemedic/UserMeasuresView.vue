<template>
    <div class="section-bg telemedic-section">
        <header>
            <telemedic-navbar/>

        </header>
        <section>
            <measures-component :user-id="this.userId"/>
        </section>
    </div>
</template>

<script>
import TelemedicNavbar from "@/components/telemedic/TelemedicNavbar";
import MeasuresComponent from "@/components/telemedic/MeasuresComponent";

export default {
    name: 'UserMeasuresView',
    components: {
        MeasuresComponent,
        TelemedicNavbar
    },
    data() {
        return {
            userId: "",
        }
    },
    methods: {
        getProfile(){
            this.axios.get('http://localhost:8090/profile', {
                headers: {
                    Authorization: 'Bearer ' + this.$store.getters.getToken
                }
            })
                .then(response => {
                    this.userId = response.data.id;
                })
                .catch(e => {
                    console.log(e);
                })
        },
    },
    created() {
        this.getProfile();
    },
}
</script>

<style scoped>
    .telemedic-section {
        padding: 20px;
    }

</style>