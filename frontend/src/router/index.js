import Vue from 'vue';
import Router from 'vue-router';
// Home
import Inicio from '@/pages/Home/Inicio' 
import Pacientes from '@/pages/Cadastro/Pacientes' 
import Enfermeiros from '@/pages/Cadastro/Enfermeiro' 
import Pacientes_listar from '@/pages/Listagem/Pacientes' 
import Enfermeiros_listar from '@/pages/Listagem/Enfermeiro' 

Vue.use(Router);

export default new Router({
    routes: [{
        path: '/',
        component: Inicio,
        nome:'inicio',
        meta: {
            routeTitle: 'Inicio'
        }
    },{
        path: '/pacientes',
        component: Pacientes,
        props:true,
        name:'pacientes',
        meta: {
            routeTitle: 'Cadastro de Pacientes',
            private: true
        }
    },{
        path: '/enfermeiros',
        component: Enfermeiros,
        name: 'enfermeiros',
        meta: {
            routeTitle: 'Cadastro de Enfermeiros',
            permissao: 'PERMISSAO_MEDICO',
            private: true
        }
    },{
        path: '/enfermeiros_listagem',
        component: Enfermeiros_listar,
        props:true,
        name: 'enfermeiros_listar',
        meta: {
            routeTitle: 'Listagem de Funcionarios',
            permissao: 'PERMISSAO_MEDICO',
            private: true
        }
    },{
        path: '/pacientes_listagem',
        component: Pacientes_listar,
        name: 'pacientes_listar',
        meta: {
            routeTitle: 'Listagem de Pacientes',
            private: true
        }
    }
   
,
]
})