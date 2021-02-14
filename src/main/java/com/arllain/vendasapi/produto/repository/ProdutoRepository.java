package com.arllain.vendasapi.produto.repository;

import com.arllain.vendasapi.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
