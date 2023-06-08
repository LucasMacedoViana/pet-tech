package br.com.fiap.pettech.dominio.produto.entitie;

import java.util.UUID;

public class Produto {
    private UUID id;
    private String nome;
    private String descricao;
    private String urlImage;
    private double preco;

    public Produto() {
    }

    public Produto(String nome, String descricao, String urlImage, double preco) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.descricao = descricao;
        this.urlImage = urlImage;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", preco=" + preco +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
