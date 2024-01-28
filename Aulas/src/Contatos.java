import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Contatos {

    private static final String ARQUIVO_CONTATOS = "contatos.txt";

    public static void salvarContatos(List<Pessoa> contatos) throws IOException {
        try (FileWriter writer = new FileWriter(new File(ARQUIVO_CONTATOS))) {
            for (Pessoa contato : contatos) {
                writer.write(converterParaLinha(contato) + "\n");
            }
        }
    }

    private static String converterParaLinha(Pessoa contato) {
        StringBuilder linha = new StringBuilder();
        linha.append(contato.getNome()).append(";")
                .append(contato.getSobrenome()).append(";");

        List<Telefone> telefones = contato.getTelefones();
        for (Telefone telefone : telefones) {
            linha.append(telefone.getDdd()).append(";")
                    .append(telefone.getNumero()).append(";");
        }

        return linha.toString();
    }

    public static List<Pessoa> carregarContatos() throws IOException {
        List<Pessoa> contatos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CONTATOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Pessoa contato = ConversorLinhaPessoa.obtemPessoa(linha);
                if (contato != null) {
                    contatos.add(contato);
                }
            }
        }
        return contatos;
    }

    public static void adicionarContato(Pessoa contato, List<Pessoa> contatos) throws IOException {
        if (!contatos.contains(contato)) {
            contatos.add(contato);
            salvarContatos(contatos);
        } else {
            System.out.println("Erro: Contato com mesmo ID já existe.");
        }
    }

    public static void removerContato(Long id, List<Pessoa> contatos) throws IOException {
        contatos.removeIf(pessoa -> pessoa.getId().equals(id));
        salvarContatos(contatos);
    }

    public static void editarContato(Long id, Pessoa novoContato, List<Pessoa> contatos) throws IOException {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getId().equals(id)) {
                contatos.set(i, novoContato);
                salvarContatos(contatos);
                return;
            }
        }
        System.out.println("Erro: Contato não encontrado com o ID fornecido.");
    }
}
