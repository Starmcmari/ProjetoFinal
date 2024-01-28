import java.util.ArrayList;

public class ConversorLinhaPessoa {

    public static Pessoa obtemPessoa(String linha) {
        String regex = "/";
        String[] valoresStrings = linha.split(regex);

        Pessoa pessoa = new Pessoa();
        pessoa.setTelefones(new ArrayList<>());

        if (valoresStrings.length >= 2) {
            String[] valores = valoresStrings[1].split(";");
            pessoa.setId(Long.parseLong(valores[0]));
            pessoa.setNome(valores[1]);
            pessoa.setSobrenome(valores[2]);

            for (int i = 3; i < valores.length; i += 2) {
                Telefone telefone = new Telefone();
                telefone.setDdd(valores[i]);
                telefone.setNumero(Long.parseLong(valores[i + 1]));
                pessoa.getTelefones().add(telefone);
            }
        } else {
            System.out.println("Erro: Formato inválido na linha - " + linha);
        }

        return pessoa;
    }
}
