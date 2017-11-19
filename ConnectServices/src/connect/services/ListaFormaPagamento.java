package connect.services;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import connect.Classes.FormaPagamento;
import connect.utils.FabricaConexao;
import connect.utils.InsereField;

@Path("listaFormaPagamento")
public class ListaFormaPagamento {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException, ParseException {

		Connection connection = FabricaConexao.getConnection();
		List<FormaPagamento> linhas = new ArrayList<>();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Pgto");
		InsereField insereField = new InsereField();
		List<Field> fieldClasse = new ArrayList<>(Arrays.asList(FormaPagamento.class.getDeclaredFields()));
		for (int i = 0; resultSet.next(); i++) {
			FormaPagamento formaPagamento = new FormaPagamento();

			for (int j = 0; fieldClasse.size() != j; j++) {
				String nomeCampo = fieldClasse.get(j).getName();
				if (nomeCampo.equals("codigo")) {
					nomeCampo = "Código";
				}
				Object formaPagamentoRetorno = null;

				if (resultSet.getString(nomeCampo) != null) {
					formaPagamentoRetorno = insereField.insereField(fieldClasse.get(j), formaPagamento,
							resultSet.getString(nomeCampo));
					formaPagamento = (FormaPagamento) formaPagamentoRetorno;
				}

			}
			linhas.add(formaPagamento);
		}
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(linhas);

	}

}
