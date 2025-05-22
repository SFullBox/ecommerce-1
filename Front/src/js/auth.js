// Verificação de autenticação para proteger as páginas
document.addEventListener('DOMContentLoaded', () => {
    // Páginas que não precisam de autenticação
    const publicPages = ['login.html', 'cadastro.html'];
    
    // Verifica se a página atual está na lista de páginas públicas
    const currentPage = window.location.pathname.split('/').pop();
    const needsAuth = !publicPages.includes(currentPage);
    
    // Verifica se o usuário está autenticado
    const isAuthenticated = localStorage.getItem('autenticado') === 'true';
    
    // Se a página precisa de autenticação mas o usuário não está autenticado, redireciona para login
    if (needsAuth && !isAuthenticated) {
        window.location.href = 'login.html';
    }
    
    // Se a página é de login ou cadastro e o usuário já está autenticado, redireciona para a página principal
    if (!needsAuth && isAuthenticated) {
        window.location.href = 'index.html';
    }
    
    // Adiciona função de logout no botão sair (se existir na página)
    const btnLogout = document.getElementById('btnLogout');
    if (btnLogout) {
        btnLogout.addEventListener('click', () => {
            localStorage.removeItem('autenticado');
            localStorage.removeItem('email');
            window.location.href = 'login.html';
        });
    }
});
