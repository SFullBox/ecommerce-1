package com.senai.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senai.ecommerce.dto.LoginDTO;
import com.senai.ecommerce.dto.UsuarioDTO;
import com.senai.ecommerce.entities.Usuario;
import com.senai.ecommerce.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
		
		Usuario user = new Usuario();
		user.setNome(dto.getNome());	
		user.setEmail(dto.getEmail());
		user.setTelefone(dto.getTelefone());
		user.setSenha(passwordEncoder.encode(dto.getSenha()));
		
		user = usuarioRepository.save(user);
		
		return new UsuarioDTO(user);
	}
	
	public boolean authenticationUser(LoginDTO loginDTO) {//Authenticando usuário com o usuarioDTO
		Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail());// Passando o email para verificar existencia
		
		if(usuario == null) {
			return false;
		}
		return passwordEncoder.matches(loginDTO.getPassword(), usuario.getSenha()); 
		// para comparar as HASHS vamos usar um metodo que verifica
		// até porque se compara o valor puro
		
		
	}
}
