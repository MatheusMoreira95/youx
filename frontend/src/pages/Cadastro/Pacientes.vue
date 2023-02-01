<template>
    <div class="menu">
        <form class="pacientes">
            <div class="inicais">
                <label>
                    <span>Nome:</span>
                    <input v-model="nome" type="text" placeholder="Informe o nome do paciente">
                </label>
                <label>
                    <span>CPF:</span>
                    <input v-model="cpf" type="text" class="form-control cpf-mask" placeholder="Ex.: 000.000.000-00">
                </label>
                <label>
                    <span>Data de Nascimento:</span>
                    <input v-model="dataNasc" type="date">
                </label>
                <button @click="inserir()">Inserir</button>

            </div>
            <div class="outras">
                <label>
                    <span>Peso:</span>
                    <input v-model="peso" type="number" min="0" max="300" step=".01">
                </label>
                <label>
                    <span>Altura:</span>
                    <input v-model="altura" type="number" min="0" max="3" step=".01">
                </label>
                <label>
                    <span>Estado:</span>
                    <select id="estados" v-model="estado_nome">
                        <option v-for="estado of estados" :key="estado.id">
                            {{ estado.nome }}
                        </option>
                    </select>
                </label>
                <button @click="limpar()">Limpar</button>
            </div>

        </form>
    </div>
</template>

<script>
import Estados from "@/services/estados";
import pacientes from '@/services/listarPacientes'

export default {
    name: 'PacientePage',
    components: {

    },
    data() {
        return {
            estados: [],
            pacientes,
            nome: null,
            cpf: null,
            dataNasc: null,
            peso: null,
            altura: null,
            estado_nome: "Acre"
        }

    },
    methods: {
        inserir() {
            let paciente = {};
            paciente.nome = this.nome;
            paciente.cpf = this.cpf;
            paciente.dataNasc = this.dataNasc;
            paciente.peso = this.peso;
            paciente.altura = this.altura;
            paciente.estado = this.estado_nome;
            if(this.validar(paciente)){
            alert("dados validados")
            }
        },
        validar(paciente) {
            let msg = "";
            if (paciente.nome == "" || paciente.nome == " " || paciente.nome == null)
                msg += "informe o nome \n";
            if (paciente.cpf == "" || paciente.cpf == " " || paciente.cpf == null)
                msg += "informe o cpf \n";
            if (paciente.estado == "" || paciente.estado == " " || paciente.estado == null)
                msg += "informe o estado \n";
            if (paciente.altura < 0)
                msg += "altura invalida \n";
            if (paciente.peso < 0)
                msg += "altura invalida \n";
            if (msg != "") {
                alert(msg);
                return false;
            }
            return true;
        },
        limpar(){
            this.nome= null,
            this.cpf=null,
           this.dataNasc= null,
            this.peso= null,
            this.altura= null,
           this.estado_nome= "Acre"
        },
        enviar(paciente){
            pacientes.enviar(paciente)
        }

    },
    mounted() {
        Estados.listar().then((resposta) => {
            console.log(resposta.data);
            this.estados = resposta.data;
        });
    }
}
</script>
<style scoped>
.inicais {
    display: inline-block;
    background: #fff;
    box-shadow: 0px 0px 3px black, 0 3px 3px rgba(0, 0, 0, 0.22);
    border-radius: 2px;
    width: 40%;
    margin-left: 7%;
    margin-top: 5%;
    flex-direction: row;
    justify-content: center;
    align-items: center;

}

select {
    text-align: center;
    flex: 1;
    margin-right: 5px;
    width: 100%;
}

.outras {
    display: inline-block;
    background: #fff;
    box-shadow: 0px 0px 3px black, 0 3px 3px rgba(0, 0, 0, 0.22);
    border-radius: 2px;
    width: 40%;
    margin-left: 7%;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}

.form {

    padding: 50px 30px;
    -webkit-transition: -webkit-transform 1.2s ease-in-out;
    transition: -webkit-transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out;
    transition: transform 1.2s ease-in-out, -webkit-transform 1.2s ease-in-out;
}

input,button {
    border: none;
    outline: none;
    background: none;
}

label span {
    font-size: 14px;
    font-weight: 600;
    color: #505f75;
    text-transform: uppercase;
}

label {
    display: block;
    width: 260px;
    margin: 25px auto 0;
    text-align: center;
    padding: 10px;
}

input,
select {
    display: block;
    width: 100%;
    margin-top: 5px;
    font-size: 16px;
    border-bottom: 1px solid rgba(109, 93, 93, 0.4);
    text-align: center;
    font-family: 'Nunito', sans-serif;
}

button{
        display: block;
        margin: 20px  auto;
        width: 260px;
        height: 36px;
        border-radius: 30px;
        background-color: #8c8c8c;
        color: white;
        font-size: 15px;
        cursor: pointer;
    }
</style>