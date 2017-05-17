
public class Aluno extends Colaborador{
	private char tipo;
	
	// Por default alunos são alunos de graduação
	public Aluno(){
		super();
		tipo = 'g';
	}

	// Set:
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	// Get:
	public char getTipo() {
		return tipo;
	}
}
