package Unidade3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AcessoBD {
	
	private static final String url = "jdbc:mysql://localhost:3306/cursojava8";
	private static final String user = "root";
	private static final String pwd = "naotemsenha";
	Connection conexao;
	
	public static Connection getConnection() throws SQLException{
		
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			getConnection().close();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void ConsultarClientes() throws SQLException{
		String sql = String.format("SELECT * FROM CLIENTES");
		Statement st = getConnection().createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			String cpf = rs.getString(2);
			String nome = rs.getString(3);
			int idade = rs.getInt(4);
			JOptionPane.showMessageDialog(null, "CPF: " + cpf +  ", Nome: " + nome + " , Idade: " + idade,
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);						
		}
		
		
	}
	
	public static void insertData() throws SQLException{
		String cpf = "33322244456";
		String nome = "João Silva";
		int idade = 25;
		String sql = String.format("INSERT INTO CLIENTES(cpf, nome, idade) VALUES('%s', '%s', %d)", 
				                   cpf, nome, idade);
		Statement st = getConnection().createStatement();
		st.execute(sql);
	}
	
	public static void MostrarMetaDataInfo() throws SQLException{
		DatabaseMetaData data = getConnection().getMetaData();
		String fabricanteBD = data.getDatabaseProductName();
		String versaoBD = data.getDatabaseProductVersion();
		JOptionPane.showMessageDialog(null, "Fabricante: " + fabricanteBD + "\n" +
		                              "Versão do Banco de Dados: " + versaoBD,
		                              "Informação", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void closeConnection() throws SQLException{
		getConnection().close();
	}

	public static void main(String[] args) {
		
		try {
			getConnection();
			MostrarMetaDataInfo();
	        ConsultarClientes();
	        insertData();
	        ConsultarClientes();
	        closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}        
	}

}
