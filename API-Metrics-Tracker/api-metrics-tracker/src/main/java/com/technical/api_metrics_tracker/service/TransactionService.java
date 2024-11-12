package com.technical.api_metrics_tracker.service;

import com.technical.api_metrics_tracker.model.DerivativeContract;
import com.technical.api_metrics_tracker.model.Trader;
import com.technical.api_metrics_tracker.model.Transaction;
import com.technical.api_metrics_tracker.model.TransactionMetrics;
import com.technical.api_metrics_tracker.repository.DerivativeContractRepository;
import com.technical.api_metrics_tracker.repository.TransactionMetricsRepository;
import com.technical.api_metrics_tracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final com.technical.api_metrics_tracker.repository.traderRepository traderRepository;
    private final DerivativeContractRepository derivativeContractRepository;
    private final TransactionMetricsRepository transactionMetricsRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              com.technical.api_metrics_tracker.repository.traderRepository traderRepository,
                              DerivativeContractRepository derivativeContractRepository,
                              TransactionMetricsRepository transactionMetricsRepository) {
        this.transactionRepository = transactionRepository;
        this.traderRepository = traderRepository;
        this.derivativeContractRepository = derivativeContractRepository;
        this.transactionMetricsRepository = transactionMetricsRepository;
    }

    // Method to log/store a transaction and its related details (Trader and Derivative Contract)
    public void logTransaction(long transactionId, String transactionType, int quantity, double transactionPrice,
                               long traderId, String traderName, double traderAccountBalance, String traderPhoneNumber,
                               long contractId, String contractType, double contractStrikePrice, String contractExpirationDate,
                               String contractUnderlyingAsset, long startTime, int statusCode) {
        try {

            // Create and save the Transaction entity
            Transaction transaction = new Transaction();
            transaction.setTransactionId(transactionId);
            transaction.setTransactionType(transactionType);
            transaction.setQuantity(quantity);
            transaction.setTransactionPrice(BigDecimal.valueOf(transactionPrice));
            transaction=transactionRepository.save(transaction);

            //  Create and save the Trader entity
            Trader trader = new Trader();
            trader.setTraderId(traderId);
            trader.setName(traderName);
            trader.setAccountBalance(BigDecimal.valueOf(traderAccountBalance));
            trader.setPhoneNumber(traderPhoneNumber);
            trader.setTransaction(transaction);
            traderRepository.save(trader);

            //  Create and save the DerivativeContract entity
            DerivativeContract derivativeContract = new DerivativeContract();
            derivativeContract.setContractId(contractId);
            derivativeContract.setContractType(contractType);
            derivativeContract.setStrikePrice(BigDecimal.valueOf(contractStrikePrice));
            derivativeContract.setExpirationDate(java.time.LocalDate.parse(contractExpirationDate));
            derivativeContract.setUnderlyingAsset(contractUnderlyingAsset);
            derivativeContract.setTransaction(transaction);

            derivativeContractRepository.save(derivativeContract);


//
            // Step 4: Calculate and save TransactionMetrics
            TransactionMetrics transactionMetrics = calculateMetrics(transaction, startTime, statusCode);
            transactionMetricsRepository.save(transactionMetrics);

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while logging the transaction", e);
        }
    }

    private TransactionMetrics calculateMetrics(Transaction transaction, long startTime, int statusCode) {
        // Retrieve existing metrics for the transaction if present
        TransactionMetrics metrics = transactionMetricsRepository.findById(transaction.getTransactionId())
                .orElse(new TransactionMetrics());

        // Calculate min, max, and average response times
        long responseTime=System.currentTimeMillis() - startTime;
        metrics.setMinResponseTime(metrics.getMinResponseTime() == null ? responseTime : Math.min(metrics.getMinResponseTime(), responseTime));
        metrics.setMaxResponseTime(metrics.getMaxResponseTime() == null ? responseTime : Math.max(metrics.getMaxResponseTime(), responseTime));

        if (metrics.getTransactionCount() == null) {
            metrics.setTransactionCount(1);
            metrics.setAvgResponseTime(responseTime);
        } else {
            int newCount = metrics.getTransactionCount() + 1;
            long totalResponseTime = metrics.getAvgResponseTime() * metrics.getTransactionCount() + responseTime;
            metrics.setTransactionCount(newCount);
            metrics.setAvgResponseTime(totalResponseTime / newCount);
        }

        // Set the response status as a formatted string
        String statusText = HttpStatus.valueOf(statusCode).getReasonPhrase();
        metrics.setResponseStatus(statusCode + " " + statusText);

        // Set other fields
        metrics.setTransaction(transaction);
        metrics.setTimestamp(LocalDateTime.now());

        return metrics;
    }

    // The getTransactionDetailsById method remains unchanged
    public String getTransactionDetailsById(long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(transaction -> "Transaction ID: " + transaction.getTransactionId() +
                        "\nType: " + transaction.getTransactionType() +
                        "\nQuantity: " + transaction.getQuantity() +
                        "\nPrice: " + transaction.getTransactionPrice())
                .orElse("Transaction not found");
    }
}