package com.arllain.vendasapi.produto.mapper;

import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.produto.requests.ProdutoPostRequestBody;
import com.arllain.vendasapi.produto.requests.ProdutoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    public static final ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    public abstract Produto toProduto(ProdutoPostRequestBody produtoPostRequestBody);

    public abstract Produto toProduto(ProdutoPutRequestBody produtoPutRequestBody);

}