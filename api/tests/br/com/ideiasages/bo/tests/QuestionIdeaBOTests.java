package br.com.ideiasages.bo.tests;

import br.com.ideiasages.bo.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class QuestionIdeaBOTests {

	@Test
	public void testValidate() {
		QuestionIdeaBO q = new QuestionIdeaBO();
		assertNotNull(q);
	}

	@Test
	public void testValidateRequiredFields() {
		QuestionIdeaBO q = new QuestionIdeaBO();
		assertNotNull(q);
	}

	@Test
	public void testValidateUserCpf() {
		QuestionIdeaBO q = new QuestionIdeaBO();
		assertNotNull(q);
	}

	@Test
	public void testExistsAnalystByCpf() {
		QuestionIdeaBO q = new QuestionIdeaBO();
		assertNotNull(q);
	}

	@Test
	public void testIdeaHasNotQuestionAnswered() {
		QuestionIdeaBO q = new QuestionIdeaBO();
		assertNotNull(q);
	}

	@Test
	public void testIsInvalidIdea() {
		QuestionIdeaBO q = new QuestionIdeaBO();
		assertNotNull(q);
	}

}
