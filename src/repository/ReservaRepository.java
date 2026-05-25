package repository;

import model.Cliente;
import model.Quarto;
import model.Reserva;
import persistencia.ReservaPersistencia;
import java.util.ArrayList;
import static enums.StatusReserva.*;


public class ReservaRepository {
    private ArrayList<Reserva> listaReservas;


    public ReservaRepository(ClienteRepository clienteRepository, QuartoRepository quartoRepository){
        listaReservas = ReservaPersistencia.carregarReservas(clienteRepository, quartoRepository);
    }

    public Reserva buscarReservaPorId(int id){
        if(id <= 0){
            return null;
        }

        for(Reserva reserva : listaReservas){
            if(reserva.getId() == id){
                return reserva;
            }
        }

        return null;
    }

    public void adicionarReserva(Reserva reserva){
        if(reserva == null){
            throw new IllegalArgumentException("ERRO! Reserva inválida.");
        }

        if(buscarReservaPorId(reserva.getId()) != null){
            throw new IllegalArgumentException("ERRO! Já existe uma reserva com esse ID.");
        }

        listaReservas.add(reserva);
        ReservaPersistencia.salvarReservas(listaReservas);
    }

    public void removerReserva(int id){
        Reserva reserva = buscarReservaPorId(id);

        if(reserva == null){
            throw new IllegalArgumentException("ERRO! Reserva inválida.");
        }

        listaReservas.remove(reserva);
        ReservaPersistencia.salvarReservas(listaReservas);
    }

    public ArrayList<Reserva> listarReservas(){
        return new ArrayList<>(listaReservas);
    }

    public ArrayList<Reserva> listarReservasAtivas(){
        ArrayList<Reserva> reservasAtivas = new ArrayList<>();

        for(Reserva reserva : listaReservas){
            if(reserva.getStatus() == AGENDADA || reserva.getStatus() == ANDAMENTO){
                reservasAtivas.add(reserva);
            }
        }

        return reservasAtivas;
    }

    public ArrayList<Reserva> listarReservasInativas(){
        ArrayList<Reserva> reservasInativas = new ArrayList<>();

        for(Reserva reserva : listaReservas){
            if(reserva.getStatus() == CONCLUIDA || reserva.getStatus() == CANCELADA){
                reservasInativas.add(reserva);
            }
        }

        return reservasInativas;
    }

    public ArrayList<Reserva> buscarReservasPorCliente(Cliente cliente){
        if(cliente == null){
            return new ArrayList<>();
        }

        ArrayList<Reserva> reservasDoCliente = new ArrayList<>();

        for(Reserva reserva : listaReservas){
            if(reserva.getCliente().getId() == cliente.getId()){
                reservasDoCliente.add(reserva);
            }
        }

        return reservasDoCliente;
    }

    public ArrayList<Reserva> buscarReservasPorQuarto(Quarto quarto){
        if(quarto == null){
            return new ArrayList<>();
        }

        ArrayList<Reserva> reservasDoQuarto = new ArrayList<>();

        for(Reserva reserva : listaReservas){
            if(reserva.getQuarto().getNumero() == quarto.getNumero()){
                reservasDoQuarto.add(reserva);
            }
        }

        return reservasDoQuarto;
    }

}
