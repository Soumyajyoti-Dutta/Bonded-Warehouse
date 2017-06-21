package com.dipak.login.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dipak.login.model.Document;
import com.dipak.login.model.Item;
import com.dipak.login.model.Status;
import com.dipak.login.model.Transaction;

@Service
public interface DocumentService {

	public Status countStatus();

	public List<Document> searchDocumentByStatus(String status);

	public String updateStatus(String id, String status, String timeStamp, String username, String reasonCode);

	// public String searchDocTrxHistory(String id);

	public List<Transaction> searchDocumentByID(String id);

	public List<Document> searchDocumentWithID(String id);

	public List<Item> getItemListByDocumentID(String id);

}
