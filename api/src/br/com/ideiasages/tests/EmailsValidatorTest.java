package br.com.ideiasages.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.validator.EmailsValidator;

public class EmailsValidatorTest {

	private EmailsValidator validator;
	private Map<String, Object> map;

	@Before
	public void setUp(){
		validator = new EmailsValidator();
		map = new HashMap<>();
	}

	@Test(expected=ValidationException.class)
	public void emailIsNullOrEmpty() throws ValidationException {
		map.put("email",null);

		validator.validar(map);

		map.replace("email", "");

		validator.validar(map);
	}

	@Test(expected=ValidationException.class)
	public void emailKeyNotExists() throws ValidationException {
		map.put(null,"");

		validator.validar(map);

		map.replace("", "");

		validator.validar(map);
	}

	@Test(expected=ValidationException.class)
	public void invalidEmailExceptions() throws ValidationException {
		map.put("email",1L);

		validator.validar(map);

		map.put("email",false);

		validator.validar(map);

		map.put("email",155.0);

		validator.validar(map);

		map.put("email",Byte.parseByte("155"));

		validator.validar(map);

		map.put("email",'a');

		validator.validar(map);
	}

	@Test(expected=ValidationException.class)
	public void invalidEmail() throws ValidationException {
		map.put("email","asdas@sasdfasd");
		
		assertEquals(false, validator.validar(map));
		
		map.put("email","asdas@googladsfasdf.com");
		
		assertEquals(true, validator.validar(map));
	}
	
}
