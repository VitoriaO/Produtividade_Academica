import java.util.ArrayList;

public class Projeto {
	private String titulo;
	private String agencia;
	private String objetivo;
	private String descricao;
	private char status;
	private int dia_inicio;
	private int dia_termino;
	private int mes_inicio;
	private int mes_termino;
	private int ano_inicio;
	private int ano_termino;
	private float valor;
	private ArrayList<Colaborador> participantes;
	
	public Projeto(){
		titulo = "";
		agencia = "";
		objetivo = "";
		descricao = "";
		status = 0;
		dia_inicio = 0;
		dia_termino = 0;
		mes_inicio = 0;
		mes_termino = 0;
		ano_inicio = 0;
		ano_termino = 0;
		valor = 0;
		participantes = new ArrayList<Colaborador>();
	}
	
	// Setters:
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
	
	public void setData_inicio(int dia_inicio, int mes_inicio, int ano_inicio) {
		this.dia_inicio = dia_inicio;
		this.mes_inicio = mes_inicio;
		this.ano_inicio = ano_inicio;
	}
	
	public void setData_termino(int dia_termino, int mes_termino, int ano_termino) {
		this.dia_termino = dia_termino;
		this.mes_termino = mes_termino;
		this.ano_termino = ano_termino;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public void setParticipantes(ArrayList<Colaborador> participantes){
		this.participantes = participantes;
	}
	
	// Getters:
	public String getTitulo() {
		return titulo;
	}
	
	public String getAgencia() {
		return agencia;
	}
	
	public String getObjetivo() {
		return objetivo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public char getStatus() {
		return status;
	}
	
	public void getData_inicio() {
		System.out.println(dia_inicio + "/" + mes_inicio + "/" + ano_inicio);
	}
	
	public void getData_termino() {
		System.out.println(dia_termino + "/" + mes_termino + "/" + ano_termino);
	}
	
	public float getValor() {
		return valor;
	}
	
	public ArrayList<Colaborador> getParticipantes() {
		return participantes;
	}
	
	/** @return 1 se projeto estiver em andamento ou concluído. 2 se alguma informação básica não tiver sido preenchida. 3 se todas as informações básicas estão preenchidas, mas ainda está em elaboração */
	public int checkStatus(){
		if(status == 'a' || status == 'c'){
			return 1;
		}
		else if (titulo.isEmpty() || objetivo.isEmpty() || agencia.isEmpty() || descricao.isEmpty() || (dia_inicio == 0) || (dia_termino == 0) || (mes_inicio == 0) || (mes_termino == 0) || (ano_inicio == 0) || (ano_termino == 0) || (valor == 0) || (participantes.isEmpty())){
			return 2;
		}
		else{
			return 3;
		}
	}
	
}
