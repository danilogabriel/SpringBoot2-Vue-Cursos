import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import store from './store'

import VueFormGenerator from "vue-form-generator"
import 'vue-form-generator/dist/vfg.css'
import SimpleVueValidation from 'simple-vue-validator/'
//import SimpleVueValidation from 'Validator'

SimpleVueValidation.extendTemplates({
  error: 'Error.',
  required: 'Ingreso obligatorio',
  float: 'Debe ser numérico',
  integer: 'Debe ser numérico entero',
  number: 'Debe ser numérico',
  lessThan: 'Debe ser un valor menor que {0}.',
  lessThanOrEqualTo: 'Debe ser un valor menor o igual que {0}.',
  greaterThan: 'Debe ser un valor mayor que {0}.',
  greaterThanOrEqualTo: 'Debe ser un valor mayor o igual que {0}.',
  between: 'Debe ser un valor entre {0} y {1}.',
  size: 'El tamaño debe ser de {0}.',
  length: 'Deben ser {0} caracteres',
  minLength: 'Deben ser al menos {0} caracteres',
  maxLength: 'Deben ser {0} caracteres como máximo',
  lengthBetween: 'El largo debe ser entre {0} y {1}.',
  in: 'Debe estar entre los siguientes valores: {0}.',
  notIn: 'No debe ser ninguno de los valores: {0}.',
  match: 'No coincide',
  regex: 'Formato inválido',
  digit: 'Solo se aceptan números',
  email: 'Formato de email inválido',
  url: 'Formato de URL inválido',
  optionCombiner: function (options) {
    if (options.length > 2) {
      options = [options.slice(0, options.length - 1).join(', '), options[options.length - 1]];
    }
    return options.join(' o ');
  }
});

Vue.use(SimpleVueValidation);
Vue.use(VueFormGenerator)
Vue.config.productionTip = false
Vue.use(BootstrapVue);

Vue.prototype.$http = axios.create({
  baseURL: process.env.VUE_APP_API_ROOT
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
