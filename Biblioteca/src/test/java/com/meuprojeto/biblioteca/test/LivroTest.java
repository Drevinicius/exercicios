package com.meuprojeto.biblioteca.test;

import com.meuprojeto.biblioteca.model.Livro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Livro.
 * Verifica construtores, getters, métodos de estado e equals/hashCode.
 */
@DisplayName("Testes para a classe Livro")
class LivroTest {

    @Test
    @DisplayName("Deve criar livro com dados corretos e disponível por padrão")
    void testConstrutorEGetters() {
        // Arrange & Act
        Livro livro = new Livro("ISBN-123", "Dom Casmurro", "Machado de Assis", 1899);
        
        // Assert
        assertAll("Verificar todos os atributos do livro",
            () -> assertEquals("ISBN-123", livro.getId()),
            () -> assertEquals("Dom Casmurro", livro.getTitulo()),
            () -> assertEquals("Machado de Assis", livro.getAutor()),
            () -> assertEquals(1899, livro.getAnoPublicacao()),
            () -> assertTrue(livro.isDisponivel(), "Livro deve estar disponível por padrão")
        );
    }

    @Test
    @DisplayName("Deve marcar livro como emprestado")
    void testMarcarComoEmprestado() {
        // Arrange
        Livro livro = new Livro("ISBN-123", "Dom Casmurro", "Machado de Assis", 1899);
        
        // Act
        livro.marcarComoEmprestado();
        
        // Assert
        assertFalse(livro.isDisponivel(), "Livro deve estar indisponível após empréstimo");
    }

    @Test
    @DisplayName("Deve marcar livro como disponível")
    void testMarcarComoDisponivel() {
        // Arrange
        Livro livro = new Livro("ISBN-123", "Dom Casmurro", "Machado de Assis", 1899);
        livro.marcarComoEmprestado(); // Primeiro marca como emprestado
        
        // Act
        livro.marcarComoDisponivel();
        
        // Assert
        assertTrue(livro.isDisponivel(), "Livro deve estar disponível após devolução");
    }

    @Test
    @DisplayName("Deve considerar livros iguais quando IDs forem iguais")
    void testEquals() {
        // Arrange
        Livro livro1 = new Livro("ISBN-123", "Dom Casmurro", "Machado de Assis", 1899);
        Livro livro2 = new Livro("ISBN-123", "Memórias Póstumas", "Machado de Assis", 1881);
        Livro livro3 = new Livro("ISBN-456", "Dom Casmurro", "Machado de Assis", 1899);
        
        // Assert
        assertAll("Verificar igualdade baseada no ID",
            () -> assertEquals(livro1, livro2, "Livros com mesmo ID devem ser iguais"),
            () -> assertNotEquals(livro1, livro3, "Livros com IDs diferentes devem ser diferentes"),
            () -> assertEquals(livro1, livro1, "Livro deve ser igual a si mesmo"),
            () -> assertNotEquals(livro1, null, "Livro não deve ser igual a null"),
            () -> assertNotEquals(livro1, "Não sou um livro", "Livro não deve ser igual a objeto de outro tipo")
        );
    }

    @Test
    @DisplayName("Deve ter hashCode igual quando IDs forem iguais")
    void testHashCode() {
        // Arrange
        Livro livro1 = new Livro("ISBN-123", "Dom Casmurro", "Machado de Assis", 1899);
        Livro livro2 = new Livro("ISBN-123", "Memórias Póstumas", "Machado de Assis", 1881);
        
        // Assert
        assertEquals(livro1.hashCode(), livro2.hashCode(), 
            "HashCode deve ser igual para livros com mesmo ID");
    }
}
