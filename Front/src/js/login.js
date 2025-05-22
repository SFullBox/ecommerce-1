document.addEventListener('DOMContentLoaded', () => {
    const formLogin = document.getElementById('formLogin');
    const mensagemLogin = document.getElementById('mensagemLogin');
    
    const baseUrl = "http://localhost:8080/usuario";
    
    formLogin.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const email = document.getElementById('email').value;
        const senha = document.getElementById('senha').value;
        
        // Limpa mensagem anterior
        mensagemLogin.innerHTML = '';
        mensagemLogin.className = 'message';
        
        // Monta o payload conforme esperado pela API
        const payload = {
            email: email,
            senha: senha
        };
        
        // Faz a requisição para o endpoint correto baseado no controller
        fetch(`${baseUrl}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then(response => {
            if (response.ok) {
                return response.text().then(text => {
                    // Login bem-sucedido
                    mensagemLogin.textContent = 'Login realizado com sucesso!';
                    mensagemLogin.className = 'message success';
                    
                    // Guarda informação de autenticação no localStorage
                    localStorage.setItem('autenticado', 'true');
                    localStorage.setItem('email', email);
                    
                    // Redireciona para a página principal após 1 segundo
                    setTimeout(() => {
                        window.location.href = 'index.html';
                    }, 1000);
                });
            } else {
                // Login falhou
                return response.text().then(text => {
                    mensagemLogin.textContent = 'Email ou senha incorretos';
                    mensagemLogin.className = 'message error';
                });
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            mensagemLogin.textContent = 'Erro ao conectar com o servidor';
            mensagemLogin.className = 'message error';
        });
    });
});
