package br.ufu.ori;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufu.ori.utils.DB;

public class TfIdf {

	
	/**
	 * Exibe pesos - letra a do projeto
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void exibirPesos() throws IOException, SQLException {
		
		ResultSet resultado = DB.query("select * from tfidf_documento");
			
		System.out.println("--------Pesos TFIDF--------");
		while(resultado.next()){
			System.out.println("Documento: " + resultado.getString("doc"));
			System.out.println("Termo: " + resultado.getString("termo"));
			System.out.println("Peso TFIDF: " + resultado.getDouble("tfidf"));
			System.out.println("************************************************");
			
			
		}
	}
	
	
	public static void escreverArquivo(String path) throws IOException, SQLException {
		ResultSet resultado = DB.query("select * from tfidf_documento");
		
		String escrever = "Doc termo tfidf\n";
		
		while(resultado.next()){
			escrever += resultado.getString("doc");
			escrever += " " + resultado.getString("termo");
			escrever += " " + String.valueOf(resultado.getDouble("tfidf"));
			escrever += "\n";
		}
		Files.write(Paths.get(path), escrever.getBytes());
	}
	
}
