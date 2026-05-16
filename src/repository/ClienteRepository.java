package repository;
import model.Cliente;
import java.util.ArrayList;

public class ClienteRepository {
    private ArrayList<Cliente> listaClientes;

    public ClienteRepository(){
        listaClientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente){
        if(cliente == null){
            throw new IllegalArgumentException("ERRO! Cliente inválido.");
        }
        if(buscarClientePorId(cliente.getId()) != null){
            throw new IllegalArgumentException("ERRO! Já existe um cliente com esse ID.");
        }

        listaClientes.add(cliente);
    }

    public ArrayList<Cliente> listarClientes(){
        return new ArrayList<>(listaClientes);
    }

    public Cliente buscarClientePorId(int id){
        if(id <= 0){
            return null;
        }

        for(Cliente cliente : listaClientes){
            if(cliente.getId() == id){
                return cliente;
            }
        }
        return null;
    }

    public void removerCliente(int id){
        Cliente cliente = buscarClientePorId(id);

        if(cliente == null){
            throw new IllegalArgumentException("ERRO, Cliente inválido.");
        }

        listaClientes.remove(cliente);
    }
}
