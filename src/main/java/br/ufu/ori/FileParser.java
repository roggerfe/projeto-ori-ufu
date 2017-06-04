package br.ufu.ori;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {

	
	public static String getConteudo(String path) {
		String conteudo = "";
		try{
			Scanner read = new Scanner (new File(path), "UTF-8");
			read.useDelimiter("\\Z");
			conteudo = read.next();
			conteudo = conteudo.toLowerCase();
			conteudo = conteudo.replaceAll("\\p{Punct}+", "");
			conteudo = conteudo.replaceAll("\n", " ");
			read.close();
		}catch(FileNotFoundException e) {
			System.err.println("Errro, arquivo " + path +" nao existe.");
			System.exit(1);
		}
		
		return conteudo;
		
	}

}
