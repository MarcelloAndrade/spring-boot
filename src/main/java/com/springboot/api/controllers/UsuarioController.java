package com.springboot.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.dto.EnderecoDto;
import com.springboot.api.dto.UsuarioDto;
import com.springboot.api.entiy.Endereco;
import com.springboot.api.entiy.Usuario;
import com.springboot.api.enums.EnumPerfil;
import com.springboot.api.response.Response;
import com.springboot.api.services.UsuarioService;
import com.springboot.api.utils.PasswordUtil;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> cadastrar(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result){
		log.info("Cadastrar usuário: {}", usuarioDto.toString());
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		
		validarDadosExistentes(usuarioDto, result);
		Usuario usuario = converterDtoParaUsuario(usuarioDto);
		
		if(result.hasErrors()) {
			log.error("Erro validação dados de cadastro de usuário: {}", usuarioDto.toString());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
			
		}
		this.usuarioService.salvar(usuario);
		
		response.setData(this.converterUsuarioParaDto(usuario));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<UsuarioDto>> atualizar(@PathVariable("id") Long id, @Valid @RequestBody UsuarioDto usuarioDto, BindingResult result){
		log.info("Atualizar usuário: {}", usuarioDto.toString());
		Response<UsuarioDto> response = new Response<UsuarioDto>();
		
		Optional<Usuario> usuario = this.usuarioService.buscarPorId(id);
		if(!usuario.isPresent()) {
			result.addError(new ObjectError("usuario", "Usuário não encontrado"));
		}
		
		this.atualizarDadosUsuario(usuario.get(), usuarioDto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.usuarioService.salvar(usuario.get());
		response.setData(this.converterUsuarioParaDto(usuario.get()));

		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "/{id}/endereco")
	public ResponseEntity<Response<EnderecoDto>> atualizarEndereco(@PathVariable("id") Long id, @Valid @RequestBody EnderecoDto enderecoDto, BindingResult result){
		log.info("Atualizar endereco: {}", enderecoDto.toString());
		Response<EnderecoDto> response = new Response<EnderecoDto>();
		
		Optional<Usuario> usuario = this.usuarioService.buscarPorId(id);
		if(!usuario.isPresent()) {
			result.addError(new ObjectError("usuario", "Usuário não encontrado"));
		}
		
		usuario.get().setEndereco(this.atualizarDadosEndereco(usuario.get(), enderecoDto));
		
		if (result.hasErrors()) {
			log.error("Erro validando endereco: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.usuarioService.salvar(usuario.get());
		response.setData(this.converterEnderecoParaDto(usuario.get().getEndereco()));

		return ResponseEntity.ok(response);
	}

	private void atualizarDadosUsuario(Usuario usuario, UsuarioDto usuarioDto, BindingResult result) {
		if (!usuario.getCpf().equals(usuarioDto.getCpf())) {
			this.usuarioService.buscarPorCpf(usuarioDto.getCpf()).ifPresent(user -> result.addError(new ObjectError("cpf", "CPF já cadastrado.")));
			usuario.setCpf(usuarioDto.getCpf());
		}
		
		if (!usuario.getEmail().equals(usuarioDto.getEmail())) {
			this.usuarioService.buscarPorEmail(usuarioDto.getEmail()).ifPresent(user -> result.addError(new ObjectError("email", "Email já cadastrado.")));
			usuario.setEmail(usuarioDto.getEmail());
		}
		
		usuario.setNome(usuarioDto.getNome());
		usuario.setDataNascimento(usuarioDto.getDataNascimento());
		
		if (usuarioDto.getSenha().isPresent()) {
			usuario.setSenha(PasswordUtil.gerarBCrypt(usuarioDto.getSenha().get()));
		}
	}
	
	private Endereco atualizarDadosEndereco(Usuario usuario, EnderecoDto enderecoDto) {
		Endereco endereco = usuario.getEndereco();
		if(endereco == null) {
			endereco = new Endereco();
		}
		endereco.setCep(enderecoDto.getCep());
		endereco.setLogradouro(enderecoDto.getLogradouro());
		endereco.setBairro(enderecoDto.getBairro());
		endereco.setCidade(enderecoDto.getCidade());
		endereco.setComplemento(enderecoDto.getComplemento());
		endereco.setEstado(enderecoDto.getEstado());
		endereco.setNumero(enderecoDto.getNumero());
		
		return endereco;
	}
	
	private Usuario converterDtoParaUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setNome(usuarioDto.getNome());
		usuario.setSenha(PasswordUtil.gerarBCrypt(usuarioDto.getSenha().get()));
		usuario.setDataNascimento(usuarioDto.getDataNascimento());
		usuario.setPerfil(EnumPerfil.ROLE_USUARIO);
		return usuario;
	}

	private UsuarioDto converterUsuarioParaDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setIdUsuario(usuario.getIdUsuario());
		usuarioDto.setCpf(usuario.getCpf());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setSenha(Optional.of(usuario.getSenha()));
		usuarioDto.setDataNascimento(usuario.getDataNascimento());
		usuarioDto.setDataCadastro(usuario.getDataCadastro());
		usuarioDto.setDataAtualizacao(usuario.getDataAtualizacao());
		return usuarioDto;
	}

	private EnderecoDto converterEnderecoParaDto(Endereco endereco) {
		EnderecoDto dto = new EnderecoDto();
		dto.setIdEndereco(endereco.getIdEndereco());
		dto.setCep(endereco.getCep());
		dto.setLogradouro(endereco.getLogradouro());
		dto.setBairro(endereco.getBairro());
		dto.setCidade(endereco.getCidade());
		dto.setComplemento(endereco.getComplemento());
		dto.setEstado(endereco.getEstado());
		dto.setNumero(endereco.getNumero());
		return dto;
	}

	private void validarDadosExistentes(UsuarioDto usuarioDto, BindingResult result) {
		this.usuarioService.buscarPorCpf(usuarioDto.getCpf()).ifPresent(user -> result.addError(new ObjectError("usuario", "CPF já cadastrado.")));
		
		this.usuarioService.buscarPorEmail(usuarioDto.getEmail()).ifPresent(user -> result.addError(new ObjectError("usuario", "E-mail já cadastrado.")));
	}

}
