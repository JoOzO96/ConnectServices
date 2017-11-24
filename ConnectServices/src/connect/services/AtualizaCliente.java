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

import connect.Classes.Cliente;
import connect.Classes.ControleCodigo;
import connect.utils.FabricaConexao;
import connect.utils.InsereDados;
import connect.utils.InsereField;

@Path("recebeCliente")
public class AtualizaCliente {
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String recebeCliente(String cliente) throws ClassNotFoundException, SQLException, ParseException {
		Gson gson = new Gson();
		InsereField insereField = new InsereField();
		Cliente clientes[] = gson.fromJson(cliente, Cliente[].class);
		Cliente clienteCadastrado = new Cliente();
		List<ControleCodigo> listcontroleCodigo = new ArrayList<>();
		List<Cliente> listCliente = new ArrayList<>(Arrays.asList(clientes));
		Connection connection = FabricaConexao.getConnection();

		for (int i = 0; i < listCliente.size(); i++) {
			ControleCodigo controleCodigo = new ControleCodigo();
			ResultSet resultSet = connection.createStatement()
					.executeQuery("SELECT * FROM Cliente where [C�digo] = " + listCliente.get(i).getCodigo());
			resultSet.next();
				Cliente cliente1 = new Cliente();
				List<Field> fields = new ArrayList<>(Arrays.asList(cliente1.getClass().getDeclaredFields()));

				for (int j = 0; j < fields.size(); j++) {

					Object retorno = insereField.retornaField(fields.get(j), listCliente.get(i));

					Object teste = insereField.insereField(fields.get(j), cliente1, retorno);
					cliente1 = (Cliente) teste;
				}
				
				// GERA UM ARRAY CONTENDO O NOME DO CAMPO E OUTRO ARRAY COM O VALOR NA MESMA POSI��O
				
				List<Field> campos = insereField.retornaArrayCampos(cliente1);
				List<String> dados = insereField.retornaArrayDados(cliente1, campos);

				InsereDados insereDados = new InsereDados();
				String insert = insereDados.retornaUpdate(campos, dados, "Cliente", "[C�digo]", cliente1.getCodigo().toString());
				insert = insert.replaceAll("codigo", "[C�digo]");
				insert = insert.replaceAll("nomecliente", "[nome cliente]");
				insert = insert.replaceAll("codprofissao", "[C�d Profissao]");
				insert = insert.replaceAll("codcidade", "[Cod Cidade]");
				insert = insert.replaceAll("codvendedor", "[C�d Vendedor]");
				insert = insert.replaceAll("responsavel", "[Respons�vel]");
				insert = insert.replaceAll("conjuge", "[Conjug�]");
				insert = insert.replace("[C�digo]pgto", "[C�digoPgto]");
//				System.out.println(insert);
				connection.createStatement().executeUpdate(insert);
				
				resultSet = connection.createStatement()
						.executeQuery("SELECT TOP 1 * FROM Cliente order by [C�digo] desc");
				if (resultSet.next()){
						controleCodigo.setCodigoAndroid(cliente1.getCodigo());
						controleCodigo.setCodigoBanco(resultSet.getLong("C�digo"));
						listcontroleCodigo.add(controleCodigo);
				}
			}
		
		if (listcontroleCodigo.size() > 0){
			return gson.toJson(listcontroleCodigo);
		}else{
			return "";
		}
	}

}
