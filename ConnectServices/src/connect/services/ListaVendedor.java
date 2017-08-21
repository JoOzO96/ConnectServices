package connect.services;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import connect.Classes.Cliente;
import connect.Classes.Vendedor;
import connect.utils.FabricaConexao;
import connect.utils.InsereField;

@Path("listaVendedor")
public class ListaVendedor {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException, ParseException {

		Connection connection = FabricaConexao.getConnection();
		List<Vendedor> linhas = new ArrayList<>();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Vendedor");
		InsereField insereField = new InsereField();
		List<Field> fieldClasse = new ArrayList<>(Arrays.asList(Vendedor.class.getDeclaredFields()));
		for (int i = 0; resultSet.next(); i++) {
			Vendedor vendedor = new Vendedor();

			for (int j = 0; fieldClasse.size() != j; j++) {
				String nomeCampo = fieldClasse.get(j).getName();
				if (nomeCampo.equals("codvendedor")) {
					nomeCampo = "Cód Vendedor";
				} else if (nomeCampo.equals("nomevendedor")) {
					nomeCampo = "Nome Vendedor";
				}
				Object vendedorRetorno = null;

				if (resultSet.getString(nomeCampo) != null) {
					vendedorRetorno = insereField.insereField(fieldClasse.get(j), vendedor,
							resultSet.getString(nomeCampo));
					vendedor = (Vendedor) vendedorRetorno;
				}

			}
			linhas.add(vendedor);
		}
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(linhas);

	}

}
