package com.technical.api_metrics_tracker.repository;

import com.technical.api_metrics_tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Additional custom query methods can be added here if needed
}