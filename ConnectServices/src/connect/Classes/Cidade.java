package connect.Classes;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cidade {
    private Long codcidade;
    private String nomecidade;
    private String uf;
    private String codnacionaluf;
    private String codnacionalcidade;
    private String pais;
    private String codnacionalpais;
    private String cep;
    
    public Long getCodcidade() {
		return codcidade;
	}
	public void setCodcidade(Long codcidade) {
		this.codcidade = codcidade;
	}
	public String getNomecidade() {
		return nomecidade;
	}
	public void setNomecidade(String nomecidade) {
		this.nomecidade = nomecidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCodnacionaluf() {
		return codnacionaluf;
	}
	public void setCodnacionaluf(String codnacionaluf) {
		this.codnacionaluf = codnacionaluf;
	}
	public String getCodnacionalcidade() {
		return codnacionalcidade;
	}
	public void setCodnacionalcidade(String codnacionalcidade) {
		this.codnacionalcidade = codnacionalcidade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCodnacionalpais() {
		return codnacionalpais;
	}
	public void setCodnacionalpais(String codnacionalpais) {
		this.codnacionalpais = codnacionalpais;
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

	
}
