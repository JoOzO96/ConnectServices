package connect.services;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import connect.Classes.Mac;
import connect.utils.FabricaConexao;
import connect.utils.InsereDados;

@Path("retornaMac/{macAddress}")
public class ListaMac {	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaMac(@PathParam("macAddress") String macAddress) throws ClassNotFoundException, SQLException, ParseException {
		Gson gson = new Gson();
		Connection connection = FabricaConexao.getConnection();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM ListaMac WHERE Mac = '" + macAddress + "'");
		List<Field> campos = new ArrayList<>(Arrays.asList(Mac.class.getDeclaredFields()));
		for (int i = 0; resultSet.next(); i++) {
			System.out.println("ta aqui birl");
			return gson.toJson(resultSet.getString("ip"));
		}
		resultSet = connection.createStatement().executeQuery("SELECT * FROM Mac WHERE Mac = '" + macAddress + "'");
		if (!resultSet.next()) {
			connection.createStatement().executeUpdate("INSERT INTO MAC(mac) values ('" + macAddress + "')");
			return gson.toJson(null);	
		}
		
		return gson.toJson(null);
	}
}
