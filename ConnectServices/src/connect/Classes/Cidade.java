package connect.Classes;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cidade {
    private Long codCidade;
    private String nomeCidade;
    private String UF;
    private String codNacionalUf;
    private String codNacionalCidade;
    private String pais;
    private String codNacionalPais;
    private String cep;
	public Long getCodCidade() {
		return codCidade;
	}
	public void setCodCidade(Long codCidade) {
		this.codCidade = codCidade;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getCodNacionalUf() {
		return codNacionalUf;
	}
	public void setCodNacionalUf(String codNacionalUf) {
		this.codNacionalUf = codNacionalUf;
	}
	public String getCodNacionalCidade() {
		return codNacionalCidade;
	}
	public void setCodNacionalCidade(String codNacionalCidade) {
		this.codNacionalCidade = codNacionalCidade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCodNacionalPais() {
		return codNacionalPais;
	}
	public void setCodNacionalPais(String codNacionalPais) {
		this.codNacionalPais = codNacionalPais;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
    
	public List<Field> retornaLista(){
		Field[] field = Cidade.class.getDeclaredFields();
		List<Field> listCampos = new ArrayList<>(Arrays.asList(field));		
		return listCampos;
	}
	@Override
	public String toString() {
		return "Cidade [codCidade=" + codCidade + ", nomeCidade=" + nomeCidade + ", UF=" + UF + ", codNacionalUf="
				+ codNacionalUf + ", codNacionalCidade=" + codNacionalCidade + ", pais=" + pais + ", codNacionalPais="
				+ codNacionalPais + ", cep=" + cep + "]";
	}   
	
}
