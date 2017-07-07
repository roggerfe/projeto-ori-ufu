package br.ufu.ori;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import br.ufu.ori.utils.DB;

public class Menu {

    public static void menu(){
        System.out.println("\tGerenciar Documentos");
        System.out.println("0. Finalizar Execucao");
        System.out.println("1. Processar Documentos");
        System.out.println("2. Exibir Pesos TFIDF");
        System.out.println("3. Similaridade Vetorial - query");
        System.out.println("4. Similaridade Probabilistico - query");
        System.out.println("Opcao:");
    }

    public static void inserirDocumentos(){
        System.out.println("Inserir Documentos");
    }
    
    //Exibe pesos TF_ID
    public static void exibirPesos(){
        System.out.println("Exibir Pesos");
      		try {
				TfIdf.exibirPesos();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
    }
    
  //Escrever Arquivo
    public static void escreverArquivo(){
        System.out.println("Escrever Arquivo");
    }
    
    
    public static void consultarVetorial(Scanner entrada) throws IOException, SQLException{
        System.out.println("----SIMILARIDADE QUERY--------");
        
        System.out.println("Digite o nome da query desejada");
        entrada = new Scanner(System.in);        
        //entrada.next();
        String q = entrada.nextLine();
        
        
        q = q.trim();
        System.out.println("Query inserir " + q);
        DB.query("select fn_inserir_query('"+q+"');");

        Similaridade.exibirSimilaridadeVetorial(q);
        
        
    }
    

    public static void consultarProbabilistico(Scanner entrada) throws IOException, SQLException{
        System.out.println("----SIMILARIDADE QUERY--------");
        
        System.out.println("Digite o nome da query desejada");
        entrada = new Scanner(System.in);        
        String q = entrada.nextLine();
        
        q = q.trim();
        DB.query("select fn_inserir_query('"+q+"');");

        Similaridade.exibirSimilaridadeProbabilistico(q);
        
    }

    
    
}
