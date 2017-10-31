package com.springboot.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.entiy.Usuario;
import com.springboot.api.enums.EnumStatusAtivo;
import com.springboot.api.repositories.UsuarioRepository;
import com.springboot.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		log.info("SERVICE: Buscar usuario por ID {}", id);
		return Optional.ofNullable(usuarioRepository.findOne(id));
	}

	public Optional<Usuario> buscarPorCpf(String cpf) {
		log.info("SERVICE: Buscar usuario por CPF {}", cpf);
		return Optional.ofNullable(usuarioRepository.findByCpf(cpf));
	}

	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("SERVICE: Buscar usuario por Email {}", email);
		return Optional.ofNullable(usuarioRepository.findByEmail(email));
	}

	public Optional<Usuario> buscarPorCpfOuEmail(String cpf, String email) {
		log.info("SERVICE: Buscar usuario por CPF {} ou email {}", cpf, email);
		return Optional.ofNullable(usuarioRepository.findByCpfOrEmail(cpf, email));
	}

	public Usuario salvar(Usuario usuario) {
		log.info("SERVICE: Salvar usuario {}", usuario);
		return usuarioRepository.save(usuario);
	}

	public Usuario alterarStatus(Long idUsuario, EnumStatusAtivo status) {
		log.info("SERVICE: Alterar status usuario {}, para {}", idUsuario, status);
		Usuario usuario = usuarioRepository.findOne(idUsuario);
		usuario.setStatusAtivo(status);
		return usuarioRepository.save(usuario);
	}
}
