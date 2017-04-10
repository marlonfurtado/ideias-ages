package br.com.ideiasages.command;

import javax.servlet.http.HttpServletRequest;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.Util;


public class LoginCommand implements Command {

	private UserBO userBO;

	private String proxima;
	
	private Util util;

	@Override
	public String execute(HttpServletRequest request) {
		// seta a mesma pagina, para o caso de erro/exce��o
		proxima = "login.jsp";
		User user = new User();
		userBO = new UserBO();
		util = new Util();

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		User usuarioDTO = new User(email, senha);

		try {

			
			if( userBO.validaUser(usuarioDTO)) {
				
				request.getSession().setAttribute("usuarioSessao", user);
				request.getSession().setAttribute("versao", util.getVersion());
				proxima = "index.jsp";
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		
		return proxima;
	}
}
