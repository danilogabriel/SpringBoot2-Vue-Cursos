import Vue from 'vue'
import Router from 'vue-router'
import store from './store'
import Login from './views/Login.vue'
import Home from './views/Home.vue'
import DetalleCurso from './views/DetalleCurso.vue'
import NuevoCurso from './views/NuevoCurso.vue'


Vue.use(Router)

const router =  new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: { 
        requiresAuth: true
      }  
    },    
    {
      path: '/login',
      name: 'login',
      component: Login
    },    
    {
      path: '/detallecurso',
      name: 'detallecurso',
      component: DetalleCurso,
      meta: { 
        requiresAuth: true
      }      
    },
    {
      path: '/nuevocurso',
      name: 'nuevocurso',
      component: NuevoCurso,
      meta: { 
        requiresAuth: true
      }      
    }    

  ]
})
router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)) {
    if (store.getters.isLoggedIn) {
      return next()
    }
    next('/login') 
  } else {
    next() 
  }
})
export default router
