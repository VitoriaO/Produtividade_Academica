import java.util.ArrayList;

public class Publicacao implements Comparable<Publicacao>{
	private String titulo;
	private String conferencia;
	private ArrayList<Colaborador> autores;
	private Projeto projeto_a;
	private int ano;

	public String getTitulo() {
		return titulo;
	}
	
	public String getConferencia() {
		return conferencia;
	}
	
	public Projeto getProjeto_a() {
		return projeto_a;
	}
	
	public int getAno(){
		return ano;
	}
	
	public ArrayList<Colaborador> getAutores() {
		return autores;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setConferencia(String conferencia) {
		this.conferencia = conferencia;
	}
	
	public void setProjeto_a(Projeto projeto_a) {
		this.projeto_a = projeto_a;
	}
	
	public void setAno(int ano){
		this.ano = ano;
	}
	
	public void addAutores(Colaborador autor) {
		autores.add(autor);
	}
	
	@Override
	public int compareTo(Publicacao prod){
		if(this.ano < prod.ano){
			return -1;
		}
		else if(this.ano > prod.ano){
			return 1;
		}
		return 0;
	}
	
}
