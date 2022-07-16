package com.hscastro.apirest.resources;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hscastro.apirest.domain.Usuario;
import com.hscastro.apirest.dto.UsuarioDTO;
import com.hscastro.apirest.repositories.UserRepository;
import com.hscastro.apirest.services.UserServiceImpl;

class UserResourceTest {
	
	private static final int ID = 1;
	private static final String EMAIL = "antonio12@gmail.com";
	private static final String NAME = "Antonio";
	private static final String PASSWORD = "qwe123";
	private static final int INDEX = 0;
	private static final int TAMANHO = 1;
	
	@InjectMocks
	private UserResource resource;

	@Mock
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	@Mock
	private Usuario user;
	
	@Mock
	private UsuarioDTO userDTO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUsers();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDTO);
		
		ResponseEntity<UsuarioDTO> response = resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UsuarioDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NAME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(PASSWORD, response.getBody().getPassword());
	}
	
	@Test
	void whenFindAllThenReturnAnListOfUserDTO() {
		
		when(service.findAll()).thenReturn(List.of(user));
		when(mapper.map(any(), any())).thenReturn(userDTO);		
		
		ResponseEntity<List<UsuarioDTO>> response = resource.findAll();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());		
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		assertEquals(UsuarioDTO.class, response.getBody().get(INDEX).getClass());	
		
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(NAME, response.getBody().get(INDEX).getName());
		assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
		assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
		
		assertEquals(TAMANHO, response.getBody().size());
		
	}
	
	
	@Test
	void whenCreateThenReturnCreated() {
		
		when(service.create(any())).thenReturn(user);
		
		ResponseEntity<Usuario> response = resource.create(userDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());		
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().getId());		
		assertEquals(NAME, response.getBody().getName());	
		assertEquals(EMAIL, response.getBody().getEmail());
		//assertEquals(PASSWORD, response.getBody().getPassword());
	}

	@Test
	void whenUpdateThenReturnSuccess() {		
		when(service.create(any())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDTO);		
		
		ResponseEntity<UsuarioDTO> response = resource.update(ID, userDTO);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ID, response.getBody().getId());		
		assertEquals(NAME, response.getBody().getName());	
		assertEquals(EMAIL, response.getBody().getEmail());
		//assertEquals(PASSWORD, response.getBody().getPassword());
	}
	
	private void startUsers() {
		user = new Usuario(ID, NAME, EMAIL, PASSWORD); 
		userDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD); 
	}

}
