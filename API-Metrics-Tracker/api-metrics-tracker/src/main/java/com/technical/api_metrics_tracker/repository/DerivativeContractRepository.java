package com.technical.api_metrics_tracker.repository;

import com.technical.api_metrics_tracker.model.DerivativeContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DerivativeContractRepository extends JpaRepository<DerivativeContract, Long> {

    // Find all derivative contracts associated with a specific transaction
    List<DerivativeContract> findByTransactionTransactionId(Long transactionId);
}
