package repository;
import model.Cliente;
import persistencia.ClientePersistencia;
import java.util.ArrayList;
import java.util.HashMap;

public class ClienteRepository {
    private static ClienteRepository instance;

    private HashMap<Integer, Cliente> listaClientes;

    private ClienteRepository(){
        listaClientes = ClientePersistencia.carregarClientes();
    }

    public static ClienteRepository getInstance(){
        if(instance == null){
            instance = new ClienteRepository();
        }

        return instance;
    }

    public void adicionarCliente(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException("ERRO! Cliente inválido.");
        }
        if(listaClientes.containsKey(cliente.getId())){
            throw new IllegalArgumentException("ERRO! Já existe um cliente com esse ID.");
        }

        listaClientes.put(cliente.getId(), cliente);
    }

    public ArrayList<Cliente> listarClientes(){
        return new ArrayList<>(listaClientes.values());
    }

    public Cliente buscarClientePorId(int id){
        if(id <= 0){
            return null;
        }

        return listaClientes.get(id);
    }

    public void removerCliente(int id){
        if(listaClientes.remove(id) == null){
            throw new IllegalArgumentException("ERRO! Não existe cliente com esse ID.");
        }
    }

    public void salvar(){
        ClientePersistencia.salvarClientes(listaClientes);
    }
}
