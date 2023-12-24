package com.BookStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.BookStore.Entity.Book;
import com.BookStore.Entity.MyBookList;
import com.BookStore.Service.BookService;
import com.BookStore.Service.MyBookListService;

@Controller
public class BookController 
{	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService mybookservice;
	
  @GetMapping("/")
  public String home()
  {
	  return "Home";
  }
  @GetMapping("/book_register")
  public String bookRegister()
  {
	  return "bookRegister";
  }
  @GetMapping("/avaliable_books")
  public ModelAndView getAllBook()
  {
	  List<Book> list=service.getAllBook();	  
	  return new ModelAndView("bookList","book",list);
  }
  
  @PostMapping("/save")
  public String addBook(@ModelAttribute Book b)
  {
	  service.save(b);
	  return "redirect:/avaliable_books";  
  }
  
  
  @GetMapping("/my_books")
  public String getMyBooks(Model model)
  {
	  List<MyBookList>list=mybookservice.getAllMyBooks();
	  model.addAttribute("book",list);
	  return "myBooks";
  }
  
  @RequestMapping("/mylist/{id}")
  public String getMyList(@PathVariable("id") int id)
  {
	  Book b=service.getBookById(id);
	  MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
	  mybookservice.saveMyBooks(mb);
		return "redirect:/my_books";  
  }
  
  @RequestMapping("/editBook/{id}")
  public String editBook(@PathVariable("id") int id,Model model)
  {
	  Book b=service.getBookById(id);
	  model.addAttribute("book",b);
	  return "bookEdit";
  }
  
  @RequestMapping("/deleteBook/{id}")
  public String deleteBook(@PathVariable("id") int id)
  {
	    service.deleteById(id);
		return "redirect:/avaliable_books";  
  }
  
}
