public class Professor extends Pessoa {
    private String formacao;

    private String turmas;
    //Construtor professor
    public Professor(String nome, int idade, String cpf, String sexo, String formacao, String senha, String cargo, String turmas){
        super(nome, sexo, idade, cpf, senha, cargo);
        this.formacao = formacao;
    }
    //Gets e sets
    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void adicionarNota(){

    }

    public void consultarTurma(){

    }
}
