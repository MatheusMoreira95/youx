<template>
  <div class="container">
    <template>
      <el-table class="tb_pacientes" :data="pacientes.filter(data => !pesquisar ||
      data.nome.toLowerCase().includes(pesquisar.toLowerCase()))" style="width: 100%">
        <el-table-column label="Nome" prop="nome" width=270px>
        </el-table-column>
        <el-table-column label="CPF" prop="cpf" width=150px>
        </el-table-column>
        <el-table-column label="Data de Nascimento" prop="dataNascimento" width=190px>
        </el-table-column>
        <el-table-column label="Peso" prop="peso" width=80px>
        </el-table-column>
        <el-table-column label="Altura" prop="altura" width=80px>
        </el-table-column>
        <el-table-column label="UF" prop="uf" width=150px>
        </el-table-column>
        <el-table-column align="right">
          <template slot="header">
            <input class="pesquisa" v-model="pesquisar" placeholder="Pesquisar Nome" />
          </template>
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" circle
              @click="handleEdit(scope.row)"></el-button>
            <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

  </div>
</template>

<script>
import Pacientes from "@/services/listarPacientes";
export default {
  name: "PacientePage",
  components: {},
  data() {
    return {
      pacientes: [],
      pesquisar: '',
    };
  },
  methods: {
    handleEdit(paciente) {
      this.$router.push({
        name: 'pacientes',
        params:{
          nome: paciente.nome,
          cpf: paciente.cpf,
          data : paciente.dataNascimento,
          peso : paciente.peso,
          altura: paciente.altura,
          uf: paciente.uf
        }
      })
    },
    handleDelete(row) {
      Pacientes.deletar(row.cpf);
      location.reload();
    }

  },
  mounted() {
    if (this.$route.params.paciente) {
      location.reload();
     }

    Pacientes.listar().then((resposta) => {
      resposta.data.map(paciente => {
        const dataInput = new Date(paciente.dataNascimento);
        paciente.dataNascimento = dataInput.toLocaleDateString('pt-BR', { timeZone: 'UTC' });
      });
      this.pacientes = resposta.data;
    });
  },
};
</script>
<style scoped>

* {
  font-family: "Nunito", sans-serif;
  font-size: 16px;
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

input {
  border: none;
  outline: none;
  background: none;
  border-bottom: 1px solid rgba(109, 93, 93, 0.4);
  text-align: center;

}
</style>
