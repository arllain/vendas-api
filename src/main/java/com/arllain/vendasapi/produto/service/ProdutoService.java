package com.arllain.vendasapi.produto.service;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.cliente.mapper.ClienteMapper;
import com.arllain.vendasapi.cliente.requests.ClientePutRequestBody;
import com.arllain.vendasapi.produto.entity.Produto;
import com.arllain.vendasapi.produto.mapper.ProdutoMapper;
import com.arllain.vendasapi.produto.repository.ProdutoRepository;
import com.arllain.vendasapi.produto.requests.ProdutoPostRequestBody;
import com.arllain.vendasapi.produto.requests.ProdutoPutRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;

    public ProdutoService(@Autowired ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Produto> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, this.getClass().getName()
                + " not found"));
    }

    @Transactional
    public Produto save(ProdutoPostRequestBody produtoPostRequestBody) {
        return produtoRepository.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequestBody));
    }

    public void update(ProdutoPutRequestBody produtoPutRequestBody) {
        Produto savedProduto = findById(produtoPutRequestBody.getId());
        Produto produtoToUpdate = ProdutoMapper.INSTANCE.toProduto(produtoPutRequestBody);
        produtoToUpdate.setId(savedProduto.getId());
        produtoRepository.save(produtoToUpdate);
    }

    public void deleteById(long id) {
        produtoRepository.deleteById(id);
    }
}
