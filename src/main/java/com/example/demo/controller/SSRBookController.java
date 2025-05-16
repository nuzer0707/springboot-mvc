package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Controller
@RequestMapping("ssr/book")
public class SSRBookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String findAllBooks(Model model) {
		List<Book> books = bookService.findAllBooks();
		
		model.addAttribute("books",books);// 將要傳遞給 jsp 的資料放入 Model 容器中
		
		return "book-list";// 對應到 /WEB-INF/view/book-list.jsp
		
	}
	
	@PostMapping("/add")
	public String addBook(Book book,Model model) {
		try {
			bookService.addBook(book);
		} catch (BookException e) {
			model.addAttribute("message","新增錯誤"+e.getMessage());
			return "error";
		}
		return "redirect:/ssr/book";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Integer id,Model model) {
		try {
			bookService.deleteBook(id);
		} catch (BookException e) {
			model.addAttribute("message","刪除錯誤"+e.getMessage());
			return "error";
		}
		return "redirect:/ssr/book";
	}
	
	
	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable Integer id,Model model) {
		
		try {
			Book book = bookService.getBookById(id);
			model.addAttribute("book",book);
			return "book-edit";
		} catch (BookException e) {
			model.addAttribute("message","查無該筆資料"+e.getMessage());
			return "error";
		}
		
	}
	
	@PutMapping("/edit/{id}")
	public String editBook(@PathVariable Integer id,Book book,Model model) {
		
		try {
			bookService.updateBook(id,book);
		} catch (BookException e) {
			model.addAttribute("message","修改錯誤"+e.getMessage());
			return "error";
		}
		return "redirect:/ssr/book";
	}
	
	
}
