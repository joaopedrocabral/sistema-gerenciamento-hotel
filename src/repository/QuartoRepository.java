package repository;
import model.Quarto;
import persistencia.QuartoPersistencia;

import java.util.ArrayList;

public class QuartoRepository {
    private ArrayList<Quarto> listaQuartos;

    public QuartoRepository(){
        listaQuartos = QuartoPersistencia.carregarQuartos();
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

        QuartoPersistencia.salvarQuartos(listaQuartos);
    }

    public void removerQuarto(int numero){
        Quarto quarto = buscarQuartoPorNumero(numero);

        if(quarto == null){
            throw new IllegalArgumentException("ERRO, Quarto inválido.");
        }

        listaQuartos.remove(quarto);

        QuartoPersistencia.salvarQuartos(listaQuartos);
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
