package service;
import enums.StatusQuarto;
import model.*;
import persistencia.QuartoPersistencia;
import repository.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaService {
    private ReservaRepository reservaRepository;
    private ClienteRepository clienteRepository;
    private QuartoRepository quartoRepository;

    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository, QuartoRepository quartoRepository){
        this.reservaRepository =  reservaRepository;
        this.clienteRepository = clienteRepository;
        this.quartoRepository = quartoRepository;
    }

    public Reserva criarReserva(int idCliente, int numeroQuarto, LocalDate checkin, LocalDate checkout){
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);

        if(cliente == null){
            throw new IllegalArgumentException("ERRO! Não existe cliente com esse ID.");
        }

        Quarto quarto = quartoRepository.buscarQuartoPorNumero(numeroQuarto);

        if(quarto == null){
            throw new IllegalArgumentException("ERRO! Não existe quarto com esse número.");
        }

        if(!quarto.isDisponivel()){
            throw new IllegalArgumentException("ERRO! Esse quarto não está disponível.");
        }

        Reserva reserva = new Reserva(reservaRepository.gerarProximoId(), cliente, quarto, checkin, checkout);

        reservaRepository.adicionarReserva(reserva);
        quarto.setStatus(StatusQuarto.RESERVADO);

        reservaRepository.salvar();
        quartoRepository.salvar();

        return reserva;
    }

    public void cancelarReserva(int idReserva){
        Reserva reserva = buscarReservaPorId(idReserva);
        reserva.cancelarReserva();

        reservaRepository.salvar();
        quartoRepository.salvar();
    }

    public void realizarCheckIn(int idReserva){
        Reserva reserva = buscarReservaPorId(idReserva);
        reserva.realizarCheckIn();

        reservaRepository.salvar();
        quartoRepository.salvar();
    }

    public void realizarCheckOut(int idReserva){
        Reserva reserva = buscarReservaPorId(idReserva);
        reserva.realizarCheckout();

        reservaRepository.salvar();
        quartoRepository.salvar();
    }

    public Reserva buscarReservaPorId(int idReserva){
        Reserva reserva = reservaRepository.buscarReservaPorId(idReserva);

        if(reserva == null){
            throw new IllegalArgumentException("ERRO! Não existe reserva com esse ID");
        }

        return reserva;
    }

    public ArrayList<Reserva> buscarReservasPorCliente(Cliente cliente){
        return reservaRepository.buscarReservasPorCliente(cliente);
    }

    public ArrayList<Reserva> buscarReservasPorQuarto(Quarto quarto){
        return reservaRepository.buscarReservasPorQuarto(quarto);
    }

    public ArrayList<Reserva> listarReservas(){
        return reservaRepository.listarReservas();
    }

    public ArrayList<Reserva> listarReservasAtivas(){
        return reservaRepository.listarReservasAtivas();
    }

    public ArrayList<Reserva> listarReservasInativas(){
        return reservaRepository.listarReservasInativas();
    }

}
