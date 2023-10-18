package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.JwtTokenProvider;
import com.example.demo.Dao.TelefonoDao;
import com.example.demo.Dao.UsuarioDao;
import com.example.demo.entity.TelefonoEntModel;
import com.example.demo.entity.UsuarioEntModel;
import com.example.demo.request.TelefonoReqModel;
import com.example.demo.request.UsuarioReqModel;
import com.example.demo.response.RegistroUsuarioRespModel;
import com.example.demo.response.TelefonoRespModel;
import com.example.demo.response.UsuarioRespModel;
import com.example.demo.util.Validaciones;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	TelefonoDao telefonoDao;
	
	@Autowired
	Validaciones validaService;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	
	
	public ResponseEntity<RegistroUsuarioRespModel> registroUsuario(UsuarioReqModel params) {
		RegistroUsuarioRespModel response = new RegistroUsuarioRespModel();
		
		if(validaService.nullOBlancoString(params.getName())) {
			response.setMensaje("El campo nombre es obligatorio");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(!validaService.validaEmail(params.getEmail())) {
			response.setMensaje("El campo email es obligatorio");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		//valida que el email no exista
		
		if(validaService.validaPwd(
				params.getPassword(),
				"^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=])(?![\\s])(.{8,})$")
				) {
			response.setMensaje("El campo password es obligatorio");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(validaService.validaLista(params.getPhones())) {
			response.setMensaje("Debe definir al menos un telefono");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		for (TelefonoReqModel tel : params.getPhones()) {
			if(!validaService.validaNumerico(tel.getNumber()) ||
				!validaService.validaNumerico(tel.getCitycode()) ||
				!validaService.validaNumerico(tel.getContrycode())
					) {
					response.setMensaje("Los campos del numero de telefono deben ser numéricos");
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
		
		
		try {
			
			UsuarioEntModel usuarioEnt = new UsuarioEntModel();
			
			usuarioEnt.setName(params.getName());
			usuarioEnt.setEmail(params.getEmail());
			usuarioEnt.setPassword(params.getPassword());
			usuarioEnt.setCreated(new Date());
			usuarioEnt.setModified(new Date());
			usuarioEnt.setLastLogin(new Date());
			usuarioEnt.setToken(jwtTokenProvider.generateToken(params.getName()));
			usuarioEnt.setActive(true);
			
			
			UsuarioEntModel usuarioSaved = usuarioDao.agregarUsuario(usuarioEnt);
			List<TelefonoEntModel> listaTelefonosSaved = new ArrayList<>();
			
			if(usuarioSaved != null) {
				
				for (TelefonoReqModel tel : params.getPhones()) {
					
					TelefonoEntModel telefonoEnt = new TelefonoEntModel();
					telefonoEnt.setUsuarioId(usuarioSaved.getId());
					telefonoEnt.setNumber(tel.getNumber());
					telefonoEnt.setCitycode(tel.getCitycode());
					telefonoEnt.setContrycode(tel.getContrycode());
					
					TelefonoEntModel telefonoSaved = telefonoDao.agregarTelefono(telefonoEnt);
					
					if(telefonoSaved == null) {
						response.setMensaje("Error al guardar el telefono del usuario ");
						return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
						//hacer rollback
					}
					
					listaTelefonosSaved.add(telefonoSaved);
					
					
				}
			
				List<TelefonoRespModel> listatelefonos = new ArrayList<>();
				
				for (TelefonoEntModel tel : listaTelefonosSaved) {
					
					TelefonoRespModel telefonoResp = new TelefonoRespModel();
					
					telefonoResp.setNumber(tel.getNumber());
					telefonoResp.setCitycode(tel.getCitycode());
					telefonoResp.setContrycode(tel.getContrycode());
					
					listatelefonos.add(telefonoResp);
				
				}
				
				
				
				
				
				
				
				UsuarioRespModel usuarioResp = new UsuarioRespModel();
				
				usuarioResp.setIdUsuario(usuarioSaved.getId());
				usuarioResp.setName(usuarioSaved.getName());
				usuarioResp.setEmail(usuarioSaved.getEmail());
				usuarioResp.setCreated(usuarioSaved.getCreated().toString());
				usuarioResp.setModified(usuarioSaved.getModified().toString());
				usuarioResp.setLastLogin(usuarioSaved.getLastLogin().toString());
				usuarioResp.setToken(usuarioSaved.getToken());
				usuarioResp.setActive(usuarioSaved.isActive());
				usuarioResp.setPhones(listatelefonos);
				
				
				
				response.setUsuario(usuarioResp);
				return new ResponseEntity<>(response, HttpStatus.CREATED);
				
			}else {
				response.setMensaje("Error al guardar el usuario ");
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			log.error("Error en UsuarioService.registroUsuario: " + e);
			
			response.setMensaje("Error al registrar el usuario ");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}

}
