package persistencia;

import enums.StatusQuarto;
import enums.TipoQuarto;
import model.Quarto;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.HashMap;

public class QuartoPersistencia {

    public static final String CAMINHO = "data/quartos.txt";

    public static void salvarQuartos(HashMap<Integer, Quarto> listaQuartos){

        try (FileWriter writer = new FileWriter(CAMINHO);
             BufferedWriter buffer = new BufferedWriter(writer)){

            for(Quarto quarto : listaQuartos.values()){
                buffer.write(quarto.getNumero() + ";" +
                            quarto.getTipo() + ";" +
                            quarto.getCapacidade() + ";" +
                            quarto.getValorDiaria() + ";" +
                            quarto.getStatus()
                );
                buffer.newLine();
            }

        } catch (Exception e){
            System.out.println("Erro na escrita de dados (Quarto).");
            System.out.println(e.getMessage());
        }
    }

    public static HashMap<Integer, Quarto> carregarQuartos(){

        File arquivo = new File(CAMINHO);

        if(!arquivo.exists()){
            return new HashMap<>();
        }

        HashMap<Integer, Quarto> listaQuartos = new HashMap<>();

        try (FileReader writer = new FileReader(CAMINHO);
             BufferedReader buffer = new BufferedReader(writer)) {

            String linha = buffer.readLine();

            while (linha != null){
                String [] dados = linha.split(";");

                Quarto quarto = new Quarto(Integer.parseInt(dados[0]), TipoQuarto.valueOf(dados[1]),
                                            Integer.parseInt(dados[2]), Double.parseDouble(dados[3]),
                                            StatusQuarto.valueOf(dados[4])
                );

                listaQuartos.put(quarto.getNumero(), quarto);
                linha = buffer.readLine();
            }

            return listaQuartos;

        } catch (Exception e){
            System.out.println("Erro na leitura de dados (Quarto).");
            System.out.println(e.getMessage());
        }

        return listaQuartos;
    }
}
