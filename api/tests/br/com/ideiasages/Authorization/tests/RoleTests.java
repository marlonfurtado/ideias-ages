package br.com.ideiasages.Authorization.tests;

import br.com.ideiasages.Authorization.*;
import br.com.ideiasages.authorization.Role;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoleTests {

	@Test
	public void testGetPrimeNumberFromRole() {

		assertNotNull(Role.getPrimeNumberFromRole("role"));
		assertNotNull(Role.getPrimeNumberFromRole("administrator"));
		assertNotNull(Role.getPrimeNumberFromRole("analyst"));
		assertNotNull(Role.getPrimeNumberFromRole("idealizer"));
		assertEquals("Role.ADMINISTRATOR", Role.getPrimeNumberFromRole("administrator"));
		assertEquals("Role.ANALYST", Role.getPrimeNumberFromRole("analyst"));
		assertEquals("Role.IDEALIZER", Role.getPrimeNumberFromRole("idealizer"));
		assertEquals(-1, Role.getPrimeNumberFromRole("role"));

	}

	@Test
	public void testMerge() {
		long f = 123;
		assertNotNull(Role.merge(f));
		assertNotEquals(555, Role.merge(f));
	}

}
