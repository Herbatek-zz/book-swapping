package com.piotrek.bookswapping.respositories;

import com.piotrek.bookswapping.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
