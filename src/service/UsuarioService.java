package service;
import model.Usuario;
import repository.UsuarioRepository;

import java.util.ArrayList;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarUsuarioPorId(int idUsuario){
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(idUsuario);

        if(usuario == null){
            throw new IllegalArgumentException("ERRO! Não existe usuário com esse ID");
        }

        return usuario;
    }

    public void cadastrarUsuario(Usuario usuario){
        usuarioRepository.adicionarUsuario(usuario);
    }

    public void removerUsuario(int idUsuario){
        usuarioRepository.removerUsuario(idUsuario);
    }

    public ArrayList<Usuario> listarUsuarios(){
        return usuarioRepository.listarUsuarios();
    }

    public Usuario autenticarUsuario(int id, String senha){
        Usuario usuario = buscarUsuarioPorId(id);

        if(!usuario.validarSenha(senha)){
            throw new IllegalArgumentException("ERRO! A senha digitada é inválida.");
        }

        return usuario;

    }
}
