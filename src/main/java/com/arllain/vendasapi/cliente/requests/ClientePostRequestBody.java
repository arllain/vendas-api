package com.arllain.vendasapi.cliente.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientePostRequestBody {

    private Long id;
    private String nome;
}
