package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "USUARIOS")
@Data
public class UsuarioEntModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "ID_USUARIO")
    private UUID id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CREATED")
	private Date created;
	
	@Column(name = "MODIFIED")
	private Date modified;
	
	@Column(name = "LAST_LOGIN")
	private Date lastLogin;
	
	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "ACTIVE")
	private boolean active;

}
