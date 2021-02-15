package com.arllain.vendasapi.cliente.service;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.cliente.mapper.ClienteMapper;
import com.arllain.vendasapi.cliente.repository.ClienteRepository;
import com.arllain.vendasapi.cliente.requests.ClientePostRequestBody;
import com.arllain.vendasapi.cliente.requests.ClientePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(@Autowired ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;

    }

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, this.getClass().getName()
                        + " not found"));
    }

    @Transactional
    public Cliente save(ClientePostRequestBody clientePostRequestBody) {
        return clienteRepository.save(ClienteMapper.INSTANCE.toCliente(clientePostRequestBody));
    }


    public void deleteById(Long id) {
        clienteRepository.delete(findById(id));
    }

    public void update(ClientePutRequestBody clientePutRequestBody) {
        Cliente savedCliente = findById(clientePutRequestBody.getId());
        Cliente clienteToUpdate = ClienteMapper.INSTANCE.toCliente(clientePutRequestBody);
        clienteToUpdate.setId(savedCliente.getId());
        clienteRepository.save(clienteToUpdate);
    }
}
