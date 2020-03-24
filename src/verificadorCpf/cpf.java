package verificadorCpf;

import java.util.Scanner;

public class cpf {
	
	public static void main(String[] args) {
		
		//RECEBE CPF
		long cpf;
		System.out.print("Insira seu cpf (somente números): ");
		Scanner scan = new Scanner(System.in);
		cpf = scan.nextLong();
		scan.close();
				
		//GERA MULTIPLICADOR PARA FUTURA SEPARAÇÃO DOS DÍGITOS
		int[] multiplicador =  new int[10];
		int count = 0;
		for (int i = 11; i >= 2; i--) {
			multiplicador[count] = i;
			count++;
		}
		
		//SEPARA OS DÍGITOS
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
			System.out.println("CPF inválido. Dígitos repetidos.");
		else {						
			//VERIFICAÇÃO - PARTE 1 
			int verificacao = 0;
			for (int i = 0; i < cpfDigitoInt.length - 2; i++) {
				verificacao += (cpfDigitoInt[i] * multiplicador[i+1]);
			}
			int resto = verificacao % 11;
			int subtracao = 11 - resto;
			int digito = subtracao;
			if (digito > 9) 
				digito = 0;			
			System.out.println("Primeiro dígito: " + digito);
			
			if (digito != cpfDigitoInt[9]) 
				System.out.println("Primeiro dígito inválido.");
			else {
				//VERIFICAÇÃO - PARTE 2 
				int verificaoParte2 = 0;
				for (int i = 0; i < cpfDigitoInt.length - 1; i++) {
					 verificaoParte2 += (cpfDigitoInt[i] * multiplicador[i]);
				}
				int resto2 = verificaoParte2 % 11;
				int subtracao2 = 11 - resto2;
				int digito2 = subtracao2;
				if (digito2 > 9) 
					digito2 = 0;
				System.out.println("Segundo dígito: " +digito2);
				//PRINTA RESULTADO FINAL
				if(digito2 == cpfDigitoInt[10]) {
					System.out.println("O CPF digitado é valido");
				} else {
					System.out.println("O CPF digitado é inválido");
				}
			}						
		}
	}
}
