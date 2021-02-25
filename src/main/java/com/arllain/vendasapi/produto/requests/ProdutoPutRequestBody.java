package com.arllain.vendasapi.produto.requests;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutoPutRequestBody {

    private Long id;
    private String descricao;
    private BigDecimal preco;
}