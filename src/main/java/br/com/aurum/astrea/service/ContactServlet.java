package br.com.aurum.astrea.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.Gson;

import br.com.aurum.astrea.controller.ContactController;
import br.com.aurum.astrea.domain.Contact;

@SuppressWarnings("serial")
public class ContactServlet extends HttpServlet {
	
	private static final ContactController CONTROLLER = new ContactController();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Contact contact = new Gson().fromJson(req.getReader(), Contact.class);
		CONTROLLER.save(contact);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// TODO: Implementar um método que irá listar todas as entidades do tipo 'Contato' e devolver para o client essa listagem.
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		// TODO: Implementar um método que irá deletar uma entidade do tipo 'Contato', dado parâmetro de identificação.
	}
}
