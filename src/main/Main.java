package main;
import enums.Cargo;
import enums.TipoQuarto;
import model.*;
import repository.*;
import service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void menuPrincipal(){
        titulo("SISTEMA HOTEL");
        System.out.println("1 - Clientes");
        System.out.println("2 - Quartos");
        System.out.println("3 - Reservas");
        System.out.println("4 - Usuários");
        System.out.println("0 - Encerrar sistema\n");

        System.out.println("Escolha uma opção: ");
    }

    public static void titulo(String titulo){
        System.out.println("===== " + titulo.toUpperCase() + " =====\n");
    }

    public static void menuClientes(){
        titulo("MENU CLIENTES");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Buscar Cliente");
        System.out.println("3 - Listar Clientes");
        System.out.println("4 - Remover Cliente");
        System.out.println("0 - Voltar\n");
        System.out.println("Escolha uma opção: ");
    }

    public static void executarMenuClientes(Scanner scanner, ClienteService clienteService){
        int opcaoMenuCliente = -1;

        while (opcaoMenuCliente != 0) {
            menuClientes();

            opcaoMenuCliente = lerInteiro(scanner);

            switch (opcaoMenuCliente) {
                case 1:
                    try {

                        titulo("CADASTRO DE CLIENTES");

                        System.out.println("ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);

                        System.out.println("Nome: ");
                        String nomeCliente = lerString(scanner);

                        System.out.println("CPF: ");
                        String cpfCliente = lerString(scanner);

                        System.out.println("Telefone: ");
                        String telefoneCliente = lerString(scanner);

                        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, idCliente);
                        clienteService.cadastrarCliente(cliente);

                        System.out.println("Cliente cadastrado com sucesso!");

                    } catch (IllegalArgumentException e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 2:
                    try{
                        System.out.println("Digite o ID do cliente: ");
                        int idCliente = lerInteiro(scanner);
                        System.out.println(clienteService.buscarClientePorId(idCliente));

                    } catch (IllegalArgumentException e){

                        System.out.println(e.getMessage());

                    }
                    break;

                case 3:
                    ArrayList<Cliente> listaClientes = clienteService.listarClientes();

                    if(listaClientes.isEmpty()){
                        System.out.println("ERRO! Não existe clientes cadastrados.");

                    }else {
                        for (Cliente cliente : listaClientes) {
                            System.out.println(cliente);
                        }
                    }
                    break;

                case 4:
                    try{
                        titulo("REMOVER CLIENTE");

                        System.out.println("Digite o ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);

                        clienteService.removerCliente(idCliente);

                        System.out.println("Cliente removido com sucesso!");

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:

                    break;

                default:

                    System.out.println("Opção Inválida, tente novamente.");

                    break;
            }
        }
    }

    public static void menuQuartos(){
        titulo("MENU QUARTOS");

        System.out.println("1 - Cadastrar Quarto");
        System.out.println("2 - Buscar Quarto");
        System.out.println("3 - Listar Quartos");
        System.out.println("4 - Listar Quartos Disponíveis");
        System.out.println("5 - Remover Quarto");
        System.out.println("0 - Voltar\n");
        System.out.println("Escolha uma opção: ");
    }

    public static void executarMenuQuartos(Scanner scanner, QuartoService quartoService){
        int opcaoMenuQuartos = -1;

        while (opcaoMenuQuartos != 0){
            menuQuartos();
            opcaoMenuQuartos = lerInteiro(scanner);

            switch (opcaoMenuQuartos){
                case 1:
                    try {
                        titulo("CADASTRO DE QUARTOS");

                        System.out.println("Digite o Número do quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        menuTiposQuartos();
                        int opcaoTipo = lerInteiro(scanner);

                        TipoQuarto tipoQuarto;

                        switch (opcaoTipo) {
                            case 1:
                                tipoQuarto = TipoQuarto.SIMPLES;
                                break;

                            case 2:
                                tipoQuarto = TipoQuarto.PREMIUM;
                                break;

                            case 3:
                                tipoQuarto = TipoQuarto.LUXO;
                                break;

                            default:
                                throw new IllegalArgumentException("ERRO! Tipo de quarto inválido.");
                        }

                        System.out.println("Digite a capacidade do Quarto: ");
                        int capacidadeQuarto = lerInteiro(scanner);

                        System.out.println("Digite o valor da diária: R$ ");
                        double valorDiariaQuarto = lerDouble(scanner);

                        Quarto quarto = new Quarto(numeroQuarto, tipoQuarto, capacidadeQuarto, valorDiariaQuarto);

                        quartoService.cadastrarQuarto(quarto);

                        System.out.println("Quarto cadastrado com sucesso!");

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Digite o número do quarto: ");
                        int numeroQuarto = lerInteiro(scanner);
                        System.out.println(quartoService.buscarQuartoPorNumero(numeroQuarto));

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    ArrayList<Quarto> listaQuartos = quartoService.listarQuartos();

                    if(listaQuartos.isEmpty()){
                        System.out.println("ERRO! Não existe quartos cadastrados!");

                    }else{
                        for(Quarto quarto : listaQuartos){
                            System.out.println(quarto);
                        }
                    }
                    break;

                case 4:
                    ArrayList<Quarto> listaQuartosDisponiveis = quartoService.listarQuartosDisponiveis();

                    if(listaQuartosDisponiveis.isEmpty()){
                        System.out.println("ERRO! Não existe quartos disponíveis!");

                    } else {
                        for(Quarto quarto : listaQuartosDisponiveis){
                            System.out.println(quarto);
                        }
                    }
                    break;

                case 5:
                    try{
                        titulo("REMOVER QUARTO");

                        System.out.println("Digite o Número do Quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        quartoService.removerQuarto(numeroQuarto);

                        System.out.println("Quarto removido com sucesso!");

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:

                    break;

                default:
                    System.out.println("Opção Inválida!");

                    break;
            }
        }
    }

    public static void menuTiposQuartos(){
        System.out.println("Escolha o tipo do quarto:\n");
        System.out.println("1 - Simples");
        System.out.println("2 - Premium");
        System.out.println("3 - Luxo\n");
        System.out.println("Escolha uma opção: ");
    }

    public static void menuReservas(){
        titulo("MENU RESERVAS");
        System.out.println("1 - Criar Reserva");
        System.out.println("2 - Realizar Check-in");
        System.out.println("3 - Realizar Check-out");
        System.out.println("4 - Cancelar Reserva");
        System.out.println("5 - Buscar Reserva");
        System.out.println("6 - Listar Reservas");
        System.out.println("7 - Listar Reservas Ativas");
        System.out.println("8 - Listar Reservas Inativas");
        System.out.println("9 - Buscar Reservas por Cliente");
        System.out.println("10 - Buscar Reservas por Quarto");
        System.out.println("0 - Voltar\n");
        System.out.println("Escolha uma opção: ");
    }

    public static void executarMenuReservas(Scanner scanner, ReservaService reservaService,
                                            ClienteService clienteService, QuartoService quartoService){
        int opcaoMenuReservas = -1;

        while (opcaoMenuReservas != 0){
            menuReservas();
            opcaoMenuReservas = lerInteiro(scanner);

            switch (opcaoMenuReservas) {
                case 1:
                    try {

                        titulo("CRIAR RESERVA");

                        System.out.println("Digite o ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        System.out.println("Digite o ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);

                        System.out.println("Digite o Número do Quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        System.out.println("Digite a data de Check-in (DD/MM/AAAA): ");
                        LocalDate dataCheckin = lerData(scanner);

                        System.out.println("Digite a data de Check-out (DD/MM/AAAA): ");
                        LocalDate dataCheckout = lerData(scanner);

                        Reserva reserva = reservaService.criarReserva(idReserva, idCliente, numeroQuarto, dataCheckin, dataCheckout);

                        System.out.println(reserva);

                        System.out.println("\nReserva criada com sucesso!");

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        titulo("CHECK-IN");

                        System.out.println("Digite o ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        reservaService.realizarCheckIn(idReserva);

                        System.out.println("Check-in realizado com sucesso!");

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        titulo("CHECK-OUT");

                        System.out.println("Digite o ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        reservaService.realizarCheckOut(idReserva);

                        System.out.println("Check-Out realizado com sucesso!");

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        titulo("CANCELAR RESERVA");

                        System.out.println("Digite o ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        reservaService.cancelarReserva(idReserva);

                        System.out.println("Reserva cancelada com sucesso!");

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        titulo("BUSCAR RESERVA POR ID");

                        System.out.println("Digite o ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        System.out.println(reservaService.buscarReservaPorId(idReserva));

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    titulo("LISTAR RESERVAS");

                    ArrayList<Reserva> listaReservas = reservaService.listarReservas();

                    if (listaReservas.isEmpty()) {
                        System.out.println("ERRO! Não existe reservas cadastradas!");

                    } else {
                        for (Reserva reserva : listaReservas) {
                            System.out.println(reserva);
                        }

                    }
                    break;

                case 7:
                    titulo("LISTAR RESERVAS ATIVAS");

                    ArrayList<Reserva> listaReservasAtivas = reservaService.listarReservasAtivas();

                    if (listaReservasAtivas.isEmpty()) {
                        System.out.println("ERRO! Não existe reservas ativas!");

                    } else {
                        for (Reserva reserva : listaReservasAtivas) {
                            System.out.println(reserva);
                        }

                    }
                    break;

                case 8:
                    titulo("LISTAR RESERVAS INATIVAS");

                    ArrayList<Reserva> listaReservasInativas = reservaService.listarReservasInativas();

                    if (listaReservasInativas.isEmpty()) {
                        System.out.println("ERRO! Não existe reservas inativas!");

                    } else {
                        for (Reserva reserva : listaReservasInativas) {
                            System.out.println(reserva);
                        }

                    }

                    break;

                case 9:
                    try {
                        titulo("BUSCAR RESERVA POR CLIENTE");

                        System.out.println("Digite o ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);

                        Cliente cliente = clienteService.buscarClientePorId(idCliente);

                        ArrayList<Reserva> reservasDoCliente = reservaService.buscarReservasPorCliente(cliente);

                        if (reservasDoCliente.isEmpty()) {
                            System.out.println("ERRO! Esse cliente não possui reservas cadastradas.");

                        } else {
                            for (Reserva reserva : reservasDoCliente) {
                                System.out.println(reserva);
                            }

                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:
                    try {
                        titulo("BUSCAR RESERVAR POR QUARTO");

                        System.out.println("Digite o número do Quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        Quarto quarto = quartoService.buscarQuartoPorNumero(numeroQuarto);

                        ArrayList<Reserva> reservasDoQuarto = reservaService.buscarReservasPorQuarto(quarto);

                        if (reservasDoQuarto.isEmpty()) {
                            System.out.println("ERRO! Esse quarto não possui reservas.");

                        } else {
                            for (Reserva reserva : reservasDoQuarto) {
                                System.out.println(reserva);
                            }

                        }
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:

                    break;

                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }
    }
    public static void menuUsuarios(){
        titulo("MENU USUÁRIOS");

        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Autenticar Usuário");
        System.out.println("3 - Listar Usuários");
        System.out.println("4 - Remover Usuário");
        System.out.println("0 - Voltar");
    }

    public static void executarMenuUsuarios(Scanner scanner, UsuarioService usuarioService){

        int opcaoMenuUsuarios = -1;

        while(opcaoMenuUsuarios != 0){
            menuUsuarios();

            opcaoMenuUsuarios = lerInteiro(scanner);

            switch (opcaoMenuUsuarios){
                case 1:
                    try {
                     titulo("CADASTRO DE USUÁRIO");

                        System.out.println("Digite o ID do Usuário: ");
                        int idUsuario = lerInteiro(scanner);

                        System.out.println("Digite o Nome do Usuário: ");
                        String nomeUsuario = lerString(scanner);

                        System.out.println("Digite o CPF do Usuário: ");
                        String cpfUsuario = lerString(scanner);

                        System.out.println("Digite o Telefone do Usuário: ");
                        String telefoneUsuario = lerString(scanner);

                        System.out.println("Digite a Senha do Usuário: ");
                        String senhaUsuario = lerString(scanner);

                        menuCargoUsuario();
                        int opcaoCargo = lerInteiro(scanner);

                        Cargo cargo;

                        switch (opcaoCargo) {
                            case 1:
                                cargo = Cargo.ATENDENTE;
                                break;
                            case 2:
                                cargo = Cargo.GERENTE;
                                break;

                            default:
                                throw new IllegalArgumentException("ERRO! Cargo inválido.");
                        }

                        Usuario usuario = new Usuario(nomeUsuario, cpfUsuario, telefoneUsuario, idUsuario, senhaUsuario, cargo);

                        usuarioService.cadastrarUsuario(usuario);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        titulo("AUTENTICAR USUÁRIO");

                        System.out.println("Digite o ID do Usuário: ");
                        int idUsuario = lerInteiro(scanner);

                        System.out.println("Digite a Senha: ");
                        String senhaUsuario = lerString(scanner);

                        Usuario usuario = usuarioService.autenticarUsuario(idUsuario, senhaUsuario);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    ArrayList<Usuario> listaUsuarios = usuarioService.listarUsuarios();

                    if(listaUsuarios.isEmpty()){
                        System.out.println("ERRO! Não existe usuários cadastrados.");

                    } else {
                        for (Usuario usuario : listaUsuarios){
                            System.out.println(usuario);
                        }
                    }
                    break;

                case 4:
                    try {
                        titulo("REMOVER USUÁRIO");

                        System.out.println("Digite o ID do Usuário: ");
                        int idUsuario = lerInteiro(scanner);

                        usuarioService.removerUsuario(idUsuario);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 0:

                    break;

                default:
                    System.out.println("ERRO! Opção Inválida.");
                    break;
            }

        }
    }

    public static void menuCargoUsuario(){
        System.out.println("Escolha o cargo do Usuário:\n");
        System.out.println("1 - Atendente");
        System.out.println("2 - Gerente");
        System.out.println("Escolha uma opção: ");
    }

    public static int lerInteiro(Scanner scanner){
        while (true){
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;

            } catch (Exception e){
                System.out.println("ERRO! Digite um número inteiro válido.");

                scanner.nextLine();
            }
        }
    }

    public static double lerDouble(Scanner scanner){
        while(true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;

            } catch (Exception e) {
                System.out.println("ERRO! Digite um valor válido.");
                scanner.nextLine();
            }
        }
    }

    public static String lerString(Scanner scanner){
        return scanner.nextLine();
    }

    public static LocalDate lerData(Scanner scanner){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                String data = scanner.nextLine();

                return LocalDate.parse(data, formatador);

            } catch (Exception e){
                System.out.println("ERRO! Use DD/MM/AAAA");
            }
        }
    }

    static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ClienteRepository clienteRepository = new ClienteRepository();
        QuartoRepository quartoRepository = new QuartoRepository();
        ReservaRepository reservaRepository = new ReservaRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();

        ClienteService clienteService = new ClienteService(clienteRepository);
        QuartoService quartoService = new QuartoService(quartoRepository);
        ReservaService reservaService = new ReservaService(reservaRepository, clienteRepository, quartoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        int opcaoMenu = -1;

        while (opcaoMenu != 0){
            menuPrincipal();

            opcaoMenu = lerInteiro(scanner);

            switch (opcaoMenu){
                case 1:
                    executarMenuClientes(scanner, clienteService);
                    break;

                case 2: {
                    executarMenuQuartos(scanner, quartoService);
                    break;
                }

                case 3: {
                    executarMenuReservas(scanner, reservaService, clienteService, quartoService);
                    break;
                }

                case 4: {
                    executarMenuUsuarios(scanner, usuarioService);
                    break;
                }

                case 0: {
                    System.out.println("Encerrando...");

                    break;
                }

                default: {
                    System.out.println("ERRO! Opção Inválida");

                    break;
                }

            }
        }

    }
}
