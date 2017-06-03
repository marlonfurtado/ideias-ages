package br.com.ideiasages.tests.validators;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.CPFValidator;

public class CPFValidatorTest {

	private CPFValidator validator;
	private Map<String, Object> map;
	public static final String EXPECTED_INVALID_CPF_EXCEPTION_MESSAGE =  MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "<b>CPF</b>").concat("<br/>");

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp(){
		validator = new CPFValidator();
		map = new HashMap<>();
	}

	@Test
	public void cpfIsNull() throws ValidationException {
		expectedExceptionResults();

		map.put("cpf",null);

		validator.validar(map);
	}

	@Test
	public void cpfIsEmpty() throws ValidationException {
		expectedExceptionResults();

		map.replace("cpf", "");

		validator.validar(map);
	}

	@Test
	public void cpfKeyIsNull() throws ValidationException {
		expectedExceptionResults();

		map.put(null,"");

		validator.validar(map);
	}

	@Test
	public void cpfKeyIsEmpty() throws ValidationException {
		expectedExceptionResults();

		map.replace("", "");

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenCPFisLong() throws ValidationException {
		expectedExceptionResults();

		map.put("cpf",1L);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenCPFisBoolean() throws ValidationException {
		expectedExceptionResults();

		map.put("cpf",false);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenCPFisDecimal() throws ValidationException {
		expectedExceptionResults();

		map.put("cpf",155.0);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenCPFisByte() throws ValidationException {
		expectedExceptionResults();
		Integer integer = 155;
		
		map.put("cpf",integer.byteValue());

		validator.validar(map);
	}

	@Test
	public void whenCPFisNotStringNumber() throws ValidationException {
		expectedExceptionResults();

		map.put("cpf",'a');

		validator.validar(map);
	}

	@Test
	public void invalidCPFRule() throws ValidationException {
		expectedExceptionResults();

		//When a CPF don't matched a valid CPF.
		map.put("cpf", "26436567786");
		validator.validar(map);
	}

	private void expectedExceptionResults(){
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(EXPECTED_INVALID_CPF_EXCEPTION_MESSAGE);
	}
}
