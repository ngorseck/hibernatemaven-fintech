package com.samanecorp.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.fintech.dto.UserDTO;
import com.samanecorp.fintech.service.LoginService;

/**
 * Servlet implementation class SingUpServlet
 */
@WebServlet(name = "singup", value = "/singup")
public class SingUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private LoginService loginService;
	private final String LOGIN_PAGE = "index.jsp";
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		loginService = new LoginService();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		loadIndex(null, req, resp);
	}

	private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("mail");
		String password = req.getParameter("pwd");
		logger.info("Tentative d'inscription avec {}", email);
		String message = null;
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(email);
			userDTO.setPassword(password);
			boolean result = loginService.save(userDTO);
			if (result) {
				message = "informations ajoutées dans la base de données.";
				logger.info("{}", message);
			}
		} catch (Exception e) {
			message = "informations non ajoutées.";
			logger.error("{}", message);
		}
		loadIndex(message, req, resp);
	}

}
