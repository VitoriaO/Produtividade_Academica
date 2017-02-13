import java.util.ArrayList;

public class Professor extends Colaborador{
	private ArrayList<Producao> orientacoes;

	// Set:
	public void setOrientacoes(ArrayList<Producao> orientacoes) {
		this.orientacoes = orientacoes;
	}
	
	// Get:
	public ArrayList<Producao> getOrientacoes() {
		return orientacoes;
	}

}