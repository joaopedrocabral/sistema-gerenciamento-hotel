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

    public Cliente cadastrarCliente(String nome, String cpf, String telefone){
        Cliente cliente = new Cliente(nome, cpf, telefone, clienteRepository.gerarProximoId());

        clienteRepository.adicionarCliente(cliente);
        clienteRepository.salvar();

        return cliente;
    }

    public void removerCliente(int idCliente){
        clienteRepository.removerCliente(idCliente);

        clienteRepository.salvar();
    }

    public ArrayList<Cliente> listarClientes(){
        return clienteRepository.listarClientes();
    }
}
