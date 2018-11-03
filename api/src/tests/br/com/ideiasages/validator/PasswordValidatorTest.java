package tests.br.com.ideiasages.validator;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.PasswordValidator;

public class PasswordValidatorTest {

	private PasswordValidator validator;
	private Map<String, Object> map;

	private static final String EXPECTED_EXCEEDED_PASSWORD_LENGTH_EXCEPTION_MESSAGE = 
			MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", "senha").concat("<br/>");

	private static final String EXPECTED_EXCEEDED_OTHERPASSWORD_LENGTH_EXCEPTION_MESSAGE = 
			MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", "confirmação de senha").concat("<br/>");
	
	private static final String EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE = 
			MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "senha ou confirmação de senha").concat("<br/>");

	private static final String EXPECTED_UNLIKE_PASSWORD_EXCEPTION_MESSAGE = 
			MensagemContantes.MSG_ERR_SENHAS_DIFERENTES.concat("<br/>");

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp() {
		validator = new PasswordValidator();
		map = new HashMap<>();
	}


	@Test
	public void passwordIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("password",null);

		validator.validar(map);
	}

	@Test
	public void otherPasswordIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("otherPassword",null);

		validator.validar(map);
	}


	@Test
	public void passwordIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.replace("password", "");

		validator.validar(map);
	}

	@Test
	public void otherPasswordIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.replace("otherPassword", "");

		validator.validar(map);
	}

	@Test
	public void passwordKeyIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put(null,"");

		validator.validar(map);
	}

	@Test
	public void otherPasswordKeyIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put(null,"");

		validator.validar(map);
	}

	@Test
	public void passwordKeyIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.replace("", "");

		validator.validar(map);
	}

	@Test
	public void otherPasswordKeyIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.replace("", "");

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPasswordIsLong() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("password",1L);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenOtherPasswordIsLong() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("otherPassword",1L);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPasswordIsBoolean() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("password",false);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenOtherPasswordIsBoolean() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("otherPassword",false);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPasswordIsDecimal() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("password",155.0);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenOtherPasswordIsDecimal() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);

		map.put("otherPassword",155.0);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPasswordIsByte() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);
		Integer integer = 155;

		map.put("password",integer.byteValue());

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenOtherPasswordIsByte() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PASSWORD_EXCEPTION_MESSAGE);
		Integer integer = 155;

		map.put("otherPassword",integer.byteValue());

		validator.validar(map);
	}

	@Test
	public void passwordLengthExceeded() throws ValidationException {
		expectedExceptionResults(EXPECTED_EXCEEDED_PASSWORD_LENGTH_EXCEPTION_MESSAGE);

		//When a email don't matched a valid email.
		String longString = "asdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaasdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaasdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsa";

		map.put("password", longString);
		map.put("otherPassword", longString);
		
		validator.validar(map);
	}

	@Test
	public void otherPasswordLengthExceeded() throws ValidationException {
		expectedExceptionResults(EXPECTED_EXCEEDED_OTHERPASSWORD_LENGTH_EXCEPTION_MESSAGE);

		//When a email don't matched a valid email.
		String longString = "asdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaasdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaasdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsa";

		map.put("password", longString);
		map.put("otherPassword", longString);
		validator.validar(map);
	}

	@Test
	public void invalidPassword() throws ValidationException {
		expectedExceptionResults(EXPECTED_UNLIKE_PASSWORD_EXCEPTION_MESSAGE);
		map.put("password", "12345678");
		map.put("otherPassword", "123456789");

		validator.validar(map);
	}

	private void expectedExceptionResults(String message){
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(message);
	}

}
