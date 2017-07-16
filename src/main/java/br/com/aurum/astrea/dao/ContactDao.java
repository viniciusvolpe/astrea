package br.com.aurum.astrea.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;

import br.com.aurum.astrea.domain.Contact;

public class ContactDao {
	
	static {
		ObjectifyService.register(Contact.class);
	}
	
	public void save(Contact contact) {
		ObjectifyService.ofy().save().entities(contact).now();
	}
	
	public List<Contact> list() {
		return ObjectifyService.ofy().load().type(Contact.class).list();
	}
	public Contact findOne(Long contactId) {
		return ObjectifyService.ofy().load().type(Contact.class).id(contactId).now();
	}

	public void delete(Contact contact) {
		ObjectifyService.ofy().delete().entity(contact).now();
	}
}
