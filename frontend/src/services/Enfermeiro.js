import {https} from './config.js'

export default{
    listar:() =>{
        return https.get('enfermeiro')
    },
    salvar:(pacientes)=>{
        return https.post('pacientes',pacientes)
    }
}