package com.meuprojeto.biblioteca.test;

import com.meuprojeto.biblioteca.model.Livro;
import com.meuprojeto.biblioteca.model.Membro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Membro.
 * Verifica empréstimo, devolução e gestão de livros.
 */
@DisplayName("Testes para a classe Membro")
class MembroTest {

    private Membro membro;
    private Livro livro;

    @BeforeEach
    void setUp() {
        membro = new Membro("MEM-001", "João Silva");
        livro = new Livro("ISBN-123", "Dom Casmurro", "Machado de Assis", 1899);
    }

    @Test
    @DisplayName("Deve criar membro com dados corretos e lista vazia de livros")
    void testConstrutorEGetters() {
        // Assert
        assertAll("Verificar todos os atributos do membro",
            () -> assertEquals("MEM-001", membro.getId()),
            () -> assertEquals("João Silva", membro.getNome()),
            () -> assertTrue(membro.getLivrosEmprestados().isEmpty(), 
                "Lista de livros emprestados deve estar vazia inicialmente")
        );
    }

    @Test
    @DisplayName("Deve emprestar livro válido para membro")
    void testEmprestarLivroValido() {
        // Act
        membro.emprestarLivro(livro);
        
        // Assert
        List<Livro> livrosEmprestados = membro.getLivrosEmprestados();
        assertEquals(1, livrosEmprestados.size(), 
            "Deve haver 1 livro na lista de emprestados");
        assertEquals(livro, livrosEmprestados.get(0), 
            "O livro emprestado deve ser o mesmo que foi passado");
    }

    @Test
    @DisplayName("Deve lançar exceção ao emprestar livro null")
    void testEmprestarLivroNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> membro.emprestarLivro(null),
            "Deve lançar IllegalArgumentException para livro null");
        
        assertEquals("Livro não pode ser null", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao emprestar livro já emprestado")
    void testEmprestarLivroDuplicado() {
        // Arrange
        membro.emprestarLivro(livro);
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> membro.emprestarLivro(livro),
            "Deve lançar IllegalArgumentException para livro duplicado");
        
        assertEquals("Livro já está emprestado para este membro", exception.getMessage());
    }

    @Test
    @DisplayName("Deve devolver livro que foi emprestado")
    void testDevolverLivroEmprestado() {
        // Arrange
        membro.emprestarLivro(livro);
        
        // Act
        membro.devolverLivro(livro);
        
        // Assert
        assertTrue(membro.getLivrosEmprestados().isEmpty(), 
            "Lista deve estar vazia após devolução");
    }

    @Test
    @DisplayName("Deve lançar exceção ao devolver livro null")
    void testDevolverLivroNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> membro.devolverLivro(null),
            "Deve lançar IllegalArgumentException para livro null");
        
        assertEquals("Livro não pode ser null", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao devolver livro não emprestado")
    void testDevolverLivroNaoEmprestado() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> membro.devolverLivro(livro),
            "Deve lançar IllegalArgumentException para livro não emprestado");
        
        assertEquals("Livro não está emprestado para este membro", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar cópia da lista de livros emprestados")
    void testGetLivrosEmprestadosRetornaCopia() {
        // Arrange
        membro.emprestarLivro(livro);
        
        // Act
        List<Livro> lista1 = membro.getLivrosEmprestados();
        List<Livro> lista2 = membro.getLivrosEmprestados();
        
        // Assert
        assertNotSame(lista1, lista2, 
            "Deve retornar cópias diferentes da lista para evitar modificações externas");
        assertEquals(lista1, lista2, 
            "As cópias devem conter os mesmos elementos");
    }

    @Test
    @DisplayName("Deve considerar membros iguais quando IDs forem iguais")
    void testEquals() {
        // Arrange
        Membro membro1 = new Membro("MEM-001", "João Silva");
        Membro membro2 = new Membro("MEM-001", "Maria Santos");
        Membro membro3 = new Membro("MEM-002", "João Silva");
        
        // Assert
        assertAll("Verificar igualdade baseada no ID",
            () -> assertEquals(membro1, membro2, "Membros com mesmo ID devem ser iguais"),
            () -> assertNotEquals(membro1, membro3, "Membros com IDs diferentes devem ser diferentes")
        );
    }
}
