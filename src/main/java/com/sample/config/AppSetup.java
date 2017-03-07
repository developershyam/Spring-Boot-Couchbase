package com.sample.config;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.sample.data.model.Book;
import com.sample.data.repository.BookRepository;
import com.sample.dto.DataWrapper;
import com.sample.service.BookService;

/**
 * This class is designed to setup initial configurations.
 * 
 * @author shyam.pareek
 *
 */
@Component
public class AppSetup {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;

	// @Autowired
	// @Qualifier("transactionManager")
	// protected PlatformTransactionManager transactionManager;

	@PostConstruct
	public void init() {

		/*
		 * TransactionTemplate tmpl = new
		 * TransactionTemplate(transactionManager); tmpl.execute(new
		 * TransactionCallbackWithoutResult() {
		 * 
		 * @Override protected void
		 * doInTransactionWithoutResult(TransactionStatus status) {
		 * 
		 * //tearUPDefaultSettings(); } });
		 */
		
		tearUPDefaultSettings();

	}

	private void tearUPDefaultSettings() {

		// Add items in bucket if items are less than 10.
		DataWrapper dataWrapper = bookService.getBooksPage(new PageRequest(0, 5));
		long total = dataWrapper.getTotalElement();
		if(total <10){
			
			for (int i = 0; i < 13-total; i++) {
				
				SecureRandom random = new SecureRandom();
				String name = new BigInteger(130, random).toString(32).substring(0, 3);
				String author = new BigInteger(130, random).toString(32).substring(0, 3);
				String email = name + author + "@" + (new BigInteger(130, random).toString(32).substring(0, 3))
						+ ".com";
				Book book=new Book(null, name, author, email);
				bookService.addBook(book);
			}
		}
		
	}

}
