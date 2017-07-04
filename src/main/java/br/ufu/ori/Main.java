package br.ufu.ori;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.ufu.ori.models.Documento;
import br.ufu.ori.utils.DB;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {

		int opcao;
		Scanner entrada = new Scanner(System.in);


		if (args.length != 2) {
			System.err.println("Passar como parametro o arquivo da colecao e o arquivo contendo os docts selecionados.");
			System.exit(1);
		}
		
		
		DB.queryArquivo("limpar.sql"); // limpa dados existentes

		
		do {
			String colecao = args[0];
			String docsProcessar = args[1];
			Menu.menu();
			opcao = entrada.nextInt();

			switch (opcao) {
			case 1:
				Menu.inserirDocumentos();
				
				List<Integer> docs = FileParser.getDocumentosSelecionados(docsProcessar);
				
				System.out.println(docs.toString());
				
				List<Documento> documentos = FileParser.processarColecao(colecao, docs);
				
				for(Documento d : documentos) {
					//System.out.println("-------conteudo");
					//System.out.println(d.getConteudo());
					//System.out.println(colecao+"_"+d.getId());
					DB.query("select fn_inserir_doc('"+colecao+"_"+d.getId()+"', '"+d.getConteudo()+"')");
				}
				
				
				break;

			case 2:
				Menu.exibirPesos();
				break;

			case 3:
				Menu.consultarVetorial(entrada);
				break;

			case 4:
				Menu.consultarProbabilistico(entrada);
				break;

			default:
				System.out.println("FINALIZANDO EXECUCAO");
			}
		} while (opcao != 0);


	}
}