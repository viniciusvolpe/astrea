package br.com.aurum.astrea.controller;

import java.util.List;
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

	public List<Contact> findAll() {
		return DAO.list();
	}

	public void delete(Long contactId) {
		Validate.notNull(contactId, "Código do contato não informado.");
		Contact contact = DAO.findOne(contactId);
		Validate.notNull(contact, "Contato não encontrado para o código informado.");
		DAO.delete(contact);
	}
}
