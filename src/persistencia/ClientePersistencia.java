package persistencia;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.HashMap;

import model.Cliente;

public class ClientePersistencia {

    private static final String CAMINHO = "data/clientes.txt";

    public static void salvarClientes(HashMap<Integer, Cliente> listaClientes){
        try(FileWriter writer = new FileWriter(CAMINHO);
            BufferedWriter buffer = new BufferedWriter(writer)) {

            for(Cliente cliente : listaClientes.values()){
                buffer.write(cliente.getId() + ";" +
                                 cliente.getNome() + ";" +
                                 cliente.getCpf() + ";" +
                                 cliente.getTelefone()
                );
                buffer.newLine();
            }

        } catch (Exception e){
            System.out.println("Erro na escrita de dados (Cliente).");
            System.out.println(e.getMessage());
        }
    }

    public static HashMap<Integer, Cliente> carregarClientes(){

        File arquivo = new File(CAMINHO);

        if(!arquivo.exists()){
            return new HashMap<>();
        }

        HashMap<Integer, Cliente> listaClientes = new HashMap<>();

        try (FileReader reader = new FileReader(CAMINHO);
            BufferedReader buffer = new BufferedReader(reader)) {

            String linha = buffer.readLine();

            while (linha != null){
                String [] dados = linha.split(";");
                Cliente cliente = new Cliente(dados[1], dados[2], dados[3], Integer.parseInt(dados[0]));

                listaClientes.put(cliente.getId(), cliente);
                linha = buffer.readLine();
            }

            return listaClientes;

        } catch (Exception e){
            System.out.println("Erro na leitura de dados (Cliente).");
            System.out.println(e.getMessage());
        }

        return listaClientes;
    }

}
