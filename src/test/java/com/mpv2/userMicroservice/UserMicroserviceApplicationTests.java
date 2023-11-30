package com.mpv2.userMicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mpv2.userMicroservice.service.HelloWorldService;

@SpringBootTest
class UserMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void helloWorldTest() {
	    // Arrange
		HelloWorldService helloWorldService = new HelloWorldService();
	    // Act
		String result = helloWorldService.getHelloWorld();
	    // Assert
	    assertEquals("Hello, World!", result);
	}

}
