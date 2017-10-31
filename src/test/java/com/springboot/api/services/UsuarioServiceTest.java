package com.springboot.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.api.entiy.Usuario;
import com.springboot.api.enums.EnumStatusAtivo;
import com.springboot.api.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private static final String CPF = "38531201213";
	private static final String EMAIL = "test@teste";
	
	@Before
	public void before() {
		BDDMockito.given(this.usuarioRepository.findByCpf(Mockito.anyString())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findByEmail(Mockito.anyString())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findByCpfOrEmail(Mockito.anyString(), Mockito.anyString())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findOne(Mockito.anyLong())).willReturn(new Usuario());
	}
	
	@Test
	public void testBuscarPorCpf() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorCpf(CPF);
		assertTrue(usuario.isPresent());
	}
	
	@Test
	public void testBuscarPorEmail() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(EMAIL);
		assertTrue(usuario.isPresent());
	}
	
	@Test
	public void testBuscarPorCpfOuEmail() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorCpfOuEmail(CPF, EMAIL);
		assertTrue(usuario.isPresent());
	}
	
	@Test
	public void testSalvarUsuario() {
		Usuario usuario = this.usuarioService.salvar(new Usuario());
		assertNotNull(usuario);
	}
	
	@Test
	public void testAlterarStatus() {
		Usuario usuario = this.usuarioService.alterarStatus(new Long(1), EnumStatusAtivo.NAO);
		assertNotNull(usuario);
	}
}
