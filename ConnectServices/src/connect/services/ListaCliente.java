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
import connect.utils.FabricaConexao;
import connect.utils.InsereField;

@Path("listaCliente")
public class ListaCliente {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaCliente() throws ClassNotFoundException, SQLException, ParseException {

		Connection connection = FabricaConexao.getConnection();
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		List<String> linha = new ArrayList<>();
		List<Cliente> linhas = new ArrayList<>();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Cliente");
		InsereField insereField = new InsereField();
		List<Field> fieldClasse = new ArrayList<>(Arrays.asList(Cliente.class.getDeclaredFields()));
		for (int i = 0; resultSet.next(); i++) {
			Cliente cliente = new Cliente();

			for (int j = 0; fieldClasse.size() != j; j++) {
				String nomeCampo = fieldClasse.get(j).getName();
				if (nomeCampo.equals("codigo")) {
					nomeCampo = "C�digo";
				} else if (nomeCampo.equals("nomecliente")) {
					nomeCampo = "Nome Cliente";
				} else if (nomeCampo.equals("datanasc")) {
					nomeCampo = "Data Nasc";
				} else if (nomeCampo.equals("codprofissao")) {
					nomeCampo = "C�d Profissao";
				} else if (nomeCampo.equals("codcidade")) {
					nomeCampo = "Cod Cidade";
				} else if (nomeCampo.equals("responsavel")) {
					nomeCampo = "Respons�vel";
				} else if (nomeCampo.equals("codvendedor")) {
					nomeCampo = "C�d Vendedor";
				} else if (nomeCampo.equals("conjuge")) {
					nomeCampo = "conjug�";
				} else if (nomeCampo.equals("codigopgto")) {
					nomeCampo = "C�digoPgto";
				}
				Object cidadeRetorno = null;
				if (nomeCampo.toLowerCase().contains("data")) {
					if (resultSet.getDate(nomeCampo) != null) {
						cidadeRetorno = insereField.insereField(fieldClasse.get(j), cliente,
								resultSet.getDate(nomeCampo));
						cliente = (Cliente) cidadeRetorno;
					}
				} else {
					if (resultSet.getString(nomeCampo) != null) {
						cidadeRetorno = insereField.insereField(fieldClasse.get(j), cliente,
								resultSet.getString(nomeCampo));
						cliente = (Cliente) cidadeRetorno;
					}
				}

			}

			// cliente.setCodigo(resultSet.getLong("c�digo"));
			// cliente.setNomecliente(resultSet.getString("Nome Cliente"));
			// cliente.setCpf(resultSet.getString("CPF"));
			// if (resultSet.getDate("Data Nasc") != null){
			// cliente.setDatanasc(resultSet.getDate("Data Nasc"));
			// }
			// cliente.setEndereco(resultSet.getString("Endereco"));
			// cliente.setPosicao(resultSet.getString("Posicao"));
			// cliente.setPai(resultSet.getString("Pai"));
			// cliente.setMae(resultSet.getString("Mae"));
			// cliente.setBairro(resultSet.getString("Bairro"));
			// cliente.setCep(resultSet.getString("CEP"));
			// cliente.setIdentidade(resultSet.getString("Identidade"));
			// cliente.setTrabalho(resultSet.getString("Trabalho"));
			// cliente.setTelefone(resultSet.getString("Telefone"));
			// cliente.setFonetrab(resultSet.getString("Fonetrab"));
			// cliente.setCgc(resultSet.getString("CGC"));
			// cliente.setIncest(resultSet.getString("Incest"));
			// cliente.setEnderecotrab(resultSet.getString("Enderecotrab"));
			// cliente.setCodprofissao(resultSet.getLong("C�d Profissao"));
			// cliente.setCodcidade(resultSet.getLong("Cod Cidade"));
			// cliente.setResponsavel(resultSet.getString("Respons�vel"));
			// cliente.setFone(resultSet.getString("fone"));
			// cliente.setObs(resultSet.getString("OBS"));
			// cliente.setNume(resultSet.getString("Nume"));
			// cliente.setEmail(resultSet.getString("Email"));
			// cliente.setLimitecredito(resultSet.getDouble("LimiteCredito"));
			// cliente.setPessoaauto(resultSet.getString("PessoaAuto"));
			// cliente.setPessoaauto1(resultSet.getString("PessoaAuto1"));
			// cliente.setLimitecredito1(resultSet.getDouble("LimiteCredito1"));
			// cliente.setPessoaauto2(resultSet.getString("PessoaAuto2"));
			// cliente.setLimitecredito2(resultSet.getDouble("LimiteCredito2"));
			// cliente.setLimitepessoal(resultSet.getDouble("LimitePessoal"));
			// cliente.setTipocliente(resultSet.getLong("TipoCliente"));
			// cliente.setCodvendedor(resultSet.getString("C�d Vendedor"));
			// cliente.setSimples(resultSet.getBoolean("Simples"));
			// cliente.setFisju(resultSet.getString("FisJu"));
			// cliente.setConjuge(resultSet.getString("Conjug�"));
			// cliente.setFretecli(resultSet.getString("FreteCli"));
			// cliente.setAntecipacao(resultSet.getLong("Antecipacao"));
			// cliente.setEtiquetas(resultSet.getBoolean("Etiquetas"));
			// cliente.setSistema(resultSet.getBoolean("Sistema"));
			// cliente.setVmanu(resultSet.getDouble("Vmanu"));
			// if (resultSet.getDate("DataCadastro") != null){
			// cliente.setDatacadastro(resultSet.getDate("DataCadastro"));
			// }
			// if (resultSet.getDate("DataAlteracao") != null){
			// cliente.setDatacadastro(resultSet.getDate("DataAlteracao"));
			// }
			// cliente.setRecibo(resultSet.getBoolean("Recibo"));
			// cliente.setCodigopgto(resultSet.getLong("C�digoPgto"));
			// cliente.setCodrepresentante(resultSet.getString("CodRepresentante"));
			// cliente.setLiberalimite(resultSet.getBoolean("LiberaLimite"));
			// cliente.setFantasia(resultSet.getString("Fantasia"));
			// cliente.setContatocobranca(resultSet.getString("ContatoCobranca"));
			// cliente.setInativo(resultSet.getBoolean("Inativo"));
			// cliente.setClientetipo(resultSet.getLong("ClienteTipo"));
			// cliente.setDiacobranca(resultSet.getLong("DiaCobranca"));
			// cliente.setDiaparavencimento(resultSet.getLong("DiaParaVencimento"));
			// cliente.setAntecipacao(resultSet.getLong("antecipacao"));

			linhas.add(cliente);
		}
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(linhas);

	}

}
