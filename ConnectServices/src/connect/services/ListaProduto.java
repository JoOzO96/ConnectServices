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

import connect.Classes.Produto;
import connect.utils.FabricaConexao;

@Path("listaProduto")
public class ListaProduto {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException {
	
		Connection connection = FabricaConexao.getConnection();
		List<String> linha = new ArrayList<>();
		List<Produto> linhas = new ArrayList<>();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Produto");
		for (int i = 0; resultSet.next(); i++) {
			Produto produto = new Produto();
			produto.setCodProduto(resultSet.getString(1));
			produto.setMercadoria(resultSet.getString(2));
			produto.setUnid(resultSet.getString(3));
			produto.setCodFornecedor(resultSet.getString(4));
			String teste = resultSet.getString(5);
			produto.setValorAvista(resultSet.getDouble(5));
			produto.setValorCompra(resultSet.getDouble(6));
			linhas.add(produto);
		}
		
		return new Gson().toJson(linhas);
		
	}
	
}
