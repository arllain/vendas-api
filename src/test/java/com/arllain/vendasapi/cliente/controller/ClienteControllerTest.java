package com.arllain.vendasapi.cliente.controller;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.cliente.requests.ClientePostRequestBody;
import com.arllain.vendasapi.cliente.requests.ClientePutRequestBody;
import com.arllain.vendasapi.cliente.service.ClienteService;
import com.arllain.vendasapi.util.cliente.ClienteCreator;
import com.arllain.vendasapi.util.cliente.ClientePostRequestBodyCreator;
import com.arllain.vendasapi.util.cliente.ClientePutRequestBodyCreator;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Cliente Controller")
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteServiceMock;

    @BeforeEach
    public void setUp() {
        PageImpl<Cliente> clientePage = new PageImpl<Cliente>(List.of(ClienteCreator.createValidCliente()));

        BDDMockito.when(clienteServiceMock.findAll(ArgumentMatchers.any())).thenReturn(clientePage);

        BDDMockito.when(clienteServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteCreator.createValidCliente());

        BDDMockito.when(clienteServiceMock.save(ArgumentMatchers.any(ClientePostRequestBody.class)))
                .thenReturn(ClienteCreator.createValidCliente());

        BDDMockito.doNothing().when(clienteServiceMock).update(ArgumentMatchers.any(ClientePutRequestBody.class));

        BDDMockito.doNothing().when(clienteServiceMock).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Test should return a list of clientes inside page object when successful")
    public void findAll_ReturnsListOfClientesInsidePageObject_WhenSuccessful() {
        String expectedName = ClienteCreator.createValidCliente().getNome();

        Page<Cliente> clientePage = clienteController.findAll(null).getBody();

        Assertions.assertThat(clientePage).isNotNull();

        Assertions.assertThat(clientePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(clientePage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Test should find a cliente by id when successful")
    public void findById_ReturnsCliente_WhenSuccessful(){
        Long expectedId = ClienteCreator.createValidCliente().getId();

        Cliente cliente = clienteController.findById(1L).getBody();

        Assertions.assertThat(cliente).isNotNull();

        Assertions.assertThat(cliente.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Test should save a cliente when successful")
    public void save_Cliente_WhenSuccessful() {
        Cliente cliente = clienteController.save(ClientePostRequestBodyCreator.createClientePostRequestBody()).getBody();

        Assertions.assertThat(cliente).isNotNull().isEqualTo(ClienteCreator.createValidCliente());
    }

    @Test
    @DisplayName("Test should update a Cliente when succesful")
    void replace_UpdatesCliente_WhenSuccessful(){

        Assertions.assertThatCode(() ->clienteController.update(ClientePutRequestBodyCreator.createClientePutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = clienteController.update(ClientePutRequestBodyCreator.createClientePutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Test should delete a cliente by id")
    public void deleteById_Cliente(){

        Assertions.assertThatCode(() ->  clienteController.deleteById(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> cliente = clienteController.deleteById(1L);

        Assertions.assertThat(cliente).isNotNull();

        Assertions.assertThat(cliente.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
