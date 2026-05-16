package service;
import model.Cliente;
import repository.ClienteRepository;
import java.util.ArrayList;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarClientePorId(int idCliente){
        Cliente cliente = clienteRepository.buscarClientePorId(idCliente);

        if(cliente == null){
            throw new IllegalArgumentException("ERRO! Não existe cliente com esse ID.");
        }

        return cliente;
    }

    public void cadastrarCliente(Cliente cliente){
        clienteRepository.adicionarCliente(cliente);
    }

    public void removerCliente(int idCliente){
        clienteRepository.removerCliente(idCliente);
    }

    public ArrayList<Cliente> listarClientes(){
        return clienteRepository.listarClientes();
    }
}
