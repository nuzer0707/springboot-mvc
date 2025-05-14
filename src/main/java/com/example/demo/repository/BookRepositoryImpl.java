package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository{
	//InMemory 版
	private List<Book> books = new CopyOnWriteArrayList<Book>();
	
	//初始資料有 4 本書
	{
		books.add(new Book(1,"小叮噹",12.5,20,false));
		books.add(new Book(2,"老夫子",10.5,30,false));
		books.add(new Book(3,"好小子",8.5,40, true));
		books.add(new Book(4,"尼羅河",14.5,50,true));
	}
	
	
	public List<Book> findAllBooks(){
		return books;
	}
	
	public Optional<Book> getBookById(Integer id){
		
		return books.stream().filter(book->book.getId().equals(id)).findFirst();
		
	}
	
	public boolean addBook(Book book) {
		
		OptionalInt optMaxId = books.stream().mapToInt(Book::getId).max();
		int newId = optMaxId.isEmpty() ? 1 : optMaxId.getAsInt() + 1;
	
		book.setId(newId);
		return	books.add(book);
	}
	
	public boolean updateBook(Integer id,Book updateBook) {
		
		Optional<Book> optBook = getBookById(id);
		
		if(optBook.isEmpty()) {
			return false;
		}
		
		int index = books.indexOf(optBook.get());
		if(index == -1) {
			return false;
		}
		
		
		return  books.set(index, updateBook) != null ;
	
	}
	
	public boolean deleteBook(Integer id) {
		
		Optional<Book> optBook = getBookById(id);
		if(optBook.isPresent()) {
			return books.remove(optBook.get());
		}
		return false;
	}
	
	
}
