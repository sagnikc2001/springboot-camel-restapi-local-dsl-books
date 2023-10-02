package com.springcameldslbookslocal.dao;

import java.util.Collection;

import com.springcameldslbookslocal.model.*;

public interface BookService {

	Collection<Books> getBooks();
	
	Books getBook(int isbn);
	
	void addBook(Books book);
}
