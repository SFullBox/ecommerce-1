package com.senai.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.ecommerce.dto.UsuarioDTO;
import com.senai.ecommerce.entities.Usuario;
import com.senai.ecommerce.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setTelefone(dto.getTelefone());
		usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	@Transactional
	public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO dto) {
		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setTelefone(dto.getTelefone());
		
		if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
			usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
		}

		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	@Transactional
	public void deletarUsuario(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		usuarioRepository.delete(usuario);
	}

	public UsuarioDTO buscarPorId(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return new UsuarioDTO(usuario);
	}

	public boolean autenticaUsuario(UsuarioDTO dto) {
		Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
		if (usuario == null) {
			return false;
		}
		return passwordEncoder.matches(dto.getSenha(), usuario.getSenha());
	}
}