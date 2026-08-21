# 🐾 LudoPet — Sistema de Gestão e Adoção de Animais

> API RESTful desenvolvida com Java 17 e Spring Boot 3 para automatizar e gerenciar o processo de acolhimento, cuidados e adoção de animais, garantindo integridade de regras de negócio e persistência relacional com MySQL.

---

## 📸 Demonstração da Interface & Fluxos

Abaixo estão capturas de tela reais da plataforma LudoPet em funcionamento, ilustrando a interface de usuário e os principais fluxos de interação.

### 🏠 Página Inicial e Serviços

Esta é a porta de entrada da plataforma, onde os usuários podem acessar rapidamente as principais funcionalidades, como adoção, localização de pets perdidos e a loja.

<p align="center">
  <img src="./assets/image_1.png" alt="Página Inicial do LudoPet" width="800">
</p>

### 📢 Sistema de Alerta e Mural Público

Uma das funcionalidades centrais do LudoPet é o auxílio na localização de pets perdidos. Quando um animal é cadastrado como perdido, ele vai para um mural público. O sistema também oferece suporte para criar alertas no mural.

<p align="center">
  <img src="./assets/image_2.png" alt="Sistema de Alerta e Mural Público" width="800">
</p>

### 🛒 Loja Pet (Festival de Rações e Categorias)

O LudoPet conta com uma loja integrada. Esta captura mostra o banner do "Festival de Rações Especiais" e a navegação por categorias de produtos como brinquedos, higiene, camas e petiscos.

<p align="center">
  <img src="./assets/image_3.png" alt="Loja Pet - Festival de Rações" width="800">
</p>

### 🎁 Ofertas Especiais e Benefícios

Para novos clientes, a plataforma oferece cupons de desconto. Além disso, o sistema destaca benefícios como entrega rápida, pagamento seguro e suporte humano via WhatsApp.

<p align="center">
  <img src="./assets/image_4.png" alt="Ofertas Especiais e Benefícios" width="800">
</p>

### 🛍️ Carrinho de Compras e Catálogo

O fluxo de compra inclui um catálogo de produtos (filtrável por categoria) e um carrinho de compras simulado. Esta imagem mostra o carrinho vazio e a navegação do catálogo.

<p align="center">
  <img src="./assets/image_5.png" alt="Carrinho de Compras e Catálogo" width="800">
</p>

---

## 🎯 Sobre o Projeto

O **LudoPet** foi idealizado para solucionar a falta de padronização no controle de animais disponíveis para adoção e seus respectivos tutores, além de centralizar serviços essenciais para proprietários de pets. A aplicação implementa regras de negócio bem definidas, controle de status do animal e persistência segura em banco de dados relacional.

---

## ⚙️ Principais Funcionalidades (Backend)

- [x] **Gestão de Animais:** Cadastro, atualização cadastral, filtros por porte/espécie e controle de disponibilidade para adoção.
- [x] **Gestão de Tutores & Adoção:** Registro de adotantes e validação de requisitos para efetivação do processo de adoção.
- [x] **Sistema de Alertas:** Endpoints para criação e consulta de alertas de animais perdidos.
- [x] **Integração com Loja:** Estrutura para gerenciamento de produtos e pedidos.
- [x] **Arquitetura em Camadas:** Separação clara de responsabilidades entre Controladores, Serviços, Repositórios e DTOs.
- [x] **Tratamento de Exceções:** Manipulação global de erros HTTP com retornos padronizados (`400`, `404`, `500`).
- [x] **Validação de Integridade:** Proteção de endpoints e entidades com Jakarta Bean Validation.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Validação:** Jakarta Validation API
* **Gerenciador de Dependências:** Maven

---

## 🏛️ Estrutura do Projeto

```text
src/main/java/com/ludopet
 ├── controllers/    # Endpoints REST e recepção das requisições HTTP
 ├── services/       # Regras de negócio, fluxos e validações
 ├── repositories/   # Interfaces de comunicação e consultas ao banco
 ├── models/         # Entidades mapeadas pelo JPA/Hibernate
 └── dtos/           # Objetos de transferência de dados (Request/Response)
