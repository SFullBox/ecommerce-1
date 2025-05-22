document.addEventListener('DOMContentLoaded', () => {
    const formCadastro = document.getElementById('formCadastro');
    const mensagemCadastro = document.getElementById('mensagemCadastro');
    
    const baseUrl = "http://localhost:8080/usuario";
    
    formCadastro.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const nome = document.getElementById('nome').value;
        const email = document.getElementById('email').value;
        const senha = document.getElementById('senha').value;
        
        // Limpa mensagem anterior
        mensagemCadastro.innerHTML = '';
        mensagemCadastro.className = 'message';
        
        // Monta o payload conforme esperado pela API
        const payload = {
            nome: nome,
            email: email,
            senha: senha
        };
        
        // Faz a requisição para o endpoint correto baseado no controller
        fetch(`${baseUrl}/salvar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then(response => {
            if (response.ok) {
                return response.json().then(data => {
                    // Cadastro bem-sucedido
                    mensagemCadastro.textContent = 'Cadastro realizado com sucesso!';
                    mensagemCadastro.className = 'message success';
                    
                    // Limpa o formulário
                    formCadastro.reset();
                    
                    // Redireciona para a página de login após 2 segundos
                    setTimeout(() => {
                        window.location.href = 'login.html';
                    }, 2000);
                });
            } else {
                // Cadastro falhou
                return response.text().then(text => {
                    mensagemCadastro.textContent = 'Erro ao cadastrar: ' + text;
                    mensagemCadastro.className = 'message error';
                });
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            mensagemCadastro.textContent = 'Erro ao conectar com o servidor';
            mensagemCadastro.className = 'message error';
        });
    });
});
