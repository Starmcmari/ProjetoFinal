import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	
	private static Long proximoId = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    private List<Telefone> telefones = new ArrayList<>();
    
    public Pessoa() {
        this.id = proximoId++;
        this.telefones = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

	public void setTelefones(String novoTelefone, List<Telefone> telefones) {
		this.telefones = telefones;
		
	}
}
