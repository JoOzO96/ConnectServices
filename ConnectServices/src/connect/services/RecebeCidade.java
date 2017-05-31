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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import connect.Classes.Cidade;
import connect.utils.FabricaConexao;
import connect.utils.InsereDados;
import connect.utils.InsereField;

@Path("recebeCidade")
public class RecebeCidade {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String recebeCidade(String cidade) throws ClassNotFoundException, SQLException, ParseException {
		Gson gson = new Gson();
		InsereField insereField = new InsereField();
		Cidade cidades[] = gson.fromJson(cidade, Cidade[].class);

		List<Cidade> listCidade = new ArrayList<>(Arrays.asList(cidades));
		Connection connection = FabricaConexao.getConnection();

		for (int i = 0; i < listCidade.size(); i++) {
			ResultSet resultSet = connection.createStatement()
					.executeQuery("SELECT * FROM Cidade where [Cód Cidade] = " + listCidade.get(i).getCodCidade());
			if (resultSet.next()) {

			} else {
				Cidade cidade2 = new Cidade();
				List<Field> fields = cidade2.retornaLista();

				for (int j = 0; j < fields.size(); j++) {

					Object retorno = insereField.retornaField(fields.get(j), listCidade.get(i));

					Object teste = insereField.insereField(fields.get(j), cidade2, retorno);
					cidade2 = (Cidade) teste;
				}
				
				// GERA UM ARRAY CONTENDO O NOME DO CAMPO E OUTRO ARRAY COM O VALOR NA MESMA POSIÇÃO
				
				List<Field> campos = insereField.retornaArrayCampos(cidade2);
				List<String> dados = insereField.retornaArrayDados(cidade2, campos);

				InsereDados insereDados = new InsereDados();
				String insert = insereDados.retornaInsert(campos, dados, "Cidade");
				insert = insert.replaceAll("codCidade", "[Cód Cidade]");
				insert = insert.replaceAll("nomeCidade", "[Nome Cidade]");
				System.out.println(insert);
				connection.createStatement().executeUpdate(insert);
			}
		}
		return "SUCESSO";
	}

}
