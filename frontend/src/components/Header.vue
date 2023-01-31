<template>
    <header>
        <router-link class="link" to="/teste">
            <h1>{{ this.$route.meta.routeTitle }}</h1>
        </router-link>
        <el-button v-if="verificaAutenticacao()" type="primary">{{ textoBotao }}</el-button>
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
        }
    },
    mounted() {
    }
};
</script>

<style scoped>
header {
    width: 100%;
    height: 60px;
    background-color: #6495ED;
    border-radius: 6px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
};

</style>