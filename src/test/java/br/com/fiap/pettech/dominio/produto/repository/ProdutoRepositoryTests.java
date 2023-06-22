package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entitie.Produto;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;
    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        UUID id = UUID.fromString("e23c5a59-886f-461d-9423-18e1cfa8d4b5");
        Optional<Produto> result = produtoRepository.findById(id);
        Assertions.assertTrue(result.isPresent());
    }
    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista(){
        UUID id = UUID.fromString("e23c5a59-886f-461d-9423-18e1cfa8d4b6");
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        });
    }
    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull(){
        Produto produto = new Produto();
        produto.setNome("Ração");
        produto.setDescricao("Ração para cachorro");
        produto.setPreco(100.00);
        produto.setUrlImage("url imagem");
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }
}
