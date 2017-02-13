import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	public static void addproj(Scanner entradast, Scanner entrada, ArrayList<Projeto> projetos){
		Projeto proj = new Projeto();
		int i, dia, mes, ano;
		String s, aux;
		
		proj.setStatus('e');
		System.out.println("Digite o nome do novo projeto:");
		proj.setTitulo(entradast.nextLine());
		
		do{
			System.out.println("Escolha qual informa��o deseja adicionar ao projeto \"" + proj.getTitulo() + "\":");
			System.out.println("1. Data de in�cio");
			System.out.println("2. Data de T�rmino");
			System.out.println("3. Ag�ncia financiadora");
			System.out.println("4. Valor financiado");
			System.out.println("5. Objetivo");
			System.out.println("6. Descri��o");
			System.out.println("7. Participantes");
			
			i = entrada.nextInt();
			
			switch(i){
			case 1:
				System.out.println("Digite o dia de in�cio do projeto:");
				dia = entrada.nextInt();
				System.out.println("Digite o m�s de in�cio do projeto:");
				mes = entrada.nextInt();
				System.out.println("Digite o ano de in�cio do projeto:");
				ano = entrada.nextInt();
				proj.setData_inicio(dia, mes, ano);
				System.out.print("Data de in�cio salva: ");
				proj.getData_inicio();
				break;
				
			case 2:
				System.out.println("Digite o dia do t�rmino do projeto:");
				dia = entrada.nextInt();
				System.out.println("Digite o m�s do t�rmino do projeto:");
				mes = entrada.nextInt();
				System.out.println("Digite o ano do t�rmino do projeto:");
				ano = entrada.nextInt();
				proj.setData_termino(dia, mes, ano);
				System.out.print("Data de t�rmino salva: ");
				proj.getData_termino();
				break;
				
			case 3:
				System.out.println("Digite o nome da ag�ncia financiadora do projeto:");
				proj.setAgencia(entradast.nextLine());
				System.out.println("Informa��o salva: \n" + "Ag�ncia financiadora: " + proj.getAgencia());
				break;
				
			case 4:
				System.out.println("Digite o custo do projeto: ");
				proj.setValor(entrada.nextFloat());
				System.out.println("Informa��o salva: \n" + "Valor financiado: " + proj.getValor());
				break;
				
			case 5:
				System.out.println("Qual o objetivo do projeto?");
				proj.setObjetivo(entradast.nextLine());
				System.out.println("Informa��o salva: \n" + "Objetivo: " + proj.getObjetivo());
				break;
				
			case 6:
				System.out.println("Digite uma pequena descri��o do projeto:");
				proj.setDescricao(entradast.nextLine());
				System.out.println("Informa��o salva: \n" + "Descri��o: " + proj.getDescricao());
				break;
			case 7:
				ArrayList<Colaborador> colabs = new ArrayList<Colaborador>();
				Colaborador novoc;
				System.out.println("O colaborador a ser adicionado � professor, aluno, ou pesquisador?");
				aux = entradast.nextLine();
				
				if(aux.equals("professor") || aux.equals("Professor")){
					novoc = new Professor();
				}
				else if(aux.equals("aluno") || aux.equals("Aluno")){
					novoc = new Aluno();
					System.out.println("Aluno de? (Gradua��o/Mestrado/Doutorado)");
					aux = entradast.nextLine();
					if(aux.equals("gradua��o") || aux.equals("Gradua��o")){
						Aluno novoc2 = (Aluno) novoc;
						novoc2.setTipo('g');
					}
					else if(aux.equals("mestrado") || aux.equals("Mestrado")){
						Aluno novoc2 = (Aluno) novoc;
						novoc2.setTipo('m');
					}
					else
					{
						Aluno novoc2 = (Aluno) novoc;
						novoc2.setTipo('d');
					}
					
				}
				else{
					novoc = new Colaborador();
				}
				
				System.out.println("Digite o nome do colaborador:");
				novoc.setNome(entradast.nextLine());
				System.out.println("Digite o email do colaborador:");
				novoc.setEmail(entradast.nextLine());
				
				colabs.add(novoc);
				
				proj.setParticipantes(colabs);
				break;
				
			default:
				System.out.println("op��o Inv�lida. Por favor, tente novamente com uma op��o v�lida.");
			}
			
			System.out.println("Deseja adicionar alguma nova informa��o? (sim/n�o)");
			s = entradast.nextLine();
			if(s.equals("n�o") || s.equals("N�o")){
				i = 0;
			}
		}while(i != 0);
		
		if(proj.checkStatus() == 3){
			proj.setStatus('a');
			System.out.println("Projeto \"" + proj.getTitulo() + "\" em andamento");
		}
		
	}
	
	public static void main(String[] args){
		Scanner entrada = new Scanner(System.in);
		Scanner entradast = new Scanner(System.in);
		ArrayList<Projeto> proj_lab = new ArrayList<>();
		String sn;
		int opcao;
		
		do{
			System.out.println("Escolha a op��o desejada:");
			System.out.println("1. Adicionar um novo projeto");
			System.out.println("2. Editar informa��es de um projeto");
			System.out.println("2. Inserir informa��es sobre a produ��o acad�mica");
			System.out.println("3. Consultar informa��es de um colaborador");
			System.out.println("4. Consultar informa��es de um projeto");
			System.out.println("5. Relat�rio da produ��o do laborat�rio");
			
			opcao = entrada.nextInt();
			
			switch(opcao){
			case 1:
				addproj(entradast, entrada, proj_lab);
				break;
			
			case 2:
				break;
				
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
				break;
				
			case 6:
				break;
			
			default:
				System.out.println("Op��o Inv�lida. Por favor, tente novamente.");
				
			}
			
			System.out.println("Deseja continuar? (sim/n�o)");
			sn = entradast.nextLine();
			if(sn.equals("n�o") || sn.equals("N�o")){
				opcao = 0;
			}
		}while(opcao != 0);
		
		entrada.close();
	}
}
