package verificadorCpf;

import java.util.Scanner;

public class cpf {
	
	public static void main(String[] args) {
		
		//RECEBE CPF
		long cpf;
		System.out.print("Insira seu cpf (somente n�meros): ");
		Scanner scan = new Scanner(System.in);
		cpf = scan.nextLong();
		scan.close();
				
		//GERA MULTIPLICADOR PARA FUTURA SEPARA��O DOS D�GITOS
		int[] multiplicador =  new int[10];
		int count = 0;
		for (int i = 11; i >= 2; i--) {
			multiplicador[count] = i;
			count++;
		}
		
		//SEPARA OS D�GITOS
		char[] cpfDigitoChar = String.valueOf(cpf).toCharArray();
		int[] cpfDigitoInt = new int[cpfDigitoChar.length];
		for (int i = 0; i < cpfDigitoChar.length; i++) {	
			cpfDigitoInt[i] = Integer.valueOf(String.valueOf(cpfDigitoChar[i]));
		}
		
		int numRepetidos = 0;
		for (int i = 0; i < cpfDigitoInt.length - 1; i++) {			
			if (cpfDigitoInt[i] == cpfDigitoInt[i + 1]) 
				numRepetidos++;
		}		
		if (numRepetidos > 5) 
			System.out.println("CPF inv�lido. D�gitos repetidos.");
		else {						
			//VERIFICA��O - PARTE 1 
			int verificacao = 0;
			for (int i = 0; i < cpfDigitoInt.length - 2; i++) {
				verificacao += (cpfDigitoInt[i] * multiplicador[i+1]);
			}
			int resto = verificacao % 11;
			int subtracao = 11 - resto;
			int digito = subtracao;
			if (digito > 9) 
				digito = 0;			
			System.out.println("Primeiro d�gito: " + digito);
			
			if (digito != cpfDigitoInt[9]) 
				System.out.println("Primeiro d�gito inv�lido.");
			else {
				//VERIFICA��O - PARTE 2 
				int verificaoParte2 = 0;
				for (int i = 0; i < cpfDigitoInt.length - 1; i++) {
					 verificaoParte2 += (cpfDigitoInt[i] * multiplicador[i]);
				}
				int resto2 = verificaoParte2 % 11;
				int subtracao2 = 11 - resto2;
				int digito2 = subtracao2;
				if (digito2 > 9) 
					digito2 = 0;
				System.out.println("Segundo d�gito: " +digito2);
				//PRINTA RESULTADO FINAL
				if(digito2 == cpfDigitoInt[10]) {
					System.out.println("O CPF digitado � valido");
				} else {
					System.out.println("O CPF digitado � inv�lido");
				}
			}						
		}
	}
}
