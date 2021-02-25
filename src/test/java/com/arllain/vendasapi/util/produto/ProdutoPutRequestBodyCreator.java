package com.arllain.vendasapi.util.produto;

import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.produto.requests.ProdutoPutRequestBody;

public class ProdutoPutRequestBodyCreator {

    public static ProdutoPutRequestBody createProdutoPutRequestBody() {
        Produto produtoToUpdate = ProdutoCreator.createValidUpdatedProduto();
        return ProdutoPutRequestBody.builder()
                .id(produtoToUpdate.getId())
                .descricao(produtoToUpdate.getDescricao())
                .preco(produtoToUpdate.getPreco())
                .build();
    }
}
