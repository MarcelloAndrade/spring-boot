package com.springboot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.api.entiy.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly = true)
	Usuario findByCpf(String cpf); 
	
	@Transactional(readOnly = true)
	Usuario findByEmail(String email);

	@Transactional(readOnly = true)
	Usuario findByCpfOrEmail(String cpf, String email);
}
