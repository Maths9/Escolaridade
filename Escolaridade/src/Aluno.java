import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Aluno extends Pessoa{

    private String turma;

    //Construtor do aluno
    public Aluno(String cpf, String nome, int idade,String sexo, String senha, String cargo, String turma){
        //String nome, String sexo, int idade, String cpf, String senha, String cargo
        super(nome,sexo,idade,cpf, senha, cargo);
        this.turma = turma;
    }

    //Métodos gets e sets
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void consultarBoletim(){
        if(new File("dados/"+this.getCpf()+"/boletim.txt").exists()){
            String line;
            try{

                BufferedReader reader = new BufferedReader(new FileReader("dados/"+this.getCpf()+"/boletim.txt"));

                while((line = reader.readLine())!=null){
                    System.out.println(line);
                }

            }catch(IOException exception){
                System.out.println("ERRO:"+exception.getMessage());
            }
        } else{
            System.out.println("Nenhuma de suas notas foi publicada!");
        }
    }
    public void consultarComprovante(){
        System.out.println("Declaramos que o aluno "+ this.getNome() + " Está matriculado!");
    }

    public String toString(){
        return super.toString()+this.turma;
    }
}
