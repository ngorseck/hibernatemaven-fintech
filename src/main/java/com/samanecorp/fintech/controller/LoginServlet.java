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

import com.samanecorp.fintech.service.LoginService;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	private LoginService loginService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		loginService = new LoginService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int val = 10;
		//logger.info("La valeur de val est {}", val);
		//super.doGet(req, resp);
		logger.info("Connexion ...");
		//loginService.loginMockito("", "");
		loginService.logException("contact@samanecorporation.com", "passer123@");
		req.getRequestDispatcher("WEB-INF/welcome.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
