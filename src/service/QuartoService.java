package service;
import model.Quarto;
import repository.QuartoRepository;
import java.util.ArrayList;

public class QuartoService {
    private QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository){
        this.quartoRepository = quartoRepository;
    }

    public Quarto buscarQuartoPorNumero(int numeroQuarto){
        Quarto quarto = quartoRepository.buscarQuartoPorNumero(numeroQuarto);

        if(quarto == null){
            throw new IllegalArgumentException("ERRO! Não existe quarto com esse número.");
        }

        return quarto;
    }

    public void cadastrarQuarto(Quarto quarto){
        quartoRepository.adicionarQuarto(quarto);

        quartoRepository.salvar();
    }

    public void removerQuarto(int numeroQuarto){
        Quarto quarto = buscarQuartoPorNumero(numeroQuarto);

        if(!quarto.isDisponivel()){
            throw new IllegalArgumentException("ERRO! O quarto não pode ser removido pois está reservado ou ocupado!");
        }

        quartoRepository.removerQuarto(numeroQuarto);

        quartoRepository.salvar();
    }

    public ArrayList<Quarto> listarQuartos(){
        return quartoRepository.listarQuartos();
    }

    public ArrayList<Quarto> listarQuartosDisponiveis(){
        return quartoRepository.listarQuartosDisponiveis();
    }

}
