package com.arllain.vendasapi.domain.repository;

import com.arllain.vendasapi.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
