document.addEventListener('DOMContentLoaded', () => {
    const listaPedidos = document.getElementById("lista-pedidos");
    const modal = document.getElementById("modalPedido");
    const modalDetalhes = document.getElementById("modalDetalhes");
    const fecharModal = document.getElementById("fecharModal");
    const fecharDetalhes = document.getElementById("fecharDetalhes");
    const formPedido = document.getElementById("formPedido");
    const btnNovoPedido = document.getElementById("btnNovoPedido");
    const detalhesPedido = document.getElementById("detalhesPedido");
    const modalTitulo = document.getElementById("modalTitulo");
    const pedidoId = document.getElementById("pedidoId");
    const clienteId = document.getElementById("clienteId");
    const dataPedido = document.getElementById("dataPedido");
  
    const baseUrl = "http://localhost:8080/pedidos";
    const clientesUrl = "http://localhost:8080/clientes";
    
    // Verifica se há parâmetros na URL (quando vem da página de clientes)
    const params = new URLSearchParams(window.location.search);
    const clienteIdParam = params.get('clienteId');
    
    // Se for um novo pedido a partir da página de cliente, preenche o ID do cliente
    if (clienteIdParam) {
      setTimeout(() => {
        pedidoId.value = "";
        clienteId.value = clienteIdParam;
        dataPedido.value = new Date().toISOString().split('T')[0]; // Data atual
        modalTitulo.innerText = "Novo Pedido";
        modal.classList.remove("hidden");
      }, 300);
    }
    
    // Função para carregar a lista de clientes no dropdown
    function carregarClientes() {
      // Modifica o campo clienteId para ser um select em vez de input
      if (clienteId.tagName !== "SELECT") {
        const select = document.createElement("select");
        select.id = "clienteId";
        select.required = true;
        clienteId.parentNode.replaceChild(select, clienteId);
        clienteId = document.getElementById("clienteId");
      }
      
      // Carrega a lista de clientes
      fetch(clientesUrl)
        .then(res => res.json())
        .then(clientes => {
          clienteId.innerHTML = "<option value=\"\">Selecione um cliente</option>";
          clientes.forEach(c => {
            const option = document.createElement("option");
            option.value = c.id;
            option.textContent = `${c.id} - ${c.nome}`;
            if (clienteIdParam && c.id == clienteIdParam) {
              option.selected = true;
            }
            clienteId.appendChild(option);
          });
        });
    }
  
    function carregarPedidos() {
      fetch(baseUrl)
        .then(res => res.json())
        .then(pedidos => {
          listaPedidos.innerHTML = "";
          pedidos.forEach(p => {
            const card = document.createElement("div");
            card.className = "pedido-card";
            card.innerHTML = `
              <strong>ID:</strong> ${p.id}<br>
              <strong>Cliente:</strong> ${p.clienteId}<br>
              <strong>Data:</strong> ${p.data}<br>
              <button class="btnEditar" data-id="${p.id}">Editar</button>
              <button class="btnExcluir" data-id="${p.id}">Excluir</button>
            `;
            // Ao clicar no card (exceto botões), mostra detalhes
            card.onclick = e => {
              if (e.target.classList.contains('btnEditar') || e.target.classList.contains('btnExcluir')) {
                return; // Ignora clique no botão para não abrir modal detalhes
              }
              verDetalhes(p.id);
            };
            listaPedidos.appendChild(card);
          });
  
          // Adiciona eventos para os botões Editar e Excluir
          document.querySelectorAll(".btnEditar").forEach(btn => {
            btn.onclick = e => {
              e.stopPropagation(); // para não disparar o card.onclick
              const id = btn.getAttribute("data-id");
              editarPedido(id);
            };
          });
  
          document.querySelectorAll(".btnExcluir").forEach(btn => {
            btn.onclick = e => {
              e.stopPropagation();
              const id = btn.getAttribute("data-id");
              excluirPedido(id);
            };
          });
        });
    }
  
    function verDetalhes(id) {
      fetch(`${baseUrl}/${id}`)
        .then(res => res.json())
        .then(p => {
          detalhesPedido.innerHTML = `
            <p><strong>ID:</strong> ${p.id}</p>
            <p><strong>Cliente ID:</strong> ${p.clienteId}</p>
            <p><strong>Data:</strong> ${p.data}</p>
          `;
          modalDetalhes.classList.remove("hidden");
        });
    }
  
    function editarPedido(id) {
      fetch(`${baseUrl}/${id}`)
        .then(res => res.json())
        .then(p => {
          pedidoId.value = p.id;
          dataPedido.value = p.data;
          modalTitulo.innerText = "Editar Pedido";
          carregarClientes(); // Carrega a lista de clientes no dropdown
          // Após carregar os clientes, selecionamos o cliente correto em um timeout
          setTimeout(() => {
            if (clienteId.tagName === "SELECT") {
              clienteId.value = p.clienteId;
            }
          }, 300);
          modal.classList.remove("hidden");
        });
    }
  
    function excluirPedido(id) {
      if (!confirm("Tem certeza que deseja excluir este pedido?")) return;
      fetch(`${baseUrl}/${id}`, {
        method: "DELETE"
      }).then(() => {
        carregarPedidos();
      });
    }
  
    btnNovoPedido.onclick = () => {
      pedidoId.value = "";
      dataPedido.value = new Date().toISOString().split('T')[0]; // Data atual
      modalTitulo.innerText = "Novo Pedido";
      carregarClientes(); // Carrega a lista de clientes no dropdown
      modal.classList.remove("hidden");
    };
  
    fecharModal.onclick = () => modal.classList.add("hidden");
    fecharDetalhes.onclick = () => modalDetalhes.classList.add("hidden");
  
    formPedido.onsubmit = e => {
      e.preventDefault();
      const metodo = pedidoId.value ? "PUT" : "POST";
      const url = pedidoId.value ? `${baseUrl}/${pedidoId.value}` : baseUrl;
      const payload = {
        clienteId: clienteId.value,
        data: dataPedido.value
      };
      fetch(url, {
        method: metodo,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      }).then(() => {
        modal.classList.add("hidden");
        carregarPedidos();
      });
    };
  
    // Carrega os clientes quando a página é carregada
    carregarClientes();
    
    // Carrega os pedidos
    carregarPedidos();
  });
  