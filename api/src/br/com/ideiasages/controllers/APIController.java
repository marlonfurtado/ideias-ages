package br.com.ideiasages.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.ideiasages.util.Util;
/**
 * @deprecated Classe sem utilidade. Será removida na refatoração de código.
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 08/06/2017
 **/
@Path("/")
public class APIController {


	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	public String index() {
		return "works";
	}

	@GET
	@Path("/version")
	@Produces("application/text; charset=UTF-8")
	public String getVersion() {

		return Util.getVersion();
	}
}
