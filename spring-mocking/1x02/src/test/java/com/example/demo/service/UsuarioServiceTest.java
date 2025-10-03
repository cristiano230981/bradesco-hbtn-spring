package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Cristiano", "cristiano@email.com");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarUsuarioPorId(id);

        assertNotNull(resultado);
        assertEquals("Cristiano", resultado.getNome());
        assertEquals("cristiano@email.com", resultado.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        Long id = 99L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(id));
        assertEquals("Usuário não encontrado", excecao.getMessage());
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario usuario = new Usuario(null, "Ana", "ana@email.com");
        Usuario usuarioSalvo = new Usuario(2L, "Ana", "ana@email.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuarioSalvo);

        Usuario resultado = usuarioService.salvarUsuario(usuario);

        assertNotNull(resultado.getId());
        assertEquals("Ana", resultado.getNome());
        assertEquals("ana@email.com", resultado.getEmail());
    }
}