package br.com.aurum.astrea.service;

import javax.servlet.http.HttpServletResponse;

public interface IServlet {
	
	final String CONTET_TYPE = "application/json";
	final String CHAR_SET = "UTF-8";
	
	default void configResponse(HttpServletResponse response){
		response.setContentType(CONTET_TYPE);
		response.setCharacterEncoding(CHAR_SET);
	}
}
