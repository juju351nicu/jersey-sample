package com.example.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Component
@Path("/")
public class HelloResource {

	@GET
	public String index() {
		return "Hello Spring Jersey!";
	}

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> list() {
		return Arrays.asList("a", "b");
	}

	@GET
	@Path("map")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> map() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "000");
		map.put("b", "111");
		return map;
	}

	static public class Person {
		public String name;
		public int age;
	};

	@GET
	@Path("pojo")
	@Produces(MediaType.APPLICATION_JSON)
	public Person pojo() {
		Person person = new Person();
		person.name = "Foo Bar";
		person.age = 30;
		return person;
	}
}
