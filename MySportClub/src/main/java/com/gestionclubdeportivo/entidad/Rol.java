package com.gestionclubdeportivo.entidad;

import java.util.ArrayList;
import java.util.List;

public enum Rol {

	ADMIN,ENTRENADOR,JUGADOR;
	
	public static List<String> returnRoles(){
		List<String> lista = new ArrayList<>();
		Rol[] roles = Rol.values();
		for(int i=0;i<roles.length;i++) {
			lista.add(roles[i].toString());
		}
		return lista;
	}
}



