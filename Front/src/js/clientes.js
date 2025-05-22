document.addEventListener('DOMContentLoaded', () => {
  const listaClientes = document.getElementById("lista-clientes");
  const modal = document.getElementById("modalCliente");
  const modalDetalhes = document.getElementById("modalDetalhes");
  const fecharModal = document.getElementById("fecharModal");
  const fecharDetalhes = document.getElementById("fecharDetalhes");
  const formCliente = document.getElementById("formCliente");
  const btnNovoCliente = document.getElementById("btnNovoCliente");
  const detalhesCliente = document.getElementById("detalhesCliente");
  const listaPedidosCliente = document.getElementById("lista-pedidos-cliente");
  const btnNovoPedidoCliente = document.getElementById("btnNovoPedidoCliente");
  const modalTitulo = document.getElementById("modalTitulo");
  const clienteId = document.getElementById("clienteId");
  const nome = document.getElementById("nome");
  const email = document.getElementById("email");
  const telefone = document.getElementById("telefone");
  const endereco = document.getElementById("endereco");

  const baseUrl = "http://localhost:8080/clientes";
  const pedidosUrl = "http://localhost:8080/pedidos";

  function carregarClientes() {
    fetch(baseUrl)
      .then(res => res.json())
      .then(clientes => {
        listaClientes.innerHTML = "";
        clientes.forEach(c => {
          const card = document.createElement("div");
          card.className = "cliente-card";
          card.innerHTML = `
            <strong>Nome:</strong> ${c.nome}<br>
            <strong>Email:</strong> ${c.email}<br>
            <strong>Telefone:</strong> ${c.telefone}<br>
            <button class="btnEditar" data-id="${c.id}">Editar</button>
            <button class="btnExcluir" data-id="${c.id}">Excluir</button>
          `;
          // Ao clicar no card (exceto botões), mostra detalhes
          card.onclick = e => {
            if (e.target.classList.contains('btnEditar') || e.target.classList.contains('btnExcluir')) {
              return; // Ignora clique no botão para não abrir modal detalhes
            }
            verDetalhes(c.id);
          };
          listaClientes.appendChild(card);
        });

        // Adiciona eventos para os botões Editar e Excluir
        document.querySelectorAll(".btnEditar").forEach(btn => {
          btn.onclick = e => {
            e.stopPropagation(); // para não disparar o card.onclick
            const id = btn.getAttribute("data-id");
            editarCliente(id);
          };
        });

        document.querySelectorAll(".btnExcluir").forEach(btn => {
          btn.onclick = e => {
            e.stopPropagation();
            const id = btn.getAttribute("data-id");
            excluirCliente(id);
          };
        });
      });
  }

  function verDetalhes(id) {
    // Busca detalhes do cliente
    fetch(`${baseUrl}/${id}`)
      .then(res => res.json())
      .then(c => {
        // Mostra detalhes do cliente
        detalhesCliente.innerHTML = `
          <h2>${c.nome}</h2>
          <p><strong>ID:</strong> ${c.id}</p>
          <p><strong>Email:</strong> ${c.email}</p>
          <p><strong>Telefone:</strong> ${c.telefone}</p>
          <p><strong>Endereço:</strong> ${c.endereco}</p>
        `;

        // Busca pedidos do cliente
        fetch(`${pedidosUrl}?clienteId=${id}`)
          .then(res => res.json())
          .then(pedidos => {
            listaPedidosCliente.innerHTML = "";
            if (pedidos.length === 0) {
              listaPedidosCliente.innerHTML = "<p>Este cliente não possui pedidos.</p>";
            } else {
              pedidos.forEach(p => {
                const pedidoItem = document.createElement("div");
                pedidoItem.className = "pedido-cliente-item";
                pedidoItem.innerHTML = `
                  <p><strong>ID do Pedido:</strong> ${p.id}</p>
                  <p><strong>Data:</strong> ${p.data}</p>
                `;
                listaPedidosCliente.appendChild(pedidoItem);
              });
            }
          });

        modalDetalhes.classList.remove("hidden");
        
        // Configura botão para criar novo pedido para este cliente
        btnNovoPedidoCliente.onclick = () => {
          window.location.href = `pedidos.html?clienteId=${id}`;
        };
      });
  }

  function editarCliente(id) {
    fetch(`${baseUrl}/${id}`)
      .then(res => res.json())
      .then(c => {
        clienteId.value = c.id;
        nome.value = c.nome;
        email.value = c.email;
        telefone.value = c.telefone;
        endereco.value = c.endereco;
        modalTitulo.innerText = "Editar Cliente";
        modal.classList.remove("hidden");
      });
  }

  function excluirCliente(id) {
    if (!confirm("Tem certeza que deseja excluir este cliente? Todos os pedidos relacionados também serão excluídos.")) return;
    fetch(`${baseUrl}/${id}`, {
      method: "DELETE"
    }).then(() => {
      carregarClientes();
    });
  }

  btnNovoCliente.onclick = () => {
    clienteId.value = "";
    nome.value = "";
    email.value = "";
    telefone.value = "";
    endereco.value = "";
    modalTitulo.innerText = "Novo Cliente";
    modal.classList.remove("hidden");
  };

  fecharModal.onclick = () => modal.classList.add("hidden");
  fecharDetalhes.onclick = () => modalDetalhes.classList.add("hidden");

  formCliente.onsubmit = e => {
    e.preventDefault();
    const metodo = clienteId.value ? "PUT" : "POST";
    const url = clienteId.value ? `${baseUrl}/${clienteId.value}` : baseUrl;
    const payload = {
      nome: nome.value,
      email: email.value,
      telefone: telefone.value,
      endereco: endereco.value
    };
    fetch(url, {
      method: metodo,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    }).then(() => {
      modal.classList.add("hidden");
      carregarClientes();
    });
  };

  carregarClientes();
});
