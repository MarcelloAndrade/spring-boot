package com.springboot.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.api.entiy.Endereco;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EnderecoRepositoryTest {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	private static final String CEP = "16220-124";
	private static Long idEndereco;

	@Before
	public void before() throws Exception {
		Endereco endereco = new Endereco();
		endereco.setCep(CEP);
		endereco.setLogradouro("Rua Manoel Pedro Pimentel");
		endereco.setBairro("Continental");
		endereco.setNumero("123");
		endereco.setCidade("Osasco");
		endereco.setComplemento("Bloco A - 332");
		endereco.setEstado("SP");
		Endereco newEndereco = this.enderecoRepository.save(endereco);
		idEndereco = newEndereco.getIdEndereco();
	}
	
	@After
    public final void after() { 
		this.enderecoRepository.deleteAll();
	}

	@Test
	public void testBuscarPorId() {
		Endereco endereco = this.enderecoRepository.findOne(idEndereco);
		assertEquals(CEP, endereco.getCep());
	}
}

