package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistroUsuarioRespModel {
	
	@JsonProperty("mensaje")
	private String mensaje;
	
	@JsonProperty("usuario")
	private UsuarioRespModel usuario;
	
	
}
