package connect.utils;

import java.lang.reflect.Field;
import java.util.List;

public class InsereDados {
	
	public String retornaInsert(List<Field> campos, List<String> dados, String tabela){
		String insert = "";
		String camposTabela = "";
		String dadosTabela = "";
		insert =  "INSERT INTO ";
		insert = insert + tabela;
		for (int i = 0; i < campos.size(); i++) {
			camposTabela += campos.get(i).getName();
			if (i != campos.size() -1){
				camposTabela += ",";
			}
		}
		for (int i = 0; i < dados.size(); i++) {
			if (campos.get(i).getType().toString().replaceAll("class java.lang.", "").toUpperCase().equals("STRING")){
				dadosTabela +="'";
			}
			dadosTabela += dados.get(i);
			if (campos.get(i).getType().toString().replaceAll("class java.lang.", "").toUpperCase().equals("STRING")){
				dadosTabela +="'";
			}
			if (i != dados.size() -1){
				dadosTabela += ",";
			}
		}
		insert = insert + "(" + camposTabela + ") VALUES (" + dadosTabela +");";
		return insert;
	}

}
