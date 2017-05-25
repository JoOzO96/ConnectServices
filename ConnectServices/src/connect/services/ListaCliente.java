package connect.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Path("listaCliente")
public class ListaCliente {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retornaProduto() throws ClassNotFoundException, SQLException, ParseException {

		Connection connection = FabricaConexao.getConnection();
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		List<String> linha = new ArrayList<>();
		List<Cliente> linhas = new ArrayList<>();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Cliente");
		for (int i = 0; resultSet.next(); i++) {
			Cliente cliente = new Cliente();
			cliente.setCodigo(resultSet.getLong("código"));
			cliente.setNomeCliente(resultSet.getString("Nome Cliente"));
			cliente.setCpf(resultSet.getString("CPF"));
			if (resultSet.getDate("Data Nasc") != null){
				cliente.setDataNasc(resultSet.getDate("Data Nasc"));
			}
			cliente.setEndereco(resultSet.getString("Endereco"));
			cliente.setPosicao(resultSet.getString("Posicao"));
			cliente.setPai(resultSet.getString("Pai"));
			cliente.setMae(resultSet.getString("Mae"));
			cliente.setBairro(resultSet.getString("Bairro"));
			cliente.setCep(resultSet.getString("CEP"));
			cliente.setIdentidade(resultSet.getString("Identidade"));
			cliente.setTrabalho(resultSet.getString("Trabalho"));
			cliente.setTelefone(resultSet.getString("Telefone"));
			cliente.setFonetrab(resultSet.getString("Fonetrab"));
			cliente.setCgc(resultSet.getString("CGC"));
			cliente.setIncest(resultSet.getString("Incest"));
			cliente.setEnderecotrab(resultSet.getString("Enderecotrab"));
			cliente.setCodProfissao(resultSet.getLong("Cód Profissao"));
			cliente.setCodCidade(resultSet.getLong("Cod Cidade"));
			cliente.setResponsavel(resultSet.getString("Responsável"));
			cliente.setFone(resultSet.getString("fone"));
			cliente.setObs(resultSet.getString("OBS"));
			cliente.setNume(resultSet.getString("Nume"));
			cliente.setEmail(resultSet.getString("Email"));
			cliente.setLimiteCredito(resultSet.getDouble("LimiteCredito"));
			cliente.setPessoaAuto(resultSet.getString("PessoaAuto"));
			cliente.setPessoaAuto1(resultSet.getString("PessoaAuto1"));
			cliente.setLimiteCredito1(resultSet.getDouble("LimiteCredito1"));
			cliente.setPessoaAuto2(resultSet.getString("PessoaAuto2"));
			cliente.setLimiteCredito2(resultSet.getDouble("LimiteCredito2"));
			cliente.setLimitePessoal(resultSet.getDouble("LimitePessoal"));
			cliente.setTipoCliente(resultSet.getLong("TipoCliente"));
			cliente.setCodVendedor(resultSet.getString("Cód Vendedor"));
			cliente.setSimples(resultSet.getBoolean("Simples"));
			cliente.setFisJu(resultSet.getString("FisJu"));
			cliente.setConjuge(resultSet.getString("Conjugê"));
			cliente.setFreteCli(resultSet.getString("FreteCli"));
			cliente.setAntecipacao(resultSet.getLong("Antecipacao"));
			cliente.setEtiquetas(resultSet.getBoolean("Etiquetas"));
			cliente.setSistema(resultSet.getBoolean("Sistema"));
			cliente.setVmanu(resultSet.getDouble("Vmanu"));
			if (resultSet.getDate("DataCadastro") != null){
				cliente.setDataCadastro(resultSet.getDate("DataCadastro"));
			}
			if (resultSet.getDate("DataAlteracao") != null){
				cliente.setDataCadastro(resultSet.getDate("DataAlteracao"));
			}
			cliente.setRecibo(resultSet.getBoolean("Recibo"));
			cliente.setCodigoPgto(resultSet.getLong("CódigoPgto"));
			cliente.setCodRepresentante(resultSet.getString("CodRepresentante"));
			cliente.setLiberaLimite(resultSet.getBoolean("LiberaLimite"));
			cliente.setFantasia(resultSet.getString("Fantasia"));
			cliente.setContatoCobranca(resultSet.getString("ContatoCobranca"));
			cliente.setInativo(resultSet.getBoolean("Inativo"));
			cliente.setClienteTipo(resultSet.getLong("ClienteTipo"));
			cliente.setDiaCobranca(resultSet.getLong("DiaCobranca"));
			cliente.setDiaParaVencimento(resultSet.getLong("DiaParaVencimento"));
			cliente.setAntecipacao(resultSet.getLong("antecipacao"));

			linhas.add(cliente);
		}
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		return gson.toJson(linhas);

	}

}
