package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {
    @Autowired
    private IProdutoRepository produtoRepository;

    public Page<ProdutoDTO> findAll(PageRequest pagina) {
        var produtos = produtoRepository.findAll(pagina);
        return produtos.map(prod -> new ProdutoDTO(prod));
    }

    public ProdutoDTO findById(UUID id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return new ProdutoDTO(produto);
    }

    public ProdutoDTO save(ProdutoDTO produto) {
        Produto entity = new Produto();
        entity.setNome(produto.getNome());
        entity.setDescricao(produto.getDescricao());
        entity.setUrlImage(produto.getUrlImagem());
        entity.setPreco(produto.getPreco());
        var produtoSaved =  produtoRepository.save(entity);
        return new ProdutoDTO(produtoSaved);
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produto) {
        try {
            Produto buscaProduto = produtoRepository.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImage(produto.getUrlImagem());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = produtoRepository.save(buscaProduto);
            return new ProdutoDTO(buscaProduto);
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
