package tests.br.com.ideiasages.validator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.EmailsValidator;

public class EmailsValidatorTest {

	private EmailsValidator validator;
	private Map<String, Object> map;
	private static final String EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE =  
			MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "<b>email</b>").concat("<br/>");
	private static final String EXPECTED_EXCEEDED_EMAIL_LENGTH_EXCEPTION_MESSAGE = 
			MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", "<b>email</b>").concat("<br/>");

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp(){
		validator = new EmailsValidator();
		map = new HashMap<>();
	}

	@Test
	public void emailIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		map.put("email",null);

		validator.validar(map);
	}

	@Test
	public void emailIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		map.replace("email", "");

		validator.validar(map);
	}

	@Test
	public void emailKeyIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		map.put(null,"");

		validator.validar(map);
	}

	@Test
	public void emailKeyIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		map.replace("", "");

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenEmailIsLong() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		map.put("email",1L);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenEmailIsBoolean() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		map.put("email",false);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenEmailIsDecimal() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		map.put("email",155.0);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenEmailIsByte() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);
		Integer integer = 155;
		
		map.put("email",integer.byteValue());

		validator.validar(map);
	}

	@Test
	public void invalidEmailStructure() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_EMAIL_EXCEPTION_MESSAGE);

		//When a email don't matched a valid email.
		map.put("email", "asdfads@aaasd");
		validator.validar(map);
	}

	@Test
	public void emailLengthExceeded() throws ValidationException {
		expectedExceptionResults(EXPECTED_EXCEEDED_EMAIL_LENGTH_EXCEPTION_MESSAGE);

		//When a email don't matched a valid email.
		String longString = "asdfads@aaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsa.com";
		
		map.put("email", longString);
		validator.validar(map);
	}

	private void expectedExceptionResults(String message){
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(message);
	}
}
