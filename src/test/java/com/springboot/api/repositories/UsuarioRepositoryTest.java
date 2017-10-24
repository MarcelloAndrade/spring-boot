package com.springboot.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.api.entiy.Usuario;
import com.springboot.api.enums.EnumPerfil;
import com.springboot.api.utils.PasswordUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private static final String CPF = "38531201213";
	private static final String EMAIL = "test@test";

	@Before
	public void before() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Marcello Andrade");
		usuario.setCpf(CPF);
		usuario.setEmail(EMAIL);
		usuario.setSenha(PasswordUtil.gerarBCrypt("senhadeteste"));
		usuario.setDataNascimento(new Date());
		usuario.setPerfil(EnumPerfil.ROLE_USUARIO);
		this.usuarioRepository.save(usuario);
	}
	
	@After
    public final void after() { 
		this.usuarioRepository.deleteAll();
	}

	@Test
	public void testBuscarPorCpf() {
		Usuario usuario = this.usuarioRepository.findByCpf(CPF);
		assertEquals(CPF, usuario.getCpf());
	}
	
	@Test
	public void testBuscarPorEmail() {
		Usuario usuario = this.usuarioRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, usuario.getEmail());
	}
	
	@Test
	public void testBuscarPorCpfOuEmailOndeCpfTrue() {
		Usuario usuario = this.usuarioRepository.findByCpfOrEmail(CPF, "");
		assertEquals(CPF, usuario.getCpf());
		assertEquals(EMAIL, usuario.getEmail());
	}
	
	@Test
	public void testBuscarPorCpfOuEmailOndeEmailTrue() {
		Usuario usuario = this.usuarioRepository.findByCpfOrEmail("", EMAIL);
		assertEquals(CPF, usuario.getCpf());
		assertEquals(EMAIL, usuario.getEmail());
	}
}

