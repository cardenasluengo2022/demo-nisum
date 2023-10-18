package com.example.demo.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TelefonoRespModel {

	@JsonProperty("number")
	private String number;

	@JsonProperty("citycode")
	private String citycode;

	@JsonProperty("contrycode")
	private String contrycode;
	
}