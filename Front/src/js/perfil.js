document.addEventListener('DOMContentLoaded', () => {
    const perfilInfo = document.getElementById('perfilInfo');
    const formContainer = document.getElementById('formContainer');
    const formPerfil = document.getElementById('formPerfil');
    const btnEditar = document.getElementById('btnEditar');
    const btnCancelar = document.getElementById('btnCancelar');
    const mensagemPerfil = document.getElementById('mensagemPerfil');
    
    const userId = document.getElementById('userId');
    const nome = document.getElementById('nome');
    const email = document.getElementById('email');
    const senha = document.getElementById('senha');
    
    const baseUrl = "http://localhost:8080/usuario";
    
    // Dados temporários para exibição inicial - na implementação real, buscaríamos os dados do usuário logado
    let usuarioAtual = null;
    const emailLogado = localStorage.getItem('email');
    
    // Função para buscar e exibir o perfil do usuário
    function carregarPerfil() {
        // Na implementação real, buscaríamos pelo ID, mas neste mockup usaremos o email como identificador
        // Simularemos uma busca pelo usuário com base no email armazenado no localStorage
        perfilInfo.innerHTML = '<p>Carregando dados do usuário...</p>';
        
        // Aqui você implementaria a lógica para buscar o usuário por email
        // Como o backend não tem um endpoint específico para isso, você precisaria adaptar ou criar um novo
        // Para fins de demonstração, simularemos isso buscando todos os usuários
        
        fetch(`${baseUrl}/1`)  // Supondo que o ID 1 é o do usuário logado
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Erro ao buscar dados do usuário');
                }
            })
            .then(data => {
                usuarioAtual = data;
                
                // Exibe os dados do usuário
                perfilInfo.innerHTML = `
                    <p><strong>ID:</strong> ${data.id}</p>
                    <p><strong>Nome:</strong> ${data.nome}</p>
                    <p><strong>Email:</strong> ${data.email}</p>
                `;
                
                // Preenche o formulário com os dados atuais
                userId.value = data.id;
                nome.value = data.nome;
                email.value = data.email;
                senha.value = ''; // Não exibimos a senha atual
            })
            .catch(error => {
                console.error('Erro:', error);
                perfilInfo.innerHTML = `<p>Erro ao carregar dados: ${error.message}</p>`;
            });
    }
    
    // Mostra o formulário de edição
    btnEditar.addEventListener('click', () => {
        perfilInfo.classList.add('hidden');
        formContainer.classList.remove('hidden');
        btnEditar.classList.add('hidden');
    });
    
    // Cancela a edição
    btnCancelar.addEventListener('click', () => {
        formContainer.classList.add('hidden');
        perfilInfo.classList.remove('hidden');
        btnEditar.classList.remove('hidden');
        
        // Limpa qualquer mensagem
        mensagemPerfil.innerHTML = '';
        mensagemPerfil.className = 'message';
    });
    
    // Submete o formulário de edição
    formPerfil.addEventListener('submit', (e) => {
        e.preventDefault();
        
        // Limpa mensagem anterior
        mensagemPerfil.innerHTML = '';
        mensagemPerfil.className = 'message';
        
        const id = userId.value;
        
        // Monta o payload conforme esperado pela API
        const payload = {
            nome: nome.value,
            email: email.value
        };
        
        // Adiciona a senha apenas se foi preenchida
        if (senha.value) {
            payload.senha = senha.value;
        }
        
        // Faz a requisição para o endpoint correto baseado no controller
        fetch(`${baseUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao atualizar perfil');
            }
        })
        .then(data => {
            // Atualização bem-sucedida
            mensagemPerfil.textContent = 'Perfil atualizado com sucesso!';
            mensagemPerfil.className = 'message success';
            
            // Atualiza o email no localStorage se foi alterado
            if (data.email !== emailLogado) {
                localStorage.setItem('email', data.email);
            }
            
            // Volta para a visualização
            setTimeout(() => {
                formContainer.classList.add('hidden');
                perfilInfo.classList.remove('hidden');
                btnEditar.classList.remove('hidden');
                
                // Recarrega os dados
                carregarPerfil();
            }, 1500);
        })
        .catch(error => {
            console.error('Erro:', error);
            mensagemPerfil.textContent = `Erro ao atualizar: ${error.message}`;
            mensagemPerfil.className = 'message error';
        });
    });
    
    // Carrega o perfil ao iniciar
    carregarPerfil();
});
