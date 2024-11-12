package com.technical.api_metrics_tracker.controller;

public class TransactionDTO {

    private long transactionId;
    private String transactionType;
    private int quantity;
    private double transactionPrice;

    // Trader Details
    private long traderId;
    private String traderName;
    private double traderAccountBalance;
    private String traderPhoneNumber;

    // Derivative Contract Details
    private long contractId;
    private String contractType;
    private double contractStrikePrice;
    private String contractExpirationDate;
    private String contractUnderlyingAsset;

    private long responseTime;


    private String responseStatus;

    // Getters and Setters for all fields

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getTraderId() {
        return traderId;
    }

    public void setTraderId(long traderId) {
        this.traderId = traderId;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public double getTraderAccountBalance() {
        return traderAccountBalance;
    }

    public void setTraderAccountBalance(double traderAccountBalance) {
        this.traderAccountBalance = traderAccountBalance;
    }

    public String getTraderPhoneNumber() {
        return traderPhoneNumber;
    }

    public void setTraderPhoneNumber(String traderPhoneNumber) {
        this.traderPhoneNumber = traderPhoneNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public double getContractStrikePrice() {
        return contractStrikePrice;
    }

    public void setContractStrikePrice(double contractStrikePrice) {
        this.contractStrikePrice = contractStrikePrice;
    }

    public String getContractExpirationDate() {
        return contractExpirationDate;
    }

    public void setContractExpirationDate(String contractExpirationDate) {
        this.contractExpirationDate = contractExpirationDate;
    }

    public String getContractUnderlyingAsset() {
        return contractUnderlyingAsset;
    }

    public void setContractUnderlyingAsset(String contractUnderlyingAsset) {
        this.contractUnderlyingAsset = contractUnderlyingAsset;
    }

    // Getter and setter for responseStatus
    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
