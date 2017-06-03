package br.com.ideiasages.tests.validators;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.PhoneNumberValidator;

public class PhoneNumberValidatorTest {

	private PhoneNumberValidator validator;
	private Map<String, Object> map;

	private static final String EXPECTED_INVALID_PHONENUMBER = MensagemContantes.MSG_ERR_TELEFONE_INVALIDO;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp() {
		validator = new PhoneNumberValidator();
		map = new HashMap<>();
	}

	@Test
	public void phoneNumberIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);
		map.put("phone", null);

		validator.validar(map);

	}

	@Test
	public void phoneNumberIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);
		map.replace("phone", "");

		validator.validar(map);
	}

	@Test
	public void phoneNumberKeyIsNull() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);
		map.put(null,"");

		validator.validar(map);
	}

	@Test
	public void passwordKeyIsEmpty() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);
		map.put("","");

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPhoneNumberIsLong() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);

		map.put("phone",1L);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPhoneNumberIsBoolean() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);

		map.put("phone",false);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPhoneNumberIsDecimal() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);

		map.put("phone",122.0);

		validator.validar(map);
	}

	@Test
	public void shouldBeFailWhenPhoneNumberIsByte() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);
		Integer integer = 155;

		map.put("phone",integer.byteValue());

		validator.validar(map);
	}

	@Test
	public void phoneNumberLengthExceeded() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);

		String longString = "asdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaasdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaasdfadsaaasdasdfasfasfadfafadfasfsdafdsafaaasdasdfasfasfadfafadfasfsdafdsafadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsaadasfasdfdsafsdfasdfsadfasdfsadsdfdasfsa";

		map.put("phone", longString);

		validator.validar(map);
	}

	@Test
	public void invalidPhoneNumber() throws ValidationException {
		expectedExceptionResults(EXPECTED_INVALID_PHONENUMBER);
		map.put("password", "12345");
		map.put("otherPassword", "12345");

		validator.validar(map);
	}

	private void expectedExceptionResults(String message){
		expectedEx.expect(ValidationException.class);
		expectedEx.expectMessage(message);
	}

}
