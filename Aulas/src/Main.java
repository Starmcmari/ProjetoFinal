import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Pessoa> contatos;

    public static void main(String[] args) {
        try {
            contatos = Contatos.carregarContatos();
            exibirMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exibirMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("Lista de contatos:");
            for (Pessoa contato : contatos) {
                System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome());
            }

            System.out.println(
                    "1 - Adicionar Contato\n" +
                            "2 - Remover Contato\n" +
                            "3 - Editar Contato\n" +
                            "4 - Sair\n" +
                            "Escolha a opção desejada: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
                executarOpcao(opcao, contatos, scanner);
            } else {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }

        Contatos.salvarContatos(contatos);
        scanner.close();
    }

    public static void executarOpcao(int opcao, List<Pessoa> contatos, Scanner scanner) throws IOException {
        switch (opcao) {
            case 1:
                Pessoa novoContato = obterNovoContato(scanner);
                Contatos.adicionarContato(novoContato, contatos);
                break;
            case 2:
                removerUmContato(contatos, scanner);
                break;
            case 3:
                editarUmContato(contatos, scanner);
                break;
            case 4:
                Contatos.salvarContatos(contatos);
                System.out.println("Contatos salvos com sucesso.");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static Pessoa obterNovoContato(Scanner scanner) {
        Pessoa novoContato = new Pessoa();

        System.out.print("Digite o nome do novo contato: ");
        novoContato.setNome(scanner.nextLine());

        System.out.print("Digite o sobrenome do novo contato: ");
        novoContato.setSobrenome(scanner.nextLine());

        List<Telefone> telefones = new ArrayList<>();
        boolean adicionarOutroTelefone = true;

        while (adicionarOutroTelefone) {
            Telefone novoTelefone = new Telefone();

            System.out.print("Digite o DDD do telefone: ");
            novoTelefone.setDdd(scanner.nextLine());

            System.out.print("Digite o número do telefone: ");
            novoTelefone.setNumero(Long.parseLong(scanner.nextLine()));

            telefones.add(novoTelefone);

            System.out.print("Deseja adicionar outro telefone? (S/N): ");
            String resposta = scanner.nextLine().toUpperCase();

            adicionarOutroTelefone = resposta.equals("S");
        }

        novoContato.setTelefones(telefones);

        return novoContato;
    }

    public static void removerUmContato(List<Pessoa> contatos, Scanner scanner) throws IOException {
        System.out.print("Digite o ID do contato que deseja remover: ");
        Long idRemover = scanner.nextLong();

        contatos.removeIf(contato -> contato.getId().equals(idRemover));

        System.out.println("Contato removido com sucesso.");
        Contatos.salvarContatos(contatos);
    }

    public static void editarUmContato(List<Pessoa> contatos, Scanner scanner) throws IOException {
        System.out.print("Digite o ID do contato que deseja editar: ");
        Long idEditar = scanner.nextLong();

        Pessoa contatoParaEditar = contatos.stream()
                .filter(contato -> contato.getId().equals(idEditar))
                .findFirst()
                .orElse(null);

        if (contatoParaEditar != null) {
            System.out.print("Digite o novo nome: ");
            String novoNome = scanner.next();
            contatoParaEditar.setNome(novoNome);

            System.out.print("Digite o novo sobrenome: ");
            String novoSobrenome = scanner.next();
            contatoParaEditar.setSobrenome(novoSobrenome);

            List<Telefone> novosTelefones = new ArrayList<>();
            boolean adicionarOutroTelefone = true;

            while (adicionarOutroTelefone) {
                Telefone novoTelefone = new Telefone();

                System.out.print("Digite o DDD do telefone: ");
                novoTelefone.setDdd(scanner.next());

                System.out.print("Digite o número do telefone: ");
                novoTelefone.setNumero(Long.parseLong(scanner.next()));

                novosTelefones.add(novoTelefone);

                System.out.print("Deseja adicionar outro telefone? (S/N): ");
                String resposta = scanner.next().toUpperCase();

                adicionarOutroTelefone = resposta.equals("S");
            }

            contatoParaEditar.setTelefones(novosTelefones);

            System.out.println("Contato editado com sucesso.");
            Contatos.salvarContatos(contatos);
        } else {
            System.out.println("Contato não encontrado com o ID fornecido.");
        }
    }
}
