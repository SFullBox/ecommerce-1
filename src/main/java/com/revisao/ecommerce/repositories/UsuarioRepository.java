package com.revisao.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revisao.ecommerce.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
