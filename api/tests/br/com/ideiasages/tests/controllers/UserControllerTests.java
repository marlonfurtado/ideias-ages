package br.com.ideiasages.tests.controllers;

import br.com.ideiasages.controllers.*;

import static org.junit.Assert.*;

import javax.ws.rs.PathParam;

import org.junit.Test;

import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.PersistenciaException;
import junit.framework.Assert;

public class UserControllerTests {

	@Test
	public void testList() throws PersistenciaException {
		UserController user = new UserController();
		
		user.enable("12314549");
		assertTrue(user.enable("12314549") instanceof StandardResponseDTO);
	}

	@Test
	public void testEnable() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisable() throws PersistenciaException {
		UserController user = new UserController();
		
		user.disable("12314549");
		assertTrue(user.disable("12314549") instanceof StandardResponseDTO);
		
	}

	@Test
	public void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

}
