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

import connect.Classes.Produto;
import connect.utils.FabricaConexao;
import connect.utils.InsereField;

@Path("listaProduto")
public class ListaProduto {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException {

		Connection connection = FabricaConexao.getConnection();
		List<String> linha = new ArrayList<>();
		List<Produto> linhas = new ArrayList<>();
		InsereField insereField = new InsereField();
		List<Field> fieldClasse = new ArrayList<>(Arrays.asList(Produto.class.getDeclaredFields()));
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Produto");
		for (int i = 0; resultSet.next(); i++) {
			Produto produto = new Produto();
			for (int j = 0; fieldClasse.size() != j; j++) {
				String nomeCampo = fieldClasse.get(j).getName();
				if (nomeCampo.equals("codproduto")) {
					nomeCampo = "Cod Produto";
				} else if (nomeCampo.equals("codfornecedor")) {
					nomeCampo = "Cod Fornecedor";
				} else if (nomeCampo.equals("valorcompra")) {
					nomeCampo = "Valor Compra";
				} else if (nomeCampo.equals("valoravista")) {
					nomeCampo = "Valor Avista";
				} else if (nomeCampo.equals("valorprazo")) {
					nomeCampo = "Valor Prazo";
				} else if (nomeCampo.equals("codgrupo")) {
					nomeCampo = "Cod Grupo";
				} else if (nomeCampo.equals("codicms")) {
					nomeCampo = "Cod Icms";
				} else if (nomeCampo.equals("dificms")) {
					nomeCampo = "Dif ICMS";
				} else if (nomeCampo.equals("perprazo")) {
					nomeCampo = "Per Prazo";
				} else if (nomeCampo.equals("codpis")) {
					nomeCampo = "c�dPIS";
				} else if (nomeCampo.equals("codcofins")) {
					nomeCampo = "C�dCOFINS";
				} else if (nomeCampo.equals("codcofinse")) {
					nomeCampo = "c�dCOFINSE";
				} else if (nomeCampo.equals("codipi")) {
					nomeCampo = "C�dIPI";
				} else if (nomeCampo.equals("carencia")) {
					nomeCampo = "car�ncia";
				} else if (nomeCampo.equals("aplicacao")) {
					nomeCampo = "Aplica��o";
				} else if (nomeCampo.equals("concentracao")) {
					nomeCampo = "Concentra��o";
				} else if (nomeCampo.equals("formulacao")) {
					nomeCampo = "Formula��o";
				} else if (nomeCampo.equals("comentario")) {
					nomeCampo = "Coment�rio";
				} else if (nomeCampo.equals("codsubgrupo")) {
					nomeCampo = "C�dSubGrupo";
				} else if (nomeCampo.equals("maximo")) {
					nomeCampo = "m�ximo";
				} else if (nomeCampo.equals("codpise")) {
					nomeCampo = "C�dPisE";
				} else if (nomeCampo.equals("codipise")) {
					nomeCampo = "C�dIPISE";
				} else if (nomeCampo.equals("comissao")) {
					nomeCampo = "Comiss�o";
				} else if (nomeCampo.equals("maquina")) {
					nomeCampo = "m�quina";
				}

				Object produtoRetorno = null;
				if (nomeCampo.toLowerCase().contains("data")) {
					if (resultSet.getDate(nomeCampo) != null) {
						produtoRetorno = insereField.insereField(fieldClasse.get(j), produto,
								resultSet.getDate(nomeCampo));
						produto = (Produto) produtoRetorno;
					}
				} else {

					try {
						if (nomeCampo.equals("itensPedido")) {

						} else if (resultSet.getString(nomeCampo) != null) {
							produtoRetorno = insereField.insereField(fieldClasse.get(j), produto,
									resultSet.getString(nomeCampo));
							produto = (Produto) produtoRetorno;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			linhas.add(produto);
		}
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(linhas);
	}

}
