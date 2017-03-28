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
	          uri.endsWith("formAluno") ||
	          uri.endsWith("formProfessor") ||
	          uri.contains("adm") ||
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
