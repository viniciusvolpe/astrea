package br.com.aurum.astrea.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

	public Contact findOne(Long contactId) {
		Validate.notNull(contactId, "Código do contato não informado.");
		return DAO.findOne(contactId);
	}

	public List<Contact> findByFilter(String filter) {
		List<Contact> contacts = DAO.list();
		List<Contact> filteredByName = contacts.stream().filter(c -> c.getName().contains(filter)).collect(Collectors.toList());
		List<Contact> filteredByCpf = contacts.stream().filter(c -> filter.equals(c.getCpf())).collect(Collectors.toList());
		List<Contact> filteredByPhones = contacts.stream().filter(c -> c.getPhones().contains(filter)).collect(Collectors.toList());
		
		Set<Contact> result = new HashSet<>();
		result.addAll(filteredByName);
		result.addAll(filteredByCpf);
		result.addAll(filteredByPhones);
		
		return new ArrayList<>(result);
	}
}
