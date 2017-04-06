package br.com.ideiasages.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



/**
 * Servlet Filter implementation class LoginFilter
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {
	
	Logger logger = Logger.getLogger("servlet.FileUploadServlet");
	private static final String[] URLS_TO_EXCLUDE = {".css", ".js", ".jpg", ".png", ".gif","/index.html","/modeloRest/api/login","/modeloFront/", "/modeloRest/" };

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		logger.info("Filtro de Login Finalizado " + new Date());
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);


		String uri = httpRequest.getRequestURI();
		
		// trocado pelo m�todo isURLToExclusao(uri) (!uri.endsWith("login.jsp") && !uri.endsWith(".css") 
	
		if (!isURLToExclusao(uri, httpRequest)) {
			HttpSession session = httpRequest.getSession();
			if (session.getAttribute("user") == null) {
				request.setAttribute("msgAviso", "Acesso Negado");
				request.getRequestDispatcher("index.html").forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("Filtro de Login Inicializado " + new Date());
	}
	
	private boolean isURLToExclusao(String uri, HttpServletRequest request) {
		boolean retorno = false;

		for (String url : URLS_TO_EXCLUDE) {
			if(uri != null && uri.endsWith(url)){
				retorno = true; 
			}
//			
//			if(uri != null && uri.endsWith("main") 
//					&& (acao != null 
//					&& acao.equals("login")
//					|| (acao.equals("recuperarSenha")))){
//				retorno = true;
//			}
		}
		return retorno;
	}
	

}
