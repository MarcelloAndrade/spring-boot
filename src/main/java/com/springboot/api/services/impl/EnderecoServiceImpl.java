package com.springboot.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.entiy.Endereco;
import com.springboot.api.repositories.EnderecoRepository;
import com.springboot.api.services.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	
	private static final Logger log = LoggerFactory.getLogger(EnderecoServiceImpl.class);
	
	@Autowired
	private EnderecoRepository enderecoRepository;

		public Optional<Endereco> buscarPorId(Long id) {
		log.info("SERVICE: Buscar endereco por id {}", id);
		return Optional.ofNullable(enderecoRepository.findOne(id));
	}
}
