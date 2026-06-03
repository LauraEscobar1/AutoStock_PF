package com.autostock.repository;

import com.autostock.model.AlertaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaStockRepository extends JpaRepository<AlertaStock, Long> {
}
