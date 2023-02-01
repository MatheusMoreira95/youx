import {https} from './estados_config'

export default{
    listar:() =>{
        return https.get('estados?orderBy=nome')
    }
}