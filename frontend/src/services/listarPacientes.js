import {https} from './config.js'

export default{
    listar:() =>{
        return https.get('pacientes')
    },
    salvar:(pacientes)=>{
        return https.post('pacientes',pacientes)
    }
}