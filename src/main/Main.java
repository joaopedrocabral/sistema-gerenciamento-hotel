package main;
import enums.Cargo;
import enums.TipoQuarto;
import model.*;
import repository.*;
import service.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
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
                System.out.println("ERRO! Use o formato DD/MM/AAAA");
                e.getMessage();
            }
        }
    }
    public static void mostrarTitulos(String titulo){
        int largura = 40;
        int espacos = (largura - titulo.length()) / 2;

        titulo = titulo.trim().toUpperCase();

        System.out.println();

        System.out.println("=".repeat(largura));
        System.out.println(" ".repeat(espacos) + titulo);
        System.out.println("=".repeat(largura));
    }
    public static void aguardarEnter(Scanner scanner){
        System.out.println();
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
    public static String formatarCPF(String cpf){
        if(cpf == null){
            throw new IllegalArgumentException("ERRO! CPF inválido.");
        }

        cpf = cpf.replaceAll("\\D","");

        if(cpf.length() != 11){
            throw new IllegalArgumentException("ERRO! CPF deve conter 11 dígitos");
        }

        return  cpf.substring(0,3) + "." +
                cpf.substring(3,6) + "." +
                cpf.substring(6,9) + "-" +
                cpf.substring(9);
    }
    public static String formatarTelefone(String telefone){
        if(telefone == null){
            throw new IllegalArgumentException("ERRO! Telefone inválido.");
        }

        telefone = telefone.replaceAll("\\D", "");

        if(telefone.length() != 10 && telefone.length() != 11){
            throw new IllegalArgumentException("ERRO! Esse não é um número de telefone.");
        }

        if(telefone.length() == 10){
            return  "(" + telefone.substring(0,2) +") " +
                    telefone.substring(2,6) + "-" +
                    telefone.substring(6);
        }

        return "(" + telefone.substring(0,2) +") " +
                telefone.substring(2,7) + "-" +
                telefone.substring(7);
    }
    public static boolean confirmarAcao(Scanner scanner, String mensagem){
        System.out.println();
        System.out.println(mensagem);

        System.out.println("1 - Sim");
        System.out.println("2 - Não");

        int opcaoConfirmacao = lerInteiro(scanner);

        return opcaoConfirmacao == 1;
    }

    public static void menuPrincipal(){
        mostrarTitulos("SISTEMA HOTEL");
        System.out.println("1 - Clientes");
        System.out.println("2 - Quartos");
        System.out.println("3 - Reservas");
        System.out.println("4 - Usuários\n");

        System.out.println("0 - Encerrar sistema\n");

        System.out.println("Escolha: ");
    }
    public static void menuClientes(){
        mostrarTitulos("MENU CLIENTES");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Buscar Cliente");
        System.out.println("3 - Listar Clientes");
        System.out.println("4 - Remover Cliente\n");
        System.out.println("0 - Voltar\n");
        System.out.println("Escolha: ");
    }
    public static void menuQuartos(){
        mostrarTitulos("MENU QUARTOS");

        System.out.println("1 - Cadastrar Quarto");
        System.out.println("2 - Buscar Quarto");
        System.out.println("3 - Listar Quartos");
        System.out.println("4 - Listar Quartos Disponíveis");
        System.out.println("5 - Remover Quarto\n");
        System.out.println("0 - Voltar\n");
        System.out.println("Escolha: ");
    }
    public static void menuTiposQuartos(){
        System.out.println("Escolha o tipo do quarto:\n");
        System.out.println("1 - Simples");
        System.out.println("2 - Premium");
        System.out.println("3 - Luxo\n");
        System.out.println("Escolha: ");
    }
    public static void menuReservas(){
        mostrarTitulos("MENU RESERVAS");
        System.out.println("1 - Criar Reserva");
        System.out.println("2 - Realizar Check-in");
        System.out.println("3 - Realizar Check-out");
        System.out.println("4 - Cancelar Reserva");
        System.out.println("5 - Buscar Reserva");
        System.out.println("6 - Listar Reservas");
        System.out.println("7 - Listar Reservas Ativas");
        System.out.println("8 - Listar Reservas Inativas");
        System.out.println("9 - Buscar Reservas por Cliente");
        System.out.println("10 - Buscar Reservas por Quarto\n");

        System.out.println("0 - Voltar\n");

        System.out.println("Escolha: ");
    }
    public static void menuUsuarios(){
        mostrarTitulos("MENU USUÁRIOS");

        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Autenticar Usuário");
        System.out.println("3 - Listar Usuários");
        System.out.println("4 - Remover Usuário\n");

        System.out.println("0 - Voltar\n");

        System.out.println("Escolha: ");
    }
    public static void menuCargoUsuario(){
        System.out.println("Escolha o cargo do Usuário:\n");
        System.out.println("1 - Atendente");
        System.out.println("2 - Gerente\n");

        System.out.println("Escolha: ");
    }

    public static void executarMenuClientes(Scanner scanner, ClienteService clienteService){
        int opcaoMenuCliente = -1;

        while (opcaoMenuCliente != 0) {
            menuClientes();

            opcaoMenuCliente = lerInteiro(scanner);

            switch (opcaoMenuCliente) {
                case 1:
                    try {

                        mostrarTitulos("CADASTRO DE CLIENTES");

                        System.out.println("Nome: ");
                        String nomeCliente = lerString(scanner);

                        System.out.println("CPF (somente números): ");
                        String cpfCliente = formatarCPF(lerString(scanner));

                        System.out.println("Telefone: (somente números)");
                        String telefoneCliente = formatarTelefone(lerString(scanner));

                        Cliente cliente = clienteService.cadastrarCliente(nomeCliente, cpfCliente, telefoneCliente);

                        System.out.println("Cliente cadastrado!");
                        System.out.println("ID do Cliente: " + cliente.getId());

                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e) {

                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);

                    }

                    break;

                case 2:
                    try{
                        System.out.println("ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);
                        System.out.println(clienteService.buscarClientePorId(idCliente));
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){

                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);

                    }
                    break;

                case 3:
                    ArrayList<Cliente> listaClientes = clienteService.listarClientes();

                    if(listaClientes.isEmpty()){
                        System.out.println("ERRO! Não existe clientes cadastrados.");
                        aguardarEnter(scanner);

                    }else {
                        for (Cliente cliente : listaClientes) {
                            System.out.println(cliente);
                        }
                        aguardarEnter(scanner);
                    }
                    break;

                case 4:
                    try{
                        mostrarTitulos("REMOVER CLIENTE");

                        System.out.println("ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);

                        if(confirmarAcao(scanner, "Deseja realmente remover esse cliente?")){
                            clienteService.removerCliente(idCliente);

                            System.out.println("Cliente removido!");

                        } else {
                            System.out.println("Operação cancelada.");
                        }

                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 0:

                    break;

                default:

                    System.out.println("Opção Inválida, tente novamente.");
                    aguardarEnter(scanner);

                    break;
            }
        }
    }
    public static void executarMenuQuartos(Scanner scanner, QuartoService quartoService){
        int opcaoMenuQuartos = -1;

        while (opcaoMenuQuartos != 0){
            menuQuartos();
            opcaoMenuQuartos = lerInteiro(scanner);

            switch (opcaoMenuQuartos){
                case 1:
                    try {
                        mostrarTitulos("CADASTRO DE QUARTOS");

                        System.out.println("Número do Quarto: ");
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

                        System.out.println("capacidade do Quarto: ");
                        int capacidadeQuarto = lerInteiro(scanner);

                        System.out.println("valor da diária: R$ ");
                        double valorDiariaQuarto = lerDouble(scanner);

                        Quarto quarto = new Quarto(numeroQuarto, tipoQuarto, capacidadeQuarto, valorDiariaQuarto);

                        quartoService.cadastrarQuarto(quarto);

                        System.out.println("Quarto cadastrado!");
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 2:
                    try {
                        System.out.println("Número do Quarto: ");
                        int numeroQuarto = lerInteiro(scanner);
                        System.out.println(quartoService.buscarQuartoPorNumero(numeroQuarto));
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 3:
                    ArrayList<Quarto> listaQuartos = quartoService.listarQuartos();

                    if(listaQuartos.isEmpty()){
                        System.out.println("ERRO! Não existe quartos cadastrados!");
                        aguardarEnter(scanner);

                    }else{
                        for(Quarto quarto : listaQuartos){
                            System.out.println(quarto);
                        }
                        aguardarEnter(scanner);
                    }
                    break;

                case 4:
                    ArrayList<Quarto> listaQuartosDisponiveis = quartoService.listarQuartosDisponiveis();

                    if(listaQuartosDisponiveis.isEmpty()){
                        System.out.println("ERRO! Não existe quartos disponíveis!");
                        aguardarEnter(scanner);

                    } else {
                        for(Quarto quarto : listaQuartosDisponiveis){
                            System.out.println(quarto);
                        }
                        aguardarEnter(scanner);
                    }
                    break;

                case 5:
                    try{
                        mostrarTitulos("REMOVER QUARTO");

                        System.out.println("Número do Quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        if(confirmarAcao(scanner, "Deseja realmente remover esse quarto?")){
                            quartoService.removerQuarto(numeroQuarto);

                            System.out.println("Quarto removido!");

                        } else {
                            System.out.println("Operação cancelada.");
                        }
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 0:

                    break;

                default:
                    System.out.println("Opção Inválida!");
                    aguardarEnter(scanner);

                    break;
            }
        }
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
                        mostrarTitulos("CRIAR RESERVA");

                        System.out.println("ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);

                        System.out.println("Número do Quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        System.out.println("data de Check-in (DD/MM/AAAA): ");
                        LocalDate dataCheckin = lerData(scanner);

                        System.out.println("data de Check-out (DD/MM/AAAA): ");
                        LocalDate dataCheckout = lerData(scanner);

                        Reserva reserva = reservaService.criarReserva(idCliente, numeroQuarto, dataCheckin, dataCheckout);

                        System.out.println(reserva);

                        System.out.println("\nReserva criada!");
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 2:
                    try {
                        mostrarTitulos("CHECK-IN");

                        System.out.println("ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        reservaService.realizarCheckIn(idReserva);

                        System.out.println("Check-in realizado!");
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 3:
                    try {
                        mostrarTitulos("CHECK-OUT");

                        System.out.println("ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        reservaService.realizarCheckOut(idReserva);

                        System.out.println("Check-Out realizado!");
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 4:
                    try {
                        mostrarTitulos("CANCELAR RESERVA");

                        System.out.println("ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        if(confirmarAcao(scanner, "Deseja realmente cancelar essa reserva?")){
                            reservaService.cancelarReserva(idReserva);

                            System.out.println("Reserva cancelada.");

                        } else {
                            System.out.println("Operação cancelada.");
                        }
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 5:
                    try {
                        mostrarTitulos("BUSCAR RESERVA POR ID");

                        System.out.println("ID da Reserva: ");
                        int idReserva = lerInteiro(scanner);

                        System.out.println(reservaService.buscarReservaPorId(idReserva));
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 6:
                    mostrarTitulos("LISTAR RESERVAS");

                    ArrayList<Reserva> listaReservas = reservaService.listarReservas();

                    if (listaReservas.isEmpty()) {
                        System.out.println("ERRO! Não existe reservas cadastradas!");
                        aguardarEnter(scanner);

                    } else {
                        for (Reserva reserva : listaReservas) {
                            System.out.println(reserva);
                        }
                        aguardarEnter(scanner);

                    }
                    break;

                case 7:
                    mostrarTitulos("LISTAR RESERVAS ATIVAS");

                    ArrayList<Reserva> listaReservasAtivas = reservaService.listarReservasAtivas();

                    if (listaReservasAtivas.isEmpty()) {
                        System.out.println("ERRO! Não existe reservas ativas!");
                        aguardarEnter(scanner);

                    } else {
                        for (Reserva reserva : listaReservasAtivas) {
                            System.out.println(reserva);
                        }
                        aguardarEnter(scanner);
                    }
                    break;

                case 8:
                    mostrarTitulos("LISTAR RESERVAS INATIVAS");

                    ArrayList<Reserva> listaReservasInativas = reservaService.listarReservasInativas();

                    if (listaReservasInativas.isEmpty()) {
                        System.out.println("ERRO! Não existe reservas inativas!");
                        aguardarEnter(scanner);

                    } else {
                        for (Reserva reserva : listaReservasInativas) {
                            System.out.println(reserva);
                        }
                        aguardarEnter(scanner);
                    }

                    break;

                case 9:
                    try {
                        mostrarTitulos("BUSCAR RESERVA POR CLIENTE");

                        System.out.println("ID do Cliente: ");
                        int idCliente = lerInteiro(scanner);

                        Cliente cliente = clienteService.buscarClientePorId(idCliente);

                        ArrayList<Reserva> reservasDoCliente = reservaService.buscarReservasPorCliente(cliente);

                        if (reservasDoCliente.isEmpty()) {
                            System.out.println("ERRO! Esse cliente não possui reservas cadastradas.");
                            aguardarEnter(scanner);

                        } else {
                            for (Reserva reserva : reservasDoCliente) {
                                System.out.println(reserva);
                            }
                            aguardarEnter(scanner);
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 10:
                    try {
                        mostrarTitulos("BUSCAR RESERVAR POR QUARTO");

                        System.out.println("Número do Quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        Quarto quarto = quartoService.buscarQuartoPorNumero(numeroQuarto);

                        ArrayList<Reserva> reservasDoQuarto = reservaService.buscarReservasPorQuarto(quarto);

                        if (reservasDoQuarto.isEmpty()) {
                            System.out.println("ERRO! Esse quarto não possui reservas.");
                            aguardarEnter(scanner);

                        } else {
                            for (Reserva reserva : reservasDoQuarto) {
                                System.out.println(reserva);
                            }
                            aguardarEnter(scanner);
                        }
                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 0:

                    break;

                default:
                    System.out.println("Opção Inválida");
                    aguardarEnter(scanner);
                    break;
            }
        }
    }
    public static void executarMenuUsuarios(Scanner scanner, UsuarioService usuarioService){

        int opcaoMenuUsuarios = -1;

        while(opcaoMenuUsuarios != 0){
            menuUsuarios();

            opcaoMenuUsuarios = lerInteiro(scanner);

            switch (opcaoMenuUsuarios){
                case 1:
                    try {
                        mostrarTitulos("CADASTRO DE USUÁRIO");

                        System.out.println("Nome: ");
                        String nomeUsuario = lerString(scanner);

                        System.out.println("CPF: ");
                        String cpfUsuario = lerString(scanner);

                        System.out.println("Telefone: ");
                        String telefoneUsuario = lerString(scanner);

                        System.out.println("Senha: ");
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

                        Usuario usuario = usuarioService.cadastrarUsuario(nomeUsuario, cpfUsuario, telefoneUsuario, senhaUsuario, cargo);

                        System.out.println("Usuário cadastrado!");
                        System.out.println("ID Usuário: " + usuario.getId());

                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 2:
                    try {
                        mostrarTitulos("AUTENTICAR USUÁRIO");

                        System.out.println("ID Usuário: ");
                        int idUsuario = lerInteiro(scanner);

                        System.out.println("Senha: ");
                        String senhaUsuario = lerString(scanner);

                        Usuario usuario = usuarioService.autenticarUsuario(idUsuario, senhaUsuario);

                        System.out.println("Usuário autenticado");
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 3:
                    ArrayList<Usuario> listaUsuarios = usuarioService.listarUsuarios();

                    if(listaUsuarios.isEmpty()){
                        System.out.println("ERRO! Não existe usuários cadastrados.");
                        aguardarEnter(scanner);

                    } else {
                        for (Usuario usuario : listaUsuarios){
                            System.out.println(usuario);
                        }
                        aguardarEnter(scanner);
                    }
                    break;

                case 4:
                    try {
                        mostrarTitulos("REMOVER USUÁRIO");

                        System.out.println("ID Usuário: ");
                        int idUsuario = lerInteiro(scanner);

                        if(confirmarAcao(scanner, "Deseja realmente remover esse usuário?")){
                            usuarioService.removerUsuario(idUsuario);

                            System.out.println("Usuário removido");

                        } else {
                            System.out.println("Operação Cancelada");
                        }
                        aguardarEnter(scanner);

                    } catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        aguardarEnter(scanner);
                    }
                    break;

                case 0:

                    break;

                default:
                    System.out.println("ERRO! Opção Inválida.");
                    aguardarEnter(scanner);
                    break;
            }

        }
    }

    static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ClienteRepository clienteRepository = ClienteRepository.getInstance();
        QuartoRepository quartoRepository = QuartoRepository.getInstance();
        UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();
        ReservaRepository reservaRepository = ReservaRepository.getInstance(clienteRepository, quartoRepository);

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
                    if (confirmarAcao(scanner, "Deseja encerrar o sistema?")){
                        System.out.println("Encerrando...");
                        opcaoMenu = 0;

                    } else {
                        opcaoMenu = -1;
                    }
                    break;
                }

                default: {
                    System.out.println("ERRO! Opção Inválida");
                    aguardarEnter(scanner);
                    break;
                }

            }
        }

    }
}
