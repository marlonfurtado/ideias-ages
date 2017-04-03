package br.com.ideaisages.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ideaisages.bo.UserBO;
import br.com.ideaisages.model.User;
import br.com.ideaisages.util.Util;



/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

    
   	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		
		// TODO Auto-generated method stub
		response.getWriter().append( email + " - " + senha ).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		UserBO usuarioBO = new UserBO();
		Util util = new Util();

		String usuario = request.getParameter("login");
		String senha = request.getParameter("senha");

		User usuarioDTO = new User(usuario, senha);

		try {

			if (usuarioBO.validaUser(usuarioDTO)) {
				request.getSession().setAttribute("usuarioSessao", user);
				request.getSession().setAttribute("versao", util.getVersion());

			
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}

		System.out.println("PASSEI AQUI");
		//doGet(request, response);
	}

}
