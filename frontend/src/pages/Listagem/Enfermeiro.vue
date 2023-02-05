<template>
  <div class="container">
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
              @click="handleEdit(scope.row)"></el-button>
            <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"></el-button>
          </template>
      </el-table-column>
    </el-table>
  </div>
</template>
 

<script>
import Enfermeiros from "@/services/Enfermeiro";
export default {
  name: "EnfermeiroPage",
  components: {},
  data() {
    return {
      pesquisar: null,
      enfermeiros: []
    };
  },
  methods: {
    handleEdit(enfermeiro) {
      this.$router.push({
        name: 'enfermeiros',
        params:{
          nome: enfermeiro.nome,
          cpf: enfermeiro.cpf
        }
      })
    
    },

    handleDelete(row) {
      Enfermeiros.deletar(row.cpf);
      location.reload()
    },
    redirectListagem() {
      this.$router.push({
        name: 'enfermeiros_atualizar',   
      })
  
    }
  },
  mounted() {
    console.log(this.$route.params);
    
    if (this.$route.params.enfermeiro) {
      location.reload();
     }

    Enfermeiros.listar().then((resposta) => {
      this.enfermeiros = resposta.data;
    });

  }
}
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
