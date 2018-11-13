package br.com.ideiasages.bo.tests;

import br.com.ideiasages.bo.*;
import br.com.ideiasages.dto.QuestionAnswerDTO;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordChangeBOTests {

	@Test
	public void testCreatePasswordChangeRequest() {
		PasswordChangeBO q = new PasswordChangeBO();
		assertNotNull(q);
	}

	@Test
	public void testVeifyToken() {
		PasswordChangeBO q = new PasswordChangeBO();
		assertNotNull(q);
	}

	@Test
	public void testChangePassword() {
		PasswordChangeBO q = new PasswordChangeBO();
		assertNotNull(q);
	}

}
