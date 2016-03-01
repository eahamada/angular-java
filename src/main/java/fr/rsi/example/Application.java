package fr.rsi.example;

import static io.undertow.servlet.Servlets.servlet;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.FileResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import java.io.File;

import javax.servlet.ServletException;

import org.glassfish.jersey.servlet.ServletContainer;

public class Application {
	 private static Undertow server;

	    public static void main(String[] args) throws ServletException {
	        startContainer(9090);
	    }

	    public static void stopContainer(){
	        server.stop();
	    }

	    public static void startContainer(int port) throws ServletException {
	        DeploymentInfo servletBuilder = Servlets.deployment().setResourceManager(new FileResourceManager(new File("src/main/webapp"), 1024)).addWelcomePage("index.html");

	        servletBuilder
	                .setClassLoader(Application.class.getClassLoader())
	                .setContextPath("/habilist")
	                .setDeploymentName("habilist.war")
	                .addServlets(servlet("jerseyServlet", ServletContainer.class)
	                        .setLoadOnStartup(1)
	                        .addInitParam("javax.ws.rs.Application", Habilist2Config.class.getName())
	                        .addMapping("/api/*"));

	        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
	        manager.deploy();
	        PathHandler path = Handlers.path(Handlers.redirect("/"))
	                .addPrefixPath("/habilist", manager.start())
	                ;

	        server =
	                Undertow
	                        .builder()
	                        .addHttpListener(port, "localhost")
	                        .setHandler(path)
	                        .build();

	        server.start();
	    }
}
