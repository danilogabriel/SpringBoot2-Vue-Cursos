<template>

<div >
    <navigation-bar/>

    <h3 class="m-2">Nuevo Curso</h3>

    <div class="container">
        <b-col class="mw-6">           
              <b-input-group prepend="Titulo" class="mt-3">
                <b-form-input v-model="curso.titulo"></b-form-input>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.titulo') }}</div>

              <b-input-group prepend="Tipo  de Capacitación" class="mt-3">
                <b-form-select v-model="curso.tipo" :options="tipoCursoOptions"></b-form-select>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.tipo') }}</div>

              <b-input-group prepend="Categoría" class="mt-3">
                <b-form-select v-model="curso.categoria" :options="categoriaOptions"></b-form-select>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.categoria') }}</div>

              <b-input-group prepend="Descripcion" class="mt-3">
                <b-form-textarea id="textarea"  rows="3" max-rows="6" v-model="curso.descripcion"></b-form-textarea>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.descripcion') }}</div>

              <b-input-group prepend="Contenidos" class="mt-3">
                <b-form-textarea id="textarea"  rows="3" max-rows="6" v-model="curso.contenidos"></b-form-textarea>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.contenidos') }}</div>

              <b-input-group prepend="Instructores" class="mt-3">
                <b-form-textarea id="textarea"  rows="3" max-rows="6" v-model="curso.instructores"></b-form-textarea>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.instructores') }}</div>

              <b-input-group prepend="Perfil alumno" class="mt-3">
                <b-form-select v-model="curso.perfilAlumno" :options="perfilOptions"></b-form-select>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.titulo') }}</div>

              <b-input-group prepend="Cant. Horas" class="mt-3">
                <b-form-input v-model="curso.cantHoras"></b-form-input>
              </b-input-group>
               <div class="text-danger">{{ validation.firstError('curso.cantHoras') }}</div>

              <b-input-group prepend="Max. Asistentes" class="mt-3 m">
                <b-form-input v-model="curso.maxAlumnos"></b-form-input>
              </b-input-group>            
               <div class="text-danger">{{ validation.firstError('curso.maxAlumnos') }}</div>

              <div>
                  <b-form-checkbox v-model="curso.publicado" name="check-button" switch class="mt-3">
                    <b>Publicar este curso</b>
                  </b-form-checkbox>
              </div>                  
        </b-col>
    </div>

    <div align="center">
        <b-button class="m-3 width: 200px;" style="m-3 width: 200px;"  variant="primary" @click="guardar()">Guardar</b-button>
        <b-button class="m-3 width: 200px;" style="m-3 width: 200px;"  variant="danger" @click="showModal()">Eliminar</b-button>
        <b-button style="m-3 width: 200px;"  variant="primary" @click="goToHome()">Volver</b-button>
    </div>

    <b-modal
      id="modal-prevent"
      ref="confirmacion-modal"
      title="Si está de acuerdo, confirme su email"
      @ok="handleConfirmModal"
    >
      <form @submit.stop.prevent="handleSubmit">
        <b-form-input v-model="mailAConfirmar" placeholder="Ingrese email"></b-form-input>
      </form>
    </b-modal>

</div>

</template>

<script>
import NavigationBar from '@/components/NavigationBar.vue'
import {Validator} from 'simple-vue-validator'

export default {
    name: 'FormCurso',
    props: {
      curso: {
        type: Object,
        required: false,
        default: function () {
          return { 
            "titulo": null,
            "tipo": null,
            "categoria": null,
            "descripcion": null,
            "contenidos": null,
            "instructores": null,
            "perfilAlumno": null,
            "cantHoras": null,
            "maxAlumnos": null,
            "publicado": false                       
          }
        }
      }
    }, 
    mounted() {
        this.cursoSeleccionado = this.$store.getters.cursoSeleccionado
    },
    data() {
        return {
            
            tipoCursoOptions: [
                { value: null, text: 'Seleccione tipo de Curso' },
                { value: 'Curso', text: 'Curso' },
                { value: 'Charla', text: 'Charla' },
                { value: 'Taller', text: 'Taller' },
                { value: 'Foro', text: 'Foro de Discución'}
            ],
            tipoCursoSelected: null,

            categoriaOptions: [
                { value: null, text: 'Seleccione categoría' },
                { value: 'WEB', text: 'Desarrollo Web' },
                { value: 'DB', text: 'Bases de Datos' },
                { value: 'TEST', text: 'Testing' },
                { value: 'FRONT', text: 'Front End'},
                { value: 'TOOLS', text: 'Herramientas'},
                { value: 'OPER', text: 'Operaciones'},
                { value: 'STD', text: 'Estándares'}
            ],
            categoriaSelected: null,

            perfilOptions: [
                { value: null, text: 'Seleccione perfil' },
                { value: 'DEV', text: 'Desarrollo' },
                { value: 'QA', text: 'Homologación' },
                { value: 'FUNC', text: 'Funcional' },
                { value: 'VAR', text: 'Varios' },
            ],
            perfilSelected: null,
            mailAConfirmar: "",
            cursoSeleccionado: { },
        }
    },  
    validators: {
      'curso.titulo': function (value) {
        //return Validator.value(value).required().regex('^[A-Za-z ]*$', 'Debe ingresar solo caracteres alfabéticos');
        return Validator.value(value).required().minLength(10);
      },
      'curso.tipo': function (value) {
        return Validator.value(value).required();
      },
      'curso.categoria': function (value) {
        return Validator.value(value).required();
      },
      'curso.descripcion': function (value) {
        return Validator.value(value).required();
      },
      'curso.contenidos': function (value) {
        return Validator.value(value).required();
      },
      'curso.instructores': function (value) {
        return Validator.value(value).required();
      },
      'curso.perfilAlumno': function (value) {
        return Validator.value(value).required();
      },
      'curso.cantHoras': function (value) {
        return Validator.value(value).required().integer();
      },
      'curso.maxAlumnos': function (value) {
        return Validator.value(value).required().integer();
      }    
        
    },    
    methods: {
      async guardar() {
          try {
            let esValido = await this.$validate()
            if (esValido) {							
                await this.$store.dispatch('createCurso', this.curso)               
                this.$router.push('/')
            } else {
                this.$bvToast.toast('Hay errores en el formulario y no es posible enviar la información', {
                  title: 'Advertencia',
                  variant: 'danger',
                  toaster: 'b-toaster-top-center',
                  solid: true
                })   
            }
          } catch (error) {
              console.log(error)
          }
      },
      handleConfirmModal() {         
          this.$nextTick(() => {
              this.hideModal()
              this.goToHome()
          // Wrapped in $nextTick to ensure DOM is rendered before closing
          })
      },
      showModal() {
          this.$refs['confirmacion-modal'].show()
      },
      hideModal() {
          this.$refs['confirmacion-modal'].hide()
      },        
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
