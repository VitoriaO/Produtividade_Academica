
public class Aluno extends Colaborador{
	private char tipo;
	
	// Por default alunos s�o alunos de gradua��o
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
