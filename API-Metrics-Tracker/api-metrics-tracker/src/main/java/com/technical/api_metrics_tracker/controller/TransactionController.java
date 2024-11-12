package com.technical.api_metrics_tracker.controller;

import com.technical.api_metrics_tracker.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint to log transaction and related details
    @PostMapping("/log")
    public ResponseEntity<String> logTransaction(@RequestBody TransactionDTO request,
                                                 HttpServletRequest servletRequest) { // Added servletRequest as a parameter
        long startTime = (long) servletRequest.getAttribute("startTime");  // Get the start time from the interceptor

        try {

            // Extract attributes from all 4 entities and log/store them
            long transactionId = request.getTransactionId();
            String transactionType = request.getTransactionType();
            int quantity = request.getQuantity();
            double transactionPrice = request.getTransactionPrice();

            // Extract Trader details
            long traderId = request.getTraderId();
            String traderName = request.getTraderName();
            double traderAccountBalance = request.getTraderAccountBalance();
            String traderPhoneNumber = request.getTraderPhoneNumber();

            // Extract Derivative Contract details
            long contractId = request.getContractId();
            String contractType = request.getContractType();
            double contractStrikePrice = request.getContractStrikePrice();
            String contractExpirationDate = request.getContractExpirationDate();
            String contractUnderlyingAsset = request.getContractUnderlyingAsset();

            long responseTime = request.getResponseTime();

            // Call the service to handle logging/storing the data
            transactionService.logTransaction(transactionId, transactionType, quantity, transactionPrice, traderId, traderName,
                    traderAccountBalance, traderPhoneNumber, contractId, contractType,
                    contractStrikePrice, contractExpirationDate, contractUnderlyingAsset, startTime, HttpStatus.CREATED.value());

            return new ResponseEntity<>("Transaction and related details logged successfully", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while logging the transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<String> getTransactionDetails(@PathVariable long transactionId) {
        // Call the service method to fetch transaction details by transactionId
        String transactionDetails = transactionService.getTransactionDetailsById(transactionId);

        // If transaction details are found, return them in string format
        if (transactionDetails != null) {
            return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}
