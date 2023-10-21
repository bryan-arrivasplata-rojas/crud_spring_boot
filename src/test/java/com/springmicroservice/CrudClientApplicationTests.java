package com.springmicroservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudClientApplicationTests {

	@Test
	void contextLoads() {
		CrudClientApplication.main(new String[] {});
	}

}
