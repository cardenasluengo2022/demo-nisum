package com.example.demo.request;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UsuarioReqModel {

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;
	
	@JsonProperty("phones")
	private List<TelefonoReqModel> phones;
	
}