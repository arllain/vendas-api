package com.arllain.vendasapi.produto.repository;

import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.util.produto.ProdutoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Produto Repository")
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Test should save Produto ")
    void should_save_produto() {
        Produto produtoToBeSaved = ProdutoCreator.createProdutoToBeSaved();

        Produto produtoSaved = this.produtoRepository.save(produtoToBeSaved);

        Assertions.assertThat(produtoSaved).isNotNull();

        Assertions.assertThat(produtoSaved.getId()).isNotNull();

        Assertions.assertThat(produtoSaved.getDescricao()).isEqualTo(produtoSaved.getDescricao());

    }

    @Test
    @DisplayName("Test should throws ConstraintViolationException when descricao is empty")
    void should_throws_ConstraintViolationException_when_descricao_is_empty() {
        Produto produtoToBeSaved = new Produto();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.produtoRepository.save(produtoToBeSaved))
                .withMessageContaining("The Produto descricao cannot be empty");
    }

    @Test
    @DisplayName("Test should update produto ")
    void should_update_produto() {
        Produto produtoToBeSaved = ProdutoCreator.createProdutoToBeSaved();

        Produto produtoSaved = this.produtoRepository.save(produtoToBeSaved);

        produtoSaved.setDescricao("produto updated");
        produtoSaved.setPreco(new BigDecimal(20));

        Produto produtoUpdated = this.produtoRepository.save(produtoSaved);

        Assertions.assertThat(produtoUpdated).isNotNull();

        Assertions.assertThat(produtoUpdated.getDescricao()).isEqualTo(produtoSaved.getDescricao());
    }
    @Test
    @DisplayName("Test should delete produto ")
    void should_delete_cliente() {
        Produto produtoToBeSaved = ProdutoCreator.createProdutoToBeSaved();

        Produto produtoSaved = this.produtoRepository.save(produtoToBeSaved);

        this.produtoRepository.deleteById(produtoSaved.getId());

        Optional<Produto> produtoOptional = this.produtoRepository.findById(produtoSaved.getId());

        Assertions.assertThat(produtoOptional).isEmpty();
    }
}
