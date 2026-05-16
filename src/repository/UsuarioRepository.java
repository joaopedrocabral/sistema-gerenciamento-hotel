package repository;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioRepository {
    private ArrayList<Usuario> listaUsuarios;

    public UsuarioRepository(){
        listaUsuarios = new ArrayList<>();
    }

    public Usuario buscarUsuarioPorId(int id){
        if(id <= 0){
            return null;
        }

        for(Usuario usuario : listaUsuarios){
            if(usuario.getId() == id){
                return usuario;
            }
        }

        return null;
    }

    public void adicionarUsuario(Usuario usuario){
        if(usuario == null){
            throw new IllegalArgumentException("ERRO! Usuário inválido.");
        }

        if(buscarUsuarioPorId(usuario.getId()) != null){
            throw new IllegalArgumentException("ERRO! Já existe um usuário com esse ID.");
        }

        listaUsuarios.add(usuario);
    }

    public void removerUsuario(int id){
        Usuario usuario = buscarUsuarioPorId(id);

        if(usuario == null){
            throw new IllegalArgumentException("ERRO! Usuário inválido.");
        }

        listaUsuarios.remove(usuario);
    }

    public ArrayList<Usuario> listarUsuarios(){
        return new ArrayList<>(listaUsuarios);
    }

}
