import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Professor extends Pessoa {
    private String formacao;

    private String turmas;
    //Construtor professor
    public Professor(String nome, int idade, String cpf, String sexo, String formacao, String senha, String cargo, String turmas){
        super(nome, sexo, idade, cpf, senha, cargo);
        this.formacao = formacao;
        this.turmas = turmas;
    }
    //Gets e sets
    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void adicionarNota(Scanner sc){
        for(String aluno: this.consultarTurma()){
            String[] dados = aluno.split(":");
            System.out.println("Nota para o aluno "+dados[1]+":");
            String nota = sc.nextLine();
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter("dados/"+dados[0]+"/boletim.txt",true));
                BufferedReader reader = new BufferedReader(new FileReader("dados/"+dados[0]+"/boletim.txt"));

                String line;
                boolean notaAdicionada=false;


                while((line=reader.readLine())!=null){
                    if(line.contains(this.formacao)){
                        notaAdicionada=true;
                        break;
                    }
                }

                if(notaAdicionada){
                    System.out.println("Você já adicionou a nota desse aluno!");
                } else{
                    writer.write(this.formacao+":"+nota);
                    writer.newLine();
                    System.out.println("Nota adicionada!");
                }

                writer.close();
                reader.close();

            } catch (IOException exception){
                System.out.println("ERRO:"+exception.getMessage());
            }
        }
    }

    public ArrayList<String> consultarTurma(){
        ArrayList<String> alunos = new ArrayList<>(0);
        for(File usuario: new File("dados").listFiles()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(usuario+"/dados.txt"));
                String[] dados = reader.readLine().split(" ");
                String turma = reader.readLine();
                if(dados[5].equalsIgnoreCase("aluno") && this.turmas.contains(turma)){
                    alunos.add(dados[0]+":"+dados[2]+":"+turma);
                }

                reader.close();
            } catch(IOException exception){
                System.out.println("ERRO:"+exception.getMessage());
            }
        }
        return alunos;
    }

    public String toString(){
        return super.toString()+this.formacao+this.turmas;
    }
}
