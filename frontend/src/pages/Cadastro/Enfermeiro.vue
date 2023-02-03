<template>
  <el-row :gutter="20">
    <el-col :span="10">
      <div class="grid-content bg-purple">

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
      </div>
    </el-col>
    <el-col :span="12">
      <div class="grid-content bg-purple">
        <template>
          <el-table :data="enfermeiros.filter(data => !pesquisar ||
          data.nome.toLowerCase().includes(pesquisar.toLowerCase()))" style="width: 100%">
            <el-table-column label="Nome" prop="nome" width=200px>
            </el-table-column>
            <el-table-column label="CPF" prop="cpf" width=150px>
            </el-table-column>
            <el-table-column align="right">
              <template slot="header">
                <input class="pesquisa" v-model="pesquisar" placeholder="Pesquisar Nome" />
              </template>
              <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-edit" circle
                  @click="handleEdit(scope.$index, scope.row)"></el-button>
                <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </div>
    </el-col>
  </el-row>
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
      enfermeiros: []
    };
  },
  mounted() {
    Enfermeiros.listar().then((resposta) => {
      this.enfermeiros = resposta.data;
    });
  }
}
</script>

<style scoped>
input {
  border: none;
  outline: none;
  background: none;
}

.el-row {
  margin-bottom: 20px;
  margin-top: 20px;
}
  .last-child {
    margin-bottom: 0;
  }


.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
