package br.com.aurum.astrea.contact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
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
	private ContactDao dao;
	
	@Before
	public void setup() throws Exception{
		dao = Mockito.mock(ContactDao.class);
		PowerMockito.whenNew(ContactDao.class).withNoArguments().thenReturn(dao);
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
		Mockito.doNothing().when(dao).save(Mockito.any(Contact.class));
		Contact contact = new Contact();
		contact.setName("Vinicius");
		controller.save(contact);
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveDeletarSeNaoForInformadoId(){
		try{
			controller.delete(null);			
		} catch(Exception e){
			Assert.assertEquals("Código do contato não informado.", e.getMessage());
			throw e;
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveDeletarSeNaoEncontrarOContatoInformado() throws Exception{
		try {
			controller.delete(1L);			
		} catch(Exception e){
			Assert.assertEquals("Contato não encontrado para o código informado.", e.getMessage());
			throw e;
		}
	}
	
	@Test
	@Ignore
	public void deveDeletarOContatoQuandoEncontrado() throws Exception{
		// TODO corrigir o problema com o mock que não retorna um contato no findOne
		Mockito.doReturn(new Contact()).when(dao).findOne(1L);
		Mockito.doNothing().when(dao).delete(Mockito.any(Contact.class));
		controller = new ContactController();
		controller.delete(1L);
	}
	
	@Test(expected = NullPointerException.class)
	public void naoDeveConsultarUmContatoSeOIdForNulo(){
		try {
			controller.findOne(null);			
		} catch(Exception e){
			Assert.assertEquals("Código do contato não informado.", e.getMessage());
			throw e;
		}
	}
}
