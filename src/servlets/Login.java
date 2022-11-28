package servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forms.AuthentificationForm;


/**
 * Servlet implementation class Login
 */
@WebServlet({ "/login", "/logout" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_LOGIN = "/WEB-INF/login.jsp";
	private static final String VUE_LIST = "/WEB-INF/listerUtilisateur.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		switch (request.getServletPath()) {
		case "/logout":
			request.getSession().invalidate();
			response.sendRedirect("login");
			break;
		case "/login":
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_LOGIN);
			dispatcher.forward(request, response);
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AuthentificationForm form = new AuthentificationForm();
		boolean connexionFailed = form.connexion(request);
		if (connexionFailed) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE_LIST);
			request.setAttribute("connexionFailed", true);
			request.setAttribute("login", request.getParameter("admin"));
			dispatcher = getServletContext().getRequestDispatcher(VUE_LOGIN);
			dispatcher.forward(request, response);
		} else {

			request.getSession().setAttribute("isConnected", true);
			response.sendRedirect("list");

		}

	}

}
