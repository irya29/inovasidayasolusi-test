package com.inovasidayasolusi.api.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inovasidayasolusi.api.test.model.Status;
import com.inovasidayasolusi.api.test.model.Transaction;
import com.inovasidayasolusi.api.test.repository.StatusRepository;
import com.inovasidayasolusi.api.test.repository.TransactionRepository;

@Service
public class TransactionService {
	private final TransactionRepository transRepo;
	private final StatusRepository statusRepo;
	
	public TransactionService(TransactionRepository transactionRepository, StatusRepository statusRepository) {
		this.transRepo = transactionRepository;
		this.statusRepo = statusRepository;
		
	}
	
	public Transaction insertData(Transaction transaction) {
		return transRepo.save(transaction);
		
	}
	
	public Status insertStatus(Status status) {
		return statusRepo.save(status);
		
	}
	
	public List<Transaction> getAllTransaction(){
		return transRepo.findAll();
	}
	
	public List<Status> getAllStatus(){
		return statusRepo.findAll();
	}
	
	public Transaction getTransactionById(int id) {
        return transRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }
	
	public Transaction updateTransaction(int id, Transaction newTransaction) {
        return transRepo.findById(id).map(transaction -> {
            transaction.setProductID(newTransaction.getProductID());
            transaction.setProductName(newTransaction.getProductName());
            transaction.setAmount(newTransaction.getAmount());
            transaction.setCustomerName(newTransaction.getCustomerName());
            transaction.setStatus(newTransaction.getStatus());
            transaction.setTransactionDate(newTransaction.getTransactionDate());
            transaction.setCreateBy(newTransaction.getCreateBy());
            transaction.setCreateOn(newTransaction.getCreateOn());
            return transRepo.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }
	
	public void deleteTransaction(int id) {
        if (!transRepo.existsById(id)) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
        transRepo.deleteById(id);
    }
}
