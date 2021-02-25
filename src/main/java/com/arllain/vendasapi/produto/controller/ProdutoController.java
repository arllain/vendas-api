package com.arllain.vendasapi.produto.controller;

import com.arllain.vendasapi.cliente.requests.ClientePutRequestBody;
import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.produto.requests.ProdutoPostRequestBody;
import com.arllain.vendasapi.produto.requests.ProdutoPutRequestBody;
import com.arllain.vendasapi.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/all")
    public ResponseEntity<Page<Produto>> findAll(Pageable pageable){
        return ResponseEntity.ok(produtoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoPostRequestBody produtoPostRequestBody) {
        return new ResponseEntity<>(produtoService.save(produtoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(ProdutoPutRequestBody produtoPutRequestBody) {
        produtoService.update(produtoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(long id) {
        produtoService.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
