public class Professores extends Pessoas {
    private String formacao;
    //
    public Professores(String nome, int idade, int cpf, String sexo, String formacao){
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
