package tests.br.com.ideiasages.validator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.RequiredFieldsValidator;

public class RequiredFieldsValidatorTest {

	private RequiredFieldsValidator validator;
	private Map<String, Object> map;
	private static final String EXPECTED_REQUIRED_FIELD = MensagemContantes.MSG_ERR_CAMPOS_OBRIGATORIOS;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp() {
		validator = new RequiredFieldsValidator();
		map = new HashMap<>();
	}
	
	@Test
	public void fieldIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_REQUIRED_FIELD);
		map.put("field", null);
		
		validator.validar(map);
	}
	
	@Test
	public void fieldIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_REQUIRED_FIELD);
		map.put("field", "");
		
		validator.validar(map);
	}

	@Test
	public void fieldKeysIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_REQUIRED_FIELD);
		map.put(null, "");

		validator.validar(map);

	}
	
	@Test
	public void fieldKeysIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_REQUIRED_FIELD);
		map.put("", "");

		validator.validar(map);

	}
	
	private void expectedExceptionResults(String message){
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(message);
	}
}
