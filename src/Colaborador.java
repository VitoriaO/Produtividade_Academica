import java.util.ArrayList;
import java.util.Collections;

public class Colaborador {
	private String nome;
	private String email;
	private ArrayList<Projeto> projetos;
	private ArrayList<Publicacao> producao;
	
	public Colaborador(){
		nome = null;
		email = null;
		projetos = new ArrayList<Projeto>();
		producao = new ArrayList<Publicacao>();
	}
	
	// Setters:
	public void setNome(String n) {
		nome = n;
	}
	
	public void setEmail(String em) {
		email = em;
	}
	
	public void setProjetos(ArrayList<Projeto> proj) {
		projetos = proj;
	}
	
	public void setProducao(ArrayList<Publicacao> prod) {
		producao = prod;
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
	
	public ArrayList<Publicacao> getProducao() {
		return producao;
	}
	
	// Imprime os nomes dos colaboradores
	public void printColabs(ArrayList<Colaborador> c){
		for(int i = 0; i < c.size(); i++){
			System.out.println(c.get(i).nome);
		}
	}
	
	public void addProjeto(Projeto proj){
		projetos.add(proj);
		Collections.sort(projetos);
	}

	public void printProjetos(char status){
		for(int i = 0; i < projetos.size(); i++){
			if(projetos.get(i).getStatus() == status){
				System.out.println(projetos.get(i).getTitulo());
			}
		}
	}
	
	public void printProducao(){
		for(int i = 0; i < producao.size(); i++){
			System.out.println(producao.get(i).getTitulo());
		}
	}
	
}