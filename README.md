# 🛍️ E-commerce API

<div align="center">
  <img src="https://media.giphy.com/media/3o7TKSjRrfIPjeiVyM/giphy.gif" width="200"/>
</div>

## 📋 Sobre o Projeto

API de e-commerce desenvolvida com Spring Boot para gerenciamento de produtos, pedidos e usuários.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## 🛠️ Funcionalidades

- ✅ Cadastro de usuários
- 🛒 Gerenciamento de produtos
- 📦 Controle de pedidos
- 🔐 Autenticação de usuários

## 📝 Endpoints

### Usuários
```
POST /usuarios - Criar usuário
GET /usuarios - Listar usuários
GET /usuarios/{id} - Buscar usuário por ID
PUT /usuarios/{id} - Atualizar usuário
DELETE /usuarios/{id} - Deletar usuário
```

### Produtos
```
POST /produtos - Criar produto
GET /produtos - Listar produtos
GET /produtos/{id} - Buscar produto por ID
PUT /produtos/{id} - Atualizar produto
DELETE /produtos/{id} - Deletar produto
```

### Pedidos
```
POST /pedidos - Criar pedido
GET /pedidos - Listar pedidos
GET /pedidos/{id} - Buscar pedido por ID
PUT /pedidos/{id} - Atualizar pedido
DELETE /pedidos/{id} - Deletar pedido
GET /pedidos/cliente/{clienteId} - Listar pedidos do cliente
GET /pedidos/{id}/itens - Listar itens do pedido
```

## 🎨 Exemplos de Requisições

### Criar Usuário
```json
{
    "nome": "João Silva",
    "email": "joao@email.com",
    "telefone": "11999999999",
    "senha": "123456"
}
```

### Criar Produto
```json
{
    "nome": "Smartphone XYZ",
    "descricao": "Smartphone de última geração",
    "preco": 1999.99,
    "imagemUrl": "https://exemplo.com/smartphone.jpg"
}
```

### Criar Pedido
```json
{
    "cliente": {
        "id": 1
    },
    "items": [
        {
            "produtoId": 1,
            "quantidade": 2,
            "preco": 1999.99
        }
    ]
}
```

## 🚀 Como Executar

1. Clone o repositório
2. Configure o banco de dados no `application.properties`
3. Execute o projeto:
```bash
mvn spring-boot:run
```

## 📊 Status do Projeto

![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring](https://img.shields.io/badge/Spring%20Boot-3.1.0-green)

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<div align="center">
  <img src="https://media.giphy.com/media/3o7TKSjRrfIPjeiVyM/giphy.gif" width="200"/>
  <p>Feito com ❤️ por você!</p>
</div>

