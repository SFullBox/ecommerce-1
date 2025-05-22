document.addEventListener('DOMContentLoaded', () => {
    // Preenche o nome do usuário logado
    const email = localStorage.getItem('email');
    const bemVindo = document.getElementById('bemVindo');
    
    if (email && bemVindo) {
        bemVindo.textContent = `Bem-vindo, ${email}`;
    }
});
