package com.arllain.vendasapi.util;

import com.arllain.vendasapi.cliente.requests.ClientePutRequestBody;

public class ClientePutRequestBodyCreator {

    public static ClientePutRequestBody createClientePutRequestBody() {
        return ClientePutRequestBody.builder()
                .id(ClienteCreator.createValidUpdatedCliente().getId())
                .nome(ClienteCreator.createValidUpdatedCliente().getNome())
                .build();
    }
}
