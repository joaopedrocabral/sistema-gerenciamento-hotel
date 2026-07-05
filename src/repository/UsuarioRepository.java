package repository;
import java.util.ArrayList;
import java.util.HashMap;

import model.Usuario;

public class UsuarioRepository {
    private static UsuarioRepository instance;
    private static final int PRIMEIRO_ID = 1;

    private HashMap<Integer, Usuario> listaUsuarios;

    private UsuarioRepository(){
        listaUsuarios = new HashMap<>();
    }

    public static UsuarioRepository getInstance(){
        if(instance == null){
            instance = new UsuarioRepository();
        }

        return instance;
    }

    public int getProximoId(){
        int maiorId = PRIMEIRO_ID - 1;

        for(Integer idAtual : listaUsuarios.keySet()){
            if(idAtual > maiorId){
                maiorId = idAtual;
            }
        }

        return maiorId + 1;
    }

    public Usuario buscarUsuarioPorId(int id){
        if(id <= 0){
            return null;
        }

        return listaUsuarios.get(id);
    }

    public void adicionarUsuario(Usuario usuario){
        if(usuario == null){
            throw new IllegalArgumentException("ERRO! Usuário inválido.");
        }

        if(listaUsuarios.containsKey(usuario.getId())){
            throw new IllegalArgumentException("ERRO! Já existe um usuário com esse ID.");
        }

        listaUsuarios.put(usuario.getId(), usuario);
    }

    public void removerUsuario(int id){
        if(listaUsuarios.remove(id) == null){
            throw new IllegalArgumentException("ERRO! Usuário inválido.");
        }
    }

    public ArrayList<Usuario> listarUsuarios(){
        return new ArrayList<>(listaUsuarios.values());
    }

}
