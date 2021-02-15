package com.arllain.vendasapi.cliente.mapper;

import com.arllain.vendasapi.cliente.requests.ClientePostRequestBody;
import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.cliente.requests.ClientePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {

    public static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    public abstract Cliente toCliente(ClientePostRequestBody clientePostRequestBody);

    public abstract Cliente toCliente(ClientePutRequestBody clientePutRequestBody);

}

//@Mapper(componentModel = "spring")
//public interface ClienteMapper {
//
//    ClienteMapper INSTANCE = Mappers.getMapper( ClienteMapper.class );
//
//    Cliente toCliente(ClientePostRequestBody clientePostRequestBody);
//    Cliente toCliente(ClientePutRequestBody clientePutRequestBody);
//}