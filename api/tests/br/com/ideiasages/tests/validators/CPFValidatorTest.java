package br.com.ideiasages.tests.validators;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.validator.CPFValidator;

public class CPFValidatorTest {

	private CPFValidator validator;
	private Map<String, Object> map;

	@Before
	public void setUp(){
		validator = new CPFValidator();
		map = new HashMap<>();
	}

	@Test(expected=ValidationException.class)
	public void cpfIsNullOrEmpty() throws ValidationException {
		map.put("cpf",null);

		validator.validar(map);

		map.replace("cpf", "");

		validator.validar(map);
	}

	@Test(expected=ValidationException.class)
	public void cpfKeyNotExists() throws ValidationException {
		map.put(null,"");

		validator.validar(map);

		map.replace("", "");

		validator.validar(map);
	}

	@Test(expected=ValidationException.class)
	public void invalidCPFExceptions() throws ValidationException {
		map.put("cpf",1L);

		validator.validar(map);

		map.put("cpf",false);

		validator.validar(map);

		map.put("cpf",155.0);

		validator.validar(map);

		map.put("cpf",Byte.parseByte("155"));

		validator.validar(map);

		map.put("cpf",'a');

		validator.validar(map);
	}

	@Test(expected=ValidationException.class)
	public void invalidCPF() throws ValidationException {
		validator.validar(map);

		map.put("cpf", "26436567786");

		validator.validar(map);

		map.put("cpf", "89987967879");

		validator.validar(map);

		map.put("cpf", "55656563543");

		validator.validar(map);

		map.put("cpf", "90988732773");

		validator.validar(map);

		map.put("cpf", "90988732773");

		validator.validar(map);

		map.put("cpf", "00000000000");

		validator.validar(map);

		map.put("cpf", "88888888888");

		validator.validar(map);

		map.put("cpf", "99999999999");

		validator.validar(map);
		
		map.put("cpf", "asdfsaf");

		validator.validar(map);
		
		map.put("cpf", "123.345.567.67");

		validator.validar(map);
	}
	
	@Test(expected=ValidationException.class)
	public void charactersNonNumericsInCPF() throws ValidationException{
		map.put("cpf", "407.384.741-42");
		
		assertEquals(true,validator.validar(map));
		
		map.put("cpf", "123.343.444-44");
		
		validator.validar(map);
		
		map.put("cpf", "asdfsdaf&**&*&***");
		
		validator.validar(map);
		
		map.put("cpf", "sadasdsad373.940.485-00asdsdsdasdasdasdasdasd");
		
		assertEquals(true,validator.validar(map));
	}
}
