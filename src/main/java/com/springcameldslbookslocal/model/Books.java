package com.springcameldslbookslocal.model;

public class Books {
	
	private int isbn;
	
	private String bookName;
	
	private String author;
	
	private String genre;
	private int qty;
	
//	Constructor
//	For Creating new default entries in the start of BooksServiceImpl class
	public Books(int isbn, String bookName, String author, String genre, int qty) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.qty = qty;
	}
	
//	Default Constructor for the POST method inside BooksRouteBuilder class
//	 Used To deserialize the incoming JSON data from POST into the Books class
//	Jackson will use this constructor to create instances of the class during deserialization.
	public Books() {
	    // Default constructor
	}

	
//	Getters and setters

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

//	toString
	
	@Override
	public String toString() {
		return "Books [isbn=" + isbn + ", bookName=" + bookName + ", author=" + author + ", genre=" + genre + ", qty="
				+ qty + "]";
	}
	
	
}
