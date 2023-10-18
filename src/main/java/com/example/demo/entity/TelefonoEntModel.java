package com.example.demo.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TELEFONOS")
@Data
public class TelefonoEntModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID_TELEFONO")
	private UUID id;
	
	@Column(name = "USUARIO_ID")
	private UUID usuarioId;

	@Column(name = "NUMBER")
	private String number;

	@Column(name = "CITYCODE")
	private String citycode;

	@Column(name = "CONTRYCODE")
	private String contrycode;

}
