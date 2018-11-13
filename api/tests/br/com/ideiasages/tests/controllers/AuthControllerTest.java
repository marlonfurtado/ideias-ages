package br.com.ideiasages.tests.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.ideiasages.controllers.AuthController;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.User;

public class AuthControllerTest {

	@Test
	public void testLogin() {
		StandardResponseDTO response = new StandardResponseDTO();
		assertNotNull(response);
	}

	@Test
	public void testCreate() throws PersistenciaException, ValidationException {
		User u = new User();
		AuthController auth = new AuthController(); 
		assertNotNull(auth.create(u));
	}

	@Test
	public void testLogoutUser() {
		AuthController auth = new AuthController(); 
		assertNotNull(auth.logoutUser());
	}

	@Test
	public void testGetMe() {
		User u = new User();
		AuthController auth = new AuthController();
		auth.login(u);
		assertNotNull(auth.getMe());
	}

	@Test
	public void testRecoverPasswordRequest() {
		User u = new User();
		AuthController auth = new AuthController();
		assertNotNull(auth.recoverPasswordRequest(u));
	}

}
