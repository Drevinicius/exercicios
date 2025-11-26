package com.meuprojeto.biblioteca.model;

import java.util.Objects;

/**
 *
 * @author andrevinicius
 */

/**
 * Classe que representa um livro na biblioteca.
 * Implementa encapsulamento através de atributos privados e métodos públicos controlados.
 */
public class Livro {
    private final String id;
    private final String titulo;
    private final String autor;
    private final int anoPublicacao;
    private boolean disponivel;

    /**
     * Construtor que inicializa os atributos básicos do livro.
     * O livro é marcado como disponível por padrão.
     * 
     * @param id Identificador único do livro
     * @param titulo Título do livro
     * @param autor Autor do livro
     * @param anoPublicacao Ano de publicação
     */
    public Livro(String id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true; // Por padrão, o livro está disponível
    }

    // Getters
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public boolean isDisponivel() { return disponivel; }

    /**
     * Marca o livro como emprestado (não disponível)
     */
    public void marcarComoEmprestado() {
        this.disponivel = false;
    }

    /**
     * Marca o livro como disponível
     */
    public void marcarComoDisponivel() {
        this.disponivel = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Livro{id='%s', titulo='%s', autor='%s', ano=%d, disponivel=%s}",
                id, titulo, autor, anoPublicacao, disponivel);
    }
}
