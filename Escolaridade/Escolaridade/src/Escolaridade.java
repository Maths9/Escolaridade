import java.util.Scanner;

public class Escolaridade {
    public void menuPrincipal(){
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        System.out.println("Bem vindo ao sistema da Escolaridade");
        System.out.println("---------------------------");
        
        while (opcao != 0) {
            System.out.println("Menu:");
            System.out.println("1- Cadastrar Professor");
            System.out.println("2- Cadastrar Aluno");
            System.out.println("3- Comprovamte de Matrícula");
            System.out.println("4- Turmas");
            System.out.println("5- Grade de Horário");
            System.out.println("0- Sair");
            System.out.println("Digite a opção que deseja: ");
            opcao = sc.nextInt();
            sc.nextLine();
            if(opcao == 1){
                System.out.println("Tá funcionando");
            }

            
        }
    }
}
