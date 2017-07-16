package br.com.aurum.astrea.controller;

import java.util.Optional;

import org.apache.commons.lang3.Validate;

import br.com.aurum.astrea.dao.ContactDao;
import br.com.aurum.astrea.domain.Contact;

public class ContactController {
	
	private static final ContactDao DAO = new ContactDao();
	
	public void save(Contact contact) {
		Validate.notNull(contact, "Contato não informado.");
		Validate.isTrue(Optional.ofNullable(contact.getName()).isPresent(), "Dados do contato estão inválidos.");
		DAO.save(contact);
	}
}
