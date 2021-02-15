package com.arllain.vendasapi.cliente.service;


import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.cliente.repository.ClienteRepository;
import com.arllain.vendasapi.util.ClienteCreator;
import com.arllain.vendasapi.util.ClientePostRequestBodyCreator;
import com.arllain.vendasapi.util.ClientePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Cliente Service")
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepositoryMock;

    @BeforeEach
    public void setUp() {
        PageImpl<Cliente> clientePage = new PageImpl<Cliente>(List.of(ClienteCreator.createValidCliente()));

        BDDMockito.when(clienteRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(clientePage);

        BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ClienteCreator.createValidCliente()));

        BDDMockito.when(clienteRepositoryMock.save(ArgumentMatchers.any(Cliente.class)))
                .thenReturn(ClienteCreator.createValidCliente());
        
        BDDMockito.doNothing().when(clienteRepositoryMock).deleteById(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("Test should return a list of clientes inside page object when successful")
    public void findAll_ReturnsListOfClientesInsidePageObject_WhenSuccessful() {
        String expectedName = ClienteCreator.createValidCliente().getNome();

        Page<Cliente> clientePage = clienteService.findAll(PageRequest.of(1,1));

        Assertions.assertThat(clientePage).isNotNull();

        Assertions.assertThat(clientePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(clientePage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Test should find a cliente by id when successful")
    public void findById_ReturnsCliente_WhenSuccessful(){
        Long expectedId = ClienteCreator.createValidCliente().getId();

        Cliente cliente = clienteService.findById(1L);

        Assertions.assertThat(cliente).isNotNull();

        Assertions.assertThat(cliente.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Test should throws BadRequestException when cliente not found")
    public void throwBadRequestException_when_Cliente_not_found(){

        BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> clienteService.findById(1L));

    }

    @Test
    @DisplayName("Test should save a cliente when successful")
    public void save_Cliente_WhenSuccessful() {
        Cliente cliente = clienteService.save(ClientePostRequestBodyCreator.createClientePostRequestBody());

        Assertions.assertThat(cliente).isNotNull().isEqualTo(ClienteCreator.createValidCliente());
    }

    @Test
    @DisplayName("Test should update a Cliente when succesful")
    void replace_UpdatesCliente_WhenSuccessful(){

        Assertions.assertThatCode(() ->clienteService.update(ClientePutRequestBodyCreator.createClientePutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Test should delete a cliente by id")
    public void deleteById_Cliente(){

        Assertions.assertThatCode(() ->  clienteService.deleteById(1L)).doesNotThrowAnyException();
    }

}
