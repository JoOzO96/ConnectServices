package connect.services;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import connect.Classes.ControleCodigo;
import connect.Classes.Pedido;
import connect.Classes.PedidoProduto;
import connect.utils.FabricaConexao;
import connect.utils.InsereDados;
import connect.utils.InsereField;

@Path("recebePedidoProduto")
public class RecebePedidoProduto {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String recebeCliente(String pedidoProduto) throws ClassNotFoundException, SQLException, ParseException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		InsereField insereField = new InsereField();
		PedidoProduto pedidoProdutos[] = gson.fromJson(pedidoProduto, PedidoProduto[].class);
		PedidoProduto pedidoProdutoCadastrado = new PedidoProduto();
		List<ControleCodigo> listcontroleCodigo = new ArrayList<>();
		List<PedidoProduto> listPedidoProduto = new ArrayList<>(Arrays.asList(pedidoProdutos));
		Connection connection = FabricaConexao.getConnection();

		for (int i = 0; i < listPedidoProduto.size(); i++) {
			ControleCodigo controleCodigo = new ControleCodigo();
			ResultSet resultSet = connection.createStatement()
					.executeQuery("SELECT * FROM [Pedido Produto] where [Pedido] = " + listPedidoProduto.get(i).getPedido());
			resultSet.next();
				PedidoProduto pedidoProduto1 = new PedidoProduto();
				List<Field> fields = new ArrayList<>(Arrays.asList(pedidoProduto1.getClass().getDeclaredFields()));

				for (int j = 0; j < fields.size(); j++) {
					
					
					
					Object retorno = insereField.retornaField(fields.get(j), listPedidoProduto.get(i));

					Object teste = insereField.insereField(fields.get(j), pedidoProduto1, retorno);
					pedidoProduto1 = (PedidoProduto) teste;
				}
				pedidoProduto1.setDatas(new Date());
				// GERA UM ARRAY CONTENDO O NOME DO CAMPO E OUTRO ARRAY COM O VALOR NA MESMA POSIÇÃO
				
				List<Field> campos = insereField.retornaArrayCampos(pedidoProduto1);
				List<String> dados = insereField.retornaArrayDados(pedidoProduto1, campos);

				InsereDados insereDados = new InsereDados();
				String insert = insereDados.retornaInsert(campos, dados, "[Pedido Produto]");
				insert = insert.replace("codproduto", "[Cód Produto]");
				insert = insert.replace("codpedido", "[Cód Pedido]");
				insert = insert.replace("valorunitario", "[Valor Unitário]");
				insert = insert.replace("valortotal", "[Valor Total]");
				insert = insert.replace("codmecanico", "[Cód Mecanico]");
				connection.createStatement().executeUpdate(insert);
				
//			resultSet = connection.createStatement().executeQuery("SELECT TOP 1 * FROM Pedido order by [Pedido] desc");
//			if (resultSet.next()) {
//				controleCodigo.setCodigoAndroid(pedidoProduto1.getPedido());
//				controleCodigo.setCodigoBanco(resultSet.getLong("Pedido"));
//				listcontroleCodigo.add(controleCodigo);
//			}
		}
		if (listcontroleCodigo.size() > 0){
			return gson.toJson(listcontroleCodigo);
		}else{
			return "nada";
		}
	}

}
