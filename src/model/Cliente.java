package model;

public class Cliente extends Pessoa {
    private int id;

    public Cliente(String nome, String cpf, String telefone, int id){
        super(nome, cpf, telefone);
        setId(id);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if(id <= 0){
            throw new IllegalArgumentException("ERRO! ID inválido!");
        }
        this.id = id;
    }

    @Override
    public String toString(){
        return "===== CLIENTE =====\n" +
                "ID: " + id + "\n" +
                super.toString();
    }
}
