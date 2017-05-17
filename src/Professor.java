import java.util.ArrayList;

public class Professor extends Colaborador{
	private ArrayList<Orientacao> orientacoes;
	private boolean gerente;
	private String senha;
	
	public Professor(){
		super();
		orientacoes = new ArrayList<Orientacao>();
	}

	// Set:
	public void setOrientacoes(ArrayList<Orientacao> orientacoes) {
		this.orientacoes = orientacoes;
	}
	
	public void setGerente(){
		gerente = true;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	// Get:
	public ArrayList<Orientacao> getOrientacoes() {
		return orientacoes;
	}

	public boolean getGerente(){
		return gerente;
	}

	public String getSenha() {
		return senha;
	}
	
}