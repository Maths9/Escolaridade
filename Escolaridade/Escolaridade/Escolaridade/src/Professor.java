public class Professor extends Pessoa {
    private String formacao;
    //
    public Professor(String nome, int idade, String cpf, String sexo, String formacao){
        super(nome, sexo, idade, cpf);
        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    
    
}
