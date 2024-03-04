public class Aluno extends Pessoa{

    private String turma;

    //Construtor do aluno
    public Aluno(String cpf, String nome, int idade,String sexo, String senha, String cargo){
        super(cpf,nome,idade,sexo, senha, cargo);
    }

    //Métodos gets e sets
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void consultarBoletim(){

    }
    public void consultarComprovante(){
        System.out.println("Declaramos que o aluno"+ this.getNome()+ "Está matriculado!");
    }
}
