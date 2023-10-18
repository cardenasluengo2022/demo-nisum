package com.example.demo.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UsuarioEntModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntModel, UUID> {

	 
}
