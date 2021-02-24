package com.arllain.vendasapi.util.cliente;

import com.arllain.vendasapi.cliente.requests.ClientePostRequestBody;

public class ClientePostRequestBodyCreator {

    public static ClientePostRequestBody createClientePostRequestBody() {
        return ClientePostRequestBody.builder()
                .nome(ClienteCreator.createClienteToBeSaved().getNome())
                .build();
    }
}
