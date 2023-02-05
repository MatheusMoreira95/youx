import {https} from './config.js'

export default{
    listar:() =>{
        return https.get('enfermeiro')
    },
    salvar:(enfermeiro)=>{
        return https.post('enfermeiro',enfermeiro).catch(function (error){
            if(error.response){
                alert('CPF cadastrado: '+ enfermeiro.cpf)
            }
        })
        },
    deletar:(cpf)=>{
        return https.delete(`enfermeiro/`+cpf);
    },
    logar:(model)=>{
        return https.post('enfermeiro/login', model).then((usuario) => {
            localStorage.setItem('usuario', JSON.stringify(usuario));
            return true
        }).catch(() => {
            return alert('Login ou senha incorretos!');
        });
    },
    atualizar:(enfermeiro,cpf)=>{
        return https.put(`enfermeiro/`+cpf,enfermeiro).catch(function (error){
            if(error.response){
                alert('CPF cadastrado: '+ enfermeiro.cpf)
            }
        })
    }
}