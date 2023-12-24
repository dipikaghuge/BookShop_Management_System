package com.BookStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookStore.Entity.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book, Integer>
{
  
}
