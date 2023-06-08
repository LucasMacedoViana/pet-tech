package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {
    @Autowired
    private IProdutoRepository produtoRepository;

    public Collection<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(UUID id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        var produtoSaved =  produtoRepository.save(produto);
        return produtoSaved;
    }

//    public Optional<Produto> update(UUID id, Produto produto) {
//        Optional<Produto> BuscarProduto = this.findById(id);
//        if (BuscarProduto.isPresent()) {
//            produtoRepository.update(id, produto);
//            return Optional.of(produto);
//        }
//        return Optional.empty();
//    }
//
//    public void delete(UUID id) {
//        produtoRepository.delete(id);
//    }
}
