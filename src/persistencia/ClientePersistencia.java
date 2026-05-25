package persistencia;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

import model.Cliente;

public class ClientePersistencia {

    private static final String CAMINHO = "data/clientes.txt";

    public static void salvarClientes(ArrayList<Cliente> listaClientes){
        try(FileWriter writer = new FileWriter(CAMINHO);
            BufferedWriter buffer = new BufferedWriter(writer)) {

            for(Cliente cliente : listaClientes){
                buffer.write(cliente.getId() + ";" +
                                 cliente.getNome() + ";" +
                                 cliente.getCpf() + ";" +
                                 cliente.getTelefone()
                );
                buffer.newLine();
            }

        } catch (Exception e){
            System.out.println("Erro ao salvar dados!");
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Cliente> carregarClientes(){

        File arquivo = new File(CAMINHO);

        if(!arquivo.exists()){
            return new ArrayList<>();
        }

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try (FileReader reader = new FileReader(CAMINHO);
            BufferedReader buffer = new BufferedReader(reader)) {

            String linha = buffer.readLine();

            while (linha != null){
                String [] dados = linha.split(";");
                Cliente cliente = new Cliente(dados[1], dados[2], dados[3], Integer.parseInt(dados[0]));

                listaClientes.add(cliente);
                linha = buffer.readLine();
            }

            return listaClientes;

        } catch (Exception e){
            System.out.println("Erro na leitura do arquivo!");
            System.out.println(e.getMessage());
        }

        return listaClientes;
    }

}
