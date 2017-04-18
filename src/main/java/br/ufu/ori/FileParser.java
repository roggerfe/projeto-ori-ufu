package br.ufu.ori;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufu.ori.models.Termo;

public class FileParser {

	
	public static List<Termo> toList(String path){
		
		List<Termo> vocabulario = new ArrayList<Termo>();
		
		try{
			Scanner read = new Scanner (new File(path));
			read.useDelimiter("\\s"); // TODO: LIDAR COM \n
			
			while(read.hasNext()) {
				String palavra = read.next();
				palavra = palavra.trim();
				palavra = palavra.replaceAll("\\p{Punct}+", "");
				if(palavra.length() > 3) {
					Termo t = new Termo(palavra);
					if(vocabulario.contains(t)){
						int index = vocabulario.indexOf(t);
						vocabulario.get(index).iteraQuantidade();
					}else{
						vocabulario.add(t);	
					}
					
				}
			}
		
			read.close();
		}catch(FileNotFoundException e) {
			System.err.println("Errro, arquivo " + path +" nao existe.");
			System.exit(1);
		}
		
		
		return vocabulario;
		
	}
	
}
