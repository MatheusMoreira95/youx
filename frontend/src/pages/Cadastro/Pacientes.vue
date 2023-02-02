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
                    <input v-model="dataNascimento" type="date">
                </label>
                <label>
                    <button class="inserir" @click="inserir()">Inserir</button>
                </label>
            </div>
            <div class="outros">
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
                <label>
                    <button class="limpar" @click="limpar()">Limpar</button>
                </label>
            </div>
            <div class="tabela">
                <table>
                    <thead>
                        <tr>
                            <th>NOME</th>
                            <th>CPF</th>
                            <th>DATA NASCIMENTO</th>
                            <th>PESO</th>
                            <th>ALTURA</th>
                            <th>ESTADO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="paciente of pacientes" :key="paciente.cpf">
                            <td>{{ paciente.nome }}</td>
                            <td>{{ paciente.cpf }}</td>
                            <td>{{ paciente.dataNascimento }}</td>
                            <td>{{ paciente.peso }}</td>
                            <td>{{ paciente.altura }}</td>
                            <td>{{ paciente.uf }}</td>
                        </tr>
                    </tbody>


                </table>
            </div>
        </form>


    </div>

</template>

<script>
import Estados from "@/services/estados";
import Pacientes from '@/services/listarPacientes'

export default {
    name: 'PacientePage',
    components: {

    },
    data() {
        return {
            estados: [],
            pacientes: [],
            nome: null,
            cpf: null,
            dataNascimento: null,
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
            paciente.dataNascimento = this.dataNascimento;
            paciente.peso = this.peso;
            paciente.altura = this.altura;
            paciente.uf = this.estado_nome;
            if (this.validar(paciente)) {
                alert("dados validados")
                this.enviar(paciente)
            }
        },
        validar(paciente) {
            let msg = "";
            if (paciente.nome == "" || paciente.nome == " " || paciente.nome == null)
                msg += "informe o nome \n";
            if (paciente.cpf == "" || paciente.cpf == " " || paciente.cpf == null)
                msg += "informe o cpf \n";
            if (paciente.uf == "" || paciente.uf == " " || paciente.uf == null)
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
        limpar() {
            this.nome = null,
                this.cpf = null,
                this.dataNascimento = null,
                this.peso = null,
                this.altura = null,
                this.uf = "Acre"
        },
        enviar(paciente) {
            this.limpar()
            Pacientes.salvar(paciente)

        }

    },
    mounted() {
        Estados.listar().then((resposta) => {
            this.estados = resposta.data;
        });
        Pacientes.listar().then((resposta) => {
            console.log(resposta.data)
            this.pacientes = resposta.data;
        }
        )
    }
}
</script>
<style scoped>
table {
    width: 100%;
    border-spacing: 0;
    border-collapse: collapse;
}

table thead {
    height: 55px;
    font-size: 16 px;
    color: #e9e9e9;
    font-weight: 700;
    background-color: #1ab2ff;
}

table tbody {
    font-size: 18px;
    font-weight: 400;
    color: #fff;
    text-align: center;
    background-color: #1ab2ff;
}

table tbody tr {
    height: 74px;
    border-top: 1px solid #5f6e82;
}

tr:hover>td {
    background-color: #384459;
}

table tbody tr:nth-child(odd) {
    background-color: #73a4bdad;
}

.inicais {
    margin-top: 5%;
    display: inline-block;
    background: #fff;
    box-shadow: 0px 0px 3px black, 0 3px 3px rgba(0, 0, 0, 0.22);
    border-radius: 2px;
    width: 42%;
    margin-left: 5%;
    justify-content: center;
    align-items: center;
}

.outros{
    margin-top: 5%;
    display: inline-block;
    background: #fff;
    box-shadow: 0px 0px 3px black, 0 3px 3px rgba(0, 0, 0, 0.22);
    border-radius: 2px;
    width: 42%;
    margin-left: 5%;
    justify-content: center;
    align-items: center;
}



.tabela {

    background: #fff;
    box-shadow: 0px 0px 3px black, 0 3px 3px rgba(0, 0, 0, 0.22);
    width: 80%;
    margin-left: 10%;
    margin-top: 5%;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}

.inserir {
    display: inline-block;
    width: 46%;
    margin-left: 1%;
}

.limpar {
    display: inline-block;
    width: 46%;
    margin-left: 1%;
}

select {
    text-align: center;
    flex: 1;
    margin-right: 5px;
    width: 100%;
}

input,
button {
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

button {
    margin: 20px auto;
    width: 260px;
    height: 36px;
    border-radius: 30px;
    background-color: #8c8c8c;
    color: white;
    font-size: 15px;
}
</style>