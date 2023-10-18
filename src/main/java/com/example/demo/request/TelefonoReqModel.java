package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TelefonoReqModel {

	
	@JsonProperty("number")
	private String number;

	@JsonProperty("citycode")
	private String citycode;

	@JsonProperty("contrycode")
	private String contrycode;

	
}
