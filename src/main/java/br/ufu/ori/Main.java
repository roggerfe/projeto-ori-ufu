package br.ufu.ori;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
	
	public static void main(String[] args) {
	
		if (args.length == 0) {
			System.err.println("Passar como parametro caminho completo do arquivo de entrada.");
			System.exit(1);
		}		
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());

		
		String aaaaaaaaaa = args[0].substring(0, args[0].lastIndexOf("/"));
		System.out.println(aaaaaaaaaa + "/" + args[0].substring(args[0].lastIndexOf("/")+1, args[0].length()).replaceAll(".txt", "") + "_" + timeStamp + ".txt");
		//System.out.println(FileParser.toList(args[0]).toString());
		
	}
}