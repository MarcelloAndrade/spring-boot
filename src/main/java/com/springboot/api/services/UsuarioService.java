package com.springboot.api.services;

import java.util.Optional;

import com.springboot.api.entiy.Usuario;
import com.springboot.api.enums.EnumStatusAtivo;

public interface UsuarioService {
	
	Optional<Usuario> buscarPorId(Long id);
	
	Optional<Usuario> buscarPorCpf(String cpf);
	
	Optional<Usuario> buscarPorEmail(String email);

	Optional<Usuario> buscarPorCpfOuEmail(String cpf, String email);
	
	Usuario salvar(Usuario usuario);
	
	Usuario alterarStatus(Long idUsuario, EnumStatusAtivo status);

}
