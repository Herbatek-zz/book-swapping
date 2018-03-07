package com.piotrek.bookswapping.respositories;

import com.piotrek.bookswapping.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
