package model;
import enums.StatusQuarto;
import enums.TipoQuarto;


public class Quarto {
    private int numero;
    private TipoQuarto tipo;
    private int capacidade;
    private double valorDiaria;
    private StatusQuarto status;

    public Quarto(int numero, TipoQuarto tipo, int capacidade, double valorDiaria){
        setNumero(numero);
        setTipo(tipo);
        setCapacidade(capacidade);
        setValorDiaria(valorDiaria);
        setStatus(StatusQuarto.DISPONIVEL);
    }

    public Quarto(int numero, TipoQuarto tipo, int capacidade, double valorDiaria, StatusQuarto status){
        setNumero(numero);
        setTipo(tipo);
        setCapacidade(capacidade);
        setValorDiaria(valorDiaria);
        setStatus(status);
    }

    public int getNumero(){
        return numero;
    }

    public TipoQuarto getTipo(){
        return tipo;
    }

    public int getCapacidade(){
        return capacidade;
    }

    public double getValorDiaria(){
        return valorDiaria;
    }

    public StatusQuarto getStatus(){
        return status;
    }

    public void setNumero(int numero){
        if(numero <= 0){
            throw new IllegalArgumentException("ERRO! Número do quarto inválido.");
        }

        this.numero = numero;
    }

    public void setTipo(TipoQuarto tipo){
        if(tipo == null){
            throw new IllegalArgumentException("ERRO! Tipo inválido.");
        }

        this.tipo = tipo;
    }

    public void setCapacidade(int capacidade){
        if(capacidade <= 0){
            throw new IllegalArgumentException("ERRO! Capaciade Inválida");
        }

        this.capacidade = capacidade;
    }

    public void setValorDiaria(double valorDiaria){
        if(valorDiaria <= 0){
            throw new IllegalArgumentException("ERRO! Valor Inválido");
        }

        this.valorDiaria = valorDiaria;
    }

    public void setStatus(StatusQuarto status){
        if(status == null){
            throw new IllegalArgumentException("ERRO! Status inválido.");
        }

        this.status = status;
    }

    public boolean isDisponivel(){
        return this.status != null && status == StatusQuarto.DISPONIVEL;
    }

    public void alterarStatus(StatusQuarto status){
        if(this.status != null && this.status == status){
            throw new IllegalArgumentException("ERRO! Esse já é o status atual.");
        }

        setStatus(status);
    }

    @Override
    public String toString(){
        return "===== INFORMAÇÕES DO QUARTO =====\n" +
                "Número: " + numero + "\n" +
                "Tipo: " + tipo + "\n" +
                "Capacidade: " + capacidade + "\n" +
                "Valor da Diária: R$ " + valorDiaria + "\n" +
                "Status: " + status + "\n";
    }
}
