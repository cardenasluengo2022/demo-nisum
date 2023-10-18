package com.example.demo.response;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioRespModel {

	@JsonProperty("idUsuario")
	private UUID idUsuario;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("phones")
	private List<TelefonoRespModel> phones;
	
	@JsonProperty("created")
	private String created;
	
	@JsonProperty("modified")
	private String modified;
	
	@JsonProperty("lastLogin")
	private String lastLogin;
	
	@JsonProperty("token")
	private String token;
	
	@JsonProperty("active")
	private boolean active;
	
}