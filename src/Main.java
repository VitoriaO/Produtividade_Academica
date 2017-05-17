import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

public class Main {
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		Scanner ent_string = new Scanner(System.in);
		Laboratorio lab = new Laboratorio();
		String aux;
		int opcao;
		
		do{
			System.out.println("Menu\nEscolha a op��o desejada:");
			System.out.println("1. Adicionar um novo projeto");
			System.out.println("2. Editar informa��es de um projeto");
			System.out.println("3. Inserir informa��es sobre a produ��o acad�mica");
			System.out.println("4. Consultar informa��es de um colaborador");
			System.out.println("5. Consultar informa��es de um projeto");
			System.out.println("6. Relat�rio da produ��o do laborat�rio");
			try{
				opcao = entrada.nextInt();
				
				switch(opcao){
				case 1:
					lab.addproj(entrada, ent_string);
					break;
				
				case 2:
					lab.editarProjeto(entrada, ent_string);
					break;
					
				case 3:
					lab.addProducao(entrada, ent_string);
					break;
					
				case 4:
					lab.consultarColaborador(entrada);
					break;
					
				case 5:
					lab.consultarProjeto(entrada);
					break;
					
				case 6:
					lab.relatorio();
					break;
				
				default:
					System.err.println("Op��o Inv�lida. Por favor, tente novamente.");
					
				}
				
				System.out.println("Deseja voltar ao menu? (Sim/N�o)");
				aux = ent_string.nextLine();
				if(aux.equals("n�o") || aux.equals("N�o")){
					System.out.println("Obrigada por usar nosso sistema");
					break;
				}
			}
			catch(InputMismatchException e){
				System.err.println("Entrada n�o � um inteiro. Voltando para o menu...");
				entrada.nextLine();
			}
			catch(IndexOutOfBoundsException e){
				System.err.println("�ndice fora dos limites do array. Voltando para o menu...");
			}
		}while(true);
		
		entrada.close();
		ent_string.close();
	}
}
