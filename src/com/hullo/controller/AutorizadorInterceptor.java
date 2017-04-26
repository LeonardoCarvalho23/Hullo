package com.hullo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// Esta classe é um interceptador, que controla se o usuario está logado e, caso nao esteja
// manda-o para a tela de login
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	  @Override
	  public boolean preHandle(HttpServletRequest request, 
	      HttpServletResponse response,
	      Object controller) throws Exception {

	      String uri = request.getRequestURI();
	      if(uri.contains("usuario") ||
	          uri.contains("formAluno") ||
	          uri.contains("newAluno") ||
	          uri.contains("newProfessor") ||
	          uri.contains("formProfessor") ||
	          uri.contains("twilio") ||
	          uri.contains("twilioWebApp") ||
	          uri.contains("welcome") ||
	                   uri.contains("resources")){
	        return true;
	      }

	      if(request.getSession()
	          .getAttribute("usuario") != null) {
	        return true;
	      }

	      response.sendRedirect("../usuario/usuarioLogin");
	      return false;
	  }
	}
