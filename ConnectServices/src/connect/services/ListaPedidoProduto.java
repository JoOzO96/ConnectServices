package connect.services;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import connect.Classes.PedidoProduto;
import connect.utils.FabricaConexao;
import connect.utils.InsereField;

@Path("listaPedidoProduto")
public class ListaPedidoProduto {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException {

		Connection connection = FabricaConexao.getConnection();
		List<String> linha = new ArrayList<>();
		List<PedidoProduto> linhas = new ArrayList<>();
		InsereField insereField = new InsereField();
		List<Field> fieldClasse = new ArrayList<>(Arrays.asList(PedidoProduto.class.getDeclaredFields()));
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM [Pedido Produto]");
		for (int i = 0; resultSet.next(); i++) {
			PedidoProduto pedidoProduto = new PedidoProduto();
			for (int j = 0; fieldClasse.size() != j; j++) {
				String nomeCampo = fieldClasse.get(j).getName();
				if (nomeCampo.equals("codproduto")) {
					nomeCampo = "Cód Produto";
				} else if (nomeCampo.equals("codpedido")) {
					nomeCampo = "Cód Pedido";
				} else if (nomeCampo.equals("valorunitario")) {
					nomeCampo = "Valor Unitário";
				} else if (nomeCampo.equals("valortotal")) {
					nomeCampo = "Valor Total";
				} else if (nomeCampo.equals("codmecanico")) {
					nomeCampo = "Cód Mecanico";
				}
				
				Object pedidoProdutoRetorno = null;
				if (nomeCampo.toLowerCase().contains("data")) {
					if (resultSet.getDate(nomeCampo) != null) {
						pedidoProdutoRetorno = insereField.insereField(fieldClasse.get(j), pedidoProduto,
								resultSet.getDate(nomeCampo));
						pedidoProduto = (PedidoProduto) pedidoProdutoRetorno;
					}
				} else {
					if (nomeCampo.equals("itensPedido")) {

					} else if (resultSet.getString(nomeCampo) != null) {
						pedidoProdutoRetorno = insereField.insereField(fieldClasse.get(j), pedidoProduto,
								resultSet.getString(nomeCampo));
						pedidoProduto = (PedidoProduto) pedidoProdutoRetorno;
					}
				}
				
			}
			linhas.add(pedidoProduto);
		}

		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(linhas);
	}

}
