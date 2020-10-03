package com.bolsaideas.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsaideas.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
	public Usuario findByUsername(String username);
	
	/***@Query("select u from usuario u where u.userna*me=?")
	public Usuario findByUsername2(String username);*/
}
