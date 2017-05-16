package br.ufu.ori.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.util.PSQLException;

public class DB {
	
	public static DB db;
	
	public static final String USERNAME = "usr_ori";
	public static final String DBNAME = "db_ori";
	public static final String PORT = "5433";
	public static final String HOST = "localhost";
	public static final String DIRETORIO_CONSULTAS = "/src/main/resources/consultas/";
	public static final String PASSWORD = "usr_or1";
	
	private static Connection con;
	
	public static DB getInstance() {
		if(db== null)
			db = new DB();
		return db;
	}
	
	private DB() {

	}
	
	public static ResultSet query(String consulta) throws IOException, SQLException {
		DB.getInstance();
		String query = consulta;

		try {
			con = connectToDatabase(USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		con.close();
		
		return rs;
	}
	
	public static void close() throws SQLException {
		con.close();
	}
	
	public static ResultSet queryArquivo(String arquivoQuery) throws IOException, SQLException {
		DB.getInstance();
		String query = readFile(System.getProperty("user.dir") + DIRETORIO_CONSULTAS + arquivoQuery);
		
		try {
			con = connectToDatabase(USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(query);
		}catch(PSQLException e ) {};
		
		con.close();
		return rs;
	}
	
	private static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	public static Connection connectToDatabase(String username, String password) throws SQLException {
		Connection con = DriverManager.getConnection(
	    		"jdbc:postgresql://" + HOST + ":" + PORT + "/" + DBNAME,
	                         username,
	                         password);	
		return con;
	}
	
	
	public static void main(String[] args) throws SQLException, IOException {
		
		query("teste.sql");
	}
	
}
