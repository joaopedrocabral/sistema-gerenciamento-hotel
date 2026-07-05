# 🏨 Sistema de Gerenciamento de Hotel

Sistema desenvolvido em Java como projeto da disciplina de **Programação de Computadores II**, com o objetivo de consolidar conhecimentos em **Programação Orientada a Objetos** por meio da implementação de um sistema de gerenciamento de hotel.

O projeto foi desenvolvido priorizando uma arquitetura organizada e escalável, aplicando conceitos como arquitetura em camadas, persistência de dados, coleções da Java Collections Framework e padrões de projeto, buscando aproximar o desenvolvimento de boas práticas utilizadas em sistemas reais.

---

## 📋 Descrição

O sistema permite realizar o gerenciamento básico de um hotel, contemplando o cadastro de clientes, quartos, usuários e reservas, além das operações de check-in, check-out e cancelamento de reservas.

Os dados são persistidos automaticamente em arquivos `.txt`, permitindo que as informações permaneçam disponíveis entre diferentes execuções da aplicação.

---

## ✨ Funcionalidades

- Cadastro, consulta, listagem e remoção de clientes, quartos, usuários e reservas;
- Realização de check-in, check-out e cancelamento de reservas;
- Persistência automática de dados em arquivos `.txt`;
- Geração automática de IDs para clientes, usuários e reservas;
- Formatação automática de CPF e telefone;
- Validações para garantir a consistência dos dados;
- Confirmação para operações destrutivas;
- Controle automático do status dos quartos e das reservas.

---

## 🛠️ Tecnologias

- Java JDK 26

---

## 📚 Conceitos aplicados

Durante o desenvolvimento foram utilizados diversos conceitos estudados ao longo da disciplina, entre eles:

- Programação Orientada a Objetos;
- Encapsulamento;
- Herança;
- Polimorfismo;
- Sobrescrita de métodos;
- Arquitetura em camadas (Model, Repository, Service e Persistência);
- Persistência de dados em arquivos texto;
- Java Collections Framework (`HashMap`);
- Padrão de Projeto Singleton;
- Tratamento de exceções;
- Manipulação de arquivos;
- Manipulação de datas com a API `java.time`.

---

## 🚀 Melhorias futuras

Algumas funcionalidades planejadas para futuras versões incluem:

- Implementação de testes automatizados utilizando JUnit;
- Criptografia das senhas dos usuários;
- Melhoria na gestão das datas das reservas e disponibilidade dos quartos;
- Controle de permissões de usuários;
- Relatórios e consultas mais avançadas;
- Evolução da persistência para utilização de banco de dados.

---

## 👨‍💻 Autor

**João Pedro Cabral**

Graduando em Engenharia de Computação  
Universidade Federal de Ouro Preto - UFOP
