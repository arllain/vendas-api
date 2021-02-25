package com.arllain.vendasapi.util.produto;

import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.produto.requests.ProdutoPostRequestBody;

public class ProdutoPostRequestBodyCreator {

    public static ProdutoPostRequestBody createProdutoPostRequestBody() {
        Produto produtoToBeSaved = ProdutoCreator.createProdutoToBeSaved();
        return ProdutoPostRequestBody.builder()
                .descricao(produtoToBeSaved.getDescricao())
                .preco(produtoToBeSaved.getPreco())
                .build();
    }
}
