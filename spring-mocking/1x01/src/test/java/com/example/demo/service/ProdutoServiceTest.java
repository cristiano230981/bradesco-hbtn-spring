package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deveRetornarProdutoQuandoIdExistir() {
        // Arrange
        Long id = 1L;
        Produto produtoMock = new Produto(id, "Notebook", 3500.00);
        Mockito.when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoMock));

        // Act
        Produto resultado = produtoService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Notebook", resultado.getNome());
        assertEquals(3500.00, resultado.getPreco());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        // Arrange
        Long id = 99L;
        Mockito.when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException excecao = assertThrows(RuntimeException.class, () -> produtoService.buscarPorId(id));
        assertEquals("Produto não encontrado", excecao.getMessage());
    }
}