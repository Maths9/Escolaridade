public abstract class Pessoa {
    //atributos em comum entre professores e alunos
    private String nome;
    private String sexo;
    private int idade;
    private String cpf;

    //construtor que contém informações em comum(professor/aluno)
    public Pessoa(String nome, String sexo, int idade, String cpf){
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
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
}
