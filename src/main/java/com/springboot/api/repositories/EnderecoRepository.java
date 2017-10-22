package com.springboot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.api.entiy.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
