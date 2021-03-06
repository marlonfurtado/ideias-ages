package br.com.ideiasages.util;

import org.apache.commons.mail.DefaultAuthenticator;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Classe para realizar o envio de e-mails.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 *
 **/
public class SendMail {
	public SendMail() {
		// TODO Auto-generated constructor stub
	}

	public void envio(String emailDestinatario, String destinatario, String assunto, String corpo) {
		try {
			SimpleEmail email = new SimpleEmail();
			// Servidor SMTP para envio do e-mail
			email.setHostName("smtp.gmail.com");

			// Autentica��o
			email.setAuthenticator(new DefaultAuthenticator("ideiasages@gmail.com", "agesideias2017"));
			email.setSSLOnConnect(true);

			// Destinat�rio
			email.addTo(emailDestinatario, destinatario);

		  // Remetente
			email.setFrom("ages@pucrs.br","AGES - Ag�ncia Experimental de Engenharia de Software" );

			// Assunto
			email.setSubject(assunto);

			// Corpo
			email.setMsg(corpo); // Conte�do

			// Envio
			email.send();

		} catch (EmailException e) {
			// TODO Auto-generated catch block
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
		}

	}
}
