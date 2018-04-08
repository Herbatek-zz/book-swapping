package com.piotrek.bookswapping.respositories;

import com.piotrek.bookswapping.entities.BookForExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookForExchangeRepository extends CrudRepository<BookForExchange, Long> {

    Iterable<BookForExchange> findAllByUserId(Long userId);
}
