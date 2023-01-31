import {https} from './config.js'

export default{
    listar:() =>{
        return https.get('pacientes')
    }
}