<template>
    <div >
    
        <navigation-bar/>

        <b-row class="m-4">
            <b-col><h3>Cursos disponibles</h3></b-col>
            <b-button v-if="this.$store.state.user.legajo==3710681"
                      align-self="end" 
                      variant="info" 
                      size="md" 
                      class="my-2 my-sm-0" 
                      type="submit" 
                      @click="goToReporte()" >Reporte</b-button>

            <b-button align-self="end" 
                      variant="info" 
                      size="md" 
                      class="my-2 my-sm-0 ml-2" 
                      type="submit" 
                      @click="goToNuevoCurso()" >Cargar Nuevo Curso</b-button>
        </b-row>

        <b-container fluid>

        <b-row >
            <b-col xs="12" sm="6" md="3" lg="3" v-for="curso in cursosList" :key="curso.id" class="d-flex">
                <b-card class="mb-4"
                    :header="curso.categoria"
                    header-tag="header"
                    footer="Inscriptos: 0"
                    footer-tag="footer"
                    :title="curso.titulo"
                    style="flex: 1 0 0%"
                    align="center"
                >
                    <b-card-text>{{curso.descripcion}}</b-card-text>
                    <div >
                        <b-button class="m-3" variant="primary" @click="verDetalles(curso)">Detalles</b-button>
                        <b-button v-if="curso.usuario==$store.state.user.username" variant="primary" @click="editarCurso(curso)">Editar</b-button>                        
                    </div>
                </b-card>
            </b-col>
        </b-row>
        </b-container>
    </div>
</template>
<script>
import NavigationBar from '@/components/NavigationBar.vue'
export default {
    data(){
	    return {
            cursosList: []
        }
    },
    name: 'Usuarios',

    mounted() {        
        this.$http({
            method: "GET",
            "url": "api/cursos/usuario/" + this.$store.state.user.legajo
        }).then(response => {
            this.cursosList = response.data;
        }, error => {
            console.error(error);
            console.log("Logout por vencimiento de session")
            this.$store.commit('logout')
            //this.$router.push("/login");
        });
    },    

	methods: {
        verDetalles(curso){
            this.$store.commit('setCursoSeleccionado', curso)
            this.$router.push("/detallecurso");
        },
        editarCurso(curso){       
            this.$router.push({name: 'formcurso', params: { curso: curso} })     
        },
        goToNuevoCurso(){
            this.$router.push("/formcurso");            
        },
        goToReporte(){
            this.$router.push("/reporte");            
        }

    },
    components: {
        NavigationBar
    }   
}
</script>
<style>
</style>