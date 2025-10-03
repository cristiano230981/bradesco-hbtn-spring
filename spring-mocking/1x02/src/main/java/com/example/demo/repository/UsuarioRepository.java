package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Busca um usuário pelo ID
    Optional<Usuario> findById(Long id);

    // Salva um usuário no banco de dados
    Usuario save(Usuario usuario);

}