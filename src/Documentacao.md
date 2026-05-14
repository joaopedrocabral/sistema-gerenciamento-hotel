## DOCUMENTAÇÃO — SISTEMA DE GERENCIAMENTO DE HOTEL  
## Objetivo do Projeto  
Desenvolver um sistema de gerenciamento de hotel/pousada utilizando Java com foco em:  
* Programação Orientada a Objetos  
* Estruturação profissional de projeto  
* Separação de responsabilidades  
* Persistência de dados  
* Tratamento de exceções  
* Regras de negócio  
* Boas práticas de desenvolvimento  
O sistema será executado via terminal (console), utilizando menus interativos.  
  
## FUNCIONALIDADES DO SISTEMA  
## Login de usuários  
O sistema deve:  
* Solicitar login e senha ao iniciar  
* Validar credenciais  
* Permitir acesso ao menu principal  
* Controlar permissões por cargo  
  
## Gerenciamento de clientes  
Permitir:  
* Cadastrar cliente  
* Buscar cliente  
* Listar clientes  
* Editar cliente  
* Remover cliente  
  
## Gerenciamento de quartos  
Permitir:  
* Cadastrar quartos  
* Listar quartos  
* Alterar status  
* Buscar quarto por número  
* Verificar disponibilidade  
  
## Gerenciamento de reservas  
Permitir:  
* Criar reserva  
* Cancelar reserva  
* Realizar check-in  
* Realizar check-out  
* Verificar conflitos de datas  
* Calcular valor total da estadia  
  
## Persistência de dados  
O sistema deve:  
* Salvar dados em arquivos TXT  
* Carregar dados ao iniciar  
* Atualizar arquivos após alterações  
  
## ESTRUTURA DE PACOTES  
```
src/
│
├── main/
├── model/
├── service/
├── repository/
├── exception/
├── util/
├── enums/

```
  
## DESCRIÇÃO DOS PACOTES  
## main  
Responsável por:  
* Inicialização do sistema  
* Menus  
* Interação com usuário  
* Fluxo principal  
Classe principal:  
```
Main.java

```
  
## model  
Responsável pelas entidades do sistema.  
Classes:  
```
Pessoa.java
Cliente.java
Usuario.java
Quarto.java
Reserva.java

```
  
## service  
Responsável pelas regras de negócio.  
Classes:  
```
AuthService.java
ClienteService.java
QuartoService.java
ReservaService.java
UsuarioService.java

```
  
## repository  
Responsável pela persistência dos dados.  
Classes:  
```
ClienteRepository.java
UsuarioRepository.java
QuartoRepository.java
ReservaRepository.java

```
  
## exception  
Responsável pelas exceções personalizadas.  
Classes:  
```
LoginInvalidoException.java
ReservaInvalidaException.java
QuartoIndisponivelException.java
DataInvalidaException.java
ClienteNaoEncontradoException.java

```
  
## util  
Responsável por utilidades auxiliares.  
Classes:  
```
LeitorArquivo.java
GeradorId.java
FormatadorData.java

```
  
## enums  
Responsável pelos enums do sistema.  
Classes:  
```
Cargo.java
StatusQuarto.java
StatusReserva.java
TipoQuarto.java

```
  
## MODELAGEM DAS CLASSES  
## Pessoa  
Classe base do sistema.  
## Atributos  
```
nome
cpf
telefone

```
## Métodos  
```
getters/setters
toString()

```
  
## Cliente  
Herda de Pessoa.  
## Atributos  
```
id
historicoReservas

```
## Métodos  
```
adicionarReserva()
listarReservas()

```
  
## Usuario  
Herda de Pessoa.  
## Atributos  
```
login
senha
cargo

```
## Métodos  
```
validarSenha()

```
  
## Quarto  
## Atributos  
```
numero
tipo
valorDiaria
status
capacidade

```
## Métodos  
```
estaDisponivel()
alterarStatus()

```
  
## Reserva  
## Atributos  
```
id
cliente
quarto
dataEntrada
dataSaida
valorTotal
status

```
## Métodos  
```
calcularValorTotal()
realizarCheckIn()
realizarCheckOut()
cancelarReserva()

```
  
## ENUMS  
## Cargo  
```
ADMIN
RECEPCIONISTA

```
  
## StatusQuarto  
```
DISPONIVEL
OCUPADO
MANUTENCAO

```
  
## StatusReserva  
```
ATIVA
CANCELADA
FINALIZADA

```
  
## TipoQuarto  
```
SIMPLES
LUXO
SUITE

```
  
## REGRAS DE NEGÓCIO  
## Login  
* Login deve existir  
* Senha deve ser válida  
* Usuário deve estar ativo  
  
## Clientes  
* CPF não pode repetir  
* Nome não pode ser vazio  
* Telefone deve possuir formato válido  
  
## Quartos  
* Número do quarto não pode repetir  
* Valor da diária deve ser positivo  
* Quarto em manutenção não pode receber reservas  
  
## Reservas  
## Regras obrigatórias  
* Data de entrada não pode ser no passado  
* Data de saída deve ser após entrada  
* Quarto deve estar disponível  
* Não pode existir conflito de datas  
  
## Check-in  
* Só pode ocorrer na data correta  
* Reserva deve estar ativa  
  
## Check-out  
* Atualiza status do quarto  
* Finaliza reserva  
  
## Cancelamento  
* Reserva cancelada não pode receber check-in  
* Reserva finalizada não pode ser cancelada  
  
## PERSISTÊNCIA  
## Arquivos TXT  
Arquivos sugeridos:  
```
clientes.txt
usuarios.txt
quartos.txt
reservas.txt

```
  
## Estrutura de salvamento  
## Cliente  
```
id;nome;cpf;telefone

```
  
## Usuario  
```
nome;cpf;telefone;login;senha;cargo

```
  
## Quarto  
```
numero;tipo;valorDiaria;status;capacidade

```
  
## Reserva  
```
id;clienteId;numeroQuarto;dataEntrada;dataSaida;valorTotal;status

```
  
## RESPONSABILIDADES DAS CAMADAS  
## Main  
Responsável apenas por:  
* Menus  
* Entrada de dados  
* Exibição de informações  
NÃO deve:  
* validar regras complexas  
* salvar arquivos  
  
## Service  
Responsável por:  
* Regras de negócio  
* Validações  
* Controle do sistema  
  
## Repository  
Responsável por:  
* Ler arquivos  
* Salvar arquivos  
* Atualizar persistência  
  
## FLUXO DO SISTEMA  
## Inicialização  
```
1. Carregar arquivos
2. Solicitar login
3. Validar usuário
4. Abrir menu principal

```
  
## Fluxo de reserva  
```
1. Selecionar cliente
2. Selecionar quarto
3. Informar datas
4. Validar disponibilidade
5. Criar reserva
6. Salvar dados

```
  
## POSSÍVEIS MELHORIAS FUTURAS  
## Banco de dados  
Migrar TXT para:  
* MySQL  
* PostgreSQL  
  
## Interface gráfica  
Migrar console para:  
* JavaFX  
* Swing  
  
## Relatórios  
Adicionar:  
* Relatório financeiro  
* Taxa de ocupação  
* Histórico de reservas  
  
## Segurança  
Adicionar:  
* Criptografia de senha  
* Controle de sessão  
* Permissões avançadas  
  
## OBJETIVOS DE APRENDIZADO  
Este projeto deve ajudar no aprendizado de:  
* POO  
* Herança  
* Encapsulamento  
* Polimorfismo  
* Interfaces  
* Collections  
* Exceções  
* Arquitetura em camadas  
* Persistência  
* Manipulação de datas  
* Estruturação profissional de software  
* Refatoração  
* Organização de código  
