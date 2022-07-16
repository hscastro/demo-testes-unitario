package com.hscastro.apirest.services;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest;

import com.hscastro.apirest.domain.Usuario;
import com.hscastro.apirest.dto.UsuarioDTO;
import com.hscastro.apirest.exceptions.ObjectNotFoundException;
import com.hscastro.apirest.repositories.UserRepository;


//@SpringBootTest
class UserServiceImplTest {
	
	private static final int ID = 1;
	private static final String EMAIL = "antonio12@gmail.com";
	private static final String NAME = "Antonio";
	private static final String PASSWORD = "qwe123";
	

	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository userRepository;

	@Mock
	private Usuario user;
	
	@Mock
	private UsuarioDTO userDTO;
	
	private Optional<Usuario> optionalUser;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUsers();
	}
	
	@Test
	void wenFindByIdThenReturnAnUserInstance() {
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		
		Usuario response = service.findById(ID);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Usuario.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
	}

	
	@Test
	void wenFindByIdThenReturnAnObjectNotFoundException() {
		Mockito.when(userRepository.findById(Mockito.anyInt()))
				.thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
			
		try {
			service.findById(ID);
			
		} catch (Exception e) {
			Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
			//Assertions.assertEquals("Objeto não encontrado", e.getMessage());
		}
	}

	
	@Test
	void wenFindByIdThenReturnAnListOfUsers() {
		Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
		
		List<Usuario> response = service.findAll();
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
		Assertions.assertEquals(Usuario.class, response.get(0).getClass());
		
		Assertions.assertEquals(ID, response.get(0).getId());
		Assertions.assertEquals(NAME, response.get(0).getName());
		Assertions.assertEquals(EMAIL, response.get(0).getEmail());
		
	}		
	
	
	private void startUsers() {
		user = new Usuario(ID, NAME, EMAIL, PASSWORD); 
		userDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD); 
		optionalUser = Optional.of(new Usuario(ID, NAME, EMAIL, PASSWORD));
	}
}
