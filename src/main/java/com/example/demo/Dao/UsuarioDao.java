package com.example.demo.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UsuarioEntModel;
import com.example.demo.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UsuarioDao {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public UsuarioEntModel agregarUsuario(UsuarioEntModel params) {
		
		log.info("- Parametros de Entrada en UsuarioDao.agregarUsuario: " + params);
		
		try {
			
			UsuarioEntModel usuarioSaved = usuarioRepository.saveAndFlush(params);
			
			return usuarioSaved;
			
		}catch(Exception e) {
			log.error("error en UsuarioDao.agregarUsuario: " + e.getMessage());
			return null;
		}
		
	}
	

}
