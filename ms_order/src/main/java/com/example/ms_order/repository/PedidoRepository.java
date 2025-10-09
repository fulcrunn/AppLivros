package com.example.ms_order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ms_order.model.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
} 

