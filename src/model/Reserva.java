package model;
import enums.StatusReserva;
import static enums.StatusReserva.*;
import static enums.StatusQuarto.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Reserva {
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("EEE dd/MM/yyyy",
            Locale.of("pt", "BR"));

    private int id;
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataCheckin;
    private LocalDate dataCheckout;
    private LocalDate dataCheckoutReal;
    private double valorTotal;
    private StatusReserva status;
    private double multa;

    public Reserva(int id, Cliente cliente, Quarto quarto, LocalDate dataCheckin, LocalDate dataCheckout){
        setId(id);
        setCliente(cliente);
        setQuarto(quarto);
        setDataCheckin(dataCheckin);
        setDataCheckout(dataCheckout);
        calcularValorTotal();
        setStatus(AGENDADA);
    }

    public Reserva(int id, Cliente cliente, Quarto quarto, LocalDate dataCheckin, LocalDate dataCheckout,
                   LocalDate dataCheckoutReal, double valorTotal, double multa, StatusReserva status){
        this.id =id;
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.dataCheckoutReal = dataCheckoutReal;
        this.valorTotal = valorTotal;
        this.multa = multa;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public Quarto getQuarto(){
        return quarto;
    }

    public LocalDate getDataCheckin(){
        return dataCheckin;
    }

    public LocalDate getDataCheckout(){
        return dataCheckout;
    }

    public LocalDate getDataCheckoutReal(){
        return dataCheckoutReal;
    }

    public double getValorTotal(){
        return valorTotal;
    }

    public double getValorFinal(){
        return valorTotal + multa;
    }

    public double getMulta(){
        return multa;
    }

    public StatusReserva getStatus(){
        return status;
    }

    public void setId(int id){
        if(id <= 0){
            throw new IllegalArgumentException("ERRO! ID inválido!");
        }

        this.id = id;
    }

    public void setCliente(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException("ERRO! Cliente inválido.");
        }

        this.cliente = cliente;
    }

    public void setQuarto(Quarto quarto){
        if(quarto == null){
            throw new IllegalArgumentException("ERRO! Quarto inválido.");
        }

        if(quarto.getStatus() != DISPONIVEL){
            throw new IllegalArgumentException("ERRO! Esse quarto não está disponível.");
        }

        this.quarto = quarto;
    }

    public void setDataCheckin(LocalDate dataCheckin){
        if(dataCheckin == null){
            throw new IllegalArgumentException("ERRO! Data de check-in inválida.");
        }

        if(this.dataCheckout != null && dataCheckin.isAfter(this.dataCheckout)){
            throw new IllegalArgumentException("ERRO! Data de check-in maior que data de check-out.");
        }

        this.dataCheckin = dataCheckin;
    }

    public void setDataCheckout(LocalDate dataCheckout){
        if(dataCheckout == null){
            throw new IllegalArgumentException("ERRO! Data de check-out inválida.");
        }

        if(this.dataCheckin != null && !dataCheckout.isAfter(this.dataCheckin)){
            throw new IllegalArgumentException("ERRO! Data de check-out menor ou igual a data de check-in.");
        }

        this.dataCheckout = dataCheckout;
    }

    public void setDataCheckoutReal(LocalDate dataCheckoutReal){
        if(dataCheckoutReal == null){
            throw new IllegalArgumentException("ERRO! Data de check-out real inválida.");
        }

        if(dataCheckoutReal.isBefore(this.dataCheckin)){
            throw new IllegalArgumentException("ERRO! Data de check-out menor que a data de check-in.");
        }

        this.dataCheckoutReal = dataCheckoutReal;
    }

    public void setValorTotal(double valorTotal){
        if(valorTotal <= 0){
            throw new IllegalArgumentException("ERRO! Valor total inválido.");
        }

        this.valorTotal = valorTotal;
    }

    private void setMulta(double multa){
        this.multa = multa;
    }

    private void setStatus(StatusReserva status){
        if(status == null){
            throw new IllegalArgumentException("ERRO! Status inválido.");
        }

        this.status = status;
    }

    public void calcularValorTotal(){
        LocalDate dataFinal =(dataCheckoutReal == null)? dataCheckout : dataCheckoutReal;

        long diasDeReserva = ChronoUnit.DAYS.between(dataCheckin, dataFinal);

        if(diasDeReserva <= 0){
            diasDeReserva = 1;
        }

        setValorTotal(diasDeReserva * quarto.getValorDiaria());
    }

    public void calcularMulta(){
        setMulta(valorTotal*0.10);
    }

    public void realizarCheckIn(){
        if(status != AGENDADA){
            throw new IllegalArgumentException("ERRO! Não é possível fazer check-in em uma reserva que não esteja agendada.");
        }

        if(quarto.getStatus() != RESERVADO){
            throw new IllegalArgumentException("ERRO! Não é possivel realizar check-in, o quarto está ocupado.");
        }

        if(LocalDate.now().isBefore(dataCheckin)){
            throw new IllegalArgumentException("ERRO! Ainda não é possível realizar check-in.");
        }

        setStatus(ANDAMENTO);
        quarto.setStatus(OCUPADO);
    }

    public void realizarCheckout(){
        if(status != ANDAMENTO){
            throw new IllegalArgumentException("ERRO! Não é possível fazer check-out em uma reserva que não esteja em andamento.");
        }

        if(quarto.getStatus() != OCUPADO){
            throw new IllegalArgumentException("ERRO! Não é possível realizar check-out, o quarto não está ocupado.");
        }

        LocalDate dataSaida = LocalDate.now();

        setDataCheckoutReal(dataSaida);
        calcularValorTotal();

        setStatus(CONCLUIDA);
        quarto.setStatus(DISPONIVEL);

        System.out.println(quarto.getStatus());
    }

    public void cancelarReserva(){
        if(status == CONCLUIDA || status == CANCELADA){
            throw new IllegalArgumentException("ERRO! Não é possível cancelar uma reserva concluída ou já cancelada.");
        }

        if(status == ANDAMENTO){
            setDataCheckoutReal(LocalDate.now());
            calcularValorTotal();
        }

        calcularMulta();

        setStatus(CANCELADA);
        quarto.setStatus(DISPONIVEL);
    }

    @Override
    public String toString(){

        String checkoutReal = "";

        if(status == CONCLUIDA || status == CANCELADA){
            checkoutReal = "Check-out Real: " + (dataCheckoutReal == null? "Não Realizado" : dataCheckoutReal.format(FORMATADOR)) + "\n";
        }

        return "===== INFORMAÇÕES DA RESERVA =====\n" +
                "ID: " + id + "\n" +
                "Cliente: " + cliente.getNome() + "\n" +
                "Quarto: " + quarto.getNumero() + "\n" +
                "Check-in: " + dataCheckin.format(FORMATADOR) + "\n" +
                "Check-out Esperado: " + dataCheckout.format(FORMATADOR) + "\n" +
                checkoutReal +
                "Valor Total: R$ " + valorTotal + "\n" +
                "Multa Aplicada: R$ " + multa + "\n" +
                "Status: " + status + "\n";

    }
}
