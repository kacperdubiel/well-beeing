<template>
    <div style="width: 100%;">
        <hr class="hr-dish title-line"/>
        <div class="title start-flex">
            <h6 class="title">NAZWA</h6>
        </div>
        <div class="start-flex">
            <p class="black-text">{{label.name}}</p>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title start-flex">
            <h6 class="title">OPIS</h6>
        </div>
        <div class="start-flex">
            <p class="black-text">{{label.description}}</p>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title start-flex">
            <h6 class="title">ZALECANA DLA CHORÓB / ALERGII / STANÓW FIZYCZNYCH</h6>
        </div>
        <div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item" v-for="ailment in label.labeledAilments" :key="ailment.id">
                    {{ailment.name}}
                </li>
            </ul>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title start-flex">
            <h6 class="title">ZALECANA DLA DIET</h6>
        </div>
        <div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item" v-for="diet in label.labeledDiets" :key="diet.id">
                    {{diet.name}}
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: "LabelComponent",
    props: {
        label:  {
            type: Object
        }
    }
}
</script>

<style scoped>
    .hr-dish {
        border: 0;
        clear:both;
        display:block;
        width: 100%;
        margin-top: 8px;          
        background-color:var(--GREY1);
        height: 1px;
    }
    .title-line {
        margin-bottom: 0px;
    }

    .title {
        color: var(--GREY1);
        margin-top: 2px;
        margin-bottom: 10px;
        font-size: 10pt;
    }
    .start-flex{
        align-items: flex-start;
        display: flex;
        text-align: start;
        justify-content: flex-start;
    }
    .black-text{
        color: black;
    }
</style>