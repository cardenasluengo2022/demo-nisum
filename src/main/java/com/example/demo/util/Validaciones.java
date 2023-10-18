package com.example.demo.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.demo.Dao.UsuarioDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Validaciones {
	
	/**
	 *
	 * @author Daniel Cárdenas
	 * @Descripcion Valida si un campo String es nulo o vacio
	 * @param campo String
	 * @return boolean 
	 */
	
	public boolean nullOBlancoString(String campo) {
		
		if(campo== null || campo.isBlank()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 *
	 * @author Daniel Cárdenas
	 * @Descripcion Valida si un campo es numérico
	 * @param campo String
	 * @return boolean
	 */
	
	public boolean validaNumerico(String campo) {
		
		if(campo!= null && !campo.isBlank()) {
			try {
				Integer.parseInt(campo);
				return true;
			}catch(NumberFormatException e) {
				log.error("¬¬ Error en ValidacionService.validaNumerico " + e);
				return false;
			}
			
		}else {
			return false;
		}
		
	}
	
	/**
	 *
	 * @author Daniel Cárdenas
	 * @Descripcion Valida si un campo Email es nulo o no cumple el formato email
	 * @param email String
	 * @return boolean 
	 */
	
	public boolean validaEmail(String email) {
		String expresionRegular = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(expresionRegular);
		
		if(email== null || email.isBlank()) {
			return true;
		}else {
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
	}
	
	/**
	 *
	 * @author Daniel Cárdenas
	 * @Descripcion Valida si un campo Password es nulo o no cumple el formato definido
	 * @param email String
	 * @return boolean 
	 */
	
	public boolean validaPwd(String pwd, String expresion) {
		Pattern pattern = Pattern.compile(expresion);
		if(pwd== null || pwd.isBlank()) {
			return true;
		}else {
			Matcher matcher = pattern.matcher(pwd);
			return matcher.matches();
		}
	}
	
	/**
	 *
	 * @author Daniel Cárdenas
	 * @Descripcion Valida si una lista es null o vacia
	 * @param lista List
	 * @return boolean 
	 */
	
	public boolean validaLista(List<?> lista) {
		if(lista == null || lista.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	

}
