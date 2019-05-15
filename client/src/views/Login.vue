<template>
	<div>
		<div class="login-form" style="width: 30%;">
			<h3 class="text-center p-4">Ingreso a Propuestas de Capacitación</h3>       

				<b-input-group append="@afip.gob.ar" class="mb-2 mr-2">
				<b-form-input placeholder="usuario de e-mail" v-model="userLogin.username">
				</b-form-input>
				</b-input-group>
				<div class="text-danger">{{ validation.firstError('userLogin.username') }}</div>

				<b-form-input class="mb-2 mr-2"
					placeholder="N° legajo"
					required 
					v-model="userLogin.legajo" />
				<div class="text-danger">{{ validation.firstError('userLogin.legajo') }}</div>

				<b-form-input class="mb-2 mr-2" @blur="verificarPrimerAcceso()"
					placeholder="CUIT / CUIL"
					required 
					v-model="userLogin.cuit" />
				<div class="text-danger">{{ validation.firstError('userLogin.cuit') }}</div>

				<div v-if="!primerAcceso">
					<b-form-input class="mb-2 mr-2" @focus="verificarPrimerAcceso()"
						type="password"
						placeholder="Contraseña"
						required 
						v-model="userLogin.password" />
					<div class="text-danger">{{ validation.firstError('userLogin.password') }}</div>
				</div>

				<div v-if="primerAcceso">
					<h6></h6>
					<h6>Es tu primer ingreso y debes cargar una contraseña para poder operar.</h6>

					<b-form-input class="mb-2 mr-2"
					  ref="password1Input"
						type="password"
						placeholder="Contraseña"
						required 
						v-model="setPassword1" />
					<div class="text-danger">{{ validation.firstError('setPassword1') }}</div>

					<b-form-input class="mb-2 mr-2"
						type="password"
						placeholder="Reingrese contraseña"
						required 
						v-model="setPassword2" />
					<div class="text-danger">{{ validation.firstError('setPassword2') }}</div>
				</div>

				<button type="submit" class="btn btn-primary btn-block" @click="login()" >Ingresar</button>		
		</div>
		<div class="text-center text-danger" v-if="$store.state.status">{{ $store.state.messageError }}</div>		
	</div>
</template>
<script>
import {Validator} from 'simple-vue-validator'

export default {
	data(){
		return {	
			primerAcceso: false,		
			//password: null,
			setPassword1: null,
			setPassword2: null,
			userLogin: {
				username : "dbiondi",
				legajo : 3710681,
				cuit: 20220254144,
				password: null
			}	
    	}
	},
  validators: {
		'userLogin.username' : function (value) {
			return Validator.value(value).required().regex('^[A-Za-z]*$', 'Debe ingresar solo caracteres alfabéticos');
		},
    'userLogin.legajo' : function (value) {
			return Validator.value(value).required().integer();
    },
    'userLogin.cuit' : function (value) {
			return Validator.value(value).required().length(11, 'Debe ser numérico de 11 digitos').digit();
		},
    'userLogin.password' : function (value) {
			return Validator.value(value).required().minLength(6);
		},
		setPassword1: function (value) {
        return Validator.value(value).required().minLength(6);
    },
    'setPassword2, setPassword1': function (setPassword2, setPassword1) {
        if (this.submitted || this.validation.isTouched('setPassword2')) {
          return Validator.value(setPassword2).required().match(setPassword1);
        }
    }	  
  }, 	
	methods: {
		async verificarPrimerAcceso(){
			try {
				var response = await this.$http({ method: "POST", data: { cuit: this.userLogin.cuit }, "url": "api/auth/firstaccess"})
				if (response.data == true) {
					this.primerAcceso = true
					this.$nextTick(() => this.$refs.password1Input.focus())
					//this.$refs.password1Input.$el.focus()
				}
			} catch (error) {
				console.log(error)
			}
		},
		login() {
			let esValido = this.$validate()
			if (esValido) {			
				if (this.primerAcceso) {
					this.procesarPrimerAcceso()
				} else {
					this.procesarLogin()
				}
			} else {
				//  Hubo errores en la captura del formulario
			}		
		},
		async procesarPrimerAcceso() {
			if (this.setPassword1==this.setPassword2) {
				try {
					var params = { 
                        "legajo": this.userLogin.legajo, 
                        "password": this.setPassword1
                    }	
					var response = await this.$http({ method: "POST", data: params, "url": "api/auth/updatepass"})
					this.userLogin.password = this.setPassword1
					this.procesarLogin();
				} catch (error) {
					console.log("Error interno del servidor procesando primer acceso")
				}
			}

		},
		async procesarLogin() {
			try {
				var response = await this.$store.dispatch('login', this.userLogin)
				if (response.message == "LOGIN_SUCCESS") {
					this.$router.push('/')
				} else if (response.message == "PASSWORD_WRONG") {					
					//---------- hubo un error en el login del backend -----
					console.log("Error: " + response.message)
				}
			} catch(error) {
				console.log("Error interno del servidor procesando Login")
			}
		}
	}
}
</script>
<style scoped>
		.login-form {
			width: 640px;
				margin: 50px auto;
				margin-bottom: 15px;
					background: #f7f7f7;
					box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
					padding: 30px;			
		}
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>