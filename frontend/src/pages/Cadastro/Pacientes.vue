<template>
  <div class="menu">
    <form class="pacientes">
      <div class="inicais">
        <label>
          <span>Nome:</span>
          <input v-model="nome" type="text" placeholder="Informe o nome do paciente" />
        </label>
        <label>
          <span>CPF:</span>
          <input v-model="cpf" type="text" class="form-control cpf-mask" placeholder="Ex.: 000.000.000-00" />
        </label>
        <label>
          <span>Data de Nascimento:</span>
          <input v-model="dataNascimento" type="date" />
        </label>
        <label>
          <el-button type="primary" round @click="inserir()">Inserir</el-button>
        </label>
      </div>
      <div class="outros">
        <label>
          <span>Peso:</span>
          <input v-model="peso" type="number" min="0" max="300" step=".01" />
        </label>
        <label>
          <span>Altura:</span>
          <input v-model="altura" type="number" min="0" max="3" step=".01" />
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
          <el-button type="primary" round @click="limpar()">Limpar</el-button>
        </label>
      </div>

      <div class="tabela">
        <template>
          <el-table class="tb_pacientes" :data="pacientes.filter(data => !pesquisar ||
          data.nome.toLowerCase().includes(pesquisar.toLowerCase()))" style="width: 100%">
            <el-table-column label="Nome" prop="nome" width=240px>
            </el-table-column>
            <el-table-column label="CPF" prop="cpf" width=150px>
            </el-table-column>
            <el-table-column label="Data de Nascimento" prop="dataNascimento" width=190px>
            </el-table-column>
            <el-table-column label="Peso" prop="peso" width=100px>
            </el-table-column>
            <el-table-column label="Altura" prop="altura" width=100px>
            </el-table-column>
            <el-table-column label="UF" prop="uf" width=150px>
            </el-table-column>
            <el-table-column align="right">
              <template slot="header">
                <input class="pesquisa" v-model="pesquisar" placeholder="Pesquisar Nome" />
              </template>
              <template slot-scope="scope">
                <el-button size="medium" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
                <el-button size="medium" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>

      </div>
    </form>
  </div>
</template>

<script>
import Estados from "@/services/estados";
import Pacientes from "@/services/listarPacientes";
export default {
  name: "PacientePage",
  components: {},
  data() {
    return {
      estados: [],
      pacientes: [],
      nome: null,
      cpf: null,
      dataNascimento: null,
      peso: null,
      altura: null,
      pesquisar:'',
      estado_nome: "Acre",
   
    };
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
        alert("dados validados");
        this.enviar(paciente);
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
      if (paciente.altura < 0) msg += "altura invalida \n";
      if (paciente.peso < 0) msg += "altura invalida \n";
      if (msg != "") {
        alert(msg);
        return false;
      }
      return true;
    },
    limpar() {
      (this.nome = null),
        (this.cpf = null),
        (this.dataNascimento = null),
        (this.peso = null),
        (this.altura = null),
        (this.uf = "Acre");
    },
    enviar(paciente) {
      this.limpar();
      Pacientes.salvar(paciente);
    },
  },
  mounted() {
    Estados.listar().then((resposta) => {
      this.estados = resposta.data;
    });
    Pacientes.listar().then((resposta) => {
      resposta.data.map(paciente => {
        const dataInput = new Date(paciente.dataNascimento);
        paciente.dataNascimento = dataInput.toLocaleDateString('pt-BR', { timezone: 'UTC' });
      });
      this.pacientes = resposta.data;
    });
  },
};
</script>
<style scoped>
.tabela {
  width: 90%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 50px;
}
.tb_pacientes{
  font-size: 17px;
}
.pesquisa{
  width: 200px;
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
.outros {
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
.inserir {
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
input {
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
  font-family: "Nunito", sans-serif;
}
</style>
