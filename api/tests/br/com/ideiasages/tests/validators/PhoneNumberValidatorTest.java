package br.com.ideiasages.tests.validators;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.validator.PhoneNumberValidator;

public class PhoneNumberValidatorTest {

	private PhoneNumberValidator validator;
	private Map<String, Object> map;

	@Before
	public void setUp() {
		validator = new PhoneNumberValidator();
		map = new HashMap<>();
	}

	@Test(expected = ValidationException.class)
	public void phoneIsNullOrEmpty() throws ValidationException {
		map.put("phone", null);

		validator.validar(map);

		map.replace("phone", "");

		validator.validar(map);
	}

	@Test(expected = ValidationException.class)
	public void phoneKeyNotExists() throws ValidationException {
		map.put(null, "");

		validator.validar(map);

		map.replace("", "");

		validator.validar(map);
	}

	@Test(expected = ValidationException.class)
	public void invalidPhoneExceptions() throws ValidationException {
		map.put("phone", 1L);

		validator.validar(map);

		map.put("phone", false);

		validator.validar(map);

		map.put("phone", 155.0);

		validator.validar(map);

		map.put("phone", Byte.parseByte("155"));

		validator.validar(map);

		map.put("phone", 'a');

		validator.validar(map);
	}

	@Test(expected = ValidationException.class)
	public void invalidPhone() throws ValidationException {
		map.put("phone", "+55 (51) 98979 - 6708");

		validator.validar(map);

		map.replace("phone", "9234234324234234234242423432");

		validator.validar(map);

		map.replace("phone", "123456789");

		validator.validar(map);

		map.replace("phone", "1234567891011");

		validator.validar(map);
	}

}
