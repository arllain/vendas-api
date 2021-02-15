package com.arllain.vendasapi.util;

import com.arllain.vendasapi.cliente.entity.Cliente;

public class ClienteCreator {

    public static Cliente createClienteToBeSaved() {
        return Cliente.builder()
                .nome("Terezinha")
                .build();
    }

    public static Cliente createValidCliente() {
        return Cliente.builder()
                .id(1L)
                .nome("Terezinha")
                .build();
    }

    public static Cliente createValidUpdatedCliente() {
        return Cliente.builder()
                .id(1L)
                .nome("Daniel")
                .build();
    }

}
