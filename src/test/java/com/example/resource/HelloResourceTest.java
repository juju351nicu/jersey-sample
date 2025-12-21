package com.example.resource;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

@SpringBootTest
class HelloResourceTest extends JerseyTest {

	@Test
	public void test2() throws Exception {
		String c = target("/rs/").request().get(String.class);
		assertEquals("Hello Spring Jersey!", c.toString());
	}

	@Override
	protected Application configure() {
		return new ResourceConfig(HelloResource.class);
	}
}
