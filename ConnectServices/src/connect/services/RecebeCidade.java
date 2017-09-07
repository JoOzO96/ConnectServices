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

import connect.Classes.Cidade;
import connect.Classes.ControleCodigo;
import connect.utils.FabricaConexao;
import connect.utils.InsereDados;
import connect.utils.InsereField;

@Path("recebeCidade")
public class RecebeCidade {

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String recebeCidade(String cidade) throws ClassNotFoundException, SQLException, ParseException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		InsereField insereField = new InsereField();
		Cidade cidades[] = gson.fromJson(cidade, Cidade[].class);
		Cidade cidadeCadastrada = new Cidade();
		List<ControleCodigo> listcontroleCodigo = new ArrayList<>();
		
		List<Cidade> listCidade = new ArrayList<>(Arrays.asList(cidades));
		Connection connection = FabricaConexao.getConnection();

		for (int i = 0; i < listCidade.size(); i++) {
			ControleCodigo controleCodigo = new ControleCodigo();
			ResultSet resultSet = connection.createStatement()
					.executeQuery("SELECT * FROM Cidade where [Cód Cidade] = " + listCidade.get(i).getCodcidade());
			resultSet.next();
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
				insert = insert.replaceAll("codcidade", "[Cód Cidade]");
				insert = insert.replaceAll("nomecidade", "[Nome Cidade]");
				//System.out.println(insert);
				connection.createStatement().executeUpdate(insert);
				
				
				
				
				resultSet = connection.createStatement()
						.executeQuery("SELECT TOP 1 * FROM Cidade order by [Cód Cidade] desc");
				if (resultSet.next()){
					if (resultSet.getLong("Cód Cidade")!= cidade2.getCodcidade()){
						controleCodigo.setCodigoAndroid(cidade2.getCodcidade());
						controleCodigo.setCodigoBanco(resultSet.getLong("Cód Cidade"));
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
