package com.arllain.vendasapi.produto.service;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.produto.repository.ProdutoRepository;
import com.arllain.vendasapi.util.cliente.ClienteCreator;
import com.arllain.vendasapi.util.cliente.ClientePostRequestBodyCreator;
import com.arllain.vendasapi.util.produto.ProdutoCreator;
import com.arllain.vendasapi.util.produto.ProdutoPostRequestBodyCreator;
import com.arllain.vendasapi.util.produto.ProdutoPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Produto Service")
public class ProdutoServiceTest {

    @InjectMocks
    ProdutoService produtoService;

    @Mock
    ProdutoRepository produtoRepositoryMock;

    @BeforeEach
    public void setUp() {
        PageImpl<Produto> produtoPage = new PageImpl<Produto>(List.of(ProdutoCreator.createValidProduto()));

        BDDMockito.when(produtoRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(produtoPage);

        BDDMockito.when(produtoRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProdutoCreator.createValidProduto()));

        BDDMockito.when(produtoRepositoryMock.save(ArgumentMatchers.any(Produto.class)))
                .thenReturn(ProdutoCreator.createValidProduto());

        BDDMockito.doNothing().when(produtoRepositoryMock).deleteById(ArgumentMatchers.anyLong());


        BDDMockito.when(produtoRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(produtoPage);
    }


    @Test
    @DisplayName("Test should return a list of produtos inside page object when successful")
    public void findAll_ReturnsListOfProdutosInsidePageObject_WhenSuccessful() {
        String expectedDescription = ProdutoCreator.createValidProduto().getDescricao();

        Page<Produto> produtoPage = produtoService.findAll(PageRequest.of(1,1));

        Assertions.assertThat(produtoPage).isNotNull();

        Assertions.assertThat(produtoPage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(produtoPage.toList().get(0).getDescricao()).isEqualTo(expectedDescription);
    }

    @Test
    @DisplayName("Test should find a produto by id when successful")
    public void findById_ReturnsProduto_WhenSuccessful(){

        BDDMockito.when(produtoRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> produtoService.findById(1L));
    }

    @Test
    @DisplayName("Test should save a produto when successful")
    public void save_Produto_WhenSuccessful() {
        Produto produto = produtoService.save(ProdutoPostRequestBodyCreator.createProdutoPostRequestBody());

        Assertions.assertThat(produto).isNotNull().isEqualTo(ProdutoCreator.createValidProduto());
    }

    @Test
    @DisplayName("Test should update a produto when successful")
    public void update_Produto_WhenSuccessful() {
        Assertions.assertThatCode(() ->produtoService.update(ProdutoPutRequestBodyCreator.createProdutoPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Test should delete a produto by id")
    public void deleteById_Produto(){

        Assertions.assertThatCode(() ->  produtoService.deleteById(1L)).doesNotThrowAnyException();
    }
}
