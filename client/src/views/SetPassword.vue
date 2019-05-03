<template>
    <div >    

        <b-navbar type="dark" variant="primary">
            <b-navbar-brand href="#">Capacita2</b-navbar-brand>

        </b-navbar>    

        <b-container fluid>
        
        <div class="text-center pt-4">

            <h4>Bienvenido <strong>{{ usuario.nombre }}</strong> al registro de propuestas de Capacitación.</h4>
            <h5>Como es tu primer ingreso, te solicitamos que cargues una contraseña para poder operar.</h5>

        </div>
        <b-row align-h="center" class="p-4">
            <b-col xs="12" sm="6" md="3" lg="3" >
				<b-form-input class="mb-2 mr-2"
                    type="password"
					placeholder="Contraseña"
					required 
					v-model="password1" />
                <div class="text-danger">{{ validation.firstError('password1') }}</div>

				<b-form-input class="mb-2 mr-2"
                    type="password"
					placeholder="Reingrese contraseña"
					required 
					v-model="password2" />
                <div class="text-danger">{{ validation.firstError('password2') }}</div>

                <button type="submit" class="btn btn-primary btn-block" @click="setPassword()" >Confirmar contraseña</button>
            </b-col>
        </b-row>
        </b-container>
    </div>
</template>
<script>
import {Validator} from 'simple-vue-validator'

export default {
    name: 'SetPassword',
    props: {
        usuario: Object
    },
    data(){
	    return {
            //usuario: $route.params.usuario,
            password1: '',
            password2: ''
        }
    },  
    validators: {
      password1: function (value) {
        return Validator.value(value).required().minLength(6);
      },
      'password2, password1': function (password2, password1) {
        if (this.submitted || this.validation.isTouched('password2')) {
          return Validator.value(password2).required().match(password1);
        }
      }
    },     
	methods: {
        async setPassword(){
			try {
				let esValido = await this.$validate()
				if (esValido) {	
                    var params = { 
                        "legajo": this.usuario.legajo, 
                        "password": this.password1
                    }	
                    var response = await this.$http({ method: "POST", data: params, "url": "api/auth/updatepass"})
					this.$router.push('/')
				}
			} catch (error) {
				console.log(error)
			}
        }
    }
}
</script>
<style>
</style>