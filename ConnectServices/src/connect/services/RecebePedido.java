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
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;

import connect.Classes.Cidade;
import connect.Classes.Cliente;
import connect.Classes.ControleCodigo;
import connect.Classes.Pedido;
import connect.utils.FabricaConexao;
import connect.utils.InsereDados;
import connect.utils.InsereField;

@Path("recebePedido")
public class RecebePedido {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String recebeCliente(String pedido) throws ClassNotFoundException, SQLException, ParseException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		InsereField insereField = new InsereField();
		Pedido pedidos[] = gson.fromJson(pedido, Pedido[].class);
		Pedido pedidoCadastrado = new Pedido();
		List<ControleCodigo> listcontroleCodigo = new ArrayList<>();
		List<Pedido> listPedido = new ArrayList<>(Arrays.asList(pedidos));
		Connection connection = FabricaConexao.getConnection();

		for (int i = 0; i < listPedido.size(); i++) {
			ControleCodigo controleCodigo = new ControleCodigo();
			ResultSet resultSet = connection.createStatement()
					.executeQuery("SELECT * FROM Pedido where [Pedido] = " + listPedido.get(i).getPedido());
			resultSet.next();
				Pedido pedido1 = new Pedido();
				List<Field> fields = new ArrayList<>(Arrays.asList(pedido1.getClass().getDeclaredFields()));

				for (int j = 0; j < fields.size(); j++) {
					
					
					
					Object retorno = insereField.retornaField(fields.get(j), listPedido.get(i));

					Object teste = insereField.insereField(fields.get(j), pedido1, retorno);
					pedido1 = (Pedido) teste;
				}
				
				// GERA UM ARRAY CONTENDO O NOME DO CAMPO E OUTRO ARRAY COM O VALOR NA MESMA POSIÇÃO
				
				List<Field> campos = insereField.retornaArrayCampos(pedido1);
				List<String> dados = insereField.retornaArrayDados(pedido1, campos);

				InsereDados insereDados = new InsereDados();
				String insert = insereDados.retornaInsert(campos, dados, "Pedido");
				insert = insert.replaceAll("codcliente", "[Cód cliente]");
				insert = insert.replaceAll("codvendedor", "[Cód Vendedor]");
				insert = insert.replaceAll("valortotal", "[Valor Total]");
				insert = insert.replaceAll("simnao", "[Sim/Não]");
				System.out.println(insert);
				connection.createStatement().executeUpdate(insert);
				
				resultSet = connection.createStatement()
						.executeQuery("SELECT TOP 1 * FROM Cliente order by [Código] desc");
				if (resultSet.next()){
					if (resultSet.getLong("Código")!= pedido1.getPedido()){
						controleCodigo.setCodigoAndroid(pedido1.getPedido());
						controleCodigo.setCodigoBanco(resultSet.getLong("Pedido"));
						listcontroleCodigo.add(controleCodigo);
					}
				}
			}
		
		if (listcontroleCodigo.size() > 0){
			return gson.toJson(listcontroleCodigo);
		}else{
			return "";
		}
	}

}
