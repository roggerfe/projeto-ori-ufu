package br.ufu.ori;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufu.ori.utils.DB;

public class Similaridade {
	
	
	public static void exibirSimilaridadeVetorial(String query) throws IOException, SQLException {
		ResultSet resultado = DB.query("select consulta, doc, sim from similaridade where consulta = '"+query+"' order by sim desc;");
			
							
		System.out.println("--------Similaridades--------");
		while(resultado.next()){
			System.out.println("Consulta: " + resultado.getString("consulta"));
			System.out.println("Documento: " + resultado.getString("doc"));
			System.out.println("Similaridade: " + resultado.getDouble("sim"));
			System.out.println("************************************************");
				
		}
	}
	
	public static void exibirSimilaridadeProbabilistico(String query) throws IOException, SQLException {
		ResultSet resultado = DB.query("select consulta, doc, simprob from similaridade_probabilistico where consulta = '"+query+"' order by simprob desc;");
		
					
		System.out.println("--------Similaridades--------");
		while(resultado.next()){
			System.out.println("Consulta: " + resultado.getString("consulta"));
			System.out.println("Documento: " + resultado.getString("doc"));
			System.out.println("Similaridade: " + resultado.getDouble("simprob"));
			System.out.println("************************************************");
				
		}
	}
	

}
