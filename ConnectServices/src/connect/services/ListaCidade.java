package connect.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import connect.Classes.Cidade;
import connect.utils.FabricaConexao;

@Path("listaCidade")
public class ListaCidade {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaCidade() throws ClassNotFoundException, SQLException, ParseException {

		Connection connection = FabricaConexao.getConnection();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Cidade");
		List<Cidade> linhas = new ArrayList<>();
		for (int i = 0; resultSet.next(); i++) {
			Cidade cidade = new Cidade();
			cidade.setCodcidade(resultSet.getLong("Cód Cidade"));
			cidade.setNomecidade(resultSet.getString("Nome Cidade"));
			cidade.setUf(resultSet.getString("UF"));
			cidade.setCodnacionaluf(resultSet.getString("CodNacionalUf"));
			cidade.setCodnacionalcidade(resultSet.getString("CodNacionalCidade"));
			cidade.setPais(resultSet.getString("Pais"));
			cidade.setCodnacionalpais(resultSet.getString("CodNacionalPais"));
			cidade.setCep(resultSet.getString("CEP"));
			linhas.add(cidade);
		}
		return new Gson().toJson(linhas);

	}
	
	
}
