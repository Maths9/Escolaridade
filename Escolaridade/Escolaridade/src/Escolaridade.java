import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Escolaridade {
    public void menuPrincipal(){

        //CRIAR OPÇÃO PARA LOGIN

        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        System.out.println("Bem vindo ao sistema da Escolaridade");
        System.out.println("---------------------------");
        
        while (opcao != 0) {
            System.out.println("Menu:");
            System.out.println("1- Cadastrar Professor");
            System.out.println("2- Cadastrar Aluno");
            System.out.println("3- Comprovante de Matrícula");
            System.out.println("4- Turmas");
            System.out.println("5- Grade de Horário");
            System.out.println("6-Login");
            System.out.println("0- Sair");
            System.out.println("Digite a opção que deseja: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if(opcao == 1) {
                this.cadastrarUsuario(sc);
            }
            if(opcao==6){
                this.entrarUsuario(sc);
            }

        }
    }
    public void cadastrarUsuario(Scanner sc){

        //Base para método de cadastro

        String cpf = null, nome = null, sexo = null;
        int idade = 0;

        try{
            System.out.println("Digite seu CPF:");
            cpf = sc.nextLine();
            System.out.println("Digite seu nome:");
            nome = sc.nextLine();
            System.out.println("Digite sua idade:");
            idade = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite seu sexo:");
            sexo = sc.nextLine();

        } catch(InputMismatchException exception){
            System.out.println("ERRO! Digite sua idade em números.");
        }
        //Validação dos dados
        if(cpf.length()==11
                && idade>=13
                && (!nome.isEmpty()) &&
                (!sexo.isEmpty() && sexo.toLowerCase().equals("masculino") || sexo.toLowerCase().equals("feminino") ))
        {
            try{

                BufferedWriter writer = new BufferedWriter(new FileWriter("dados/dados.txt",true));
                writer.write(cpf+" "+nome+" "+idade+" "+sexo);
                writer.newLine();
                writer.close();
            } catch(IOException exception) {
                System.out.println("ERRO:" + exception.getMessage());
            }
        } else{
            System.out.println("Valor inválido!");
        }

    }
    public void entrarUsuario(Scanner sc){

        //Base para o método de Login

        //NECESSÁRIO CRIAR SENHA

        boolean logado = false; //verificar se o login foi bem-sucedido

        String cpf,senha;
        System.out.println("Digite o cpf:");
        cpf = sc.nextLine();
        System.out.println("Digite a senha:");
        senha = sc.nextLine();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("dados/dados.txt"));
            String linha;
            while((linha = reader.readLine())!=null){
                String[] dados = linha.split(" ");
                if(cpf.equals(senha) && cpf.equals(dados[0])){
                    System.out.println("Bem vindo "+dados[1]+"!");
                    logado = true;
                    break;
                }
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
