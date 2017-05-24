package connect.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

	private static Connection fabrica;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
			if (fabrica == null) {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				String teste = "jdbc:ucanaccess://C://CGeral//JOSECONNECT.accdb";
				fabrica = DriverManager.getConnection(teste,"","");
				//comando = fabrica.createStatement();
			}
			return fabrica;
	}	

}
