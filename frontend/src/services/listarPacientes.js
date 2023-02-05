import {https} from './config.js'

export default{
    listar:() =>{
        return https.get('pacientes')
    },
    salvar:(pacientes)=>{
        return https.post('pacientes',pacientes).catch(function (error){
            if(error.response){
                alert('CPF cadastrado: '+ pacientes.cpf)
            }
        })
        
    },
    deletar:(cpf)=>{
        return https.delete(`pacientes/`+cpf)
    },
    atualizar:(paciente,cpf)=>{
        return https.put(`pacientes/`+cpf,paciente).catch(function (error){
            if(error.response){
                alert('CPF cadastrado: '+ paciente.cpf)
            }
        })
    }
}