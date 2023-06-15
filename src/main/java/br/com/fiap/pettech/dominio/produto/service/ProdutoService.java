package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Produto findById(UUID id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return produto;
    }

    public Produto save(Produto produto) {
        var produtoSaved =  produtoRepository.save(produto);
        return produtoSaved;
    }

    public Produto update(UUID id, Produto produto) {
        try {
            Produto buscaProduto = produtoRepository.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImage(produto.getUrlImage());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = produtoRepository.save(buscaProduto);

            return buscaProduto;

        } catch (EntityNotFoundException e){
            throw new ControllerNotFoundException("Produto não encontrado, id:" + id);
        }
    }

    public void delete(UUID id) {
        try{
            produtoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Produto não encontrado, id:" + id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade");
        }
    }
}
