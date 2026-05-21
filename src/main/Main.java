package main;
import enums.TipoQuarto;
import model.*;
import repository.*;
import service.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void menuPrincipal(){
        System.out.println("===== SISTEMA HOTEL =====\n");
        System.out.println("1 - Clientes");
        System.out.println("2 - Quartos");
        System.out.println("3 - Reservas");
        System.out.println("4 - Usuários");
        System.out.println("0 - Encerrar sistema\n");

        System.out.println("Escolha uma opção: ");
    }

    public static void menuClientes(){
        System.out.println("===== MENU CLIENTES =====\n");
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

                        System.out.println("===== CADASTRO DE CLIENTES =====\n");

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
                        System.out.println("===== REMOVER CLIENTE =====\n");

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
        System.out.println("===== MENU QUARTOS =====\n");
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
                        System.out.println("===== CADASTRO DE QUARTOS =====\n");

                        System.out.println("Digite o Número do quarto: ");
                        int numeroQuarto = lerInteiro(scanner);

                        mostrarTiposQuartos();
                        int opcaoTipo = lerInteiro(scanner);

                        TipoQuarto tipoQuarto = null;

                        switch (opcaoTipo) {
                            case 1:
                                tipoQuarto = TipoQuarto.SIMPLES;
                                break;
                            case 2: {
                                tipoQuarto = TipoQuarto.PREMIUM;
                                break;
                            }
                            case 3: {
                                tipoQuarto = TipoQuarto.LUXO;
                            }
                            default:
                                System.out.println("Opção Inválida.");
                                break;
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
                        System.out.println("===== REMOVER QUARTO =====\n");

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

    public static void mostrarTiposQuartos(){
        System.out.println("Escolha o tipo do quarto:\n");
        System.out.println("1 - Simples");
        System.out.println("2 - Premium");
        System.out.println("3 - Luxo\n");
        System.out.println("Escolha uma opção: ");
    }

    public static void menuReservas(){
        System.out.println("===== MENU RESERVAS =====\n");
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

    public static void executarMenuReservas(Scanner scanner, ReservaService reservaService){
        int opcaoMenuReservas = -1;

        while (opcaoMenuReservas != 0){
            menuReservas();
            opcaoMenuReservas = lerInteiro(scanner);

            switch (opcaoMenuReservas){
                case 1:
                    // Criar
                    break;

                case 2:
                    //Check-in
                    break;

                case 3:
                    //Check-out
                    break;

                case 4:
                    //Cancelar
                    break;

                case 5:
                    //Buscar
                    break;

                case 6:
                    //Listar
                    break;

                case 7:
                    //Listar Ativas
                    break;

                case 8:
                    //Listar Inativas
                    break;

                case 9:
                    //Buscar por Cliente
                    break;

                case 10:
                    //Buscar por quarto
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
        System.out.println("===== MENU USUÁRIOS =====\n");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Autenticar Usuário");
        System.out.println("3 - Listar Usuários");
        System.out.println("4 - Remover Usuário");
        System.out.println("0 - Voltar");
    }



    public static int lerInteiro(Scanner scanner){
        int valor = scanner.nextInt();
        scanner.nextLine();

        return valor;
    }

    public static double lerDouble(Scanner scanner){
        double valor = scanner.nextDouble();
        scanner.nextLine();

        return valor;
    }

    public static String lerString(Scanner scanner){
        return scanner.nextLine();
    }

    public static void main(String[] args){
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
                    menuReservas();
                    break;
                }

                case 4: {
                    menuUsuarios();

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
