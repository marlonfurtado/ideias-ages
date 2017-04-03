package br.com.ideaisages.command;

import javax.servlet.http.HttpServletRequest;

import br.com.ideaisages.bo.UserBO;
import br.com.ideaisages.model.User;
import br.com.ideaisages.util.MensagemContantes;

public class AddUserCommand implements Command {


	private String proxima;

	private UserBO userBO;

	@Override
	public String execute(HttpServletRequest request) {
		userBO = new UserBO();
		proxima = "biblioteca";

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		try {
			User user = new User();
			user.setEmail(email);
			user.setPassword(senha);
			
			userBO.cadastraUser(user);
			proxima = "/";
			request.setAttribute("msg", MensagemContantes.MSG_SUC_CADASTRO_USUARIO.replace("?", user.getEmail()));

			
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			
		}

		return proxima;
	}
}
