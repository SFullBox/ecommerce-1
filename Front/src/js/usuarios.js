document.addEventListener('DOMContentLoaded', () => {
    const listaUsuarios = document.getElementById('lista-usuarios');
    const modalDetalhes = document.getElementById('modalDetalhes');
    const fecharDetalhes = document.getElementById('fecharDetalhes');
    const detalhesUsuario = document.getElementById('detalhesUsuario');
    
    const baseUrl = "http://localhost:8080/usuario";
    
    // Como não temos um endpoint para listar todos os usuários no controller fornecido,
    // aqui estamos apenas simulando que teríamos acesso a alguns usuários para demonstração
    // Na prática, você precisaria adicionar um endpoint no backend para listar todos os usuários
    
    // Esta é apenas uma função de demonstração - na implementação real você faria uma requisição para o backend
    function carregarUsuarios() {
        listaUsuarios.innerHTML = '<p class="loading">Carregando usuários...</p>';
        
        // Simulação de dados - em uma implementação real, você faria uma requisição GET para o endpoint adequado
        const usuariosSimulados = [
            { id: 1, nome: 'Administrador', email: 'admin@example.com' },
            { id: 2, nome: 'João Silva', email: 'joao@example.com' },
            { id: 3, nome: 'Maria Souza', email: 'maria@example.com' }
        ];
        
        setTimeout(() => {
            // Limpa o conteúdo anterior
            listaUsuarios.innerHTML = '';
            
            if (usuariosSimulados.length === 0) {
                listaUsuarios.innerHTML = '<p>Nenhum usuário encontrado.</p>';
                return;
            }
            
            // Cria um card para cada usuário
            usuariosSimulados.forEach(usuario => {
                const card = document.createElement('div');
                card.className = 'usuario-card';
                card.innerHTML = `
                    <h3>${usuario.nome}</h3>
                    <div class="usuario-info">
                        <p><strong>Email:</strong> ${usuario.email}</p>
                    </div>
                    <div class="usuario-actions">
                        <button class="btn-visualizar" data-id="${usuario.id}">Visualizar Detalhes</button>
                    </div>
                `;
                
                listaUsuarios.appendChild(card);
            });
            
            // Adiciona evento para os botões de visualizar detalhes
            document.querySelectorAll('.btn-visualizar').forEach(btn => {
                btn.addEventListener('click', () => {
                    const id = btn.getAttribute('data-id');
                    carregarDetalhesUsuario(id);
                });
            });
        }, 1000); // Simula um delay de carregamento
    }
    
    // Função para carregar os detalhes de um usuário específico
    function carregarDetalhesUsuario(id) {
        detalhesUsuario.innerHTML = '<p>Carregando detalhes...</p>';
        modalDetalhes.classList.remove('hidden');
        
        // Faz a requisição para o endpoint correto
        fetch(`${baseUrl}/${id}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Erro ao buscar detalhes do usuário');
                }
            })
            .then(usuario => {
                detalhesUsuario.innerHTML = `
                    <p><strong>ID:</strong> ${usuario.id}</p>
                    <p><strong>Nome:</strong> ${usuario.nome}</p>
                    <p><strong>Email:</strong> ${usuario.email}</p>
                `;
            })
            .catch(error => {
                console.error('Erro:', error);
                detalhesUsuario.innerHTML = `<p>Erro ao carregar detalhes: ${error.message}</p>`;
            });
    }
    
    // Fecha o modal de detalhes
    fecharDetalhes.addEventListener('click', () => {
        modalDetalhes.classList.add('hidden');
    });
    
    // Carrega a lista de usuários ao iniciar
    carregarUsuarios();
});
