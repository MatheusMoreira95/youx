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
      <span>Senha :</span>
      <el-input v-model="senha" type="password" />
    </label>
    <label>
      <span>Senha Confirmação:</span>
      <el-input v-model="senha_confirmacao" type="password" />
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
import Enfermeiros from "@/services/Enfermeiro";

export default {
  name: "EnfermeiroPage",
  components: {},
  data() {
    return {
      nome: null,
      cpf: null,
      senha: null,
      senha_confirmacao: null,
      pesquisar: null,
      enfermeiros: [],
      cpfCadastrado: true,
      ativo: true,
      atualizar: false
    };
  },
  methods: {
    verificaCpf() {

    },
    atualiza() {
      let enfermeiro = {};
      enfermeiro.nome = this.nome;
      enfermeiro.cpf = this.cpf;
      enfermeiro.senha = this.senha;
      if (this.validar(enfermeiro)) {
        this.atualiza_enfermeiro(enfermeiro);
      }
    },
    atualiza_enfermeiro(enfermeiro) {
      this.limpar();
      Enfermeiros.atualizar(enfermeiro, enfermeiro.cpf);
    },

    inserir() {
      let enfermeiro = {};

      enfermeiro.cpf = this.cpf;
      enfermeiro.nome = this.nome;
      enfermeiro.senha = this.senha;


      if (this.validar(enfermeiro)) {
        this.enviar(enfermeiro);
      }
    },
    validar(enfermeiro) {
      let msg = "";
      if (enfermeiro.nome == "" || enfermeiro.nome == " " || enfermeiro.nome == null)
        msg += "informe o nome \n";
      if (enfermeiro.cpf == "" || enfermeiro.cpf == " " || enfermeiro.cpf == null)
        msg += "informe o cpf \n";
      if (enfermeiro.senha == "" || enfermeiro.senha == " " || enfermeiro.senha == null)
        msg += "informe a senha \n";
      if (this.senha_confirmacao == "" || this.senha_confirmacao == " " || this.senha_confirmacao == null)
        msg += "confirme a senha \n";
      if (enfermeiro.senha != this.senha_confirmacao)
        msg += "senhas digitadas diferentes \n";
      if (msg != "") {
        alert(msg);
        return false;
      }
      return true;
    },
    limpar() {
      (this.nome = null),
        (this.cpf = null),
        (this.senha = null),
        (this.senha_confirmacao = null)
    },
    enviar(enfermeiro) {
      Enfermeiros.salvar(enfermeiro);
      this.limpar()
    },
  },
  mounted() {
    if (this.$route.params) {
      let r_enfermeiro= this.$route.params
      if (r_enfermeiro.cpf != null) {
        this.ativo = false;
        this.atualizar = true;
        this.nome = r_enfermeiro.nome,
        this.cpf = r_enfermeiro.cpf
      }
    }
  }
}
</script>


<style scoped>
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
</style>
