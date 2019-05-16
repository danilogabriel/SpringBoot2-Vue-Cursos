<template>

<div >
    <navigation-bar/>

    <h3 class="m-2">Reporte</h3>
    <pre>{{ inscripcionesList }}</pre>
    <div align="center">
        <b-button class="m-3 width: 200px;" style="m-3 width: 200px;"  variant="primary" @click="goToHome()">Volver</b-button>
    </div>    
</div>

</template>

<script>
import NavigationBar from '@/components/NavigationBar.vue'
export default {
    name: 'Reporte',
    mounted() {
        this.$http({
            method: "GET",
            "url": "api/inscripcion/inscriptosporcurso"
            // "url": "api/inscripcion/cursosporlegajo"

        }).then(response => {
            this.inscripcionesList = response.data;
        }, error => {
            console.error(error);
        });
    },
    data() {
        return {
            inscripcionesList: [],
        }
    },    
    methods: {    
      goToHome() {
          this.$router.push("/")
      }
    },
    computed: {

    },
    components: {
        NavigationBar
    }     
}

</script>

<style scoped>
panel {
    box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
    border: 5px solid #53687E;
    max-width: 80%;
    margin: auto;
    margin-bottom: 3rem;
}
</style>
