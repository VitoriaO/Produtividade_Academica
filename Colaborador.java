import java.util.ArrayList;

public class Colaborador {
	private String nome;
	private String email;
	private ArrayList<Projeto> projetos;
	private ArrayList<Producao> producao;
	
	// Setters:
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setProjetos(ArrayList<Projeto> projetos) {
		this.projetos = projetos;
	}
	
	public void setProducao(ArrayList<Producao> producao) {
		this.producao = producao;
	}
	
	// Getters:
	public String getNome() {
		return nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public ArrayList<Projeto> getProjetos() {
		return projetos;
	}
	
	public ArrayList<Producao> getProducao() {
		return producao;
	}
	
	// Imprime os nomes dos colaboradores
	public void printColabs(ArrayList<Colaborador> c){
		for(int i = 0; i < c.size(); i++){
			System.out.println(c.get(i).nome);
		}
	}
	
}