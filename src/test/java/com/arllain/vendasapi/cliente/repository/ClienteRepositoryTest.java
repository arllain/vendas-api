package com.arllain.vendasapi.cliente.repository;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Cliente Repository")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Test should save cliente ")
    void should_save_cliente() {
        Cliente clienteToBeSaved = ClienteCreator.createClienteToBeSaved();

        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);

        Assertions.assertThat(clienteSaved).isNotNull();

        Assertions.assertThat(clienteSaved.getId()).isNotNull();

        Assertions.assertThat(clienteSaved.getNome()).isEqualTo(clienteToBeSaved.getNome());

    }

    @Test
    @DisplayName("Test should throws ConstraintViolationException when nome is empty")
    void should_throws_ConstraintViolationException_when_nome_is_empty() {
        Cliente clienteToBeSaved = new Cliente();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.clienteRepository.save(clienteToBeSaved))
                .withMessageContaining("The Cliente name cannot be empty");
    }

    @Test
    @DisplayName("Test should update cliente ")
    void should_update_cliente() {
        Cliente clienteToBeSaved = ClienteCreator.createClienteToBeSaved();

        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);

        clienteSaved.setNome("cliente updated");

        Cliente clienteUpdated = this.clienteRepository.save(clienteSaved);

        Assertions.assertThat(clienteUpdated).isNotNull();

        Assertions.assertThat(clienteUpdated.getNome()).isEqualTo(clienteSaved.getNome());
    }
    @Test
    @DisplayName("Test should delete cliente ")
    void should_delete_cliente() {
        Cliente clienteToBeSaved = ClienteCreator.createClienteToBeSaved();

        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);

        this.clienteRepository.deleteById(clienteSaved.getId());

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteSaved.getId());

        Assertions.assertThat(clienteOptional).isEmpty();
    }


}