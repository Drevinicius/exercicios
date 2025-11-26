package com.meuprojeto.biblioteca.service;

import com.meuprojeto.biblioteca.model.Livro;
import com.meuprojeto.biblioteca.model.Membro;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author andrevinicius
 */

/**
 * Classe que gerencia as operações da biblioteca.
 * Controla o catálogo de livros e a lista de membros.
 */
public class Biblioteca {
    private final List<Livro> catalogoLivros;
    private final List<Membro> listaMembros;

    /**
     * Construtor que inicializa as listas vazias.
     */
    public Biblioteca() {
        this.catalogoLivros = new ArrayList<>();
        this.listaMembros = new ArrayList<>();
    }

    /**
     * Adiciona um livro ao catálogo.
     * 
     * @param livro Livro a ser adicionado
     * @throws IllegalArgumentException se o livro for null ou ID duplicado
     */
    public void adicionarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser null");
        }
        if (buscarLivroPorId(livro.getId()) != null) {
            throw new IllegalArgumentException("Já existe um livro com ID: " + livro.getId());
        }
        catalogoLivros.add(livro);
    }

    /**
     * Remove um livro do catálogo pelo ID.
     * 
     * @param idLivro ID do livro a ser removido
     * @throws IllegalArgumentException se o livro não for encontrado ou estiver emprestado
     */
    public void removerLivro(String idLivro) {
        Livro livro = buscarLivroPorId(idLivro);
        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado com ID: " + idLivro);
        }
        if (!livro.isDisponivel()) {
            throw new IllegalArgumentException("Não é possível remover livro emprestado: " + idLivro);
        }
        catalogoLivros.remove(livro);
    }

    /**
     * Registra um novo membro na biblioteca.
     * 
     * @param membro Membro a ser registrado
     * @throws IllegalArgumentException se o membro for null ou ID duplicado
     */
    public void registrarMembro(Membro membro) {
        if (membro == null) {
            throw new IllegalArgumentException("Membro não pode ser null");
        }
        if (buscarMembroPorId(membro.getId()) != null) {
            throw new IllegalArgumentException("Já existe um membro com ID: " + membro.getId());
        }
        listaMembros.add(membro);
    }

    /**
     * Remove um membro da biblioteca pelo ID.
     * 
     * @param idMembro ID do membro a ser removido
     * @throws IllegalArgumentException se o membro não for encontrado ou tiver livros emprestados
     */
    public void removerMembro(String idMembro) {
        Membro membro = buscarMembroPorId(idMembro);
        if (membro == null) {
            throw new IllegalArgumentException("Membro não encontrado com ID: " + idMembro);
        }
        if (!membro.getLivrosEmprestados().isEmpty()) {
            throw new IllegalArgumentException("Membro possui livros emprestados: " + idMembro);
        }
        listaMembros.remove(membro);
    }

    /**
     * Realiza o empréstimo de um livro para um membro.
     * 
     * @param idLivro ID do livro a ser emprestado
     * @param idMembro ID do membro que vai emprestar
     * @throws IllegalArgumentException se livro ou membro não forem encontrados
     * @throws IllegalStateException se o livro não estiver disponível
     */
    public void emprestarLivro(String idLivro, String idMembro) {
        Livro livro = buscarLivroPorId(idLivro);
        Membro membro = buscarMembroPorId(idMembro);
        
        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado com ID: " + idLivro);
        }
        if (membro == null) {
            throw new IllegalArgumentException("Membro não encontrado com ID: " + idMembro);
        }
        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro não está disponível: " + idLivro);
        }
        
        livro.marcarComoEmprestado();
        membro.emprestarLivro(livro);
        System.out.println("Livro '" + livro.getTitulo() + "' emprestado com sucesso");
    }

    /**
     * Realiza a devolução de um livro por um membro.
     * 
     * @param idLivro ID do livro a ser devolvido
     * @param idMembro ID do membro que vai devolver
     * @throws IllegalArgumentException se livro ou membro não forem encontrados
     * @throws IllegalStateException se o livro não estiver emprestado ou não estiver com o membro
     */
    public void devolverLivro(String idLivro, String idMembro) {
        Livro livro = buscarLivroPorId(idLivro);
        Membro membro = buscarMembroPorId(idMembro);
        
        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado com ID: " + idLivro);
        }
        if (membro == null) {
            throw new IllegalArgumentException("Membro não encontrado com ID: " + idMembro);
        }
        if (livro.isDisponivel()) {
            throw new IllegalStateException("Livro já está disponível: " + idLivro);
        }
        
        // Verifica se o membro realmente tem o livro emprestado
        boolean membroTemLivro = membro.getLivrosEmprestados().stream()
                .anyMatch(l -> l.getId().equals(idLivro));
        
        if (!membroTemLivro) {
            throw new IllegalStateException("Livro não está emprestado para este membro: " + idLivro);
        }
        
        livro.marcarComoDisponivel();
        membro.devolverLivro(livro);
    }

    /**
     * Busca um livro pelo ID.
     * 
     * @param idLivro ID do livro
     * @return Livro encontrado ou null se não existir
     */
    public Livro buscarLivroPorId(String idLivro) {
        return catalogoLivros.stream()
                .filter(livro -> livro.getId().equals(idLivro))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca um membro pelo ID.
     * 
     * @param idMembro ID do membro
     * @return Membro encontrado ou null se não existir
     */
    public Membro buscarMembroPorId(String idMembro) {
        return listaMembros.stream()
                .filter(membro -> membro.getId().equals(idMembro))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retorna lista de todos os livros disponíveis.
     * 
     * @return Lista de livros disponíveis
     */
    public List<Livro> listarLivrosDisponiveis() {
        return catalogoLivros.stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
    }

    /**
     * Retorna lista de livros emprestados por um membro específico.
     * 
     * @param idMembro ID do membro
     * @return Lista de livros emprestados
     * @throws IllegalArgumentException se o membro não for encontrado
     */
    public List<Livro> listarLivrosEmprestadosPorMembro(String idMembro) {
        Membro membro = buscarMembroPorId(idMembro);
        if (membro == null) {
            throw new IllegalArgumentException("Membro não encontrado com ID: " + idMembro);
        }
        return membro.getLivrosEmprestados();
    }

    // Getters para acesso às listas completas
    public List<Livro> getCatalogoLivros() { return new ArrayList<>(catalogoLivros); }
    public List<Membro> getListaMembros() { return new ArrayList<>(listaMembros); }
    
    public static void main(String[] args) {
        // String id, String titulo, String autor, int anoPublicacao
        Livro lNovo = new Livro("1", "Pequeno principe", "Andre Magno", 1999);
        
        //String id, String nome
        Membro mNovo = new Membro("1", "Andre");
        
        // <Vazio>
        Biblioteca bNovo = new Biblioteca();
        
        bNovo.adicionarLivro(lNovo);
        bNovo.registrarMembro(mNovo);
       
        bNovo.emprestarLivro(lNovo.getId(), mNovo.getId());
    }
}
