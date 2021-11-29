<template>
    <div class="container-fluid" style="width: 100%;">
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
        <div style="width:100%;" class="start-flex">
            <span style="color:black;">{{label.description}}</span>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title start-flex">
            <h6 class="title">ZALECANA DLA CHORÓB / ALERGII / STANÓW FIZYCZNYCH</h6>
        </div>
        <div>
            <table style="text-align: start; color: var(--GREY3);" class="table table-hover table-borderless">
                <thead>
                    <th class="col-lg-4">Nazwa</th>
                    <th class="col-lg-2">Typ</th>
                    <th class="col-lg-6">Opis</th>
                </thead>
                <tbody>
                    <tr v-for="ailment in label.labeledAilments" :key="ailment.id">
                        <th class="col-lg-4">{{ailment.name}}</th>
                        <td class="col-lg-2">{{this.$func_global.mapAilmentType(ailment.type)}}</td>
                        <td class="col-lg-6">{{ailment.description}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <hr class="hr-dish title-line"/>
        <div class="title start-flex">
            <h6 class="title">ZALECANA DLA DIET</h6>
        </div>
        <div>
            <table style="text-align: start; color: var(--GREY3);" class="table table-hover table-borderless">
                <thead>
                    <th class="col-lg-4">Nazwa</th>
                    <th class="col-lg-2"></th>
                    <th class="col-lg-6">Opis</th>
                </thead>
                <tbody>
                    <tr v-for="diet in label.labeledDiets" :key="diet.id">
                        <th class="col-lg-4">{{diet.name}}</th>
                        <td class="col-lg-2"/>
                        <td class="col-lg-6">{{diet.description}}</td>
                    </tr>
                </tbody>
            </table>
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