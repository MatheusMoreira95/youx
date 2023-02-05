<template>
    <header>
        <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="#545c64"
            text-color="#fff" active-text-color="#ffd04b">
            <el-menu-item index="1">Inicio</el-menu-item>
            <el-submenu index="2">
                <template slot="title">Cadastro</template>
                <el-menu-item index="2-1">Enfermeiro</el-menu-item>
                <el-menu-item index="2-2">Pacientes</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">Listagem</template>
                <el-menu-item index="3-1">Enfermeiro</el-menu-item>
                <el-menu-item index="3-2">Pacientes</el-menu-item>
            </el-submenu>
        </el-menu>
    </header>
</template>

<script>

export default {
    name: 'headerPage',
    data() {
        return {
            textoBotao: 'Logout',
        }
    },
    components: {
    },
    methods: {
        verificaAutenticacao() {
            return false;
        },
        handleSelect(keyPath) {
            let pagina= "";
            if (keyPath == '2-1') {
                pagina ="enfermeiros"
            } else if (keyPath == '2-2') {
                pagina ="pacientes"
            } else if (keyPath == '3-1') {
                pagina ="enfermeiros_listar"
            } else if (keyPath == '3-2') {
                pagina ="pacientes_listar"
            } else if (keyPath == '1') {
                pagina ="inicio"
            }
            this.redirectListagem(pagina);
        },
        redirectListagem(pagina) {
            this.$router.push({
                name: pagina 
            })
        }
    },
    mounted() {
    }
};
</script>

<style scoped>

</style>