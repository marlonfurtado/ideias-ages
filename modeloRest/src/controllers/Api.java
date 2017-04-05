package br.com.ideiasages.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public class api {
	@GET
	@Path("/ping")
	@Produces("application/json")
	public String ping() {
		String date = " testetetetete";
		return date;
	}
}
