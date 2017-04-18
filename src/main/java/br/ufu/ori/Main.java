package br.ufu.ori;




public class Main {
	
	public static void main(String[] args) {
	
		if (args.length == 0) {
			System.err.println("Passar como parametro caminho completo do arquivo de entrada.");
			System.exit(1);
		}		
				
		
		System.out.println(FileParser.toList(args[0]).toString());
		
	}
}