package com.piotrek.bookswapping;

import com.piotrek.bookswapping.respositories.BookForExchangeRepository;
import com.piotrek.bookswapping.respositories.UserRepository;
import com.piotrek.bookswapping.respositories.WantedBookRepository;
import com.piotrek.bookswapping.services.BookForExchangeService;
import com.piotrek.bookswapping.services.UserService;
import com.piotrek.bookswapping.services.WantedBookService;
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
	private UserRepository userRepository;

	@Autowired
	private WantedBookRepository wantedBookRepository;

	@Autowired
	private BookForExchangeRepository bookForExchangeRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private WantedBookService wantedBookService;

	@Autowired
	private BookForExchangeService bookForExchangeService;



	@Test
	public void contextLoads() {
		assertNotNull(userRepository);
		assertNotNull(wantedBookRepository);
		assertNotNull(bookForExchangeRepository);
		assertNotNull(userService);
		assertNotNull(wantedBookService);
		assertNotNull(bookForExchangeService);
	}

}
