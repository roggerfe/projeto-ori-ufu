package br.ufu.ori;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.ufu.ori.models.Termo;

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
		
	/*public static void writeFile (String doc, String termo, Double tfidf, String FILENAME) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		String conteudoEscrever = "doc termo tfidf\n";
		
		for(Termo t : content){
			conteudoEscrever += t.getNome();
			conteudoEscrever += " " + t.getQuantidade() + "\n";
		}
		
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(conteudoEscrever);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}*/

}
