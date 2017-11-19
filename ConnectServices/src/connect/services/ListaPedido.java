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

import connect.Classes.Pedido;
import connect.Classes.PedidoProduto;
import connect.utils.FabricaConexao;
import connect.utils.InsereField;

@Path("listaPedido")
public class ListaPedido {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException, ParseException {

		Connection connection = FabricaConexao.getConnection();
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		List<String> linha = new ArrayList<>();
		List<Pedido> linhas = new ArrayList<>();
		InsereField insereField = new InsereField();
		List<Field> fieldClasse = new ArrayList<>(Arrays.asList(Pedido.class.getDeclaredFields()));
		List<Field> fieldClassePedidoProduto = new ArrayList<>(Arrays.asList(PedidoProduto.class.getDeclaredFields()));
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Pedido");

		for (int i = 0; resultSet.next(); i++) {
			Pedido pedido = new Pedido();
			for (int j = 0; fieldClasse.size() != j; j++) {
				String nomeCampo = fieldClasse.get(j).getName();
				if (nomeCampo.equals("codpedido")) {
					nomeCampo = "Cód Pedido";
				} else if (nomeCampo.equals("codcliente")) {
					nomeCampo = "Cód Cliente";
				} else if (nomeCampo.equals("codvendedor")) {
					nomeCampo = "Cód Vendedor";
				} else if (nomeCampo.equals("formadepagamento")) {
					nomeCampo = "Forma de Pagamento";
				} else if (nomeCampo.equals("valortotal")) {
					nomeCampo = "Valor Total";
				} else if (nomeCampo.equals("codbanco")) {
					nomeCampo = "Cód Banco";
				} else if (nomeCampo.equals("simnao")) {
					nomeCampo = "Sim/Não";
				} else if (nomeCampo.equals("codinstituicao")) {
					nomeCampo = "Cód Instituição";
				}
				Object pedidoRetorno = null;
				if (nomeCampo.toLowerCase().contains("data")) {
					if (resultSet.getDate(nomeCampo) != null) {
						pedidoRetorno = insereField.insereField(fieldClasse.get(j), pedido,
								resultSet.getDate(nomeCampo));
						pedido = (Pedido) pedidoRetorno;
					}
				} else {
					if (nomeCampo.equals("itensPedido")) {

					} else if (resultSet.getString(nomeCampo) != null) {
						pedidoRetorno = insereField.insereField(fieldClasse.get(j), pedido,
								resultSet.getString(nomeCampo));
						pedido = (Pedido) pedidoRetorno;
					}
				}
			}
			// List<PedidoProduto> listpedidoProduto = new ArrayList<>();
			//
			// ResultSet resultSetPedidoProduto = connection.createStatement()
			// .executeQuery("SELECT * FROM [Pedido Produto] where Pedido = " +
			// pedido.getPedido().toString());
			// for (int k = 0; resultSetPedidoProduto.next(); k++) {
			// PedidoProduto pedidoProduto = new PedidoProduto();
			// for (int h = 0; fieldClassePedidoProduto.size() != h; h++) {
			// String nomeCampoPedidoProduto = fieldClassePedidoProduto.get(h).getName();
			// //
			// if (nomeCampoPedidoProduto.equals("codproduto")) {
			// nomeCampoPedidoProduto = "Cód Produto";
			// } else if (nomeCampoPedidoProduto.equals("codpedido")) {
			// nomeCampoPedidoProduto = "Cód Pedido";
			// } else if (nomeCampoPedidoProduto.equals("valorunitario")) {
			// nomeCampoPedidoProduto = "Valor Unitário";
			// } else if (nomeCampoPedidoProduto.equals("valortotal")) {
			// nomeCampoPedidoProduto = "Valor Total";
			// } else if (nomeCampoPedidoProduto.equals("codmecanico")) {
			// nomeCampoPedidoProduto = "Cód Mecanico";
			// }
			// Object pedidoProdutoRetorno = null;
			// if (nomeCampoPedidoProduto.toLowerCase().contains("data")) {
			// if (resultSetPedidoProduto.getDate(nomeCampoPedidoProduto) != null) {
			// pedidoProdutoRetorno =
			// insereField.insereField(fieldClassePedidoProduto.get(h),
			// pedidoProduto, resultSetPedidoProduto.getDate(nomeCampoPedidoProduto));
			// pedidoProduto = (PedidoProduto) pedidoProdutoRetorno;
			// }
			// } else {
			// if (resultSetPedidoProduto.getString(nomeCampoPedidoProduto) != null) {
			// pedidoProdutoRetorno =
			// insereField.insereField(fieldClassePedidoProduto.get(h),
			// pedidoProduto, resultSetPedidoProduto.getString(nomeCampoPedidoProduto));
			// pedidoProduto = (PedidoProduto) pedidoProdutoRetorno;
			// }
			// }
			// }
			// listpedidoProduto.add(pedidoProduto);
			// }
			// pedido.setItensPedido(listpedidoProduto);

			linhas.add(pedido);
		}
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(linhas);

	}

}
