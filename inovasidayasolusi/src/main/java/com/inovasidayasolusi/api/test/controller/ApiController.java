package com.inovasidayasolusi.api.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inovasidayasolusi.api.test.model.Status;
import com.inovasidayasolusi.api.test.model.Transaction;
import com.inovasidayasolusi.api.test.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class ApiController {
	
	private final TransactionService transactionService;
	
    public ApiController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
	
	@GetMapping(value = "/")
	public String home() {
		return "Welcome";
	}
	
	@PostMapping("/insertData")
	public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
	    try {
	        Transaction savedTransaction = transactionService.insertData(transaction);
	        return ResponseEntity.ok(savedTransaction);
	    } catch (Exception e) {
	        e.printStackTrace();
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", "error");
	        errorResponse.put("message", e.getMessage());
	        return ResponseEntity.badRequest().body(errorResponse);
	    }
	}
	
	@PostMapping("/insertStatus")
	public ResponseEntity<?> createStatus(@RequestBody Status status) {
	    try {
	        Status savedStatus = transactionService.insertStatus(status);
	        return ResponseEntity.ok(status);
	    } catch (Exception e) {
	        e.printStackTrace();

	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", "error");
	        errorResponse.put("message", e.getMessage());
	        return ResponseEntity.badRequest().body(errorResponse);
	    }
	}
	
	@GetMapping("/getList")
	public Map<String, Object> getAllTransactions() {
		List<Transaction> transaction = transactionService.getAllTransaction();
		List<Status> status = transactionService.getAllStatus();
		
		Map<String, Object> response = new HashMap<>();
        response.put("data", transaction);
        response.put("status", status);
        return response;
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?>  getTransactionsById(@PathVariable int id) {
		try {
			Transaction data = transactionService.getTransactionById(id);
			return ResponseEntity.ok(data);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> error = new HashMap<>();
            error.put("status", "error");
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
	}

}
