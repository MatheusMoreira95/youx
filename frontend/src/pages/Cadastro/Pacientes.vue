<template>
  <div class="container">
    <label>
      <span>Nome:</span>
      <el-input v-model="nome" type="text" placeholder="Informe o nome do paciente" />
    </label>
    <label>
      <span>CPF:</span>
      <el-input v-model="cpf" v-mask="'###.###.###-##'" type="text" class="form-control cpf-mask"
        placeholder="Informe o CPF" />
    </label>
    <label>
      <span>Data de Nascimento:</span>
      <el-input v-model="dataNascimento" type="date" />
    </label>
    <label>
      <span>Peso:</span>
      <el-input v-model="peso" type="number" min="0" max="300" step=".01" />
    </label>
    <label>
      <span>Altura:</span>
      <el-input v-model="altura" type="number" min="0" max="3" step=".01" />
    </label>
    <label>
      <span>Estado:</span>
      <select id="estados" v-model="estado_nome">
        <option v-for="estado of estados" :key="estado.id">
          {{ estado.nome }}
        </option>
      </select>
    </label>
    <div class="botao">
      <label>
        <el-button v-if="ativo" loadingdisabled type="primary" plain @click="inserir()">Salvar</el-button>
      </label>
      <label>
        <el-button v-if="atualizar" loadingdisabled type="primary" plain @click="atualiza()">Atualizar</el-button>
      </label>
      <label>
        <el-button type="primary" plain @click="limpar()">Limpar</el-button>
      </label>
    </div>
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
      estado_nome: "Acre",
      ativo: true,
      atualizar: false

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
        this.enviar(paciente);
      }
    },
    atualiza() {
      let paciente = {};
      paciente.nome = this.nome;
      paciente.cpf = this.cpf;
      paciente.dataNascimento = this.dataNascimento;
      paciente.peso = this.peso;
      paciente.altura = this.altura;
      paciente.uf = this.estado_nome;
      if (this.validar(paciente)) {
        this.atualiza_paciente(paciente);
      }
    },
    atualiza_paciente(paciente) {
      this.limpar();
      Pacientes.atualizar(paciente, paciente.cpf);
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
    }

  },
  mounted() {
    Estados.listar().then((resposta) => {
      this.estados = resposta.data;
    });


    if (this.$route.params) {
      let r_paciente = this.$route.params
      if (r_paciente.cpf != null) {
        this.ativo = false;
      this.atualizar = true;
        let parts_of_date = r_paciente.data.split("/");
        let output = new Date(+parts_of_date[2], parts_of_date[1] - 1, +parts_of_date[0]).toISOString().substring(0, 10)
        this.nome = r_paciente.nome,
          this.cpf = r_paciente.cpf,
          console.log('data', output),
          this.dataNascimento = output,
          this.peso = r_paciente.peso,
          this.altura = r_paciente.altura,
          this.estado_nome = r_paciente.uf
        this.disabled = true
      }
    }

  },
};
</script>
<style scoped>
* {
  font-family: "Nunito", sans-serif;
  font-size: 16px;
}

.inserir {
  display: inline-block;
  width: 46%;
  margin-left: 1%;
}

.el-input {
  margin: 5px;
}

.botao {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

}

.botao button {
  margin: 30px;
}

.container {
  padding: 50px;
}

label {
  font-family: Georgia, 'Times New Roman', Times, serif;
  text-align: center;
}

label span {
  font-size: 14px;
  font-weight: 600;
  color: #505f75;
  text-transform: uppercase;
}

select {
  width: 100%;
  font-size: 18px;
  font-weight: 600;
  color: #505f75;
  font-family: Georgia, 'Times New Roman', Times, serif;
  text-align: center;
}
</style>
