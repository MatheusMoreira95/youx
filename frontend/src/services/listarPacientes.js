import {https} from './config.js'

export default{
    listar:() =>{
        return https.get('pacientes')
    },
    enviar:()=>{
        return https.post('pacientes')
    }
}