package br.com.ideiasages.util.tests;
import br.com.ideiasages.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SendMailTest {

	@Test
	public void testSendMail() {
		SendMail s = new SendMail();
		assertNotNull(s);
		s.envio("tests@ats.com", "t@ats.com", "tsts", "corpo");
		
	}

	@Test
	public void testEnvio() {
		SendMail s = new SendMail();
		assertNotNull(s);
		s.envio("tests@ats.com", "t@ats.com", "tsts", "corpo");
	}
}
