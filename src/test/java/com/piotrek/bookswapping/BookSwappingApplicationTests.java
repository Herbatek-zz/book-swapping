package com.piotrek.bookswapping;

import com.piotrek.bookswapping.respositories.BookRepository;
import com.piotrek.bookswapping.respositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookSwappingApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
		assertNotNull(bookRepository);
		assertNotNull(userRepository);
	}

}
