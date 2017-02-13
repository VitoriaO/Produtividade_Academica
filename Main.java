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
			System.out.println("Escolha qual informação deseja adicionar ao projeto \"" + proj.getTitulo() + "\":");
			System.out.println("1. Data de início");
			System.out.println("2. Data de Término");
			System.out.println("3. Agência financiadora");
			System.out.println("4. Valor financiado");
			System.out.println("5. Objetivo");
			System.out.println("6. Descrição");
			System.out.println("7. Participantes");
			
			i = entrada.nextInt();
			
			switch(i){
			case 1:
				System.out.println("Digite o dia de início do projeto:");
				dia = entrada.nextInt();
				System.out.println("Digite o mês de início do projeto:");
				mes = entrada.nextInt();
				System.out.println("Digite o ano de início do projeto:");
				ano = entrada.nextInt();
				proj.setData_inicio(dia, mes, ano);
				System.out.print("Data de início salva: ");
				proj.getData_inicio();
				break;
				
			case 2:
				System.out.println("Digite o dia do término do projeto:");
				dia = entrada.nextInt();
				System.out.println("Digite o mês do término do projeto:");
				mes = entrada.nextInt();
				System.out.println("Digite o ano do término do projeto:");
				ano = entrada.nextInt();
				proj.setData_termino(dia, mes, ano);
				System.out.print("Data de término salva: ");
				proj.getData_termino();
				break;
				
			case 3:
				System.out.println("Digite o nome da agência financiadora do projeto:");
				proj.setAgencia(entradast.nextLine());
				System.out.println("Informação salva: \n" + "Agência financiadora: " + proj.getAgencia());
				break;
				
			case 4:
				System.out.println("Digite o custo do projeto: ");
				proj.setValor(entrada.nextFloat());
				System.out.println("Informação salva: \n" + "Valor financiado: " + proj.getValor());
				break;
				
			case 5:
				System.out.println("Qual o objetivo do projeto?");
				proj.setObjetivo(entradast.nextLine());
				System.out.println("Informação salva: \n" + "Objetivo: " + proj.getObjetivo());
				break;
				
			case 6:
				System.out.println("Digite uma pequena descrição do projeto:");
				proj.setDescricao(entradast.nextLine());
				System.out.println("Informação salva: \n" + "Descrição: " + proj.getDescricao());
				break;
			case 7:
				ArrayList<Colaborador> colabs = new ArrayList<Colaborador>();
				Colaborador novoc;
				System.out.println("O colaborador a ser adicionado é professor, aluno, ou pesquisador?");
				aux = entradast.nextLine();
				
				if(aux.equals("professor") || aux.equals("Professor")){
					novoc = new Professor();
				}
				else if(aux.equals("aluno") || aux.equals("Aluno")){
					novoc = new Aluno();
					System.out.println("Aluno de? (Graduação/Mestrado/Doutorado)");
					aux = entradast.nextLine();
					if(aux.equals("graduação") || aux.equals("Graduação")){
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
				System.out.println("opção Inválida. Por favor, tente novamente com uma opção válida.");
			}
			
			System.out.println("Deseja adicionar alguma nova informação? (sim/não)");
			s = entradast.nextLine();
			if(s.equals("não") || s.equals("Não")){
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
			System.out.println("Escolha a opção desejada:");
			System.out.println("1. Adicionar um novo projeto");
			System.out.println("2. Editar informações de um projeto");
			System.out.println("2. Inserir informações sobre a produção acadêmica");
			System.out.println("3. Consultar informações de um colaborador");
			System.out.println("4. Consultar informações de um projeto");
			System.out.println("5. Relatório da produção do laboratório");
			
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
				System.out.println("Opção Inválida. Por favor, tente novamente.");
				
			}
			
			System.out.println("Deseja continuar? (sim/não)");
			sn = entradast.nextLine();
			if(sn.equals("não") || sn.equals("Não")){
				opcao = 0;
			}
		}while(opcao != 0);
		
		entrada.close();
	}
}
