package com.arllain.vendasapi.pedido.repository;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    Set<Pedido> findByCliente(Cliente cliente);

}
