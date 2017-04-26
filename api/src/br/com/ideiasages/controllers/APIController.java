package br.com.ideiasages.controllers;

import br.com.ideiasages.exception.NegocioException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class APIController {
	public APIController() {}

	@GET
	@Path("/")
	@Produces("application/json")
	public String index() {
		return "works";
	}
}
