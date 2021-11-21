<template>
    <div id="login-page" class="container-fluid mw-100">
        <div class="row" id="logo">
            <div id="logo-rel">
                <img id="bee-img" src="@/assets/bee.png" alt="Bee image" >
                <h1 id="logo-text">Well-beeing</h1>
            </div>
        </div>

        <div class="row" id="content">
            <div class="col-md-7 col-sm-11" id="info">
                <h1 id="info-text">Zadbaj z nami<br />o swoje zdrowie<br />i sylwetkę już dziś!</h1>
                <img id="doctor-img" src="@/assets/doctor.png" alt="Doctor image">
                <img id="food-img" src="@/assets/food.png" alt="Food image">
            </div>
            <div class="col-md-5 col-sm-11 ps-5 pt-5 mt-5">
                <login-panel/>
            </div>
        </div>
    </div>
</template>

<script>
// @ is an alias to /src
import LoginPanel from "@/components/LoginPanel";


export default {
    name: 'Home',
    components: {
        LoginPanel
    }
}
</script>

<style scoped>
#login-page {
    width: 100%;
    height: 100vh;
    background-image: linear-gradient(to right, var(--DARK-YELLOW) 50%, var(--LIGHT-YELLOW));
}

#logo {
    text-align: left;
    color: var(--BLACK);
}

#logo-rel {
    position: relative;
    height: 20vh;
}

#bee-img {
    z-index: 2;
    position: absolute;
    top: 20px;
    left: 50px;
    height: 70px;
}

#logo-text {
    position: absolute;
    top: 15px;
    left: 110px;
    font-family: Mistral,serif;
    font-size: 400%;
    z-index: 1;
}

#content {
    height: 80vh;
}

#info {
    position: relative;
}

#info-text {
    position: absolute;
    top: 25%;
    left: 20%;
    font-weight: bold;
    font-size: 4vw;
    color: white;
    text-align: left;
    font-style: italic;
    z-index: 2;
}

#doctor-img {
    position: absolute;
    top: 22%;
    left: -5%;
    height: 22vw;
    z-index: 3;
}

#food-img {
    position: absolute;
    top: 10%;
    left: 70%;
    height: 15vw;
    z-index: 1;
}

</style>
