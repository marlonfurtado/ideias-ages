package br.com.ideiasages.command;

import javax.servlet.http.HttpServletRequest;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

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
