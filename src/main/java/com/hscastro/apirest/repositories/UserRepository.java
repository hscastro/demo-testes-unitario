package com.hscastro.apirest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hscastro.apirest.domain.Usuario;
import com.hscastro.apirest.dto.UsuarioDTO;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(UsuarioDTO userDTO);

}
