package com.technical.api_metrics_tracker.repository;

import com.technical.api_metrics_tracker.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface traderRepository extends JpaRepository<Trader, Long> {

    // Find all traders associated with a specific transaction
    List<Trader> findByTransaction_TransactionId(Long transactionId);
}