package br.com.ideiasages.tests.validators;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.validator.RequiredFieldsValidator;

public class RequiredFieldsValidatorTest {

	private RequiredFieldsValidator validator;
	private Map<String, Object> map;

	@Before
	public void setUp() {
		validator = new RequiredFieldsValidator();
		map = new HashMap<>();
	}

	@Test(expected = ValidationException.class)
	public void fieldIsNullOrEmpty() throws ValidationException {
		map.put("field", null);

		validator.validar(map);

		map.replace("field", "");

		validator.validar(map);

		map.replace("field", "");

		validator.validar(map);
	}

	@Test(expected = ValidationException.class)
	public void fieldKeysNotExists() throws ValidationException {
		map.put(null, "");

		validator.validar(map);

		map.replace("", "");

		validator.validar(map);
	}
}
