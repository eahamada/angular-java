package com.example;

import io.undertow.Undertow;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MyResourceTest {
	
	private static final String BASE = "http://localhost:8080";
	private  Undertow server;
    private WebTarget target;
 
    @Before
    public void setUp() throws Exception {
        server =Undertow.builder()
//        		.setHandler()
                .build();
 
        Client c = ClientBuilder.newClient();
        server.start();
    }
 
    @After
    public void tearDown() throws Exception {
        server.stop();
    }
 
    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}