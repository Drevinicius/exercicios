package com.meuprojeto.biblioteca.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author andrevinicius
 */

/**
 * Classe que representa um membro da biblioteca.
 * Gerencia a lista de livros emprestados pelo membro.
 */
public class Membro {
    private final String id;
    private final String nome;
    private final List<Livro> livrosEmprestados;

    /**
     * Construtor que inicializa o membro com uma lista vazia de livros emprestados.
     * 
     * @param id Identificador único do membro
     * @param nome Nome completo do membro
     */
    public Membro(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.livrosEmprestados = new ArrayList<>();
    }

    // Getters
    public String getId() { return id; }
    public String getNome() { return nome; }

    /**
     * Retorna uma cópia da lista de livros emprestados para evitar modificações externas.
     * 
     * @return Lista imutável de livros emprestados
     */
    public List<Livro> getLivrosEmprestados() {
        return new ArrayList<>(livrosEmprestados);
    }

    /**
     * Adiciona um livro à lista de livros emprestados do membro.
     * 
     * @param livro Livro a ser emprestado
     * @throws IllegalArgumentException se o livro for null ou já estiver emprestado
     */
    public void emprestarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser null");
        }
        if (livrosEmprestados.contains(livro)) {
            throw new IllegalArgumentException("Livro já está emprestado para este membro");
        }
        livrosEmprestados.add(livro);
    }

    /**
     * Remove um livro da lista de livros emprestados do membro.
     * 
     * @param livro Livro a ser devolvido
     * @throws IllegalArgumentException se o livro for null ou não estiver emprestado
     */
    public void devolverLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser null");
        }
        if (!livrosEmprestados.contains(livro)) {
            throw new IllegalArgumentException("Livro não está emprestado para este membro");
        }
        livrosEmprestados.remove(livro);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membro membro = (Membro) o;
        return Objects.equals(id, membro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Membro{id='%s', nome='%s', livrosEmprestados=%d}",
                id, nome, livrosEmprestados.size());
    }
}
