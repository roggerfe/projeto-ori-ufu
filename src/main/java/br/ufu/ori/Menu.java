package br.ufu.ori;

import java.io.IOException;
import java.sql.SQLException;

public class Menu {

    public static void menu(){
        System.out.println("\tGerenciar Documentos");
        System.out.println("1. Inserir Documentos");
        System.out.println("2. Exibir Pesos");
        System.out.println("3. Consulta");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
  //Escrever Arquivo
    public static void escreverArquivo(){
        System.out.println("Escrever Arquivo");
    }
    
    
    public static void consultar(){
        System.out.println("Consultar Documentos");
    }
    
    
}
