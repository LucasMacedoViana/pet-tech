package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private static Set<Produto> produtos;

    static {
        produtos = new LinkedHashSet<>();
        Produto produto1 = new Produto("Ração", "Ração para cachorro", "https://www.petlove.com.br/images/products/224893/product/Ra%C3%A7%C3%A3o_Secos_Premium_Essence_C%C3%A3es_Adultos_Ra%C3%A7as_Pequenas_e_M%C3%A9dias_224893_1.jpg?1623358977", 100.00);
        Produto produto2 = new Produto("Ração2", "Ração para gato", "https://www.petlove.com.br/images/products/224893/product/Ra%C3%A7%C3%A3o_Secos_Premium_Essence_C%C3%A3es_Adultos_Ra%C3%A7as_Pequenas_e_M%C3%A9dias_224893_1.jpg?1623358977", 100.00);
        produtos.add(produto1);
        produtos.add(produto2);
    }

    @Override
    public Optional<Produto> findById(UUID id) {
        return produtos.stream().filter(produto -> produto.getId().equals(id)).findFirst();
    }

    @Override
    public Set<Produto> findAll() {
        return produtos;
    }

    @Override
    public Produto save(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    @Override
    public Produto update(UUID id, Produto produto) {
        var produtoBuscado = produtos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        produtoBuscado.setNome(produto.getNome());
        produtoBuscado.setDescricao(produto.getDescricao());
        produtoBuscado.setPreco(produto.getPreco());
        produtoBuscado.setUrlImage(produto.getUrlImage());
        return produtoBuscado;
    }

    @Override
    public void delete(UUID id) {
        produtos.removeIf(produto -> produto.getId().equals(id));
    }
}
