package persistencia;

import enums.StatusReserva;
import model.Cliente;
import model.Quarto;
import model.Reserva;
import repository.ClienteRepository;
import repository.QuartoRepository;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaPersistencia {

    public static final String CAMINHO = "data/reservas.txt";

    public static void salvarReservas(ArrayList<Reserva> listaReservas){

        try (FileWriter writer = new FileWriter(CAMINHO);
             BufferedWriter buffer = new BufferedWriter(writer)) {

            for (Reserva reserva : listaReservas){
                buffer.write(reserva.getId() + ";" +
                        reserva.getCliente().getId() + ";" +
                        reserva.getQuarto().getNumero() + ";" +
                        reserva.getDataCheckin() + ";" +
                        reserva.getDataCheckout() + ";" +
                        (reserva.getDataCheckoutReal() == null? "NULL" : reserva.getDataCheckoutReal()) + ";" +
                        reserva.getValorTotal() + ";" +
                        reserva.getMulta() + ";" +
                        reserva.getStatus()
                );
                buffer.newLine();
            }

        } catch (Exception e){
            System.out.println("Erro na escrita de dados!");
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Reserva> carregarReservas(ClienteRepository clienteRepository, QuartoRepository quartoRepository){
        ArrayList<Reserva> listaReservas = new ArrayList<>();

        File arquivo = new File(CAMINHO);

        if(!arquivo.exists()){
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(CAMINHO);
             BufferedReader buffer = new BufferedReader(reader)) {

            String linha = buffer.readLine();

            while (linha != null){
                String [] dados = linha.split(";");

                int idCliente = Integer.parseInt(dados[1]);
                int numeroQuarto = Integer.parseInt(dados[2]);
                LocalDate dataCheckoutReal = dados[5].equals("NULL")? null : LocalDate.parse(dados[5]);

                Cliente cliente = clienteRepository.buscarClientePorId(idCliente);
                Quarto quarto = quartoRepository.buscarQuartoPorNumero(numeroQuarto);

                Reserva reserva = new Reserva(Integer.parseInt(dados[0]), cliente, quarto, LocalDate.parse(dados[3]),
                                                LocalDate.parse(dados[4]), dataCheckoutReal, Double.parseDouble(dados[6]),
                                                Double.parseDouble(dados[7]), StatusReserva.valueOf(dados[8])
                );

                listaReservas.add(reserva);
                linha = buffer.readLine();
            }

            return listaReservas;

        } catch (Exception e){
            System.out.println("Erro na leitura de dados!");
            System.out.println(e.getMessage());
        }

        return listaReservas;
    }
}
