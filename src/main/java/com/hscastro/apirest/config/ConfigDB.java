package com.hscastro.apirest.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.hscastro.apirest.domain.Usuario;
import com.hscastro.apirest.repositories.UserRepository;


public class ConfigDB {

	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		Usuario u1 = new Usuario(1, "Halyson Santos", "halisnsc5@gmail.com", "qwe123");
		Usuario u2 = new Usuario(2, "Emanuela Almeida", "emanuelaalmeida_7@gtmail.com", "qwe456");
		Usuario u3 = new Usuario(3, "Halyson Castro", "a.castro@tcs.com", "12345");
		repository.saveAll(List.of(u1, u2, u3));
	}
}
