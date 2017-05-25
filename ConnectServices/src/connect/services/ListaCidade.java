package connect.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

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
			cidade.setCodCidade(resultSet.getLong("Cód Cidade"));
			cidade.setNomeCidade(resultSet.getString("Nome Cidade"));
			cidade.setUF(resultSet.getString("UF"));
			cidade.setCodNacionalUf(resultSet.getString("CodNacionalUf"));
			cidade.setCodNacionalCidade(resultSet.getString("CodNacionalCidade"));
			cidade.setPais(resultSet.getString("Pais"));
			cidade.setCodNacionalPais(resultSet.getString("CodNacionalPais"));
			cidade.setCep(resultSet.getString("CEP"));
			linhas.add(cidade);
		}
		return new Gson().toJson(linhas);

	}
}
