<template>
        <b-navbar type="dark" variant="primary">
            <b-navbar-brand href="/home">Capacita2</b-navbar-brand>

            <b-navbar-nav>
                <b-nav-item to="/nuevocurso">Nuevo</b-nav-item>
                <b-nav-item href="#">Mis Cursos</b-nav-item>
                <b-nav-item right href="#">Reporte</b-nav-item>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
                <b-button size="sm" class="my-2 my-sm-0" type="submit" @click="logout()">Cerrar sesión</b-button>
            </b-navbar-nav>
        </b-navbar>            
</template>

<script>
export default {
  name: 'NavigationBar',
  props: {
    
  },
  methods: {
      	logout() {
		   	this.$store.dispatch('logout')
		   			    .then(() => this.$router.push('/login'))
		   				.catch(err => console.log(err))		              
		}
  }
}
</script>