package connect.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InsereDados {

	public String retornaInsert(List<Field> campos, List<String> dados, String tabela) {
		String insert = "";
		String camposTabela = "";
		String dadosTabela = "";
		insert = "INSERT INTO ";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		insert = insert + tabela;
		for (int i = 0; i < campos.size(); i++) {
			camposTabela += campos.get(i).getName();

			if (i != campos.size() - 1) {
				camposTabela += ",";
			}
		}
		for (int i = 0; i < dados.size(); i++) {
			if (campos.get(i).getType().toString().replaceAll("class java.util.", "").toUpperCase().equals("DATE")) {

				// String stringData = "" + dados.get(i).substring(8, 10) + "/" +
				// dados.get(i).substring(4, 7) + "/" + dados.get(i).substring(25, 29);

				String dia;
				try {
					dia = new SimpleDateFormat("yyyy-MM-dd")
							.format(new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.US).parse(dados.get(i)));

					// dadosTabela += "'";
					// dadosTabela += dia;
					// dadosTabela += "',";
					dadosTabela += "#" + dia + "#";
					if (i != dados.size() - 1) {
						dadosTabela += ",";
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				if (campos.get(i).getType().toString().replaceAll("class java.lang.", "").toUpperCase()
						.equals("STRING")) {
					dadosTabela += "'";
				}
				if (dados.get(i).equals("0.0")) {
					dadosTabela += "0";
				} else {
					dadosTabela += dados.get(i);
				}
				if (campos.get(i).getType().toString().replaceAll("class java.lang.", "").toUpperCase()
						.equals("STRING")) {
					dadosTabela += "'";
				}
				if (i != dados.size() - 1) {
					dadosTabela += ",";
				}
			}
		}
		insert = insert + "(" + camposTabela + ") VALUES (" + dadosTabela + ");";
		return insert;
	}

}
