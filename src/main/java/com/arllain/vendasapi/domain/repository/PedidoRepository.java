package com.arllain.vendasapi.domain.repository;

import com.arllain.vendasapi.domain.entity.Cliente;
import com.arllain.vendasapi.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    Set<Pedido> findByCliente(Cliente cliente);

}
