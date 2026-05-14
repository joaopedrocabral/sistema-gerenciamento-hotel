package model;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;


    public Pessoa(String nome, String cpf, String telefone) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setNome(String nome){
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Nome inválido ou vazio!");
        }
        this.nome = nome;
    }

    public void setCpf(String cpf){
        if(cpf == null || cpf.trim().isEmpty()){
            throw new IllegalArgumentException("CPF inválido ou vazio!");
        }
        this.cpf = cpf;
    }

    public void setTelefone(String telefone){
        if(telefone == null || telefone.trim().isEmpty()){
            throw new IllegalArgumentException("Telefone inválido ou vazio!");
        }
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "Telefone: " + telefone + "\n";
    }


}