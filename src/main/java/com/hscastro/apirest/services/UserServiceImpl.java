package com.hscastro.apirest.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hscastro.apirest.domain.Usuario;
import com.hscastro.apirest.dto.UsuarioDTO;
import com.hscastro.apirest.exceptions.ObjectNotFoundException;
import com.hscastro.apirest.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<Usuario> findAll() { return userRepository.findAll(); }
	
	@Override
	public Usuario findById(Integer id) {
		Optional<Usuario> usuario = userRepository.findById(id);
		return usuario.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	@Override
	public Usuario create(UsuarioDTO userDTO) {
		//findByEmail(userDTO);
		return userRepository.save(mapper.map(userDTO, Usuario.class));
	}

	@SuppressWarnings("unused")
	private void findByEmail(UsuarioDTO userDTO) {
		Optional<Usuario> user = userRepository.findByEmail(userDTO);
		if(user.isPresent() && !user.get().getId().equals(userDTO.getId())) {
			throw new DataIntegrityViolationException("E-mail ja cadastrado no sistema");
		}		
	}

	@Override
	public Usuario update(UsuarioDTO userDTO) {
		Optional<Usuario> user = userRepository.findByEmail(userDTO);
		
		if(user.isPresent() && !user.get().getId().equals(userDTO.getId())) {
			return userRepository.save(mapper.map(userDTO, Usuario.class));
		}
		return null;	
	}

	public void delete(Integer id) {
		Optional<Usuario> user = userRepository.findById(id);	
		if(user.isPresent()) {
			userRepository.deleteById(id);
		}		
	}
}
