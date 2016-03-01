package fr.rsi.example;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationPath("/api/*")
public class Habilist2Config extends ResourceConfig {  
    public Habilist2Config() {
        packages(true, "fr.rsi.example.api");
    }
}