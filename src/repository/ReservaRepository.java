package repository;

import model.Cliente;
import model.Quarto;
import model.Reserva;
import persistencia.ReservaPersistencia;
import java.util.ArrayList;
import java.util.HashMap;
import static enums.StatusReserva.*;


public class ReservaRepository {
    private static ReservaRepository instance;
    private static final int PRIMEIRO_ID = 1000000;

    private HashMap<Integer, Reserva> listaReservas;

    private ReservaRepository(ClienteRepository clienteRepository, QuartoRepository quartoRepository){
        listaReservas = ReservaPersistencia.carregarReservas(clienteRepository, quartoRepository);
    }

    public static ReservaRepository getInstance(ClienteRepository clienteRepository, QuartoRepository quartoRepository){
        if(instance == null){
            instance = new ReservaRepository(clienteRepository, quartoRepository);
        }

        return instance;
    }

    public int gerarProximoId(){
        int maiorId = PRIMEIRO_ID - 1;

        for(Integer idAtual : listaReservas.keySet()){
            if(idAtual > maiorId){
                maiorId = idAtual;
            }
        }

        return maiorId + 1;
    }

    public Reserva buscarReservaPorId(int id){
        if(id <= 0){
            return null;
        }

        return listaReservas.get(id);
    }

    public void adicionarReserva(Reserva reserva){
        if(reserva == null){
            throw new IllegalArgumentException("ERRO! Reserva inválida.");
        }

        if(listaReservas.containsKey(reserva.getId())){
            throw new IllegalArgumentException("ERRO! Já existe uma reserva com esse ID.");
        }

        listaReservas.put(reserva.getId(), reserva);
    }

    public void removerReserva(int id){

        if(listaReservas.remove(id) == null){
            throw new IllegalArgumentException("ERRO! Reserva inválida.");
        }
    }

    public ArrayList<Reserva> listarReservas(){
        return new ArrayList<>(listaReservas.values());
    }

    public ArrayList<Reserva> listarReservasAtivas(){
        ArrayList<Reserva> ativas = new ArrayList<>();

        for(Reserva reserva : listaReservas.values()){
            if(reserva.getStatus() == AGENDADA || reserva.getStatus() == ANDAMENTO){
                ativas.add(reserva);
            }
        }

        return ativas;
    }

    public ArrayList<Reserva> listarReservasInativas(){
        ArrayList<Reserva> inativas = new ArrayList<>();

        for(Reserva reserva : listaReservas.values()){
            if(reserva.getStatus() == CONCLUIDA || reserva.getStatus() == CANCELADA){
                inativas.add(reserva);
            }
        }

        return inativas;
    }

    public ArrayList<Reserva> buscarReservasPorCliente(Cliente cliente){
        if(cliente == null){
            return new ArrayList<>();
        }

        int idCliente = cliente.getId();
        ArrayList<Reserva> reservasDoCliente = new ArrayList<>();

        for(Reserva reserva : listaReservas.values()){
            if(reserva.getCliente().getId() == idCliente){
                reservasDoCliente.add(reserva);
            }
        }

        return reservasDoCliente;
    }

    public ArrayList<Reserva> buscarReservasPorQuarto(Quarto quarto){
        if(quarto == null){
            return new ArrayList<>();
        }

        int numeroQuarto = quarto.getNumero();
        ArrayList<Reserva> reservasDoQuarto = new ArrayList<>();

        for(Reserva reserva : listaReservas.values()){
            if(reserva.getQuarto().getNumero() == numeroQuarto){
                reservasDoQuarto.add(reserva);
            }
        }

        return reservasDoQuarto;
    }

    public void salvar(){
        ReservaPersistencia.salvarReservas(listaReservas);
    }
}
