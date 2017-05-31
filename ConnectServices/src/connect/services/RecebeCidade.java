package connect.services;

import java.lang.reflect.Field;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.healthmarketscience.jackcess.impl.RowIdImpl.Type;

import connect.Classes.Cidade;
import connect.utils.FabricaConexao;

@Path("recebeCidade")
public class RecebeCidade {
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String recebeCidade(String cidade) throws ClassNotFoundException, SQLException, ParseException {
		Gson gson = new Gson();

		Cidade cidades[] = gson.fromJson(cidade, Cidade[].class);

		List<Cidade> listCidade = new ArrayList<>(Arrays.asList(cidades));
		Connection connection = FabricaConexao.getConnection();

		for (int i = 0; i < listCidade.size(); i++) {
			System.out.println("CHEGO PORRA BIRL" + listCidade.get(i).getCodCidade());
			ResultSet resultSet = connection.createStatement()
					.executeQuery("SELECT * FROM Cidade where [Cód Cidade] = " + listCidade.get(i).getCodCidade());
			if (resultSet.next()) {
				System.out.println("NAO E NULL" + listCidade.get(i).getCodCidade());
			} else {
				Cidade cidade2 = new Cidade();
				List<String> campos = cidade2.retornaLista();
				
			}
		}

		return "SUCESSO";

	}

}
