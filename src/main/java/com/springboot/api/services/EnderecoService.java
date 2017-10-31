package com.springboot.api.services;

import java.util.Optional;

import com.springboot.api.entiy.Endereco;

public interface EnderecoService {
	
	Optional<Endereco> buscarPorId(Long id);

}
