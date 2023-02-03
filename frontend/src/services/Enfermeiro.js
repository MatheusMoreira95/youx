import {https} from './config.js'

export default{
    listar:() =>{
        return https.get('enfermeiro')
    },
    salvar:(enfermeiro)=>{
        return https.post('enfermeiro',enfermeiro)
    }
}