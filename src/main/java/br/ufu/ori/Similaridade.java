package br.ufu.ori;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufu.ori.utils.DB;

public class Similaridade {
	
	
	public static void exibirSimilaridade(String query) throws IOException, SQLException {
		ResultSet resultado = DB.query("select consulta, doc, sim from similaridade order by sim desc;");
		
					
		System.out.println("--------Similaridades--------");
		while(resultado.next()){
			System.out.println("Consulta: " + resultado.getString("consulta"));
			System.out.println("Documento: " + resultado.getString("doc"));
			System.out.println("Similaridade: " + resultado.getDouble("sim"));
			System.out.println("************************************************");
				
		}
	}

}
