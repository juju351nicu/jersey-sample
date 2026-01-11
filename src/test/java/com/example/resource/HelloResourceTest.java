//package com.example.resource;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import jakarta.ws.rs.core.Application;
//import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.Response.Status;
//
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@SpringBootTest
//public class HelloResourceTest  extends JerseyTest {
//	
//    @BeforeAll
//    public void before() throws Exception {
//        super.setUp();
//    }
//
//    @AfterAll
//    public void after() throws Exception {
//        super.tearDown();
//    }
//
//    @Override
//    protected Application configure() {
//        return new ResourceConfig(HelloResource.class);
//    }
//
//    @Test
//    public void test() {
//        Response response = target("http://localhost:8080/rs/list").request().get();
//        assertEquals(Status.OK.getStatusCode(), response.getStatus());
//    }
//}
