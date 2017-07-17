package br.com.aurum.astrea.contact;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import br.com.aurum.astrea.controller.ContactController;
import br.com.aurum.astrea.dao.ContactDao;
import br.com.aurum.astrea.domain.Contact;

@PrepareForTest( { ContactDao.class, ContactController.class })
public class ContactTest {
	
	@Rule
	public PowerMockRule rule = new PowerMockRule();
	private ContactController controller;
	@Mock
	private ContactDao dao;
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks(this);
		PowerMockito.whenNew(ContactDao.class).withNoArguments().thenReturn(dao);
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveSalvarContatoNulo(){
		Contact contact = null;
		controller = new ContactController();
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
		controller = new ContactController();
		try {
			controller.save(contact);
		} catch (Exception e){
			Assert.assertEquals("Dados do contato estão inválidos.", e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void deveSalvarUmContatoComDadosValidos() throws Exception{
		Mockito.doNothing().when(dao).save(Mockito.any(Contact.class));
		controller = new ContactController();
		Contact contact = new Contact();
		contact.setName("Vinicius");
		controller.save(contact);
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveDeletarSeNaoForInformadoId(){
		controller = new ContactController();
		try{
			controller.delete(null);			
		} catch(Exception e){
			Assert.assertEquals("Código do contato não informado.", e.getMessage());
			throw e;
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveDeletarSeNaoEncontrarOContatoInformado() throws Exception{
		controller = new ContactController();
		try {
			controller.delete(1L);			
		} catch(Exception e){
			Assert.assertEquals("Contato não encontrado para o código informado.", e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void deveDeletarOContatoQuandoEncontrado() throws Exception{
		Contact contact = new Contact();
		Mockito.when(dao.findOne(1L)).thenReturn(contact);
		Mockito.doNothing().when(dao).delete(Mockito.any(Contact.class));
		controller = new ContactController();
		controller.delete(1L);
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveConsultarUmContatoSeOIdForNulo(){
		try {
			controller = new ContactController();
			controller.findOne(null);			
		} catch(Exception e){
			Assert.assertEquals("Código do contato não informado.", e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void deveFiltrarContatosPeloNome() throws Exception{
		Mockito.when(dao.list()).thenReturn(buildContactList());
		controller = new ContactController();
		List<Contact> contacts = controller.findByFilter("Vinicius");
		Assert.assertEquals(1, contacts.size());
	}
	
	@Test
	public void deveFiltrarContatosPeloCpf() throws Exception{
		Mockito.when(dao.list()).thenReturn(buildContactList());
		controller = new ContactController();
		List<Contact> contacts = controller.findByFilter("12345678901");
		Assert.assertEquals(1, contacts.size());
	}
	
	@Test
	public void deveFiltrarContatosPeloTelefone() throws Exception{
		Mockito.when(dao.list()).thenReturn(buildContactList());
		controller = new ContactController();
		List<Contact> contacts = controller.findByFilter("4899998888");
		Assert.assertEquals(1, contacts.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveFiltrarConFiltroNulo(){
		try {
			controller = new ContactController();
			controller.findByFilter(null);			
		} catch(Exception e){
			Assert.assertEquals("Filtro não informado.", e.getMessage());
			throw e;
		}
	}

	private List<Contact> buildContactList() {
		Contact c1 = new Contact();
		c1.setName("Vinicius");
		c1.setCpf("12345678901");
		c1.setPhones(Arrays.asList("4899998888"));
		
		Contact c2 = new Contact();
		c2.setName("Ana");
		
		Contact c3 = new Contact();
		c3.setName("João");
		return Arrays.asList(c1, c2, c3);
	}
}
