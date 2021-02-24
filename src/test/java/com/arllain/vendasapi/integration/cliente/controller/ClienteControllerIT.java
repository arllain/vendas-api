package com.arllain.vendasapi.integration.cliente.controller;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.cliente.repository.ClienteRepository;
import com.arllain.vendasapi.cliente.requests.ClientePostRequestBody;
import com.arllain.vendasapi.util.ClienteCreator;
import com.arllain.vendasapi.util.ClientePostRequestBodyCreator;
import com.arllain.vendasapi.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ClienteControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @LocalServerPort
    private int port;


    @Test
    @DisplayName("Test should return a list of clientes inside page object when successful")
    public void findAll_ReturnsListOfClientesInsidePageObject_WhenSuccessful() {

        Cliente clienteSaved = clienteRepository.save(ClienteCreator.createClienteToBeSaved());
        String expectedName = clienteSaved.getNome();

        PageableResponse<Cliente> clientePage = testRestTemplate.exchange("/api/v1/clientes/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Cliente>>() {
                }).getBody();


        Assertions.assertThat(clientePage).isNotNull();

        Assertions.assertThat(clientePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(clientePage.toList().get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Test should find a cliente by id when successful")
    public void findById_ReturnsCliente_WhenSuccessful(){
        Cliente clienteSaved = clienteRepository.save(ClienteCreator.createClienteToBeSaved());

        Long expectedId = clienteSaved.getId();

        Cliente cliente = testRestTemplate.getForObject("/api/v1/clientes/{id}", Cliente.class, expectedId);

        Assertions.assertThat(cliente).isNotNull();

        Assertions.assertThat(cliente.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Test should save a cliente when successful")
    public void save_Cliente_WhenSuccessful() {
        ClientePostRequestBody clientePostRequestBody = ClientePostRequestBodyCreator.createClientePostRequestBody();
        ResponseEntity<Cliente> clienteResponseEntity = testRestTemplate.postForEntity("/api/v1/clientes", clientePostRequestBody, Cliente.class);

        Assertions.assertThat(clienteResponseEntity).isNotNull();
        Assertions.assertThat(clienteResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(clienteResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(clienteResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("Test should update a Cliente when succesful")
    void replace_UpdatesCliente_WhenSuccessful(){

        Cliente clienteSaved  = clienteRepository.save(ClienteCreator.createClienteToBeSaved());

        clienteSaved.setNome("new name");

        ResponseEntity<Void> entity = testRestTemplate.exchange("/api/v1/clientes", HttpMethod.PUT,
                new HttpEntity<>(clienteSaved), Void.class);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Test should delete a cliente by id")
    public void deleteById_Cliente(){

        Cliente clienteSaved  = clienteRepository.save(ClienteCreator.createClienteToBeSaved());

        clienteSaved.setNome("new name");

        ResponseEntity<Void> entity = testRestTemplate.exchange("/api/v1/clientes/{id}", HttpMethod.DELETE,
                null, Void.class, clienteSaved.getId());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
