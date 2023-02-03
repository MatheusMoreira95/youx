import Vue from 'vue';
import Router from 'vue-router';
// Home
import Inicio from '@/pages/Home/Inicio' 
import Pacientes from '@/pages/Cadastro/Pacientes' 
import Enfermeiros from '@/pages/Cadastro/Enfermeiro' 
Vue.use(Router);

export default new Router({
    routes: [{
        path: '/',
        component: Inicio,
        meta: {
            routeTitle: 'Inicio'
        }
    },{
        path: '/pacientes',
        component: Pacientes,
        meta: {
            routeTitle: 'Cadastro de Pacientes'
        }
    },{
        path: '/enfermeiros',
        component: Enfermeiros,
        meta: {
            routeTitle: 'Cadastro de Enfermeiros'
        }
    }
    

,
]
})