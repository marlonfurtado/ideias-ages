package br.com.ideiasages.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.ideiasages.bo.PasswordChangeBO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.PasswordChangeRequest;
import br.com.ideiasages.model.PasswordChangeWrapper;
import br.com.ideiasages.util.MensagemContantes;

@Path("password")
public class PasswordChangeContoller {
	
	Logger logger = Logger.getLogger("controller.AuthController");
	
    private PasswordChangeBO passwordChangeBO = new PasswordChangeBO();

    @POST
    @Path("/validateRecoverPasswordToken")
    @Consumes("application/text; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO validateRecoverPasswordToken(String token) {
        StandardResponseDTO response = new StandardResponseDTO();

        try {
        	PasswordChangeRequest passwordChangeRequest = passwordChangeBO.veifyToken(token);
            
            if(passwordChangeRequest == null) {
            	response.setMessage(MensagemContantes.MSG_ERR_INVALID_PASSWORD_CHANGE_REQUEST);
                response.setSuccess(false);
            	return response;
            }
            
            response.setSuccess(true);
            response.setMessage(passwordChangeRequest.getUser().getCpf());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
    
    @POST
    @Path("/changePassword")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO changePassword(PasswordChangeWrapper passwordChangeWrapper) {
    	
    	StandardResponseDTO response = new StandardResponseDTO();
    	
    	try {
    		passwordChangeBO.changePassword(passwordChangeWrapper);
    		
    		response.setSuccess(true);
    		response.setMessage("Senha atualizada com sucesso!");
    	} catch (Exception e) {
    		response.setSuccess(false);
    		response.setMessage(e.getMessage());
    	}
    	
    	return response;
    }
}
