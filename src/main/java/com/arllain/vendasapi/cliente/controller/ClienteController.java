package com.arllain.vendasapi.cliente.controller;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.cliente.requests.ClientePostRequestBody;
import com.arllain.vendasapi.cliente.requests.ClientePutRequestBody;
import com.arllain.vendasapi.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Cliente>> findAll(Pageable pageable){
        return ResponseEntity.ok(clienteService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClientePostRequestBody clientePostRequestBody) {
        return new ResponseEntity<>(clienteService.save(clientePostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ClientePutRequestBody clientePutRequestBody) {
        clienteService.update(clientePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
