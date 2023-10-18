package com.example.demo.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.TelefonoEntModel;
import com.example.demo.repository.TelefonoRepository;
import com.example.demo.request.TelefonoReqModel;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

@Slf4j
@Repository
public class TelefonoDao {
	
	@Autowired
	TelefonoRepository telefonoRepository;
	
	
	public TelefonoEntModel agregarTelefono(TelefonoEntModel params) {
		
		log.info("- Parametros de Entrada en TelefonoDao.agregar: " + params);
		
		try {
			
			TelefonoEntModel telefonoSaved = telefonoRepository.saveAndFlush(params);
			
			return telefonoSaved;
			
		}catch(Exception e) {
			log.error("error en TelefonoDao.agregar " + e.getMessage());
			return null;
		}
		
	}
	

}
