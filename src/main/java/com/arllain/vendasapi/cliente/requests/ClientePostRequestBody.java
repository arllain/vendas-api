package com.arllain.vendasapi.cliente.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePostRequestBody {

    private Long id;
    private String nome;
}
