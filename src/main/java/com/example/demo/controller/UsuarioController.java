package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.UsuarioReqModel;
import com.example.demo.response.RegistroUsuarioRespModel;
import com.example.demo.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	
	@PostMapping("/tutorials")
	public ResponseEntity<RegistroUsuarioRespModel> registroUsuario(@RequestBody UsuarioReqModel params) {
		log.info("- Parametros de Entrada en UsuarioController.registroUsuario: " + params);
		
		return usuarioService.registroUsuario(params);
		
	}

}
