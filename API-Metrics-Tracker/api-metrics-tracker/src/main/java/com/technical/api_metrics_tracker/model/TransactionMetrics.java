package com.technical.api_metrics_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionMetrics {

    @Id
    private Long transactionId;

    @OneToOne
    @MapsId  // Links this entity's transactionId to the primary key of Transaction
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(name = "min_response_time")
    private Long minResponseTime;

    @Column(name = "max_response_time")
    private Long maxResponseTime;

    @Column(name = "avg_response_time")
    private Long avgResponseTime;

    @Column(name = "transaction_count", nullable = false)
    private Integer transactionCount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "response_status")
    private String responseStatus;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Long getMinResponseTime() {
        return minResponseTime;
    }

    public void setMinResponseTime(Long minResponseTime) {
        this.minResponseTime = minResponseTime;
    }

    public Long getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(Long maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public Long getAvgResponseTime() {
        return avgResponseTime;
    }

    public void setAvgResponseTime(Long avgResponseTime) {
        this.avgResponseTime = avgResponseTime;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
