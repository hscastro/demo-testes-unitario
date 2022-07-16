package com.hscastro.apirest.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hscastro.apirest.domain.Usuario;
import com.hscastro.apirest.dto.UsuarioDTO;
import com.hscastro.apirest.services.UserServiceImpl;

@RestController
@RequestMapping(value = "/usuarios")
public class UserResource {

	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> listDTO = service.findAll()
				.stream().map(x -> mapper.map(x, UsuarioDTO.class)).collect(Collectors.toList());
		
				return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper
				.map(service.findById(id), UsuarioDTO.class));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody UsuarioDTO userDTO) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(service.create(userDTO).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO obj) {
		obj.setId(id);
		return ResponseEntity.ok().body(mapper
				.map(service.update(obj), UsuarioDTO.class));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
	
//	@PostMapping
//	public ResponseEntity<Usuario> create(@RequestBody UsuarioDTO userDTO) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userDTO));
//	}
	
}
