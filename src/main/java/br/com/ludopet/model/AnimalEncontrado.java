package br.com.ludopet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade AnimalEncontrado.
 *
 * Representa um animal que foi ENCONTRADO por alguém na rua e cadastrado no
 * site para que o verdadeiro dono possa reconhecê-lo e reavê-lo.
 *
 * É o "espelho" dos Animais Perdidos: lá o dono avisa que perdeu; aqui quem
 * achou avisa que encontrou.
 *
 * Cada campo desta classe vira uma COLUNA na tabela do banco de dados.
 * O JPA/Hibernate cria e gerencia essa tabela automaticamente a partir das
 * anotações abaixo.
 */
@Entity
@Table(name = "animais_encontrados")
public class AnimalEncontrado {

    /**
     * Chave primária (identificador único de cada registro).
     * GenerationType.IDENTITY = o próprio banco gera o número, somando +1 a
     * cada novo cadastro (auto incremento).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome dado ao animal (ou "Sem nome" quando não se sabe). */
    private String nome;

    /** Espécie: Cachorro, Gato, etc. */
    private String especie;

    /** Idade aproximada do animal. */
    private String idade;

    /** Cor / pelagem, ajuda o dono a reconhecer. */
    private String cor;

    /** Local onde o animal foi encontrado (bairro, rua, praça...). */
    private String localEncontrado;

    /** Observações: comportamento, estado de saúde, coleira, etc. */
    private String descricao;

    /** Telefone ou e-mail de quem encontrou, para o dono entrar em contato. */
    private String contato;

    /** Caminho/URL da foto do animal. */
    private String imagem;

    // ----- Construtores -----

    /** Construtor vazio: o JPA exige um construtor sem argumentos. */
    public AnimalEncontrado() {
    }

    public AnimalEncontrado(String nome, String especie, String idade, String cor,
                            String localEncontrado, String descricao, String contato,
                            String imagem) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.cor = cor;
        this.localEncontrado = localEncontrado;
        this.descricao = descricao;
        this.contato = contato;
        this.imagem = imagem;
    }

    // ----- Getters e Setters -----
    // O Thymeleaf e o Spring usam esses métodos para LER e GRAVAR cada campo.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getLocalEncontrado() {
        return localEncontrado;
    }

    public void setLocalEncontrado(String localEncontrado) {
        this.localEncontrado = localEncontrado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}