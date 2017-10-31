package com.springboot.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilTest {

	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

	@Test
	public void testSenhaNula() {
		assertNull(PasswordUtil.gerarBCrypt(null));
	}

	@Test
	public void testGerarHashSenha() {
		String hash = PasswordUtil.gerarBCrypt(SENHA);

		assertTrue(bCryptEncoder.matches(SENHA, hash));
	}

}
