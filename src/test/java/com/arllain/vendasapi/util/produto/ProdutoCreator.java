package com.arllain.vendasapi.util.produto;

import com.arllain.vendasapi.produto.entity.Produto;

import java.math.BigDecimal;

public class ProdutoCreator {

    public static Produto createProdutoToBeSaved() {
        return Produto.builder()
                .descricao("Produto")
                .preco(new BigDecimal(10))
                .build();
    }

    public static Produto createValidProduto() {
        return Produto.builder()
                .id(1L)
                .descricao("Produto")
                .preco(new BigDecimal(10))
                .build();
    }

    public static Produto createValidUpdatedProduto() {
        return Produto.builder()
                .id(1L)
                .descricao("Produto")
                .preco(new BigDecimal(10))
                .build();
    }
}
