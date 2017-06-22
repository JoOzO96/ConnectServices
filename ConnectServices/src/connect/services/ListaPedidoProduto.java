package connect.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import connect.Classes.PedidoProduto;
import connect.Classes.Produto;
import connect.utils.FabricaConexao;

@Path("listaPedidoProduto")
public class ListaPedidoProduto {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException {
	
		Connection connection = FabricaConexao.getConnection();
		List<String> linha = new ArrayList<>();
		List<PedidoProduto> linhas = new ArrayList<>();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM [Pedido Produto]");
		for (int i = 0; resultSet.next(); i++) {
			PedidoProduto pedidoProduto = new PedidoProduto();
//			pedidoProduto.setPedidoProduto(resultSet.getString(1));
			linhas.add(pedidoProduto);
		}
		
		return new Gson().toJson(linhas);
		
	}
	
}
