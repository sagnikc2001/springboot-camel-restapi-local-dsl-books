package com.springcameldslbookslocal.dao;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.springcameldslbookslocal.model.Books;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	private final Map<Integer, Books> books = new TreeMap<>();
	
//	Constructor to create default values
	public BookServiceImpl()
	{
		books.put(1, new Books(1, "Gullivers Travels", "Jonathan Swift", "Fiction", 10));
		books.put(2, new Books(2, "Frankenstein", "Mary Shelley", "Fiction", 5));
		books.put(3, new Books(3, "Harry Potter", "JK Rowling", "Fiction", 100));
	}

	@Override
	public Collection<Books> getBooks() {
		return books.values();
	}

	@Override
	public Books getBook(int isbn) {
		return books.get(isbn);
	}

	@Override
	public void addBook(Books book) {
		
		int size = books.size();
		books.put(size+1, book);
	}

}
