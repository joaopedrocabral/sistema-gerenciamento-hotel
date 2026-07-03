package repository;
import model.Quarto;
import persistencia.QuartoPersistencia;

import java.util.ArrayList;
import java.util.HashMap;

public class QuartoRepository {
    private static QuartoRepository instance;

    private HashMap<Integer, Quarto> listaQuartos;

    private QuartoRepository(){
        listaQuartos = QuartoPersistencia.carregarQuartos();
    }

    public static QuartoRepository getInstance(){
        if(instance == null){
            instance = new QuartoRepository();
        }

        return instance;
    }

    public Quarto buscarQuartoPorNumero(int numero){
        if(numero <= 0){
            return null;
        }

        return listaQuartos.get(numero);
    }

    public void adicionarQuarto(Quarto quarto){
        if(quarto == null){
            throw new IllegalArgumentException("ERRO! Quarto inválido");
        }

        if(listaQuartos.containsKey(quarto.getNumero())){
            throw new IllegalArgumentException("ERRO! Já existe um quarto com esse número.");
        }

        listaQuartos.put(quarto.getNumero(), quarto);
    }

    public void removerQuarto(int numero){
        if(listaQuartos.remove(numero) == null){
            throw new IllegalArgumentException("ERRO, Quarto inválido.");
        }
    }

    public ArrayList<Quarto> listarQuartos(){
        return new ArrayList<>(listaQuartos.values());
    }

    public ArrayList<Quarto> listarQuartosDisponiveis(){
        ArrayList<Quarto> disponiveis = new ArrayList<>();

        for(Quarto quarto : listaQuartos.values()){
            if(quarto.isDisponivel()){
                disponiveis.add(quarto);
            }
        }

        return disponiveis;
    }

    public void salvar(){
        QuartoPersistencia.salvarQuartos(listaQuartos);
    }
}

