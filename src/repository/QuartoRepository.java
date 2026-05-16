package repository;
import model.Quarto;
import java.util.ArrayList;

public class QuartoRepository {
    private ArrayList<Quarto> listaQuartos;

    public QuartoRepository(){
        listaQuartos = new ArrayList<>();
    }

    public Quarto buscarQuartoPorNumero(int numero){
        if(numero <= 0){
            return null;
        }

        for(Quarto quarto : listaQuartos){
            if(quarto.getNumero() == numero){
                return quarto;
            }
        }

        return null;
    }

    public void adicionarQuarto(Quarto quarto){
        if(quarto == null){
            throw new IllegalArgumentException("ERRO! Quarto inválido");
        }

        if(buscarQuartoPorNumero(quarto.getNumero()) != null){
            throw new IllegalArgumentException("ERRO! Já existe um quarto com esse número.");
        }

        listaQuartos.add(quarto);
    }

    public void removerQuarto(int numero){
        Quarto quarto = buscarQuartoPorNumero(numero);

        if(quarto == null){
            throw new IllegalArgumentException("ERRO, Quarto inválido.");
        }

        listaQuartos.remove(quarto);
    }

    public ArrayList<Quarto> listarQuartos(){
        return new ArrayList<>(listaQuartos);
    }

    public ArrayList<Quarto> listarQuartosDisponiveis(){
        ArrayList<Quarto> quartosDisponiveis = new ArrayList<>();

        for(Quarto quarto : listaQuartos){
            if(quarto.isDisponivel()){
                quartosDisponiveis.add(quarto);
            }
        }

        return quartosDisponiveis;
    }

}
