package com.senai.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.senai.ecommerce.dto.UsuarioDTO;
import com.senai.ecommerce.entities.Usuario;
import com.senai.ecommerce.repositories.UsuarioRepository;

public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Salva usuário no banco (com senha criptografada)
    public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
        Usuario user = new Usuario();  // Transforma o DTO em Entity
        user.setTelefone(dto.getTelefone());
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));  // CRITICO: nunca salva senha pura!
        
        user = usuarioRepository.save(user);  // Mandou pro banco
        
        return new UsuarioDTO(user);  // Devolve DTO (não expõe a Entity)
    }
    
    // Verifica se email e senha estão corretos
    public boolean autenticaUsuario(UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
        
        if(usuario == null) {  // Se não achar o email, já falha
            return false;
        }
        // Compara senha digitada com a do banco (já criptografada)
        return passwordEncoder.matches(dto.getSenha(), usuario.getSenha());
    }
}