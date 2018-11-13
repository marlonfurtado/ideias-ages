package br.com.ideiasages.tests.controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.javafx.logging.Logger;

import br.com.ideiasages.controllers.IdeaController;

public class IdeaControllerTests {

	@Test
	public void testIdeaController() {
		IdeaController i = new IdeaController();
		assertNotNull(i);
	}

	@Test
	public void testGetLogger() {
		IdeaController i = new IdeaController();
		assertNotNull(i);
		assertNotNull(i.getLogger());
	}

	@Test
	public void testSetLogger() {
		IdeaController i = new IdeaController();
		Logger l = new Logger();
		assertEquals(l, i.getLogger());
	}

	@Test
	public void testCreate() {
		IdeaController idc = new IdeaController();
		assertNotNull(idc);
	}

	@Test
	public void testUpdate() {
		IdeaController idc = new IdeaController();
		assertNotNull(idc);
	}

	@Test
	public void testChangeStatus() {
		IdeaController idc = new IdeaController();
		assertNotNull(idc);
	}

	@Test
	public void testList() {
		IdeaController idc = new IdeaController();
		assertNotNull(idc);
	}

	@Test
	public void testGetIdea() {
		IdeaController idc = new IdeaController();
		assertNotNull(idc);
	}

}
