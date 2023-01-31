import Vue from 'vue';
import Router from 'vue-router';
// Home
import Inicio from '@/pages/Home/Inicio' 
Vue.use(Router);

export default new Router({
    routes: [{
        path: '/',
        component: Inicio,
        meta: {
            routeTitle: 'Inicio'
        }
    }]
})