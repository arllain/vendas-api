package com.arllain.vendasapi.produto.controller;

import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.produto.requests.ProdutoPostRequestBody;
import com.arllain.vendasapi.produto.requests.ProdutoPutRequestBody;
import com.arllain.vendasapi.produto.service.ProdutoService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Produto Controller")
public class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoServiceMock;

    @BeforeEach
    public void setUp() {
        PageImpl<Produto> produtoPage = new PageImpl<Produto>(List.of(ProdutoCreator.createValidProduto()));

        BDDMockito.when(produtoServiceMock.findAll(ArgumentMatchers.any())).thenReturn(produtoPage);

        BDDMockito.when(produtoServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ProdutoCreator.createValidProduto());

        BDDMockito.when(produtoServiceMock.save(ArgumentMatchers.any(ProdutoPostRequestBody.class)))
                .thenReturn(ProdutoCreator.createProdutoToBeSaved());

        BDDMockito.doNothing().when(produtoServiceMock).update(ArgumentMatchers.any(ProdutoPutRequestBody.class));

        BDDMockito.doNothing().when(produtoServiceMock).deleteById(ArgumentMatchers.anyLong());

    }


    @Test
    @DisplayName("Test should return a list of produtos inside page object when successful")
    public void findAll_ReturnsListOfProdutosInsidePageObject_WhenSuccessful() {
        String expectedName = ProdutoCreator.createValidProduto().getDescricao();

        Page<Produto> produtoPage = produtoController.findAll(null).getBody();

        Assertions.assertThat(produtoPage).isNotNull();

        Assertions.assertThat(produtoPage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(produtoPage.toList().get(0).getDescricao()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Test should find a produto by id when successful")
    public void findById_ReturnsProduto_WhenSuccessful(){
        Long expectedId = ProdutoCreator.createValidProduto().getId();

        Produto produto = produtoController.findById(1L).getBody();

        Assertions.assertThat(produto).isNotNull();

        Assertions.assertThat(produto.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Test should save a produto when successful")
    public void save_Produto_WhenSuccessful() {
        Produto produto = produtoController.save(ProdutoPostRequestBodyCreator.createProdutoPostRequestBody()).getBody();

        Assertions.assertThat(produto).isNotNull().isEqualTo(ProdutoCreator.createProdutoToBeSaved());
    }

    @Test
    @DisplayName("Test should update a produto when successful")
    public void update_Produto_WhenSuccessful() {
        Assertions.assertThatCode(() ->produtoController.update(ProdutoPutRequestBodyCreator.createProdutoPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = produtoController.update(ProdutoPutRequestBodyCreator.createProdutoPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Test should delete a produto by id")
    public void deleteById_Produto(){

        Assertions.assertThatCode(() ->  produtoController.deleteById(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> produto = produtoController.deleteById(1L);

        Assertions.assertThat(produto).isNotNull();

        Assertions.assertThat(produto.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
