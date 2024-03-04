import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Escolaridade {
    public void menuPrincipal(){


        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        System.out.println("Bem vindo ao sistema da Escolaridade");
        System.out.println("---------------------------");

        //Menu principal
        while (opcao != 0) {
            System.out.println("Menu:");
            System.out.println("1- Login");
            System.out.println("2- Cadastrar usuário");
            System.out.println("0- Sair");
            System.out.println("Digite a opção que deseja: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if(opcao == 1) {
                this.entrarUsuario(sc);
            }
            if(opcao==2){
                this.cadastrarUsuario(sc);
            }

        }
    }
    //Menu do aluno
    public void menuAluno(Scanner sc, Aluno aluno){
        System.out.println("1- Consultar boletim");
        System.out.println("2- Solicitar comprovante de matrícula");
        System.out.println("0- Sair");
        System.out.println("Digite a opção que deseja: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch(opcao){
            case 1:
                aluno.consultarBoletim();
                break;
            case 2:
                aluno.consultarComprovante();
                break;
        }
    }
    //Menu do professor
    public void menuProfessor(Scanner sc, Professor professor){
        System.out.println("1- Adicionar nota:");
        System.out.println("2- Consultar lista da turma:");
        System.out.println("0- Sair");
        System.out.println("Digite a opção desejada:");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch(opcao){
            case 1:
                professor.adicionarNota();
                break;
            case 2:
                professor.consultarTurma();
                break;

        }
    }
    //Cadastrar usuário
    public void cadastrarUsuario(Scanner sc){

        //Base para método de cadastro

        String cpf = null, senha = null, nome = null, sexo = null, cargo = null;
        int idade = 0;
        //LEMBRAR DE: VERIFICAR SE O CPF CONTÉM APENAS NÚMEROS
        try{
            System.out.println("Digite seu CPF:");
            cpf = sc.nextLine();
            if(cpf.length()==11){
            if(new File("dados/"+cpf).mkdir()){
                System.out.println("Digite uma senha:");
                senha = sc.nextLine();
            System.out.println("Digite seu nome:");
            nome = sc.nextLine();
            System.out.println("Digite sua idade:");
            idade = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite seu sexo:");
            sexo = sc.nextLine();
            //verificar se é aluno ou professor
                System.out.println("Digite o cargo:");
            cargo = sc.nextLine();
            } else{
                System.out.println("O usuário já existe!");
            } }

        } catch(InputMismatchException exception){
            System.out.println("ERRO! Digite sua idade em números.");
        }
        //Validação dos dados
        if(idade>=13
                && (!nome.isEmpty()) &&
                (!sexo.isEmpty() && sexo.toLowerCase().equals("masculino") || sexo.toLowerCase().equals("feminino") ))
        {
            try{

                BufferedWriter writer = new BufferedWriter(new FileWriter("dados/"+cpf+"/dados.txt"));
                writer.write(cpf+" "+senha+" "+nome+" "+idade+" "+sexo+" "+ cargo);

                if(cargo.toLowerCase().equals("professor")){
                    System.out.println("Digite a formação do professor:");
                    String formacao = sc.nextLine();
                    writer.write(" "+formacao);
                    writer.newLine();
                    System.out.println("Digite suas turmas:");
                    String turmas = sc.nextLine();
                    writer.write(turmas);

                }
                if(cargo.toLowerCase().equals("aluno")){
                    System.out.println("Digite a turma:");
                    String turma = sc.nextLine();
                    writer.newLine();
                    writer.write(turma);
                }


                writer.close();
            } catch(IOException exception) {
                System.out.println("ERRO:" + exception.getMessage());
            }
        } else{
            System.out.println("Valor inválido!");
        }

    }
    //Login usuário
    public void entrarUsuario(Scanner sc){


        boolean logado = false; //verificar se o login foi bem-sucedido

        String cpf,senha;
        System.out.println("Digite o cpf:");
        cpf = sc.nextLine();
        System.out.println("Digite a senha:");
        senha = sc.nextLine();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("dados/"+cpf+"/dados.txt"));
            String linhaDados, turmas;

                linhaDados = reader.readLine();
                turmas = reader.readLine();

                String[] dados = linhaDados.split(" ");
                if(senha.equals(dados[1]) && cpf.equals(dados[0])){
                    System.out.println("Bem vindo "+dados[2]+"!");
                    if(dados[5].equalsIgnoreCase("professor")){
                        //CORRIGIR TURMAS
                        //cpf, senha, nome,idade, sexo, cargo, formacao, turma
                        //String nome, int idade, String cpf, String sexo, String formacao, String senha, String cargo, String turmas
                        this.menuProfessor(sc, new Professor(dados[2],Integer.parseInt(dados[3]),dados[0], dados[4], dados[6], dados[1], dados[5], turmas));
                    }else if(dados[5].equalsIgnoreCase("aluno")){
                        //String cpf, String nome, int idade,String sexo, String senha, String cargo
                        this.menuAluno(sc, new Aluno(dados[0],dados[2],Integer.parseInt(dados[3]),dados[4],dados[5],turmas));
                    }
                    logado = true;


            }
            if(!logado){
                System.out.println("Usuário ou senha inválidos");
            }
            reader.close();
        }
        catch(IOException exception){
            System.out.println("ERRO:"+exception.getMessage());
        }
    }
}
