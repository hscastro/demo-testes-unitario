package com.hscastro.apirest.services;

import java.util.List;

import com.hscastro.apirest.domain.Usuario;
import com.hscastro.apirest.dto.UsuarioDTO;

public interface UserService {
	
	Usuario findById(Integer id);
	Usuario create(UsuarioDTO userDTO);
	Usuario update(UsuarioDTO userDTO);
	List<Usuario> findAll();
	void delete(Integer id);
}
