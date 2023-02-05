<template>
    <div class="menu" style="background-color: #fff">
       
        <div class="container">
            <form class="form">
                <h3>Bem Vindo</h3>
                <label>
                    <span>Login:</span>
                    <input v-model="model.nome" placeholder="insira seu usuário">
                </label>
                <label>
                    <span>Senha:</span>
                    <input v-model="model.senha" type="password" placeholder="insira sua senha">
                </label>
                <button class="subimit" @click="entrar()" type="button">Entrar</button>
            </form>
        </div>
    </div>
</template>

<script>
import Enfermeiro from '@/services/Enfermeiro';
import verificaAutenticacao  from '@/auth/auth'

export default {
    name: 'InicioPage',
    components: {

    },
    data() {
        return {
            model: {
                nome: null,
                senha: null
            }
        }
    },
    methods: {
        entrar() {
            Enfermeiro.logar(this.model)
            localStorage.setItem('usuario', JSON.stringify(this.model));
        }
    },
    mounted() {
        verificaAutenticacao();
       
    //    if (usuario) {
    //      this.$router.push({
    //         name: 'pacientes_listar'
    //      })
    //    }
    }
}

</script>

<style scoped>
.container {
    display: flex;
    background: #fff;
    box-shadow: 0 19px 38px black, 0 15px 12px rgba(0, 0, 0, 0.22);
    border-radius: 25px;
    margin-left: 30px;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width: 30vw;
    height: 50vh;
}
.menu{
    display: flex;
    justify-content: center;
    flex-direction: row;
    margin-top: 5%;
}


input,
button {
    border: none;
    outline: none;
    background: none;
}


h3 {
    color: #445063;
    padding-top: 10px;
    font-size: 25px;
    text-align: center;

}

.form {

    padding: 50px 30px;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}

label {
    display: block;
    width: 260px;
    margin: 25px auto 0;
    text-align: center;
}

label span {
    font-size: 14px;
    font-weight: 600;
    color: #505f75;
    text-transform: uppercase;
}


input {
    display: block;
    width: 100%;
    margin-top: 5px;
    font-size: 16px;
    padding-bottom: 5px;
    border-bottom: 1px solid rgba(109, 93, 93, 0.4);
    text-align: center;
    font-family: 'Nunito', sans-serif;
}

button {
    display: block;
    margin: 20px auto;
    width: 260px;
    height: 36px;
    border-radius: 30px;
    background-color: blue;
    font-size: 15 px;
    cursor: pointer;
    color: #fff;
    transition: all ease 0.2s;
}

button:hover {
    background-color: #0039e6;
    font-size: 18px;
    
}
</style>
