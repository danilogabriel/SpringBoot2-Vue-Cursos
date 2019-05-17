<template>
<div >
    <navigation-bar/>
    <b-row class="m-4">
        <b-col><h3>Reporte de Inscriptos por Curso</h3></b-col>
        <b-button v-if="this.$store.state.user.legajo==3710681"
                    align-self="end" 
                    variant="primary" 
                    size="md" 
                    class="my-2 my-sm-0" 
                    type="submit" 
                    @click="goToHome()" >Volver</b-button>

    </b-row>
    <!-------------------------------------------------------------------------------->
    <b-table :items="inscripcionesList" :fields="fieldsCurso" >
      
      <template slot="inscriptos" slot-scope="row">
        <b-button size="sm" @click="row.toggleDetails" class="mr-2">
           Inscriptos
        </b-button>
      </template>

      <template slot="row-details" slot-scope="row">
        <b-card >
            <b-table :items="row.item.usuarios" :fields="fieldsUsuario">  
            </b-table>
        </b-card>
      </template>
    </b-table>

    <!---------------------------------------------------------------------------
    <pre>{{ inscripcionesList }}</pre>
    ------>

    <div class="text-center"> 
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
            fieldsCurso: [
                { key: 'curso.titulo',       label: 'Título', sortable: true, sortDirection: 'desc' },
                { key: 'curso.tipo',         label: 'Tipo', sortable: true, class: 'text-center' },
                { key: 'curso.categoria',    label: 'Categoría' },
                { key: 'curso.instructores', label: 'Instructores' },
                { key: 'curso.perfilAlumno', label: 'Perfil' },
                { key: 'inscriptos',         label: 'Inscriptos' }
            ],
            fieldsUsuario: [
                { key: 'NOMBRE',   label: 'Nombre', sortable: true, sortDirection: 'asc' },
                { key: 'LEGAJO',   label: 'Legajo', sortable: true, sortDirection: 'asc' },
                { key: 'USERNAME', label: 'e-mail', formatter: value => { return value+'@afip.gob.ar' } }
            ]
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
