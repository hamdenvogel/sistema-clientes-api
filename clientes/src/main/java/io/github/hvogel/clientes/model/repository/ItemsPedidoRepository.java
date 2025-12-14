package io.github.hvogel.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.hvogel.clientes.model.entity.ItemPedido;

public interface ItemsPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
