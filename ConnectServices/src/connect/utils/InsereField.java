package connect.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsereField {

	public Object retornaField(Field field, Object obj) {
		try {
			String primeiro = field.getName().substring(0, 1);
			String nomeCampo = field.getName().substring(1, field.getName().length());
			Object obj1 = Class.forName(obj.getClass().toString().replaceAll("class ", "")).newInstance();
			obj1 = obj;
			Method method = obj.getClass().getMethod("get" + primeiro.toUpperCase() + nomeCampo, null);
			Object object = method.invoke(obj1, null);

			return object;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return field;
	}

	public Object insereField(Field field, Object objetoRecebido, Object recebido) {
		try {
			String primeiro = field.getName().substring(0, 1);
			String nomeCampo = field.getName().substring(1, field.getName().length());

			Object objetoInstanciado = Class.forName(objetoRecebido.getClass().toString().replaceAll("class ", ""))
					.newInstance();
			objetoInstanciado = objetoRecebido;
			Class[] cArg = new Class[1];
			if (recebido != null) {
				if (field.getType().getSimpleName().toUpperCase().equals("STRING")) {
					cArg[0] = String.class;
					Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
							cArg);
					objetoInstanciado = method.invoke(objetoInstanciado, recebido.toString());
				} else if (field.getType().getSimpleName().toUpperCase().equals("LONG")) {
					cArg[0] = Long.class;
					Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
							cArg);
					objetoInstanciado = method.invoke(objetoInstanciado, Long.parseLong(recebido.toString()));
				} else if (field.getType().getSimpleName().toUpperCase().equals("DOUBLE")) {
					cArg[0] = Double.class;
					Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
							cArg);
					objetoInstanciado = method.invoke(objetoInstanciado, Double.parseDouble(recebido.toString()));
				} else if (field.getType().getSimpleName().toUpperCase().equals("BOOLEAN")) {
					cArg[0] = Boolean.class;
					Method method = objetoInstanciado.getClass().getMethod("set" + primeiro.toUpperCase() + nomeCampo,
							cArg);
					objetoInstanciado = method.invoke(objetoInstanciado, Boolean.parseBoolean(recebido.toString()));
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objetoRecebido;
	}
	
	
	
	public List<Field> retornaArrayCampos(Object objeto) {
		List<Field> listaCampos = new ArrayList<>(); 
		try {
			
			Field[] field = objeto.getClass().getDeclaredFields();
			List<Field> listCampos = new ArrayList<>(Arrays.asList(field));	
			
			for (int i = 0; i < listCampos.size(); i++) {
				String primeiro = listCampos.get(i).getName().substring(0, 1);
				String nomeCampo = listCampos.get(i).getName().substring(1, listCampos.get(i).getName().length());
				Object obj1 = Class.forName(objeto.getClass().toString().replaceAll("class ", "")).newInstance();
				obj1 = objeto;
				Method method = objeto.getClass().getMethod("get" + primeiro.toUpperCase() + nomeCampo, null);
				Object object = method.invoke(obj1, null);
				
				if (object != null){
					listaCampos.add(listCampos.get(i));
				}
				
			}
			
			return listaCampos;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCampos;
	}

	
	public List<String> retornaArrayDados(Object objeto, List<Field> campos) {
		List<String> dados = new ArrayList<>(); 
		try {
			
			for (int i = 0; i < campos.size(); i++) {
				String primeiro = campos.get(i).getName().substring(0, 1);
				String nomeCampo = campos.get(i).getName().substring(1, campos.get(i).getName().length());
				Object obj1 = Class.forName(objeto.getClass().toString().replaceAll("class ", "")).newInstance();
				obj1 = objeto;
				Method method = objeto.getClass().getMethod("get" + primeiro.toUpperCase() + nomeCampo, null);
				Object object = method.invoke(obj1, null);
				
				if (object != null){
					dados.add(object.toString());
				}
				
			}
			
			return dados;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dados;
	}
}
