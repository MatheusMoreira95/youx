export function verificaAutenticacao() {
    console.log('chamou!');
    if (!localStorage.getItem('usuario')) {
        this.$router.push({
            name: 'inicio'
        })
    }
}

