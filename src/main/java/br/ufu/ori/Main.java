package br.ufu.ori;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import br.ufu.ori.utils.DB;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {

		int opcao;
		Scanner entrada = new Scanner(System.in);

		int qtdArquivos = args.length;

		if (qtdArquivos == 0) {
			System.err.println("Passar como parametro todos os documentos da colecao.");
			System.exit(1);
		}

		DB.queryArquivo("limpar.sql"); // limpa dados existentes

		
		do {
			Menu.menu();
			opcao = entrada.nextInt();

			switch (opcao) {
			case 1:
				Menu.inserirDocumentos();

				// Armazena conteudo dos arquivos nas tabelas termo, documento e
				// termo_documento
				for (int i = 0; i < args.length; i++) {
					String conteudo = FileParser.getConteudo(args[i]);
					System.out.println("Conteudo do arquivo " + args[i] + ": " + conteudo);
					DB.query("select fn_inserir_doc('" + args[i] + "','" + conteudo + "');");
				}
				break;

			case 2:
				Menu.exibirPesos();
				break;

			case 3:
				//Menu.escreverArquivo();
				//String timeStamp2 = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(Calendar.getInstance().getTime());
				//timeStamp = timeStamp2.replaceAll(":", "");
				//TfIdf.escreverArquivo(System.getProperty("user.dir") + "/resultado_" + timeStamp + ".txt");
				Menu.consultar(entrada);
				break;

			case 4:

				break;

			default:
				System.out.println("Opção inválida.");
			}
		} while (opcao != 0);

		// FileParser.writeFile(FileParser.toList(args[0]), caminho + "/" +
		// args[0].substring(args[0].lastIndexOf("/")+1,
		// args[0].length()).replaceAll(".txt", "") + "_" + timeStamp + ".txt");

	}
}