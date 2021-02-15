package com.arllain.vendasapi.itempedido.repository;

import com.arllain.vendasapi.itempedido.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository  extends JpaRepository<ItemPedido, Long> {

}
