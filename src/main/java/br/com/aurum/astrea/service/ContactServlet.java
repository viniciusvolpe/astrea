package br.com.aurum.astrea.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.Gson;

import br.com.aurum.astrea.controller.ContactController;
import br.com.aurum.astrea.domain.Contact;

@SuppressWarnings("serial")
public class ContactServlet extends HttpServlet {
	
	private static final ContactController CONTROLLER = new ContactController();
	private static final Gson GSON = new Gson();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Contact contact = GSON.fromJson(req.getReader(), Contact.class);
		CONTROLLER.save(contact);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Contact> contacts = CONTROLLER.findAll();
		resp.getWriter().write(GSON.toJson(contacts));
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long contactId = Long.valueOf(req.getParameter("id"));
		CONTROLLER.delete(contactId);
	}
}
