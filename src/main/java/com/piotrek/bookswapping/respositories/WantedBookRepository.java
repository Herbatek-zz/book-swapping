package com.piotrek.bookswapping.respositories;

import com.piotrek.bookswapping.entities.WantedBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WantedBookRepository extends CrudRepository<WantedBook, Long>{
}
