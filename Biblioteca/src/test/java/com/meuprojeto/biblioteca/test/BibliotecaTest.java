package com.meuprojeto.biblioteca.test;

import com.meuprojeto.biblioteca.model.Livro;
import com.meuprojeto.biblioteca.model.Membro;
import com.meuprojeto.biblioteca.service.Biblioteca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Biblioteca.
 * Verifica todas as operações do sistema de biblioteca.
 */
@DisplayName("Testes para a classe Biblioteca")
class BibliotecaTest {

    private Biblioteca biblioteca;
    private Livro livro1;
    private Livro livro2;
    private Membro membro1;
    private Membro membro2;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca();
        
        // Livros de teste
        livro1 = new Livro("ISBN-001", "Dom Casmurro", "Machado de Assis", 1899);
        livro2 = new Livro("ISBN-002", "O Cortiço", "Aluísio Azevedo", 1890);
        
        // Membros de teste
        membro1 = new Membro("MEM-001", "João Silva");
        membro2 = new Membro("MEM-002", "Maria Santos");
        
        // Adiciona alguns livros e membros para testes
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.registrarMembro(membro1);
        biblioteca.registrarMembro(membro2);
    }

    @Test
    @DisplayName("Deve adicionar livro com sucesso")
    void testAdicionarLivroSucesso() {
        // Arrange
        Livro novoLivro = new Livro("ISBN-003", "Iracema", "José de Alencar", 1865);
        
        // Act
        biblioteca.adicionarLivro(novoLivro);
        
        // Assert
        assertEquals(novoLivro, biblioteca.buscarLivroPorId("ISBN-003"),
            "Livro deve estar no catálogo após adição");
    }

    @Test
    @DisplayName("Deve lançar exceção ao adicionar livro null")
    void testAdicionarLivroNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.adicionarLivro(null));
        
        assertEquals("Livro não pode ser null", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao adicionar livro com ID duplicado")
    void testAdicionarLivroIdDuplicado() {
        // Arrange
        Livro livroDuplicado = new Livro("ISBN-001", "Outro Livro", "Outro Autor", 2000);
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.adicionarLivro(livroDuplicado));
        
        assertEquals("Já existe um livro com ID: ISBN-001", exception.getMessage());
    }

    @Test
    @DisplayName("Deve remover livro com sucesso")
    void testRemoverLivroSucesso() {
        // Act
        biblioteca.removerLivro("ISBN-001");
        
        // Assert
        assertNull(biblioteca.buscarLivroPorId("ISBN-001"),
            "Livro não deve mais estar no catálogo após remoção");
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover livro não existente")
    void testRemoverLivroNaoExistente() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.removerLivro("ISBN-999"));
        
        assertEquals("Livro não encontrado com ID: ISBN-999", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover livro emprestado")
    void testRemoverLivroEmprestado() {
        // Arrange
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.removerLivro("ISBN-001"));
        
        assertEquals("Não é possível remover livro emprestado: ISBN-001", exception.getMessage());
    }

    @Test
    @DisplayName("Deve registrar membro com sucesso")
    void testRegistrarMembroSucesso() {
        // Arrange
        Membro novoMembro = new Membro("MEM-003", "Carlos Oliveira");
        
        // Act
        biblioteca.registrarMembro(novoMembro);
        
        // Assert
        assertEquals(novoMembro, biblioteca.buscarMembroPorId("MEM-003"),
            "Membro deve estar na lista após registro");
    }

    @Test
    @DisplayName("Deve lançar exceção ao registrar membro com ID duplicado")
    void testRegistrarMembroIdDuplicado() {
        // Arrange
        Membro membroDuplicado = new Membro("MEM-001", "Outro Nome");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.registrarMembro(membroDuplicado));
        
        assertEquals("Já existe um membro com ID: MEM-001", exception.getMessage());
    }

    @Test
    @DisplayName("Deve remover membro com sucesso")
    void testRemoverMembroSucesso() {
        // Act
        biblioteca.removerMembro("MEM-001");
        
        // Assert
        assertNull(biblioteca.buscarMembroPorId("MEM-001"),
            "Membro não deve mais estar na lista após remoção");
    }

    @Test
    @DisplayName("Deve lançar exceção ao remover membro com livros emprestados")
    void testRemoverMembroComLivrosEmprestados() {
        // Arrange
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.removerMembro("MEM-001"));
        
        assertEquals("Membro possui livros emprestados: MEM-001", exception.getMessage());
    }

    @Test
    @DisplayName("Deve emprestar livro disponível para membro registrado")
    void testEmprestarLivroSucesso() {
        // Act
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        
        // Assert
        assertAll("Verificar estado após empréstimo bem-sucedido",
            () -> assertFalse(livro1.isDisponivel(), "Livro deve estar indisponível"),
            () -> assertEquals(1, membro1.getLivrosEmprestados().size(), 
                "Membro deve ter 1 livro emprestado"),
            () -> assertEquals(livro1, membro1.getLivrosEmprestados().get(0),
                "Livro emprestado deve ser o correto")
        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao emprestar livro não existente")
    void testEmprestarLivroNaoExistente() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.emprestarLivro("ISBN-999", "MEM-001"));
        
        assertEquals("Livro não encontrado com ID: ISBN-999", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao emprestar para membro não existente")
    void testEmprestarParaMembroNaoExistente() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.emprestarLivro("ISBN-001", "MEM-999"));
        
        assertEquals("Membro não encontrado com ID: MEM-999", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao emprestar livro indisponível")
    void testEmprestarLivroIndisponivel() {
        // Arrange
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        
        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> biblioteca.emprestarLivro("ISBN-001", "MEM-002"));
        
        assertEquals("Livro não está disponível: ISBN-001", exception.getMessage());
    }

    @Test
    @DisplayName("Deve devolver livro emprestado com sucesso")
    void testDevolverLivroSucesso() {
        // Arrange
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        
        // Act
        biblioteca.devolverLivro("ISBN-001", "MEM-001");
        
        // Assert
        assertAll("Verificar estado após devolução bem-sucedida",
            () -> assertTrue(livro1.isDisponivel(), "Livro deve estar disponível"),
            () -> assertTrue(membro1.getLivrosEmprestados().isEmpty(),
                "Membro não deve ter livros emprestados")
        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao devolver livro não emprestado")
    void testDevolverLivroNaoEmprestado() {
        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> biblioteca.devolverLivro("ISBN-001", "MEM-001"));
        
        assertEquals("Livro já está disponível: ISBN-001", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao devolver livro para membro errado")
    void testDevolverLivroMembroErrado() {
        // Arrange
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        
        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> biblioteca.devolverLivro("ISBN-001", "MEM-002"));
        
        assertEquals("Livro não está emprestado para este membro: ISBN-001", exception.getMessage());
    }

    @Test
    @DisplayName("Deve buscar livro existente")
    void testBuscarLivroExistente() {
        // Act
        Livro encontrado = biblioteca.buscarLivroPorId("ISBN-001");
        
        // Assert
        assertEquals(livro1, encontrado, "Deve retornar o livro correto");
    }

    @Test
    @DisplayName("Deve retornar null ao buscar livro não existente")
    void testBuscarLivroNaoExistente() {
        // Act
        Livro encontrado = biblioteca.buscarLivroPorId("ISBN-999");
        
        // Assert
        assertNull(encontrado, "Deve retornar null para livro não existente");
    }

    @Test
    @DisplayName("Deve listar livros disponíveis corretamente")
    void testListarLivrosDisponiveis() {
        // Arrange
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        
        // Act
        List<Livro> disponiveis = biblioteca.listarLivrosDisponiveis();
        
        // Assert
        assertEquals(1, disponiveis.size(), "Deve haver 1 livro disponível");
        assertEquals(livro2, disponiveis.get(0), "Livro disponível deve ser o ISBN-002");
    }

    @Test
    @DisplayName("Deve listar livros emprestados por membro")
    void testListarLivrosEmprestadosPorMembro() {
        // Arrange
        biblioteca.emprestarLivro("ISBN-001", "MEM-001");
        biblioteca.emprestarLivro("ISBN-002", "MEM-001");
        
        // Act
        List<Livro> emprestados = biblioteca.listarLivrosEmprestadosPorMembro("MEM-001");
        
        // Assert
        assertEquals(2, emprestados.size(), "Membro deve ter 2 livros emprestados");
        assertTrue(emprestados.contains(livro1), "Lista deve conter ISBN-001");
        assertTrue(emprestados.contains(livro2), "Lista deve conter ISBN-002");
    }

    @Test
    @DisplayName("Deve lançar exceção ao listar livros de membro não existente")
    void testListarLivrosEmprestadosMembroNaoExistente() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> biblioteca.listarLivrosEmprestadosPorMembro("MEM-999"));
        
        assertEquals("Membro não encontrado com ID: MEM-999", exception.getMessage());
    }

    @Test
    @DisplayName("Deve manter estado isolado entre testes")
    void testIsolamentoEntreTestes() {
        // Este teste verifica que o @BeforeEach funciona corretamente
        // e cada teste começa com um estado limpo
        
        assertAll("Verificar estado inicial após @BeforeEach",
            () -> assertEquals(2, biblioteca.getCatalogoLivros().size()),
            () -> assertEquals(2, biblioteca.getListaMembros().size()),
            () -> assertTrue(livro1.isDisponivel()),
            () -> assertTrue(livro2.isDisponivel())
        );
    }
}
