public abstract class Pessoas {
    //atributos em comum entre professores e alunos
    private String nome;
    private String sexo;
    private int idade;
    private int cpf;

    //comstrutor que contém informações em comum(professor/aluno)
    public Pessoas(String nome, String sexo, int idade, int cpf){
        this.cpf = cpf;
        this.idade = idade;
        this.nome = nome;
        this.sexo = sexo;

    }
    //métodos get e sets
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    
}
