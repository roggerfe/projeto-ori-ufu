package br.ufu.ori;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import br.ufu.ori.models.Documento;

public class FileParser {

	
	public static List<Documento> processarColecao(String path, List<Integer> processar) {
		String arqv = null;
		List<Documento> docs = new ArrayList<>();
		try {
			arqv = FileUtils.readFileToString(new File(path), "UTF-8");
			String[] infoArquivos = arqv.split("\\.I",-1);
			
			for(String infoArquivo : infoArquivos) {
				int idDocumento;
				try{
					idDocumento = Integer.valueOf(infoArquivo.substring(0, infoArquivo.indexOf("\n")).trim());
					if(processar.contains(idDocumento)){
						//System.out.println("PROCESSAR DOCUMENTO " + idDocumento);
						//System.out.println("*********");
						//System.out.println("CONTEUDO DO DOCUMENTO");
						String conteudoDoc = infoArquivo.substring(infoArquivo.indexOf(".W")+2, infoArquivo.length()).trim();
						conteudoDoc = conteudoDoc.toLowerCase();
						conteudoDoc = conteudoDoc.replaceAll("\\p{Punct}+", "");
						conteudoDoc = conteudoDoc.replaceAll("[^a-zA-Z\\s]", "");
						//System.out.println(conteudoDoc);
						docs.add(new Documento(idDocumento, conteudoDoc));
					}
				}catch(StringIndexOutOfBoundsException e){
					idDocumento=-1;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return docs;
	}
	
	/*public static String getConteudo(String path) {
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
		
	}/*
	
	/**
	 * Retorna lista com os ids dos documentos a serem processados
	 * conforme padrao de arquivo passado pelo req PROJETOP4
	 * @param path
	 * @return
	 */
	public static List<Integer> getDocumentosSelecionados(String path) {
		List<Integer> docs = new ArrayList<>();
		try{
			Scanner read = new Scanner (new File(path), "UTF-8");
			read.useDelimiter("\\n");
			while(read.hasNext()) {
				docs.add(Integer.valueOf(read.next().trim()));
			}
			read.close();
		}catch(FileNotFoundException e) {
			System.err.println("Errro, arquivo " + path +" nao existe.");
			System.exit(1);
		}
		
		return docs;
	}

}
