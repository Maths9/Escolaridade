import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//LEMBRAR DE FAZER TRATAMENTO DE EXCESSÕES NOS MENUS
public class Escolaridade {
    public void menuPrincipal(){


        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        System.out.println("Bem vindo ao sistema da Escolaridade");
        System.out.println("-------------------------------------");

        //Menu principal
        //tratar a exceção de uso de letras no menu principal
        while (opcao != 0) {
            System.out.println("Menu:");
            System.out.println("1- Login");
            System.out.println("2- Cadastrar usuário");
            System.out.println("0- Sair");
            System.out.println("Digite a opção que deseja: ");
            try{

                opcao = sc.nextInt();
                sc.nextLine();

            if(opcao == 1) {
                this.entrarUsuario(sc);
            }
            if(opcao==2){
                this.cadastrarUsuario(sc);
            }
            if(opcao>2||opcao<0) {
                System.out.println(" ");
                System.out.println("Digite uma opção válida!");
                System.out.println("------------------------");
            }
            } catch(InputMismatchException exception){
                System.out.println("ERRO:"+"Digite um número inteiro!");
                sc.nextLine();
            }

        }
        System.out.println("Programa finalizado!");
    }
    //Menu do aluno
    public void menuAluno(Scanner sc, Aluno aluno){
        int opcao = -1;
        //System.out.println(aluno);
        while(opcao!=0){
        System.out.println("1- Consultar boletim");
        System.out.println("2- Solicitar comprovante de matrícula");
        System.out.println("0- Sair");
        System.out.println("Digite a opção que deseja: ");

        try {
            opcao = sc.nextInt();

            sc.nextLine();

            switch (opcao) {
                case 1:
                    aluno.consultarBoletim();
                    break;
                case 2:
                    aluno.consultarComprovante();
                    break;
            }
        } catch(InputMismatchException exception){
            System.out.println("ERRO:"+"Digite um número inteiro!");
        }

        }
    }
    //Menu do professor
    // Falta Adicionar metodo de Adicionar nota e consultar turmas
    public void menuProfessor(Scanner sc, Professor professor){
        int opcao = -1;
        //System.out.println(professor);
        while(opcao!=0){
        System.out.println("1- Adicionar nota:");
        System.out.println("2- Consultar lista das turmas:");
        System.out.println("0- Sair");
        System.out.println("Digite a opção desejada:");
        try {
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    professor.adicionarNota(sc);
                    break;
                case 2:
                    for (String aluno : professor.consultarTurma()) {
                        System.out.println(aluno);
                    }
                    break;

            }
        } catch(InputMismatchException exception){
            System.out.println("ERRO:"+"Digite um número inteiro!");
        }
        }
    }
    //Cadastrar usuário
    public void cadastrarUsuario(Scanner sc){

        //Base para método de cadastro

        String cpf = null, senha = null, nome = null, sexo = null, cargo = null;
        File file = null;
        int idade = 0;

        //LEMBRAR DE: VERIFICAR SE O CPF CONTÉM APENAS NÚMEROS
        try{

            System.out.println("Digite seu CPF (xxxxxxxxxxx):");
            cpf = sc.nextLine();
            if(cpf.length()==11 && cpf.matches("[0-9]+")){
                // tentar criar pasta no final apenas
            if(!(file = new File("dados/"+cpf)).exists()){
                System.out.println("Digite uma senha (Mínimo 8 dígitos):");
                senha = sc.nextLine();
                System.out.println("Digite seu primeiro nome:");
                nome = sc.nextLine();
                System.out.println("Digite sua idade (xx):");
                idade = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite seu sexo (masculino/feminino):");
                sexo = sc.nextLine();
                //verificar se é aluno ou professor
                System.out.println("Digite o cargo (Aluno/Professor):");
                cargo = sc.nextLine();



            } else{
                System.out.println("O usuário já existe!");
            } }

        } catch(InputMismatchException exception){
            System.out.println("ERRO! Digite sua idade em números.");
        }
        //Validação dos dados
        if(senha.length()>=8 && idade>=13 && idade <= 99
                && (!nome.isEmpty()) && nome.toLowerCase().matches("[a-z]+") &&
                (!sexo.isEmpty() && sexo.toLowerCase().equals("masculino") || sexo.toLowerCase().equals("feminino") ))
        {
            try{

                file.mkdir();

                BufferedWriter writer = new BufferedWriter(new FileWriter("dados/"+cpf+"/dados.txt"));
                writer.write(cpf+" "+senha+" "+nome+" "+idade+" "+sexo+" "+ cargo);

                if(cargo.toLowerCase().equals("professor")){
                    System.out.println("Digite a formação do professor:");
                    String formacao = sc.nextLine();
                    writer.write(" "+formacao);
                    writer.newLine();
                    System.out.println("Digite suas turmas:");
                    String turmas = sc.nextLine();
                    System.out.println("----------------------");
                    System.out.println("Usuário cadastrado!");
                    writer.write(turmas);

                }
                if(cargo.toLowerCase().equals("aluno")){
                    System.out.println("Digite a turma:");
                    String turma = sc.nextLine();
                    System.out.println("----------------------");
                    System.out.println("Usuário cadastrado!");
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
                    System.out.println("-------------------------");
                    System.out.println("Bem vindo "+dados[2]+"!");
                    System.out.println("");
                    if(dados[5].equalsIgnoreCase("professor")){
                        //CORRIGIR TURMAS
                        //cpf, senha, nome,idade, sexo, cargo, formacao, turma
                        //String nome, int idade, String cpf, String sexo, String formacao, String senha, String cargo, String turmas
                        this.menuProfessor(sc, new Professor(dados[2],Integer.parseInt(dados[3]),dados[0], dados[4], dados[6], dados[1], dados[5], turmas));

                    }else if(dados[5].equalsIgnoreCase("aluno")){
                        //String cpf, String nome, int idade,String sexo, String senha, String cargo
                        this.menuAluno(sc, new Aluno(dados[0], dados[2], Integer.parseInt(dados[3]), dados[4], dados[1], dados[5], turmas));
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
