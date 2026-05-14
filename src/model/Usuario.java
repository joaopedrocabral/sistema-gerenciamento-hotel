package model;
import enums.Cargo;

public class Usuario extends Pessoa{
    private int id;
    private String senha;
    private Cargo cargo;

    public Usuario(String nome, String cpf, String telefone, int id, String senha, Cargo cargo){
        super(nome, cpf, telefone);
        setSenha(senha);
        setCargo(cargo);
        setId(id);
    }

    public int getId(){
        return id;
    }

    public Cargo getCargo(){
        return cargo;
    }

    public void setId(int id){
        if(id <= 0){
            throw new IllegalArgumentException("ERRO! ID inválido!");
        }
        this.id = id;
    }

    public void setCargo(Cargo cargo){
        if(this.cargo == null){
            throw new IllegalArgumentException("ERRO! Cargo inválido.");
        }
        this.cargo = cargo;
    }

    public void setSenha(String senhaDigitada){
        if(senhaDigitada == null || senhaDigitada.trim().isEmpty()){
            throw new IllegalArgumentException("ERRO! Senha inválida ou vazia.");
        }

        if(this.senha != null && validarSenha(senhaDigitada)){
            throw new IllegalArgumentException("ERRO! Senha digitada igual a senha anterior.");
        }

        this.senha = senhaDigitada;
    }

    public boolean validarSenha(String senhaDigitada){
        return this.senha != null && this.senha.equals(senhaDigitada);
    }

    public void alterarSenha(String senhaAtual, String novaSenha){
        if(!validarSenha(senhaAtual)){
            throw new IllegalArgumentException("ERRO! Senha digitada diferente da senha atual.");
        }

        setSenha(novaSenha);
    }

    @Override
    public String toString(){
        return "===== USUÁRIO =====\n" +
                "ID: " + id + "\n" +
                "Cargo: " + cargo + "\n" +
                super.toString();
    }

}
