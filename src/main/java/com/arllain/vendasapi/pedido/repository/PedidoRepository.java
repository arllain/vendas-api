package com.arllain.vendasapi.pedido.repository;

import com.arllain.vendasapi.cliente.entity.Cliente;
import com.arllain.vendasapi.pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    Set<Pedido> findByCliente(Cliente cliente);

}
