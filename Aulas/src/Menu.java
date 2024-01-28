import java.util.Scanner;

public class Menu {

    public static void exibirMenu() {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        System.out.println(
                "1 - Adicionar Contato\n" +
                "2 - Remover Contato\n" +
                "3 - Editar Contato\n" +
                "4 - Sair\n" +
                "Escolha a opção desejada: ");

        while (opcao < 1 || opcao > 4) {
            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Entrada inválida. Tente novamente.");
                sc.nextLine();
            }
        }

        sc.close();
    }

    public static Long obterIdContato() {
        Scanner sc = new Scanner(System.in);
        Long id = null;

        while (id == null) {
            System.out.print("Digite o ID do contato: ");
            if (sc.hasNextLong()) {
                id = sc.nextLong();
            } else {
                System.out.println("Erro: ID inválido. Tente novamente.");
                sc.nextLine();
            }
        }

        sc.close();
        return id;
    }
}
