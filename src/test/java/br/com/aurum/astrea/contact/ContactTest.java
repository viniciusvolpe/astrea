package br.com.aurum.astrea.contact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import br.com.aurum.astrea.controller.ContactController;
import br.com.aurum.astrea.dao.ContactDao;
import br.com.aurum.astrea.domain.Contact;

@PrepareForTest( { ContactDao.class })
public class ContactTest {
	
	@Rule
	public PowerMockRule rule = new PowerMockRule();
	private ContactController controller;
	
	@Before
	public void setup(){
		controller = new ContactController();
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveSalvarContatoNulo(){
		Contact contact = null;
		try{
			controller.save(contact);			
		} catch (Exception e){
			Assert.assertEquals("Contato não informado.", e.getMessage());
			throw e;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveSalvarContatoSemNome(){
		Contact contact = new Contact();
		try {
			controller.save(contact);
		} catch (Exception e){
			Assert.assertEquals("Dados do contato estão inválidos.", e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void deveSalvarUmContatoComDadosValidos() throws Exception{
		mockContactDAO();
		Contact contact = new Contact();
		contact.setName("Vinicius");
		
	}

	private void mockContactDAO() throws Exception {
		ContactDao dao = Mockito.mock(ContactDao.class);
		Mockito.doNothing().when(dao).save(Mockito.any(Contact.class));
		PowerMockito.whenNew(ContactDao.class).withNoArguments().thenReturn(dao);
	}
}
